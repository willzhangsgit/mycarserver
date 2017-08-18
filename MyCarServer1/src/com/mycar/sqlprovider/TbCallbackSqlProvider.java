package com.mycar.sqlprovider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class TbCallbackSqlProvider {
	public String findByCSql(final Map<String, Object> para){
		return new SQL(){
			{SELECT ("*");
			 FROM ("cardb.dbo.ptd_callback_record");
			 if (Integer.valueOf(para.get("flag").toString())==1) {
				 WHERE ("action='" + para.get("action").toString() + "'");
			 }else{
				 if(Integer.valueOf(para.get("flag").toString()) == 2){
					 WHERE ("device_code='" + para.get("code").toString() + "'");
				 }else{
					 if(Integer.valueOf(para.get("flag").toString()) == 3){
						 WHERE ("order_code='" + para.get("code").toString() + "'");
					 }					 
				 }
			 }
			}}.toString();
	}
	
	
}
