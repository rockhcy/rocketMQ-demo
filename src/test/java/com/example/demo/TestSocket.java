package com.example.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther hcy
 * @create 2020-11-06 15:28
 * @Description
 */

public class TestSocket {
    private static List<Socket> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                clients.add( socket);

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

