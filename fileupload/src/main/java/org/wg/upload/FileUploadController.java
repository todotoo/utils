package org.wg.upload;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wg.utils.ImageUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * SpringMVC实现文件上传
 */
@Controller
@RequestMapping("fileUpload")
public class FileUploadController {

	/**
	 * 上传
	 *
	 * @param uploadFile 接收上传文件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String upload(@RequestParam(value = "image", required = true) MultipartFile uploadFile) throws Exception {
		Map<String, Object> output = new HashMap<>();
		//原始名称
		String originalFilename = uploadFile.getOriginalFilename();
		try {
			//上传图片
			if (originalFilename != null && originalFilename.length() > 0) {
				//存储图片的物理路径
				String savePath = "/home/webapps/filestore/images/";
				File savePathFile = new File(savePath);
				if (!savePathFile.exists()) {
					savePathFile.mkdirs();
				}
				//新的图片名称
				String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				String picPath = savePath + newFileName;
				//新图片
				File newFile = new File(picPath);
				//将内存中的数据写入磁盘
				uploadFile.transferTo(newFile);

				output.put("status", "0");
				output.put("infoStr", "上传文件成功");
				output.put("path", picPath);
				output.put("imgPath", newFileName);
			}
		} catch (Exception e) {
			output.put("status", "-10");
			output.put("infoStr", "上传文件失败");
		}
		return JSON.toJSONString(output);
	}

	/**
	 * 上传
	 *
	 * @param uploadFile 接收上传文件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload2", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String upload2(@RequestParam(value = "image", required = true) MultipartFile uploadFile) throws Exception {
		Map<String, Object> output = new HashMap<>();
		//原始名称
		String originalFilename = uploadFile.getOriginalFilename();
		byte[] bytes = uploadFile.getBytes();
		try {
			//上传图片
			if (originalFilename != null && originalFilename.length() > 0) {
				//存储图片的物理路径
				String savePath = "/home/webapps/filestore/images/";
				File savePathFile = new File(savePath);
				if (!savePathFile.exists()) {
					savePathFile.mkdirs();
				}
				//新的图片名称
				String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				String picPath = savePath + newFileName;
				//压缩图片
				ImageUtil.resize(bytes, 400, 300, picPath);
				output.put("status", "0");
				output.put("infoStr", "上传文件成功");
				output.put("path", picPath);
				output.put("imgPath", newFileName);
			}
		} catch (Exception e) {
			output.put("status", "-10");
			output.put("infoStr", "上传文件失败");
		}
		return JSON.toJSONString(output);
	}
}
