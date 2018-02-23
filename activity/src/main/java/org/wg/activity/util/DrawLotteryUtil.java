package org.wg.activity.util;

import org.wg.activity.po.Prize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static int draw(List<BigDecimal> prizeProbList) {
        List<BigDecimal> sortRateList = new ArrayList<>();
        // 计算概率总和
        BigDecimal sumRate = BigDecimal.ZERO;
        for (BigDecimal prob : prizeProbList) {
            sumRate = sumRate.add(prob);
        }

        if (BigDecimal.ZERO.compareTo(sumRate) != 0) {
            // 概率所占比例
            BigDecimal rate = BigDecimal.ZERO;
            for (BigDecimal prob : prizeProbList) {
                rate = rate.add(prob);
                // 构建一个比例区段组成的集合(避免概率和不为1)
                sortRateList.add(rate.divide(sumRate));
            }
            // 随机生成一个随机数，并排序
            BigDecimal random = BigDecimal.valueOf(Math.random());
            sortRateList.add(random);
            Collections.sort(sortRateList);
            // 返回该随机数在比例集合中的索引
            return sortRateList.indexOf(random);
        }
        return -1;
    }

    public static void main(String[] args) {
        /*List<Double> list = new ArrayList();
        list.add(78.7d);
        list.add(15.7d);
        list.add(0.8d);
        list.add(0.8d);
        list.add(0.8d);
        list.add(1.6d);
        list.add(1.6d);
        double sum = 0d;
        for (Double d : list) {
            sum += d;
        }
        System.out.println(sum);*/
        System.out.println(0.05 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);
    }
}