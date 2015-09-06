/**
 * <!-- 服务启动加载 -->
	<listener>
		<listener-class>com.transn.fcs.common.ServerContextListener</listener-class>
	</listener>
 */
package com.dxsfw.common.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dxsfw.common.constants.GlobalValue;
import com.dxsfw.common.util.PropertiesReader;

/**
 * @author rdong
 *
 */
public class ServerContextListener implements ServletContextListener {

	private static Logger logger = LoggerFactory.getLogger(ServerContextListener.class);
	
	/* 服务关闭调用方法
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/* 服务启动调用方法
	 * tomcat配置虚拟目录可能会启动多次项目，同时可能调用多次启动监听
	 * 1.加载共用路径信息
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent contextEvent) {
		//#加载共用路径信息
		String siteRoot = this.getSiteRoot(contextEvent);
		String fileServer = PropertiesReader.readByKey("file.server.path");
		GlobalValue.PATH_PICTURE = fileServer  + "picture" + File.separator;
		GlobalValue.PATH_USER_PICTURE = fileServer  + "user" + File.separator + "picture" + File.separator;
		GlobalValue.PATH_JIANLI_PICTURE = fileServer + "jianli" + File.separator + "picture" + File.separator;
		GlobalValue.PATH_JIANLI_FUJIAN = fileServer + "jianli" + File.separator + "fujian" + File.separator;
	}
	
	private String getSiteRoot(ServletContextEvent event) {
		String root = event.getServletContext().getRealPath("/");
		if (root == null) {
			try {
				root = event.getServletContext().getResource("/").getPath();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!root.endsWith("/") && !root.endsWith("\\"))
			root = root + "/";
		return root;
	}
}
