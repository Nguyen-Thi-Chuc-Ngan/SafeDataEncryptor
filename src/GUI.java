import view.*;
import view.custom.Theme;

import javax.swing.*;

public class GUI {

    private JFrame frame;
    private JTabbedPane tabbedPane;

    public GUI() {
        frame = new JFrame("Tool mã hóa");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        init();

        frame.setVisible(true);
    }

    public void init() {
        tabbedPane = new JTabbedPane();

        // Thêm các tab, mỗi tab là một lớp riêng
        tabbedPane.addTab("Symmetric", new SymmetricEncryptionPanel());
        tabbedPane.addTab("Classical Symmetric", new ClassicalSymmetricEncryptionPanel());  // Tab mới cho các thuật toán mã hóa cổ điển
        tabbedPane.addTab("Asymmetric", new AsymmetricEncryptionPanel());
        tabbedPane.addTab("Hash", new HashPanel());
        tabbedPane.addTab("Digital Signature", new DigitalSignaturePanel());
        tabbedPane.addTab("Instructions For Use", new InstructionsForUse());
        tabbedPane.addTab("Creator Information", new CreatorInformation());

        frame.add(tabbedPane);
    }

    public static void main(String[] args) {
        Theme theme = new Theme();
        theme.setup();

        // Khởi động GUI
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
