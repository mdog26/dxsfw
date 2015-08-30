package com.dxsfw.common.page;

/**
 * 分页接口
 * 
 * @Author leo.zhou
 * @CreateDate 2013-5-20
 * @Version 1.0
 */
public interface Paginable {
	
	/**
	 * 总记录数
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-5-20
	 *
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 总页数
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-5-20
	 *
	 * @return
	 */
	public int getTotalPage();

	/**
	 * 每页记录数
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-5-20
	 *
	 * @return
	 */
	public int getPageSize();

	/**
	 * 当前页号
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-5-20
	 *
	 * @return
	 */
	public int getPageNo();

	/**
	 * 是否第一页
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-5-20
	 *
	 * @return
	 */
	public boolean isFirstPage();

	/**
	 * 是否最后一页
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-5-20
	 *
	 * @return
	 */
	public boolean isLastPage();

	/**
	 * 返回下页的页号
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-5-20
	 *
	 * @return
	 */
	public int getNextPage();

	/**
	 * 返回上页的页号
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-5-20
	 *
	 * @return
	 */
	public int getPrePage();
	
	/**
	 * 针对【首页 上一页  1 2 3 4 5 下一页 末页】分页样式的开始值
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-15
	 *
	 * @return 
	 */
	public int getStart();
	
	/**
	 * 针对【首页 上一页  1 2 3 4 5 下一页 末页】分页样式的结束值
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-15
	 *
	 * @return 
	 */
	public int getEnd();
}
