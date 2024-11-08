package view.component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InputPanel extends JPanel {

    private JTextArea textArea;

    public InputPanel() {
        setLayout(new BorderLayout(10, 10));

        // File selection panel
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel fileLabel = new JLabel("Select file:");
        JButton fileChooserButton = new JButton("Load file");
        fileChooserButton.setBackground(new Color(0, 123, 255));
        fileChooserButton.setForeground(Color.WHITE);
        fileChooserButton.setFocusPainted(false);
        filePanel.add(fileLabel);
        filePanel.add(fileChooserButton);

        // Text input panel
        JPanel textInputPanel = new JPanel(new BorderLayout());
        textInputPanel.setBorder(new TitledBorder("Enter Text"));
        textArea = new JTextArea(4, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        textInputPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        add(filePanel, BorderLayout.NORTH);
        add(textInputPanel, BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
