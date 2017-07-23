package com.mycar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycar.bo.*;


public interface tbcallbackMapper {
	
	@Select("select * from cardb.dbo.ptd_callback_record where action = #{action}")
	public List<CallbackRecord> findByC(String action, String code, int flag);
	

    @Insert({
        "insert into cardb.dbo.ptd_callback_record (action, ",
        "device_code, device_status, sign, order_code, ",
        "coin_count, time_create, status, result, ",
        "err_msg, callbacktime)",
        " values (#{action}, #{deviceCode}, #{deviceStatus}, ",
        "#{sign}, #{orderCode}, #{coinCount}, #{timeCreate}, ",
        "#{status}, #{result}, #{errMsg}, #{callbacktime})"
    })
	public int saveCallBackRecord(CallbackRecord record);

}
