package leyramu.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库工具类
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class DbUtil {

    /**
     * 测试数据库连接
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        DbUtil dbUtil = new DbUtil();

        try {
            dbUtil.getCon();
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            e.notify();
            System.out.println("数据库连接失败");
        }
    }

    /**
     * 获取数据库连接
     */
    public Connection getCon() throws IOException, SQLException, ClassNotFoundException {

        String configFilePath = "./config/db.properties";

        // 加载配置文件
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
        }

        // 从配置文件中读取属性
        String jdbcName = props.getProperty("db.driver");
        String dbUrl = props.getProperty("db.url");
        String dbUserName = props.getProperty("db.username");
        String dbPassword = props.getProperty("db.password");

        // 注册驱动并获取数据库连接
        Class.forName(jdbcName);
        return DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
    }

    /**
     * 关闭数据库连接
     *
     * @param con 连接
     * @throws Exception 异常
     */
    public void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }
}
