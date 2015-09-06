package com.dxsfw.jianzhi.service;

import com.dxsfw.common.base.BaseService;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.jianzhi.model.Jianzhi;
import com.dxsfw.jianzhi.model.JianzhiShengqing;

public interface JianzhiService extends BaseService<Jianzhi, Integer> {
	Jianzhi addJianzhi(Jianzhi jianzhi);

	boolean applyJianzhi(JianzhiShengqing record);

	Pagination search(String keyword, Pagination p);

	Pagination myList(Integer userid, Pagination p);

	Pagination myApplyList(Integer userid, Pagination p);

	Pagination applyJianLiList(Integer jianzhiid, Pagination p);
	
}
