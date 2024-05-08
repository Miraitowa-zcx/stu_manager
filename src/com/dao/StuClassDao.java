package com.dao;

import com.model.StuClass;
import com.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 学生班级 Dao 类
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StuClassDao {
    /**
     * 学生班级添加
     *
     * @param con      连接
     * @param stuClass 类型
     * @return 学生
     * @throws Exception 异常
     */
    public int add(Connection con, StuClass stuClass) throws Exception {
        String sql = "insert into t_stuclass values(null,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, stuClass.getStuClassName());
        preparedStatement.setString(2, stuClass.getStuClassDesc());
        return preparedStatement.executeUpdate();
    }

    /**
     * 学生班级查询
     *
     * @param con      连接
     * @param stuClass 类型
     * @return 类型
     * @throws Exception 异常
     */
    public ResultSet list(Connection con, StuClass stuClass) throws Exception {
        StringBuilder sb = new StringBuilder("select * from t_stu_class");
        if (StringUtil.isNotEmpty(stuClass.getStuClassName())) {
            sb.append(" and stuClassName like '%").append(stuClass.getStuClassName()).append("%'");
        }
        PreparedStatement preparedStatement = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return preparedStatement.executeQuery();
    }

    /**
     * 删除学生班级
     *
     * @param con 连接
     * @param id  编号
     * @return 类型
     * @throws Exception 异常
     */
    public int delete(Connection con, String id) throws Exception {
        String sql = "delete from t_stuclass where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, id);
        return preparedStatement.executeUpdate();
    }

    /**
     * 更新学生班级
     *
     * @param con      连接
     * @param stuClass 类型
     * @return 类型
     * @throws Exception 异常
     */
    public int update(Connection con, StuClass stuClass) throws Exception {
        String sql = "update t_stuclass set stuClassName=?,StuClassDesc=? where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, stuClass.getStuClassName());
        preparedStatement.setString(2, stuClass.getStuClassDesc());
        preparedStatement.setInt(3, stuClass.getId());
        return preparedStatement.executeUpdate();
    }
}
