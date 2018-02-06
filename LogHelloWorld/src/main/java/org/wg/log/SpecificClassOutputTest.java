package org.wg.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 特定类的日志输出到指定的日志文件中
 * @author wg
 */
public class SpecificClassOutputTest {

    public static final Logger logger1 = LoggerFactory.getLogger("SpecificClassOutput");
    public static final Logger logger2 = LoggerFactory.getLogger(SpecificClassOutputTest.class);

    public static void main(String[] args) {
        logger1.info("指定文件输出到指定日志文件！");
        logger2.info("指定文件输出到指定日志文件！");
    }
}
