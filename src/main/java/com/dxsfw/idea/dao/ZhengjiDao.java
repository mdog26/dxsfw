package com.dxsfw.idea.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.idea.model.Zhengji;

@Repository("zhengjiDao")
public interface ZhengjiDao extends BaseDao<Zhengji, Integer> {
}