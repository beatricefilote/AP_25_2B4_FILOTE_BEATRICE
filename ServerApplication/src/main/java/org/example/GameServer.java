package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                new ClientThread(clientSocket, this).start();
            }
        } catch (IOException e) {
            System.out.println("Server stopped: " + e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server socket closed.");
            }
        } catch (IOException e) {
            System.out.println("Error closing server socket: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        try {
            gameServer.start(1234);
        } catch (IOException e) {
            System.out.println("Failed to start server: " + e.getMessage());
        }
    }
}