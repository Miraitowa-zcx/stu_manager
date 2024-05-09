package com.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * 关于作者窗体
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
public class AboutAuthorFrm extends JInternalFrame {

    /**
     * 启动应用程序
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                AboutAuthorFrm aboutAuthorFrm = new AboutAuthorFrm();
                aboutAuthorFrm.setVisible(true);
            } catch (Exception e) {
                e.notify();
            }
        });
    }

    /**
     * 创建框架
     */
    public AboutAuthorFrm() {
        setFrameIcon(new ImageIcon(Objects.requireNonNull(AboutAuthorFrm.class.getResource("/icon/author.png"))));
        getContentPane().setBackground(Color.RED);

        JLabel aboutAuthor = new JLabel("");
        aboutAuthor.setIcon(new ImageIcon(Objects.requireNonNull(AboutAuthorFrm.class.getResource("/photo/author.jpg"))));
        getContentPane().add(aboutAuthor, BorderLayout.CENTER);
        setIconifiable(true);
        setClosable(true);
        setEnabled(false);
        setTitle("关于作者");
        setBounds(100, 100, 540, 445);
    }
}
