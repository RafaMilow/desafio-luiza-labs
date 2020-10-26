package com.br.luizalabs.api.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtils {

	private DAOUtils() {
	}

	public static final Integer getIntValue(ResultSet r, String paramName) throws SQLException {
		Integer result = r.getInt(paramName);
		return r.wasNull() ? null : result;
	}

	public static final Integer getIntValue(ResultSet r, String paramName, Integer defaultValue) throws SQLException {
		Integer result = r.getInt(paramName);
		return r.wasNull() ? defaultValue : result;
	}

	public static final Short getShortValue(ResultSet r, String paramName) throws SQLException {
		Short result = r.getShort(paramName);
		return r.wasNull() ? null : result;
	}

	public static final Short getShortValue(ResultSet r, String paramName, Short defaultValue) throws SQLException {
		Short result = r.getShort(paramName);
		return r.wasNull() ? defaultValue : result;
	}

	public static final Long getLongValue(ResultSet r, String paramName) throws SQLException {
		Long result = r.getLong(paramName);
		return r.wasNull() ? null : result;
	}

	public static final Long getLongValue(ResultSet r, String paramName, Long defaultValue) throws SQLException {
		Long result = r.getLong(paramName);
		return r.wasNull() ? defaultValue : result;
	}

	public static final String getStringValue(ResultSet r, String paramName) throws SQLException {
		String result = r.getString(paramName);
		return r.wasNull() ? null : result;
	}

	public static final String getStringValueDefault(ResultSet r, String paramName, String defaultValue)
			throws SQLException {
		String result = r.getString(paramName);
		return r.wasNull() ? defaultValue : result;
	}

}
