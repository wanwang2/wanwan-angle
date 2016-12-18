package org.wanwanframework.angle.word;

import java.util.Properties;

import org.wanwanframework.angle.core.FileModel;
import org.wanwanframwork.file.FileUtil;

/**
 * model 
 * @author lironghai
 *
 */
public class WordMode extends FileModel {
 
	protected String format;
 
	protected String content;
	 
	protected String type;
	
	protected String field;

	/**
	 * 构造
	 * @param fileTemplate
	 * @param property
	 */
	public WordMode(String fileTemplate, Properties property) {
		super(fileTemplate, property); 
		format = property.getProperty("format"); 
		type = getType(fileTemplate);
	}
	 
	/**
	 * get the file template for per folder.
	 * @param type
	 */
	private String getType(String type){
		String[] list = FileUtil.readDir(type);
		String templateFiles = "";
		//remove the u.properties
		for (int i = 1; i < list.length; i++) {
			templateFiles += (list[i] + "/");
		}
		return templateFiles;
	}
	
	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFormat() {
		return format;
	}

	public String getField() {
		return field;
	}

}
