package org.mikkosdev.megalomaniak.network;

import org.mikkosdev.megalomaniak.core.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class SocketListener {

    protected final Logger logger = LoggerFactory.getLogger(SocketListener.class);

    public SocketListener(int port, Consumer<String> handlerMethodRef) {
        listen(port, handlerMethodRef);
    }

    private void listen(int port, Consumer<String> handlerMethodRef) {
        logger.debug("listen() called with port {}", port);
        ServerSocket socket = null;

        // Start a socket listener
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            logger.debug("In while loop with port {}", port);
            try {
                Socket clientSocket = socket.accept();
                System.out.println("Node client connected: " + clientSocket.getInetAddress());

                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String resp = null;
                while ((resp = br.readLine()) != null) {
                    logger.debug(resp);
                    handlerMethodRef.accept(resp);
                }
            } catch (IOException e) {
                e.printStackTrace();
                closeResources(socket);
            } finally {
                logger.debug("Server shutdown.");
            }
        }
    }

    // Close resources
    private void closeResources(ServerSocket socket) {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
