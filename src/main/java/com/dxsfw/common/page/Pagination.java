package com.dxsfw.common.page;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

/**
 * 列表分页。包含list属性。
 * 
 * @Author leo.zhou
 * @CreateDate 2013-5-20
 * @Version 1.0
 */
public class Pagination extends SimplePage implements Paginable, java.io.Serializable {

	private static final long serialVersionUID = -7022149913572448763L;

	public Pagination() {
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 * @param list
	 *            分页内容
	 */
	public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	/**
	 * 第一条数据位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 当前页的数据
	 */
	private List<?> list;

	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	public void setList(List<?> list) {
		this.list = list;
	}
	
	/**
	 * 转换为 MyBatis-RowBounds
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-12
	 *
	 * @return 
	 */
	public RowBounds getRowBounds() {
		return new RowBounds(this.getFirstResult(), this.getPageSize());
	}
	
}
