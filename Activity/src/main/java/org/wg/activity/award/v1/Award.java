package org.wg.activity.award.v1;

public class Award {
    /**
     * 编号
     */
    public String id;
    /**
     * 概率（0.1代表10%，最多3位小数，即千分之一级）
     */
    public float probability;
    /**
     * 数量（该类奖品剩余数量）
     */
    public int count;

    public Award(String id, float probability, int count) {
        super();
        this.id = id;
        this.probability = probability;
        this.count = count;
    }

    public Award() {

    }
}