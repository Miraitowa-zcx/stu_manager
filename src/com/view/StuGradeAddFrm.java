package com.view;

import com.dao.StuClassDao;
import com.dao.StuGradeDao;
import com.dao.StudentDao;
import com.model.StuClass;
import com.model.StuGrade;
import com.model.Student;
import com.util.DbUtil;
import com.util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 添加学生成绩
 *
 * @author <a href=mailto:203832251@qq.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/29
 */
public class StuGradeAddFrm extends JInternalFrame {

    private final JComboBox<Student> stuNameJcb;
    private final JTextField chineseScoreText;
    private final JTextField mathScoreText;
    private final JTextField englishScoreText;
    private final JComboBox<StuClass> StuClassJcb;

    private final DbUtil dbUtil = new DbUtil();
    private final StudentDao studentDao = new StudentDao();
    private final StuClassDao bookTypeDao = new StuClassDao();
    private final StuGradeDao stuGradeDao = new StuGradeDao();

    /**
     * 启动应用程序
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StuGradeAddFrm stuGradeAddFrm = new StuGradeAddFrm();
                stuGradeAddFrm.setVisible(true);
            } catch (Exception e) {
                e.notify();
            }
        });
    }

    /**
     * 创建框架
     */
    public StuGradeAddFrm() {
        setFrameIcon(new ImageIcon(Objects.requireNonNull(StuGradeAddFrm.class.getResource("/icon/addGroup.png"))));
        getContentPane().setFont(new Font("宋体", Font.BOLD, 22));
        setIconifiable(true);
        setClosable(true);
        setTitle("学生添加");
        setBounds(100, 100, 600, 500);

        JLabel stuName = new JLabel("学生姓名：");
        stuName.setFont(new Font("宋体", Font.BOLD, 22));

        stuNameJcb = new JComboBox<>();

        JLabel stuClass = new JLabel("学生班级：");
        stuClass.setFont(new Font("宋体", Font.BOLD, 22));

        StuClassJcb = new JComboBox<>();

        JLabel chineseScore = new JLabel("语文成绩：");
        chineseScore.setFont(new Font("宋体", Font.BOLD, 22));
        chineseScoreText = new JTextField();
        chineseScoreText.setColumns(10);

        JLabel mathScore = new JLabel("数学成绩：");
        mathScore.setFont(new Font("宋体", Font.BOLD, 22));
        mathScoreText = new JTextField();
        mathScoreText.setColumns(10);

        JLabel englishScore = new JLabel("英语成绩：");
        englishScore.setFont(new Font("宋体", Font.BOLD, 22));
        englishScoreText = new JTextField();
        englishScoreText.setColumns(10);

        JButton addButton = new JButton("添加");
        addButton.addActionListener(this::bookAddActionPerformed);
        addButton.setIcon(new ImageIcon(Objects.requireNonNull(StuGradeAddFrm.class.getResource("/icon/add.png"))));

        JButton resetButton = new JButton("重置");
        resetButton.addActionListener(this::resetValueActionPerformed);
        resetButton.setIcon(new ImageIcon(Objects.requireNonNull(StuGradeAddFrm.class.getResource("/icon/reset.png"))));

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(stuName)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(stuClass)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(stuNameJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(StuClassJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(30, 30, 30)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(chineseScore)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(chineseScoreText))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(mathScore)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(mathScoreText))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(englishScore)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(englishScoreText)))
                                                .addGap(0, 96, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(116, 116, 116)
                                                .addComponent(addButton)
                                                .addGap(41)
                                                .addComponent(resetButton)
                                                .addContainerGap())))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(57)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stuName)
                                        .addComponent(stuNameJcb)
                                        .addComponent(chineseScore)
                                        .addComponent(chineseScoreText))
                                .addGap(30, 30, 30)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stuClass)
                                        .addComponent(StuClassJcb)
                                        .addComponent(mathScore)
                                        .addComponent(mathScoreText))
                                .addGap(30, 30, 30)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(englishScore)
                                        .addComponent(englishScoreText))
                                .addGap(42)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(addButton)
                                        .addComponent(resetButton))
                                .addGap(34))
        );
        fillStuName();
        fillStuClass();
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
    private void bookAddActionPerformed(ActionEvent evt) {
        String stuName = this.stuNameJcb.getSelectedItem() != null ? this.stuNameJcb.getSelectedItem().toString() : "";
        String chineseScore = this.chineseScoreText.getText();
        String mathScore = this.mathScoreText.getText();
        String englishScore = this.englishScoreText.getText();

        if (StringUtil.isEmpty(stuName)) {
            JOptionPane.showMessageDialog(null, "学生姓名不能为空！");
            return;
        }

        if (StringUtil.isEmpty(chineseScore)) {
            JOptionPane.showMessageDialog(null, "语文成绩不能为空！");
            return;
        }

        if (StringUtil.isEmpty(mathScore)) {
            JOptionPane.showMessageDialog(null, "数学成绩不能为空！");
            return;
        }

        if (StringUtil.isEmpty(englishScore)) {
            JOptionPane.showMessageDialog(null, "英语成绩不能为空！");
            return;
        }

        StuGrade stuGrade = getStuGrade(chineseScore, mathScore, englishScore);

        Connection con = null;
        try {

            con = dbUtil.getCon();

            int addNum = stuGradeDao.add(con, stuGrade);
            if (addNum == 1) {
                JOptionPane.showMessageDialog(null, "学生添加成功！");
                resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "学生添加失败！");
            }

        } catch (Exception e) {
            e.notify();
            JOptionPane.showMessageDialog(null, "学生添加失败！");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.notify();
            }
        }
    }

    /**
     * 获取学生成绩信息
     *
     * @param chineseScore 语文成绩
     * @param mathScore    数学成绩
     * @param englishScore 英语成绩
     * @return 学生成绩信息
     */
    private StuGrade getStuGrade(String chineseScore, String mathScore, String englishScore) {
        StuClass stuClass = (StuClass) StuClassJcb.getSelectedItem();
        int classId = 0;
        if (stuClass != null) {
            classId = stuClass.getId();
        }

        Student student = (Student) stuNameJcb.getSelectedItem();
        String stuName = null;
        if (student != null) {
            stuName = student.getName();
        }

        return new StuGrade(classId, stuName, Float.parseFloat(chineseScore), Float.parseFloat(mathScore), Float.parseFloat(englishScore));
    }

    /**
     * 重置表单
     */
    private void resetValue() {
        this.stuNameJcb.removeAllItems();
        fillStuName();
        if (this.stuNameJcb.getItemCount() > 0) {
            this.stuNameJcb.setSelectedIndex(0);
        }
        this.chineseScoreText.setText("");
        this.mathScoreText.setText("");
        this.englishScoreText.setText("");
        if (this.StuClassJcb.getItemCount() > 0) {
            this.StuClassJcb.setSelectedIndex(0);
        }
    }

    /**
     * 初始化学生班级下拉框
     */
    private void fillStuClass() {
        Connection connection;
        StuClass stuClass;
        try {
            connection = dbUtil.getCon();
            ResultSet rs = bookTypeDao.list(connection, new StuClass());
            while (rs.next()) {
                stuClass = new StuClass();
                stuClass.setId(rs.getInt("id"));
                stuClass.setStuClassName(rs.getString("stuClassName"));
                this.StuClassJcb.addItem(stuClass);
            }
        } catch (Exception e) {
            e.notify();
        }
    }

    /**
     * 初始化学生姓名下拉框
     */
    private void fillStuName() {
        Connection connection;
        List<Student> uniqueStudents = new ArrayList<>();
        try {
            connection = dbUtil.getCon();

            ResultSet stuInfos = studentDao.list(connection, new Student());
            while (stuInfos.next()) {
                Student student = new Student();
                student.setId(stuInfos.getInt("id"));
                String name = stuInfos.getString("name");
                if (name != null) {
                    student.setName(name);
                    uniqueStudents.add(student);
                }
            }

            ResultSet stuGradeInfos = stuGradeDao.list(connection, new StuGrade());
            Set<String> namesToRemove = new HashSet<>();
            while (stuGradeInfos.next()) {
                String gradeName = stuGradeInfos.getString("name");
                if (gradeName != null) {
                    namesToRemove.add(gradeName);
                }
            }

            List<Student> filteredStudents = uniqueStudents.stream()
                    .filter(student -> !namesToRemove.contains(student.getName()))
                    .collect(Collectors.toList());

            for (Student student : filteredStudents) {
                stuNameJcb.addItem(student);
            }

        } catch (Exception e) {
            e.notify();
        }
    }
}
