package view.classical;

import algorithm.classical.HillCipher;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.security.SecureRandom;

import static algorithm.classical.HillCipher.getMod;

public class HillCipherPanel extends JPanel {

    private JComboBox<String> matrixSizeComboBox;
    private JPanel matrixPanel;
    private JLabel errorLabel;
    private JTextField[][] matrixFields;
    public int[][] keyMatrix;
    String selectedSize;
    public boolean isKeyGenerated = false;
    String selectedLanguage;

    public HillCipherPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        add(new JLabel("Nhập khóa Hill Cipher:"));

        String[] matrixSizes = {"2x2", "3x3", "4x4"};
        matrixSizeComboBox = new JComboBox<>(matrixSizes);
        matrixSizeComboBox.addActionListener(e -> updateMatrixPanel());

        add(matrixSizeComboBox);

        matrixPanel = new JPanel();
        matrixPanel.setPreferredSize(new Dimension(590, 150));
        matrixPanel.setLayout(new GridLayout(2, 2));

        add(matrixPanel);

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        add(errorLabel);

        updateMatrixPanel();
    }


    // Kiểm tra xem giá trị nhập vào có phải là số hợp lệ không
    private void validateInput(JTextField textField) {
        String text = textField.getText().trim();

        try {
            if (!text.isEmpty() && !text.matches("-?\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập số nguyên hợp lệ.",
                        "Lỗi nhập liệu",
                        JOptionPane.ERROR_MESSAGE);
                textField.setBackground(Color.PINK);
            } else {
                textField.setBackground(Color.WHITE);
                updateKeyMatrix();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập số hợp lệ.",
                    "Lỗi nhập liệu",
                    JOptionPane.ERROR_MESSAGE);
            textField.setBackground(Color.PINK);
        }
    }

    // Cập nhật keyMatrix khi tất cả các trường nhập đều hợp lệ
    private void updateKeyMatrix() {
        boolean valid = true;
        int rows = keyMatrix.length;
        int cols = keyMatrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String value = matrixFields[i][j].getText().trim();
                try {
                    int intValue = Integer.parseInt(value);
                    keyMatrix[i][j] = intValue;
                } catch (NumberFormatException e) {
                    valid = false;
                    break;
                }
            }
        }

        if (valid && isValidKeyMatrix(keyMatrix, selectedLanguage)) {
            isKeyGenerated = true;
            errorLabel.setText("");
        } else {
            errorLabel.setText("Vui lòng nhập ma trận khóa hợp lệ.");
            isKeyGenerated = false;
        }
    }

    public void updateMatrixPanel() {
        selectedSize = (String) matrixSizeComboBox.getSelectedItem();
        int size = Integer.parseInt(selectedSize.substring(0, 1));
        keyMatrix = new int[size][size];

        matrixPanel.removeAll();
        matrixPanel.setLayout(new GridLayout(size, size));

        matrixFields = new JTextField[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JTextField textField = new JTextField(5);
                textField.setHorizontalAlignment(JTextField.CENTER);
                matrixFields[i][j] = textField;
                textField.getDocument().addDocumentListener(new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                        validateInput(textField);
                    }

                    public void removeUpdate(DocumentEvent e) {
                        validateInput(textField);
                    }

                    public void changedUpdate(DocumentEvent e) {
                        validateInput(textField);
                    }
                });
                matrixPanel.add(textField);
            }
        }

        matrixPanel.revalidate();
        matrixPanel.repaint();
    }

    // Tạo ma trận khóa ngẫu nhiên
    public void genKey(String selectedLanguage) {
        int rows = 0, cols = 0;

        this.selectedLanguage = selectedLanguage;

        switch (selectedSize) {
            case "2x2":
                rows = 2;
                cols = 2;
                break;
            case "3x3":
                rows = 3;
                cols = 3;
                break;
            case "4x4":
                rows = 4;
                cols = 4;
                break;
        }

        keyMatrix = new int[rows][cols];
        SecureRandom random = new SecureRandom();
        do {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    keyMatrix[i][j] = random.nextInt(getMod(selectedLanguage));
                    matrixFields[i][j].setText(String.valueOf(keyMatrix[i][j]));
                }
            }
        } while (!isValidKeyMatrix(keyMatrix, selectedLanguage));

        isKeyGenerated = true;
    }

    private boolean isValidKeyMatrix(int[][] matrix, String selectedLanguage) {
        int det = determinant(matrix, selectedLanguage) % getMod(selectedLanguage);
        if (det < 0) det += getMod(selectedLanguage);
        // Kiểm tra gcd(det, MOD) để đảm bảo có nghịch đảo
        return gcd(det, getMod(selectedLanguage)) == 1;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static int[] multiplyMatrix(int[][] key, int[] block, String selectedLanguage) {
        int n = key.length;
        int mod = getMod(selectedLanguage);  // Lấy giá trị modulo dựa trên ngôn ngữ
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = 0;
            for (int j = 0; j < n; j++) {
                result[i] += key[i][j] * block[j];
            }
            result[i] %= mod;  // Đảm bảo kết quả modulo
        }
        return result;
    }


    private static int determinant(int[][] matrix, String selectedLanguage) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }

        int det = 0;
        for (int i = 0; i < n; i++) {
            int[][] minor = new int[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                int colIndex = 0;
                for (int k = 0; k < n; k++) {
                    if (k == i) continue;
                    minor[j - 1][colIndex++] = matrix[j][k];
                }
            }
            det += Math.pow(-1, i) * matrix[0][i] * determinant(minor, selectedLanguage);
        }

        // Đảm bảo giá trị định thức không âm và modulo với getMod
        det = det % getMod(selectedLanguage);  // Cập nhật modulo sau khi tính toán
        return (det + getMod(selectedLanguage)) % getMod(selectedLanguage);
    }




    public static String encryptText(String plaintext, int[][] key, String selectedLanguage) {
        return HillCipher.encryptText(plaintext, key, selectedLanguage);
    }


    public static String decryptText(String ciphertext, int[][] key, String selectedLanguage) {
        return HillCipher.decryptText(ciphertext, key, selectedLanguage);
    }
}