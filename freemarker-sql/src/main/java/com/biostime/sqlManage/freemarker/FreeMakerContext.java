package com.biostime.sqlManage.freemarker;

import java.util.HashMap;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * FreeMakerContext上下文
 *
 */
public class FreeMakerContext extends HashMap<String, Object>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 一次放入所有键值数据
	 * @param data
	 */
	public FreeMakerContext(HashMap<String, Object> data) {
		Set<String> keys = data.keySet();
		for (String key : keys) {
			put(key, data.get(key));
		}
	}
	
	/**
	 * 单数据指定键值
	 * @param key
	 * @param data
	 */
	public FreeMakerContext(String key,Object data) {
		if(key==null)
			key= "data";
		super.put(key, data);
	}
	
	@Override
	public Object put(String key, Object value) {
		if(value instanceof String && StringUtils.isEmpty(value.toString())){
			value = null;
		}
		return super.put(key, value);
	}
	
	public FreeMakerContext() {}
}
