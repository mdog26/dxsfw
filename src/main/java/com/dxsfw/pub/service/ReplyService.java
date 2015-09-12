package com.dxsfw.pub.service;

import com.dxsfw.common.base.BaseService;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.pub.model.Reply;
import com.dxsfw.pub.model.ReplyExample;


/**
 * 共用回复服务
 * @author riven
 *
 */
public interface ReplyService extends BaseService<Reply, Integer> {

	Reply addReply(Reply reply);
	
	void deleteReplys(String tablename, int pk);
	
	Pagination getReplyList(ReplyExample example, Pagination p);
}
