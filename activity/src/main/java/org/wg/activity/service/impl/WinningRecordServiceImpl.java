package org.wg.activity.service.impl;

import org.springframework.stereotype.Service;
import org.wg.activity.mapper.CrudMapper;
import org.wg.activity.mapper.WinningRecordMapper;
import org.wg.activity.service.WinningRecordService;

import javax.annotation.Resource;

/**
 * @author wg
 * @version 1.0.0
 * @date 2018-02-22 15:35:51
 */
@Service
public class WinningRecordServiceImpl extends BaseCrudServiceImpl implements WinningRecordService {

    @Resource
    private WinningRecordMapper winningRecordMapper;

    @Override
    public CrudMapper init() {
        return winningRecordMapper;
    }
}