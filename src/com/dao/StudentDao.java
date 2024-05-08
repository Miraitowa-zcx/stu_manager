package com.dao;

import com.model.Student;
import com.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 学生 Dao 类
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StudentDao {
    /**
     * 学生添加
     *
     * @param con     连接
     * @param student 学生
     * @return 数量
     * @throws Exception 异常
     */
    public int add(Connection con, Student student) throws Exception {
        String sql = "insert into t_student values(null,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getHome());
        preparedStatement.setString(3, student.getSex());
        preparedStatement.setFloat(4, student.getScore());
        preparedStatement.setInt(5, student.getStuClassId());
        preparedStatement.setString(6, student.getStuDesc());
        return preparedStatement.executeUpdate();
    }

    /**
     * 学生信息查询
     *
     * @param con 连接
     * @param stu 信息
     * @return 结果集
     * @throws Exception 异常
     */
    public ResultSet list(Connection con, Student stu) throws Exception {
        StringBuilder sb = new StringBuilder("select * from t_student b,t_stu_class bt where b.stu_class_id=bt.id");
        if (StringUtil.isNotEmpty(stu.getName())) {
            sb.append(" and b.name like '%").append(stu.getName()).append("%'");
        }
        if (StringUtil.isNotEmpty(stu.getHome())) {
            sb.append(" and b.home like '%").append(stu.getHome()).append("%'");
        }
        if (stu.getStuClassId() != null && stu.getStuClassId() != -1) {
            sb.append(" and b.stu_class_id =").append(stu.getStuClassId());
        }
        PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
        return preparedStatement.executeQuery();
    }

    /**
     * 学生信息删除
     *
     * @param con 连接
     * @param id  ID
     * @return 数量
     * @throws Exception 异常
     */
    public int delete(Connection con, String id) throws Exception {
        String sql = "delete from t_student where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, id);
        return preparedStatement.executeUpdate();
    }

    /**
     * 学生信息修改
     *
     * @param con 连接
     * @param stu 学生
     * @return 数量
     * @throws Exception 异常
     */
    public int update(Connection con, Student stu) throws Exception {
        String sql = "update t_student set name=?,home=?,sex=?,score=?,stuDesc=?,stu_class_id=? where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, stu.getName());
        preparedStatement.setString(2, stu.getHome());
        preparedStatement.setString(3, stu.getSex());
        preparedStatement.setFloat(4, stu.getScore());
        preparedStatement.setString(5, stu.getStuDesc());
        preparedStatement.setInt(6, stu.getStuClassId());
        preparedStatement.setInt(7, stu.getId());
        return preparedStatement.executeUpdate();
    }

    /**
     * 指定学生班级下是否存在学生
     *
     * @param con        连接
     * @param stuClassId 班级 ID
     * @return 是否存在
     * @throws Exception 异常
     */
    public boolean existStuByStuClassId(Connection con, String stuClassId) throws Exception {
        String sql = "select * from t_student where stu_class_id=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, stuClassId);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }
}
