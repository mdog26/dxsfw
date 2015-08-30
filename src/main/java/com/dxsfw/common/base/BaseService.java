package com.dxsfw.common.base;

import java.io.Serializable;

/**
 * Service 抽象接口
 * 
 * @Author leo.zhou
 * @CreateDate 2013-7-11
 * @Version 1.0
 */
public interface BaseService <T extends AbstractVo, PK extends Serializable> {
	
	/**
	 * 添加
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param entity 
	 * @return
	 */
	int add(T entity);
	
	/**
	 * 根据所选字段添加
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param entity
	 * @return
	 */
	int addSelective(T entity);
	
	/**
	 * 根据ID删除
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param id
	 * @return 
	 */
	int deleteById(PK id);
	
	/**
	 * 根据一组ID删除
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param ids
	 * @return 
	 */
	int deleteByIds(PK[] ids);
	
	/**
	 * 根据ID查找
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param id
	 * @return 
	 */
	T findById(PK id);
	
    /**
	 * 更新
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param entity 
	 * @return 
	 */
	int updateById(T entity);
    
    /**
	 * 根据所选字段更新
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 *
	 * @param entity 
	 * @return 
	 */
	int updateByIdSelective(T entity);
}
