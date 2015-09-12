package com.dxsfw.idea.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.idea.model.Idea;

@Repository("ideaDao")
public interface IdeaDao extends BaseDao<Idea, Integer> {
}