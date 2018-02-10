package org.wg.activity.award.v2;

/**
 * Created by run on 2018/1/25.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Result {
    private int index;
    private int sumTime;
    private int time;
    private double probability;
    private double realProbability;

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

    public double getProbability() {
        return probability;
    }

    public double getRealProbability() {
        return realProbability;
    }

    public void setRealProbability(double realProbability) {
        this.realProbability = realProbability;
    }

    public Result() {

    }

    public Result(int index, int sumTime, int time, double realProbability) {
        this.setIndex(index);
        this.setTime(time);
        this.setSumTime(sumTime);
        this.setRealProbability(realProbability);

    }

    @Override
    public String toString() {
        return "索引值：" + index + "，抽奖总数：" + sumTime + "，抽中次数：" + time + "，概率："
                + realProbability + "，实际概率：" + (double) time / sumTime;
    }
}

public class TestLottery {

    static final int TIME = 100000;

    public static void iteratorMap(Map<Integer, Integer> map, List<Double> list) {
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            int index = entry.getKey();
            int time = entry.getValue();
            Result result = new Result(index, TIME, time, list.get(index));
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        //构造概率集合
        List<Double> list = new ArrayList<Double>();
        list.add(20d);
        list.add(80d);
        list.add(50d);
        list.add(30d);
        LotteryUtil ll = new LotteryUtil(list);
        double sumProbability = ll.getMaxElement();

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < TIME; i++) {
            int index = ll.randomColunmIndex();
            if (map.containsKey(index)) {
                map.put(index, map.get(index) + 1);
            } else {
                map.put(index, 1);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            double probability = list.get(i) / sumProbability;
            list.set(i, probability);
        }
        iteratorMap(map, list);

    }


}
