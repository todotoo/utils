package org.wg.download;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class DownloadServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * 两个头一个流
		 * 1. Content-Type
		 * 2. Content-Disposition
		 * 3. 流：下载文件的数据
		 */
        //第二读取文件
        String filePath = getServletContext().getRealPath("/download/test.csv");
        // 为了使下载框中显示中文文件名称不出乱码！
        String framename = filenameEncoding("xxx.csv", request);
        // 通过文件名称获取MIME类型
        String contentType = this.getServletContext().getMimeType(filePath);
        // 给出下载框中的文件名称
        String contentDisposition = "attachment;filename=" + framename;
        // 设置头
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Disposition", contentDisposition);
        // 应用程序强制下载
        response.setContentType("application/force-download");
        // 一个流
        FileInputStream input = new FileInputStream(filePath);
        // 获取绑定了响应端的流
        ServletOutputStream output = response.getOutputStream();
        // 把输入流中的数据写入到输出流中。
        IOUtils.copy(input, output);
        output.close();
        input.close();
    }

    /**
     * 用来对下载的文件名称进行编码的！
     */
    public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
        // 获取浏览器
        String agent = request.getHeader("User-Agent");
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
        } else if (agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
