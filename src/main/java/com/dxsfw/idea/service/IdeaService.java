package com.dxsfw.idea.service;

import com.dxsfw.common.base.BaseService;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.idea.model.Idea;

public interface IdeaService extends BaseService<Idea, Integer> {
	Idea addIdea(Idea chuangye);
	
	Pagination search(String keyword, Pagination p);

	Pagination myList(Integer userid, Pagination p);	
	
	void deleteByZhengji(Integer zhengjiid);

	Pagination ideaList(Integer zhengjiid, Pagination p);	
}
