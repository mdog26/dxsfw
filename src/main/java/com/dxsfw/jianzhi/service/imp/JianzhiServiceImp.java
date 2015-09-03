/**
 * 
 */
package com.dxsfw.jianzhi.service.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.common.base.BaseServiceImpl;
import com.dxsfw.jianzhi.dao.JianzhiDao;
import com.dxsfw.jianzhi.model.Jianzhi;
import com.dxsfw.jianzhi.model.JianzhiExample;
import com.dxsfw.jianzhi.service.JianzhiService;

/**
 * @author riven
 *
 */
@Repository("jianzhiService")
public class JianzhiServiceImp extends BaseServiceImpl<Jianzhi, Integer> implements JianzhiService {
	private static Logger log = LoggerFactory.getLogger(JianzhiServiceImp.class);
	
	@Autowired
    private JianzhiDao jianzhiDao;
	
	@Override
	public JianzhiDao getDao() {
		return jianzhiDao;
	}
	/* 添加兼职基本信息(不包括图片和附件)
	 */
	@Override
	public Jianzhi addJianzhi(Jianzhi record) {
		int r = jianzhiDao.insertSelective(record);
		if (r > 0) {
			JianzhiExample example = new JianzhiExample();
			example.createCriteria().andUseridEqualTo(record.getUserid());
			example.setOrderByClause("jianzhiid desc");
			List<Jianzhi> list = jianzhiDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

//	/* 查看我发布的兼职列表List
//	 */
//	@Override
//	public List<Jianzhi> getJianzhiByUser(int userid) {
//		JianzhiExample example = new JianzhiExample();
//		example.createCriteria().andUseridEqualTo(userid);
//		example.setOrderByClause("jianzhiid desc");
//		List<Jianzhi> list = jianzhiDao.selectByExample(example);
//		return list;
//	}

}
