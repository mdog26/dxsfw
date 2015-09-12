package com.dxsfw.idea.service;

import com.dxsfw.common.base.BaseService;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.idea.model.Zhengji;

public interface ZhengjiService extends BaseService<Zhengji, Integer> {
	Zhengji addZhengji(Zhengji chuangye);
	
	Pagination search(String keyword, Pagination p);

	Pagination myList(Integer userid, Pagination p);
}
