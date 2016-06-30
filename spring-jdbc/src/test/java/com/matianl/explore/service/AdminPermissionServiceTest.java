package com.matianl.explore.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:spring-conf/applicationContext.xml")
public class AdminPermissionServiceTest extends AbstractTransactionalTestNGSpringContextTests {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminPermissionService adminPermissionService;
	
	@Test
	public void listTest(){
		List<Map<String, Object>> list = adminPermissionService.list(Arrays.asList(-1));
		log.info("list result: {}", list);
		log.info("---------------------------------------||||||--------------------------------------------------------");
		list = adminPermissionService.list(Arrays.asList(-1));
		log.info("list result: {}", list);
	}
}
