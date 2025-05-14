package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private GameServer gameServer;

    public ClientThread(Socket socket, GameServer gameServer) {
        this.socket = socket;
        this.gameServer = gameServer;
        setDaemon(true);
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received command: " + request);
                if ("stop".equalsIgnoreCase(request)) {
                    out.println("Server stopped");
                    gameServer.stop();
                    break;
                } else {
                    out.println("Server received the request: " + request);
                }
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println("Client disconnected: " + socket);
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}