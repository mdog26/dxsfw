package com.dxsfw.bbs.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.bbs.model.Bbs;
import com.dxsfw.common.base.BaseDao;

@Repository("bbsDao")
public interface BbsDao extends BaseDao<Bbs, Integer> {
}