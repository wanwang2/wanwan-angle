package org.wanwanframework.angle.core;

import java.util.Properties;

/**
 * 加载配置文件的主要数据模型
 * @author coco
 *
 */
public class FileModel {
	 
	protected String module;
	protected String moduleFolder;
	protected Properties property;
	protected String outputPath;
	protected String filePath; 
	 
	public FileModel(String filePath, Properties property){
		this.filePath = filePath;
		this.property = property;//PropertyUtil.load(filePath + "@.properties");//只有是@.properties才能保证所有模板都在其后面
		module = property.getProperty("module");
		moduleFolder = property.getProperty("moduleFolder");
		outputPath = property.getProperty("filePath");
	}
	
	public void initFileModel(String moduleFiles){
		
	}
	 
	public String getModule() {
		return module;
	}
	
	public void setModule(String module) {
		this.module = module;
	}
	
	public String getModuleFolder() {
		return moduleFolder;
	}
	
	public void setModuleFolder(String moduleFolder) {
		this.moduleFolder = moduleFolder;
	}
	 
	/**
	 * 获取所有的属性
	 * @return
	 */
	public Properties getProperty() {
		return property;
	}
	 
	public String getOutputPath() {
		return outputPath;
	}
	
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	
	public String getFileTemplate() {
		return filePath;
	}
	 
}
