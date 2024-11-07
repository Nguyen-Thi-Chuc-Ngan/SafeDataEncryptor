package component.AsymmetricEncryption;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JTextArea encryptResultTextArea;
    private JTextArea decryptResultTextArea;

    public ResultPanel() {
        setLayout(new GridLayout(2, 1, 10, 10));
        setBorder(new TitledBorder("Kết quả"));

        encryptResultTextArea = new JTextArea(4, 30);
        encryptResultTextArea.setLineWrap(true);
        encryptResultTextArea.setWrapStyleWord(true);
        encryptResultTextArea.setEditable(false);
        encryptResultTextArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        decryptResultTextArea = new JTextArea(4, 30);
        decryptResultTextArea.setLineWrap(true);
        decryptResultTextArea.setWrapStyleWord(true);
        decryptResultTextArea.setEditable(false);
        decryptResultTextArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        JPanel encryptResultPanel = createTextAreaPanel("Mã hóa", encryptResultTextArea);
        JPanel decryptResultPanel = createTextAreaPanel("Giải mã", decryptResultTextArea);

        add(encryptResultPanel);
        add(decryptResultPanel);
    }

    private JPanel createTextAreaPanel(String title, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout());
        TitledBorder border = new TitledBorder(title);
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(border);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }

    public JTextArea getEncryptResultTextArea() {
        return encryptResultTextArea;
    }

    public JTextArea getDecryptResultTextArea() {
        return decryptResultTextArea;
    }
}