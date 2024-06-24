package leyramu.view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

/**
 * 创建主界面
 *
 * @author <a href=mailto:2038322151@qq.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2004/04/28
 */
public class MainFrm extends JFrame {

    /**
     * 桌面面板
     */
    private JDesktopPane table = null;

    /**
     * 启动应用程序
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainFrm mainFrm = new MainFrm();
                mainFrm.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建框架
     */
    public MainFrm() {

        initUi();
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrm.class.getResource("/icon/logo.png")));
        setFont(new Font("宋体", Font.BOLD, 25));

        JInternalFrame internalFrame = new JInternalFrame("学生管理系统主界面");
        internalFrame.setVisible(true);
        setTitle("学生管理系统主界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1920, 1200);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        /*  基本数据维护模块  */
        JMenu basicDataMenu = new JMenu("基本数据维护");
        basicDataMenu.setHorizontalAlignment(SwingConstants.CENTER);
        basicDataMenu.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/maintenance.png"))));
        menuBar.add(basicDataMenu);

        JMenu stuClassMenu = new JMenu("学生班级管理");
        stuClassMenu.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/edit.png"))));
        basicDataMenu.add(stuClassMenu);

        JMenuItem stuClassAddItem = new JMenuItem("学生班级添加");
        stuClassAddItem.addActionListener(e -> {
            StuClassAddFrm bookTypeAddInterFrm = new StuClassAddFrm();
            bookTypeAddInterFrm.setVisible(true);
            table.add(bookTypeAddInterFrm);
        });
        stuClassAddItem.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/addGroup.png"))));
        stuClassMenu.add(stuClassAddItem);

        JMenuItem stuClassUpdateItem = new JMenuItem("学生班级维护");
        stuClassUpdateItem.addActionListener(e -> {
            StuClassManageFrm bookTypeManageInterFrm = new StuClassManageFrm();
            bookTypeManageInterFrm.setVisible(true);
            table.add(bookTypeManageInterFrm);
        });
        stuClassUpdateItem.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/update.png"))));
        stuClassMenu.add(stuClassUpdateItem);

        JMenu stuMangerMenu = new JMenu("学生管理");
        stuMangerMenu.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/edit.png"))));
        basicDataMenu.add(stuMangerMenu);

        JMenuItem stuAddItem = new JMenuItem("学生添加");
        stuAddItem.addActionListener(e -> {
            StudentAddFrm bookAddInterFrm = new StudentAddFrm();
            bookAddInterFrm.setVisible(true);
            table.add(bookAddInterFrm);
        });
        stuAddItem.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/add.png"))));
        stuMangerMenu.add(stuAddItem);

        JMenuItem stuUpdateItem = new JMenuItem("学生维护");
        stuUpdateItem.addActionListener(e -> {
            StudentManageFrm bookManageInterFrm = new StudentManageFrm();
            bookManageInterFrm.setVisible(true);
            table.add(bookManageInterFrm);
        });
        stuUpdateItem.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/update.png"))));
        stuMangerMenu.add(stuUpdateItem);

        JMenuItem exitItem = new JMenuItem("安全退出");
        exitItem.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "是否退出系统");
            if (result == 0) {
                dispose();
            }
        });
        exitItem.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/loginClean.png"))));
        basicDataMenu.add(exitItem);

        /*  成绩数据维护模块  */
        JMenu gradeDataMenu = new JMenu("成绩数据维护");
        gradeDataMenu.setHorizontalAlignment(SwingConstants.CENTER);
        gradeDataMenu.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/maintenance.png"))));
        menuBar.add(gradeDataMenu);

        JMenu gradeMangerMenu = new JMenu("学生成绩管理");
        gradeMangerMenu.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/edit.png"))));
        gradeDataMenu.add(gradeMangerMenu);

        JMenuItem gradeAddItem = new JMenuItem("学生成绩添加");
        gradeAddItem.addActionListener(e -> {
            StuGradeAddFrm stuGradeAddFrm = new StuGradeAddFrm();
            stuGradeAddFrm.setVisible(true);
            table.add(stuGradeAddFrm);
        });
        gradeAddItem.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/add.png"))));
        gradeMangerMenu.add(gradeAddItem);

        JMenuItem gradeUpdateItem = new JMenuItem("学生成绩维护");
        gradeUpdateItem.addActionListener(e -> {
            StuGradeManageFrm bookTypeManageInterFrm = new StuGradeManageFrm();
            bookTypeManageInterFrm.setVisible(true);
            table.add(bookTypeManageInterFrm);
        });
        gradeUpdateItem.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/update.png"))));
        gradeMangerMenu.add(gradeUpdateItem);

        /*  关于我们模块  */
        JMenu aboutAuthorMenu = new JMenu("关于我们");
        aboutAuthorMenu.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/about.png"))));
        menuBar.add(aboutAuthorMenu);

        JMenuItem aboutAuthorItem = new JMenuItem("关于作者");
        aboutAuthorItem.addActionListener(e -> {
            AboutAuthorFrm aboutAuthorFrm = new AboutAuthorFrm();
            aboutAuthorFrm.setVisible(true);
            table.add(aboutAuthorFrm);

        });
        aboutAuthorItem.setIcon(new ImageIcon(Objects.requireNonNull(MainFrm.class.getResource("/icon/author.png"))));
        aboutAuthorMenu.add(aboutAuthorItem);
    }

    /**
     * 初始化界面
     */
    private void initUi() {
        JPanel contentPane = new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                URL imageUrl = getClass().getResource("/photo/bg.jpg");
                if (imageUrl != null) {
                    Image img = Toolkit.getDefaultToolkit().getImage(imageUrl);
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        table = new JDesktopPane();
        table.setOpaque(false);

        GroupLayout glContentPane = new GroupLayout(contentPane);
        contentPane.setLayout(glContentPane);
        glContentPane.setHorizontalGroup(
                glContentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(glContentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(table, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        glContentPane.setVerticalGroup(
                glContentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(glContentPane.createSequentialGroup()
                                .addComponent(table, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JLabel lblL = new JLabel("l");
        URL iconUrl = getClass().getResource("/photo/right.png");
        if (iconUrl != null) {
            lblL.setIcon(new ImageIcon(iconUrl));
        }

        JLabel label = new JLabel("");
        URL labelIconUrl = getClass().getResource("/photo/left.png");
        if (labelIconUrl != null) {
            label.setIcon(new ImageIcon(labelIconUrl));
        }

        GroupLayout glTable = new GroupLayout(table);
        table.setLayout(glTable);
        glTable.setHorizontalGroup(
                glTable.createParallelGroup(Alignment.LEADING)
                        .addGroup(glTable.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.UNRELATED, 301, Short.MAX_VALUE)
                                .addComponent(lblL, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE)
                                .addGap(32))
        );
        glTable.setVerticalGroup(
                glTable.createParallelGroup(Alignment.LEADING)
                        .addGroup(glTable.createSequentialGroup()
                                .addContainerGap(404, Short.MAX_VALUE)
                                .addGroup(glTable.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblL, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE))
                                .addGap(67))
        );
        setContentPane(contentPane);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setLocationRelativeTo(null);
    }
}
