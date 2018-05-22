package org.wg.activity.util;

import org.wg.activity.po.Prize;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 抽奖工具类
 * 思路：
 * 1、奖品概率放入集合中 【抽奖概率集合为{10.0, 20.0, 30.0}】
 * 2、生成随机数， 放入集合中， 进行排序
 * 3、返回随机数在集合中的索引
 */
public class DrawLotteryUtil {

    public static int drawPrize(List<Prize> prizeList) {
        if (null != prizeList && prizeList.size() > 0) {
            List<BigDecimal> orgProbList = new ArrayList<>(prizeList.size());
            // 按顺序将概率添加到集合中
            for (Prize prize : prizeList) {
                orgProbList.add(prize.getProbability());
            }
            return draw(orgProbList);
        }
        return -1;
    }


    /**
     * 抽奖
     *
     * @param prizeProbList
     * @return
     */
    public static int draw(List<BigDecimal> prizeProbList) {
        List<BigDecimal> sortRateList = new ArrayList<>();
        // 计算概率总和
        BigDecimal sumRate = BigDecimal.ZERO;
        for (BigDecimal prob : prizeProbList) {
            sumRate = sumRate.add(prob);
        }
        System.out.println("概率总和为：" + sumRate);

        if (BigDecimal.ZERO.compareTo(sumRate) != 0) {
            // 概率所占比例
            BigDecimal rate = BigDecimal.ZERO;
            for (BigDecimal prob : prizeProbList) {
                rate = rate.add(prob);
                // 构建一个比例区段组成的集合(避免概率和不为1)
                sortRateList.add(rate.divide(sumRate, 16, RoundingMode.HALF_UP));
            }
            // 随机生成一个随机数，放入集合中， 进行排序
            BigDecimal random = BigDecimal.valueOf(Math.random());
            System.out.println("排序前的概率区段集合：" + sortRateList);
            System.out.println("生成的随机数：" + random);
            sortRateList.add(random);
            Collections.sort(sortRateList);
            System.out.println("排序后的概率区段集合：" + sortRateList);
            // 返回该随机数在比例集合中的索引
            return sortRateList.indexOf(random);
        }
        return -1;
    }
}