package org.wg.activity.service;

import org.wg.activity.po.Prize;

/**
 * 抽奖服务层
 */
public interface LotteryService {


    /**
     * 抽奖
     *
     * @return
     */
    Prize lottery(Integer userId, Integer activityId);
}
