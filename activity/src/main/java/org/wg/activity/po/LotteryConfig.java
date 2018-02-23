package org.wg.activity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽奖活动配置表
 * @author wg
 * @date 2018-02-22 15:35:51
 * @version 1.0.0
 */
public class LotteryConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动名称.
     */
    private String name;

    /**
     * 开始时间.
     */
    private Date startDate;

    /**
     * 结束时间.
     */
    private Date endDate;

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
     * {@linkplain #name}
     *
     * @return the value of t_lottery_config.name
     */
    public String getName() {
        return name;
    }

    /**
     * {@linkplain #name}
     * @param name the value for t_lottery_config.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * {@linkplain #startDate}
     *
     * @return the value of t_lottery_config.start_date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * {@linkplain #startDate}
     * @param startDate the value for t_lottery_config.start_date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * {@linkplain #endDate}
     *
     * @return the value of t_lottery_config.end_date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * {@linkplain #endDate}
     * @param endDate the value for t_lottery_config.end_date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 
     * {@linkplain #gmtModified}
     *
     * @return the value of t_lottery_config.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * {@linkplain #gmtModified}
     * @param gmtModified the value for t_lottery_config.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 
     * {@linkplain #gmtCreate}
     *
     * @return the value of t_lottery_config.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * {@linkplain #gmtCreate}
     * @param gmtCreate the value for t_lottery_config.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "LotteryConfig{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                '}';
    }
}