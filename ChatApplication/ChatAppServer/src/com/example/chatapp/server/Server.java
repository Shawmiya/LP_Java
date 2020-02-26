package com.example.chatapp.server;
import com.sun.net.ssl.internal.ssl.Provider;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Security;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Server {
    int port =5000;
    ServerSocket server=null;
    Socket client=null;
    ExecutorService pool = null;
    int clientcount=0;

    public static void main(String[] args) throws IOException {
        Security.addProvider(new Provider());
        System.setProperty("javax.net.ssl.keyStore","/home/user/Music/Chat App/ChatAppServer/src/com/example/chatapp/server/ChatAppKeyStore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");
        System.setProperty("javax.net.debug", "all");


        Server serverobj=new Server(5000);
        serverobj.startServer();

        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        SSLServerSocket sslServerSocket =(SSLServerSocket)sslServerSocketFactory.createServerSocket(5000);
        SSLSocket sslSocket= (SSLSocket)sslServerSocket.accept();
    }

    Server(int port){
        this.port=port;
        pool = Executors.newFixedThreadPool(5);
    }

    public void startServer() throws IOException {

        //server=new ServerSocket(5000);
        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        SSLServerSocket sslServerSocket =(SSLServerSocket)sslServerSocketFactory.createServerSocket(5000);
        SSLSocket sslSocket= (SSLSocket)sslServerSocket.accept();
        System.out.println("Server Booted");
        System.out.println("Any client can stop the server by sending -1");
        while(true)
        {
            client = sslServerSocket.accept();
            clientcount++;
            ServerThread runnable= new ServerThread(client,clientcount,this);
            pool.execute(runnable);
        }

    }

    private static class ServerThread implements Runnable {

        Server server=null;
        Socket client=null;
        BufferedReader bufferedReader;
        PrintStream printStream;
        Scanner sc=new Scanner(System.in);
        int id;
        String s;

        ServerThread(Socket client, int count ,Server server ) throws IOException {

            this.client=client;
            this.server=server;
            this.id=count;
            System.out.println("Connection "+id+" as "+client);

            bufferedReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            printStream=new PrintStream(client.getOutputStream());

        }

        @Override
        public void run() {
            int x=1;
            try{
                while(true){
                    s=bufferedReader.readLine();

                    System. out.print("Client("+id+") :"+s+"\n");
                    System.out.print("Server : ");
                    //s=stdin.readLine();
                    s=sc.nextLine();
                    if (s.equalsIgnoreCase("bye"))
                    {
                        printStream.println("BYE");
                        x=0;
                        System.out.println("Connection ended by server");
                        break;
                    }
                    printStream.println(s);
                }


                bufferedReader.close();
                client.close();
                printStream.close();
                if(x==0) {
                    System.out.println( "Server cleaning up." );
                    System.exit(0);
                }
            }
            catch(IOException ex){
                System.out.println("Error : "+ex);
            }


        }
    }
}
