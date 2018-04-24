package org.wg.util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtilsTest {

    @Test
    public void testWriteLines() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("我");
        list.add("是");
        list.add("中");
        list.add("国");
        list.add("人");
        File file = new File("D:/temp/a.txt");
        FileUtils.writeLines(file, "GBK", list);

//        FileUtils.writeLines(file, list, "GBK");
//        FileUtils.writeLines(file, list, "GBK", true);
    }

    @Test
    public void testWrite() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("我");
        list.add("是");
        list.add("中");
        list.add("国");
        list.add("人");
        File file = new File("D:/temp/b.txt");
        for (String str : list) {
            FileUtils.write(file, str, "GBK", true);
        }
    }

    @Test
    public void testWriteStringToFile() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("我");
        list.add("是");
        list.add("中");
        list.add("国");
        list.add("人");
        File file = new File("D:/temp/b.txt");
        for (String str : list) {
            FileUtils.writeStringToFile(file, str, "GBK", true);
        }
    }

    @Test
    public void testReadFileToString() throws IOException {
        File file = new File("D:\\temp\\a.txt");
        String str = FileUtils.readFileToString(file, "GBK");
        System.out.println(str);
    }

    @Test
    public void testReadLines() throws IOException {
        File file = new File("D:\\temp\\a.txt");
        List<String> lines = FileUtils.readLines(file, "GBK");
        for (String line : lines) {
            System.out.println(line);
        }
    }

}
