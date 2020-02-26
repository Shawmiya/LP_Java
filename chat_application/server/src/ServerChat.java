import java.io.*;
import java.net.*;
import java.lang.*;

import com.sun.net.httpserver.HttpsServer;

import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import com.sun.net.httpserver.*;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLContext;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsExchange;


public class ServerChat {
	
	 private static HashMap<String, ArrayList> userList = new HashMap<>();


	  public static class MyHandler implements HttpHandler {
	    @Override
	    public void handle(HttpExchange t) throws IOException {
	      String response = "Welcome to the chat group server !";
	      HttpsExchange httpsExchange = (HttpsExchange) t;
	      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
	      t.sendResponseHeaders(200, response.getBytes().length);
	      OutputStream os = t.getResponseBody();



	      os.write(response.getBytes());
	      os.close();
	    }
	  }


	  public static class registerUser implements HttpHandler {
	    @Override
	    public void handle(HttpExchange he) throws IOException {
	      
	      Map<String, Object> parameters = new HashMap<String, Object>();
	      URI requestedUri = he.getRequestURI();
	      String query = requestedUri.getRawQuery();


	      String clientName = query.substring(query.indexOf("=") + 1, query.length());
	      

	      String response = "You are registered as "+ clientName + " with 127.0.0.1 ip and port number 8090" ;

	      
	      if (userList.keySet().stream().anyMatch(clientName::equals)) {
	        System.out.println("you are already in!");
	        response = "you are already in!";
	      } else {
	        userList.put(clientName, new ArrayList());
	      }

	      System.out.println(response);

	      he.sendResponseHeaders(200, response.length());
	      OutputStream os = he.getResponseBody();
	      os.write(response.toString().getBytes());

	      os.close();
	    }
	  }


	  public static class listUsers implements HttpHandler {
	    @Override
	    public void handle(HttpExchange he) throws IOException {
	      
	      Map<String, Object> parameters = new HashMap<String, Object>();
	      URI requestedUri = he.getRequestURI();
	      String query = requestedUri.getRawQuery();

	      String clientName = query.substring(query.indexOf("=") + 1, query.length());
	      System.out.println(clientName);

	     
	      String response = "";

	      for (String name : userList.keySet()) {
	        if (clientName.equals(name)) {
	          response += clientName + "(You)\n";
	        } else {
	          response += name + "\n";
	        }
	      }

	      System.out.println(userList + "Sent");
	      
	      he.sendResponseHeaders(200, response.length());
	      OutputStream os = he.getResponseBody();
	      os.write(response.toString().getBytes());

	      os.close();
	    }
	  }

	  public static class getMyMessage implements HttpHandler {
	    @Override
	    public void handle(HttpExchange he) throws IOException {
	      try {
	        
	        Map<String, Object> parameters = new HashMap<String, Object>();
	        URI requestedUri = he.getRequestURI();
	        String query = requestedUri.getRawQuery();

	        String clientName = query.substring(query.indexOf("=") + 1, query.length());
	        

	        
	        String response = "no";
	        if ( userList.get(clientName).size() >1) {
	          for (String name : userList.keySet()) {
	            if (clientName.equals(name)) {
	              response += userList.get(name).get(1);
	              userList.get(name).set(1, "");
	            }
	          }
	        }


	        he.sendResponseHeaders(200, response.length());
	        OutputStream os = he.getResponseBody();
	        os.write(response.toString().getBytes());

	        os.close();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
	  }

	  public static class sendMessages implements HttpHandler {
	    @Override
	    public void handle(HttpExchange he) throws IOException {
	      // parse request
	      Map<String, Object> parameters = new HashMap<String, Object>();
	      URI requestedUri = he.getRequestURI();
	      String query = requestedUri.getRawQuery();

	      System.out.println(parameters);
	      try {
	        Matcher matcher2 = Pattern.compile("message=(?<msg>\\w*)&receiver=(?<rec>\\w*)&sender=(?<sen>\\w*)").matcher(query);
	        if (matcher2.find()) {
	         

	          String message = matcher2.group("msg");
	          String receiver = matcher2.group("rec");
	          String sender = matcher2.group("sen");


	          userList.keySet().forEach(name -> {
	            if (receiver.equals(name)) {
	              userList.get(name).add(0, new ArrayList<String>());
	              userList.get(name).add(1, sender + "->" + message);
	            }
	          });

	        

	          String response = "messege sent to " + receiver;


	          
	          he.sendResponseHeaders(200, response.length());
	          OutputStream os = he.getResponseBody();
	          os.write(response.toString().getBytes());

	          os.close();
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }


	    }
	  }

	  public static void main(String[] args) throws Exception {

	    try {

	      InetSocketAddress address = new InetSocketAddress(8090);


	      HttpsServer httpsServer = HttpsServer.create(address, 0);
	      SSLContext sslContext = SSLContext.getInstance("TLS");


	      char[] password = "password".toCharArray();
	      KeyStore ks = KeyStore.getInstance("JKS");
	      FileInputStream fis = new FileInputStream("src/server.jks");
	      ks.load(fis, password);


	      KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	      kmf.init(ks, password);


	      TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
	      tmf.init(ks);


	      sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);


           
	      httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
	        public void configure(HttpsParameters params) {
	          try {

	            SSLContext context = getSSLContext();
	            SSLEngine engine = context.createSSLEngine();
	            params.setNeedClientAuth(false);
	            params.setCipherSuites(engine.getEnabledCipherSuites());
	            params.setProtocols(engine.getEnabledProtocols());


	            SSLParameters sslParameters = context.getSupportedSSLParameters();
	            params.setSSLParameters(sslParameters);

	          } catch (Exception ex) {
	            System.out.println("Failed to create HTTPS port");
	          }
	        }
	      });
	      
	      httpsServer.createContext("/inbox", new getMyMessage());
	      httpsServer.createContext("/list", new listUsers());
	      httpsServer.createContext("/register", new registerUser());
	      httpsServer.createContext("/send", new sendMessages());
	      httpsServer.createContext("/test", new MyHandler());
	      httpsServer.setExecutor(null); // creates a default executor
	      httpsServer.start();
	      System.out.println("server started ");
	    } catch (Exception exception) {
	      System.out.println("Failed to create HTTPS server on port " + 8090 + " of localhost");
	      exception.printStackTrace();

	    }
	  }
	
	

}
