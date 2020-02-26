package com.example.chatapp.connection;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Client
{

    private static String ip = "localhost", name = "saman", port = "8080";
    private static boolean isConnected=false;

    static {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {

                    public boolean verify(String hostname,
                                          javax.net.ssl.SSLSession sslSession) {
                        if (hostname.equals("127.0.0.1")) {
                            return true;
                        }
                        return false;
                    }
                });
    }


    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("please use 'connect ip:port as name' format");
        String command = scanner.nextLine();
        Pattern regex1 = Pattern.compile("^connect " +
                "(?<ip>((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))" +
                ":(?<port>\\d{4})" +
                " as " +
                "(?<name>\\w*)$");
        Matcher matches = regex1.matcher(command);

        if (matches.find()) {
            ip = matches.group("ip");
            name = matches.group("name");
            port = matches.group("port");

            System.out.println("ip: " + ip);
            System.out.println("name: " + name);
            System.out.println("port: " + port);


            System.setProperty("javax.net.ssl.trustStore", "src/testkey.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "password");

            String httpsURL = "https://" + ip + ":" + port + "/register?name=" + name;
            URL url = new URL(httpsURL);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

            InputStream inputStream = httpsURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
            }
            bufferedReader.close();

            new Thread(() -> {
                Scanner send = new Scanner(System.in);
                System.out.println("type 'list' to find other clients");
                System.out.println("Now you can Send messages by send <message> name");
                while (true) {
                    System.out.print(":");
                    String sendToServer = send.nextLine();

                    if (sendToServer.equals("list")) {
                        getList();
                    }

                    if (Pattern.matches("send (?<msg>.*) to (?<name>.*)", sendToServer)) {
                        sendToServer(sendToServer);
                    }

                    if (sendToServer.equals("inbox")) {
                        checkInbox();
                    }
                }
            }).start();


            new Thread(() -> {
                while (true) {

                    try {
                        Thread.sleep(15000);
                        String httpsURL1 = "https://127.0.0.1:8080/inbox?name=" + name;
                        URL url1 = new URL(httpsURL1);
                        HttpsURLConnection conn1 = (HttpsURLConnection) url1.openConnection();

                        InputStream is1 = conn1.getInputStream();
                        InputStreamReader isr1 = new InputStreamReader(is1);
                        BufferedReader br1 = new BufferedReader(isr1);
                        String inputLine1;
                        while ((inputLine1 = br1.readLine()) != null) {
                            if (!inputLine1.equals("no"))
                                System.out.println(inputLine1.substring(2,inputLine1.length()));
                        }
                        br1.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } else {
            System.out.println("Wrong Command !");
        }
    }

    private static void sendToServer(String sendToServer) {
        try {
            System.out.println("send message");
            System.out.println(sendToServer);

            Matcher matcher2 = Pattern.compile("send (?<msg>.*)->(?<name>.*)").matcher(sendToServer);
            if (matcher2.find()) {
                System.out.println("message: " + matcher2.group("msg"));
                System.out.println("receiver name: " + matcher2.group("name"));
                String message = matcher2.group("msg");
                String receiver = matcher2.group("name");
                String sender = name;
                System.out.println("sender :" + name);

                String httpsURL1 = "https://127.0.0.1:8080/send?message=" + message
                        + "&receiver=" + receiver
                        + "&sender=" + sender;
                URL url1 = new URL(httpsURL1);
                HttpsURLConnection conn1 = (HttpsURLConnection) url1.openConnection();

                //get response sout
                InputStream is1 = conn1.getInputStream();
                InputStreamReader isr1 = new InputStreamReader(is1);
                BufferedReader br1 = new BufferedReader(isr1);
                String inputLine1;
                while ((inputLine1 = br1.readLine()) != null) {
                    System.out.println(inputLine1);
                }
                br1.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getList() {
        try {
            System.out.println(".....Client list.....");
            String httpsURL1 = "https://127.0.0.1:8080/list?name=" + name;
            URL url1 = new URL(httpsURL1);
            HttpsURLConnection conn1 = (HttpsURLConnection) url1.openConnection();

            //get response sout
            InputStream is1 = conn1.getInputStream();
            InputStreamReader isr1 = new InputStreamReader(is1);
            BufferedReader br1 = new BufferedReader(isr1);
            String inputLine1;
            while ((inputLine1 = br1.readLine()) != null) {
                System.out.println(inputLine1);
            }
            br1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkInbox() {
        try {
            String httpsURL1 = "https://127.0.0.1:8080/inbox?name=" + name;
            URL url1 = new URL(httpsURL1);
            HttpsURLConnection conn1 = (HttpsURLConnection) url1.openConnection();

            InputStream is1 = conn1.getInputStream();
            InputStreamReader isr1 = new InputStreamReader(is1);
            BufferedReader br1 = new BufferedReader(isr1);
            String inputLine1;
            while ((inputLine1 = br1.readLine()) != null) {
                if (!inputLine1.equals("no")) {
                    System.out.println(inputLine1);
                } else {
                    System.out.println("NO messages !");
                }
            }
            br1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void disconnect() {
        try {
            String httpsURL1 = "https://127.0.0.1:8080/disconnect?name=" + name;
            URL url1 = new URL(httpsURL1);
            HttpsURLConnection conn1 = (HttpsURLConnection) url1.openConnection();
            InputStream is1 = conn1.getInputStream();
            InputStreamReader isr1 = new InputStreamReader(is1);
            BufferedReader br1 = new BufferedReader(isr1);
            String inputLine1;
            while ((inputLine1 = br1.readLine()) != null) {
                if (!inputLine1.equals("Disconnected")) {
                    System.out.println(inputLine1);
                    isConnected = false;
                    name = null;
                } else {
                    System.out.println("Still connected !");
                }
            }
            br1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


