package leyramu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import leyramu.model.User;

/**
 * 用户 Dao 类
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class UserDao {

    /**
     * 登录验证
     *
     * @param connection 数据库连接
     * @param user       用户
     * @return 用户对象
     * @throws Exception 异常
     */
    public User login(Connection connection, User user) throws Exception {
        User resultUser = null;
        String sql = "select * from t_user where userName=? and password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            resultUser = new User();
            resultUser.setId(rs.getInt("id"));
            resultUser.setUserName(rs.getString("userName"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}
