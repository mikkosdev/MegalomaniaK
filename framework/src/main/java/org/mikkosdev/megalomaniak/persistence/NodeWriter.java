package org.mikkosdev.megalomaniak.persistence;

public interface NodeWriter {

    public ClusterStatus readClusterStatus();
    public void writeNodeStatus();
}
