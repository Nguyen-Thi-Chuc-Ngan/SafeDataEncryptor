package view.component.classical;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Random;

public class HillCipherPanel extends JPanel {
    private static final int MOD = 26;

    private JComboBox<String> matrixSizeComboBox;
    private JPanel matrixPanel;
    private JLabel errorLabel;
    private JTextField[][] matrixFields;
    public int[][] keyMatrix;
    String selectedSize;
    public boolean isKeyGenerated = false;

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

        if (valid) {
            isKeyGenerated = true;
            errorLabel.setText("");
        } else {
            errorLabel.setText("Vui lòng nhập ma trận khóa hợp lệ.");
            isKeyGenerated = false;
        }
    }

    // Cập nhật giao diện bảng ma trận khi kích thước ma trận thay đổi
    public void updateMatrixPanel() {
        selectedSize = (String) matrixSizeComboBox.getSelectedItem();
        int rows = 0, cols = 0;

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

        matrixPanel.removeAll();
        matrixPanel.setLayout(new GridLayout(rows, cols));

        matrixFields = new JTextField[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JTextField textField = new JTextField(5);
                textField.setHorizontalAlignment(JTextField.CENTER);
                matrixFields[i][j] = textField;
                textField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        validateInput(textField);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        validateInput(textField);
                    }

                    @Override
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
    public void genKey() {
        int rows = 0, cols = 0;

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
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                keyMatrix[i][j] = random.nextInt(26);
                matrixFields[i][j].setText(String.valueOf(keyMatrix[i][j]));
            }
        }

        isKeyGenerated = true;
    }

    public static String encryptText(String plaintext, int[][] key) {
        plaintext = plaintext.toUpperCase().replaceAll(" ", "");
        int n = key.length;
        int padding = n - plaintext.length() % n;
        if (padding != n) {
            plaintext += "X".repeat(padding);
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += n) {
            int[] block = new int[n];
            for (int j = 0; j < n; j++) {
                block[j] = plaintext.charAt(i + j) - 'A';
            }
            int[] encryptedBlock = multiplyMatrix(key, block);
            for (int value : encryptedBlock) {
                ciphertext.append((char) (value + 'A'));
            }
        }
        return ciphertext.toString();
    }

    public static String decryptText(String ciphertext, int[][] key) {
        int determinant = determinant(key);
        int adjoint[][] = adjoint(key);
        int n = key.length;
        int[][] inverseKey = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseKey[i][j] = (adjoint[i][j] * determinant) % MOD;
                if (inverseKey[i][j] < 0) {
                    inverseKey[i][j] += MOD;
                }
            }
        }
        return encryptText(ciphertext, inverseKey);
    }

    private static int[] multiplyMatrix(int[][] key, int[] block) {
        int n = key.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += key[i][j] * block[j];
            }
            result[i] %= MOD;
        }
        return result;
    }

    private static int determinant(int[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        int det = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[][] minor = new int[matrix.length - 1][matrix.length - 1];
            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0, col = 0; k < matrix.length; k++) {
                    if (k == i) continue;
                    minor[j - 1][col++] = matrix[j][k];
                }
            }
            det += Math.pow(-1, i) * matrix[0][i] * determinant(minor);
        }
        return det;
    }

    private static int[][] adjoint(int[][] matrix) {
        int n = matrix.length;
        int[][] adjoint = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] minor = new int[n - 1][n - 1];
                for (int row = 0, mRow = 0; row < n; row++) {
                    if (row == i) continue;
                    for (int col = 0, mCol = 0; col < n; col++) {
                        if (col == j) continue;
                        minor[mRow][mCol++] = matrix[row][col];
                    }
                    mRow++;
                }
                adjoint[j][i] = (int) Math.pow(-1, i + j) * determinant(minor);
            }
        }
        return adjoint;
    }

    public static void main(String[] args) {
        // Ma trận khóa (ví dụ: ma trận 2x2)
        int[][] keyMatrix = {
                {3, 1},
                {4,1}
        };

        // Văn bản cần mã hóa và giải mã
        String plaintext = "HELL";
        String ciphertext = "";
        String decryptedText = "";

        // Mã hóa văn bản
        try {
            ciphertext = HillCipherPanel.encryptText(plaintext, keyMatrix);
            System.out.println("Văn bản mã hóa: " + ciphertext);
        } catch (Exception e) {
            System.out.println("Lỗi trong quá trình mã hóa: " + e.getMessage());
        }

        // Giải mã văn bản
        try {
            decryptedText = HillCipherPanel.decryptText(ciphertext, keyMatrix);
            System.out.println("Văn bản giải mã: " + decryptedText);
        } catch (Exception e) {
            System.out.println("Lỗi trong quá trình giải mã: " + e.getMessage());
        }
    }
}
