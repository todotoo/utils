package org.wg.activity.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.wg.activity.mapper.CrudMapper;
import org.wg.activity.mapper.WinningRecordMapper;
import org.wg.activity.service.WinningRecordService;

/**
 * 
 * @author wg
 * @date 2018-02-22 15:35:51
 * @version 1.0.0
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