package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server. Enter commands (type 'exit' to quit):");

            String userInput;
            while (true) {
                userInput = consoleIn.readLine();
                if (userInput == null || "exit".equalsIgnoreCase(userInput)) {
                    break;
                }
                out.println(userInput);
                String response = in.readLine();
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
        System.out.println("Client exited.");
    }
}