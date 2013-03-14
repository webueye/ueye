package net.ueye.commons.orm.interceptor;

import java.io.Serializable;

import net.ueye.commons.orm.entity.BaseEntity;

import org.hibernate.EmptyInterceptor;
import org.joda.time.DateTime;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@SuppressWarnings("serial")
public class EntityInterceptor extends EmptyInterceptor {

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		if (entity instanceof BaseEntity) {
			BaseEntity BaseEntity = (BaseEntity) entity;
			boolean modified = false;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("lastModifyDateTime".equals(propertyNames[i])) {
					DateTime lastModifyDateTime = new DateTime();
					currentState[i] = lastModifyDateTime;
					BaseEntity.setLastModifyDateTime(lastModifyDateTime);
					modified = true;
				}
			}
			return modified;
		}
		return false;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		if (entity instanceof BaseEntity) {
			BaseEntity baseEntity = (BaseEntity) entity;
			boolean modified = false;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("createDateTime".equals(propertyNames[i])) {
					DateTime createDateTime = new DateTime();
					state[i] = createDateTime;
					baseEntity.setCreateDateTime(createDateTime);
					modified = true;
				} else if ("lastModifyDateTime".equals(propertyNames[i])) {
					DateTime lastModifyDateTime = new DateTime();
					state[i] = lastModifyDateTime;
					baseEntity.setLastModifyDateTime(lastModifyDateTime);
					modified = true;
				}
			}
			return modified;
		}
		return false;
	}

}
