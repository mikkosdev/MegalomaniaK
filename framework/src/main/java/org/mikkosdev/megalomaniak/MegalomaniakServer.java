package org.mikkosdev.megalomaniak;
import org.mikkosdev.megalomaniak.core.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MegalomaniakServer {

    protected final Logger logger = LoggerFactory.getLogger(MegalomaniakServer.class);
    private ServerSocket nodeSocket = null;
    private ServerSocket clusterSocket = null;

    private Node node = new Node();

    public MegalomaniakServer(int nodePort, int clusterPort) {
        // Start a socket listener
        try {
            nodeSocket = new ServerSocket(nodePort);
            clusterSocket = new ServerSocket(clusterPort);
            logger.debug("********** Server started on ports <{}> (Node) and <{}> (Cluster) ********** ", nodePort, clusterPort);
            logger.debug("- Node port listens to clients, and cluster port listens to other nodes.", nodePort, clusterPort);
            logger.debug("- Expose node port to load balancer. Cluster port is for inter-node communication.", nodePort, clusterPort);

            mainLoop();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeResources();
            logger.debug("Server shutdown.");
        }
    }

    private void mainLoop() {
        while (true) {
            try {
                Socket clientSocket = nodeSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                //node.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeResources() {
        // Close resources
        try {
            if (nodeSocket != null && !nodeSocket.isClosed()) {
                nodeSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}