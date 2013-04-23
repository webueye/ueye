package net.ueye.cms.sys.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.ueye.cms.Const;
import net.ueye.commons.orm.entity.BaseEntity;

import org.hibernate.annotations.Type;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-19
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Const.tablePrefix + "role")
public class Role extends BaseEntity {

	private String roleName;
	private String description;
	@Type(type = "net.ueye.commons.orm.entity.type.PersistentSet")
	private Set<Long> menus;
	@Transient
	private String checked;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMenus(Set<Long> menus) {
		this.menus = menus;
	}

	public Set<Long> getMenus() {
		return menus;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getChecked() {
		return checked;
	}
}
