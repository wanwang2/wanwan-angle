package org.wanwanframework.angle.link;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

import org.wanwanframework.angle.core.FileVo;
import org.wanwanframework.angle.core.param.Path;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.PropertyUtil;
import org.wanwanframwork.file.util.NameUtil;

/**
 * 主要控制类
 * 
 * @author lironghai
 * 
 */
public class LinkController {

	private LinkMode model;
	private Scanner in;
	private LinkWriteService view = new LinkWriteService();
	private String path;
 
	public LinkMode getModel() {
		return model;
	}

	public void setModel(LinkMode model) {
		this.model = model;
	}

	/**
	 * ./src/resource/template/test.template
	 *
	 */
	public void read() {
		String[] tempFiles = model.getTemplates().split("/");
		String[] modules = model.getModule().split("/");
		String type;
		for (int j = 0; j < modules.length; j++) {
			model.initFileModel(tempFiles.length);
			for (int i = 0; i < tempFiles.length; i++) {
				type = tempFiles[i];
				type = NameUtil.upper(type);
				toFile(type, modules[j], model.getFileModels()[i]); 
			} 
			view.write(model, modules[j], "cc");
		}
	}

	/**
	 * 读取文件夹中的内容
	 * @param templateType
	 * @param module
	 * @return
	 */
	private String toFile(String templateType, String module, FileVo fileVo){
		String file = null;
		try {
			file = model.getTemplateDir() + templateType;
			in = new Scanner(new File(file));
			toText(module, fileVo);
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
	public void toText(String module, FileVo fileVo){
		String content = "";
		while (in.hasNextLine()) {
			content = (content + in.nextLine() + "\r\n");
		}
		content = content.substring(0, content.length() - 2);
		fileVo.setContent(content);	
	}
	  
 	public LinkWriteService getView() {
		return view;
	} 
 	
 	private void start(String commandUrl){
		String templateDir = path + "/" + commandUrl + "/";
		Properties p = PropertyUtil.load(templateDir + "@.properties");
		//Property.setProperty(p); 
		LinkMode model = new LinkMode(templateDir, p);
		setModel(model);
		read();
	}
	
	public void execute(){
		path = LinkController.class.getResource(Path.RESOURCE_LINK).getPath();
		String[] list = FileUtil.readDir(path);
		String file = null;
		for (int i = 0; i < list.length; i++) {
			file = list[i];
			System.out.println("file:" + file);
			start(file);
		}
	}
 	
 	public static void main(String[] args) {
 		LinkController app = new LinkController();
		app.execute();
	}
}
