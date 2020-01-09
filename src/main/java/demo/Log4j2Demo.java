package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Demo {

    private static Logger logger = LogManager.getLogger(Log4j2Demo.class);

    public static void main(String[] args) {
        System.out.println(" \n Testing Log messages \n");

        logger.trace("message");
        logger.info("info message");
        logger.error("error message");
        logger.warn("warning message");
        logger.fatal("fatal message");

        System.out.println(" \n End of the Log messages \n");
    }
}
