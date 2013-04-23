package net.ueye.cms.sys.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.ueye.cms.Const;
import net.ueye.commons.orm.entity.BaseEntity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

/**
 * @author rubys@vip.qq.com
 * @since 2013-4-24
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Const.tablePrefix + "account")
public class Account extends BaseEntity {

	private String username;
	private String password;
	private String email;
	private String realname;
	private Boolean status;
	private Boolean admin;
	private Boolean sex;
	private String phone;
	private String mobile;
	private String memo;

	@Type(type = "net.ueye.commons.orm.entity.type.PersistentLocalDate")
	private LocalDate birthday;

	@Type(type = "net.ueye.commons.orm.entity.type.PersistentSet")
	private Set<Long> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRealname() {
		return realname;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<Long> getRoles() {
		return roles;
	}

	public void setRoles(Set<Long> roles) {
		this.roles = roles;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

}
