package component.SymmetricEncryption;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ResultPanel extends JPanel {
    public JTextArea encryptResultTextArea;
    public JTextArea decryptResultTextArea;

    public ResultPanel() {
        setLayout(new GridLayout(2, 1, 10, 10));
        setBorder(new TitledBorder("Kết quả"));

        encryptResultTextArea = new JTextArea(4, 30);
        encryptResultTextArea.setLineWrap(true);
        encryptResultTextArea.setWrapStyleWord(true);
        encryptResultTextArea.setEditable(false);

        decryptResultTextArea = new JTextArea(4, 30);
        decryptResultTextArea.setLineWrap(true);
        decryptResultTextArea.setWrapStyleWord(true);
        decryptResultTextArea.setEditable(false);

        JPanel encryptResultPanel = new JPanel(new BorderLayout());
        TitledBorder encryptTitleBorder = new TitledBorder("Mã hóa");
        encryptTitleBorder.setTitleJustification(TitledBorder.CENTER); // Căn giữa tiêu đề
        encryptResultPanel.setBorder(encryptTitleBorder);
        encryptResultPanel.add(new JScrollPane(encryptResultTextArea), BorderLayout.CENTER);

        JPanel decryptResultPanel = new JPanel(new BorderLayout());
        TitledBorder decryptTitleBorder = new TitledBorder("Giải mã");
        decryptTitleBorder.setTitleJustification(TitledBorder.CENTER); // Căn giữa tiêu đề
        decryptResultPanel.setBorder(decryptTitleBorder);
        decryptResultPanel.add(new JScrollPane(decryptResultTextArea), BorderLayout.CENTER);

        add(encryptResultPanel);
        add(decryptResultPanel);
    }
}
