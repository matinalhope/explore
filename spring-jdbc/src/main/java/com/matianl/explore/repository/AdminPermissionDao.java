package com.matianl.explore.repository;

import java.util.List;
import java.util.Map;

public interface AdminPermissionDao {
	
	List<Map<String, Object>> list(List<Integer> ids);

}
