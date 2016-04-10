package com.matianl.explore.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.matianl.explore.repository.AdminPermissionDao;

@Repository
public class AdminPermissionDaoImpl extends AbstractDao implements AdminPermissionDao {

	@Override
	public List<Map<String, Object>> list(List<Integer> ids) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("ids", ids);
		return this.jdbcTemplate.queryForList("select * from blc_admin_permission where admin_permission_id in (:ids)",
				paramMap);
	}

}
