package org.wanwanframework.angle.link;

import java.util.Properties;

import org.wanwanframework.angle.core.FileVo;
import org.wanwanframwork.file.FileUtil;

/**
 * model
 * 
 * @author lironghai
 *
 */
public class LinkMode {

	private String packages;
	private String authors;
	private String module;
	private String moduleFolder;

	private String outputPath;
	private String templates;
	private String templateDir;
	
	private FileVo[] fileModels;
	
	private Properties property;

	public LinkMode(String templateDir, Properties property) {
		this.templateDir = templateDir;
		this.property = property;// PropertyUtil.load(fileTemplate +"@.properties");

		module = property.getProperty("module");
		moduleFolder = property.getProperty("moduleFolder");

		outputPath = property.getProperty("filePath");
		authors = property.getProperty("authors");
		packages = property.getProperty("packages");
		templates = getTemplates(templateDir);
	}

	public void initFileModel(int length) {
		FileVo model;
		this.fileModels = new FileVo[length];
		for (int i = 0; i < length; i++) {
			model = new FileVo();
			this.fileModels[i] = model;
		}
	}
	
	/**
	 * get the file template for per folder.
	 * 
	 * @param templateDir
	 */
	private String getTemplates(String templateDir) {
		String[] list = FileUtil.readDir(templateDir);
		String templateFiles = "";
		// remove the u.properties
		for (int i = 1; i < list.length; i++) {
			templateFiles += (list[i] + "/");
		}
		return templateFiles;
	}

	public String getModule() {
		return module;
	}

	public String getOutPutPath() {
		return outputPath;
	}

	public String getTemplateDir() {
		return templateDir;
	}

	public String getAuthors() {
		return authors;
	}

	public String getPackages() {
		return packages;
	}

	/**
	 * 用于生成可替换的字段:直接读取配置文件 PropertyUtil.loadProperty(fileTemplate +
	 * "@.properties");
	 * 
	 * @return
	 */
	public Properties getProperty() {
		return property;
	}

	public String getTemplates() {
		return templates;
	}

	public FileVo[] getFileModels() {
		return fileModels;
	}


	public String getModuleFolder() {
		return moduleFolder;
	}

}
