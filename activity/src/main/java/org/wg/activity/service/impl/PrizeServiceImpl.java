package org.wg.activity.service.impl;

import org.springframework.stereotype.Service;
import org.wg.activity.mapper.CrudMapper;
import org.wg.activity.mapper.PrizeMapper;
import org.wg.activity.po.Prize;
import org.wg.activity.service.PrizeService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wg
 * @version 1.0.0
 * @date 2018-02-22 15:35:51
 */
@Service
public class PrizeServiceImpl extends BaseCrudServiceImpl implements PrizeService {

    @Resource
    private PrizeMapper prizeMapper;

    @Override
    public CrudMapper init() {
        return prizeMapper;
    }

    @Override
    public List<Prize> listByActivityId(Integer activityId) {
        Map<String, Object> params = new HashMap<>();
        params.put("activityId", activityId);
        return this.listByParams(params);
    }
}