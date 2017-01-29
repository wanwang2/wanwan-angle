package org.wanwanframework.angle.spiritgirl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.wanwanframework.file.map.MappingUtil;
import org.wanwanframwork.file.FileReader;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.Log;

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
	private Map<String, String> contentMap = new HashMap<String, String>();
	
	public void init() {
		String url = "./src/main/resources/spirit/param";
		Map<String, String>[] mapArray = MappingUtil.getMapping(url, ":\t");
		
		String templateFile = "pom.template.xml";
		String content = FileReader.load("./src/main/resources/spirit/" + templateFile);
		String contentKey = templateFile.split("\\.")[0];
		contentMap.put(contentKey, content);
		if(mapArray.length > 0) {
			param = mapArray[0];
			for(int i = 0; i < mapArray.length - 1; i++) {
				processFileStructure(mapArray[i + 1]);
				processTemplate(mapArray[i + 1]);
			}
		}
	}
	
	/**
	 * 最后处理模板文件:得到内容后直接放到路径下面去修改
	 */
	private void processTemplate(Map<String, String> map) {
		String content;
		for(String key :contentMap.keySet()) {
			content = contentMap.get(key);
			content = processFilter(content);
			modifyFile(key, map, content);
		}
	}
	
	/**
	 * 修改文件：通过匹配文件名关键字，把内容写到空文件中
	 * @param templateKey
	 * @param map
	 * @param content
	 */
	private void modifyFile(String templateKey, Map<String, String> map, String content) {
		for(String fileKey: map.keySet()) {
			if(fileKey.indexOf(templateKey) >= 0) {
				String path = map.get(fileKey);
				try {
					FileUtil.createFile(path, content);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
			map.put(key, value); // 修改键值对
			makeFile(key, value);
			Log.log("key:" + key + ", value:" + value);
		}
	}
	
	/**
	 * 处理单个文件或者目录
	 * @param key
	 * @param value
	 */
	private void makeFile(String key, String value) {
		
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
