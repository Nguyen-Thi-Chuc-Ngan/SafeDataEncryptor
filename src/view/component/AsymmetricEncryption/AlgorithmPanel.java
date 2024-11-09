package view.component.AsymmetricEncryption;

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

        // Panel containing Algorithm and Key Size on the same row
        JPanel algorithmAndKeyLengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel algorithmLabel = new JLabel("Algorithm:");
        algorithmComboBox = new JComboBox<>(new String[]{"RSA", "DSA", "ElGamal"});
        JLabel keyLengthLabel = new JLabel("Key Size:");

        String[] keyOptions = {"128", "256", "512", "1024", "2048"};
        keyLengthField = new JComboBox<>(keyOptions);
        keyLengthField.setPreferredSize(new Dimension(140, 40));

        algorithmComboBox.setPreferredSize(new Dimension(200, 40));

        // Mode and Padding selectors
        JLabel modeLabel = new JLabel("Mode:");
        modeComboBox = new JComboBox<>(new String[]{"ECB", "CBC", "CFB", "OFB"});
        modeComboBox.setPreferredSize(new Dimension(150, 40));

        JLabel paddingLabel = new JLabel("Padding:");
        paddingComboBox = new JComboBox<>(new String[]{"PKCS1Padding", "OAEPWithSHA-256AndMGF1Padding", "NoPadding"});
        paddingComboBox.setPreferredSize(new Dimension(250, 40));

        // Adding a JLabel with an "i" (information) icon
        ImageIcon infoIcon = new ImageIcon(getClass().getResource("/image/information.png"));
        Image img = infoIcon.getImage();
        Image resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        JLabel keyInstructionLabel = new JLabel(resizedIcon);
        keyInstructionLabel.setToolTipText("You can enter the key here if desired.");

        keyInstructionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(AlgorithmPanel.this,
                        "You can enter the key here if desired. Otherwise, click the generate key button to create it automatically.",
                        "Key Entry Instructions", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Adding components to the panel
        algorithmAndKeyLengthPanel.add(algorithmLabel);
        algorithmAndKeyLengthPanel.add(algorithmComboBox);
        algorithmAndKeyLengthPanel.add(keyLengthLabel);
        algorithmAndKeyLengthPanel.add(keyLengthField);
        algorithmAndKeyLengthPanel.add(modeLabel);
        algorithmAndKeyLengthPanel.add(modeComboBox);
        algorithmAndKeyLengthPanel.add(paddingLabel);
        algorithmAndKeyLengthPanel.add(paddingComboBox);
        algorithmAndKeyLengthPanel.add(keyInstructionLabel);

        // Panel containing Public Key and Private Key on the same row
        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        keyPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JLabel publicKeyLabel = new JLabel("Public Key:");
        publicKeyField = new JTextField(40);
        publicKeyField.setEditable(true);
        publicKeyField.setPreferredSize(new Dimension(500, 40));

        JLabel privateKeyLabel = new JLabel("Private Key:");
        privateKeyField = new JTextField(40);
        privateKeyField.setEditable(true);
        privateKeyField.setPreferredSize(new Dimension(500, 40));

        // Generate Keys button
        JButton generateKeysButton = new JButton("Generate");
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

        // Adding KeyListener for publicKeyField and privateKeyField
        publicKeyField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (!publicKeyField.isEditable()) {
                    e.consume(); // Stop action when user tries to input
                    JOptionPane optionPane = new JOptionPane(
                            "Public key cannot be edited after creation.",
                            JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog(AlgorithmPanel.this, "Warning");
                    dialog.setLocationRelativeTo(AlgorithmPanel.this); // Set dialog position to center of screen
                    dialog.setVisible(true); // Display dialog
                }
            }
        });
        privateKeyField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (!privateKeyField.isEditable()) {
                    e.consume(); // Stop action when user tries to input
                    JOptionPane optionPane = new JOptionPane(
                            "Private key cannot be edited after creation.",
                            JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog(AlgorithmPanel.this, "Warning");
                    dialog.setLocationRelativeTo(AlgorithmPanel.this); // Set dialog position to center of screen
                    dialog.setVisible(true); // Display dialog
                }
            }
        });

        keyPanel.add(publicKeyLabel);
        keyPanel.add(publicKeyField);
        keyPanel.add(privateKeyLabel);
        keyPanel.add(privateKeyField);
        keyPanel.add(generateKeysButton);

        // Add sub-panels to main panel
        add(algorithmAndKeyLengthPanel);
        add(keyPanel);
    }

    public void generateKeys() {
        // Simulating key generation functionality
        String publicKey = "Public: ABC123XYZ";
        String privateKey = "Private: XYZ123ABC";

        publicKeyField.setText(publicKey);
        privateKeyField.setText(privateKey);
        publicKeyField.setEditable(false);
        privateKeyField.setEditable(false);
    }
}
