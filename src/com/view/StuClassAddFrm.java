package com.view;

import com.dao.StuClassDao;
import com.model.StuClass;
import com.util.DbUtil;
import com.util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.Objects;

/**
 * 学生班级添加界面
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class StuClassAddFrm extends JInternalFrame {

    private final JTextField stuClassNameTxt;
    private final JTextArea stuClassDescTxt;

    private final DbUtil dbUtil = new DbUtil();
    private final StuClassDao bookTypeDao = new StuClassDao();

    /**
     * 启动应用程序
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                StuClassAddFrm stuClassAddFrm = new StuClassAddFrm();
                stuClassAddFrm.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建框架
     */
    public StuClassAddFrm() {
        setFrameIcon(new ImageIcon(Objects.requireNonNull(StuClassAddFrm.class.getResource("/icon/addGroup.png"))));
        setIconifiable(true);
        setClosable(true);
        setTitle("学生班级添加");
        setBounds(100, 100, 660, 490);

        JLabel stuClassNameLbl = new JLabel("学生班级名称：");
        stuClassNameLbl.setFont(new Font("宋体", Font.BOLD, 20));
        stuClassNameTxt = new JTextField();
        stuClassNameTxt.setColumns(10);

        JLabel stuClassDescLbl = new JLabel("学生班级描述：");
        stuClassDescLbl.setFont(new Font("宋体", Font.BOLD, 20));
        stuClassDescTxt = new JTextArea();

        JButton addStuClassBtn = new JButton("添加");
        addStuClassBtn.addActionListener(this::bookTypeAddActionPerformed);
        addStuClassBtn.setIcon(new ImageIcon(Objects.requireNonNull(StuClassAddFrm.class.getResource("/icon/add.png"))));

        JButton resetStuClassBtn = new JButton("重置");
        resetStuClassBtn.addActionListener(this::resetValueActionPerformed);
        resetStuClassBtn.setIcon(new ImageIcon(Objects.requireNonNull(StuClassAddFrm.class.getResource("/icon/reset.png"))));
        GroupLayout stuClassAddFrmLayout = new GroupLayout(getContentPane());
        stuClassAddFrmLayout.setHorizontalGroup(
                stuClassAddFrmLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuClassAddFrmLayout.createSequentialGroup()
                                .addGroup(stuClassAddFrmLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(stuClassAddFrmLayout.createSequentialGroup()
                                                .addGap(163)
                                                .addComponent(addStuClassBtn)
                                                .addGap(113)
                                                .addComponent(resetStuClassBtn))
                                        .addGroup(stuClassAddFrmLayout.createSequentialGroup()
                                                .addGap(54)
                                                .addGroup(stuClassAddFrmLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(stuClassDescLbl)
                                                        .addComponent(stuClassNameLbl))
                                                .addGap(32)
                                                .addGroup(stuClassAddFrmLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(stuClassDescTxt, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(stuClassNameTxt, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))))
                                .addGap(132))
        );
        stuClassAddFrmLayout.setVerticalGroup(
                stuClassAddFrmLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(stuClassAddFrmLayout.createSequentialGroup()
                                .addGap(95)
                                .addGroup(stuClassAddFrmLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stuClassNameLbl)
                                        .addComponent(stuClassNameTxt, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                .addGap(45)
                                .addGroup(stuClassAddFrmLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(stuClassDescLbl)
                                        .addComponent(stuClassDescTxt, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
                                .addGap(36)
                                .addGroup(stuClassAddFrmLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(addStuClassBtn, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                        .addComponent(resetStuClassBtn, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                                .addGap(46))
        );
        getContentPane().setLayout(stuClassAddFrmLayout);

        //设置文本域边框
        stuClassDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185), 1, false));
    }

    /**
     * 学生班级添加事件处理
     *
     * @param evt 事件
     */
    private void bookTypeAddActionPerformed(ActionEvent evt) {
        String stuClassName = this.stuClassNameTxt.getText();
        String stuClassDesc = this.stuClassDescTxt.getText();
        if (StringUtil.isEmpty(stuClassName)) {
            JOptionPane.showMessageDialog(null, "学生班级名称不能为空！");
            return;
        }
        StuClass stuClass = new StuClass(stuClassName, stuClassDesc);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int n = bookTypeDao.add(con, stuClass);
            if (n == 1) {
                JOptionPane.showMessageDialog(null, "学生班级添加成功！");
                resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "学生班级添加失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "学生班级添加失败！");
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
        this.resetValue();

    }

    /**
     * 重置表单
     */
    private void resetValue() {
        this.stuClassNameTxt.setText("");
        this.stuClassDescTxt.setText("");
    }
}
