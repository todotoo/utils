package org.wg.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
}