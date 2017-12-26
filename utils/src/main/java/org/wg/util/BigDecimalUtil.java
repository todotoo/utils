package org.wg.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 为避免金额计算精度丢失，将double类型转换为BigDecimal再进行相关计算
 */
public class BigDecimalUtil {
    /*
    根据给定的舍入模式将输入数字舍入为一位数的结果
    输入数字  UP    DOWN  CEILING FLOOR HALF_UP HALF_DOWN HALF_EVEN UNNECESSARY
      5.5     6     5       6       5      6        5         6     抛出 ArithmeticException
      2.5     3     2       3       2      3        2         2     抛出 ArithmeticException
      1.6     2     1       2       1      2        2         2     抛出 ArithmeticException
      1.1     2     1       2       1      1        1         1     抛出 ArithmeticException
      1.0     1     1       1       1      1        1         1      1
     -1.0     -1    -1      -1      -1     -1       -1        -1     -1
     -1.1     -2    -1      -1      -2     -1       -1        -1     抛出 ArithmeticException
     -1.6     -2    -1      -1      -2     -2       -2        -2     抛出 ArithmeticException
     -2.5     -3    -2      -2      -3     -3       -2        -2     抛出 ArithmeticException
     -5.5     -6    -5      -5      -6     -6       -5        -6     抛出 ArithmeticException

     */


    /**
     * 向上进位
     *
     * @param: newScale 返回的 BigDecimal 值的标度
     * @param: b 要转换值
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundUp(BigDecimal b, int newScale) {
        // 舍入远离零的舍入模式。在丢弃非零部分之前始终增加数字。注意，此舍入模式始终不会减少计算值的大小。
        return b.setScale(newScale, RoundingMode.UP);
    }

    /**
     * 截位
     *
     * @param: newScale 返回的 BigDecimal 值的标度
     * @param: b 要转换值
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundDown(BigDecimal b, int newScale) {
        // 接近零的舍入模式。在丢弃某部分之前始终不增加数字（即截短）。注意，此舍入模式始终不会增加计算值的大小。
        return b.setScale(newScale, RoundingMode.DOWN);
    }

    /**
     * @param: @param
     * newScale
     * @param: @param
     * b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundCeiling(BigDecimal b, int newScale) {
        return b.setScale(newScale, RoundingMode.CEILING);
    }

    /**
     * @param: @param
     * newScale
     * @param: @param
     * b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundFloor(BigDecimal b, int newScale) {
        return b.setScale(newScale, RoundingMode.FLOOR);
    }

    /**
     * @param: @param
     * newScale
     * @param: @param
     * b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundHalfUp(BigDecimal b, int newScale) {
        return b.setScale(newScale, RoundingMode.HALF_UP);
    }

    /**
     * @param: @param
     * newScale
     * @param: @param
     * b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundHalfDown(BigDecimal b, int newScale) {
        return b.setScale(newScale, RoundingMode.HALF_DOWN);
    }

    /**
     * @param: @param
     * newScale
     * @param: @param
     * b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundHalfEven(BigDecimal b, int newScale) {
        return b.setScale(newScale, RoundingMode.HALF_EVEN);
    }

    /**
     * @param: @param
     * newScale
     * @param: @param
     * b
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal setScaleRoundUnnecessary(BigDecimal b, int newScale) {
        return b.setScale(newScale, RoundingMode.UNNECESSARY);
    }

    public static void main(String[] args) {
        BigDecimal b = new BigDecimal("1.1");
        System.out.println("执行数值：" + b.toString());
        // 如果b为正数，则进行RoundUp操作，否则，RoundDown操作
//        System.out.println("setScaleRoundCeiling:" + setScaleRoundCeiling(0, b));
//        // 如果b为正数，则进行RoundDown操作，否则，RoundUp操作
//        System.out.println("setScaleRoundFloor:" + setScaleRoundFloor(1, b));
//        // 进位
//        System.out.println("setScaleRoundUp:" + setScaleRoundUp(b, 1));
//        // 截位
//        System.out.println("setScaleRoundDown:" + setScaleRoundDown(2, b));
//        // 四舍五入，截取小数 > .5 进位
//        System.out.println("setScaleRoundHalfDown:" + setScaleRoundHalfDown(2, b));
//        // 四舍五入，截取小数 >= .5 进位
//        System.out.println("setScaleRoundHalfUp:" + setScaleRoundHalfUp(2, b));
//        // 如果舍弃部分左边的数字为奇数，则作 ROUND_HALF_UP ；如果它为偶数，则作 ROUND_HALF_DOWN 。
//        // 奇数/偶数根据舍弃位数后，数值的最后一位判断，比如4.123，保留1位，为4.1，则1是奇数
//        System.out.println("setScaleRoundHalfEven:" + setScaleRoundHalfEven(1, b));
//        // 该“伪舍入模式”实际是指明所要求的操作必须是精确的，，因此不需要舍入操作。
        System.out.println("setScaleRoundUnnecessary:" + setScaleRoundUnnecessary(b, 3));


//        BigDecimal aDouble = BigDecimal.valueOf(1.22);
//        System.out.println("construct with a double value: " + aDouble);
//        BigDecimal aString = new BigDecimal("1.22");
//        System.out.println("construct with a String value: " + aString);

    }
}