package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatorInformation extends JPanel {
    public CreatorInformation() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));

        // Tiêu đề thông tin
        JLabel titleLabel = new JLabel("Thông tin về công cụ", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 0, 128));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Thông tin chi tiết về người phát triển và công cụ
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Thêm thông tin về công cụ
        JTextArea infoText = new JTextArea();
        infoText.setText("Tên công cụ: Tool mã hóa\n" +
                "Phiên bản: 1.0\n" +
                "Nhà phát triển: Nguyễn Thị Chúc Ngân\n" +
                "Email: 21130451@st.hcmuaf.edu.vn\n\n" +
                "Tên Khoa: Công nghệ thông tin\n" +
                "Lớp: DH21DTC\n" +
                "Trường: Đại học Nông Lâm TP.HCM\n" +
                "Môn: An toàn và bảo mật hệ thống thông tin\n\n" +
                "Cảm ơn bạn đã sử dụng công cụ của chúng tôi!");
        infoText.setEditable(false);
        infoText.setFont(new Font("Arial", Font.PLAIN, 16));
        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);
        infoText.setBackground(new Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(infoText);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        infoPanel.add(scrollPane);

        // Thêm liên kết liên hệ với nhà phát triển
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linkPanel.setBackground(new Color(255, 255, 255));
        linkPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nút Email
        JButton emailButton = new JButton("Liên hệ qua Email");
        emailButton.setForeground(Color.BLUE);
        emailButton.setBorderPainted(false);
        emailButton.setContentAreaFilled(false);
        emailButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon emailIcon = new ImageIcon(getClass().getResource("/image/email_icon.png"));
        Image image = emailIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        emailButton.setIcon(new ImageIcon(image));
        emailButton.setText(" Liên hệ qua Email");
        emailButton.setFont(new Font("Arial", Font.PLAIN, 16));
        emailButton.setPreferredSize(new Dimension(200, 40));
        emailButton.setFocusPainted(false);
        emailButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().mail(new java.net.URI("mailto:21130451@st.hcmuaf.edu.vn?subject=Câu hỏi về Tool Mã Hóa"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        emailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                emailButton.setForeground(Color.RED);
                emailButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                emailButton.setForeground(Color.BLUE);
                emailButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
            }
        });

        linkPanel.add(emailButton);

        // Nút GitHub
        JButton githubButton = new JButton("GitHub");
        githubButton.setForeground(Color.BLUE);
        githubButton.setFont(new Font("Arial", Font.PLAIN, 16));
        githubButton.setBorderPainted(false);
        githubButton.setContentAreaFilled(false);
        githubButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        githubButton.setPreferredSize(new Dimension(200, 40));
        githubButton.setFocusPainted(false);
        githubButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
        githubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://github.com/Nguyen-Thi-Chuc-Ngan/SafeDataEncryptor.git"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        linkPanel.add(githubButton);

        // Nút Phản hồi
        JButton feedbackButton = new JButton("Đánh giá công cụ");
        feedbackButton.setForeground(Color.GREEN);
        feedbackButton.setFont(new Font("Arial", Font.PLAIN, 16));
        feedbackButton.setBorderPainted(false);
        feedbackButton.setContentAreaFilled(false);
        feedbackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        feedbackButton.setPreferredSize(new Dimension(200, 40));
        feedbackButton.setFocusPainted(false);
        feedbackButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cảm ơn bạn đã phản hồi!");
            }
        });

        linkPanel.add(feedbackButton);

        // Nút Tìm hiểu thêm
        JButton moreInfoButton = new JButton("Tìm hiểu thêm");
        moreInfoButton.setForeground(Color.BLUE);
        moreInfoButton.setFont(new Font("Arial", Font.PLAIN, 16));
        moreInfoButton.setBorderPainted(false);
        moreInfoButton.setContentAreaFilled(false);
        moreInfoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        moreInfoButton.setPreferredSize(new Dimension(200, 40));
        moreInfoButton.setFocusPainted(false);
        moreInfoButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
        moreInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Lịch sử phát triển: \nPhiên bản 1.0...");
            }
        });

        linkPanel.add(moreInfoButton);

        infoPanel.add(linkPanel);

        add(infoPanel, BorderLayout.CENTER);
    }
}
