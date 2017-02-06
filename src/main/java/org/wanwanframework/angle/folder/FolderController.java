package org.wanwanframework.angle.folder;

import java.util.Properties;
import java.util.Scanner;

import org.wanwanframework.angle.core.FileVo;
import org.wanwanframework.angle.core.context.Controller;
import org.wanwanframework.angle.core.param.Path;
import org.wanwanframework.file.map.PropertyUtil;
import org.wanwanframwork.file.FileUtil;

/**
 * 文件夹分发功能
 * @author coco
 *
 */
public class FolderController extends Controller {

	private FolderWriteService writeService = new FolderWriteService();
	private Scanner in;

	protected FolderMode getFolderModel() {
		return (FolderMode) this.model;
	}

	/**
	 * 先生成文件夹
	 * @param path
	 * @param fileVoList
	 */
	public void read(String path, FileVo[] fileVoList) {
		String toPath = Path.ROOT + model.getOutputPath() + "/" + model.getModule();
		writeService.write(path, fileVoList, toPath);
	}

	public void toText(String module, FileVo fileVo) {
		String content = "";
		while (in.hasNextLine()) {
			content = (content + in.nextLine() + "\r\n");
		}
		content = content.substring(0, content.length() - 2);
		fileVo.setContent(content);
	}

	private void start(String commandUrl){
		String templateDir = path + "/" + commandUrl + "/";
		Properties p = PropertyUtil.load(templateDir + "@.properties");
		FolderMode model = new FolderMode(templateDir, p);
		setModel(model);
		read(templateDir, model.getFileModels());
	}
	
	/**
	 * create a dir file
	 */
	public void execute(){
		path = FolderController.class.getResource(Path.RESOURCE_FOLDER).getPath();
		String[] list = FileUtil.readDir(path, true);
		String file = null;
		for (int i = 0; i < list.length; i++) {
			file = list[i];
			System.out.println("file:" + file);
			start(file);
		}
	}
	
	public static void main(String[] args) {
		FolderController controller = new FolderController();
		controller.execute();
	}
}
