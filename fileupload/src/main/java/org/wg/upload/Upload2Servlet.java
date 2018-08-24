package org.wg.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Upload2Servlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /*
		    	String getName()：获取文件字段的文件名称；
				String getString()：获取字段的内容，如果是文件字段，那么获取的是文件内容，当然上传的文件必须是文本文件；
				String getFieldName()：获取字段名称，例如：<input type=”text” name=”username”/>，返回的是username；
				String getContentType()：获取上传的文件的类型，例如：text/plain。
				int getSize()：获取上传文件的大小；
				boolean isFormField()：判断当前表单字段是否为普通文本字段，如果返回false，说明是文件字段；
				InputStream getInputStream()：获取上传文件对应的输入流；
				void write(File)：把上传的文件保存到指定文件中。
		 */

		/*
		 * 上传三步
		 * 1. 得到工厂
		 * 2. 通过工厂创建解析器
		 * 3. 解析request，得到FileItem集合
		 * 4. 遍历FileItem集合，调用其API完成文件的保存
		 */
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        try {
            List<FileItem> fileItemList = sfu.parseRequest(request);
            FileItem fi1 = fileItemList.get(0);
            FileItem fi2 = fileItemList.get(1);

            System.out.println("普通表单项演示：" + fi1.getFieldName() + "=" + fi1.getString("UTF-8"));
            System.out.println("文件表单项演示：");
            System.out.println("Content-Type: " + fi2.getContentType());
            System.out.println("size: " + fi2.getSize());
            System.out.println("filename: " + fi2.getName());

            // 保存文件
            File destFile = new File("f:/temp/baibing.jpg");
            fi2.write(destFile);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
