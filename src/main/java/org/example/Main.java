package org.example;
//package com.shekhar.wordgame.server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.glassfish.tyrus.server.Server;
public class Main {

    public static void main(String[] args) {
                runServer();

    }

    public static void runServer() {

        Server server = new Server("localhost", 8025, "/websockets", WebSocketServerEndpoint.class);



        try {

            server.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Please press a key to stop the server.");

            reader.readLine();

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            server.stop();

        }

    }

}