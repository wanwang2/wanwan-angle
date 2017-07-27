package org.wanwanframework.angle.link;

import org.wanwanframework.angle.core.param.Path;
import org.wanwanframwork.file.FileUtil;

/**
 * view
 * 
 * @author coco
 *
 */
public class LinkWriteService {

	private String file;

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
		String dir = Path.ROOT + model.getOutPutPath() + "/" + module + "/" + model.getModuleFolder();
		String content = "";
		this.file = dir + "/" + getModule(module, append);
		for (int i = 0; i < model.getFileModels().length; i++) {
			content += model.getFileModels()[i].getContent() + "\r\n";
		}
		FileUtil.createFile(file, content);
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
