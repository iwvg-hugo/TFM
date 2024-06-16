package com.miw.tripplanner.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@MappedTypes(List.class)
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        String[] array = parameter.toArray(new String[0]);
        Array sqlArray = ps.getConnection().createArrayOf("varchar", array);
        ps.setArray(i, sqlArray);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Array sqlArray = rs.getArray(columnName);
        if (sqlArray == null) {
            return Collections.emptyList();
        }
        String[] array = (String[]) sqlArray.getArray();
        return Arrays.asList(array);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Array sqlArray = rs.getArray(columnIndex);
        if (sqlArray == null) {
            return Collections.emptyList();
        }
        String[] array = (String[]) sqlArray.getArray();
        return Arrays.asList(array);
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Array sqlArray = cs.getArray(columnIndex);
        if (sqlArray == null) {
            return Collections.emptyList();
        }
        String[] array = (String[]) sqlArray.getArray();
        return Arrays.asList(array);
    }
}