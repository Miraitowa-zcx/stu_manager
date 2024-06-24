package leyramu.view;

import leyramu.dao.StuClassDao;
import leyramu.dao.StudentDao;
import leyramu.model.StuClass;
import leyramu.model.Student;
import leyramu.util.DbUtil;
import leyramu.util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Objects;

/**
 * 添加学生界面
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StudentAddFrm extends JInternalFrame {

    /**
     * 学生添加界面
     */
    private final JTextField stuNameTxt;

    /**
     * 家庭地址文本框
     */
    private final JTextField homeAddressTxt;

    /**
     * 学生年级文本框
     */
    private final JTextField stuGradeTxt;

    /**
     * 学生班级下拉框
     */
    private final JComboBox<StuClass> stuClassComboBox;

    /**
     * 学生描述文本域
     */
    private final JTextArea stuDescTxt;

    /**
     * 学生性别单选按钮 - 男
     */
    private final JRadioButton manJrb;

    /**
     * 学生性别单选按钮 - 女
     */
    private final JRadioButton femaleJrb;

    /**
     * 数据库工具类对象
     */
    private final DbUtil dbUtil = new DbUtil();

    /**
     * 学生班级数据访问对象
     */
    private final StuClassDao stuClassDao = new StuClassDao();

    /**
     * 学生数据访问对象
     */
    private final StudentDao studentDao = new StudentDao();

    /**
     * 启动应用程序
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                StudentAddFrm studentAddFrm = new StudentAddFrm();
                studentAddFrm.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建框架
     */
    public StudentAddFrm() {
        setFrameIcon(new ImageIcon(Objects.requireNonNull(StudentAddFrm.class.getResource("/icon/addGroup.png"))));
        getContentPane().setFont(new Font("宋体", Font.BOLD, 15));
        setIconifiable(true);
        setClosable(true);
        setTitle("学生添加");
        setBounds(100, 100, 644, 584);

        JLabel stuNameLbl = new JLabel("学生姓名：");
        stuNameLbl.setFont(new Font("宋体", Font.BOLD, 15));
        stuNameTxt = new JTextField();
        stuNameTxt.setColumns(10);

        JLabel homeAddressLbl = new JLabel("家庭住址：");
        homeAddressLbl.setFont(new Font("宋体", Font.BOLD, 15));
        homeAddressTxt = new JTextField();
        homeAddressTxt.setColumns(10);

        JLabel stuSexLbl = new JLabel("学生性别：");
        stuSexLbl.setFont(new Font("宋体", Font.BOLD, 15));
        manJrb = new JRadioButton("男");
        ButtonGroup stuSexButtonGroup = new ButtonGroup();
        stuSexButtonGroup.add(manJrb);
        manJrb.setSelected(true);
        femaleJrb = new JRadioButton("女");
        stuSexButtonGroup.add(femaleJrb);

        JLabel stuGradeLbl = new JLabel("学生成绩：");
        stuGradeLbl.setFont(new Font("宋体", Font.BOLD, 15));
        stuGradeTxt = new JTextField();
        stuGradeTxt.setColumns(10);

        JLabel stuDescLbl = new JLabel("学生描述：");
        stuDescLbl.setFont(new Font("宋体", Font.BOLD, 15));
        stuDescTxt = new JTextArea();

        JLabel stuClassLbl = new JLabel("学生班级：");
        stuClassLbl.setFont(new Font("宋体", Font.BOLD, 15));
        stuClassComboBox = new JComboBox<>();

        JButton addStudentBtn = new JButton("添加");
        addStudentBtn.addActionListener(this::studentAddActionPerformed);
        addStudentBtn.setIcon(new ImageIcon(Objects.requireNonNull(StudentAddFrm.class.getResource("/icon/add.png"))));

        JButton resetStudentBtn = new JButton("重置");
        resetStudentBtn.addActionListener(this::resetValueActionPerformed);
        resetStudentBtn.setIcon(new ImageIcon(Objects.requireNonNull(StudentAddFrm.class.getResource("/icon/reset.png"))));
        GroupLayout studentAddFrmLayout = new GroupLayout(getContentPane());
        studentAddFrmLayout.setHorizontalGroup(
                studentAddFrmLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(studentAddFrmLayout.createSequentialGroup()
                                .addGap(66)
                                .addGroup(studentAddFrmLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(studentAddFrmLayout.createSequentialGroup()
                                                .addComponent(addStudentBtn)
                                                .addGap(41)
                                                .addComponent(resetStudentBtn))
                                        .addGroup(studentAddFrmLayout.createSequentialGroup()
                                                .addComponent(stuDescLbl)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(stuDescTxt, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(studentAddFrmLayout.createSequentialGroup()
                                                .addGroup(studentAddFrmLayout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addGroup(Alignment.LEADING, studentAddFrmLayout.createSequentialGroup()
                                                                .addComponent(stuClassLbl)
                                                                .addGap(18)
                                                                .addComponent(stuClassComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(Alignment.LEADING, studentAddFrmLayout.createSequentialGroup()
                                                                .addComponent(stuNameLbl)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(stuNameTxt, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(Alignment.LEADING, studentAddFrmLayout.createSequentialGroup()
                                                                .addComponent(stuSexLbl)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(manJrb)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(femaleJrb)))
                                                .addGap(48)
                                                .addGroup(studentAddFrmLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addGroup(studentAddFrmLayout.createSequentialGroup()
                                                                .addComponent(homeAddressLbl)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(homeAddressTxt))
                                                        .addGroup(studentAddFrmLayout.createSequentialGroup()
                                                                .addComponent(stuGradeLbl)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(stuGradeTxt, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(96, Short.MAX_VALUE))
        );
        studentAddFrmLayout.setVerticalGroup(
                studentAddFrmLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(studentAddFrmLayout.createSequentialGroup()
                                .addGap(57)
                                .addGroup(studentAddFrmLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(homeAddressTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stuNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(homeAddressLbl)
                                        .addComponent(stuNameLbl))
                                .addGap(42)
                                .addGroup(studentAddFrmLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stuSexLbl)
                                        .addComponent(manJrb)
                                        .addComponent(femaleJrb)
                                        .addComponent(stuGradeLbl)
                                        .addComponent(stuGradeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(39)
                                .addGroup(studentAddFrmLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stuClassLbl)
                                        .addComponent(stuClassComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(42)
                                .addGroup(studentAddFrmLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stuDescLbl)
                                        .addComponent(stuDescTxt, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addGroup(studentAddFrmLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(addStudentBtn)
                                        .addComponent(resetStudentBtn))
                                .addGap(34))
        );
        getContentPane().setLayout(studentAddFrmLayout);
        stuDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185), 1, false));
        fillStudentClassComboBox();
    }

    /**
     * 重置事件处理
     *
     * @param e 事件
     */
    private void resetValueActionPerformed(ActionEvent e) {
        this.resetValue();
    }

    /**
     * 学生添加事件处理
     *
     * @param evt 事件
     */
    private void studentAddActionPerformed(ActionEvent evt) {

        String name = this.stuNameTxt.getText();
        String home = this.homeAddressTxt.getText();
        String score = this.stuGradeTxt.getText();
        String stuDesc = this.stuDescTxt.getText();

        if (StringUtil.isEmpty(name)) {
            JOptionPane.showMessageDialog(null, "学生姓名不能为空！");
            return;
        }
        if (StringUtil.isEmpty(home)) {
            JOptionPane.showMessageDialog(null, "家庭住址不能为空！");
            return;
        }
        if (StringUtil.isEmpty(score)) {
            JOptionPane.showMessageDialog(null, "学生成绩不能为空！");
            return;
        }

        String sex = "";
        if (manJrb.isSelected()) {
            sex = "男";
        } else if (femaleJrb.isSelected()) {
            sex = "女";
        }

        StuClass stuClass = (StuClass) stuClassComboBox.getSelectedItem();
        int stuClassId = 0;
        if (stuClass != null) {
            stuClassId = stuClass.getId();
        }

        Student student = new Student(name, home, sex, Float.parseFloat(score), stuClassId, stuDesc);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int addNum = studentDao.add(con, student);
            if (addNum == 1) {
                JOptionPane.showMessageDialog(null, "学生添加成功！");
                resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "学生添加失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "学生添加失败！");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重置表单
     */
    private void resetValue() {
        this.stuNameTxt.setText("");
        this.homeAddressTxt.setText("");
        this.stuGradeTxt.setText("");
        this.manJrb.setSelected(true);
        this.stuDescTxt.setText("");
        if (this.stuClassComboBox.getItemCount() > 0) {
            this.stuClassComboBox.setSelectedIndex(0);
        }
    }

    /**
     * 初始化学生班级下拉框
     */
    private void fillStudentClassComboBox() {
        Connection con;
        StuClass stuClass;
        try {
            con = dbUtil.getCon();
            ResultSet rs = stuClassDao.list(con, new StuClass());
            while (rs.next()) {
                stuClass = new StuClass();
                stuClass.setId(rs.getInt("id"));
                stuClass.setStuClassName(rs.getString("stuClassName"));
                this.stuClassComboBox.addItem(stuClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
