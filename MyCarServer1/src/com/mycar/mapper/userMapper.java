package com.mycar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycar.bo.*;

public interface userMapper {

	@Select("select * from cardb.dbo.UserEnrollment where userid = #{id}")
	public UserEnrollment findById(String id);

	@Select("select * from cardb.dbo.UserEnrollment")
	public List<UserEnrollment> findAll();

	@Select("select * from cardb.dbo.UserEnrollment where accounts = #{id}")
	public List<UserEnrollment> findByAccount(String id);

	@Select("select * from cardb.dbo.UserEnrollment where phone = #{id}")
	public List<UserEnrollment> findByPhone(String id);

	@Insert("insert into cardb.dbo.UserEnrollment(accounts,password,phone,registerdate,address,mailaddress,zipcode,receiver) values(#{accounts},#{password},#{phone},getdate(),#{address},#{mailaddress},#{zipcode},#{receiver}) ")
	@Options(useGeneratedKeys = true, keyProperty = "cardb.dbo.UserEnrollment.userid")
	public int reg(UserEnrollment user);
	
	@Update("update cardb.dbo.UserEnrollment set alllogintimes = isnull(alllogintimes,0) + 1,lastlogindate = getdate(),lastloginip = #{lastloginip} where (phone = #{phone} or accounts = #{phone}) and password = #{password}")
	public int login(UserEnrollment user);
}
