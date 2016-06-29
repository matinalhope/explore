package com.matianl.explore.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.matianl.explore.repository.base.BaseDao;

@Repository
public class AdminPermissionDao {
	
	@Autowired
	private BaseDao baseDao;

	public List<Map<String, Object>> list(List<Integer> ids) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("ids", ids);
		return baseDao.findListBy("select * from blc_admin_permission where admin_permission_id in (:ids)", paramMap);
	}

}
