package org.wg.activity.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.wg.activity.mapper.CrudMapper;
import org.wg.activity.mapper.PrizeMapper;
import org.wg.activity.service.PrizeService;

/**
 * 
 * @author wg
 * @date 2018-02-22 15:35:51
 * @version 1.0.0
 */
@Service
public class PrizeServiceImpl extends BaseCrudServiceImpl implements PrizeService {

    @Resource
    private PrizeMapper prizeMapper;

    @Override
    public CrudMapper init() {
        return prizeMapper;
    }
}