package com.dxsfw.common.base;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * DAO 基类
 * 
 * @Author leo.zhou
 * @CreateDate 2013-7-11
 * @Version 1.0
 */
public interface BaseDao <T extends AbstractVo, PK extends Serializable> {

	/**
	 * 根据条件统计数量
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param <E>
	 * @param example
	 * @return
	 */
	<E extends BaseExample> int countByExample(E example);
	
	/**
	 * 根据条件删除
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param <E>
	 * @param example
	 * @return
	 */
	<E extends BaseExample> int deleteByExample(E example);
	
	/**
	 * 根据主键删除
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param id
	 * @return 
	 */
	int deleteByPrimaryKey(PK id);

    /**
     * 插入
     * 
     * @Author Leo.Zhou
     * @Version 1.0
     * @CreateDate 2013-7-11
     *
     * @param record
     * @return 
     */
    int insert(T record);

    /**
     * 选择性插入
     * 
     * @Author Leo.Zhou
     * @Version 1.0
     * @CreateDate 2013-7-11
     *
     * @param record
     * @return 
     */
    int insertSelective(T record);
    
    /**
     * 根据条件查询列表
     * 
     * @Author Leo.Zhou
     * @Version 1.0
     * @CreateDate 2013-7-11
     *
     * @param <E>
     * @param example
     * @return
     */
    <E extends BaseExample> List<T> selectByExample(E example);
    
    /**
     * 根据条件查询列表分页
     * 
     * @Author Leo.Zhou
     * @Version 1.0
     * @CreateDate 2013-7-11
     *
     * @param <E>
     * @param example
     * @param rb
     * @return
     */
    <E extends BaseExample> List<T> selectByExample(E example, RowBounds rb);
    
    /**
     * 根据主键查找
     * 
     * @Author Leo.Zhou
     * @Version 1.0
     * @CreateDate 2013-7-11
     *
     * @param id
     * @return 
     */
    T selectByPrimaryKey(PK id);
    
    /**
     * 根据条件修改
     * 
     * @Author Leo.Zhou
     * @Version 1.0
     * @CreateDate 2013-7-11
     *
     * @param <E>
     * @param record
     * @param example
     * @return
     */
    <E extends BaseExample> int updateByExample(@Param("record") T record, @Param("example") E example);
    
    /**
     * 根据条件选择性修改
     * 
     * @Author Leo.Zhou
     * @Version 1.0
     * @CreateDate 2013-7-11
     *
     * @param <E>
     * @param record
     * @param example
     * @return
     */
    <E extends BaseExample> int updateByExampleSelective(@Param("record") T record, @Param("example") E example);
    
    /**
     * 根据主键修改
     * 
     * @Author Leo.Zhou
     * @Version 1.0
     * @CreateDate 2013-7-11
     *
     * @param record
     * @return 
     */
    int updateByPrimaryKey(T record);

    /**
     * 选择性修改
     * 
     * @Author Leo.Zhou
     * @Version 1.0
     * @CreateDate 2013-7-11
     *
     * @param record
     * @return 
     */
    int updateByPrimaryKeySelective(T record);
}
