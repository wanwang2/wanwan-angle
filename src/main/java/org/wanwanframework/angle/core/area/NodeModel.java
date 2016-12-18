package org.wanwanframework.angle.core.area;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wanwanframwork.file.core.ConfigProperties;

/**
 * 作用域为并行的多个方括号
 * 
 * @author coco
 *
 */
public class NodeModel {
	
	private static final String circle_regex = "\\{[\\d\\D]*?\\}"; // 正则表达式
	
	private List<Node> nodeArray;
	private String content;

	/**
	 * 通过正则表达式处理
	 * str = (a)(b)(c)(d)(e) regex = "\\(.*?\\)";
	 * 
	 * @param source
	 */
	public void processSymbol(String source, String[] word) {
		content = source.replaceAll(circle_regex, ConfigProperties.itemAnnotation);
		Pattern pattern = Pattern.compile(circle_regex);
		Matcher matcher = pattern.matcher(source);
		nodeArray = new ArrayList<Node>();
		while (matcher.find()) {
			Node node = new Node();
			node.template = matcher.group(0);
			node.word = word;
			nodeArray.add(node);
			System.out.println("node.template: " + node.template);
		}
		System.out.println("replace: " + content);
	}

	public void createNodeContent() {
		for (int i = 0; i < nodeArray.size(); i++) {
			content = nodeArray.get(i).locationLine(content);
		}
		System.out.println("content: " + content);
	}
	
	public String getContent() {
		return content;
	}

	public static void main(String[] args) {
		NodeModel model = new NodeModel();
		String[] word = new String[]{"vv", "oo", "cc"};
		model.processSymbol("---------t{aaaa..@node..aaaaaaa}--t{aaaavv..@node..vvaaaaaaa}--t", word);
		model.createNodeContent();
	}
}
