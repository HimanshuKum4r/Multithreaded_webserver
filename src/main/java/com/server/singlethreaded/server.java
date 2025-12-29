package com.server.singlethreaded;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public void run() throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
        while(true){
            try {
                System.out.println("server listening on port" + port);
                Socket connectionexcepted = serverSocket.accept();
                System.out.println("connection succesfull from client" + connectionexcepted.getRemoteSocketAddress());
                PrintWriter toclient = new PrintWriter(connectionexcepted.getOutputStream());
                BufferedReader fromserver = new BufferedReader(new InputStreamReader(connectionexcepted.getInputStream()));
                toclient.println("hello from the server");
                toclient.close();
                fromserver.close();
                connectionexcepted.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        server server = new server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
