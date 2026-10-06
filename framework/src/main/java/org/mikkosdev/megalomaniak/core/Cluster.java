package org.mikkosdev.megalomaniak.core;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private List<AbstractNode> nodes = new ArrayList<AbstractNode>();

    /**
     * This method informs other cluster members (Nodes) of the things that they need to know about:
     * - Cluster node added
     * - Cluster node removed
     * - Updated actor catalog
     */
    public void informCluster() {

    }
}
