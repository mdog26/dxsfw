package com.dxsfw.party.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.party.model.PartyShengqing;

@Repository("partyShengqingDao")
public interface PartyShengqingDao extends BaseDao<PartyShengqing, Integer> {
}