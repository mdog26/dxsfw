package com.dxsfw.common.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
	private static Logger log = LoggerFactory.getLogger(BaseController.class);
	
	// ---------------------------辅助方法---------------------------
		/**
		 * 下载文件
		 * @param response
		 * @param fileName
		 * @param downLoadPath
		 * @throws IOException
		 */
		private void downloadFile(HttpServletResponse response, String fileName,
				String downLoadPath) throws IOException {
			response.setContentType("multipart/form-data");
			java.io.BufferedInputStream bis = null;
			java.io.BufferedOutputStream bos = null;
			try {
				
				long fileLength = new File(downLoadPath).length();
				response.setContentType("multipart/form-data");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName);
				response.setHeader("Content-Length", String.valueOf(fileLength));
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[1024];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				log.error("downloadFile", e);
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		}
}
