package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

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
     *
     * @return void
     * @throws Exception 异常
     */
    public Connection getCon() throws Exception {
        String jdbcName = "com.mysql.cj.jdbc.Driver";
        Class.forName(jdbcName);
        String dbUserName = "root";
        String dbUrl = "jdbc:mysql://localhost:3306/stu_manger?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String dbPassword = "Zcx3701812004/";
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
