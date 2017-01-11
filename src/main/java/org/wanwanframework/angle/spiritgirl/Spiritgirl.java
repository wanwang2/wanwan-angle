package org.wanwanframework.angle.spiritgirl;

import java.util.List;
import java.util.Map;

/**
 * Spiritgirl数据类型
 * @author coco
 *
 */
public class Spiritgirl {

	/**
	 * 创建文件目录及文件地址列表
	 */
	private List<String> fileList;

	/**
	 * 文件内容
	 */
	private Map<String, String> contentMap;

	public List<String> getMakeList() {
		return fileList;
	}

	public void setMakeList(List<String> makeList) {
		this.fileList = makeList;
	}

	public Map<String, String> getContentMap() {
		return contentMap;
	}

	public void setContentMap(Map<String, String> contentMap) {
		this.contentMap = contentMap;
	}

	@Override
	public String toString() {
		return "Spiritgirl [fileList=" + fileList + ", contentMap=" + contentMap + "]";
	}

}
