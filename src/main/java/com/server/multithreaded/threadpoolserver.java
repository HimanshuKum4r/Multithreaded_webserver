package com.server.multithreaded;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadpoolserver {
    private static int port = 8080;
    private static final ExecutorService threadpool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        System.out.println("server started on port : " +port);
        try (ServerSocket serversocket = new ServerSocket(port)){

            while(true){
                Socket client = serversocket.accept();
                System.out.println("client connected" + client.getRemoteSocketAddress());

                threadpool.submit(new clienthandler(client));

            }

        }catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            threadpool.shutdown();
        }
    }

}

