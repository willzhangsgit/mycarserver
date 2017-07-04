package com.mycar.bo;

public class St_city {
	public String id;
	public String desc_en;
	public String desc_cn;
	public String region;
	public String province;
	public String baidu_mapping;
	
	public St_city() {
		super();
		// TODO Auto-generated constructor stub
	}

	public St_city(String id, String desc_en, String desc_cn, String region,
			String province, String baidu_mapping) {
		super();
		this.id = id;
		this.desc_en = desc_en;
		this.desc_cn = desc_cn;
		this.region = region;
		this.province = province;
		this.baidu_mapping = baidu_mapping;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc_en() {
		return desc_en;
	}
	public void setDesc_en(String desc_en) {
		this.desc_en = desc_en;
	}
	public String getDesc_cn() {
		return desc_cn;
	}
	public void setDesc_cn(String desc_cn) {
		this.desc_cn = desc_cn;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getBaidu_mapping() {
		return baidu_mapping;
	}
	public void setBaidu_mapping(String baidu_mapping) {
		this.baidu_mapping = baidu_mapping;
	}
	
	@Override
	public String toString() {
		return "St_city [id=" + id + ", desc_en=" + desc_en + ", desc_cn="
				+ desc_cn + ", region=" + region + ", province=" + province
				+ ", baidu_mapping=" + baidu_mapping + "]";
	}

}
