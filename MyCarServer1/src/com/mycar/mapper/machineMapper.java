package com.mycar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycar.bo.*;


public interface machineMapper {
	
	@Select("select * from cardb.dbo.MachineInfo where deviceSerial = #{id}")
	public MachineInfo findById(String id);
	
	@Select("select * from cardb.dbo.MachineInfo")
	public List<MachineInfo> findAll();
	
	@Update("update cardb.dbo.MachineInfo set userid= #{userid},iscontrol = #{iscontrol} where deviceSerial = #{id}")
	public int updateContorller(String userid, String id, String iscontrol);

}
