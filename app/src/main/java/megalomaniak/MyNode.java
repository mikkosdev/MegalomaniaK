package megalomaniak;

import org.mikkosdev.megalomaniak.core.AbstractNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyNode extends AbstractNode {
    protected final Logger logger = LoggerFactory.getLogger(MyNode.class);

    @Override
    public void handleNodeMessage(String data) {
        logger.debug("MyNode.handleNodeMessage()");
    }
}
