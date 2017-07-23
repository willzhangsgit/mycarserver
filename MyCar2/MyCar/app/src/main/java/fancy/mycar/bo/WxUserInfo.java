package fancy.mycar.bo;

import java.io.Serializable;

/**
 * Created by Will on 2017/7/1.
 */

public class WxUserInfo implements Serializable {
	public String openid;
	public String nickname;
	public String sex;
	public String city;
	public String country;
	public String headimgurl;
	public String unionid;
	public String access_token;
	public String refresh_token;
	public String expires_in;
	public String scope;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Override
	public String toString() {
		return "WxUserInfo{" +
				"access_token='" + access_token + '\'' +
				", openid='" + openid + '\'' +
				", nickname='" + nickname + '\'' +
				", sex='" + sex + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", headimgurl='" + headimgurl + '\'' +
				", unionid='" + unionid + '\'' +
				", refresh_token='" + refresh_token + '\'' +
				", expires_in='" + expires_in + '\'' +
				", scope='" + scope + '\'' +
				'}';
	}
}
