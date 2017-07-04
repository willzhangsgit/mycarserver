package com.mycar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mycar.bo.*;


public interface userMapper {
	
	@Select("select * from cardb.dbo.ets_cis_config where cfg_id = #{id}")
	public ets_cis_config findById(String id);
	
	@Select("select * from cardb.dbo.ets_cis_config")
	public List<ets_cis_config> findAll();

}
