package org.wanwanframework.angle.spiritgirl;

import java.io.IOException;
import java.util.Map;

import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.Log;
import org.wanwanframwork.file.util.MappingUtil;

/**
 * 分发格式：
 * file->path
 * 		|content
 * 
 * fileList->list<file>
 * 
 * 
 */
public class SpiritgirlController {

	private Map<String, String> param;
	
	public void init() {
		
		String[] resources = new String[]{
				"./src/main/resources/spirit/param/param.txt",
				"./src/main/resources/spirit/filelist.txt"};
		Map<String, String>[] mapArray = MappingUtil.getMapping(resources, ":\t");
		param = mapArray[0];
		processFileStructure(mapArray[1]);
		Log.log(mapArray);
	}
	
	/**
	 * 替换元数据
	 */
	private String processFilter(String content) {
		String value;
		for(String key:param.keySet()) {
			value = param.get(key);
			content = filter(content, key, value);
			Log.log("key:" + key + ", value:" + value);
		}
		return content;
	}
	
	private String filter(String content, String key, String value) {
		return content = content.replaceAll(key, value);
	}
	
	/**
	 * 处理文件系统
	 * @param map
	 */
	private void processFileStructure(Map<String, String> map) {
		String value;
		for(String key:map.keySet()) {
			value = map.get(key);
			value = processFilter(value);
			processFile(key, value);
			Log.log("key:" + key + ", value:" + value);
		}
	}
	
	/**
	 * 处理单个文件或者目录
	 * @param key
	 * @param value
	 */
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
