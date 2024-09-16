package com.ls.typehandler_demo.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/16 14:47
 */
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        // 将List<String>转换为逗号分隔的字符串
        String value = String.join(",", parameter);
        ps.setString(i, value);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 将数据库中的逗号分隔字符串转换为List<String>
        String value = rs.getString(columnName);
        return convertStringToList(value);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return convertStringToList(value);
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return convertStringToList(value);
    }

    private List<String> convertStringToList(String value) {
        // 处理空值的情况
        if (value == null || value.isEmpty()) {
            return null;
        }
        // 根据逗号分隔字符串并返回List<String>
        List<String> list = new ArrayList<>();
        for (String s : value.split(",")) {
            list.add(s);
        }
        return list;
//        // 根据逗号分隔字符串并返回List<String>
//        return Arrays.asList(value.split(","));
    }
}
