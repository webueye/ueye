package net.ueye.commons.orm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	@Column(updatable = false)
	@Type(type = "net.ueye.commons.orm.entity.type.PersistentDateTime")
	private DateTime createDateTime;
	@Type(type = "net.ueye.commons.orm.entity.type.PersistentDateTime")
	private DateTime lastModifyDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(DateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public DateTime getLastModifyDateTime() {
		return lastModifyDateTime;
	}

	public void setLastModifyDateTime(DateTime lastModifyDateTime) {
		this.lastModifyDateTime = lastModifyDateTime;
	}

}
