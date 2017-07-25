/**
 * Created by eclipse3.2.
 * function:
 * User: FMD(冯敏栋)
 * Date: 2008-12-16
 * Time: 上午09:21:28
 * Email:fmdsaco99@163.com
 * To change this template use File | Settings | File Templates.
 */
package onther;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ChangType {
	public static void main(String[] arg) {
		// System.out.println(check("下载到手机","                  下载到手机"));
		// System.out.println(filter("    美女把手插错了地方."));
//		String content = "http://theme.3g.cn/Detail_theme.aspx?show=1&themeID=52517&quality=1&areaType=1&seriesID=0&sid=03493202B54&wid=0";
//		System.out.println(check(
//				"http://theme.3g.cn/Detail_theme.aspx(.*)show=1", content));
//		BigDecimal dd = new BigDecimal("1000");
//		BigDecimal dds = new BigDecimal("1000");
//		// BigDecimal dsa=dd.divide(divisor)-dds;

		 
			System.out.println(getRandom(10));
	 
	}

	/**
	 * 把小数点后的去掉
	 * 
	 * @param val
	 * @return
	 */
	public static String getShortBigDecimal(BigDecimal val) {
		if (val == null)
			return "";
		String tt = val.toString();
		int i = tt.indexOf(".");
		if (i > 0) {
			tt = tt.substring(0, +tt.indexOf(".") + 3);
		}

		return tt;
	}

	/**
	 * 过滤
	 * 
	 * @param content
	 * @param strBuf
	 * @return
	 */
	public static String filter(String content) {
		String key = "&#[\\d]{0,4}";
		content = delAfterSpace(content);
		return checks(key, content);
	}

	public static String getSeedSign(String url) {

		String str = getDomain(url);
		str = "theme-" + str;
		return str;
	}

	public static String checks(String key, String content) {
		if (key == null)
			return "";
		if (key.trim().equals(""))
			return "";
		Pattern p = Pattern.compile(key.trim());

		Matcher m = p.matcher(content);

		StringBuffer sb = new StringBuffer();

		int i = 0;

		boolean result = m.find();

		while (result) {
			i++;
			m.appendReplacement(sb, " ");
			result = m.find();
		}

		m.appendTail(sb);

		return sb.toString();

	}

	// 全角转半角
	public static final String SBCchange(String QJstr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++) {
			try {
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;

				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}

		return outStr;
	}

	/**
	 * 过滤掉asc码值小于32（空格）的字符
	 * 
	 * @param str
	 * @return
	 */
	public static String delAfterSpace(String str) {
		String ret = "";
		StringBuffer strBuf = new StringBuffer();
		if (null != str) {
			char[] tmp = str.toCharArray();
			int len = tmp.length;
			int m = 0;
			for (int i = 0; i < len; i++) {
				m = tmp[i];
				if (m > 32) {
					strBuf.append(tmp[i]);
				}
			}
			ret = strBuf.toString();
		}
		// 清空字符串缓冲区
		if (null != strBuf && strBuf.length() > 0) {
			strBuf.setLength(0);
		}
		return ret;
	}

	public static boolean startCheck(String reg, String string) {
		boolean tem = false;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);

		tem = matcher.matches();
		return tem;
	}

	/**
	 * 检查是含有关健字
	 * 
	 * @param key
	 *            关健字
	 * @param content
	 * @return
	 */
	public static boolean check(String key, String content) {
		if (key == null || content == null)
			return false;
		if (key.trim().equals(""))
			return false;
		Pattern p = Pattern.compile(key.trim());
		Matcher m = p.matcher(content);

		return m.find();

	}

	/**
	 * 检验整数,适用于正整数、负整数、0，负整数不能以-0开头, 正整数不能以0开头
	 * 
	 * */
	public static boolean checkNr(String nr) {
		String reg = "^(-?)[1-9]+\\d*|0";
		return startCheck(reg, nr);
	}

	/**
	 * 判断字符串是否为数字，如是就返回此字符的数值，不是就返回0
	 * 
	 * @param str
	 * @return
	 */
	public static int isNumeric(String str) {
		if (str == null)
			return 0;
		if (str.equals(""))
			str = "0";
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				if (Character.isDigit(str.charAt(0))) {
					return Integer.parseInt(str.charAt(0) + "");
				} else {
					return 0;
				}

			}
		}
		return Integer.parseInt(str);
	}

	/**
	 * 
	 * @param str
	 *            数据类型为 fileName:1,resName:2
	 */
	public static HashMap<String, Integer> extractType(String str) {

		HashMap<String, Integer> type = new HashMap<String, Integer>();
		if (str == null)
			return type;
		String strs[] = str.split(",");
		for (String key : strs) {
			String keys[] = key.split(":");
			type.put(keys[0], Integer.parseInt(keys[1]));
		}
		return type;
	}

	/**
	 * md5加密
	 * 
	 * @param newByte1
	 * @return
	 */
	public static String convertToMd5s(byte[] newByte1) {
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			byte newByte2[] = messagedigest.digest(newByte1);
			String cryptograph = "";
			String temp = "";
			for (int i = 0; i < newByte2.length; i++) {
				temp = Integer.toHexString(newByte2[i] & 0x000000ff);
				if (temp.length() < 2)
					temp = "0" + temp;
				cryptograph += temp;
				// cryptograph += Integer.toHexString( newByte2[i] & 0x000000ff
				// );
			}
			return cryptograph;
		} catch (Exception e) {
			return "error";
		}
	}

	public static boolean checkIsEmail(String email) {
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(email);
		return m.find();

	}

	private static int getRandom(int i) {
		Random a = new Random();
		return a.nextInt(i);
		// System.out.println(a.nextInt(1000000));
		// return 0;
	}

	public static boolean chinese(String str) {
		if (str == null) {
			return false;
		}
		// System.out.println(str.length());
		String regex = ".*[\u0391-\uFFE5]+.*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		boolean validate = m.matches();
		return validate;
	}

	/**
	 * 取网址的域名
	 * 
	 * @param url网址
	 * @return
	 */
	public static String getDomain(String url) {

		String str = url;
		str = str.replace("http://", "");
		str = str.replace(".", "#-rw-#");
		if (str != null) {
			int i = str.indexOf("/");
			if (i == 0)
				i = str.indexOf("\\");
			if (i > 0) {
				str = str.substring(0, i);
			}
			String[] strs = str.split("#-rw-#");
			str = "";
			int j = 0;
			for (int jj = strs.length - 1; jj >= 0; jj--) {
				if (strs[jj] != null && j <= 1) {
					j++;
					if (j > 1) {
						str = strs[jj] + "." + str;
					} else {
						str = strs[jj] + str;
					}

					if (str.equals("com.cn")) {
						j--;
					}
					if (str.equals("net.cn")) {
						j--;
					}
					if (str.equals("org.cn")) {
						j--;
					}
					if (str.equals("gov.cn")) {
						j--;
					}

				}
			}
		}
		return str;

	}

	public static String urlEncode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			// System.out.println("the urlEncode:" + e.toString());
			return "";
		}
	}

	public static String DisEncode(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (Exception e) {
			// System.out.println("the DisEncode:" + e.toString());
			return "";
		}
	}
	
	/**
	 * 
	 * @param i
	 *            第几个格
	 * @param childSheet
	 *            excle 表里的sheet
	 * @param r
	 *            第几行
	 * @return
	 */
	public static String getCellValue(short c, HSSFSheet childSheet, int r,
			Integer type) {
		// 循环该子sheet行对应的单元格项
		// HSSFCell cell = childSheet.getRow(r).getCell(i);
		String value = "";
		HSSFCell cell = null;
		try {

			cell = childSheet.getRow(r).getCell(c);
			// System.out.println("cell:: " + cell);

			childSheet.getRow(r).getPhysicalNumberOfCells();
			if (cell != null) {
				// value = cell.toString();
				if (type == null) {
					value = cell.toString();
				} else if (type == 1) { // 日期
					Date dd = new Date();
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						System.out.println("日期型：" + c);
						dd = cell.getDateCellValue();
						if (dd == null) {
							value = cell.toString();
							System.out.println(value);
						}
					} else {
						System.out.println("不是日期型：" + c);
						value = cell.toString();
					}

					// System.out.println(" 日期yyyy-MM-dd："+dd);
					value = TimeTools.dateToStr(dd, "yyyy-MM-dd");
					if (value == null) {
						value = cell.toString();
						System.out.println(value);
					}
				} else if (type == 2) {// 时间 13:30
					value = cell.toString();
					System.out.println(" 时间 HH:mm ：" + value);
					// value = TimeTools.getStrDate(value, "HH:mm");//
					// .dateToStr(dd, "HH:mm");

				} else {
					value = cell.toString();
				}

				// System.out.println(value);
				// System.out.println("cell.getCellType():: "+
				// cell.getCellType());
				// switch (cell.getCellType()) {
				//
				// case HSSFCell.CELL_TYPE_STRING:
				// value = cell.getStringCellValue();
				// break;
				// case HSSFCell.CELL_TYPE_NUMERIC:
				// value = "" + cell.getNumericCellValue();
				// break;
				//
				// case HSSFCell.CELL_TYPE_BLANK:
				// ;
				// break;
				// default:
				// }
			}
			// System.out.println("childSheet " + (r + 1) + "行数:: "+
			// childSheet.getPhysicalNumberOfRows()+"value :: " + value);
			if (value == null)
				value = "";
			value = value.replace("：", ":");
			value = value.replace("：", ":");
		} catch (Exception e) {
			// value =ChangType.convertCell(cell);
			System.out.println("取数出错：" + c + "  " + e.toString() + " " + cell);
		}

		value = value == null ? "" : value;
		return value;
	}
	
	
	 public  static  HashMap parserToMap(String s){
	 
		    GsonBuilder builder = new GsonBuilder();
	        // 不转换没有 @Expose 注解的字段
	        builder.excludeFieldsWithoutExposeAnnotation();
	        Gson gson = builder.create();
	        
	        
	    	String jsonStr=	gson.toJson(s);
            HashMap  rsMap=   gson.fromJson(jsonStr, HashMap.class);
			return rsMap;
		}
}
