package com.dxsfw.party.service;

import com.dxsfw.common.base.BaseService;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.party.model.Party;
import com.dxsfw.party.model.PartyShengqing;

public interface PartyService extends BaseService<Party, Integer> {
	Party addParty(Party party);

	boolean applyParty(PartyShengqing record);

	Pagination search(String keyword, Pagination p);

	Pagination myList(Integer userid, Pagination p);

	Pagination myApplyList(Integer userid, Pagination p);

	Pagination applyUserList(Integer partyid, Boolean includePublishUser, Pagination p);
	
}
