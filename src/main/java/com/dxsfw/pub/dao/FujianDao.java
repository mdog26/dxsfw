package com.dxsfw.pub.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.pub.model.Fujian;

@Repository("fujianDao")
public interface FujianDao extends BaseDao<Fujian, Integer> {
}