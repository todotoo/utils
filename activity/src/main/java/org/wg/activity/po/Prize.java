package org.wg.activity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 奖品表
 *
 * @author wg
 * @version 1.0.0
 * @date 2018-02-22 15:35:51
 */
public class Prize implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 所属活动id.
     */
    private Integer activityId;

    /**
     * 奖品名称.
     */
    private String name;

    /**
     * 奖品等级.
     */
    private Byte grade;

    /**
     * 概率.
     */
    private BigDecimal probability;

    /**
     * 中奖次数上限.
     */
    private Short upperLimit;

    /**
     * 奖品类型：0-实物，1-红包，2-现金，3-理财金, 4-其它.
     */
    private Byte type;

    /**
     * 金额.
     */
    private Long amount;

    /**
     * 修改时间.
     */
    private Date gmtModified;

    /**
     * 创建时间.
     */
    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * {@linkplain #activityId}
     *
     * @return the value of t_prize.activity_id
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * {@linkplain #activityId}
     *
     * @param activityId the value for t_prize.activity_id
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * {@linkplain #name}
     *
     * @return the value of t_prize.name
     */
    public String getName() {
        return name;
    }

    /**
     * {@linkplain #name}
     *
     * @param name the value for t_prize.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * {@linkplain #grade}
     *
     * @return the value of t_prize.grade
     */
    public Byte getGrade() {
        return grade;
    }

    /**
     * {@linkplain #grade}
     *
     * @param grade the value for t_prize.grade
     */
    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    /**
     * {@linkplain #probability}
     *
     * @return the value of t_prize.probability
     */
    public BigDecimal getProbability() {
        return probability;
    }

    /**
     * {@linkplain #probability}
     *
     * @param probability the value for t_prize.probability
     */
    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    /**
     * {@linkplain #upperLimit}
     *
     * @return the value of t_prize.upper_limit
     */
    public Short getUpperLimit() {
        return upperLimit;
    }

    /**
     * {@linkplain #upperLimit}
     *
     * @param upperLimit the value for t_prize.upper_limit
     */
    public void setUpperLimit(Short upperLimit) {
        this.upperLimit = upperLimit;
    }

    /**
     * {@linkplain #type}
     *
     * @return the value of t_prize.type
     */
    public Byte getType() {
        return type;
    }

    /**
     * {@linkplain #type}
     *
     * @param type the value for t_prize.type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * {@linkplain #amount}
     *
     * @return the value of t_prize.amount
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * {@linkplain #amount}
     *
     * @param amount the value for t_prize.amount
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * {@linkplain #gmtModified}
     *
     * @return the value of t_prize.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * {@linkplain #gmtModified}
     *
     * @param gmtModified the value for t_prize.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * {@linkplain #gmtCreate}
     *
     * @return the value of t_prize.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * {@linkplain #gmtCreate}
     *
     * @param gmtCreate the value for t_prize.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", activityId=" + activityId +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", probability=" + probability +
                ", upperLimit=" + upperLimit +
                ", type=" + type +
                ", amount=" + amount +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                '}';
    }
}