package view.SymmetricEncryption;

import model.ComboBoxValues;
import strategy.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KeyOptionsPanel extends JPanel {
    private JComboBox<String> algorithmComboBox;
    private JComboBox<String> keyLengthComboBox;
    private JComboBox<String> modeComboBox;
    private JComboBox<String> paddingComboBox;
    private JButton generateKeyButton, saveKeyButton, loadKeyButton, chooseIvButton;
    private JTextField keyField, ivField;
    private EncryptionStrategy encryptionStrategy;
    private String iv = "";

    public KeyOptionsPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        // Initialize components
        algorithmComboBox = new JComboBox<>(new String[]{"AES", "DES", "3DES"});
        algorithmComboBox.setPreferredSize(new Dimension(100, 40));

        keyLengthComboBox = new JComboBox<>(new String[]{}); // Start with empty model
        keyLengthComboBox.setPreferredSize(new Dimension(80, 40));

        modeComboBox = new JComboBox<>(new String[]{}); // Start with empty model
        modeComboBox.setPreferredSize(new Dimension(80, 40));

        paddingComboBox = new JComboBox<>(new String[]{}); // Start with empty model
        paddingComboBox.setPreferredSize(new Dimension(160, 40));

        generateKeyButton = new JButton("Generate");
        saveKeyButton = new JButton("Save Key");
        loadKeyButton = new JButton("Load Key");
        chooseIvButton = new JButton("Choose IV");

        keyField = new JTextField(30);
        keyField.setEditable(true);
        keyField.setPreferredSize(new Dimension(400, 40));

        ivField = new JTextField(30);
        ivField.setEditable(false);
        ivField.setPreferredSize(new Dimension(400, 40));

        // Default strategy is AES
        encryptionStrategy = new AESStrategy();
        ComboBoxValues comboBoxValues = new ComboBoxValues("AES"); // Pass "AES" to the constructor here
        encryptionStrategy.configure(comboBoxValues);

        // Set ComboBox models with values from AES
        keyLengthComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues.getKeyLength()));
        modeComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues.getMode()));
        paddingComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues.getPadding()));

        // Set the selected items
        keyLengthComboBox.setSelectedItem(comboBoxValues.getKeyLength()[0]);
        modeComboBox.setSelectedItem(comboBoxValues.getMode()[0]);
        paddingComboBox.setSelectedItem(comboBoxValues.getPadding()[0]);

        // Action for algorithm selection
        algorithmComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEncryptionStrategy();
            }
        });

        // Add action for the "Generate KEY" button
        generateKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String generatedKey = encryptionStrategy.generateKey();
                keyField.setText(generatedKey);
                keyField.setEditable(false);
            }
        });

        // Save Key button action
        saveKeyButton.addActionListener(e -> saveKeyToFile());

        // Load Key button action
        loadKeyButton.addActionListener(e -> loadKeyFromFile());

        // Choose IV button action
        chooseIvButton.addActionListener(e -> chooseIv());
        
        // Key input guidance
        ImageIcon infoIcon = new ImageIcon(getClass().getResource("/image/information.png"));
        Image img = infoIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel keyInstructionLabel = new JLabel(new ImageIcon(img));
        keyInstructionLabel.setToolTipText("You can enter a key here if desired.");

        keyInstructionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(KeyOptionsPanel.this,
                        "You can enter a key here if desired. Otherwise, click the Generate Key button to create one automatically.",
                        "Key Input Guidance",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        keyField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (!keyField.isEditable()) {
                    e.consume();
                    JOptionPane optionPane = new JOptionPane(
                            "The key cannot be edited after generation.",
                            JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog(KeyOptionsPanel.this, "Warning");
                    dialog.setLocationRelativeTo(KeyOptionsPanel.this);
                    dialog.setVisible(true);
                }
            }
        });

        // Add components to the panel
        add(new JLabel("Algorithm:"));
        add(algorithmComboBox);
        add(new JLabel("Key Length:"));
        add(keyLengthComboBox);
        add(new JLabel("Mode:"));
        add(modeComboBox);
        add(new JLabel("Padding:"));
        add(paddingComboBox);
        add(generateKeyButton);
        add(saveKeyButton);
        add(loadKeyButton);
        add(chooseIvButton);
        add(keyField);
        add(keyInstructionLabel);
    }

    private void chooseIv() {
    }

    private void loadKeyFromFile() {

    }

    private void saveKeyToFile() {
    }

    private void updateEncryptionStrategy() {
        String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();

        // Tạo ComboBoxValues với thuật toán đã chọn
        ComboBoxValues comboBoxValues = new ComboBoxValues(selectedAlgorithm);

        // Cấu hình lại chiến lược mã hóa
        if (selectedAlgorithm.equals("AES")) {
            encryptionStrategy = new AESStrategy();
        } else if (selectedAlgorithm.equals("DES")) {
            encryptionStrategy = new DESStrategy();
        } else if (selectedAlgorithm.equals("3DES")) {
            encryptionStrategy = new TripleDESStrategy();
        }

        encryptionStrategy.configure(comboBoxValues); // Cấu hình lại giá trị cho ComboBoxValues

        // Cập nhật lại mô hình ComboBox với các giá trị mới
        keyLengthComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues.getKeyLength()));
        modeComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues.getMode()));
        paddingComboBox.setModel(new DefaultComboBoxModel<>(comboBoxValues.getPadding()));

        // Chọn giá trị mặc định từ ComboBoxValues
        keyLengthComboBox.setSelectedItem(comboBoxValues.getKeyLength()[0]);
        modeComboBox.setSelectedItem(comboBoxValues.getMode()[0]);
        paddingComboBox.setSelectedItem(comboBoxValues.getPadding()[0]);
    }
}
