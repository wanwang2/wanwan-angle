package org.wanwanframework.angle.core.context;

import java.util.LinkedList;
import java.util.List;

import org.wanwanframework.angle.core.FileModel;

public abstract class Controller {
	
	/**
	 * 已经读取的文件list
	 */
	protected List<String> readedFiles = new LinkedList<String>();
	
	/**
	 * 输出文件list
	 */
	protected List<String> writeFiles = new LinkedList<String>();
	
	/**
	 * 初始化放到子类
	 */
	protected WriteService writeService; 
	
	protected FileModel model;
	protected String path;
	 
	public FileModel getModel() {
		return model;
	}

	public void setModel(FileModel model) {
		this.model = model;
	}
	
	public WriteService getView() {
		return writeService;
	}

	public List<String> getFiles() {
		return readedFiles;
	}

	public List<String> getViewFiles() {
		return writeFiles;
	}

}
