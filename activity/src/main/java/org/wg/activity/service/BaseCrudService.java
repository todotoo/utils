package org.wg.activity.service;


import org.wg.activity.exception.ServiceException;

import java.util.List;
import java.util.Map;


/**
 * crud对应Service接口
 */
public interface BaseCrudService {

    <T> int save(T modelType) throws ServiceException;


    <T> int removeById(T modelType) throws ServiceException;

    /**
     * 根据id修改实体
     *
     * @param modelType
     * @return
     * @throws ServiceException
     */
    <T> int updateById(T modelType) throws ServiceException;

    <T> T getById(T modelType) throws ServiceException;

    <T> T getById(Integer id) throws ServiceException;

    /**
     * 根据参数查询
     *
     * @param params 页面其他参数
     * @return
     * @throws ServiceException
     */
    <T> List<T> listByParams(Map<String, Object> params) throws ServiceException;

    /**
     * 根据参数查询总记录数
     *
     * @param params
     * @return
     * @throws ServiceException
     */
    int countByParams(Map<String, Object> params) throws ServiceException;
}
