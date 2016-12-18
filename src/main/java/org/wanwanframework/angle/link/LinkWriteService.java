package org.wanwanframework.angle.link;

import java.io.IOException;
import java.util.Map;

import org.wanwanframework.angle.core.param.Path;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.LineTool;

/**
 * view
 * 
 * @author coco
 *
 */
public class LinkWriteService {

	private String file;
	private Map<String, String> properties = LineTool.getLine("./src/main/resources/symbol.properties", "=");

	/**
	 * write
	 */
	public void write(LinkMode model, String module, String append) {
		toFileList(model, module, append);
	}

	/**
	 * 生成多个文件的方法：文件参数通过moduleFiles指定
	 * 
	 * @param model
	 * @param module
	 * @param append
	 */
	private void toFileList(LinkMode model, String module, String append) {
		String dir = Path.ROOT + model.getOutPutPath() + properties.get("l").trim() + module + properties.get("l").trim() + model.getModuleFolder();
		String content = "";
		this.file = dir + properties.get("l").trim() + getModule(module, append);
		for (int i = 0; i < model.getFileModels().length; i++) {
			content += model.getFileModels()[i].getContent() + "\r\n";
		}
		try {
			FileUtil.createFile(file, dir, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给文件名替换名字
	 * 
	 * @param module
	 * @param append
	 * @return
	 */
	private String getModule(String module, String append) {
		return append.replaceAll("@module", module);
	}

	public String getFile() {
		return file;
	}

}
