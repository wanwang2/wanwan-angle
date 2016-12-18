package org.wanwanframework.angle.word;

import java.io.IOException;

import org.wanwanframework.angle.core.FileModel;
import org.wanwanframework.angle.core.context.WriteService;
import org.wanwanframework.angle.core.param.Path;
import org.wanwanframework.log.Log;
import org.wanwanframwork.file.FileUtil;

/**
 * view
 * @author coco
 *
 */
public class WordWriteService extends WriteService {
   
	/**
	 * write 
	 */
	@Override
	public void write(FileModel model, String module, String append) {
		toFileList((WordMode)model, module, append);
		Log.log("WordWriteService");
	}
	
	/**
	 * 生成多个文件的方法：文件参数通过moduleFiles指定
	 * @param model
	 * @param module
	 * @param append
	 */
	private void toFileList(WordMode model, String module, String append){
		String dir = Path.ROOT + model.getOutputPath() + "/" + module + "/" + model.getModuleFolder();
		String content = "";
		WordFilter filter = new WordFilter();
		this.file = dir + "/" + filter.toModule(append, module);
		String[] words = model.getContent().split("\t|\r\n");
		String field = null;
		String word = null;
		for (int i = 0; i < words.length; i++) {
			word = words[i];
			if(word.contains("/")){
				field = word.split("-")[0];
			}
			if(words[i].length() > 0){ 
				content += filter.filter(word, model.getProperty(), field); 	 
			}
		}
		System.out.println(content);
		try {
			FileUtil.createFile(this.file, dir, content);
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

}
