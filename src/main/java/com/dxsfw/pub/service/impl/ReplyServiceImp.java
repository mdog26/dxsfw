 /**
 * 
 */
package com.dxsfw.pub.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.common.base.BaseServiceImpl;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.pub.dao.ReplyDao;
import com.dxsfw.pub.model.Reply;
import com.dxsfw.pub.model.ReplyExample;
import com.dxsfw.pub.service.ReplyService;

/**
 * @author riven
 *
 */
@Repository("replyService")
public class ReplyServiceImp extends BaseServiceImpl<Reply, Integer> implements ReplyService {
	private static Logger log = LoggerFactory.getLogger(ReplyServiceImp.class);
	
	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public ReplyDao getDao() {
		return replyDao;
	}
	/**
	 * 回复
	 */
	@Override
	public Reply addReply(Reply reply) {
		reply.setTime(new Date());
		int r = replyDao.insertSelective(reply);
		if (r > 0) {
			ReplyExample example = new ReplyExample();
			example.createCriteria().andTablenameEqualTo(reply.getTablename())
					.andPkEqualTo(reply.getPk())
					.andReplyuseridEqualTo(reply.getReplyuserid());
			example.setOrderByClause("time desc");
			List<Reply> list = replyDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 回复列表
	 */
	@Override
	public Pagination getReplyList(ReplyExample example, Pagination p){
		return this.queryByExample(example, p);
	}
	
	/**
	 * 删除所有回复记录 
	 */
	@Override
	public void deleteReplys(String tablename, int pk) {
		ReplyExample example = new ReplyExample();
		example.createCriteria().andTablenameEqualTo(tablename).andPkEqualTo(pk);
		replyDao.deleteByExample(example);
	}
}
