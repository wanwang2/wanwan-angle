package org.wanwanframework.angle.core.context;

import java.util.Properties;

/**
 * 可过滤的接口
 * @author Administrator
 *
 */
public interface Filterable {
	
	public String filter(String word, Properties e, String str); 
}
