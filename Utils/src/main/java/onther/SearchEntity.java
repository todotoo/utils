/**
 * Created by eclipse3.2.
 * function:
 * User: FMD(冯敏栋)
 * Date: 2009-10-8
 * Time: 上午09:12:43
 * Email:fmdsaco99@163.com
 * To change this template use File | Settings | File Templates.
 */
package onther;

public abstract class SearchEntity {

	private String orderby; // 排序
	private String groupby; //
	private String where = "";

	private int pagestart = 1;
	private int pageend = 15;

	private PageModel page;
	private String message; // 消息

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PageModel getPage() {
		return page;
	}

	public void setPage(PageModel page) {
		this.page = page;
	}

	public int getPagestart() {
		return pagestart;
	}

	public void setPagestart(int pagestart) {
		this.pagestart = pagestart;
	}

	public int getPageend() {
		return pageend;
	}

	public void setPageend(int pageend) {
		this.pageend = pageend;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getGroupby() {
		return groupby;
	}

	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

}
