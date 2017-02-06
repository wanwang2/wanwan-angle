package org.wanwanframework.angle.core.context;

import org.wanwanframework.angle.core.FileModel;
import org.wanwanframwork.file.Log;

/**
 * 需要写多个文件时才调用这个类
 * @author coco
 *
 */
public abstract class WriteService {
	
	protected String file;
	
	public void write(FileModel model, String module, String append){
		Log.log("WriteService");
	}
	
	public String getFile(){
		return file;
	}
}
