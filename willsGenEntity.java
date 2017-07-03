package GwMybatis.com;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import GwMybatis.bo.*;
import GwMybatis.utils.userMapper;

public class willsGenEntity {

	private static String tablename = "cardb.dbo.UserEnrollment";

	private static String[] colnames; // 列名数组
	private static String[] colTypes; //列名类型数组
	private static int[] colSizes; //列名大小数组
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//创建连接
    	Connection con;

        
        //数据库连接
    	String URL ="jdbc:sqlserver://192.168.1.103:1433";
    	String NAME = "sa";
    	String PASS = "123";
    	String DRIVER ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    	
		//查要生成实体类的表
    	String sql = "select * from " + tablename;
    	PreparedStatement pStemt = null;
    	try {
    		try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		con = DriverManager.getConnection(URL,NAME,PASS);
			pStemt = con.prepareStatement(sql);
			ResultSetMetaData rsmd = pStemt.getMetaData();
			int size = rsmd.getColumnCount();	//统计列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);
				
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}
			
			String content = parse(colnames,colTypes,colSizes);
			
			System.out.println(content);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{

		}
	}
	
	private static String parse(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\r\n");
		//注释部分
		sb.append("   /**\r\n");
		sb.append("    * "+tablename+" 实体类\r\n");
		sb.append("    */ \r\n");
		//实体部分
		sb.append("\r\n\r\npublic class " + initcap(tablename) + "{\r\n");
		processAllAttrs(sb);//属性
		processAllMethod(sb);//get set方法
		sb.append("}\r\n");
		
    	//System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * 功能：生成所有属性
	 * @param sb
	 */
	private static void processAllAttrs(StringBuffer sb) {
		
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\r\n");
		}
		
	}
 
	/**
	 * 功能：生成所有方法
	 * @param sb
	 */
	private static void processAllMethod(StringBuffer sb) {
		
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " + 
					colnames[i] + "){\r\n");
			sb.append("\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
			sb.append("\t\treturn " + colnames[i] + ";\r\n");
			sb.append("\t}\r\n");
		}
		
	}
	
	/**
	 * 功能：将输入字符串的首字母改成大写
	 * @param str
	 * @return
	 */
	private static String initcap(String str) {
		
		char[] ch = str.toCharArray();
		if(ch[0] >= 'a' && ch[0] <= 'z'){
			ch[0] = (char)(ch[0] - 32);
		}
		
		return new String(ch);
	}
 
	/**
	 * 功能：获得列的数据类型
	 * @param sqlType
	 * @return
	 */
	private static String sqlType2JavaType(String sqlType) {
		
		if(sqlType.equalsIgnoreCase("bit")){
			return "boolean";
		}else if(sqlType.equalsIgnoreCase("tinyint")){
			return "byte";
		}else if(sqlType.equalsIgnoreCase("smallint")){
			return "short";
		}else if(sqlType.equalsIgnoreCase("int")){
			return "int";
		}else if(sqlType.equalsIgnoreCase("bigint")){
			return "long";
		}else if(sqlType.equalsIgnoreCase("float")){
			return "float";
		}else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") 
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") 
				|| sqlType.equalsIgnoreCase("smallmoney")){
			return "double";
		}else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") 
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
				|| sqlType.equalsIgnoreCase("text")){
			return "String";
		}else if(sqlType.equalsIgnoreCase("datetime")){
			return "Date";
		}else if(sqlType.equalsIgnoreCase("image")){
			return "Blod";
		}
		
		return null;
	}

}
