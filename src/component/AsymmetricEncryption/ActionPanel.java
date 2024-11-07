package component.AsymmetricEncryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel {

    private JButton encryptButton;
    private JButton decryptButton;

    public ActionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        encryptButton = new JButton("Mã hóa");
        decryptButton = new JButton("Giải mã");

        // Cài đặt màu sắc cho các nút
        encryptButton.setBackground(new Color(0, 128, 0));
        encryptButton.setForeground(Color.WHITE);
        encryptButton.setFocusPainted(false);

        decryptButton.setBackground(new Color(128, 0, 0));
        decryptButton.setForeground(Color.WHITE);
        decryptButton.setFocusPainted(false);

        add(encryptButton);
        add(decryptButton);
    }

    public void addEncryptListener(ActionListener listener) {
        encryptButton.addActionListener(listener);
    }

    public void addDecryptListener(ActionListener listener) {
        decryptButton.addActionListener(listener);
    }
}