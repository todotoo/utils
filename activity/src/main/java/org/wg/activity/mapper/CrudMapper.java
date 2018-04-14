package org.wg.activity.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO支持类
 */
public interface CrudMapper {

    /**
     * 插入全部字段
     *
     * @param entity
     * @param <T>
     * @return
     */
    <T> int insert(T entity);

    /**
     * 可选的插入字段
     *
     * @param entity
     * @param <T>
     * @return
     */
    <T> int insertSelective(T entity);

    /**
     * 删除
     *
     * @param t
     * @return
     */
    <T> int deleteByPrimaryKey(T t);

    /**
     * 更新全部字段
     *
     * @param entity
     * @param <T>
     * @return
     */
    <T> int updateByPrimaryKey(T entity);

    /**
     * 可选的更新
     *
     * @param entity
     * @param <T>
     * @return
     */
    <T> int updateByPrimaryKeySelective(T entity);

    <T> T getByPrimaryKey(String id);

    <T> T getByPrimaryKey(T entity);

    <T> List<T> listByParams(@Param("params") Map<String, Object> params);

    int countByParams(@Param("params") Map<String, Object> params);
}