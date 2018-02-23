package org.wg.activity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.wg.activity.exception.ServiceException;
import org.wg.activity.mapper.CrudMapper;
import org.wg.activity.service.BaseCrudService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * 公共服务层接口
 *
 * @author wg
 */
public abstract class BaseCrudServiceImpl implements BaseCrudService {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private CrudMapper mapper;

    @PostConstruct
    private void initConfig() {
        this.mapper = init();
    }

    public abstract CrudMapper init();

    @Override
    @Transactional
    public <T> int save(T entity) throws ServiceException {
        try {
            return mapper.insertSelective(entity);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    @Transactional
    public <T> int removeById(T t) throws ServiceException {
        try {
            return mapper.deleteByPrimaryKey(t);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    @Transactional
    public <T> int updateById(T entity) throws ServiceException {
        try {
            return mapper.updateByPrimaryKeySelective(entity);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    public <T> T getById(T entity) throws ServiceException {
        try {
            return (T) mapper.getByPrimaryKey(entity);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getById(Integer id) throws ServiceException {
        try {
            return (T) mapper.getByPrimaryKey(id);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    public <T> List<T> findByBiz(Map<String, Object> params) throws ServiceException {
        try {
            return mapper.getByParams(params);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }


    @Override
    public int findCount(Map<String, Object> params) throws ServiceException {
        try {
            return mapper.getCount(params);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }
}
