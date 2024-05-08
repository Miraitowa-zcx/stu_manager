package com.view;

import com.dao.StuClassDao;
import com.dao.StudentDao;
import com.model.StuClass;
import com.util.DbUtil;
import com.util.StringUtil;

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
 * 学生班级管理
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StuClassManageFrm extends JInternalFrame {

    private final JTable stuClassTable;
    private final JTextArea stuClassDescTxt;
    private final JTextField searchStuClassNameTxt;
    private final JTextField schoolDegreeTxt;
    private final JTextField stuClassNameTxt;

    private final DbUtil dbUtil = new DbUtil();
    private final StuClassDao stuClassDao = new StuClassDao();
    private final StudentDao studentDao = new StudentDao();

    /**
     * 启动应用程序
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                StuClassManageFrm stuClassManageFrm = new StuClassManageFrm();
                stuClassManageFrm.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建框架
     */
    public StuClassManageFrm() {

        setFrameIcon(new ImageIcon(Objects.requireNonNull(StuClassManageFrm.class.getResource("/icon/setting.png"))));
        setIconifiable(true);
        setClosable(true);
        setTitle("学生班级管理");
        setBounds(100, 100, 626, 643);

        JScrollPane stuClassTableScrollPane = new JScrollPane();

        JLabel searchStuClassNameLbl = new JLabel("学生班级名称：");
        searchStuClassNameTxt = new JTextField();
        searchStuClassNameTxt.setColumns(10);

        JButton searchStuClassNameBtn = new JButton("查询");
        searchStuClassNameBtn.addActionListener(this::stuClassSearchActionPerformed);
        searchStuClassNameBtn.setIcon(new ImageIcon(Objects.requireNonNull(StuClassManageFrm.class.getResource("/javax/swing/plaf/metal/icons/ocean/newFolder.gif"))));

        JPanel stuClassFormPanel = new JPanel();
        stuClassFormPanel.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout stuClassFormLayout = new GroupLayout(getContentPane());
        stuClassFormLayout.setHorizontalGroup(
                stuClassFormLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuClassFormLayout.createSequentialGroup()
                                .addGap(64)
                                .addGroup(stuClassFormLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(stuClassFormLayout.createSequentialGroup()
                                                .addComponent(stuClassFormPanel, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(stuClassFormLayout.createParallelGroup(Alignment.LEADING)
                                                .addGroup(stuClassFormLayout.createSequentialGroup()
                                                        .addComponent(stuClassTableScrollPane, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap())
                                                .addGroup(stuClassFormLayout.createSequentialGroup()
                                                        .addComponent(searchStuClassNameLbl)
                                                        .addGap(34)
                                                        .addComponent(searchStuClassNameTxt, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(searchStuClassNameBtn)
                                                        .addContainerGap(66, Short.MAX_VALUE)))))
        );
        stuClassFormLayout.setVerticalGroup(
                stuClassFormLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuClassFormLayout.createSequentialGroup()
                                .addGap(44)
                                .addGroup(stuClassFormLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(searchStuClassNameLbl)
                                        .addComponent(searchStuClassNameBtn)
                                        .addComponent(searchStuClassNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(33)
                                .addComponent(stuClassTableScrollPane, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                                .addGap(31)
                                .addComponent(stuClassFormPanel, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                .addGap(26))
        );

        JLabel schoolDegreeLbl = new JLabel("编号：");
        schoolDegreeTxt = new JTextField();
        schoolDegreeTxt.setEditable(false);
        schoolDegreeTxt.setColumns(10);

        JLabel stuClassNameLbl = new JLabel("学生班级名称：");
        stuClassNameTxt = new JTextField();
        stuClassNameTxt.setColumns(10);

        JLabel stuClassDescLbl = new JLabel("描述：");
        stuClassDescTxt = new JTextArea();

        JButton updateBtn = new JButton("修改");
        updateBtn.addActionListener(this::stuClassTableUpdateActionEvent);
        updateBtn.setIcon(new ImageIcon(Objects.requireNonNull(StuClassManageFrm.class.getResource("/icon/update.png"))));

        JButton deleteBtn = new JButton("删除");
        deleteBtn.addActionListener(this::stuClassTableDeleteActionEvent);
        deleteBtn.setIcon(new ImageIcon(Objects.requireNonNull(StuClassManageFrm.class.getResource("/icon/delete.png"))));
        GroupLayout stuClassLayout = new GroupLayout(stuClassFormPanel);
        stuClassLayout.setHorizontalGroup(
                stuClassLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuClassLayout.createSequentialGroup()
                                .addGroup(stuClassLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(stuClassLayout.createSequentialGroup()
                                                .addComponent(schoolDegreeLbl)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(schoolDegreeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(37)
                                                .addComponent(stuClassNameLbl)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(stuClassNameTxt, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(stuClassLayout.createSequentialGroup()
                                                .addComponent(stuClassDescLbl)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(stuClassDescTxt, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(stuClassLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(updateBtn)
                                                .addGap(42)
                                                .addComponent(deleteBtn)))
                                .addContainerGap(23, Short.MAX_VALUE))
        );
        stuClassLayout.setVerticalGroup(
                stuClassLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuClassLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(stuClassLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(schoolDegreeLbl)
                                        .addComponent(schoolDegreeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stuClassNameLbl)
                                        .addComponent(stuClassNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(32)
                                .addGroup(stuClassLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stuClassDescLbl)
                                        .addComponent(stuClassDescTxt, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addGroup(stuClassLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(updateBtn)
                                        .addComponent(deleteBtn))
                                .addContainerGap())
        );
        stuClassFormPanel.setLayout(stuClassLayout);

        stuClassTable = new JTable();
        stuClassTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                stuClassTableMousePressed();
            }
        });
        stuClassTable.setFont(new Font("宋体", Font.PLAIN, 12));
        stuClassTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "编号", "学生班级名称", "学生班级描述"
                }
        ) {
            final boolean[] columnEditable = new boolean[]{
                    false, false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return columnEditable[column];
            }
        });
        stuClassTable.getColumnModel().getColumn(1).setPreferredWidth(134);
        stuClassTable.getColumnModel().getColumn(2).setPreferredWidth(176);
        stuClassTableScrollPane.setViewportView(stuClassTable);
        getContentPane().setLayout(stuClassFormLayout);

        this.fillTable(new StuClass());
        stuClassDescTxt.setBorder(new LineBorder(new Color(127, 157, 185), 1, false));
    }

    /**
     * 学生班级删除事件处理
     *
     * @param evt 事件
     */
    private void stuClassTableDeleteActionEvent(ActionEvent evt) {
        String schoolDegreeTxtText = schoolDegreeTxt.getText();
        if (StringUtil.isEmpty(schoolDegreeTxtText)) {
            JOptionPane.showMessageDialog(null, "请选择要删除的记录");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                boolean flag = studentDao.existStuByStuClassId(con, schoolDegreeTxtText);
                if (flag) {
                    JOptionPane.showMessageDialog(null, "当前学生班级下有学生，不能删除此类别");
                    return;
                }
                int deleteNum = stuClassDao.delete(con, schoolDegreeTxtText);
                if (deleteNum == 1) {
                    JOptionPane.showConfirmDialog(null, "删除成功");
                    this.resetValue();
                    this.fillTable(new StuClass());
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
     * 学生班级修改事件处理
     *
     * @param evt 事件
     */
    private void stuClassTableUpdateActionEvent(ActionEvent evt) {
        String id = schoolDegreeTxt.getText();
        String stuClassName = stuClassNameTxt.getText();
        String stuClassDesc = stuClassDescTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }
        if (StringUtil.isEmpty(stuClassName)) {
            JOptionPane.showMessageDialog(null, "学生班级名称不能为空");
            return;
        }
        StuClass stuClass = new StuClass(Integer.parseInt(id), stuClassName, stuClassDesc);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int modifyNum = stuClassDao.update(con, stuClass);
            if (modifyNum == 1) {
                JOptionPane.showMessageDialog(null, "修改成功");
                this.resetValue();
                this.fillTable(new StuClass());
            } else {
                JOptionPane.showMessageDialog(null, "修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(null, "修改失败");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 表格行点击处理
     */
    private void stuClassTableMousePressed() {
        int row = stuClassTable.getSelectedRow();
        schoolDegreeTxt.setText((String) stuClassTable.getValueAt(row, 0));
        stuClassNameTxt.setText((String) stuClassTable.getValueAt(row, 1));
        stuClassDescTxt.setText((String) stuClassTable.getValueAt(row, 2));
    }

    /**
     * 学生班级搜索事件处理
     *
     * @param evt 事件
     */
    private void stuClassSearchActionPerformed(ActionEvent evt) {
        String stuClassName = this.searchStuClassNameTxt.getText();
        StuClass stuClass = new StuClass();
        stuClass.setStuClassName(stuClassName);
        this.fillTable(stuClass);

    }

    /**
     * 初始化表格
     *
     * @param stuClass 学生班级
     */
    private void fillTable(StuClass stuClass) {
        DefaultTableModel dtm = (DefaultTableModel) stuClassTable.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = stuClassDao.list(con, stuClass);
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("id"));
                v.add(rs.getString("stuClassName"));
                v.add(rs.getString("stuClassDesc"));
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

    /**
     * 重置表单
     */
    private void resetValue() {
        this.schoolDegreeTxt.setText("");
        this.stuClassNameTxt.setText("");
        this.stuClassDescTxt.setText("");
    }
}
