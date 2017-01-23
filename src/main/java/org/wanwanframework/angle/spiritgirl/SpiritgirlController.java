package org.wanwanframework.angle.spiritgirl;

import java.util.Map;

import org.wanwanframwork.file.Log;
import org.wanwanframwork.file.util.MappingUtil;

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
		
		String[] resources = new String[]{
				"./src/main/resources/spirit/param/param.txt",
				"./src/main/resources/spirit/filelist.txt"};
		Map<String, String>[] mapArray = MappingUtil.getMapping(resources, ":\t");
		
		Log.log(mapArray);
	}
	
	public static void main(String[] args) {
		SpiritgirlController controller = new SpiritgirlController();
		controller.init();
	}
}
