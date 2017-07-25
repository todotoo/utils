/**
 * Copyright ? 2009 www.debug.cn
 * All Rights Reserved
 */
package onther;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 上传文件
 * 
 * @author XuHuiJun
 * 
 */
public class UploadFileServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2774161001869185749L;

	private static String baseDir = "userfiles";

	private static String fileExt = "jpg,jpeg,gif,png";

	private static Long maxSize = 0l;

	// 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录 建议使用按天存

	private static String dirType = "1";

	private static final Log logger = LogFactory
			.getLog(UploadFileServlet.class);

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {

		baseDir = getInitParameter("baseDir");
		if (StringUtils.isBlank(baseDir))
			baseDir = "/userfiles/";
		String realBaseDir = getServletContext().getRealPath(baseDir);
		File baseFile = new File(realBaseDir);
		if (!baseFile.exists()) {
			baseFile.mkdir();
		}
		fileExt = getInitParameter("fileExt");
		if (StringUtils.isBlank(fileExt))
			fileExt = "jpg,jpeg,gif,png";

		String maxSize_str = getInitParameter("maxSize");
		if (StringUtils.isNotBlank(maxSize_str))
			maxSize = 9999l;
		dirType = getInitParameter("dirType");
		if (StringUtils.isBlank(dirType))
			dirType = "1";
		if (",0,1,2,3,".indexOf("," + dirType + ",") < 0)
			dirType = "1";
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("--- UploadFileServlet BEGIN DOPOST ---");

		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		String err = "";
		String newName = "";

		DiskFileUpload upload = new DiskFileUpload();
		try {
			List items = upload.parseRequest(request);

			Map<String, Serializable> fields = new HashMap<String, Serializable>();

			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField())
					fields.put(item.getFieldName(), item.getString());
				else
					fields.put(item.getFieldName(), item);
			}
			FileItem uplFile = (FileItem) fields.get("upload");
			String fileNameLong = uplFile.getName();
			fileNameLong = fileNameLong.replace('\\', '/');
			String[] pathParts = fileNameLong.split("/");
			String fileName = pathParts[pathParts.length - 1];
			String nameWithoutExt = getNameWithoutExtension(fileName);
			String ext = getExtension(fileName);
			// 检查文件类型

			if (("," + fileExt + ",").indexOf("," + ext + ",") < 0) {
				printInfo(response, "不允许上传此类型的文件", "");
				return;
			}
			if (uplFile.getSize() == 0) {
				printInfo(response, "上传文件不能为空", "");
				return;
			}
			if (maxSize > 0 && uplFile.getSize() > maxSize) {
				printInfo(response, "上传文件的大小超出限制", "");
				return;
			}

			// 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录 建议使用按天存
			String fileFolder = "";
			if (dirType.equalsIgnoreCase("1"))
				fileFolder = TimeTools.dateToStr(new Date(), "yyyyMMdd");
			if (dirType.equalsIgnoreCase("2"))
				fileFolder = TimeTools.dateToStr(new Date(), "yyyyMM");
			if (dirType.equalsIgnoreCase("3"))
				fileFolder = ext.toLowerCase();
			String currentPath = baseDir + fileFolder;
			String currentDirPath = getServletContext()
					.getRealPath(currentPath);
			logger.debug("项目路径：" + currentDirPath);
			// FileUtil.makeDirectory(currentDirPath + "/" + fileName);
			File filess = new File(currentDirPath + "/" + fileName);
			filess.mkdirs();
			File pathToSave = new File(currentDirPath, fileName);
			int counter = 1;
			while (pathToSave.exists()) {
				newName = nameWithoutExt + "_" + counter + "." + ext;
				pathToSave = new File(currentDirPath, newName);
				counter++;
			}
			uplFile.write(pathToSave);
			if (StringUtils.isBlank(newName))
				newName = fileName;

			// 这个地方根据项目的不一样，需要做一些特别的定制。
			newName = currentPath + "/" + newName;
			newName = newName.substring(newName.indexOf("/"));

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			newName = "";
			err = "错误 " + ex.getMessage();
		}
		printInfo(response, err, newName);

	}

	public void printInfo(HttpServletResponse response, String err,
			String filename) throws IOException {

		PrintWriter out = response.getWriter();

		out.println("{\"err\":\"" + err + "\",\"msg\":\"" + filename + "\"}");
		out.flush();
		out.close();
	}

	/**
	 * @param fileName
	 * @return
	 */
	private static String getNameWithoutExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * @param fileName
	 * @return
	 */
	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
}
