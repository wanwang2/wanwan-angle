package org.wanwanframework.angle.list;

import java.io.IOException;

import org.wanwanframework.angle.core.FileModel;
import org.wanwanframework.angle.core.FilterImpl;
import org.wanwanframework.angle.core.context.WriteService;
import org.wanwanframework.angle.core.param.Path;
import org.wanwanframwork.file.FileUtil;

/**
 * view
 * 
 * @author coco
 *
 */
public class ListWriteService extends WriteService {

	private FilterImpl filter = new FilterImpl();

	/**
	 * write
	 */
	public void write(FileModel model, String module, String append) {
		try {
			toFileList(model, module, append); // 如果存在连续性文件属性就生成连续性文件
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成多个文件的方法：文件参数通过moduleFiles指定
	 * 
	 * @param model
	 * @param module
	 * @param append
	 * @throws IOException
	 */
	private void toFileList(FileModel model, String module, String append) throws IOException {
		String dir = Path.ROOT + model.getOutputPath() + "/" + module + "/" + model.getModuleFolder();
		String moduleFile;
		String content;
		for (int i = 0; i < ((ListMode) model).getFileModels().length; i++) {
			moduleFile = ((ListMode) model).getFileModels()[i].getName();
			content = ((ListMode) model).getFileModels()[i].getContent();
			this.file = dir + "/" + filter.toModulez(append, moduleFile);
			FileUtil.createFile(this.file, dir, content);
		}
	}

}
