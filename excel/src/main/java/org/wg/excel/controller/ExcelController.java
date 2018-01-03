package org.wg.excel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wg.excel.util.ExcelReader;
import org.wg.excel.util.UploadFileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by run on 2018/1/4.
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

    @RequestMapping("readExcel")
    public String readExcel(HttpServletRequest request) throws IOException {
        List<String> pathList = UploadFileUtils.uploadFiles(request);
        if (pathList != null && pathList.size() > 0) {
            for (String path : pathList) {
                List<String[]> listRow = ExcelReader.getExcelData(path);
            }
        }
        return "siccess";
    }
}
