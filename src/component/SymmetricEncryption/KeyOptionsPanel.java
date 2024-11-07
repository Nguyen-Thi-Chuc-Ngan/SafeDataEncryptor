package component.SymmetricEncryption;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KeyOptionsPanel extends JPanel {
    public JComboBox<String> keyLengthComboBox;
    public JComboBox<String> algorithmComboBox;
    public JComboBox<String> modeComboBox;
    public JComboBox<String> paddingComboBox;
    public JButton generateKeyButton;
    public JTextField keyField;

    public KeyOptionsPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        keyLengthComboBox = new JComboBox<>(new String[]{"128", "192", "256"});
        keyLengthComboBox.setPreferredSize(new Dimension(60, 25));

        algorithmComboBox = new JComboBox<>(new String[]{"AES", "DES", "3DES"});
        algorithmComboBox.setPreferredSize(new Dimension(100, 25));

        modeComboBox = new JComboBox<>(new String[]{"GCM", "ECB", "KWP", "CBC", "OFB", "KW", "CFB"});
        modeComboBox.setPreferredSize(new Dimension(80, 25));

        paddingComboBox = new JComboBox<>(new String[]{"PKCS5Padding", "NoPadding"});
        paddingComboBox.setPreferredSize(new Dimension(180, 25));

        generateKeyButton = new JButton("Tạo KEY");
        keyField = new JTextField(29);
        keyField.setEditable(true); // Make the keyField not editable
        keyField.setPreferredSize(new Dimension(400, 30));

        // Hướng dẫn nhập khóa
        ImageIcon infoIcon = new ImageIcon(getClass().getResource("/image/information.png"));
        Image img = infoIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel keyInstructionLabel = new JLabel(new ImageIcon(img));
        keyInstructionLabel.setToolTipText("Bạn có thể nhập khóa ở đây nếu muốn.");

        keyInstructionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(KeyOptionsPanel.this,
                        "Bạn có thể nhập khóa ở đây nếu muốn. Nếu không, hãy click nút tạo key khóa sẽ được tạo tự động.",
                        "Hướng dẫn nhập khóa",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        keyField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (!keyField.isEditable()) {
                    e.consume(); // Ngừng hành động khi người dùng cố gắng nhập vào
                    JOptionPane optionPane = new JOptionPane(
                            "Khóa không thể chỉnh sửa sau khi tạo.",
                            JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog(KeyOptionsPanel.this, "Cảnh báo");
                    dialog.setLocationRelativeTo(KeyOptionsPanel.this); // Đặt vị trí thông báo ở giữa màn hình
                    dialog.setVisible(true); // Hiển thị thông báo
                }
            }
        });

        // Thêm sự kiện cho nút "Tạo KEY"
        generateKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateKey(); // Gọi hàm tạo khóa khi nhấn nút "Tạo KEY"
            }
        });

        add(new JLabel("Thuật toán:"));
        add(algorithmComboBox);
        add(new JLabel("Độ dài khóa:"));
        add(keyLengthComboBox);
        add(new JLabel("Chế độ:"));
        add(modeComboBox);
        add(new JLabel("Padding:"));
        add(paddingComboBox);
        add(generateKeyButton);
        add(keyField);
        add(keyInstructionLabel);
    }

    // Hàm để tạo khóa dựa trên các tham số người dùng chọn
    public void generateKey() {
        try {
            String algorithm = (String) algorithmComboBox.getSelectedItem();
            int keyLength = Integer.parseInt((String) keyLengthComboBox.getSelectedItem());
            String padding = (String) paddingComboBox.getSelectedItem();

            KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
            keyGen.init(keyLength); // Đặt độ dài khóa

            // Tạo khóa bí mật
            SecretKey secretKey = keyGen.generateKey();

            // Hiển thị khóa vào keyField (chuyển đổi khóa thành chuỗi base64 hoặc hex)
            String key = bytesToHex(secretKey.getEncoded()); // Mã hóa khóa thành chuỗi Hex
            keyField.setText(key);
            keyField.setEditable(false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi tạo khóa: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Chuyển đổi byte array thành chuỗi hex để hiển thị
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
