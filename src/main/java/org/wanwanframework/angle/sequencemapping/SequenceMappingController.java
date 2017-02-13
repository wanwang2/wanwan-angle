package org.wanwanframework.angle.sequencemapping;

import java.util.Map;

import org.wanwanframework.file.map.LineTool;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.Log;

public class SequenceMappingController {

	private Map<String, String> config;
	private String path = null;
	
	public void init() {
		config = LineTool.getConfig("./src/main/resources/sequencemapping/url.txt", ":\t");
		path = config.get("path");
		Log.log("path:" + path);
		String[] list = FileUtil.readDir(path);
		String file = null;
		for (int i = 0; i < list.length; i++) {
			file = list[i];
			System.out.println("file:" + file);
			start(file);
		}
		Log.log("list:" + list);
	}
	
 	private void start(String commandUrl){
		String modulePath = path + "/" + commandUrl + "/";
		Map<String, String> content = LineTool.getLine(modulePath);
		Log.log(content);
	}

	public static void main(String[] args) {
		SequenceMappingController controller = new SequenceMappingController();
		controller.init();
	}
}
