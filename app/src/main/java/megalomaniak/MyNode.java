package megalomaniak;

import org.mikkosdev.megalomaniak.core.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyNode extends Node {
    protected final Logger logger = LoggerFactory.getLogger(Node.class);

    @Override
    public void handleNodeMessage(String data) {
        logger.debug("MyNode.handleNodeMessage()");
    }
}
