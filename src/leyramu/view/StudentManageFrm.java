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
 * 学生管理界面
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StudentManageFrm extends JInternalFrame {

    /**
     * 学生表格
     */
    private final JTable stuTable;

    /**
     * 搜索学生姓名文本框
     */
    private final JTextField searchStudentNameTxt;

    /**
     * 搜索家庭地址文本框
     */
    private final JTextField searchHomeAddressTxt;

    /**
     * 搜索学生班级下拉框
     */
    private final JComboBox<StuClass> searchStudentClassJcb;

    /**
     * 搜索学生性别单选按钮 - 男
     */
    private final JRadioButton manJrb;

    /**
     * 搜索学生性别单选按钮 - 女
     */
    private final JRadioButton femaleJrb;

    /**
     * 学生描述文本域
     */
    private final JTextArea stuDescTxt;

    /**
     * 学生班级下拉框
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
     * 学生成绩文本框
     */
    private final JTextField stuGradeTxt;

    /**
     * 家庭住址文本框
     */
    private final JTextField homeAddressTxt;

    /**
     * 数据库工具类
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
    public StudentManageFrm() {
        setFrameIcon(new ImageIcon(Objects.requireNonNull(StudentManageFrm.class.getResource("/icon/setting.png"))));
        setIconifiable(true);
        setClosable(true);
        setTitle("学生管理");
        setBounds(100, 100, 735, 600);

        JScrollPane stuManagePanel = new JScrollPane();

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
                                        .addComponent(stuManagePanel, GroupLayout.PREFERRED_SIZE, 669, GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(stuManagePanel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
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
        stuNameTxt.setColumns(10);

        JLabel stuSexJbl = new JLabel("学生性别：");
        manJrb = new JRadioButton("男");
        ButtonGroup stuSexButtonBtn = new ButtonGroup();
        stuSexButtonBtn.add(manJrb);
        manJrb.setSelected(true);
        femaleJrb = new JRadioButton("女");
        stuSexButtonBtn.add(femaleJrb);

        JLabel stuGradeJbl = new JLabel("成绩：");
        stuGradeTxt = new JTextField();
        stuGradeTxt.setColumns(10);

        JLabel homeAddressJbl = new JLabel("家庭住址：");
        homeAddressTxt = new JTextField();
        homeAddressTxt.setColumns(10);

        JLabel stuClassJbl = new JLabel("学生班级：");
        stuClassJcb = new JComboBox<>();

        JLabel stuDescJbl = new JLabel("学生描述：");
        stuDescTxt = new JTextArea();

        JButton reviseButton = new JButton("修改");
        reviseButton.addActionListener(this::bookUpdateActionPerformed);
        reviseButton.setIcon(new ImageIcon(Objects.requireNonNull(StudentManageFrm.class.getResource("/icon/edit.png"))));

        JButton deleteButton = new JButton("删除");
        deleteButton.addActionListener(this::studentDeleteActionPerformed);
        deleteButton.setIcon(new ImageIcon(Objects.requireNonNull(StudentManageFrm.class.getResource("/icon/delete.png"))));

        GroupLayout stuManagePanelLayout = new GroupLayout(formPanel);
        stuManagePanelLayout.setHorizontalGroup(
                stuManagePanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(stuManagePanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                .addGroup(stuManagePanelLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                                .addGroup(stuManagePanelLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                                                .addComponent(schoolDegreeJbl)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(schoolDegreeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                                                .addComponent(stuGradeJbl)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(stuGradeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(31)
                                                                .addGroup(stuManagePanelLayout.createParallelGroup(Alignment.LEADING, false)
                                                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                                                .addComponent(stuNameJbl)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(stuNameTxt, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                                                .addComponent(homeAddressJbl)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(homeAddressTxt)))
                                                                .addGap(18)
                                                                .addGroup(stuManagePanelLayout.createParallelGroup(Alignment.LEADING, false)
                                                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                                                .addComponent(stuSexJbl)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(manJrb)
                                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                                .addComponent(femaleJrb))
                                                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                                                .addComponent(stuClassJbl)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(stuClassJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                                .addComponent(stuDescJbl)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(stuDescTxt)))
                                                .addContainerGap(95, Short.MAX_VALUE))
                                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                                .addComponent(reviseButton)
                                                .addGap(40)
                                                .addComponent(deleteButton)
                                                .addContainerGap())))
        );
        stuManagePanelLayout.setVerticalGroup(
                stuManagePanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuManagePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(stuManagePanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(schoolDegreeJbl)
                                        .addComponent(schoolDegreeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stuNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stuNameJbl)
                                        .addComponent(stuSexJbl)
                                        .addComponent(manJrb)
                                        .addComponent(femaleJrb))
                                .addGap(18)
                                .addGroup(stuManagePanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stuGradeJbl)
                                        .addComponent(stuGradeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(homeAddressJbl)
                                        .addComponent(homeAddressTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stuClassJbl)
                                        .addComponent(stuClassJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(stuManagePanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(stuDescJbl)
                                        .addComponent(stuDescTxt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addGroup(stuManagePanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(reviseButton)
                                        .addComponent(deleteButton))
                                .addContainerGap())
        );
        formPanel.setLayout(stuManagePanelLayout);

        JLabel searchStuNameJbl = new JLabel("学生姓名：");
        searchStudentNameTxt = new JTextField();
        searchStudentNameTxt.setColumns(10);

        JLabel searchHomeAddressJbl = new JLabel("家庭住址：");

        searchHomeAddressTxt = new JTextField();
        searchHomeAddressTxt.setColumns(10);

        JLabel searchStudentClassJbl = new JLabel("学生班级：");
        searchStudentClassJcb = new JComboBox<>();

        JButton inquireBtn = new JButton("查询");
        inquireBtn.addActionListener(this::stuSearchActionPerformed);
        inquireBtn.setIcon(new ImageIcon(Objects.requireNonNull(StudentManageFrm.class.getResource("/icon/computer.gif"))));

        GroupLayout formGroupLayout = new GroupLayout(searchPanel);
        formGroupLayout.setHorizontalGroup(
                formGroupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(formGroupLayout.createSequentialGroup()
                                .addComponent(searchStuNameJbl)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(searchStudentNameTxt, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(searchHomeAddressJbl)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(searchHomeAddressTxt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
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
                                                .addComponent(searchHomeAddressJbl))
                                        .addGroup(formGroupLayout.createSequentialGroup()
                                                .addGap(14)
                                                .addComponent(searchHomeAddressTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
                studentTableMousePressed();
            }
        });
        stuTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "学号", "姓名", "家庭住址", "性别", "成绩", "学生描述", "学生班级"
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
        stuManagePanel.setViewportView(stuTable);
        getContentPane().setLayout(listGroupLayout);

        stuDescTxt.setBorder(new LineBorder(new Color(127, 157, 185), 1, false));

        this.fillStuClassJcb("search");
        this.fillStuClassJcb("modify");
        this.fillStuTable(new Student());

    }

    /**
     * 学生删除事件处理
     *
     * @param evt 事件
     */
    private void studentDeleteActionPerformed(ActionEvent evt) {
        String schoolDegree = schoolDegreeTxt.getText();
        if (StringUtil.isEmpty(schoolDegree)) {
            JOptionPane.showMessageDialog(null, "请选择要删除的记录");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                int deleteNum = studentDao.delete(con, schoolDegree);
                if (deleteNum == 1) {
                    JOptionPane.showConfirmDialog(null, "删除成功");
                    this.resetValue();
                    this.fillStuTable(new Student());
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
    private void bookUpdateActionPerformed(ActionEvent evt) {
        String id = this.schoolDegreeTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }
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

        StuClass stuClass = (StuClass) stuClassJcb.getSelectedItem();
        int stuClassId = 0;
        if (stuClass != null) {
            stuClassId = stuClass.getId();
        }

        Student student = new Student(Integer.parseInt(id), name, home, sex, Float.parseFloat(score), stuClassId, stuDesc);

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int addNum = studentDao.update(con, student);
            if (addNum == 1) {
                JOptionPane.showMessageDialog(null, "学生修改成功！");
                resetValue();
                this.fillStuTable(new Student());
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
        this.homeAddressTxt.setText("");
        this.stuGradeTxt.setText("");
        this.manJrb.setSelected(true);
        this.stuDescTxt.setText("");
        if (this.stuClassJcb.getItemCount() > 0) {
            this.stuClassJcb.setSelectedIndex(0);
        }
    }

    /**
     * 表格行点击事件处理
     */
    private void studentTableMousePressed() {
        int row = this.stuTable.getSelectedRow();
        this.schoolDegreeTxt.setText((String) stuTable.getValueAt(row, 0));
        this.stuNameTxt.setText((String) stuTable.getValueAt(row, 1));
        this.homeAddressTxt.setText((String) stuTable.getValueAt(row, 2));
        String sex = (String) stuTable.getValueAt(row, 3);
        if ("男".equals(sex)) {
            this.manJrb.setSelected(true);
        } else if ("女".equals(sex)) {
            this.femaleJrb.setSelected(true);
        }
        this.stuGradeTxt.setText(stuTable.getValueAt(row, 4) + "");
        this.stuDescTxt.setText((String) stuTable.getValueAt(row, 5));
        String stuClassName = (String) this.stuTable.getValueAt(row, 6);
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
    private void stuSearchActionPerformed(ActionEvent evt) {
        String name = this.searchStudentNameTxt.getText();
        String home = this.searchHomeAddressTxt.getText();
        StuClass stuClass = (StuClass) this.searchStudentClassJcb.getSelectedItem();
        int stuClassId = 0;
        if (stuClass != null) {
            stuClassId = stuClass.getId();
        }

        Student student = new Student(name, home, stuClassId);
        this.fillStuTable(student);

    }

    /**
     * 初始化下拉框
     */
    private void fillStuClassJcb(String type) {
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
     * @param student 学生
     */
    private void fillStuTable(Student student) {
        DefaultTableModel dtm = (DefaultTableModel) stuTable.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = studentDao.list(con, student);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("home"));
                v.add(rs.getString("sex"));
                v.add(rs.getFloat("score"));
                v.add(rs.getString("stuDesc"));
                v.add(rs.getString("stuClassName"));
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
