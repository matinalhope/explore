package com.matianl.explore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

public class TestUtil {
	
	private static Logger log = LoggerFactory.getLogger(TestUtil.class);
	
	/**
	 * 获取request类的field
	 * 
	 * @param object
	 * @throws Exception
	 */
	public static void getNeedFields(Class<?> clazz) throws Exception {

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (Modifier.isPublic(field.getModifiers())) {
				Type genericType = field.getGenericType();
				if(genericType instanceof ParameterizedType){
					ParameterizedType type = (ParameterizedType) genericType;
					Type[] actualTypeArguments = type.getActualTypeArguments();
					Type rawType = type.getRawType();
					if(rawType instanceof Map){
						log.info("{} key类型为：{}, value类型为：{}",field.getName(), actualTypeArguments[0], actualTypeArguments[1]);
					} else {
						log.info("{}元素类型为：{}",field.getName(), actualTypeArguments[0]);
					}
				} else {
					// 打印该类的所有属性类型
					log.info("{} : {}", genericType, field.getName());
				}
			}
		}
	}

	/**
	 * 扫描Rpc服务类
	 * 
	 * @throws Exception
	 */
	public static void scanRpcService(String basePackage) throws Exception {

		String resourcePattern = "*.class";

		ConfigurableEnvironment env = new StandardEnvironment();
		String basePackagePath = ClassUtils.convertClassNameToResourcePath(env
				.resolveRequiredPlaceholders(basePackage));

		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ basePackagePath + "/" + resourcePattern;

		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

		Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

		for (Resource resource : resources) {
			String filename = resource.getFilename();
			if (filename.contains("ServiceImpl.class")) {
				String className = filename.replace(".class", "");
				Class<?> serviceClass = Class.forName(basePackage + "." + className);
				Method[] declaredMethods = serviceClass.getDeclaredMethods();
				log.info("{}", serviceClass);
				for (Method method : declaredMethods) {
					log.info(method.getName());
				}
				log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
		}
	}
}
