package view;

import view.component.ActionPanel;
import view.component.InputPanel;
import view.component.ResultPanel;
import view.component.AsymmetricEncryption.AlgorithmPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AsymmetricEncryptionPanel extends JPanel {

    private InputPanel inputPanel;
    private ActionPanel actionPanel;
    private ResultPanel resultPanel;
    private AlgorithmPanel algorithmPanel;

    public AsymmetricEncryptionPanel() {
        setLayout(new BorderLayout(1, 1));

        inputPanel = new InputPanel();
        actionPanel = new ActionPanel();
        resultPanel = new ResultPanel();
        algorithmPanel = new AlgorithmPanel();  // Thêm AlgorithmPanel

        // Thêm sự kiện cho các nút trong actionPanel
        actionPanel.addEncryptListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptText();
            }
        });

        actionPanel.addDecryptListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptText();
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, resultPanel);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);

        add(splitPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
        add(algorithmPanel, BorderLayout.NORTH);  // Thêm AlgorithmPanel vào phía trên
    }

    private void encryptText() {
        String text = inputPanel.getTextArea().getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập văn bản để mã hóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        resultPanel.getEncryptResultTextArea().setText("Mã hóa: " + text);
    }

    private void decryptText() {
        String encryptedText = resultPanel.getEncryptResultTextArea().getText();
        if (encryptedText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng mã hóa văn bản trước.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        resultPanel.getDecryptResultTextArea().setText(encryptedText.replace("Mã hóa: ", ""));
    }
}
