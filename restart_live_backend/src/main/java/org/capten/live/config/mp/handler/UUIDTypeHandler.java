package org.capten.live.config.mp.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@MappedJdbcTypes(JdbcType.OTHER)
@MappedTypes(String.class)
public class UUIDTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        // 将 String 转换为 UUID 对象，然后设置到 PreparedStatement 中
        ps.setObject(i, UUID.fromString(parameter));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从数据库获取 UUID 对象，然后转换为 String
        Object object = rs.getObject(columnName);
        return object != null ? object.toString() : null;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object object = rs.getObject(columnIndex);
        return object != null ? object.toString() : null;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object object = cs.getObject(columnIndex);
        return object != null ? object.toString() : null;
    }
}

