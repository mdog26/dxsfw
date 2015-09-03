package com.dxsfw.jianzhi.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.jianzhi.model.Jianzhi;

@Repository("jianzhiDao")
public interface JianzhiDao extends BaseDao<Jianzhi, Integer> {
}