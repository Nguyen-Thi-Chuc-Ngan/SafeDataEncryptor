package view;

import view.component.ActionPanel;
import view.component.InputPanel;
import view.component.ResultPanel;
import view.classical.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassicalSymmetricEncryptionPanel extends JPanel {

    private JComboBox<String> algorithmComboBox;
    private JPanel algorithmPanel;
    private JComboBox<String> languageComboBox;
    private JComboBox<String> alphabetComboBox;
    private JButton genKeyButton;
    private HillCipherPanel hillCipherPanel;
    private AffineCipherPanel affineCipherPanel;

    public ClassicalSymmetricEncryptionPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new BorderLayout());

        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.setPreferredSize(new Dimension(590, 150));

        selectionPanel.add(createAlgorithmSelectionPanel());
        selectionPanel.add(createLanguageSelectionPanel());
        selectionPanel.add(createAlphabetSelectionPanel());

        genKeyButton = new JButton("Generate");
        genKeyButton.setPreferredSize(new Dimension(120, 40));
        selectionPanel.add(genKeyButton);
        // Tạo đường phân cách ngang sử dụng JSeparator
        JPanel separator = new JPanel();
        separator.setLayout(new BorderLayout());
        JSeparator verticalSeparator = new JSeparator(SwingConstants.VERTICAL);  // Đường phân cách ngang

        // Đặt đường phân cách vào JPanel
        separator.add(verticalSeparator, BorderLayout.CENTER);

        // Đặt chiều cao cho separator ngang
        separator.setPreferredSize(new Dimension(3, 10)); // Đặt chiều cao cho đường phân cách ngang

        algorithmPanel = new JPanel();
        algorithmPanel.setLayout(new BoxLayout(algorithmPanel, BoxLayout.Y_AXIS));
        algorithmPanel.setPreferredSize(new Dimension(590, 200));

        // Thêm đường phân cách ngang
        JPanel horizontalSeparatorPanel = new JPanel();
        horizontalSeparatorPanel.setLayout(new BorderLayout());
        JSeparator horizontalSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        horizontalSeparatorPanel.add(horizontalSeparator, BorderLayout.CENTER);
        // Đảm bảo đường phân cách có màu sắc
        horizontalSeparator.setBackground(Color.GRAY);
        horizontalSeparator.setForeground(Color.GRAY);

        comboPanel.add(selectionPanel, BorderLayout.WEST);
        comboPanel.add(separator, BorderLayout.CENTER);
        comboPanel.add(algorithmPanel, BorderLayout.EAST);
        comboPanel.add(horizontalSeparatorPanel, BorderLayout.SOUTH);

        InputPanel inputPanel = new InputPanel();
        ResultPanel resultPanel = new ResultPanel();
        ActionPanel actionPanel = new ActionPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, resultPanel);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);


        // Chắc chắn rằng comboPanel, horizontalSeparatorPanel và splitPane được thêm đúng cách
        add(comboPanel, BorderLayout.NORTH);  // Đặt comboPanel ở trên cùn
        add(splitPane, BorderLayout.CENTER);  // Đặt splitPane ở trung tâm để nó chiếm hết không gian còn lại
        add(actionPanel, BorderLayout.SOUTH);  // Đặt actionPanel ở dưới cùng

        updatePanel();
        languageComboBox.addActionListener(e -> updateAlphabetSelectionPanel((String) algorithmComboBox.getSelectedItem()));
        algorithmComboBox.addActionListener(e -> updatePanel());

        genKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                String selectedAlphabet = (String) alphabetComboBox.getSelectedItem();
                String selectedLanguage = (String) languageComboBox.getSelectedItem();
                switch (selectedAlgorithm) {
                    case "Hill":
                        if (hillCipherPanel != null) {
                            System.out.println("Generating key...");
                            hillCipherPanel.genKey(selectedLanguage);
                        }
                        break;
                    case "Affine":
                        System.out.println("Generating key for Affine Cipher...");
                        if (affineCipherPanel != null) {

                            affineCipherPanel.generateKeys();
                        }
                        break;
                }
            }
        });
        actionPanel.addEncryptListener(e -> encryptText(inputPanel, resultPanel));
        actionPanel.addDecryptListener(e -> decryptText(resultPanel));
    }

    private JPanel createAlphabetSelectionPanel() {
        String[] alphabets = {
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        };
        alphabetComboBox = createComboBox(alphabets);

        JPanel alphabetPanel = new JPanel();
        alphabetPanel.setLayout(new BoxLayout(alphabetPanel, BoxLayout.X_AXIS));
        alphabetPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        alphabetPanel.add(new JLabel("Alphabet:"));
        alphabetPanel.add(Box.createHorizontalStrut(15));
        alphabetPanel.add(alphabetComboBox);

        return alphabetPanel;
    }

    private HillCipherPanel findHillCipherPanel() {
        for (Component comp : algorithmPanel.getComponents()) {
            if (comp instanceof HillCipherPanel) {
                return (HillCipherPanel) comp;
            }
        }
        return null;
    }

    private AffineCipherPanel findAffineCipherPanel() {
        for (Component comp : algorithmPanel.getComponents()) {
            if (comp instanceof AffineCipherPanel) {
                return (AffineCipherPanel) comp;
            }
        }
        return null;
    }


    private JPanel createAlgorithmSelectionPanel() {
        String[] algorithms = {"Hill", "Substitution", "Vigence", "Affine", "Transposition"};
        algorithmComboBox = createComboBox(algorithms); // Lưu giá trị trả về vào algorithmComboBox

        JPanel algorithmPanel = new JPanel();
        algorithmPanel.setLayout(new BoxLayout(algorithmPanel, BoxLayout.X_AXIS));
        algorithmPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        algorithmPanel.add(new JLabel("Algorithm:"));
        algorithmPanel.add(Box.createHorizontalStrut(10));
        algorithmPanel.add(algorithmComboBox);

        algorithmPanel.revalidate();
        algorithmPanel.repaint();

        return algorithmPanel;
    }

    private JPanel createLanguageSelectionPanel() {
        String[] languages = {"English", "Vietnamese"};
        languageComboBox = createComboBox(languages);

        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new BoxLayout(languagePanel, BoxLayout.X_AXIS));
        languagePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        languagePanel.add(new JLabel("Language:"));
        languagePanel.add(Box.createHorizontalStrut(10));
        languagePanel.add(languageComboBox);
        languagePanel.revalidate();
        languagePanel.repaint();

        return languagePanel;
    }

    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setPreferredSize(new Dimension(150, 25));
        return comboBox;
    }

    private void updatePanel() {
        String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
        String selectedLanguage = (String) languageComboBox.getSelectedItem();
        algorithmPanel.removeAll();   // Xóa các thành phần cũ

        // Tạo một đối tượng HillCipherPanel mới mỗi khi thuật toán được thay đổi
        switch (selectedAlgorithm) {
            case "Hill":
                hillCipherPanel = new HillCipherPanel();  // Khởi tạo HillCipherPanel
                algorithmPanel.add(hillCipherPanel);  // Thêm vào giao diện
                break;
            case "Substitution":
                algorithmPanel.add(new SubstitutionCipherPanel());
                break;
            case "Vigence":
                algorithmPanel.add(new VigeneceCipherPanel());
                break;
            case "Affine":
                affineCipherPanel = new AffineCipherPanel();  // Khởi tạo AffineCipherPanel
                algorithmPanel.add(affineCipherPanel);
                break;
            case "Transposition":
                algorithmPanel.add(new TranspositionCipherPanel());
                break;
        }

        algorithmPanel.revalidate();
        algorithmPanel.repaint();  // Cập nhật lại giao diện

        // Cập nhật lại bảng chữ cái khi thay đổi thuật toán
        updateAlphabetSelectionPanel(selectedAlgorithm);
    }

    private void updateAlphabetSelectionPanel(String selectedAlgorithm) {
        String selectedLanguage = (String) languageComboBox.getSelectedItem();
        // Cập nhật lại bảng chữ cái nếu cần thiết
        String[] alphabets;

        if ("Hill".equals(selectedAlgorithm)) {
            // Kiểm tra ngôn ngữ để cập nhật bảng chữ cái tương ứng
            if ("English".equals(selectedLanguage)) {
                alphabets = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ"};
            } else if ("Vietnamese".equals(selectedLanguage)) {
                alphabets = new String[]{"AĂÂBCDĐEFGHIJKLMNOPQRSTUƯVXY"};
            } else {
                alphabets = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ"};  // Giá trị mặc định cho trường hợp ngôn ngữ không xác định
            }
        } else if ("Affine".equals(selectedAlgorithm)) {
            // Kiểm tra ngôn ngữ để cập nhật bảng chữ cái cho Affine Cipher
            if ("English".equals(selectedLanguage)) {
                alphabets = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"};
            } else if ("Vietnamese".equals(selectedLanguage)) {
                alphabets = new String[]{"AĂÂBCDĐEÊFGHIJKLMNOÔƠPQRSTUƯVXY", "aăâbcdeêfghijklmnoôơpqrstuưvwxyzAĂÂBCDĐEÊFGHIJKLMNOÔƠPQRSTUƯVXY"};
            } else {
                alphabets = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"};  // Giá trị mặc định
            }
        }
        else {
            // Nếu thuật toán không phải Hill, xử lý theo thuật toán đã chọn
            switch (selectedAlgorithm) {
                case "Substitution":
                    alphabets = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ZABCDEFGHIJKLMNOPQRSTUVWXY", "ABCDEFGHIJKLMNOPQRSTUVWXYZ_", "_ABCDEFGHIJKLMNOPQRSTUVWXYZ"};
                    break;
                case "Vigenère":
                    alphabets = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"};
                    break;
                case "Affine":
                    alphabets = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"};
                    break;
                case "Transposition":
                    alphabets = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ_123456"};
                    break;
                default:
                    alphabets = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ"}; // Giá trị mặc định cho trường hợp thuật toán không xác định
                    break;
            }
        }

        // Cập nhật danh sách lựa chọn bảng chữ cái trong ComboBox
        alphabetComboBox.setModel(new DefaultComboBoxModel<>(alphabets));
    }

    private void encryptText(InputPanel inputPanel, ResultPanel resultPanel) {
        String text = inputPanel.getTextArea().getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter text to encrypt.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedLanguage = (String) languageComboBox.getSelectedItem();
        String selectedAlphabet = (String) alphabetComboBox.getSelectedItem();
        String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();  // Lấy thuật toán chọn từ ComboBox

        // Xử lý văn bản trước khi mã hóa (chuyển sang chữ hoa và loại bỏ dấu cách)

        // Xử lý mã hóa tùy theo thuật toán đã chọn
        String encryptedText = "";
        switch (selectedAlgorithm) {
            case "Affine":
                encryptedText =  encryptWithAffineCipher(text, selectedAlphabet);


                break;
//            case "Transposition":
//                encryptedText = transpositionEncrypt(formattedText, selectedAlphabet);
//                break;
            case "Hill":
                encryptedText = encryptWithHillCipher(text, selectedLanguage);
                // Tìm HillCipherPanel trong algorithmPanel

                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown algorithm selected.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Hiển thị kết quả mã hóa trong resultPanel
        resultPanel.getEncryptResultTextArea().setText(encryptedText);
    }

    private String encryptWithAffineCipher(String text, String selectedAlphabet) {
        AffineCipherPanel affineCipherPanel = findAffineCipherPanel();
        String encryptedText = "";
        if(affineCipherPanel!= null) {
            encryptedText = affineCipherPanel.encrypt(text, selectedAlphabet);
        }
        else {
            JOptionPane.showMessageDialog(this, "Please generate the key first.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return encryptedText;
    }

    private String encryptWithHillCipher(String text, String selectedLanguage) {
        HillCipherPanel hillCipherPanel = findHillCipherPanel();
        String encryptedText = "";
        if (hillCipherPanel != null && hillCipherPanel.isKeyGenerated) {
            // Gọi phương thức mã hóa trong HillCipherPanel
            encryptedText = hillCipherPanel.encryptText(text, hillCipherPanel.keyMatrix, selectedLanguage);
        }
        else {
            JOptionPane.showMessageDialog(this, "Please generate the key first.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return encryptedText;
    }


    private void decryptText(ResultPanel resultPanel) {
        String encryptedText = resultPanel.getEncryptResultTextArea().getText();
        System.out.println(encryptedText);

        if (encryptedText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please encrypt text first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();  // Lấy thuật toán đã chọn từ ComboBox
        String selectedLanguage = (String) languageComboBox.getSelectedItem();
        String selectedAlphabet = (String) alphabetComboBox.getSelectedItem();
        switch (selectedAlgorithm) {
            case "Affine":
                AffineCipherPanel affineCipherPanel = findAffineCipherPanel();
                if (affineCipherPanel != null) {
                    // Giải mã với thuật toán Affine
                    String decryptedAffineText = affineCipherPanel.decrypt(encryptedText, selectedAlphabet);
                    resultPanel.getDecryptResultTextArea().setText(decryptedAffineText);
                } else {
                    JOptionPane.showMessageDialog(this, "Please generate the key first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Hill":
                HillCipherPanel hillCipherPanel = findHillCipherPanel();
                if (hillCipherPanel != null && hillCipherPanel.isKeyGenerated) {
                    // Giải mã với thuật toán Hill Cipher
                    String decryptedHillText = hillCipherPanel.decryptText(encryptedText, hillCipherPanel.keyMatrix, selectedLanguage);
                    resultPanel.getDecryptResultTextArea().setText(decryptedHillText);
                } else {
                    JOptionPane.showMessageDialog(this, "Please generate the key first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Substitution":
                // Giải mã với thuật toán Substitution Cipher
                SubstitutionCipherPanel substitutionCipherPanel = findSubstitutionCipherPanel();
                if (substitutionCipherPanel != null) {
                    String decryptedSubstitutionText = substitutionCipherPanel.decrypt(encryptedText);
                    resultPanel.getDecryptResultTextArea().setText(decryptedSubstitutionText);
                } else {
                    JOptionPane.showMessageDialog(this, "Please generate the key first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Vigence":
                // Giải mã với thuật toán Vigenère Cipher
                VigeneceCipherPanel vigeneceCipherPanel = findVigeneceCipherPanel();
                if (vigeneceCipherPanel != null) {
                    String decryptedVigeneceText = vigeneceCipherPanel.decrypt(encryptedText);
                    resultPanel.getDecryptResultTextArea().setText(decryptedVigeneceText);
                } else {
                    JOptionPane.showMessageDialog(this, "Please generate the key first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Transposition":
                // Giải mã với thuật toán Transposition Cipher
                TranspositionCipherPanel transpositionCipherPanel = findTranspositionCipherPanel();
                if (transpositionCipherPanel != null) {
                    String decryptedTranspositionText = transpositionCipherPanel.decrypt(encryptedText);
                    resultPanel.getDecryptResultTextArea().setText(decryptedTranspositionText);
                } else {
                    JOptionPane.showMessageDialog(this, "Please generate the key first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            default:
                JOptionPane.showMessageDialog(this, "Unknown algorithm selected.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private VigeneceCipherPanel findVigeneceCipherPanel() {
        return null;
    }

    private TranspositionCipherPanel findTranspositionCipherPanel() {
        return null;
    }

    private SubstitutionCipherPanel findSubstitutionCipherPanel() {
        return null;
    }
}