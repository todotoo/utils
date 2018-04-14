package org.wg.activity.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.wg.activity.po.LotteryConfig;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
//@ContextConfiguration({"/spring/applicationContext-*.xml"})
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class LotteryConfigServiceTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private LotteryConfigService lotteryConfigService;


    @Test
    public void testGetById() {
        LotteryConfig lotteryConfig = lotteryConfigService.getById(1);
    }


}
