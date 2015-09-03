package com.dxsfw.pub.service;

import java.util.List;

import com.dxsfw.pub.model.Picture;


/**
 * 共用服务
 * @author riven
 *
 */
public interface PubService {

	void addorUpdatePicture(Picture p);

	void deletePicture(Picture p);

	Picture getPicture(Integer pictureid);

	List<Picture> getPicture(Picture p);
	
}
