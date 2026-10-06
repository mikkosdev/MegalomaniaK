package org.mikkosdev.megalomaniak;

import org.mikkosdev.megalomaniak.core.Node;

/**
 * Factory class for creating Megalomaniak clients and servers.
 */
public class Megalomaniak {
    
    public static MegalomaniakClient createClient() {
        return new MegalomaniakClient();
    }

    public static MegalomaniakServer createServer(int nodePort, int clusterPort, Node node) {
        return new MegalomaniakServer(nodePort, clusterPort, node);
    }
}