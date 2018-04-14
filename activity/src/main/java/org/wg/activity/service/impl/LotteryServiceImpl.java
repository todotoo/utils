package org.wg.activity.service.impl;

import org.springframework.stereotype.Service;
import org.wg.activity.po.Prize;
import org.wg.activity.service.LotteryService;
import org.wg.activity.service.PrizeService;
import org.wg.activity.util.DrawLotteryUtil;
import org.wg.activity.util.LotteryUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LotteryServiceImpl implements LotteryService {

    @Resource
    private PrizeService prizeService;

    @Override
    public Prize lottery(Integer userId, Integer activityId) {
        // 获取奖品列表
        List<Prize> prizeList = prizeService.listByActivityId(activityId);

//        int index = DrawLotteryUtil.drawPrize(prizeList);

        int index = new LotteryUtil(getProbabilityList(prizeList)).randomColunmIndex();
        return prizeList.get(index);
    }

    public static List<BigDecimal> getProbabilityList(List<Prize> prizeList) {
        if (null != prizeList && prizeList.size() > 0) {
            List<BigDecimal> orgProbList = new ArrayList<>(prizeList.size());
            // 按顺序将概率添加到集合中
            for (Prize prize : prizeList) {
                orgProbList.add(prize.getProbability());
            }
            return orgProbList;
        }
        return Collections.emptyList();
    }
}
