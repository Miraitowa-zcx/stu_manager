package leyramu.view;

import leyramu.dao.UserDao;
import leyramu.model.User;
import leyramu.util.DbUtil;
import leyramu.util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 登录界面
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class logOnFrm extends JFrame {

    /**
     * 用户名文本框
     */
    private final JTextField userNameTxt;

    /**
     * 密码文本框
     */
    private final JPasswordField passwordTxt;

    /**
     * 数据库工具类
     */
    private final DbUtil dbUtil = new DbUtil();

    /**
     * 用户 Dao
     */
    private final UserDao userDao = new UserDao();

    /**
     * 启动应用程序
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                logOnFrm logOnFrm = new logOnFrm();
                logOnFrm.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建框架
     */
    public logOnFrm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(logOnFrm.class.getResource("/icon/logo.png")));
        Font font = new Font("Dialog", Font.PLAIN, 12);
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, font);
            }
        }
        JLabel passwordLabel = new JLabel("密  码：");
        passwordLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        passwordLabel.setIcon(new ImageIcon(Objects.requireNonNull(logOnFrm.class.getResource("/icon/loginPassword.png"))));
        setResizable(false);
        setTitle("管理员登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 537, 414);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel stuManageSystem = new JLabel("学生管理系统");
        stuManageSystem.setFont(new Font("宋体", Font.BOLD, 23));
        stuManageSystem.setIcon(new ImageIcon(Objects.requireNonNull(logOnFrm.class.getResource("/icon/stuManager.png"))));

        JLabel userNameLabel = new JLabel("用户名：");
        userNameLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        userNameLabel.setIcon(new ImageIcon(Objects.requireNonNull(logOnFrm.class.getResource("/icon/loginUsername.png"))));

        userNameTxt = new JTextField();
        userNameTxt.setColumns(10);

        passwordTxt = new JPasswordField();

        JButton loginBtn = new JButton("登录");
        loginBtn.addActionListener(this::loginActionPerformed);
        loginBtn.setIcon(new ImageIcon(Objects.requireNonNull(logOnFrm.class.getResource("/icon/login.png"))));

        JButton resetBtn = new JButton("重置");
        resetBtn.addActionListener(this::resetValueActionPerformed);
        resetBtn.setIcon(new ImageIcon(Objects.requireNonNull(logOnFrm.class.getResource("/icon/loginClean.png"))));
        resetBtn.setSelectedIcon(new ImageIcon(Objects.requireNonNull(logOnFrm.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png"))));
        GroupLayout loginLayout = new GroupLayout(contentPane);
        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(loginLayout.createSequentialGroup()
                                .addGroup(loginLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(loginLayout.createSequentialGroup()
                                                .addGap(80)
                                                .addGroup(loginLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(passwordLabel)
                                                        .addGroup(loginLayout.createSequentialGroup()
                                                                .addComponent(userNameLabel)
                                                                .addPreferredGap(ComponentPlacement.RELATED))))
                                        .addGroup(Alignment.TRAILING, loginLayout.createSequentialGroup()
                                                .addContainerGap(89, Short.MAX_VALUE)
                                                .addComponent(loginBtn)
                                                .addPreferredGap(ComponentPlacement.RELATED)))
                                .addGroup(loginLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(loginLayout.createSequentialGroup()
                                                .addGap(29)
                                                .addGroup(loginLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(userNameTxt)
                                                        .addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)))
                                        .addGroup(loginLayout.createSequentialGroup()
                                                .addGap(112)
                                                .addComponent(resetBtn)))
                                .addGap(110))
                        .addGroup(loginLayout.createSequentialGroup()
                                .addGap(144)
                                .addComponent(stuManageSystem)
                                .addContainerGap(165, Short.MAX_VALUE))
        );
        loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(loginLayout.createSequentialGroup()
                                .addGap(35)
                                .addComponent(stuManageSystem)
                                .addGap(48)
                                .addGroup(loginLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(userNameLabel)
                                        .addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                .addGap(28)
                                .addGroup(loginLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                                .addGap(36)
                                .addGroup(loginLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(loginBtn)
                                        .addComponent(resetBtn))
                                .addContainerGap(78, Short.MAX_VALUE))
        );
        contentPane.setLayout(loginLayout);

        this.setLocationRelativeTo(null);
    }

    /**
     * 登录事件处理
     *
     * @param evt 登录事件
     */
    private void loginActionPerformed(ActionEvent evt) {
        String userName = this.userNameTxt.getText();
        String password = new String(this.passwordTxt.getPassword());
        if (StringUtil.isEmpty(userName)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空！");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空！");
            return;
        }
        User user = new User(userName, password);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            User currentUser = userDao.login(con, user);
            if (currentUser != null) {
                dispose();
                new MainFrm().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 重置事件处理
     *
     * @param evt 事件
     */
    private void resetValueActionPerformed(ActionEvent evt) {
        this.userNameTxt.setText("");
        this.passwordTxt.setText("");
    }
}
