package org.wanwanframework.angle.word;

import java.util.Map;
import java.util.Properties;

import org.wanwanframework.angle.core.FilterImpl;
import org.wanwanframework.angle.core.context.Filterable;
import org.wanwanframwork.file.LineTool;
import org.wanwanframwork.file.Log;

public class WordFilter extends FilterImpl implements Filterable{
	
	private static Map<String, String> properties = LineTool.getLine("./src/main/resources/symbol.properties", "=");

	/**
	 * 定义过滤器
	 */
	@Override
	public String filter(String word, Properties e, String field) { 
		String[] words = word.split("-"); 
		String content = e.getProperty("format");
		if(word.trim().length() > 0){
			content = toFormatEnglish(words[0], content);
			content = toFormatChina(words[1], content); 
			content = toT(content);
			content = toField(field, content) + "\r\n";
		}

		return content;
	}
 
	/**
	 * 替换模块数据
	 * @param word
	 * @param model
	 * @return
	 */
	private String toField(String word, String content){
		return content = content.replaceAll("@field", word); 
	}
	
	/**
	 * t
	 * @param word
	 * @param model
	 * @return
	 */
	private String toT(String content){
		return content = content.replaceAll("@t", "\t"); 
	}
	
	/**
	 * 分离元数据
	 * @param word
	 * @param model
	 * @return
	 */
	public String toFormats(String word, String format){
		return format.replaceAll("@", word) + "\r\n"; 
	}
	
	/**
	 * 分离元数据
	 * @param word
	 * @param model
	 * @return
	 */
	private String toFormatEnglish(String word, String content){
		return content = content.replaceAll("@e", word); 
	}
	
	/**
	 * 分离元数据
	 * @param word
	 * @param model
	 * @return
	 */
	private String toFormatChina(String word, String content){
		return content = content.replaceAll("@c", word); 
	}
	
	public static void main(String[] args) {
		Log.log("properties:" + properties);
		Log.log("tr:" + "\r\n" + "vv");
	}
}
