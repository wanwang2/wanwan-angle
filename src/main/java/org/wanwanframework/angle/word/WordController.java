package org.wanwanframework.angle.word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

import org.wanwanframework.angle.core.FileModel;
import org.wanwanframework.angle.core.context.Control;
import org.wanwanframework.angle.core.context.Controller;
import org.wanwanframework.angle.core.context.WriteService;
import org.wanwanframework.angle.core.param.Path;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.PropertyUtil;
import org.wanwanframwork.file.util.NameUtil;

/**
 * 主要控制类:读取模板的内容,放到model里面
 * 
 * @author lironghai
 * 
 */
public class WordController extends Controller implements Control{
 
	private Scanner in;
	private WriteService view = new WordWriteService();
	private String path;
   
	/**
	 * ./src/resource/template/test.template
	 * 
	 * @param model
	 */
	public void read() {
		WordMode module = (WordMode)model;
		String[] tempFiles = module.getType().split("/");
		String[] modules = model.getModule().split("/");
		String type;
		for (int j = 0; j < modules.length; j++) {
			for (int i = 0; i < tempFiles.length; i++) {
				type = tempFiles[i];
				type = NameUtil.upper(type);
				toFile(type, modules[j], null); 
			} 
			view.write(model, modules[j], "line.word");
		}
	}

	/**
	 * 读取文件夹中的内容
	 * @param templateType
	 * @param module
	 * @param models
	 * @return
	 */
	public String toFile(String templateType, String module, FileModel vo){
		String file = null;
		try {
			file = model.getFileTemplate() + templateType;
			in = new Scanner(new File(file));
			toText(module);
			in.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * 生成每个FileModel并每个文件的内容
	 * 
	 */
	public void toText(String module){
		String content = "";
		while (in.hasNextLine()) {
			content = (content + in.nextLine() + "\r\n");
		}
		((WordMode)this.model).setContent(content);	
	}
	  
 	public WriteService getView() {
		return view;
	}
 	
 	private void start(String commandUrl){
		String modulePath = path + "/" + commandUrl + "/";
		Properties p = PropertyUtil.load(modulePath + "@.properties");
		WordMode model = new WordMode(modulePath, p);
		setModel(model);
		read();
	}
 	
 	/**
 	 * Path.RESOURCE_WORD
 	 */
 	public void execute(){
 		path = WordController.class.getResource(Path.RESOURCE_WORD).getPath();
		String[] list = FileUtil.readDir(path);
		String file = null;
		for (int i = 0; i < list.length; i++) {
			file = list[i];
			System.out.println("file:" + file);
			start(file);
		}
	}
 	
 	public static void main(String[] args) {
 		WordController app = new WordController();
		app.execute();
	}
}
