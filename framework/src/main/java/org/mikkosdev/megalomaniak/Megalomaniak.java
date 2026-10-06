package org.mikkosdev.megalomaniak;

import org.mikkosdev.megalomaniak.core.AbstractNode;

/**
 * Factory class for creating Megalomaniak clients and servers.
 */
public class Megalomaniak {
    
    public static MegalomaniakClient createClient() {
        return new MegalomaniakClient();
    }

    public static MegalomaniakServer createServer(int nodePort, int clusterPort, AbstractNode node) {
        return new MegalomaniakServer(nodePort, clusterPort, node);
    }
}