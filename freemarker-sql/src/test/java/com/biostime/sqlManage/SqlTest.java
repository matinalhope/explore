package com.biostime.sqlManage;

import java.util.HashMap;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.biostime.sqlManage.freemarker.FreeMakerContext;

@ContextConfiguration(locations = { "classpath:/spring-conf/spring-test.xml" })
public class SqlTest extends AbstractTestNGSpringContextTests {
	
	/**
	 * 运行前请先修改/spring-conf/spring-test.xml里面存放sql的目录路径
	 */
	@Test
	public void test() {
		//不经过freemarker渲染的sql
		System.out.println(Sqls.get("common2.chooseDataSource"));
		
		//经过freemarker渲染的sql
		HashMap<String, Object> paramater = new HashMap<String, Object>();
		paramater.put("common.chooseDataSource", "%test%");
		String sql = Sqls.get("common.chooseDataSource", new FreeMakerContext(paramater));
		
//		实际使用时可以在控制层统一把参数封装进xxxx，然后只需：
//		String sql = Sqls.get("common.chooseDataSource", xxxx.getFreeMakerContext());
		System.out.println(sql);
	}

}
