package view;

import view.component.InputPanel;
import view.component.ResultPanel;
import view.SymmetricEncryption.KeyOptionsPanel;
import view.component.ActionPanel;

import javax.swing.*;
import java.awt.*;

public class SymmetricEncryptionPanel extends JPanel {

    public SymmetricEncryptionPanel() {
        setLayout(new BorderLayout(10, 10));

        KeyOptionsPanel keyOptionsPanel = new KeyOptionsPanel();
        InputPanel inputPanel = new InputPanel();
        ResultPanel resultPanel = new ResultPanel();
        ActionPanel actionPanel = new ActionPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, resultPanel);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);

        add(keyOptionsPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.SOUTH);
        add(splitPane, BorderLayout.CENTER);

        // Add event listeners for the encryption and decryption buttons from ActionPanel
        actionPanel.addEncryptListener(e -> encryptText(inputPanel, resultPanel));
        actionPanel.addDecryptListener(e -> decryptText(resultPanel));
    }

    private void encryptText(InputPanel inputPanel, ResultPanel resultPanel) {
        String text = inputPanel.getTextArea().getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter text to encrypt.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Simulate encryption process (replace with actual encryption if needed)
        String encryptedText = "Encrypted: " + text;
        resultPanel.getEncryptResultTextArea().setText(encryptedText);
    }

    private void decryptText(ResultPanel resultPanel) {
        String encryptedText = resultPanel.getEncryptResultTextArea().getText();
        if (encryptedText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please encrypt text first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Simulate decryption process (replace with actual decryption if needed)
        String decryptedText = encryptedText.replace("Encrypted: ", "");
        resultPanel.getDecryptResultTextArea().setText(decryptedText);
    }
}
