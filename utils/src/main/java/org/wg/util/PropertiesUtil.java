package org.wg.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
    private static Map<String, PropertiesUtil> instanceMap = new HashMap<String, PropertiesUtil>();
    private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);
    private Properties properties = null;
    private ClassLoader oClassLoader = null;
    private String propertyFileName;

    //定义私有构造方法（防止通过 new PropertiesUtil()去实例化）
    private PropertiesUtil(String propertyFileName) {
        this.propertyFileName = propertyFileName;
        loadProperties();
    }

    /**
     * 获取单例对象
     *
     * @param propertyFileName 文件名称
     * @return
     */
    public static PropertiesUtil getInstance(String propertyFileName) {
        if (instanceMap.get(propertyFileName) != null) {
            return (PropertiesUtil) instanceMap.get(propertyFileName);
        }
        //实例化
        PropertiesUtil instance = new PropertiesUtil(propertyFileName);
        instanceMap.put(propertyFileName, instance);

        return instance;
    }

    public static void main(String[] args) {
//		PropertiesUtil.getInstance("message").setProperty("NO", "no");
        System.out.println(PropertiesUtil.getInstance("message").getPropertyAsString("CHINESE.DREAM"));
    }

    /**
     * 加载properties文件
     */
    private void loadProperties() {
        try {
            this.properties = new Properties();
            this.oClassLoader = Thread.currentThread().getContextClassLoader();

            InputStream is = oClassLoader.getResourceAsStream(this.propertyFileName + ".properties");
            if (is != null) {
                this.properties.load(is);
                is.close();
            }
            is = null;
        } catch (Exception e) {
            logger.error("找不到指定的文件,查看文件名是否正确:" + propertyFileName + e.getMessage(), e);
        }
    }

    /**
     * 获取文件属性值
     *
     * @param propertyName 属性名
     * @param defaultValue 默认值
     * @return
     */
    public String getPropertyAsString(String propertyName, String defaultValue) {
        try {
            if (this.properties == null) {
                loadProperties();
            }
            return this.properties.getProperty(propertyName, defaultValue);
        } catch (Exception e) {
            logger.error(propertyName + "属性文件读取错误" + e.getMessage(), e);
        }
        return defaultValue;
    }

    /**
     * 获取String类型的文件属性值
     *
     * @param propertyName
     * @return
     */
    public String getPropertyAsString(String propertyName) {
        return getPropertyAsString(propertyName, null);
    }

    /**
     * 获取Int类型的文件属性值
     *
     * @param propertyName
     * @param defaultValue 默认值
     * @return
     */
    public int getPropertyAsInt(String propertyName, int defaultValue) {
        try {
            if (this.properties == null) {
                loadProperties();
            }
            String sProperty = this.properties.getProperty(propertyName);

            return Integer.parseInt(sProperty);
        } catch (Exception e) {
            logger.error(propertyName + "属性文件读取错误" + e.getMessage(), e);
        }
        return defaultValue;
    }

    /**
     * 获取Int类型的文件属性值
     *
     * @param propertyName
     * @return
     */
    public int getPropertyAsInt(String propertyName) {
        return getPropertyAsInt(propertyName, 0);
    }

    /**
     * 修改文件属性值
     *
     * @param propertyName  属性名
     * @param propertyValue 属性值
     */
    public void setProperty(String propertyName, String propertyValue) {
        try {
            if (this.properties == null) {
                loadProperties();
            }
            String filePath = String.valueOf(this.oClassLoader.getResource("")) + this.propertyFileName + ".properties";
            filePath = filePath.replaceAll("file:/", "");
            filePath = filePath.replaceAll("%20", " ");
            if (filePath.indexOf(":") != 1) {
                filePath = File.separator + filePath;
            }
            OutputStream fos = new FileOutputStream(filePath);

            properties.setProperty(propertyName, propertyValue);
            properties.store(fos, "Update ‘" + propertyName + "‘ value");

            if (fos != null) {
                fos.close();
            }
            fos = null;
        } catch (Exception e) {
            logger.error(propertyName + "属性文件更新错误" + e.getMessage(), e);
        }
    }
}