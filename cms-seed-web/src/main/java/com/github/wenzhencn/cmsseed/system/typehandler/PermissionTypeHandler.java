package com.github.wenzhencn.cmsseed.system.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.github.wenzhencn.cmsseed.system.security.Permission;

/**
 * 
 * @author zhen.wen
 *
 */
public class PermissionTypeHandler extends BaseTypeHandler<Permission> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Permission parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setByte(i, parameter.getMask());
	}

	@Override
	public Permission getNullableResult(ResultSet rs, String columnName) throws SQLException {
		byte value = rs.getByte(columnName);
		return rs.wasNull() ? null : Permission.fromMask(value);
	}

	@Override
	public Permission getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		byte value = rs.getByte(columnIndex);
		return rs.wasNull() ? null : Permission.fromMask(value);
	}

	@Override
	public Permission getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		byte value = cs.getByte(columnIndex);
		return cs.wasNull() ? null : Permission.fromMask(value);
	}

}
