package com.dxsfw.bbs.service;

import com.dxsfw.bbs.model.Bbs;
import com.dxsfw.bbs.model.BbsShengqing;
import com.dxsfw.common.base.BaseService;
import com.dxsfw.common.page.Pagination;

public interface BbsService extends BaseService<Bbs, Integer> {
	Bbs addBbs(Bbs bbs);
	
	Bbs addClass(Bbs bbs);

	boolean applyClass(BbsShengqing record);

	Pagination search(String keyword, Pagination p);

	Pagination myList(Integer userid, String type, Pagination p);

	Pagination myApplyList(Integer userid, Pagination p);

	Pagination applyUserList(Integer bbsid, Boolean includePublishUser, Pagination p);
	
}
