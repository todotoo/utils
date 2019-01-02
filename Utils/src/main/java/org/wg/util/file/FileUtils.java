package org.wg.util.file;

import java.io.File;
import java.io.IOException;
import java.util.Collection;


import static org.apache.commons.io.FileUtils.listFiles;
import static org.apache.commons.io.FileUtils.deleteDirectory;

public class FileUtils {

    public static void main(String[] args) throws IOException {
    }


    /**
     * 删除过滤文件
     *
     * @throws IOException
     */
    public static void deleteFilterFile(String filePath, String suffix) throws IOException {
        Collection<File> listFiles = listFiles(new File(filePath),
                new String[]{suffix}, true);
        for (File file : listFiles) {
            deleteDirectory(file);
        }
    }
}
