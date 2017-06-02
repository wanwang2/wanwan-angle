package org.wanwanframework.angle.list;

import java.util.Map;
import java.util.Properties;

import org.wanwanframework.angle.core.FileModel;
import org.wanwanframework.angle.core.FileVo;
import org.wanwanframework.angle.core.context.Control;
import org.wanwanframework.angle.core.context.AngleController;
import org.wanwanframework.file.map.LineTool;
import org.wanwanframework.file.map.PropertyUtil;
import org.wanwanframwork.file.FileUtil;

/**
 * 主要控制类
 * 
 * @author lironghai
 * 
 */
public class ListController extends AngleController implements Control {

	private Map<String, String> config;
	
	public ListController() {
		this.writeService = new ListWriteService();
	}

	/**
	 * ./src/resource/template/test.template
	 *
	 */
	public void read() {
		String[] tempFiles = ((ListMode) model).getTemplates().split("/");
		String type;
		for (int i = 0; i < tempFiles.length; i++) {
			type = tempFiles[i];
			// type = PropertyUtil.upperName(type);
			String[] modules = model.getModule().split("/");
			String file = null;
			for (int j = 0; j < modules.length; j++) {
				file = toFile(type, modules[j], model);
			}
			this.readedFiles.add(file);
			this.writeFiles.add(this.writeService.getFile());
		}
	}

	public String toFile(String templateType, String module, FileModel models) {
		String file = model.getTemplatePath() + templateType;
		String content = FileUtil.readFile(file);// 获取文件与字节流中的内容
		toFilter(content, module, ((ListMode) models).getFileModels());// 过滤文件
		writeService.write(model, module, templateType);
		return file;
	}

	private void toFilter(String content, String module, FileVo[] file) {
		FileVo model;
		ListFilter vo;
		ListFilter.readStatic(content, this.getModel().getProperty(), null);// 读取全局元数据
		for (int i = 0; i < file.length; i++) {
			model = file[i];
			if (model != null) {
				vo = new ListFilter(module, model.getName(), model.getDescribe(), model.getNode());
				model.setContent(vo.filter(content, this.getModel().getProperty(), null));
				System.out.println(file[i].getContent());
			}
		}
	}

	private void start(String commandUrl) {
		String templateDir = path + "/" + commandUrl + "/";
		Properties p = PropertyUtil.load(templateDir + "@.properties");
		FileModel model = new ListMode(templateDir, p);
		this.setModel(model);
		this.read();
	}
	
	/**
	 * create a dir file
	 */
	public void process() {
		//path = ListController.class.getResource(Path.RESOURCE_LIST).getPath();
		//Log.log(path);
		config = LineTool.getConfig("./src/main/resources/list/url.txt", ":\t");
		path = config.get("path");
		String[] list = FileUtil.readDir(path, true);
		String file = null;
		for (int i = 0; i < list.length; i++) {
			file = list[i];
			System.out.println("file:" + file);
			start(file);
		}
	}

	public static void main(String[] args) {
		ListController.call(new ListController());
	}
}
