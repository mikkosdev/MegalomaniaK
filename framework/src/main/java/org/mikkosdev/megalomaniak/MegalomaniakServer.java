package org.mikkosdev.megalomaniak;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MegalomaniakServer {

    public MegalomaniakServer(int port) {
        ServerSocket serverSocket = null;

        // Start a socket listener
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            // Keep the server running
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}