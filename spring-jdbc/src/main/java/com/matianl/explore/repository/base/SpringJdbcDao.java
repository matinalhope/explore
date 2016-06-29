package com.matianl.explore.repository.base;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public class SpringJdbcDao extends NamedParameterJdbcDaoSupport implements BaseDao {
	
	public SpringJdbcDao() {
		super();
	}

	@Override
	public <T> T findBy(String sql, Class<T> clazz, Map<String, Object> paramMap) {
		return getNamedParameterJdbcTemplate().queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(clazz));
	}

	@Override
	public <T> List<T> findListBy(String sql, Class<T> clazz, Map<String, Object> paramMap) {
		return getNamedParameterJdbcTemplate().queryForList(sql, paramMap, clazz);
	}

	@Override
	public Map<String, Object> findBy(String sql, Map<String, Object> paramMap) {
		return getNamedParameterJdbcTemplate().queryForMap(sql, paramMap);
	}

	@Override
	public List<Map<String, Object>> findListBy(String sql, Map<String, Object> paramMap) {
		return getNamedParameterJdbcTemplate().queryForList(sql, paramMap);
	}

}