package com.dxsfw.party.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.party.model.Party;

@Repository("partyDao")
public interface PartyDao extends BaseDao<Party, Integer> {
}