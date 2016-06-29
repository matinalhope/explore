package com.matianl.explore.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matianl.explore.repository.AdminPermissionDao;

@Service
public class AdminPermissionService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminPermissionService.class);

	@Autowired
	private AdminPermissionDao adminPermissionDao;
	
	@Transactional(readOnly=true)
	@Cacheable(value = "oneDayCache", key = "#ids + 'list'")
	public List<Map<String, Object>> list(List<Integer> ids) {
		log.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		return adminPermissionDao.list(ids);
	}
	
}

