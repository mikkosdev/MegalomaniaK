package org.mikkosdev.megalomaniak;

import org.mikkosdev.megalomaniak.core.Node;
import org.mikkosdev.megalomaniak.network.SocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class MegalomaniakServer {

    protected final Logger logger = LoggerFactory.getLogger(MegalomaniakServer.class);
    private Node node = null;
    private int nodePort;
    private int clusterPort;

    public MegalomaniakServer(int nodePort, int clusterPort, Node node) {
        this.node = node;
        this.nodePort = nodePort;
        this.clusterPort = clusterPort;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void start() {
        var nodeListenerThread = Thread.startVirtualThread(() -> new SocketListener(nodePort, node::handleNodeMessage));
        var clusterListenerThread = Thread.startVirtualThread(() -> new SocketListener(clusterPort, node::handleClusterMessage));

        // Display server start message with brief description for ports
        logger.debug("********** Server started on ports <{}> (Node) and <{}> (Cluster) ********** ", nodePort, clusterPort);
        logger.debug("- Node port listens to clients, and cluster port listens to other nodes.", nodePort, clusterPort);
        logger.debug("- Expose node port to load balancer. Cluster port is for inter-node communication.", nodePort, clusterPort);

        try {
            // Wait for threads to finish (they won't)
            nodeListenerThread.join();
            clusterListenerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    private void listen(int port, Consumer<String> handlerMethodRef) {
//        logger.debug("listen() called with port {}", port);
//        ServerSocket socket = null;
//
//        // Start a socket listener
//        try {
//            socket = new ServerSocket(port);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        while (true) {
//            logger.debug("In while loop with port {}", port);
//            try {
//                Socket clientSocket = socket.accept();
//                System.out.println("Node client connected: " + clientSocket.getInetAddress());
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                String resp = null;
//                while ((resp = br.readLine()) != null) {
//                    logger.debug(resp);
//                    handlerMethodRef.accept(resp);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                closeResources(socket);
//            } finally {
//                logger.debug("Server shutdown.");
//            }
//        }
//    }
//
//    // Close resources
//    private void closeResources(ServerSocket socket) {
//        try {
//            if (socket != null && !socket.isClosed()) {
//                socket.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}