package leyramu.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
@Setter
@Getter
public class User {

    /**
     * 用户 ID
     */
    private int id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 构造方法
     */
    public User() {
    }

    /**
     * 构造方法
     *
     * @param userName 用户名
     * @param password 密码
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
