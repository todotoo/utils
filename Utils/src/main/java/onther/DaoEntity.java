/**
 * Created by eclipse3.2.
 * function:
 * User: FMD(冯敏栋)
 * Date: 2009-10-11
 * Time: 下午08:17:54
 * Email:fmdsaco99@163.com
 * To change this template use File | Settings | File Templates.
 */
package onther;

import java.io.Serializable;
import java.util.List;

public class DaoEntity<T> implements Serializable {

	private List<T> lists;

	private String p;

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

}
