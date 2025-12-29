package com.server.singlethreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
    public void run() throws IOException {
        int port = 8080;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address,port);
        PrintWriter toserver = new PrintWriter(socket.getOutputStream());
        BufferedReader fromserver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line  = fromserver.readLine();
        System.out.println("response from server : " + line);
        toserver.close();
        fromserver.close();
        socket.close();
    }

    public static void main(String[] args) {
        client client = new client();
        try {
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
