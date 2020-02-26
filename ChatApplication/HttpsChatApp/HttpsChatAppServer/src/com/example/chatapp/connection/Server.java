package com.example.chatapp.connection;

import java.io.*;
import java.net.*;
import java.util.*;
import java.net.InetSocketAddress;
import java.lang.*;
import com.sun.net.httpserver.HttpsServer;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import com.sun.net.httpserver.*;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsExchange;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Server {

    private static HashMap<String, ArrayList> clients = new HashMap<>();

    public static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response ....Hiiii!";
            HttpsExchange httpsExchange = (HttpsExchange) t;
            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static void main(String[] args) throws Exception {

        try {

            InetSocketAddress address = new InetSocketAddress(8080);
            HttpsServer httpsServer = HttpsServer.create(address, 0);
            SSLContext sslContext = SSLContext.getInstance("TLS");


            char[] password = "password".toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream("src/testkey.jks");
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
                        System.out.println("ok to create HTTPS port");

                    } catch (Exception ex) {
                        System.out.println("Failed to create HTTPS port");
                    }
                }
            });

            httpsServer.createContext("/inbox", new getMyMessage());
            httpsServer.createContext("/list", new listofClient());
            httpsServer.createContext("/register", new registerClient());
            httpsServer.createContext("/send", new sendMessages());
            httpsServer.createContext("/test", new MyHandler());
            httpsServer.createContext("/disconnect", new disconnect());
            httpsServer.setExecutor(null);
            httpsServer.start();
            System.out.println("server started ");

        } catch (Exception exception) {
            System.out.println("Failed to create HTTPS server on port " + 8080 + " of localhost");
            exception.printStackTrace();

        }
    }
    }

    public static class registerClient implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = httpExchange.getRequestURI();
            String query = requestedUri.getRawQuery();

            String clientName = query.substring(query.indexOf("=") + 1, query.length());
            System.out.println(clientName);

            String response = "You are registered !";

            if (clients.keySet().stream().anyMatch(clientName::equals)) {
                System.out.println("client Already Present !");
                response = "client Already Present !";
            } else {
                clients.put(clientName, new ArrayList());
            }

            System.out.println(clients);

            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.toString().getBytes());

            outputStream.close();
        }
    }


    public static class listofClient implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            // parse request
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();

            String clientName = query.substring(query.indexOf("=") + 1, query.length());
            System.out.println(clientName);

            String response = "";

            for (String name : clients.keySet()) {
                if (clientName.equals(name)) {
                    response += clientName + "(You)\n";
                } else {
                    response += name + "\n";
                }
            }

            System.out.println(clients + "Sended");
            // send response
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
                System.out.println(clientName);

                String response = "No messages for this clients";
                if (clients.get(clientName).size() > 1) {
                    for (String name : clients.keySet()) {
                        if (clientName.equals(name)) {
                            response += clients.get(name).get(1);
                            clients.get(name).set(1, "");
                        }
                    }
                }

                System.out.println(clients + "Sended");
                // send response
                he.sendResponseHeaders(200, response.length());
                OutputStream outputStream = he.getResponseBody();
                outputStream.write(response.toString().getBytes());

                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class sendMessages implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {

            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();

            System.out.println(parameters);
            try {
                Matcher matcher2 = Pattern.compile("message=(?<msg>\\w*)&receiver=(?<rec>\\w*)&sender=(?<sen>\\w*)").matcher(query);
                if (matcher2.find()) {
                    System.out.println("message: " + matcher2.group("msg"));
                    System.out.println("receiver name: " + matcher2.group("rec"));
                    System.out.println("sender name: " + matcher2.group("sen"));

                    String message = matcher2.group("msg");
                    String receiver = matcher2.group("rec");
                    String sender = matcher2.group("sen");


                    clients.keySet().forEach(name -> {
                        if (receiver.equals(name)) {
                            clients.get(name).add(0, new ArrayList<String>());
                            clients.get(name).add(1, sender + "->" + message);
                        }
                    });

                    System.out.println(clients.values());

                    String response = "messeges sended to " + receiver;

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

    public static class disconnect implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {

            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();

            System.out.println(parameters);
            try {
                String clientName = query.substring(query.indexOf("=") - 1, query.length());
                System.out.println(clientName);
                clients.remove(clientName);

                String response = "User doesn't exist";
                response = "Disconnected";

                he.sendResponseHeaders(200, response.length());
                OutputStream os = he.getResponseBody();
                os.write(response.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                he.close();
            }


        }

    }

}