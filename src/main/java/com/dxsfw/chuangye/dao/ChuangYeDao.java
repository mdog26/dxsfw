package com.dxsfw.chuangye.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.chuangye.model.ChuangYe;
import com.dxsfw.common.base.BaseDao;

@Repository("chuangyeDao")
public interface ChuangYeDao extends BaseDao<ChuangYe, Integer> {
}