package com.dxsfw.bbs.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.bbs.model.BbsShengqing;
import com.dxsfw.common.base.BaseDao;

@Repository("bbsShengqingDao")
public interface BbsShengqingDao extends BaseDao<BbsShengqing, Integer> {
}