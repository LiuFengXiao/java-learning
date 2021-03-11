package Typehandler;

import POJO.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexEnumTypeHandler<T> implements TypeHandler<SexEnum> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,sexEnum.getId());
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, String s) throws SQLException {
         int id = (int) resultSet.getInt(s);
         return SexEnum.getSexEnumById(id);
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, int i) throws SQLException {

        return SexEnum.getSexEnumById(resultSet.getInt(i));
    }

    @Override
    public SexEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        return SexEnum.getSexEnumById(callableStatement.getInt(i));
    }
}
