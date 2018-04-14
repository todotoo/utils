package org.wg.activity.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.wg.activity.po.Prize;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class LotteryServicelTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private LotteryService lotteryService;

    @Test
    public void testLottery() throws Exception {

        Prize prize = lotteryService.lottery(1, 1);
        System.out.println(prize);
    }
}