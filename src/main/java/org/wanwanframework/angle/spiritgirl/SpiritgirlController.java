package org.wanwanframework.angle.spiritgirl;

import org.wanwanframework.angle.core.param.Path;
import org.wanwanframework.angle.express.ExpressCopyController;

public class SpiritgirlController {

	/**
	 * 创建文件结构
	 */
	public void makeFileStruct() {
		
	}
	
	/**
	 * 创建文件内容
	 */
	public void makeFileContent() {
		
	}
	
	
	public void init() {
		path = ExpressCopyController.class.getResource(Path.RESOURCE_LIST).getPath();
		readFile(path + "/" + FILE_EXPRESS);
		readDir(path);
		write();
	}
}
