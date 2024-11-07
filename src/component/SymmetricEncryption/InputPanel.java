package component.SymmetricEncryption;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InputPanel extends JPanel {
    public JTextArea textArea;
    public JButton fileChooserButton;

    public InputPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        fileChooserButton = new JButton("Chọn file");
        filePanel.add(new JLabel("Chọn file:"));
        filePanel.add(fileChooserButton);

        JPanel textInputPanel = new JPanel(new BorderLayout());
        textInputPanel.setBorder(new TitledBorder("Nhập văn bản"));
        textArea = new JTextArea(4, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textInputPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        add(filePanel, BorderLayout.NORTH);
        add(textInputPanel, BorderLayout.CENTER);
    }
}