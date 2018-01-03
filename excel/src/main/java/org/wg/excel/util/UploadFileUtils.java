package org.wg.excel.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 文件上传，支持多个文件一起
 */
public class UploadFileUtils {

    /**
     * 上传文件，返回文件路径集合
     *
     * @param request
     * @param response
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static List<String> uploadFiles(HttpServletRequest request)
            throws IllegalStateException, IOException {

        List<String> list = new ArrayList<String>();
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        String suffix = myFileName.substring(myFileName.lastIndexOf("."));
                        String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(100)) + suffix;
                        String uploadDir = request.getSession().getServletContext().getRealPath("upload");
                        File uploadDirFile = new File(uploadDir);
                        if (!uploadDirFile.exists()) {
                            uploadDirFile.mkdirs();
                        }
                        String path = request.getSession().getServletContext().getRealPath("upload") + File.separator + fileName;
                        File localFile = new File(path);

                        file.transferTo(localFile);

                        list.add(localFile.getAbsolutePath());
                    }
                }
            }
        }
        return list;
    }
}
