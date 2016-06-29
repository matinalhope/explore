package com.matianl.explore.repository.base;

import java.util.List;
import java.util.Map;

public interface BaseDao {

	<T> T findBy(String sql, Class<T> clazz, Map<String, Object> params);
	
	Map<String, Object> findBy(String sql, Map<String, Object> params);
	
	<T> List<T> findListBy(String sql, Class<T> clazz, Map<String, Object> params);
	
	List<Map<String, Object>> findListBy(String sql, Map<String, Object> params);
}
