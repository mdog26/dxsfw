package com.dxsfw.common.base;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxsfw.common.page.Pagination;

/**
 * Service 抽象实现
 * 
 * @Author leo.zhou
 * @CreateDate 2013-7-11
 * @Version 1.0
 */
@Service
public abstract class BaseServiceImpl<T extends AbstractVo, PK extends Serializable> implements BaseService<T, PK> {
	
	static {
		// BeanUtils 中的类型转换器，解决NULL值复制出现：No value specified for 'XXX' 问题
		BigDecimalConverter bigDecimalCvt = new BigDecimalConverter(null);
		DateConverter dateCvt = new DateConverter(null);
		
		ConvertUtils.register(bigDecimalCvt, java.math.BigDecimal.class);
		ConvertUtils.register(dateCvt, java.util.Date.class);
	}
	
	/**
	 * DAO 接口
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-11
	 * 
	 * @param <Dao>
	 * @return
	 */
	public abstract <Dao extends BaseDao<T, PK>> Dao getDao();

	@Override
	public int add(T entity) {
		return getDao().insert(entity);
	}

	@Override
	public int addSelective(T entity) {
		return getDao().insertSelective(entity);
	}
	
	@Override
	public int deleteById(PK id) {
		return getDao().deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int deleteByIds(PK[] ids) {
		int i = 0;
		if (ids != null) {
			for (PK id : ids) {
				i += deleteById(id);
			}
		}
		return i;
	}

	@Override
	public T findById(PK id) {
		return (T) getDao().selectByPrimaryKey(id);
	}
	
	@Override
	public int updateById(T entity) {
		return getDao().updateByPrimaryKey(entity);
	}

	@Override
	public int updateByIdSelective(T entity) {
		return getDao().updateByPrimaryKeySelective(entity);
	}
	
	/**
	 * 根据条件统计数量
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-12
	 *
	 * @param <E> 需要继承 BaseExample
	 * @param example Example
	 * @return
	 */
	protected <E extends BaseExample> int countByExample(E example) {
		return getDao().countByExample(example);
	}
	
	/**
	 * 根据条件查询列表分页
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-12
	 *
	 * @param <E> 需要继承 BaseExample
	 * @param example Example
	 * @param p 分页对象
	 * @return 分页对象
	 */
	protected <E extends BaseExample> Pagination queryByExample(E example, Pagination p) {
		int totalCount = this.countByExample(example);
		p.setTotalCount(totalCount);
		p.adjustPageNo();
		
		RowBounds rb = new RowBounds(p.getFirstResult(), p.getPageSize());
		List<T> list = getDao().selectByExample(example, rb);
		p.setList(list);
		
		return p;
	}
}
