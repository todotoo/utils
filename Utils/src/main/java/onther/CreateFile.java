package onther;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

public class CreateFile {

	public void createfile(String paths, String filename) {

		String path = createdir(paths);
		// out.println(path);
		File f = new File(path, filename.trim());

		// out.println(f);
		// out.println(f.exists());

		if (f.exists()) {// ���File.txt�Ƿ����

			f.delete();// ɾ��File.txt�ļ�
			try {
				// �ڵ�ǰĿ¼�½�bһ����Ϊfilename���ļ�
				f.createNewFile();
			} catch (IOException e) {
				// TODO �Զ���� catch ��
				e.printStackTrace();
			}

		} else {
			try {

				f.createNewFile();
			} catch (IOException e) {
				// TODO �Զ���� catch ��
				e.printStackTrace();
			}

		}

	}

	public String createdir(String paths) {
		String path = paths;

		File d = new File(path);// ��b���SubĿ¼��File���󣬲��õ����һ������
		if (d.exists()) {// ���SubĿ¼�Ƿ����
			d.delete();
		} else {
			d.mkdir();// ��bSubĿ¼
			System.out.println("Ŀ¼�����ڣ��ѽ�b");
		}
		return path;

	}

	public void readfilearriy() {

	}

	/**
	 * 
	 * 
	 * �����ļ�
	 * 
	 */
	public void readfile() {
		String path = "d:";

		int c;
		try {
			FileReader fr;
			fr = new FileReader(path + "\\ReadData.txt");
			c = fr.read();
			// �ؼ����ڶ�ȡ����У�Ҫ�ж����ȡ���ַ��Ƿ��Ѿ������ļ���ĩβ����������ַ��ǲ����ļ��еĶ��з��жϸ��ַ�ֵ�Ƿ�Ϊ13��

			while (c != -1) {
				System.out.print((char) c);// ���u������
				c = fr.read();// ���ļ��м����ȡ���
				if (c == 13) {// �ж��Ƿ�Ϊ�����ַ�
					System.out.print("<br>");// �����б�ǩ
					fr.skip(1);// �Թ�һ���ַ�
					// c=fr.read();//��ȡһ���ַ�
				}
			}
			fr.close();
			// ���ļ��ж�ȡһ���ַ�
			// �ж��Ƿ��Ѷu��ļ���β

		} catch (IOException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		}

	}

	public void readdirFile() {

	}

	/**
	 * 
	 * 
	 * ׷�����ݵ��ļ���ȥ
	 * 
	 * 
	 */
	public void appwritefile() {

		String path = "d:";
		RandomAccessFile rf;
		try {
			rf = new RandomAccessFile(path + "\\ss.txt", "rw");
			// ����һ����RandomAccessFile�Ķ��󣬲�ʵ��

			rf.seek(rf.length());// ��ָ���ƶ����ļ�ĩβ
			rf.writeBytes("\nAppend a line to the file!");
			rf.close();// �ر��ļ���
			System.out.println("д���ļ�����Ϊ��<br>");
			FileReader fr = new FileReader(path + "\\WriteData.txt");
			BufferedReader br = new BufferedReader(fr);// ��ȡ�ļ���BufferedRead����
			String Line = br.readLine();
			while (Line != null) {
				System.out.println(Line + "<br>");
				Line = br.readLine();
			}
			fr.close();// �ر��ļ�
		} catch (FileNotFoundException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * ��д����ݷ���
	 * 
	 * 
	 */
	public void writeline() {

		String path = "d:";
		FileWriter fw;
		try {
			fw = new FileWriter(path + "\\WriteData.txt");

			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("��Һã�");
			bw.write("�����ǡ�JSP��̼��ɡ���");
			bw.newLine();// ����
			bw.write("����ָ�̣�");
			bw.newLine();// ����
			bw.write("email: stride@sina.com");
			bw.flush();// ����ݸ������ļ�
			fw.close();// �ر��ļ���
			System.out.println("д���ļ�����Ϊ��<br>");
			FileReader fr = new FileReader(path + "\\WriteData.txt");
			BufferedReader br = new BufferedReader(fr);
			String Line = br.readLine();// ��ȡһ�����
			while (Line != null) {
				System.out.println(Line + "<br>");
				Line = br.readLine();
			}
			fr.close();
		} catch (IOException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * �����д���ļ�
	 * 
	 * @param paths
	 *            ��b·��
	 * @param filename
	 *            Ҫ��b���ļ���
	 * @str ����Ҫ��b��StringBuffer
	 * 
	 */
	public void witerfile(String paths, String filename, StringBuffer str) {

		createfile(paths, filename.trim());
		String pathed = paths + "\\" + filename.trim();
		String strs = "";
		FileWriter fw;
		try {
			// //System.out.println(pathed);
			fw = new FileWriter(pathed);
			// ��bFileWriter���󣬲�ʵ��fw
			// strs = new String(str.toString().getBytes(), "UTF-8");
			fw.write(str.toString());

			// //fw.getEncoding();
			fw.close();

			// //FileReader fr = new FileReader(path +"\\"+ filename.trim());
			// / BufferedReader br = new BufferedReader(fr);//
			// ��bBufferedReader���󣬲�ʵ��Ϊbr
			// //String Line = br.readLine();
			// ��ȡһ�����
			// //System.out.println(Line + "<br>");
			// // br.close();// �ر�BufferedReader����
			// /fr.close();
		} catch (IOException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		}

	}

	/**
	 * 创造文件夹
	 * 
	 * @param path
	 * @return
	 */
	public static void createPaths(String path) {
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
					file.mkdirs();
				}
			} finally {
				file = null;
			}

		}

	}

	/**
	 * 建立文件
	 * 
	 * @param filePath
	 *            文件及路径 d:/dsl/sdf/sdf/sdfs/dol.txt
	 */
	public static void createFiles(String filePath) {
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
			System.out.println(e);
		} finally {

			file = null;
		}

	}

	public void wrterUTF8file(String paths, String filename, StringBuffer str) {

		createfile(paths, filename.trim());
		String pathed = paths + "\\" + filename.trim();
		createFiles(pathed);
		File file = new File(pathed);
		// System.out.println(pathed);
		FileOutputStream fileout = null;
		OutputStreamWriter outw = null;
		try {
			if (!file.canRead()) {
				createFiles(pathed);
			}
			fileout = new FileOutputStream(file);

			outw = new OutputStreamWriter(fileout, "UTF-8");
			outw.write(str.toString());
			outw.flush();
			// /System.out.println("编码"+outw.getEncoding());
			// // Charset.defaultCharset();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (outw != null) {
					outw.close();
				}
				if (fileout != null) {
					fileout.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * 
	 * һ��һ�ж�ȡ���
	 * 
	 * 
	 * 
	 */

	public void readlinefile() {

		String path = "d:";// ȡ�õ�ǰĿ¼��·��
		FileReader fr;
		try {
			fr = new FileReader(path + "\\file\\inc\\t.txt");
			BufferedReader br = new BufferedReader(fr);// ��bBufferedReader���󣬲�ʵ��Ϊbr
			String Line = br.readLine();
			// ���ļ���ȡһ���ַ�
			// �ж϶�ȡ�����ַ��Ƿ�Ϊ��
			while (Line != null) {
				System.out.println(Line + "<br>");
				// �����ļ��ж�ȡ�����
				Line = br.readLine();
				// ���ļ��м����ȡһ�����
			}
			br.close();
			// �ر�BufferedReader����
			fr.close();
			// �ر��ļ�
			// ��bFileReader���󣬲�ʵ��Ϊfr
		} catch (FileNotFoundException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		}

	}

	public void writerfile(String fileName, StringBuffer str) {
		String string;
		FileOutputStream fout;
		DataOutputStream bfo = null;

		try {
			string = str.toString();
			bfo = new DataOutputStream(new FileOutputStream(fileName));

			bfo.write(string.getBytes());
			bfo.close();

			/*
			 * BufferedWriter bw = new BufferedWriter(new FileWriter(new
			 * File("d:/test.txt"))); bw.write("�й����񹲺͹�"); bw.flush();
			 * bw.close();
			 */
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}

	/**
	 * 
	 * ���ļ�ȫ������
	 * 
	 */
	public void readallfile() {

		String path = "d:";
		FileReader fr;
		try {
			fr = new FileReader(path + "\\ReadData.txt");
			// �ؼ����ڶ�ȡ����У�Ҫ�ж����ȡ���ַ��Ƿ��Ѿ������ļ���ĩβ����������ַ��ǲ����ļ��еĶ��з��жϸ��ַ�ֵ�Ƿ�Ϊ13��

			int c = fr.read();
			// ���ļ��ж�ȡһ���ַ�
			// �ж��Ƿ��Ѷu��ļ���β
			while (c != -1) {
				System.out.print((char) c);// ���u������
				c = fr.read();// ���ļ��м����ȡ���
				if (c == 13) {// �ж��Ƿ�Ϊ�����ַ�
					System.out.print("<br>");// �����б�ǩ
					fr.skip(1);// �Թ�һ���ַ�
					// c=fr.read();//��ȡһ���ַ�
				}
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ���� catch ��
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * ��ȡ�ļ����µ��ļ���
	 * 
	 * @param path
	 * @param xml后缀名
	 * 
	 */
	public void getdir(String path, String xml) {

		// //String path = "E:\\fmd\\����";
		File d = new File(path);// ��b��ǰĿ¼���ļ���File����
		File lists[] = d.listFiles();// ȡ�ô��Ŀ¼�������ļ���File��������
		System.out.println(" " + path);
		for (int i = 0; i < lists.length; i++) {
			if (lists[i].isFile()) {
				if (xml.equals(getxml(lists[i].getName()))) { // 符合后缀就打印
					System.out.println(lists[i].getName());
				}
			}
		}
		System.out.println(path);
		for (int i = 0; i < lists.length; i++) {
			if (lists[i].isDirectory()) {

				System.out.println(lists[i].getName());

			}
		}

	}

	public static String getxml(String filename) {
		if (filename == null)
			return "";
		int e = filename.lastIndexOf(".");
		filename = filename.substring(e + 1, filename.length());
		return filename;
	}

	/**
	 * 创造文件夹
	 * 
	 * @param path
	 * @return
	 */
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
					file.mkdirs();
				}
			} finally {
				file = null;
			}

		}

	}

	/**
	 * 建立文件
	 * 
	 * @param filePath
	 *            文件及路径 d:/dsl/sdf/sdf/sdfs/dol.txt
	 */
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

		} finally {

			file = null;
		}

	}

	public static synchronized void appwritefiles(String filename, String bodys) {
		RandomAccessFile rf = null;
		File file = new File(filename);
		try {

			if (!file.canRead()) {
				createFile(filename);// /建立文件
			}
			file = null;
			rf = new RandomAccessFile(filename, "rw");
			rf.seek(rf.length());// 将指针移动到文件末尾
			// /String strs=new String(bodys.getBytes(),"UTF-8");
			rf.write(bodys.getBytes());

		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} finally {

			file = null;
			if (rf != null) {
				try {
					rf.close();
				} catch (IOException e) {

				}
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreateFile cre = new CreateFile();
		cre.getdir("D:/deploy/crawler-node/lib", "jar");
		// // System.out.println(cre.getxml("d:/ald/ada/allfa.xml"));
		// / cre.witerfile();
		// / cre.readfile();
	}

}
