package com.matianl.explore;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matianl.explore.service.AdminPermissionService;

/**
 * Hello world!
 */
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-conf/applicationContext.xml");
		testList(ctx);
	}

	private static void testList(AbstractApplicationContext ctx) {
		AdminPermissionService bean2 = ctx.getBean(AdminPermissionService.class);
		List<Map<String, Object>> list = bean2.list(Arrays.asList(-1));
		log.info("list result: {}", list);
	}
}
