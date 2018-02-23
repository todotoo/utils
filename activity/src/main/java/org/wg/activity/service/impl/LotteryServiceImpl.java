package org.wg.activity.service.impl;

import org.springframework.stereotype.Service;
import org.wg.activity.po.Prize;
import org.wg.activity.service.LotteryService;
import org.wg.activity.service.PrizeService;
import org.wg.activity.util.DrawLotteryUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LotteryServiceImpl implements LotteryService {

    @Resource
    private PrizeService prizeService;

    @Override
    public Prize lottery(Integer userId, Integer activityId) {
        // 获取奖品列表
        List<Prize> prizeList = prizeService.listByActivityId(activityId);
        int index = DrawLotteryUtil.drawGift(prizeList);
        return prizeList.get(index);
    }
}
