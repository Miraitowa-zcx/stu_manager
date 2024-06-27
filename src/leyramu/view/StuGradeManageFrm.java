package leyramu.view;

import leyramu.dao.StuClassDao;
import leyramu.dao.StuGradeDao;
import leyramu.model.StuClass;
import leyramu.model.StuGrade;
import leyramu.util.DbUtil;
import leyramu.util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.Vector;

/**
 * 学生成绩管理界面
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StuGradeManageFrm extends JInternalFrame {

    /**
     * 学生表格
     */
    private final JTable stuTable;

    /**
     * 搜索学生姓名文本框
     */
    private final JTextField searchStudentNameTxt;

    /**
     * 搜索学生班级下拉框
     */
    private final JComboBox<StuClass> searchStudentClassJcb;

    /**
     * 搜索学生班级下拉框
     */
    private final JComboBox<StuClass> stuClassJcb;

    /**
     * 学号文本框
     */
    private final JTextField schoolDegreeTxt;

    /**
     * 学生姓名文本框
     */
    private final JTextField stuNameTxt;

    /**
     * 语文成绩文本框
     */
    private final JTextField chineseScoreText;

    /**
     * 数学成绩文本框
     */
    private final JTextField mathScoreText;

    /**
     * 英语成绩文本框
     */
    private final JTextField englishScoreText;

    /**
     * 搜索学号文本框
     */
    private final JTextField searchNumberTxt;

    /**
     * 数据库工具类对象
     */
    private final DbUtil dbUtil = new DbUtil();

    /**
     * 学生班级对象
     */
    private final StuClassDao stuClassDao = new StuClassDao();

    /**
     * 学生成绩对象
     */
    private final StuGradeDao stuGradeDao = new StuGradeDao();

    /**
     * 启动应用程序
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                StudentManageFrm studentManageFrm = new StudentManageFrm();
                studentManageFrm.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建框架
     */
    public StuGradeManageFrm() {
        setFrameIcon(new ImageIcon(Objects.requireNonNull(StudentManageFrm.class.getResource("/icon/setting.png"))));
        setIconifiable(true);
        setClosable(true);
        setTitle("学生成绩管理");
        setBounds(100, 100, 735, 600);

        JScrollPane stuGradeScrollPane = new JScrollPane();

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(new TitledBorder(null, "搜索条件", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel formPanel = new JPanel();
        formPanel.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout listGroupLayout = new GroupLayout(getContentPane());
        listGroupLayout.setHorizontalGroup(
                listGroupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(listGroupLayout.createSequentialGroup()
                                .addGap(30)
                                .addGroup(listGroupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(stuGradeScrollPane, GroupLayout.PREFERRED_SIZE, 669, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(listGroupLayout.createParallelGroup(Alignment.TRAILING)
                                                .addComponent(formPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(searchPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(17, Short.MAX_VALUE))
        );
        listGroupLayout.setVerticalGroup(
                listGroupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(listGroupLayout.createSequentialGroup()
                                .addGap(18)
                                .addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(stuGradeScrollPane, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(formPanel, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        JLabel schoolDegreeJbl = new JLabel("学号：");
        schoolDegreeTxt = new JTextField();
        schoolDegreeTxt.setEditable(false);
        schoolDegreeTxt.setColumns(10);

        JLabel stuNameJbl = new JLabel("学生姓名：");
        stuNameTxt = new JTextField();
        stuNameTxt.setEditable(false);
        stuNameTxt.setColumns(10);

        JLabel chineseScoreJbl = new JLabel("语文成绩：");
        chineseScoreText = new JTextField();
        chineseScoreText.setColumns(10);

        JLabel mathScoreJbl = new JLabel("数学成绩：");
        mathScoreText = new JTextField();
        mathScoreText.setColumns(10);

        JLabel englishScore = new JLabel("英语成绩：");
        englishScoreText = new JTextField();
        englishScoreText.setColumns(10);

        JLabel stuClassJbl = new JLabel("学生班级：");
        stuClassJcb = new JComboBox<>();
        stuClassJcb.setEditable(false);

        JButton reviseButton = new JButton("修改");
        reviseButton.addActionListener(this::stuGradeUpdateActionPerformed);
        reviseButton.setIcon(new ImageIcon(Objects.requireNonNull(StudentManageFrm.class.getResource("/icon/edit.png"))));

        JButton deleteButton = new JButton("删除");
        deleteButton.addActionListener(this::stuGradeDeleteActionPerformed);
        deleteButton.setIcon(new ImageIcon(Objects.requireNonNull(StudentManageFrm.class.getResource("/icon/delete.png"))));

        GroupLayout stuInfoList = new GroupLayout(formPanel);

        stuInfoList.setHorizontalGroup(
                stuInfoList.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuInfoList.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(stuInfoList.createParallelGroup(Alignment.LEADING)
                                        .addGroup(stuInfoList.createSequentialGroup()
                                                .addGap(18)
                                                .addComponent(schoolDegreeJbl)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(schoolDegreeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(stuNameJbl)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(stuNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGap(18)
                                                .addComponent(stuClassJbl)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(stuClassJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(stuInfoList.createSequentialGroup()
                                                .addGroup(stuInfoList.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(stuInfoList.createSequentialGroup()
                                                                .addComponent(chineseScoreJbl)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(chineseScoreText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(mathScoreJbl)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(mathScoreText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(englishScore)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(englishScoreText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                )
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(stuInfoList.createSequentialGroup()
                                                .addComponent(reviseButton)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(deleteButton)))
                                .addContainerGap(95, Short.MAX_VALUE))
        );

        stuInfoList.setVerticalGroup(
                stuInfoList.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuInfoList.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(stuInfoList.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(schoolDegreeJbl)
                                        .addComponent(schoolDegreeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stuNameJbl)
                                        .addComponent(stuNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stuClassJbl)
                                        .addComponent(stuClassJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(stuInfoList.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(mathScoreJbl)
                                        .addComponent(mathScoreText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chineseScoreJbl)
                                        .addComponent(chineseScoreText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(englishScore)
                                        .addComponent(englishScoreText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(stuInfoList.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(reviseButton)
                                        .addComponent(deleteButton))
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        formPanel.setLayout(stuInfoList);

        JLabel searchStuNameJbl = new JLabel("学生姓名：");
        searchStudentNameTxt = new JTextField();
        searchStudentNameTxt.setColumns(10);

        JLabel searchSchoolDegreeJbl = new JLabel("学号：");

        searchNumberTxt = new JTextField();
        searchNumberTxt.setColumns(10);

        JLabel searchStudentClassJbl = new JLabel("学生班级：");
        searchStudentClassJcb = new JComboBox<>();

        JButton inquireBtn = new JButton("查询");
        inquireBtn.addActionListener(this::stuGradeSearchActionPerformed);
        inquireBtn.setIcon(new ImageIcon(Objects.requireNonNull(StudentManageFrm.class.getResource("/icon/computer.gif"))));

        GroupLayout formGroupLayout = new GroupLayout(searchPanel);
        formGroupLayout.setHorizontalGroup(
                formGroupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(formGroupLayout.createSequentialGroup()
                                .addComponent(searchStuNameJbl)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(searchStudentNameTxt, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(searchSchoolDegreeJbl)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(searchNumberTxt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(searchStudentClassJbl)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(searchStudentClassJcb, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(inquireBtn)
                                .addContainerGap())
        );
        formGroupLayout.setVerticalGroup(
                formGroupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(formGroupLayout.createSequentialGroup()
                                .addGroup(formGroupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(formGroupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(formGroupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(searchStuNameJbl)
                                                        .addComponent(searchStudentNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(formGroupLayout.createSequentialGroup()
                                                .addGap(16)
                                                .addComponent(searchSchoolDegreeJbl))
                                        .addGroup(formGroupLayout.createSequentialGroup()
                                                .addGap(14)
                                                .addComponent(searchNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(formGroupLayout.createSequentialGroup()
                                                .addGap(18)
                                                .addComponent(searchStudentClassJbl))
                                        .addGroup(formGroupLayout.createSequentialGroup()
                                                .addGap(14)
                                                .addComponent(searchStudentClassJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(formGroupLayout.createSequentialGroup()
                                                .addGap(14)
                                                .addComponent(inquireBtn)))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        searchPanel.setLayout(formGroupLayout);

        stuTable = new JTable();
        stuTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent met) {
                stuGradeTableMousePressed();
            }
        });
        stuTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "编号", "学号", "姓名", "学生班级", "语文成绩", "数学成绩", "英语成绩"
                }
        ) {
            final boolean[] columnEditable = new boolean[]{
                    false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return columnEditable[column];
            }
        });
        stuTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        stuTable.getColumnModel().getColumn(5).setPreferredWidth(97);
        stuTable.getColumnModel().getColumn(6).setPreferredWidth(90);
        stuGradeScrollPane.setViewportView(stuTable);
        getContentPane().setLayout(listGroupLayout);

        this.fillStudentClassJcb("search");
        this.fillStudentClassJcb("modify");
        this.fillStuGradeTable(new StuGrade());

    }

    /**
     * 学生删除事件处理
     *
     * @param evt 事件
     */
    private void stuGradeDeleteActionPerformed(ActionEvent evt) {
        String id = schoolDegreeTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要删除的记录");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                int deleteNum = stuGradeDao.delete(con, id);
                if (deleteNum == 1) {
                    JOptionPane.showConfirmDialog(null, "删除成功");
                    this.resetValue();
                    this.fillStuGradeTable(new StuGrade());
                } else {
                    JOptionPane.showConfirmDialog(null, "删除失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showConfirmDialog(null, "删除失败");
            } finally {
                try {
                    dbUtil.closeCon(con);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 学生修改事件处理
     *
     * @param evt 事件
     */
    private void stuGradeUpdateActionPerformed(ActionEvent evt) {
        String schoolDegree = this.schoolDegreeTxt.getText();
        if (StringUtil.isEmpty(schoolDegree)) {
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }
        String name = this.stuNameTxt.getText();
        String chineseScore = this.chineseScoreText.getText();
        String mathScore = this.mathScoreText.getText();
        String englishScore = this.englishScoreText.getText();
        if (StringUtil.isEmpty(name)) {
            JOptionPane.showMessageDialog(null, "学生姓名不能为空！");
            return;
        }

        if (StringUtil.isEmpty(chineseScore)) {
            JOptionPane.showMessageDialog(null, "学生成绩不能为空！");
            return;
        }

        if (StringUtil.isEmpty(mathScore)) {
            JOptionPane.showMessageDialog(null, "学生成绩不能为空！");
            return;
        }

        if (StringUtil.isEmpty(englishScore)) {
            JOptionPane.showMessageDialog(null, "学生成绩不能为空！");
            return;
        }

        StuClass stuClass = (StuClass) stuClassJcb.getSelectedItem();
        int stuClassId = 0;
        if (stuClass != null) {
            stuClassId = stuClass.getId();
        }

        StuGrade stuGrade = new StuGrade(stuClassId, name, Float.parseFloat(chineseScore), Float.parseFloat(mathScore), Float.parseFloat(englishScore));

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int addNum = stuGradeDao.update(con, stuGrade);
            if (addNum == 1) {
                JOptionPane.showMessageDialog(null, "学生修改成功！");
                resetValue();
                this.fillStuGradeTable(new StuGrade());
            } else {
                JOptionPane.showMessageDialog(null, "学生修改失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "学生修改失败！");
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
        this.schoolDegreeTxt.setText("");
        this.stuNameTxt.setText("");
        this.chineseScoreText.setText("");
        this.mathScoreText.setText("");
        this.englishScoreText.setText("");
        if (this.stuClassJcb.getItemCount() > 0) {
            this.stuClassJcb.setSelectedIndex(0);
        }
    }

    /**
     * 表格行点击事件处理
     */
    private void stuGradeTableMousePressed() {
        int row = this.stuTable.getSelectedRow();
        this.schoolDegreeTxt.setText((String) stuTable.getValueAt(row, 1));
        this.stuNameTxt.setText((String) stuTable.getValueAt(row, 2));
        this.chineseScoreText.setText(String.valueOf(stuTable.getValueAt(row, 4)));
        this.mathScoreText.setText(String.valueOf(stuTable.getValueAt(row, 5)));
        this.englishScoreText.setText(String.valueOf(stuTable.getValueAt(row, 6)));
        String stuClassName = (String) this.stuTable.getValueAt(row, 3);
        this.stuClassJcb.getItemCount();
        int n = this.stuClassJcb.getItemCount();
        for (int i = 0; i < n; i++) {
            StuClass item = this.stuClassJcb.getItemAt(i);
            if (item.getStuClassName().equals(stuClassName)) {
                this.stuClassJcb.setSelectedIndex(i);
            }
        }
    }

    /**
     * 学生查询事件处理
     *
     * @param evt 事件
     */
    private void stuGradeSearchActionPerformed(ActionEvent evt) {
        String name = this.searchStudentNameTxt.getText();
        String numberStr = this.searchNumberTxt.getText();
        if (numberStr == null || numberStr.trim().isEmpty()) {
            return;
        }
        int number = Integer.parseInt(numberStr);

        StuClass stuClass = (StuClass) this.searchStudentClassJcb.getSelectedItem();
        int stuClassId = 0;
        if (stuClass != null) {
            stuClassId = stuClass.getId();
        }

        StuGrade stuGrade = new StuGrade(name, number, stuClassId);
        this.fillStuGradeTable(stuGrade);
    }

    /**
     * 初始化下拉框
     */
    private void fillStudentClassJcb(String type) {
        Connection con = null;
        StuClass stuClassName;
        try {
            con = dbUtil.getCon();
            ResultSet rs = stuClassDao.list(con, new StuClass());
            if ("search".equals(type)) {
                stuClassName = new StuClass();
                stuClassName.setStuClassName("请选择...");
                stuClassName.setId(-1);
                this.searchStudentClassJcb.addItem(stuClassName);
            }
            while (rs.next()) {
                stuClassName = new StuClass();
                stuClassName.setStuClassName(rs.getString("stuClassName"));
                stuClassName.setId(rs.getInt("id"));
                if ("search".equals(type)) {
                    this.searchStudentClassJcb.addItem(stuClassName);
                } else if ("modify".equals(type)) {
                    this.stuClassJcb.addItem(stuClassName);
                }
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
     * 初始化表格数据
     *
     * @param stuGrade 数据
     */
    private void fillStuGradeTable(StuGrade stuGrade) {
        DefaultTableModel dtm = (DefaultTableModel) stuTable.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = stuGradeDao.list(con, stuGrade);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("id"));
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("stuClassName"));
                v.add(rs.getFloat("chinese_score"));
                v.add(rs.getFloat("math_score"));
                v.add(rs.getFloat("english_score"));
                dtm.addRow(v);
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
}
