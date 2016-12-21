package org.wanwanframework.angle.express;

import java.io.File;
import java.io.IOException;

import org.wanwanframework.angle.core.param.Path;
import org.wanwanframwork.file.FileUtil;

/**
 * Express是将上级文件夹的配置分发到子级文件里面的一个分发器 ——Express是针对@.properties文件进行修改的一种工具.
 * ——当一个文件夹里没有@.properties文件时，可以用这个Express去进行修改.
 * 
 * @author Administrator
 *
 */
public class ExpressCopyController {

	public static final String FILE_EXPRESS = "Express.properties";
	public static final String FILE_PROPERTY = "@.properties";
	
	private Express express = new Express();
	private String path;

	public void init() {
		path = ExpressCopyController.class.getResource(Path.RESOURCE_LIST).getPath();
		readFile(path + "/" + FILE_EXPRESS);
		readDir(path);
		write();
	}

	public void readFile(String path) {
		express.content = FileUtil.readFile(path);
	}

	public void readDir(String path) {
		express.dir = FileUtil.readFileList(path, true);
	}

	public void writeFile(String dir) {
		try {
			FileUtil.createFileUion(FILE_PROPERTY, dir, express.content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write() {
		File[] list = express.dir;
		String dir = null;
		for (File file : list) {
			dir = file.getPath();
			System.out.println("dir:" + dir);
			writeFile(dir);
		}
	}

	public static void main(String[] args) {
		ExpressCopyController controller = new ExpressCopyController();
		controller.init();
		System.out.println(controller.express);

	}
}
