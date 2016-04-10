package com.matianl.explore.service;

import java.util.List;
import java.util.Map;

public interface AdminPermissionService {

	List<Map<String, Object>> list(List<Integer> ids);
}
