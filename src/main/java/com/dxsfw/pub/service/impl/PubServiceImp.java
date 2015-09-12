 /**
 * 
 */
package com.dxsfw.pub.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.pub.dao.FujianDao;
import com.dxsfw.pub.dao.PictureDao;
import com.dxsfw.pub.model.Fujian;
import com.dxsfw.pub.model.FujianExample;
import com.dxsfw.pub.model.Picture;
import com.dxsfw.pub.model.PictureExample;
import com.dxsfw.pub.service.PubService;

/**
 * @author riven
 *
 */
@Repository("pubService")
public class PubServiceImp implements PubService {
	private static Logger log = LoggerFactory.getLogger(PubServiceImp.class);
	
	@Autowired
    private PictureDao pictureDao;
	
	@Autowired
    private FujianDao fujianDao;
	
	/* 添加或更新图片(业务唯一图片)
	 */
	@Override
	public void addorUpdatePicture(Picture p) {
		if (p.getPictureid() == null) {
			// 如果没有id, 表明单一业务的图片关系
			PictureExample example = new PictureExample();
			example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
			List<Picture> list = pictureDao.selectByExample(example);
			if (list.size() > 0) {
				p.setPictureid(list.get(0).getPictureid());
				pictureDao.updateByPrimaryKeySelective(p);
			} else {
				pictureDao.insertSelective(p);
			}
		} else {
			// 如果有唯一id, 证明肯定有数据
			pictureDao.updateByPrimaryKeySelective(p);
		}
	}
	
	/* 添加新图片
	 */
	@Override
	public Picture addPicture(Picture p) {
		int r = pictureDao.insertSelective(p);
		if (r > 0) {
			PictureExample example = new PictureExample();
			example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
			example.setOrderByClause("pictureid desc");
			List<Picture> list = pictureDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	/* 删除图片
	 */
	@Override
	public void deletePicture(Picture p) {
		if (p.getPictureid() == null && p.getTablename() != null && p.getPk() != null) {
			// 如果没有唯一id, 那么删除对应的所有图片（包括单图片）
			PictureExample example = new PictureExample();
			example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
			pictureDao.deleteByExample(example);
		} else {
			// 如果有唯一id, 证明肯定有数据
			pictureDao.deleteByPrimaryKey(p.getPictureid());
		}
	}

	/**
	 * 获取图片
	 */
	@Override
	public Picture getPicture(Integer pictureid) {
		return pictureDao.selectByPrimaryKey(pictureid);
	}
	
	/**
	 * 获取图片集
	 */
	@Override
	public List<Picture> getPicture(Picture p) {
		PictureExample example = new PictureExample();
		example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
		return pictureDao.selectByExample(example);
	}
	
	/* 更新附件
	 */
	@Override
	public void addorUpdateFujian(Fujian p) {
		if (p.getFujianid() == null) {
			// 如果没有id, 表明单一业务的附件关系
			FujianExample example = new FujianExample();
			example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
			List<Fujian> list = fujianDao.selectByExample(example);
			if (list.size() > 0) {
				p.setFujianid(list.get(0).getFujianid());
				fujianDao.updateByPrimaryKeySelective(p);
			} else {
				fujianDao.insertSelective(p);
			}
		} else {
			// 如果有唯一id, 证明肯定有数据
			fujianDao.updateByPrimaryKeySelective(p);
		}
	}
	
	/* 添加新附件
	 */
	@Override
	public Fujian addFujian(Fujian p) {
		int r = fujianDao.insertSelective(p);
		if (r > 0) {
			FujianExample example = new FujianExample();
			example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
			example.setOrderByClause("fujianid desc");
			List<Fujian> list = fujianDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	/* 删除附件
	 */
	@Override
	public void deleteFujian(Fujian p) {
		if (p.getFujianid() == null && p.getPath() != null) {
			// 如果没有唯一id
			FujianExample example = new FujianExample();
			example.createCriteria().andPathEqualTo(p.getPath());
			List<Fujian> list = fujianDao.selectByExample(example);
			if (list.size() > 0) {
				fujianDao.deleteByPrimaryKey(list.get(0).getFujianid());
			} else {
				fujianDao.deleteByPrimaryKey(p.getFujianid());
			}
		} else {
			// 如果有唯一id, 证明肯定有数据
			fujianDao.deleteByPrimaryKey(p.getFujianid());
		}
	}

	/**
	 * 获取附件
	 */
	@Override
	public Fujian getFujian(Integer fujianid) {
		return fujianDao.selectByPrimaryKey(fujianid);
	}
	
	/**
	 * 获取附件集
	 */
	@Override
	public List<Fujian> getFujian(Fujian p) {
		FujianExample example = new FujianExample();
		example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
		return fujianDao.selectByExample(example);
	}
}
