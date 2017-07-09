package com.mycar.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class HttpUtil {
	/**
	 * HTTP��Post����ʽ(�Ƽ�)
	 * @param strUrl ���ʵ�ַ
	 * @param param �����ַ���
	 * */
	public String requestPost(String strUrl, String param) {
		System.out.println("HTTP��POST����:" + strUrl + ";����:" + param);
		String returnStr = null; // ���ؽ������
		URL url = null;
		HttpURLConnection httpURLConnection = null;
		
		try {
			url = new URL(strUrl);
			httpURLConnection = (HttpURLConnection) url.openConnection();	
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("POST"); // post��ʽ
			httpURLConnection.connect();
			//System.out.println("ResponseCode:" + httpURLConnection.getResponseCode());
			//POST����ʱʹ��
			byte[] byteParam = param.getBytes("UTF-8");
			DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
			out.write(byteParam);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			returnStr = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
		}
		return returnStr;
	}
	
	/**
	 * HTTP��Get����ʽ
	 * @param strUrl ���ʵ�ַ
	 * @param param �����ַ���
	 * */
	public String requestGet(String strUrl, String param) {
		System.out.println("HTTP��GET����:" + strUrl + "?" + param);
		String returnStr = null; // ���ؽ������
		URL url = null;
		HttpURLConnection httpURLConnection = null;
		try {
			url = new URL(strUrl + "?" + param);
			httpURLConnection = (HttpURLConnection) url.openConnection();			
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("GET"); // get��ʽ
			httpURLConnection.setUseCaches(false); // ���û���
			httpURLConnection.connect();
			//System.out.println("ResponseCode:" + httpURLConnection.getResponseCode());
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			returnStr = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		} finally {
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
		}
		return returnStr;
	}

	
	/**
	 * ��ȡ���ķ���
	 * @param type ����ʽ,POST,GET,�Ƽ�POST
	 */
	public void getBalance(String type) {
		String url = "http://www.stongnet.com/sdkhttp/getbalance.aspx";
		String regCode = "101100-WEB-HUAX-032304"; // ������ͨע���룬����������д���ӿͷ��ǵõ���ע����
		String regPasswod = "GFDJHKHB"; // ������ͨע�����Ӧ�����룬����������д���ӿͷ��ǵõ���ע����
		String param = "reg=" + regCode + "&pwd=" + regPasswod;
		
		String returnStr = null;
		if(type.equals("GET")){
			returnStr = this.requestGet(url, param);
		}else{
			returnStr = this.requestPost(url, param);
		}
				
		System.out.println(returnStr);
	}
	
	/**
	 * ���Ͷ��ŵķ���
	 * @param type ����ʽ,POST,GET,�Ƽ�POST
	 */
	public void sendSms(String type) {
		String url = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
		String regCode = "101100-WEB-HUAX-032304"; // ������ͨע���룬����������д���ӿͷ��ǵõ���ע����
		String regPasswod = "GFDJHKHB"; // ������ͨע�����Ӧ�����룬����������д���ӿͷ��ǵõ���ע����
		String sourceAdd = null;		//��ͨ���ţ��10λ����Ϊ��
		String phone = "13166029815";		//�ֻ����루���1000�����������Ӣ�Ķ���(,)����������Ϊ��
		/*
         *  ǩ��:���Ų��涨,ǩ����ʾ�û�����ʵ���,�벻Ҫ��ǩ����ð�ñ��˵����,��ͻ�ʹ�����������ǽ���Ŵ�����թƭΪ���ύ���Ų�������һ�����κ���ɿͻ��е�
         *  ������ͨ����ϵͳҪ��ǩ�����븽���ڶ������ݵ�β��,��ȫ�����������Ű���,������֮�������пո�,���򽫵��·���ʧ��
         *  ��Ȼ�ڳ�����,ǩ���Ǹ����ڶ������ݵ�β��,������ʵ�����ʹﵽ�û��ֻ�ʱ,ǩ������ܳ����ڶ��ŵ�ͷ��,���Ǹ�����Ӫ�̵����߲�ͬ,���������Լ���·�ɶ�ǩ����λ��������
         *  �������ݵĳ��ȼ�������ǩ��;ǩ�����ݵĳ������������߱仯,��������ѯ�ͷ�
         *  д�ڳ����������û��Զ���ǩ���ķ�ʽ,����һ�ַ�ʽ���ÿͷ���ǩ��,���ַ�ʽǩ������Ҫд�ڳ�����,��������ѯ�ͷ�
         */
		String signature = "�����ˡ�";      //ǩ��
		Random random = new Random();
		String content = "�Ϻ�����Ͷ�ʹ������޹�˾$-_.+!*',^(���� &@#%)��֤��:" +  (random.nextInt(999999)) + signature;		//��������,���ϸ��տͷ������ģ�����ɶ�������,�����ͽ�ʧ��(�������ģ�������ŵȷ�ASCII������ݣ��û����뱣֤��ΪUTF-8�����ʽ)		
		try {
			content = URLEncoder.encode(content,"UTF-8");		//content�к��пո񣬻��У����ĵȷ�ascii�ַ�ʱ����Ҫ����url���룬�����޷���ȷ���䵽������
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return;
		}
		String param = "reg=" + regCode + "&pwd=" + regPasswod + "&sourceadd=" + sourceAdd + "&phone=" + phone + "&content=" + content;
		
		String returnStr = null;
		if(type.equals("GET")){
			returnStr = this.requestGet(url, param);
		}else{
			returnStr = this.requestPost(url, param);
		}
		System.out.println(returnStr);
	}
}
