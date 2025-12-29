package com.server.multithreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException {

        Socket server = new Socket("localhost",8080);

        BufferedReader fromserver = new BufferedReader(new InputStreamReader(server.getInputStream()));

        PrintWriter toserver = new PrintWriter(server.getOutputStream(),true);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String input;

        while((input=console.readLine())!=null){
            toserver.println(input);
            System.out.println("server :" + fromserver.readLine());

            if ("bye".equalsIgnoreCase(input)){
                break;
            }
            server.close();
        }






    }
}
