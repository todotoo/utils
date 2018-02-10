package org.wg.activity.award.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by run on 2018/1/25.
 */
public class LotteryUtil {

    public static Award lottery(List<Award> awards) {
        //总的概率区间
        float totalPro = 0f;
        //存储每个奖品新的概率区间
        List<Float> proSection = new ArrayList<>();
        proSection.add(0f);
        //遍历每个奖品，设置概率区间，总的概率区间为每个概率区间的总和
        for (Award award : awards) {
            //每个概率区间为奖品概率乘以1000（把三位小数转换为整）再乘以剩余奖品数量
            totalPro += award.probability * 10 * award.count;
            proSection.add(totalPro);
        }
        //获取总的概率区间中的随机数
        Random random = new Random();
        float randomPro = (float) random.nextInt((int) totalPro);
        //判断取到的随机数在哪个奖品的概率区间中
        for (int i = 0, size = proSection.size(); i < size; i++) {
            if (randomPro >= proSection.get(i)
                    && randomPro < proSection.get(i + 1)) {
                return awards.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        List<Award> awards = new ArrayList<>();
        awards.add(new Award("a1", 0.1f, 100));
        awards.add(new Award("a2", 0.2f, 100));
        awards.add(new Award("a3", 0.3f, 100));
        awards.add(new Award("a4", 0.05f, 100));
        awards.add(new Award("a5", 0.2f, 100));
        for (int i = 0; i < 10; i++) {
            System.out.println("恭喜您，抽到了：" + lottery(awards).id);
        }
    }

    private static void test2() {
        List<Award> awards = new ArrayList<>();
        awards.add(new Award("a1", 0.1f, 10));
        awards.add(new Award("a2", 0.2f, 100));
        awards.add(new Award("a3", 0.3f, 10));
        awards.add(new Award("a4", 0.05f, 10));
        awards.add(new Award("a5", 0.2f, 100));
        for (int i = 0; i < 10; i++) {
            System.out.println("恭喜您，抽到了：" + lottery(awards).id);
        }
    }
}
