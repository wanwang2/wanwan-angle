package org.wanwanframework.angle.core.annotation;

import java.util.Properties;

import org.wanwanframework.file.map.PropertyUtil;
import org.wanwanframwork.file.Log;

/**
 * 元数据值获取器
 * 
 * @author coco
 *
 */
public class SymbolAnnotation {

	private static Properties properties;

	/**
	 * 获取Symbol注解
	 * 
	 * @param classz
	 * @return
	 */
	public static String getSymboAnnotation(Class<?> classz) {
		Symbol symbol = (Symbol) classz.getAnnotation(Symbol.class);
		if (symbol != null) {
			return symbol.value();
		} else {
			return null;
		}
	}

	public static String getSymbol(Object obj) {
		String a = SymbolAnnotation.getSymboAnnotation(obj.getClass());
		Log.log("a:" + a);
		return a;
	}

	private static Properties getProperties() {
		if (properties == null) {
			String path = SymbolAnnotation.class.getResource("/symbol.properties").getPath();
			properties = PropertyUtil.load(path);
		}
		return properties;
	}

	/**
	 * 检查是否配置某个符号：symbol.properties
	 * 
	 * @param symbol
	 * @return
	 */
	public static String getConfig(String symbol) {
		String key = (String) getProperties().get(symbol);
		if (key != null && key.length() > 0) {
			return key;
		}
		return null;
	}

	public static void main(String[] args) {
		getProperties();
	}
}
