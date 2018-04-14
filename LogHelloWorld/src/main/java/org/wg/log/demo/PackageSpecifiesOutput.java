package org.wg.log.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 指定包的日志输出到指定文件中
 * @author wg
 */
public class PackageSpecifiesOutput {

    public static final Logger logger = LoggerFactory.getLogger(PackageSpecifiesOutput.class);

    public static void main(String[] args) {
        logger.info("指定包的日志输出到指定文件中！！！");
    }
}
