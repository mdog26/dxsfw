package com.dxsfw.pub.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.pub.model.Reply;

@Repository("replyDao")
public interface ReplyDao extends BaseDao<Reply, Integer> {
}