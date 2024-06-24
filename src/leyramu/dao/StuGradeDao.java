package leyramu.dao;

import leyramu.model.StuGrade;
import leyramu.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 学生Dao类
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StuGradeDao {

    /**
     * 学生添加
     *
     * @param con      连接
     * @param stuGrade 学生成绩
     * @return 数量
     * @throws Exception 异常
     */
    public int add(Connection con, StuGrade stuGrade) throws Exception {
        String sql = "insert into t_stu_grade values(null,?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, stuGrade.getStuClassId());
        preparedStatement.setString(2, stuGrade.getName());
        preparedStatement.setFloat(3, stuGrade.getChineseScore());
        preparedStatement.setFloat(4, stuGrade.getMathScore());
        preparedStatement.setFloat(5, stuGrade.getEnglishScore());
        return preparedStatement.executeUpdate();
    }

    /**
     * 学生信息查询
     *
     * @param con      连接
     * @param stuGrade 信息
     * @return 结果集
     * @throws Exception 异常
     */
    public ResultSet list(Connection con, StuGrade stuGrade) throws Exception {
        StringBuilder sb = new StringBuilder("select * from t_stu_grade b,t_stu_class bt where b.stu_class_id=bt.id");
        if (StringUtil.isNotEmpty(stuGrade.getName())) {
            sb.append(" and b.name like '%").append(stuGrade.getName()).append("%'");
        }
        if (stuGrade.getStuClassId() != null && stuGrade.getStuClassId() != -1) {
            sb.append(" and b.stu_class_id =").append(stuGrade.getStuClassId());
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
        String sql = "delete from t_stu_grade where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, id);
        return preparedStatement.executeUpdate();
    }

    /**
     * 学生信息修改
     *
     * @param con 连接
     * @param stuGrade 学生成绩
     * @return 数量
     * @throws Exception 异常
     */
    public int update(Connection con, StuGrade stuGrade) throws Exception {
        String sql = "update t_stu_grade set stu_class_id=?,name=?,chinese_score=?,math_score=?,english_score=? where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, stuGrade.getStuClassId());
        preparedStatement.setString(2, stuGrade.getStuClassName());
        preparedStatement.setString(3, stuGrade.getName());
        preparedStatement.setFloat(4, stuGrade.getChineseScore());
        preparedStatement.setFloat(5, stuGrade.getMathScore());
        preparedStatement.setFloat(6, stuGrade.getEnglishScore());
        return preparedStatement.executeUpdate();
    }
}
