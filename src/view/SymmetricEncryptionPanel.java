package view;

import component.SymmetricEncryption.InputPanel;
import component.SymmetricEncryption.KeyOptionsPanel;
import component.SymmetricEncryption.ResultPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SymmetricEncryptionPanel extends JPanel {

    public SymmetricEncryptionPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new TitledBorder("Mã hóa đối xứng"));

        KeyOptionsPanel keyOptionsPanel = new KeyOptionsPanel();
        InputPanel inputPanel = new InputPanel();
        ResultPanel resultPanel = new ResultPanel();

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton encryptButton = new JButton("Mã hóa");
        JButton decryptButton = new JButton("Giải mã");
        actionPanel.add(encryptButton);
        actionPanel.add(decryptButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, resultPanel);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);

        add(keyOptionsPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.SOUTH);
        add(splitPane, BorderLayout.CENTER);

        // Thêm sự kiện cho các nút mã hóa và giải mã
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptText(inputPanel, resultPanel);
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptText(inputPanel, resultPanel);
            }
        });
    }

    private void encryptText(InputPanel inputPanel, ResultPanel resultPanel) {
        String text = inputPanel.textArea.getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập văn bản để mã hóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Giả lập quá trình mã hóa (thực tế bạn sẽ thay bằng mã hóa đối xứng)
        String encryptedText = "Mã hóa: " + text;  // Đây là ví dụ, bạn có thể thay thế bằng thuật toán mã hóa thực sự.
        resultPanel.encryptResultTextArea.setText(encryptedText);
    }

    private void decryptText(InputPanel inputPanel, ResultPanel resultPanel) {
        String encryptedText = resultPanel.decryptResultTextArea.getText();
        if (encryptedText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng mã hóa văn bản trước.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Giả lập quá trình giải mã (thực tế bạn sẽ thay bằng thuật toán giải mã đối xứng)
        String decryptedText = encryptedText.replace("Mã hóa: ", "");  // Đây là ví dụ, bạn có thể thay thế bằng thuật toán giải mã thực sự.
        resultPanel.decryptResultTextArea.setText(decryptedText);
    }
}
