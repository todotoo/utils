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
        // 方法1
//        int index = DrawLotteryUtil.drawPrize(prizeList);
        // 方法2
        int index = new LotteryUtil(getProbabilityList(prizeList)).randomColunmIndex();
        System.out.println("index:" + index);
        return prizeList.get(index);
    }

    /**
     * 把每个奖品的概率放入集合中
     * @param prizeList
     * @return
     */
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
