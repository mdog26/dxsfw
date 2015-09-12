package com.dxsfw.chuangye.service;

import com.dxsfw.chuangye.model.ChuangYe;
import com.dxsfw.common.base.BaseService;
import com.dxsfw.common.page.Pagination;

public interface ChuangYeService extends BaseService<ChuangYe, Integer> {
	ChuangYe addChuangYe(ChuangYe chuangye);
	
	Pagination search(String keyword, Pagination p);

	Pagination myList(Integer userid, Pagination p);	
}
