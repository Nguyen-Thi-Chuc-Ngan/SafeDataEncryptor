package view;

import javax.swing.*;

public class DigitalSignaturePanel extends JPanel {

    public DigitalSignaturePanel() {
        JLabel privateKeyLabel = new JLabel("Chọn Private Key:");
        JButton loadPrivateKeyButton = new JButton("Load Private Key");

        JLabel publicKeyLabel = new JLabel("Chọn Public Key:");
        JButton loadPublicKeyButton = new JButton("Load Public Key");

        JLabel messageLabel = new JLabel("Nhập văn bản:");
        JTextField messageField = new JTextField(20);

        JButton signButton = new JButton("Tạo chữ ký");
        JButton verifyButton = new JButton("Kiểm tra chữ ký");

        add(privateKeyLabel);
        add(loadPrivateKeyButton);
        add(publicKeyLabel);
        add(loadPublicKeyButton);
        add(messageLabel);
        add(messageField);
        add(signButton);
        add(verifyButton);
    }
}