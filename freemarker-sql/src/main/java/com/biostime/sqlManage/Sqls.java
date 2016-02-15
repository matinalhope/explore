package com.biostime.sqlManage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.biostime.sqlManage.freemarker.FreeMakerContext;
import com.biostime.sqlManage.freemarker.FreeMarkerBuilder;


/**
 * SQL定义文件解析工具
 * 
 *
 */
public final class Sqls {

	private static Logger log = Logger.getLogger(Sqls.class);

	private static HashMap<String, String> sqls = new HashMap<String, String>();
	
	private String sqlsPath;
	private String classPath;
	private static Properties p;

	private Sqls() {}
	
	private Sqls(String sqlsPath) {
		//在web下使用时有用。可以按我们的需求改造
//		this.classPath = this.getClass().getResource("/").getPath().replaceAll("%20", " ");
//		this.sqlsPath = sqlsPath.replace("classpath:", classPath);
		this.sqlsPath = sqlsPath;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		String fileName = "";
		try {
			List<File> sqlFiles =  getSqlFiles();
			if (sqlFiles.size() > 0) {
				SAXReader sr = new SAXReader();
				for (File file : sqlFiles) {
					fileName = file.getName();
					Document doc = sr.read(file);
					Element root = doc.getRootElement();
					for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
						Element element = it.next();
						String key = element.attributeValue("key");
						if (sqls.containsKey(key)) {
							log.error(fileName+"中【" + key + "】key已经存在!");
							continue;
						}
						sqls.put(key, element.getTextTrim());
					}
				}
			}
			
			p = new Properties();
//			File pfile = new File(classPath+"tableAlias.properties");
//			if(pfile.exists()){
//				p.load(new FileInputStream(pfile));
//			}
		} catch (DocumentException e) {
			log.error(fileName+"sql定义文件解析异常！", e);
		}/* catch (FileNotFoundException e) {
			log.error("找不到tableConfig.properties文件。",e);
		} catch (IOException e) {
			log.error("读取tableConfig.properties出错。",e);
		}*/
	}

	/**
	 * 根据定义的key值提取sql
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		if (!sqls.containsKey(key)) {
			log.error("key为【" + key + "】的sql未定义！");
			return "";
		}
		return sqls.get(key);
	}
	
	/**
	 * 根据定义的key值提取sql
	 * 
	 * @param key
	 * @param alias
	 * 	表别名(会把SQL中#table#替换成alias值,<br/>需要配置tableAlias.properties放在class环境下)
	 * @return
	 */
	public static String get(String key,String alias) {
		if (!sqls.containsKey(key)) {
			log.error("key为【" + key + "】的sql未定义！");
			return "";
		}
		return sqls.get(key).replaceAll("#table#", getTableNameByAlias(alias));
	}
	
	/**
	 * 支持FreeMaker获取sql
	 * @param key
	 * @param alias
	 * @param ctx
	 * @return
	 */
	public static String get(String key,String alias,FreeMakerContext ctx) {
		if (!sqls.containsKey(key)) {
			log.error("key为【" + key + "】的sql未定义！");
			return "";
		}
		return FreeMarkerBuilder.processString(sqls.get(key).replaceAll("#table#", getTableNameByAlias(alias)), ctx);
	}
	
	/**
	 * 支持FreeMaker获取sql
	 * @param key
	 * @param ctx
	 * @return
	 */
	public static String get(String key,FreeMakerContext ctx) {
		if (!sqls.containsKey(key)) {
			log.error("key为【" + key + "】的sql未定义！");
			return "";
		}
		return FreeMarkerBuilder.processString(sqls.get(key), ctx);
	}
	
	/**
	 * 根据别名获取表名
	 * @param alias
	 * @return
	 */
	public static String getTableNameByAlias(String alias){
		return p.getProperty(alias,"");
	}
	
	/**
	 * 获取所有SQL定义XML
	 * @return
	 */
	private List<File> getSqlFiles(){
		List<File> sqlFiles = new ArrayList<File>();
		File basePath = new File(sqlsPath);
		if(basePath.exists()){
			File[] files = basePath.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if(f.isDirectory()){
					List<File> rsSqlFiles = RecursionDir(f);
					sqlFiles.addAll(rsSqlFiles);
				}else if(f.getName().endsWith("sql.xml")){
					sqlFiles.add(f);
				}
			}
		}
		return sqlFiles;
	}
	
	/**
	 * 递归目录
	 * @param file
	 * @return
	 */
	private List<File> RecursionDir(File file){
		List<File> sqlFiles = new ArrayList<File>();
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if(f.isDirectory()){
				List<File> rsSqlFiles = RecursionDir(f);
				sqlFiles.addAll(rsSqlFiles);
			}else if(f.getName().endsWith("sql.xml")){
				sqlFiles.add(f);
			}
		}
		return sqlFiles;
	}
}
