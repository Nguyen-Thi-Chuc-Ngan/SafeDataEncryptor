import view.*;

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
        tabbedPane.addTab("Mã hóa đối xứng", new SymmetricEncryptionPanel());
        tabbedPane.addTab("Mã hóa bất đối xứng", new AsymmetricEncryptionPanel());
        tabbedPane.addTab("Chữ ký điện tử", new DigitalSignaturePanel());
        tabbedPane.addTab("Hướng dẫn sử dụng", new InstructionsForUse());
        tabbedPane.addTab("Thông tin", new CreatorInformation());

        frame.add(tabbedPane);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
