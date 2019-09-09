package com.github.wenzhencn.cmsseed.system.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.github.wenzhencn.cmsseed.system.security.EntryLevel;

/**
 * 
 * @author zhen.wen
 *
 */
public class EntryLevelTypeHandler extends BaseTypeHandler<EntryLevel> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, EntryLevel parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setByte(i, parameter.getLevel());
	}

	@Override
	public EntryLevel getNullableResult(ResultSet rs, String columnName) throws SQLException {
		byte value = rs.getByte(columnName);
		return rs.wasNull() ? null : EntryLevel.fromValue(value);
	}

	@Override
	public EntryLevel getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		byte value = rs.getByte(columnIndex);
		return rs.wasNull() ? null : EntryLevel.fromValue(value);
	}

	@Override
	public EntryLevel getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		byte value = cs.getByte(columnIndex);
		return cs.wasNull() ? null : EntryLevel.fromValue(value);
	}

}
