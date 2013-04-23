package net.ueye.cms.sys.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.ueye.cms.Const;

import org.hibernate.annotations.Type;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Const.tablePrefix + "dept")
public class Dept extends NodeModel {

	private String name;
	private String deptNo;
	private Integer orderValue;
	private String telNo;
	private String faxNo;
	private Integer recordStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Dept parent;

	@Type(type = "net.ueye.commons.orm.entity.type.PersistentSet")
	private Set<Long> roles;

	public Dept() {

	}

	public Dept(Long id) {
		this.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dept getParent() {
		return parent;
	}

	public void setParent(Dept parent) {
		this.parent = parent;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public void setRoles(Set<Long> roles) {
		this.roles = roles;
	}

	public Set<Long> getRoles() {
		return roles;
	}

}
