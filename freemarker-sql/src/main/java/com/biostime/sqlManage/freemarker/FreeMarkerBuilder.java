package com.biostime.sqlManage.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.log4j.Logger;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreeMarker处理工具 <br>
 * <b>需要在classPath下面创建 ftl 目录存放模板。模板是.ftl后缀的文件。</b>
 * 
 */
public class FreeMarkerBuilder {
	private static final Logger log = Logger.getLogger(FreeMarkerBuilder.class);
	private static Configuration cfg;
	private static String suffix = ".ftl";

	// 初始化FreeMarker配置
	static {
		// 创建一个Configuration实例
		cfg = new Configuration();
		// 设置FreeMarker的模版文件夹位置
		try {
			String classPath = FreeMarkerBuilder.class.getResource("/").getPath().replaceAll("%20", " ");
			File ftlDir = new File(classPath + "ftl");
			if (ftlDir.exists() && ftlDir.isDirectory()) {
				cfg.setDirectoryForTemplateLoading(ftlDir);
				// 加载freemarker配置
				File pfile = new File(classPath + "freemarker.properties");
				if (pfile.exists()) {
					Properties p = new Properties();
					p.load(new FileInputStream(pfile));
					cfg.setSettings(p);
				} else {
					log.info(classPath + "下没有找到【freemarker.properties】");
				}
				// 自动导入全局模板
				File include = new File(classPath + "ftl/include");
				if (include.exists() && include.isDirectory()) {
					File[] ins = include.listFiles();
					for (File file : ins) {
						cfg.addAutoInclude("include/" + file.getName());
					}
				} else {
					log.info(classPath + "ftl目录下没找到include目录，没有需要自动加载的模板。");
				}
			}else{
				log.error(classPath + "ftl/模板更路径没有找到。");
			}
		} catch (IOException e) {
			log.error("设置模板路径错误！", e);
		} catch (TemplateException e) {
			log.error("freemarker.properties加载错误！", e);
		}
	}

	/**
	 * 根据模板，直接返回解析后生成的字符串
	 * 
	 * @param ftlName
	 *            模板文件名称(不要后缀)，必须实在ftl目录下面存在的模板文件
	 * @param context
	 *            数据
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String process(String ftlName, FreeMakerContext context) {
		String content = "";
		try {
			// 创建模版对象
			Template t = cfg.getTemplate(ftlName + suffix);
			StringWriter stringWriter = new StringWriter();
			BufferedWriter writer = new BufferedWriter(stringWriter);
			t.setEncoding("UTF-8");
			t.process(context, writer);
			content = stringWriter.toString();

			writer.flush();
			writer.close();
			stringWriter.flush();
			stringWriter.close();
		} catch (IOException e) {
			throw new FreeMarkerException("读取模板文件失败，模板名称：" + ftlName, e);
		} catch (TemplateException e) {
			throw new FreeMarkerException("模板解析异常，模板名称：" + ftlName, e);
		}
		return content;
	}

	/**
	 * 根据模板，输出到Writer
	 * 
	 * @param ftlName
	 *            模板文件名称 (不要后缀)，必须实在ftl目录下面存在的模板文件
	 * @param context
	 *            数据
	 * @param response
	 * @throws TemplateException
	 * @throws IOException
	 */
	public static void process(String ftlName, FreeMakerContext context, Writer writer) {
		try {
			// 创建模版对象
			Template t = cfg.getTemplate(ftlName + suffix);
			t.setEncoding("UTF-8");
			t.process(context, writer);
		} catch (IOException e) {
			throw new FreeMarkerException("读取模板文件失败，模板名称：" + ftlName, e);
		} catch (TemplateException e) {
			throw new FreeMarkerException("模板解析异常，模板名称：" + ftlName, e);
		}
	}
	
	/**
	 * 解析字符串模板
	 * @param StringTemplate 模板字符串
	 * @param context
	 * @return
	 */
	public static String processString(String StringTemplate, FreeMakerContext context){
		try {
			Configuration scfg = new Configuration();
			StringTemplateLoader st = new StringTemplateLoader();
			st.putTemplate("__template__", StringTemplate);
			scfg.setTemplateLoader(st);
			scfg.setNumberFormat("#.#########################");
			scfg.setDefaultEncoding("UTF-8");  
			Template template = scfg.getTemplate("__template__");
			template.setEncoding("UTF-8");
			
			StringWriter stringWriter = new StringWriter();
			BufferedWriter writer = new BufferedWriter(stringWriter);
			template.process(context, writer);
			return stringWriter.toString();
		} catch (IOException e) {
			throw new FreeMarkerException("读取模板文件失败", e);
		} catch (TemplateException e) {
			throw new FreeMarkerException("模板解析异常", e);
		}
	}
}
