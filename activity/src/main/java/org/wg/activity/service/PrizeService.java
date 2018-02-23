package org.wg.activity.service;


import org.wg.activity.po.Prize;

import java.util.List;

/**
 * t_prize数据库操作接口
 *
 * @author wg
 * @version 1.0.0
 * @date 2018-02-22 15:35:51
 */
public interface PrizeService extends BaseCrudService {

    /**
     * 根据活动id获取奖品列表
     *
     * @param activityId
     * @return
     */
    List<Prize> listByActivityId(Integer activityId);
}