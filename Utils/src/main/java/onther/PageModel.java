package onther;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageModel implements Serializable {

	private int current = 1; // 当前页

	private int pagenumber; // 总记录数

	private int recordcount = 15; // 每页显示记录数

	private String url; // 链接地址

	private int pagetype = 0;// 分页方式0为正常链接，1为 pagechange(i)js跳转页

	private int pagesum; // 总页数

	private int halfshow = 5; // 数字列表半边

	private String[] orders;
	private int pagestart = 0;
	private int pageend = 1;
	public static int pagerecord = 20; // 每页20
	private List<String> parameter = new ArrayList<String>();// 其它参数 value:

	// state=1

	public PageModel(String url, int recordcount) {
		this.current = 1;
		if (recordcount > 0) {
			this.recordcount = recordcount;
		}

		this.url = url;
	}

	public void setPageModel(String url, int recordcount) {
		if (recordcount > 0) {
			this.recordcount = recordcount;
		}
		this.url = url;
	}

	/**
	 * google式分页 1,2,3,4,5,6,7,8,9,10
	 * 
	 * @param sumpage
	 *            总页
	 * 
	 */
	public String getGooglePage(PageModel wappagemodel) {
		StringBuffer sb = new StringBuffer();
		int start = wappagemodel.getCurrent() - wappagemodel.getHalfshow();
		int end = wappagemodel.getCurrent() + wappagemodel.getHalfshow();
		if (start <= 0)
			start = 1;
		if (end > wappagemodel.getPagesum())
			end = wappagemodel.getPagesum();
		for (int y = start; y <= end; y++) {
			sb.append(getpageurl(wappagemodel, y));
		}
		// sb.append(""+getParameter()); //加上其它参数
		return sb.toString();
	}

	/**
	 * 
	 * @param wappagemodel
	 * @param i
	 *            组合的
	 * @return
	 */
	public String getpageurl(PageModel wappagemodel, int i) {
		String str = "" + i;
		if (wappagemodel.getCurrent() == i) {
			str = "<strong>" + i + "</strong>";
		}
		String url = "<a href='" + wappagemodel.getUrl()
				+ "?pagemodel.current=" + i + getParameter() + "'>" + str
				+ "</a>";
		return url;
	}

	/**
	 * JS提交翻页法
	 * 
	 * @param wappagemodel
	 * @param i
	 * @return
	 */
	public String getOnclickPageUrl(PageModel wappagemodel, int i) {
		String str = "" + i;
		if (wappagemodel.getCurrent() == i) {
			str = "<strong>" + i + "</strong>";
		}
		String url = "<a href='#' onclick='\"pagechange('" + i + "');\"'>"
				+ str + "</a>";
		return url;

	}

	/**
	 * WEBWORK取数方式分页WAP专用 简单模式
	 * 
	 * @param current
	 *            当前页
	 * @param pagenumber
	 *            总记录数
	 * @param recordcount
	 *            每页显示记录数
	 * @param url
	 *            链接地址 //首页 上页 下页 未页 页次：1/1 6条记录/页 转到 1 页 共 2 条记录
	 * @return
	 */
	public String getWapPages(PageModel wappagemodel) {
		StringBuffer page = new StringBuffer();
		// page.append("<br/>");
		page.append("<a href='" + wappagemodel.getUrl()
				+ "?pagemodel.pagenumber=" + wappagemodel.getPagenumber()
				+ "&pagemodel.current=1'>首页</a>");
		if (wappagemodel.getCurrent() != lastpage(wappagemodel)) {
			page.append("<a href=\"" + wappagemodel.getUrl()
					+ "?pagemodel.pagenumber=" + wappagemodel.getPagenumber()
					+ "&amp;pagemodel.current=" + nextpage(wappagemodel)
					+ "\">下一页</a>");
		}
		if (wappagemodel.getCurrent() != 1) {
			page.append("<a href=\"" + wappagemodel.getUrl()
					+ "?pagemodel.pagenumber=" + wappagemodel.getPagenumber()
					+ "&amp;pagemodel.current=" + previous(wappagemodel)
					+ "\">上一页</a>");
		}

		page.append("<a href='" + wappagemodel.getUrl()
				+ "?pagemodel.pagenumber=" + wappagemodel.getPagenumber()
				+ "&pagemodel.current=" + lastpage(wappagemodel) + "'>未页</a>");

		page.append("页次： " + wappagemodel.getCurrent() + "/"
				+ lastpage(wappagemodel) + "条");
		page.append("记录" + wappagemodel.getPagesum() + "/页");
		page.append("共 " + wappagemodel.getPagenumber() + "条记录");
		return page.toString();
	}

	public String getPageFunction() {
		StringBuffer sb = new StringBuffer();
		sb
				.append("<input type=\"hidden\"  name=\"pagemodel.current\" id=\"current\" value=\"<ww:property value=\"pagemodel.current\"/>\"/> \n");
		sb
				.append("<input type=\"hidden\"  name=\"pagemodel.pagenumber\" id=\"pagenumber\" value=\"<ww:property value=\"pagemodel.pagenumber\"/>\"/> \n");
		sb
				.append("<input type=\"hidden\"  name=\"pagemodel.recordcount\" id=\"recordcount\" value=\"<ww:property value=\"pagemodel.recordcount\"/>\"/> \n");
		sb
				.append("<input type=\"hidden\"  name=\"pagemodel.url\" id=\"url\" value=\"<ww:property value=\"pagemodel.url\"/>\"/> \n");
		sb
				.append("<input type=\"hidden\"  name=\"pagemodel.pagesum\" id=\"pagesum\" value=\"<ww:property value=\"pagemodel.pagesum\"/>\"/> \n");
		return sb.toString();
	}

	/**
	 * 上一页
	 * 
	 * @param pagemodel
	 * @return
	 */
	private int previous(PageModel pagemodel) {
		int i = 1;
		// /int
		// j=(pagemodel.getCurrent()-1)*pagemodel.getRecordcount()-pagemodel.getPagenumber();
		if (pagemodel.getCurrent() >= 1) {
			i = pagemodel.getCurrent() - 1;
			if (i == 0) {
				i = 1;
			}
		}
		return i;
	}

	/**
	 * 下一页 ///下一页的总数减全局总数大于显示数
	 * 
	 * @param pagemodel
	 * @return
	 */
	private int nextpage(PageModel pagemodel) {
		int i = 1;
		if (pagemodel.getCurrent() < lastpage(pagemodel)) {// 当前页数小于总页数
			i = pagemodel.getCurrent() + 1;
			if (i == lastpage(pagemodel)) {
				i = lastpage(pagemodel);
			}
		} else {
			i = lastpage(pagemodel);
		}

		return i;
	}

	// 最后一页
	private int lastpage(PageModel pagemodel) {
		float ss = (float) pagemodel.getPagenumber()
				/ pagemodel.getRecordcount();
		// /pagemodel.getRecordcount()
		int s = (int) Math.ceil(ss);

		return s;

	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(int pagenumber) {
		this.pagenumber = pagenumber;
	}

	public int getRecordcount() {
		return recordcount;
	}

	public void setRecordcount(int recordcount) {
		this.recordcount = recordcount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPagesum() {

		float ss = (float) this.pagenumber / this.recordcount;
		int s = (int) Math.ceil(ss);
		return s;
	}

	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}

	// public String[] getOrders() {
	// return ChangType.getOrder(this.recordcount, this.current,
	// this.pagenumber);
	//
	// }

	public void setOrders(String[] orders) {
		this.orders = orders;
	}

	public int getPagestart() {

		int c = this.current;
		if (c > 1) {
			c = c - 1;
		} else {
			c = 0;
		}
		return c * this.recordcount;
	}

	public void setPagestart(int pagestart) {
		this.pagestart = pagestart;
	}

	public int getPageend() {
		if (this.current <= 0) {
			this.current = 1;
		}
		Integer ii = this.recordcount;
		return ii;
	}

	public void setPageend(int pageend) {
		this.pageend = pageend;
	}

	public int getHalfshow() {
		return halfshow;
	}

	public void setHalfshow(int halfshow) {
		this.halfshow = halfshow;
	}

	public int getPagetype() {
		return pagetype;
	}

	public void setPagetype(int pagetype) {
		this.pagetype = pagetype;
	}

	public String getParameter() {
		StringBuffer sb = new StringBuffer();
		for (String key : parameter) {

			// key.getBytes(Charset.defaultCharset());
			sb.append("&" + key);
		}

		// String str=java.net.URLEncoder.encode( );
		return sb.toString();
	}

	public void setParameter(String parameter) {

		this.parameter.add(parameter);
	}

}
