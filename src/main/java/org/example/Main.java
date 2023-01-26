package org.example;
import org.glassfish.tyrus.server.Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {

    public static void main(String[] args) {
                runServer();

    }

    public static void runServer() {

        Server server = new Server("192.168.77.45", 8025, "/websockets", WebSocketServerEndpoint.class);



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