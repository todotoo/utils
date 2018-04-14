package org.wg.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author wg
 * @date 2018-02-08
 */
public class CsvUtil {

    public final static char SEPARATOR = ',';

    public static final Logger logger = LoggerFactory.getLogger(CsvUtil.class);

    public static void main(String[] args) throws Exception {
        // 测试导出
        /*String filePath = "C:/test.csv";
        List<String[]> dataList = new ArrayList<String[]>();
        for (int i = 0; i < 10; i++) {
            dataList.add(new String[] { "0" + i, "小明" + i, "java" + i });
        }
        export(dataList, filePath);
        */
        String filePath = "C:/test.csv";
        String[] headers = new String[]{"编号", "姓名", "课程"};
        List<String[]> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add(new String[]{"0" + i, "小明" + i, "java" + i});
        }
        write(filePath, headers, dataList);
    }

    /**
     * 读取csv文件
     *
     * @param csvFilePath
     * @return
     */
    public static List<String[]> read(String csvFilePath) {
        // 用来保存数据
        List<String[]> dataList = new ArrayList<>();
        CsvReader reader = null;
        try {
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            reader = new CsvReader(csvFilePath, SEPARATOR, Charset.forName("GBK"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                dataList.add(reader.getValues());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return dataList;
    }

    /**
     * 数据写入csv文件中
     *
     * @param dataList 数据集
     * @return 文件路径
     * @throws Exception
     */
    public static String write(String filePath, String[] headers, List<String[]> dataList) throws Exception {
        CsvWriter csvWriter = null;
        try {
            // 创建CSV写对象
            csvWriter = new CsvWriter(filePath, SEPARATOR, Charset.forName("GBK"));
            // 写头
            csvWriter.writeRecord(headers);
            for (String[] row : dataList) {
                csvWriter.writeRecord(row);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (null != csvWriter) {
                csvWriter.close();
            }
        }
        return filePath;
    }

    /**
     * 数据写入csv文件中
     *
     * @param headers    csv文件的第一行文件头
     * @param properties 写入数据对应的属性名
     * @param dataList   数据集
     * @return 文件路径
     * @throws Exception
     */
    public static <T> String write(String filePath, String[] headers, String[] properties, List<T> dataList) throws Exception {
        if (dataList == null) {
            return "";
        }
        CsvWriter writer = new CsvWriter(filePath, SEPARATOR, Charset.forName("GBK"));
        writer.writeRecord(headers);
        for (T bean : dataList) {
            writer.writeRecord(setRowData(properties, bean));
        }
        return filePath;
    }

    /**
     * 设置每行数据
     *
     * @param properties
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> String[] setRowData(String[] properties, T bean) {
        String[] row = new String[properties.length];
        for (int i = 0, j = properties.length; i < j; i++) {
            String cellValue = "";
            String property = properties[i];
            try {
                if (property.indexOf(".") > 0) {
                    String[] params = property.split("\\.");
                    Method getMethod = null;
                    Object value = null;
                    for (int n = 0; n < params.length; n++) {
                        if (n == 0) {
                            getMethod = bean.getClass().getMethod(StringUtils.getMethodName("get", params[n]));
                            value = getMethod.invoke(bean);
                        } else if (n < params.length - 1) {
                            getMethod = value.getClass().getMethod(StringUtils.getMethodName("get", params[n]));
                            value = getMethod.invoke(value);
                        }
                    }
                    if (value != null) {
                        cellValue = getValueByProperty(value, params[params.length - 1]);
                    }
                } else {
                    cellValue = getValueByProperty(bean, property);
                }
                row[i] = cellValue;
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return row;
    }

    /**
     * @param obj
     * @param property
     * @return
     * @throws Exception
     */
    public static String getValueByProperty(Object obj, String property) throws Exception {
        String val = "";
        String pp = property.indexOf("[") > 0 ? property.split("\\[")[0] : property;
        String fmt = property.indexOf("[") > 0 ? property.split("\\[")[1].replace("]", "") : "";

        Method getMethod = obj.getClass().getMethod(StringUtils.getMethodName("get", pp));
        // 获取方法返回类型
        Object value = getMethod.invoke(obj);
        if (value != null) {
            if (value instanceof Date) {
                Date dv = (Date) value;
                if (StringUtils.isEmpty(fmt)) {
                    val = DateUtils.formatDateTime(dv);
                } else {
                    SimpleDateFormat format = new SimpleDateFormat(fmt);
                    val = format.format(dv);
                }
            } else if (value instanceof Enum) {
                Method enumGetMenthod = value.getClass().getMethod("getName");
                Object enumValue = enumGetMenthod.invoke(value);
                val = enumValue == null ? "" : enumValue.toString();
            } else if (!StringUtils.isEmpty(fmt) && "phonefmt".equals(fmt)) {
                if (value.toString().length() >= 8) {
                    val = value.toString().replace(value.toString().substring(3, 8), "****");
                } else {
                    val = value.toString();
                }
            } else {
                val = value.toString();
            }
        } else {
            val = "";
        }
        return val;
    }
}