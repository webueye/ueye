//package net.ueye.cms.sys.entity;
//
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import net.ueye.commons.orm.entity.BaseEntity;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.builder.ToStringBuilder;
//
//import com.google.common.collect.ImmutableList;
//
///**
// * 角色.
// * 
// * @author calvin
// */
//@SuppressWarnings("serial")
//@Entity
//@Table(name = "ss_role")
//public class Role extends BaseEntity {
//
//	private String name;
//
//	private String permissions;
//
//	public Role() {
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
//	public String getPermissions() {
//		return permissions;
//	}
//
//	public void setPermissions(String permissions) {
//		this.permissions = permissions;
//	}
//
//	@Transient
//	public List<String> getPermissionList() {
//		return ImmutableList.copyOf(StringUtils.split(permissions, ","));
//	}
//
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this);
//	}
//}
