package org.wg.activity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽奖次数
 * @author wg
 * @date 2018-02-22 15:35:51
 * @version 1.0.0
 */
public class LotteryNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id.
     */
    private Integer userId;

    /**
     * 可抽次数.
     */
    private Integer count;

    /**
     * 修改时间.
     */
    private Date gmtModified;

    /**
     * 创建时间.
     */
    private Date gmtCreate;

    /**
     * 
     * {@linkplain #userId}
     *
     * @return the value of t_lottery_number.user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * {@linkplain #userId}
     * @param userId the value for t_lottery_number.user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 
     * {@linkplain #count}
     *
     * @return the value of t_lottery_number.count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * {@linkplain #count}
     * @param count the value for t_lottery_number.count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * {@linkplain #gmtModified}
     *
     * @return the value of t_lottery_number.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * {@linkplain #gmtModified}
     * @param gmtModified the value for t_lottery_number.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 
     * {@linkplain #gmtCreate}
     *
     * @return the value of t_lottery_number.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * {@linkplain #gmtCreate}
     * @param gmtCreate the value for t_lottery_number.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}