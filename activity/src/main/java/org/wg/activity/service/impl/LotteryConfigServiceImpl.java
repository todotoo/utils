package org.wg.activity.service.impl;

import org.springframework.stereotype.Service;
import org.wg.activity.mapper.CrudMapper;
import org.wg.activity.mapper.LotteryConfigMapper;
import org.wg.activity.service.LotteryConfigService;

import javax.annotation.Resource;

/**
 * 
 * @author wg
 * @date 2018-02-22 15:35:51
 * @version 1.0.0
 */
@Service
public class LotteryConfigServiceImpl extends BaseCrudServiceImpl implements LotteryConfigService {

    @Resource
    private LotteryConfigMapper lotteryConfigMapper;

    @Override
    public CrudMapper init() {
        return lotteryConfigMapper;
    }
}