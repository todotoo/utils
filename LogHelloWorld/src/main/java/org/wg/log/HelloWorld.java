package org.wg.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wg
 */
public class HelloWorld {

    public static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        logger.trace("Trace ...");
        logger.debug("Debug ...");
        logger.info("Info ...");
        logger.warn("Warn ...");
        logger.error("Error ...");
    }
}
