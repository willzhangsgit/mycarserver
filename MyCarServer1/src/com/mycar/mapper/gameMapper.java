package com.mycar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mycar.bo.*;


public interface gameMapper {
	
	@Select("select * from cardb.dbo.GameInfo where id = #{id}")
	public GameInfo findById(String id);
	
	@Select("select * from cardb.dbo.GameInfo")
	public List<GameInfo> findAll();

}
