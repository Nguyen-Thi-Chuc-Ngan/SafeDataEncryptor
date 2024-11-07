package component.AsymmetricEncryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgorithmPanel extends JPanel {
    private JComboBox<String> algorithmComboBox;
    private JComboBox<String> keyLengthField;
    private JComboBox<String> modeComboBox;
    private JComboBox<String> paddingComboBox;
    private JTextField publicKeyField;
    private JTextField privateKeyField;

    public AlgorithmPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel chứa Thuật toán và Độ dài khóa trên cùng một hàng
        JPanel algorithmAndKeyLengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel algorithmLabel = new JLabel("Algorithm:");
        algorithmComboBox = new JComboBox<>(new String[]{"RSA", "DSA", "ElGamal"});
        JLabel keyLengthLabel = new JLabel("Key Size:");

        String[] keyOptions = {"128", "256", "512", "1024", "2048"};
        keyLengthField = new JComboBox<>(keyOptions);
        keyLengthField.setPreferredSize(new Dimension(140, 30));

        algorithmComboBox.setPreferredSize(new Dimension(200, 30));

        // Chế độ và Padding selectors
        JLabel modeLabel = new JLabel("Mode:");
        modeComboBox = new JComboBox<>(new String[]{"ECB", "CBC", "CFB", "OFB"});
        modeComboBox.setPreferredSize(new Dimension(150, 30));

        JLabel paddingLabel = new JLabel("Padding:");
        paddingComboBox = new JComboBox<>(new String[]{"PKCS1Padding", "OAEPWithSHA-256AndMGF1Padding", "NoPadding"});
        paddingComboBox.setPreferredSize(new Dimension(250, 30));

        // Thêm một JLabel với icon "i" (thông tin)
        ImageIcon infoIcon = new ImageIcon(getClass().getResource("/image/information.png"));
        Image img = infoIcon.getImage();
        Image resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        JLabel keyInstructionLabel = new JLabel(resizedIcon);
        keyInstructionLabel.setToolTipText("Bạn có thể nhập khóa ở đây nếu muốn.");

        keyInstructionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(AlgorithmPanel.this,
                        "Bạn có thể nhập khóa ở đây nếu muốn. Nếu không, hãy click nút tạo key khóa sẽ được tạo tự động.",
                        "Hướng dẫn nhập khóa", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Thêm các thành phần vào panel
        algorithmAndKeyLengthPanel.add(algorithmLabel);
        algorithmAndKeyLengthPanel.add(algorithmComboBox);
        algorithmAndKeyLengthPanel.add(keyLengthLabel);
        algorithmAndKeyLengthPanel.add(keyLengthField);
        algorithmAndKeyLengthPanel.add(modeLabel);
        algorithmAndKeyLengthPanel.add(modeComboBox);
        algorithmAndKeyLengthPanel.add(paddingLabel);
        algorithmAndKeyLengthPanel.add(paddingComboBox);
        algorithmAndKeyLengthPanel.add(keyInstructionLabel);

        // Panel chứa Khóa công khai và Khóa riêng tư nằm cùng một hàng
        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        keyPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JLabel publicKeyLabel = new JLabel("Khóa công khai:");
        publicKeyField = new JTextField(40);
        publicKeyField.setEditable(true);
        publicKeyField.setPreferredSize(new Dimension(500, 30));

        JLabel privateKeyLabel = new JLabel("Khóa riêng tư:");
        privateKeyField = new JTextField(40);
        privateKeyField.setEditable(true);
        privateKeyField.setPreferredSize(new Dimension(500, 30));

        // Nút Tạo Khóa
        JButton generateKeysButton = new JButton("Tạo khóa");
        generateKeysButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateKeysButton.setBackground(new Color(0, 123, 255));
        generateKeysButton.setForeground(Color.WHITE);
        generateKeysButton.setFocusPainted(false);

        generateKeysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateKeys();
            }
        });

        // Thêm KeyListener cho publicKeyField và privateKeyField
        publicKeyField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (!publicKeyField.isEditable()) {
                    e.consume(); // Ngừng hành động khi người dùng cố gắng nhập vào
                    JOptionPane optionPane = new JOptionPane(
                            "Khóa công khai không thể chỉnh sửa sau khi tạo.",
                            JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog(AlgorithmPanel.this, "Cảnh báo");
                    dialog.setLocationRelativeTo(AlgorithmPanel.this); // Đặt vị trí thông báo ở giữa màn hình
                    dialog.setVisible(true); // Hiển thị thông báo
                }
            }
        });
        privateKeyField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (!privateKeyField.isEditable()) {
                    e.consume(); // Ngừng hành động khi người dùng cố gắng nhập vào
                    JOptionPane optionPane = new JOptionPane(
                            "Khóa riêng tư không thể chỉnh sửa sau khi tạo.",
                            JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog(AlgorithmPanel.this, "Cảnh báo");
                    dialog.setLocationRelativeTo(AlgorithmPanel.this); // Đặt vị trí thông báo ở giữa màn hình
                    dialog.setVisible(true); // Hiển thị thông báo
                }
            }
        });



        keyPanel.add(publicKeyLabel);
        keyPanel.add(publicKeyField);
        keyPanel.add(privateKeyLabel);
        keyPanel.add(privateKeyField);
        keyPanel.add(generateKeysButton);

        // Thêm các panel con vào panel chính
        add(algorithmAndKeyLengthPanel);
        add(keyPanel);
    }

    public void generateKeys() {
        // Giả lập chức năng tạo khóa
        String publicKey = "Công khai: ABC123XYZ";
        String privateKey = "Riêng tư: XYZ123ABC";

        publicKeyField.setText(publicKey);
        privateKeyField.setText(privateKey);
        publicKeyField.setEditable(false);
        privateKeyField.setEditable(false);
    }
}
