package com.matianl.explore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matianl.explore.repository.AdminPermissionDao;
import com.matianl.explore.service.AdminPermissionService;

@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

	@Autowired
	private AdminPermissionDao adminPermissionDao;
	
	@Override
	public List<Map<String, Object>> list(List<Integer> ids) {
		return adminPermissionDao.list(ids);
	}
	
}

