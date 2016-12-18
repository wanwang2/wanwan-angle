package org.wanwanframework.angle.core.area;

import org.wanwanframework.angle.core.FilterImpl;
import org.wanwanframwork.file.core.ConfigProperties;

/**
 * 作用域为一个方括号
 * @author coco
 *
 */
public class Node {

	public String template; // {AAA@nodeAAA} 大括号里面的内容
	public String word[]; // 目标替换字符串：@node = word;
	
	/**
	 * 生成一个node段落：一个方括号的内容
	 * @return
	 */
	public String createLine() {
		String line = "";
		for (int i = 0; i < word.length; i++) {
			String wordz = template.replace(FilterImpl.to() + "node", word[i]);
			line += wordz.substring(1, wordz.length() - 1);
		}
		return line;
	}
	
	/**
	 * @locationKey = @location1,2,3,...
	 * @param content
	 * @return
	 */
	public String locationLine(String content) {
		String line = null;
		if(word != null && word.length > 0) {
			line = content.replaceFirst(ConfigProperties.itemAnnotation, createLine());
		} else {
			line = content.replaceFirst(ConfigProperties.itemAnnotation, "");
		}
		return line;
	}
}
