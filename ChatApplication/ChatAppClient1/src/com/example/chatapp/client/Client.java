package com.example.chatapp.client;

import com.sun.net.ssl.internal.ssl.Provider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import javax.net.ssl.*;
import javax.net.ssl.SSLServerSocket;
import java.net.Socket;
import java.security.Security;

public class Client {
    public static void main(String args[]) throws Exception
    {   Security.addProvider(new Provider());
        System.setProperty("javax.net.ssl.trustStore","/home/user/Music/Chat App/ChatAppClient1/src/com/example/chatapp/client/ChatAppKeyStore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        System.setProperty("javax.net.debug", "all");

        int port =5000;
        String ip = "127.0.0.1";
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            SSLSocket sk = (SSLSocket) sslSocketFactory.createSocket(ip, port);
            // Socket sk=new Socket("127.0.0.1",5000);
            sk.startHandshake();
            BufferedReader sin=new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintStream sout=new PrintStream(sk.getOutputStream());
            BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
            String s;
            while (  true )
            {
                System.out.print("Client : ");
                s=stdin.readLine();
                sout.println(s);
                if ( s.equalsIgnoreCase("BYE") )
                {
                    System.out.println("Connection ended by client");
                    break;
                }
                s=sin.readLine();
                System.out.print("Server : "+s+"\n");

            }
            sk.close();
            sin.close();
            sout.close();
            stdin.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
