package org.wanwanframework.angle.core.context;

import org.wanwanframework.angle.core.FileModel;

/**
 * 控制器接口
 * @author Administrator
 *
 */
public interface Control {
 
	public void read();
	public String toFile(String templateType, String module, FileModel models);
	public void setModel(FileModel model);
}
