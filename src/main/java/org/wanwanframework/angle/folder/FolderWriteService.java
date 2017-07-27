package org.wanwanframework.angle.folder;

import org.wanwanframework.angle.core.FileVo;
import org.wanwanframwork.file.FileUtil;

/**
 * 文件夹写文件Service
 * 
 * @author coco
 *
 */
public class FolderWriteService {

	public void write(String path, FileVo[] fileVoList, String toPath) {
		String dir;
		for (int i = 0; i < fileVoList.length; i++) {
			
			String[] moduleList = FileUtil.readDir(path, "@.properties");
			for (int j = 0; j < moduleList.length; j++) {
				dir = path + moduleList[j] + "/";
				String toDir = toPath + "/" + toModule(moduleList[j], fileVoList[i].getName()) + "/";
				FileUtil.copyDir(dir, toDir);
			}
		}
	}
	
	private String toModule(String word, String value) {
		word = word.replace("@module", value);
		return word;
	}
}
