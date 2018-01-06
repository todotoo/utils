package org.wg.excel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wg.excel.util.ExcelUtils;
import org.wg.excel.util.UploadFileUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by run on 2018/1/4.
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

    @RequestMapping("readExcel")
    @ResponseBody
    public String readExcel(HttpServletRequest request) throws Exception {
        List<String> pathList = UploadFileUtils.uploadFiles(request);
        List<List<String>> listRow;
        if (pathList != null && pathList.size() > 0) {
            for (String path : pathList) {
                listRow = ExcelUtils.read(path);
//                new ImportExcelUtil().getBankListByExcel()
                for (List<String> row : listRow) {
                    System.out.println(row);
                }
            }
        }
        return "success";
    }
}
