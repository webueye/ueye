package net.ueye.commons.orm.entity.type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Set;

import net.ueye.commons.util.StringUtils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;

import com.google.common.collect.Sets;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-19
 */
@SuppressWarnings("serial")
public class PersistentSet implements UserType, Serializable {

	public static final PersistentDateTime INSTANCE = new PersistentDateTime();
	private static final int[] SQL_TYPES = new int[] { Types.VARCHAR };

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class<?> returnedClass() {
		return Set.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		return x.equals(y);
	}

	@Override
	public int hashCode(Object value) throws HibernateException {
		if (value == null) {
			return 31;
		} else {
			return value.hashCode();
		}
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object object) throws HibernateException,
			SQLException {
		return nullSafeGet(resultSet, strings[0], sessionImplementor);
	}

	public Object nullSafeGet(ResultSet resultSet, String string, SessionImplementor sessionImplementor) throws SQLException {
		Object value = StandardBasicTypes.STRING.nullSafeGet(resultSet, string, sessionImplementor);
		if (value == null) {
			return null;
		}
		return StringUtils.stringToSet((String) value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SessionImplementor sessionImplementor)
			throws HibernateException, SQLException {
		if (value == null) {
			StandardBasicTypes.STRING.nullSafeSet(preparedStatement, null, index, sessionImplementor);
		} else {
			StandardBasicTypes.STRING.nullSafeSet(preparedStatement, StringUtils.collectionToString((Collection<Object>) value), index,
					sessionImplementor);
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return Sets.newHashSet(value);
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object value) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
