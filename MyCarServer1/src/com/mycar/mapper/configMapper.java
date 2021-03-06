package com.mycar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mycar.bo.*;


public interface configMapper {
	
	@Select("select * from cardb.dbo.ets_cis_config where cfg_name = #{id} and enabled=1 ")
	public ets_cis_config findById(String id);
	
	@Select("select * from cardb.dbo.ets_cis_config where enabled=1 ")
	public List<ets_cis_config> findAll();

}
