package org.wg.activity.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 抽奖工具类,概率和可以不等于1
 * 概率为百分数去掉百分号的部分，如10%，则为10
 * 抽奖操作如下：
 * 1.输入抽奖概率集合，【抽奖概率集合为{10.0, 20.0, 30.0}】
 * 2.生成连续区间值集合，       【生成的连续区间值集合为{(0.0, 10.0],(10.0, 30.0],(30.0, 60.0]}】
 * 3.生成随机数，          【生成方法为 random.nextDouble() * maxElement】
 * 4.判断随机数在哪个区间内，返回该区间的index【生成了随机数12.001，则它属于(10.0, 30.0]，返回 index = 1】
 */
public class LotteryUtil {

    /**
     * 概率连续集合
     */
    private List<IntervalValue> lotteryList;
    /**
     * 这里只需要最大值，最小值默认为0.0
     */
    private BigDecimal maxElement = BigDecimal.ZERO;

    /**
     * 构造抽奖集合
     *
     * @param probabilityList 奖品概率集合
     */
    public LotteryUtil(List<BigDecimal> probabilityList) {
        System.err.println("奖品概率集合：" + probabilityList);
        if (probabilityList.size() == 0) {
            throw new IllegalArgumentException("抽奖集合不能为空！");
        }
        lotteryList = new ArrayList<>();
        BigDecimal minElement;
        IntervalValue intervalValue;
        for (BigDecimal d : probabilityList) {
            minElement = maxElement;
            maxElement = maxElement.add(d);
            intervalValue = new IntervalValue(minElement, maxElement);
            lotteryList.add(intervalValue);
            System.err.println("概率区间：" + intervalValue);
        }
    }

    /**
     * 进行抽奖操作
     * 返回：奖品的概率list集合中的下标
     */
    public int randomColunmIndex() {
        int index = -1;
        Random r = new Random();
        // 生成0-1间的随机数
        BigDecimal d = BigDecimal.valueOf(r.nextDouble()).multiply(maxElement);
        System.err.println("生成的概率：" + d);
        int size = lotteryList.size();
        for (int i = 0; i < size; i++) {
            IntervalValue intervalValue = lotteryList.get(i);
            if (intervalValue.isContainKey(d)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException("概率集合设置不合理！");
        }
        return index;
    }

    public BigDecimal getMaxElement() {
        return maxElement;
    }

    public List<IntervalValue> getLotteryList() {
        return lotteryList;
    }

    public void setLotteryList(List<IntervalValue> lotteryList) {
        this.lotteryList = lotteryList;
    }

    /**
     * 定义一个区间值
     * 集合中元素x满足:(minElement, maxElement]
     * 数学表达式为：minElement < x <= maxElement
     */
    public class IntervalValue {

        private BigDecimal minElement;
        private BigDecimal maxElement;

        public IntervalValue() {
        }

        public IntervalValue(BigDecimal minElement, BigDecimal maxElement) {
            if (minElement.compareTo(maxElement) > 0) {
                throw new IllegalArgumentException("区间不合理，minElement不能大于maxElement！");
            }
            this.minElement = minElement;
            this.maxElement = maxElement;
        }

        /**
         * 判断当前区间是否包含特定元素
         *
         * @param element
         * @return
         */
        public boolean isContainKey(BigDecimal element) {

            return element.compareTo(minElement) > 0 && element.compareTo(maxElement) <= 0;
        }

        @Override
        public String toString() {
            return "IntervalValue{" +
                    "minElement=" + minElement +
                    ", maxElement=" + maxElement +
                    '}';
        }
    }
}