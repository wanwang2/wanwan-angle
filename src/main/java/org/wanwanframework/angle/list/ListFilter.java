package org.wanwanframework.angle.list;

import java.util.Enumeration;
import java.util.Properties;

import org.wanwanframework.angle.core.FilterImpl;
import org.wanwanframework.angle.core.annotation.Symbol;
import org.wanwanframework.angle.core.area.NodeModel;
import org.wanwanframework.angle.core.context.Filterable;
import org.wanwanframwork.file.util.LexUtil;
import org.wanwanframwork.file.util.Sequence;

@Symbol
public class ListFilter extends FilterImpl implements Filterable {
	
	public static int static_index;// 全局的index只初始化一次
	private String module;
	private String child;
	private String describe;

	private String[] node;

	public ListFilter(String module, String child, String desc, String[] node) {
		this.module = module;
		this.child = child;
		this.describe = desc;
		this.node = node;
	}

	/**
	 * 读取全局元数据:只读一次,并在模块作用域之前读
	 * 
	 * @param word
	 * @param type
	 */
	public static void readStatic(String word, Properties e, String type) {
		if (e.containsKey("staticIndex")) {
			static_index = Integer.parseInt(e.getProperty("staticIndex"));
		}
	}

	public String filter(String word, Properties e, String type) {

		Enumeration<Object> keys = e.keys();
		while (keys.hasMoreElements()) {
			String field = (String) keys.nextElement();
			if (field.equals("module")) {
				word = toModule(word, this.module);
			} else if (field.equals("moduleFiles")) {

				word = toChild(word, this.child);// 直接大小写一起替换
				word = toLittleChild(word, this.child);
				word = toDescribe(word, this.describe);// 替换中文内容
				word = toNode(word, this.node);// 替换节点内容

				if (static_index > 0) {// 替换index内容
					Sequence integer = new Sequence(static_index);
					word = toIndex(word, integer);
					static_index = integer.i++;
				}
			} else {
				word = toProperty(word, e, field);
				// String value = e.getProperty(field);
				// word = word.replaceAll("@" + field, value);
			}
		}
		word = toModify(word);
		return word;
	}

	/**
	 * 替换文件中的节点
	 * 
	 * @param word
	 * @return
	 */
	private String toNode(String word, String[] nodez) {
		if (nodez != null) {
			word = toNodeAll(word, nodez);
		}
		NodeModel model = new NodeModel();
		model.processSymbol(word, nodez);
		model.createNodeContent();
		word = model.getContent();
		return word;
	}

	/**
	 * 修饰文件中某些字段
	 * 
	 * @param word
	 * @return
	 */
	private String toModify(String word) {
		word = toTime(word);
		word = toLocalTime(word);
		word = LexUtil.replaceContent(LexUtil.replace_express, word);
		word = word.replaceAll(LexUtil.remove_strings, "");
		return word;
	}

}
