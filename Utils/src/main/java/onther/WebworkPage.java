package onther;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class WebworkPage extends JdbcDaoSupport implements Serializable {

	public List getListPage(PageModel pagemodel, List list) {
		int i = nextpage(pagemodel) * pagemodel.getPagenumber();
		int j = previous(pagemodel) * pagemodel.getPagenumber();
		List lists = new ArrayList();
		for (i = i; i < j; i++) {
			if (i <= list.size())
				lists.add(list.get(i));
		}
		return lists;
	}

	public PageModel ListPage(PageModel pagemodel, List list) {
		pagemodel.setPagenumber(list.size());
		return pagemodel;
	}

	public PageModel ListPage(PageModel pagemodel, int pagesum) {
		pagemodel.setPagenumber(pagesum);
		return pagemodel;
	}

	/**
	 * 求总记录数并且对PageModel赋值 ,每次查询内容必需设pagemodel.setPagenumber(0)
	 * 
	 * @param queryinfos
	 *            查询条件
	 * @param table
	 *            对表的查询 (from 后面的东西)
	 * @param pagemodel
	 *            分页BEAN
	 * @return 返回PageModel
	 */
	// public PageModel PageModel(QueryInfos queryinfos, String table,
	// PageModel pagemodel) {
	// int inti = 0;
	// String sql = "select count(*) from " + table;
	// if (queryinfos.getWhereClause() != null) {
	// if (queryinfos.getWhereClause().length() > 3) {
	// sql = sql + " where " + queryinfos.getWhereClause();
	// }
	// }
	// if (pagemodel.getPagenumber() > 0) {
	// return pagemodel;
	// }
	// long list = this.getJdbcTemplate().queryForLong(sql);
	// inti = Integer.parseInt(list + "");
	// pagemodel.setPagenumber(inti);
	// return pagemodel;
	// }
	/**
	 * WEBWORK取数方式分页WAP专用 加上主健
	 * 
	 * @param current
	 *            当前页
	 * @param pagenumber
	 *            总记录数
	 * @param recordcount
	 *            每页显示记录数
	 * @param url
	 *            链接地址
	 * @return
	 * 
	 */
	public String getWapPageId(PageModel wappagemodel, String id) {
		StringBuffer page = new StringBuffer();
		page.append("页次： " + wappagemodel.getCurrent() + "/"
				+ lastpage(wappagemodel) + "");
		page.append("<br/>");
		// /page.append("<a
		// href=\""+wappagemodel.getUrl()+"?wappagemodel.pagenumber="+wappagemodel.getPagenumber()+"&amp;wappagemodel.current=1&amp;esid="+id+"\">首页</a>");
		if (wappagemodel.getCurrent() != 1) {
			page.append("<a href=\"" + wappagemodel.getUrl()
					+ "?wappagemodel.pagenumber="
					+ wappagemodel.getPagenumber()
					+ "&amp;wappagemodel.current=" + previous(wappagemodel)
					+ "&amp;esid=" + id + "\">上一页</a>");
		}
		if (wappagemodel.getCurrent() != lastpage(wappagemodel)) {
			page.append("<a href=\"" + wappagemodel.getUrl()
					+ "?wappagemodel.pagenumber="
					+ wappagemodel.getPagenumber()
					+ "&amp;wappagemodel.current=" + nextpage(wappagemodel)
					+ "&amp;esid=" + id + "\">下一页</a>");
		}
		// //page.append("<a
		// href=\""+wappagemodel.getUrl()+"?wappagemodel.pagenumber="+wappagemodel.getPagenumber()+"&amp;wappagemodel.current="+lastpage(wappagemodel)+"&amp;esid="+id+"\">尾页</a>");
		page.append("<br/>");
		page.append("<input type=\"hidden\" id=\"esid\" name=\"esid\" value=\""
				+ id + "\"/>");
		page
				.append("<input type=\"text\" size=\"3\"   id=\"getCurrent\" name=\"wappagemodel.getCurrent\" value=\""
						+ wappagemodel.getCurrent() + "\"/>");
		page
				.append("<input type=\"hidden\" id=\"pagenumber\" name=\"wappagemodel.pagenumber\" value=\""
						+ wappagemodel.getPagenumber() + "\"/>");
		page
				.append("<input type=\"hidden\" id=\"url\" name=\"wapwappagemodel.url\" value=\""
						+ wappagemodel.getUrl() + "\"/>");
		page
				.append("<input type=\"hidden\" id=\"recordcount\" name=\"wapwappagemodel.recordcount\" value=\""
						+ wappagemodel.getRecordcount() + "\"/>");
		page.append(" <anchor>GO <go href=\"" + wappagemodel.getUrl()
				+ "\" method=\"post\">  </go> </anchor>");

		return page.toString();
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
	 * @param type
	 *            固定的参数
	 * @param url
	 *            链接地址
	 * @return
	 */
	public String getWapPages(PageModel wappagemodel, String type) {
		StringBuffer page = new StringBuffer();
		page.append("页次： " + wappagemodel.getCurrent() + "/"
				+ lastpage(wappagemodel) + "");
		page.append("<br/>");
		// /page.append("<a
		// href=\""+wappagemodel.getUrl()+"?wappagemodel.pagenumber="+wappagemodel.getPagenumber()+"&amp;wappagemodel.current=1&amp;esid="+id+"\">首页</a>");

		if (wappagemodel.getCurrent() != lastpage(wappagemodel)) {
			page.append("<a href=\"" + wappagemodel.getUrl()
					+ "?wappagemodel.pagenumber="
					+ wappagemodel.getPagenumber()
					+ "&amp;wappagemodel.current=" + nextpage(wappagemodel)
					+ "&amp;" + type + "\">下一页</a>");
		}
		if (wappagemodel.getCurrent() != 1) {
			page.append("<a href=\"" + wappagemodel.getUrl()
					+ "?wappagemodel.pagenumber="
					+ wappagemodel.getPagenumber()
					+ "&amp;wappagemodel.current=" + previous(wappagemodel)
					+ "&amp;" + type + "\">上一页</a>");
		}

		page.append("<br/>");
		// / page.append("<input type=\"text\" size=\"3\" id=\"getCurrent\"
		// name=\"wappagemodel.getCurrent\"
		// value=\""+wappagemodel.getCurrent()+"\"/>");
		// / page.append("<input type=\"hidden\" id=\"pagenumber\"
		// name=\"wappagemodel.pagenumber\"
		// value=\""+wappagemodel.getPagenumber()+"\"/>");
		// / page.append("<input type=\"hidden\" id=\"url\"
		// name=\"wapwappagemodel.url\" value=\""+wappagemodel.getUrl()+"\"/>");
		// / page.append("<input type=\"hidden\" id=\"recordcount\"
		// name=\"wapwappagemodel.recordcount\"
		// value=\""+wappagemodel.getRecordcount()+"\"/>");
		// / page.append(" <anchor>GO <go href=\""+wappagemodel.getUrl()+"\"
		// method=\"post\"> </go> </anchor>");

		return page.toString();
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
	 *            链接地址
	 * @return
	 */
	public String getWapPages(PageModel wappagemodel) {
		StringBuffer page = new StringBuffer();
		page.append("页次： " + wappagemodel.getCurrent() + "/"
				+ lastpage(wappagemodel) + "");
		page.append("<br/>");
		if (wappagemodel.getCurrent() != lastpage(wappagemodel)) {
			page.append("<a href=\"" + wappagemodel.getUrl()
					+ "?wappagemodel.pagenumber="
					+ wappagemodel.getPagenumber()
					+ "&amp;wappagemodel.current=" + nextpage(wappagemodel)
					+ "\">下一页</a>");
		}
		if (wappagemodel.getCurrent() != 1) {
			page.append("<a href=\"" + wappagemodel.getUrl()
					+ "?wappagemodel.pagenumber="
					+ wappagemodel.getPagenumber()
					+ "&amp;wappagemodel.current=" + previous(wappagemodel)
					+ "\">上一页</a>");
		}
		page.append("<br/>");
		return page.toString();
	}

	/**
	 * WEBWORK取数方式分页WAP专用
	 * 
	 * @param current
	 *            当前页
	 * @param pagenumber
	 *            总记录数
	 * @param recordcount
	 *            每页显示记录数
	 * @param url
	 *            链接地址
	 * @return
	 */
	public String getWapPage(PageModel wappagemodel) {
		StringBuffer page = new StringBuffer();
		page.append("页次： " + wappagemodel.getCurrent() + "/"
				+ lastpage(wappagemodel) + "");
		page.append("<br/>");
		page.append("<a href=\"" + wappagemodel.getUrl()
				+ "?wappagemodel.pagenumber=" + wappagemodel.getPagenumber()
				+ "&amp;wappagemodel.current=1\">首页</a>");
		page.append("<a href=\"" + wappagemodel.getUrl()
				+ "?wappagemodel.pagenumber=" + wappagemodel.getPagenumber()
				+ "&amp;wappagemodel.current=" + previous(wappagemodel)
				+ "\">上一页</a>");
		page.append("<a href=\"" + wappagemodel.getUrl()
				+ "?wappagemodel.pagenumber=" + wappagemodel.getPagenumber()
				+ "&amp;wappagemodel.current=" + nextpage(wappagemodel)
				+ "\">下一页</a>");
		page.append("<a href=\"" + wappagemodel.getUrl()
				+ "?wappagemodel.pagenumber=" + wappagemodel.getPagenumber()
				+ "&amp;wappagemodel.current=" + lastpage(wappagemodel)
				+ "\">尾页</a>");
		page.append("<br/>");
		// page.append("<input type=\"text\" size=\"3\" id=\"getCurrent\"
		// name=\"wappagemodel.getCurrent\"
		// value=\""+wappagemodel.getCurrent()+"\"/>");
		// page.append("<input type=\"hidden\" id=\"pagenumber\"
		// name=\"wappagemodel.pagenumber\"
		// value=\""+wappagemodel.getPagenumber()+"\"/>");
		// page.append("<input type=\"hidden\" id=\"url\"
		// name=\"wapwappagemodel.url\" value=\""+wappagemodel.getUrl()+"\"/>");
		// page.append("<input type=\"hidden\" id=\"recordcount\"
		// name=\"wapwappagemodel.recordcount\"
		// value=\""+wappagemodel.getRecordcount()+"\"/>");
		// page.append(" <anchor>GO <go href=\""+wappagemodel.getUrl()+"\"
		// method=\"post\"> </go> </anchor>");
		//		
		return page.toString();
	}

	/**
	 * WEBWORK取数方式分页
	 * 
	 * @param current
	 *            当前页
	 * @param pagenumber
	 *            总记录数
	 * @param recordcount
	 *            每页显示记录数
	 * @param url
	 *            链接地址
	 * @return
	 */
	public String getPage(PageModel pagemodel) {

		StringBuffer page = new StringBuffer();
		page.append("每页条数/当前页/总页数 " + pagemodel.getRecordcount() + "/"
				+ pagemodel.getCurrent() + "/" + lastpage(pagemodel) + "");
		page.append("<a href='" + pagemodel.getUrl() + "?pagemodel.pagenumber="
				+ pagemodel.getPagenumber() + "&pagemodel.current=1'>首页</a>");
		page.append("<a href='" + pagemodel.getUrl() + "?pagemodel.pagenumber="
				+ pagemodel.getPagenumber() + "&pagemodel.current="
				+ previous(pagemodel) + "'>上一页</a>");
		page
				.append("<input type='text' size='3'   id='getCurrent' name='pagemodel.getCurrent' value='"
						+ pagemodel.getCurrent() + "'>");
		page
				.append("<input type='hidden' id='pagenumber' name='pagemodel.pagenumber' value='"
						+ pagemodel.getPagenumber() + "'>");
		page
				.append("<input type='hidden' id='url' name='pagemodel.url' value='"
						+ pagemodel.getUrl() + "'>");
		page
				.append("<input type='hidden' id='recordcount' name='pagemodel.recordcount' value='"
						+ pagemodel.getRecordcount() + "'>");
		page.append("<a href='" + pagemodel.getUrl() + "?pagemodel.pagenumber="
				+ pagemodel.getPagenumber() + "&pagemodel.current="
				+ nextpage(pagemodel) + "'>下一页</a>");
		page.append("<a href='" + pagemodel.getUrl() + "?pagemodel.pagenumber="
				+ pagemodel.getPagenumber() + "&pagemodel.current="
				+ lastpage(pagemodel) + "'>尾页</a>");

		return page.toString();
	}

	/**
	 * WEBWORK取数方式分页WAP专用 加上主健
	 * 
	 * @param current
	 *            当前页
	 * @param pagenumber
	 *            总记录数
	 * @param recordcount
	 *            每页显示记录数
	 * @param url
	 *            链接地址
	 * @return
	 */
	public String getPageId(PageModel pagemodel, String id) {
		StringBuffer page = new StringBuffer();
		page.append("页次： " + pagemodel.getCurrent() + "/" + lastpage(pagemodel)
				+ "");
		page.append("<br/>");
		// /page.append("<a
		// href=\""+pagemodel.getUrl()+"?pagemodel.pagenumber="+pagemodel.getPagenumber()+"&pagemodel.current=1&esid="+id+"\">首页</a>");
		if (pagemodel.getCurrent() != 1) {
			page.append("<a href=\"" + pagemodel.getUrl()
					+ "?pagemodel.pagenumber=" + pagemodel.getPagenumber()
					+ "&pagemodel.current=" + previous(pagemodel) + "&esid="
					+ id + "\">上一页</a>");
		}
		if (pagemodel.getCurrent() != lastpage(pagemodel)) {
			page.append("<a href=\"" + pagemodel.getUrl()
					+ "?pagemodel.pagenumber=" + pagemodel.getPagenumber()
					+ "&pagemodel.current=" + nextpage(pagemodel) + "&esid="
					+ id + "\">下一页</a>");
		}
		// //page.append("<a
		// href=\""+pagemodel.getUrl()+"?pagemodel.pagenumber="+pagemodel.getPagenumber()+"&pagemodel.current="+lastpage(pagemodel)+"&esid="+id+"\">尾页</a>");
		page.append("<br/>");
		page.append("<input type=\"hidden\" id=\"esid\" name=\"esid\" value=\""
				+ id + "\"/>");
		page
				.append("<input type=\"text\" size=\"3\"   id=\"getCurrent\" name=\"pagemodel.getCurrent\" value=\""
						+ pagemodel.getCurrent() + "\"/>");
		page
				.append("<input type=\"hidden\" id=\"pagenumber\" name=\"pagemodel.pagenumber\" value=\""
						+ pagemodel.getPagenumber() + "\"/>");
		page
				.append("<input type=\"hidden\" id=\"url\" name=\"pagemodel.url\" value=\""
						+ pagemodel.getUrl() + "\"/>");
		page
				.append("<input type=\"hidden\" id=\"recordcount\" name=\"pagemodel.recordcount\" value=\""
						+ pagemodel.getRecordcount() + "\"/>");
		page.append(" <anchor>GO <go href=\"" + pagemodel.getUrl()
				+ "\" method=\"post\">  </go> </anchor>");

		return page.toString();
	}

	public String getPageOnclick(PageModel pagemodel) {
		StringBuffer page = new StringBuffer();
		page.append("Sum " + pagemodel.getPagenumber() + " Record  "
				+ pagemodel.getCurrent() + "/" + pagemodel.getPagesum()
				+ " Page  ");
		page.append("<a href='#' onclick=\"showpage('1','"
				+ pagemodel.getPagenumber() + "','"
				+ pagemodel.getRecordcount() + "','" + pagemodel.getUrl()
				+ "','" + pagemodel.getPagesum() + "');\">[first]</a>&nbsp;");
		page
				.append("<a href='#' onclick=\"showpage('"
						+ previous(pagemodel) + "','"
						+ pagemodel.getPagenumber() + "','"
						+ pagemodel.getRecordcount() + "','"
						+ pagemodel.getUrl() + "','" + pagemodel.getPagesum()
						+ "');\">[Previous]</a>&nbsp;");
		page.append("<a href='#' onclick=\"showpage('" + nextpage(pagemodel)
				+ "','" + pagemodel.getPagenumber() + "','"
				+ pagemodel.getRecordcount() + "','" + pagemodel.getUrl()
				+ "','" + pagemodel.getPagesum() + "');\">[Next]</a>&nbsp;");
		page.append("<a href='#' onclick=\"showpage('" + lastpage(pagemodel)
				+ "','" + pagemodel.getPagenumber() + "','"
				+ pagemodel.getRecordcount() + "','" + pagemodel.getUrl()
				+ "','" + pagemodel.getPagesum()
				+ "');\">[Last]</a>&nbsp;&nbsp;GoTo");
		page
				.append("<input type='text' size='3'   id='getCurrents' name='getCurrents' value='"
						+ pagemodel.getCurrent() + "'>");
		page
				.append("<input type='button' size='2'    value='go' onclick=\"showpage(document.getElementById('getCurrents').value,'"
						+ pagemodel.getPagenumber()
						+ "','"
						+ pagemodel.getRecordcount()
						+ "','"
						+ pagemodel.getUrl()
						+ "','"
						+ pagemodel.getPagesum()
						+ "');\">");
		page.append("Page");
		return page.toString();

	}

	// First|Previous|
	// public String getPageOnclick(PageModel pagemodel){
	// StringBuffer page = new StringBuffer();
	// page.append("共 "+pagemodel.getPagenumber()+" 条  "+pagemodel.getCurrent()+"/"+pagemodel.getPagesum()+" 页  "
	// );
	// page.append("<a href='#' onclick=\"showpage('1','"+pagemodel.getPagenumber()+"','"+pagemodel.getRecordcount()+"','"+pagemodel.getUrl()+"','"+pagemodel.getPagesum()+"');\">[首页]</a>&nbsp;");
	// page.append("<a href='#' onclick=\"showpage('"+previous(pagemodel)+"','"+pagemodel.getPagenumber()+"','"+pagemodel.getRecordcount()+"','"+pagemodel.getUrl()+"','"+pagemodel.getPagesum()+"');\">[上一页]</a>&nbsp;");
	// page.append("<a href='#' onclick=\"showpage('"+nextpage(pagemodel)+"','"+pagemodel.getPagenumber()+"','"+pagemodel.getRecordcount()+"','"+pagemodel.getUrl()+"','"+pagemodel.getPagesum()+"');\">[下一页]</a>&nbsp;");
	// page.append("<a href='#' onclick=\"showpage('"+lastpage(pagemodel)+"','"+pagemodel.getPagenumber()+"','"+pagemodel.getRecordcount()+"','"+pagemodel.getUrl()+"','"+pagemodel.getPagesum()+"');\">[末页]</a>&nbsp;&nbsp;转到");
	// page.append("<input type='text' size='3'   id='getCurrents' name='getCurrents' value='"+
	// pagemodel.getCurrent() + "'>");
	// page.append("<input type='button' size='2'    value='go' onclick=\"showpage(document.getElementById('getCurrents').value,'"+pagemodel.getPagenumber()+"','"+pagemodel.getRecordcount()+"','"+pagemodel.getUrl()+"','"+pagemodel.getPagesum()+"');\">");
	// page.append("页");
	// return page.toString();
	//        
	//        
	// }

	public String getFuntcion() {
		StringBuffer bu = new StringBuffer();
		bu
				.append(" function showpage(current,pagenumber,recordcount,url,pagesum){ \n");
		bu.append("document.getElementById(\"current\").value=current; \n");
		bu
				.append("document.getElementById(\"pagenumber\").value=pagenumber; \n");
		bu
				.append("document.getElementById(\"recordcount\").value=recordcount; \n");
		bu.append("document.getElementById(\"url\").value=url; \n");
		bu.append("document.getElementById(\"pagesum\").value=pagesum;\n");
		// / bu.append("document.getElementById(\"orders\").value=orders; \n");
		bu.append("  document.form1.submit();\n");
		bu.append(" \n}");
		return bu.toString();
	}

	public String getPageModel(PageModel pagemodel) {
		StringBuffer bu = new StringBuffer();
		bu
				.append("<input type=\"hidden\" id=\"current\" name=\"pagemodel.current\" value=\""
						+ pagemodel.getCurrent() + "\"> \n");
		bu
				.append("<input type=\"hidden\" id=\"pagenumber\" name=\"pagemodel.pagenumber\" value=\""
						+ pagemodel.getPagenumber() + "\"> \n");
		bu
				.append("<input type=\"hidden\" id=\"recordcount\" name=\"pagemodel.recordcount\" value=\""
						+ pagemodel.getRecordcount() + "\"> \n");
		bu
				.append("<input type=\"hidden\" id=\"url\" name=\"pagemodel.url\" value=\""
						+ pagemodel.getUrl() + "\"> \n");
		bu
				.append("<input type=\"hidden\" id=\"pagesum\" name=\"pagemodel.pagesum\" value=\""
						+ pagemodel.getPagesum() + "\"> \n");
		// /
		// bu.append("<input type=\"hidden\" id=\"orders\" name=\"pagemodel.orders\" value=\""+pagemodel.getOrders()+"\"> \n");
		return bu.toString();
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

	/**
	 * 最后一页
	 * 
	 * @param pagemodel
	 * @return
	 */
	private int lastpage(PageModel pagemodel) {
		float ss = (float) pagemodel.getPagenumber()
				/ pagemodel.getRecordcount();
		// /pagemodel.getRecordcount()
		int s = (int) Math.ceil(ss);

		return s;

	}

	/**
	 * 总页数
	 * 
	 * @param pagemodel
	 * @return
	 */
	private int getPagecount(PageModel pagemodel) {

		return 0;
	}
}
