package com.mycar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.mycar.bo.*;
import com.mycar.sqlprovider.TbCallbackSqlProvider;

public interface tbcallbackMapper {
	
	@SelectProvider(type = TbCallbackSqlProvider.class, method = "findByCSql")
	public List<CallbackRecord> findByC(@Param("action") String action,@Param("code") String code,@Param("flag") int flag);
	

    @Insert({
        "insert into cardb.dbo.ptd_callback_record (action, ",
        "device_code, device_status, sign, order_code, ",
        "coin_count, time_create, status, result, ",
        "err_msg, callbacktime)",
        " values (#{action}, #{device_code}, #{device_status}, ",
        "#{sign}, #{order_code}, #{coin_count}, #{time_create}, ",
        "#{status}, #{result}, #{err_msg}, getdate())"
    })
	public int saveCallBackRecord(CallbackRecord record);

}
