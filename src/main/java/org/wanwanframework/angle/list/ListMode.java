package org.wanwanframework.angle.list;

import java.util.Properties;

import org.wanwanframework.angle.core.FileModel;
import org.wanwanframework.angle.core.FileVo;
import org.wanwanframwork.file.FileUtil;

/**
 * model
 * 
 * @author lironghai
 *
 */
public class ListMode extends FileModel {

	private FileVo[] fileModels;
	private String templates; // 代码模板字符串集
	
	public ListMode(String templateDir, Properties property) {
		super(templateDir, property);
		this.initFileModel(property.getProperty("moduleFiles"));
		this.templates = FileUtil.getFilesString(templateDir);
	}

	public void initFileModel(String moduleFiles) {
		String[] fileArray = moduleFiles.split("/");
		FileVo model;
		this.fileModels = new FileVo[fileArray.length - 1];
		String nameNode = null;
		String node = null;
		String[] nameNodeArr = null;
		for (int i = 0; i < fileModels.length; i++) {
			model = new FileVo();
			// 设置子模块的child属性
			nameNode = fileArray[i].split("-")[0];
			nameNodeArr = nameNode.split("\\[|\\]");
			model.setName(nameNodeArr[0]);
			if (nameNodeArr.length > 1) {
				node = nameNodeArr[1];
				model.setNode(node.split(","));
			}

			// 设置子模块的describe属性
			model.setDescribe((fileArray[i].split("-")[1]));
			this.fileModels[i] = model;
		}
	}

	public String getTemplates() {
		return templates;
	}
 
	public FileVo[] getFileModels() {
		return fileModels;
	}
 
}
