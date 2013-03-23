//package net.ueye.cms.sys.entity;
//
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.OrderBy;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import net.ueye.commons.orm.entity.BaseEntity;
//
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.codehaus.jackson.annotate.JsonIgnore;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//import com.google.common.collect.Lists;
//
///**
// * 用户.
// * 
// * @author calvin
// */
//@SuppressWarnings("serial")
//@Entity
//@Table(name = "ss_user")
//public class User extends BaseEntity {
//
//	private String loginName;
//	private String plainPassword;
//	private String password;
//	private String salt;
//	private String name;
//	private String email;
//	private String status;
//
//	private Team team;
//
//	@ManyToMany
//	@JoinTable(name = "ss_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
//	// Fecth策略定义
//	@Fetch(FetchMode.SUBSELECT)
//	private List<Role> roleList = Lists.newArrayList(); // 有序的关联对象集合
//
//	public String getLoginName() {
//		return loginName;
//	}
//
//	public void setLoginName(String loginName) {
//		this.loginName = loginName;
//	}
//
//	@Transient
//	public String getPlainPassword() {
//		return plainPassword;
//	}
//
//	public void setPlainPassword(String plainPassword) {
//		this.plainPassword = plainPassword;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getSalt() {
//		return salt;
//	}
//
//	public void setSalt(String salt) {
//		this.salt = salt;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	// 多对多定义
//	@ManyToMany
//	@JoinTable(name = "ss_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
//	// Fecth策略定义
//	@Fetch(FetchMode.SUBSELECT)
//	// 集合按id排序
//	@OrderBy("id ASC")
//	// 缓存策略
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	public List<Role> getRoleList() {
//		return roleList;
//	}
//
//	public void setRoleList(List<Role> roleList) {
//		this.roleList = roleList;
//	}
//
//	@ManyToOne
//	@JoinColumn(name = "team_id")
//	public Team getTeam() {
//		return team;
//	}
//
//	public void setTeam(Team team) {
//		this.team = team;
//	}
//
//	@Transient
//	@JsonIgnore
//	public String getRoleNames() {
//		return Collections3.extractToString(roleList, "name", ", ");
//	}
//
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this);
//	}
//}