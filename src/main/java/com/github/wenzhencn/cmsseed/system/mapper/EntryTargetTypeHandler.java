package com.github.wenzhencn.cmsseed.system.mapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.github.wenzhencn.cmsseed.system.security.EntryTargetType;

/**
 * 
 * @author zhen.wen
 *
 */
public class EntryTargetTypeHandler extends BaseTypeHandler<EntryTargetType> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, EntryTargetType parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setByte(i, parameter.getValue());
	}

	@Override
	public EntryTargetType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		byte value = rs.getByte(columnName);
		return rs.wasNull() ? null : EntryTargetType.fromValue(value);
	}

	@Override
	public EntryTargetType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		byte value = rs.getByte(columnIndex);
		return rs.wasNull() ? null : EntryTargetType.fromValue(value);
	}

	@Override
	public EntryTargetType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		byte value = cs.getByte(columnIndex);
		return cs.wasNull() ? null : EntryTargetType.fromValue(value);
	}

}
