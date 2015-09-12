package com.dxsfw.pub.service;

import java.util.List;

import com.dxsfw.pub.model.Fujian;
import com.dxsfw.pub.model.Picture;


/**
 * 共用服务
 * @author riven
 *
 */
public interface PubService {

	void addorUpdatePicture(Picture p);
	
	void addorUpdateFujian(Fujian p);

	void deletePicture(Picture p);
	
	void deleteFujian(Fujian p);

	Picture getPicture(Integer pictureid);
	
	Fujian getFujian(Integer fujianid);

	List<Picture> getPicture(Picture p);
	
	List<Fujian> getFujian(Fujian p);

	Picture addPicture(Picture p);
	
	Fujian addFujian(Fujian p);
	
}
