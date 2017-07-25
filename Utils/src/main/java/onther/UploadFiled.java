package onther;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UploadFiled {
	private static final Log log = LogFactory.getLog(UploadFiled.class);

	private String ext;

	/**
	 * 构建上传并上传
	 * 
	 * @param file
	 * @param photoService
	 * @return 返回上传的路径及文件名
	 */
	public static String uploadfiles(File file, String photoService,
			String filename) {
		if (file == null)
			return "";
		java.util.Date dt = new java.util.Date(System.currentTimeMillis());
		byte[] by = null;
		String sExt = "";
		String sServerFileName = "";
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSS");
		SimpleDateFormat fmts = new SimpleDateFormat("yyMMdd");
		String filedir = fmts.format(dt);
		sServerFileName = fmt.format(dt);

		String sLocalFileName = filename;
		if (sLocalFileName != null && !"".equals(sLocalFileName.trim())) {
			int ii = sLocalFileName.lastIndexOf("."); // 取文件名的后缀
			sExt = (sLocalFileName.substring(ii, sLocalFileName.length()))
					.toLowerCase();

		}
		sServerFileName = sServerFileName + sExt;

		try {
			by = FileUtils.readFileToByteArray(file);
		} catch (IOException e1) {
			log.error(e1);

		}

		writerfile(photoService + sServerFileName, by);

		return sServerFileName;
	}

	public static String uploadfileslt(File file, String photoService) {
		if (file == null)
			return "";
		java.util.Date dt = new java.util.Date(System.currentTimeMillis());
		byte[] by = null;
		String sExt = "";
		String sServerFileName = "";
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSS");
		SimpleDateFormat fmts = new SimpleDateFormat("yyMMdd");
		String filedir = fmts.format(dt);
		sServerFileName = fmt.format(dt);

		String sLocalFileName = file.getName();
		// if (sLocalFileName != null && !"".equals(sLocalFileName.trim())) {
		// int ii = sLocalFileName.lastIndexOf("."); //取文件名的后缀
		// sExt = (sLocalFileName.substring(ii,
		// sLocalFileName.length())).toLowerCase();
		//               
		// }
		sServerFileName = sLocalFileName;

		try {
			by = FileUtils.readFileToByteArray(file);
		} catch (IOException e1) {
			log.error(e1);

		}

		writerfile(photoService + sServerFileName, by);

		return sServerFileName;
	}

	public static void writerfile(String fileName, byte[] str) {
		String string;
		FileOutputStream fout;
		DataOutputStream bfo = null;
		try {

			bfo = new DataOutputStream(new FileOutputStream(fileName));
			bfo.write(str);
			bfo.close();

			/*
			 * BufferedWriter bw = new BufferedWriter(new FileWriter(new
			 * File("d:/test.txt"))); bw.write("锟叫癸拷锟斤拷锟今共和癸拷"); bw.flush();
			 * bw.close();
			 */
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}

	public static void createFile(String filePath) {
		// // log.info("建建立文件建立文件建立文件建立文件" + filePath);
		String temp = "";
		filePath = filePath.replace("\\", "/");
		File file = null;

		try {

			file = new File(filePath);
			if (filePath != null) {
				int i = 0;
				i = filePath.lastIndexOf("/");
				if (i > 0) {

					temp = filePath.substring(0, i);
					createPath(temp);// /如果文件夹不存在建立
				}

			}
			file.createNewFile();
		} catch (IOException e) {
			log.debug(e.toString());
		} finally {

			file = null;
		}

	}

	public static void createPath(String path) {
		// // log.info("建立文路径建立文路径建立文路径建立文路径" + path);
		if (path != null) {
			path = path.replace("\\", "/");
			int i = 0;
			i = path.lastIndexOf("/");// /读系统配置分隔符
			if (i == 0)
				return;
			// //temp = path.substring(i, path.length());
			File file = new File(path);
			try {
				if (!file.canRead()) {// /如果不存在就建立
					file.mkdir();
					// /file.mkdirs();
				}
			} finally {
				file = null;
			}

		}

	}

	public static void wirterBigDate(String filename, String bodys) {

		// String message =
		// "asdfaksdjfalskdfjalksdjflkasjdfkajsdfkljasdlkfjasdlkfjasdfjalksdjflasdjflasdjflasdfjlasdfjeqoiuiruqnakncaskn asjdfas ffjqwoerj";
		FileOutputStream fos = null;
		FileChannel fc = null;
		File file = null;
		try {
			file = new File(filename);
			if (!file.canRead()) {
				createFile(filename);// /建立文件
			}
			fos = new FileOutputStream(file, true);
			fc = fos.getChannel();
			byte[] src = bodys.getBytes("UTF-8");
			Long size = fc.size();
			fc.write(ByteBuffer.wrap(src), size);
			fc.force(false);
		} catch (FileNotFoundException e) {
			log.debug(" wirter file fail:" + e.toString());
		} catch (IOException e) {
			log.debug(" wirter file fail:" + e.toString());
		} finally {
			try {
				if (null != fos) {
					fos.flush();
					fos.close();
				}
				if (null != fc) {
					fc.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean deletePic(String fileName) {
		boolean bu = false;
		File file = new File(fileName);
		if (file.canRead()) {
			file.delete();
			bu = true;
		}
		return bu;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
}
