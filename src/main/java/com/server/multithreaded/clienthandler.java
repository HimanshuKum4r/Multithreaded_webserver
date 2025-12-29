package com.server.multithreaded;

import java.io.*;
import java.net.Socket;

public class clienthandler implements Runnable {

    private final Socket client;

    public clienthandler(Socket client){
        this.client = client;
    }


    @Override
    public void run() {
        try(
                BufferedReader fromclient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter toclient = new PrintWriter(client.getOutputStream(),true);
        ){
            toclient.println("connected to the server");

            String message;
            while((message = fromclient.readLine()) !=null){
                System.out.println("[" + client.getRemoteSocketAddress() + "] " + message);

                if("bye".equalsIgnoreCase(message)){
                    toclient.println("goodbyee");
                    break;
                }
                toclient.println("server receivedd:" +message);
            }
        }catch (IOException e){
            System.out.println("disconnected abruptly");
        }
        finally {
            try {
                client.close();
                System.out.println("connection closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
