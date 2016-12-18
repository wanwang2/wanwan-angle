package org.wanwanframework.angle.core;

public class FileVo {

	/**
	 * 英文名
	 */
	protected String name;
	
	/**
	 * 中文名
	 */
	protected String describe;
	
	/**
	 * 文件内容
	 */
	protected String content;
	
	/**
	 * 文件节点
	 */
	protected String node[];
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescribe() {
		return describe;
	}
	
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String[] getNode() {
		return node;
	}
	
	public void setNode(String[] node) {
		this.node = node;
	}
}
