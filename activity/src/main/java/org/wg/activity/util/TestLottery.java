package org.wg.activity.util;

/**
 * Created by run on 2018/1/25.
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Result {
    private int index;
    private int sumTime;
    private int time;
    private BigDecimal probability;
    private BigDecimal realProbability;

    public Result() {

    }

    public Result(int index, int sumTime, int time, BigDecimal realProbability) {
        this.setIndex(index);
        this.setTime(time);
        this.setSumTime(sumTime);
        this.setRealProbability(realProbability);

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSumTime() {
        return sumTime;
    }

    public void setSumTime(int sumTime) {
        this.sumTime = sumTime;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public BigDecimal getRealProbability() {
        return realProbability;
    }

    public void setRealProbability(BigDecimal realProbability) {
        this.realProbability = realProbability;
    }

    @Override
    public String toString() {
        return "索引值：" + index + "，抽奖总数：" + sumTime + "，抽中次数：" + time + "，概率："
                + realProbability + "，实际概率：" + (double) time / sumTime;
    }
}

public class TestLottery {

    static final int TIME = 100000;

    public static void iteratorMap(Map<Integer, Integer> map, List<BigDecimal> list) {
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            int index = entry.getKey();
            int time = entry.getValue();
            Result result = new Result(index, TIME, time, list.get(index));
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        //构造概率集合
        List<BigDecimal> list = new ArrayList<>();
        list.add(BigDecimal.valueOf(0.20d));
        list.add(BigDecimal.valueOf(0.30d));
        list.add(BigDecimal.valueOf(0.15d));
        list.add(BigDecimal.valueOf(0.15d));
        LotteryUtil ll = new LotteryUtil(list);
        BigDecimal sumProbability = ll.getMaxElement();

        Map<Integer, Integer> map = new HashMap<>(3);
        for (int i = 0; i < TIME; i++) {
            int index = ll.randomColunmIndex();
            if (map.containsKey(index)) {
                map.put(index, map.get(index) + 1);
            } else {
                map.put(index, 1);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            BigDecimal probability = list.get(i).divide(sumProbability, 8, RoundingMode.DOWN);
            list.set(i, probability);
        }
        iteratorMap(map, list);
    }
}
