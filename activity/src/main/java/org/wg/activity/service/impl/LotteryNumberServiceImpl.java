package org.wg.activity.service.impl;

import org.springframework.stereotype.Service;
import org.wg.activity.mapper.CrudMapper;
import org.wg.activity.mapper.LotteryNumberMapper;
import org.wg.activity.service.LotteryNumberService;

import javax.annotation.Resource;

/**
 * 
 * @author wg
 * @date 2018-02-22 15:35:51
 * @version 1.0.0
 */
@Service
public class LotteryNumberServiceImpl extends BaseCrudServiceImpl implements LotteryNumberService {

    @Resource
    private LotteryNumberMapper lotteryNumberMapper;

    @Override
    public CrudMapper init() {
        return lotteryNumberMapper;
    }
}