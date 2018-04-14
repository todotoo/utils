package org.wg.activity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 中奖记录表
 *
 * @author wg
 * @version 1.0.0
 * @date 2018-02-22 15:35:51
 */
public class WinningRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id.
     */
    private Integer userId;

    /**
     * 中奖奖品名称.
     */
    private String prizeName;

    /**
     * 奖品等级.
     */
    private Byte prizeGrade;

    /**
     * 中奖时间.
     */
    private Date winningTime;

    /**
     * {@linkplain #userId}
     *
     * @return the value of t_winning_record.user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * {@linkplain #userId}
     *
     * @param userId the value for t_winning_record.user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * {@linkplain #prizeName}
     *
     * @return the value of t_winning_record.prize_name
     */
    public String getPrizeName() {
        return prizeName;
    }

    /**
     * {@linkplain #prizeName}
     *
     * @param prizeName the value for t_winning_record.prize_name
     */
    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    /**
     * {@linkplain #prizeGrade}
     *
     * @return the value of t_winning_record.prize_grade
     */
    public Byte getPrizeGrade() {
        return prizeGrade;
    }

    /**
     * {@linkplain #prizeGrade}
     *
     * @param prizeGrade the value for t_winning_record.prize_grade
     */
    public void setPrizeGrade(Byte prizeGrade) {
        this.prizeGrade = prizeGrade;
    }

    /**
     * {@linkplain #winningTime}
     *
     * @return the value of t_winning_record.winning_time
     */
    public Date getWinningTime() {
        return winningTime;
    }

    /**
     * {@linkplain #winningTime}
     *
     * @param winningTime the value for t_winning_record.winning_time
     */
    public void setWinningTime(Date winningTime) {
        this.winningTime = winningTime;
    }
}