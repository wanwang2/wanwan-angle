package org.wanwanframework.angle.spiritgirl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.wanwanframwork.file.FileUtil;
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
	
	private void processFile(String key, String value) {
		
		if(key.contains("@")) {
			String keys[] = key.split("@");
			if(keys[1].indexOf("file") >= 0) {
				try {
					FileUtil.makeFile(value);
				} catch (IOException e) { 
					e.printStackTrace();
				}
			} else if(keys[1].indexOf("folder") >= 0) {
				FileUtil.makeFolder(value);
			}
		}
	}
	
	public static void main(String[] args) {
		SpiritgirlController controller = new SpiritgirlController();
		controller.init();
	}
}
