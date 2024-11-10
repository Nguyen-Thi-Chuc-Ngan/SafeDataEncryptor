package view.classical;

import algorithm.classical.AffineCipher;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AffineCipherPanel extends JPanel {
    private JTextField keyAField; // Khóa 'a'
    private JTextField keyBField; // Khóa 'b'

    public AffineCipherPanel() {
        setLayout(new BorderLayout(20, 20));

        // Panel nhập khóa 'a' và 'b'
        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(3, 2, 10, 20)); // 3 hàng, 2 cột, điều chỉnh khoảng cách
        keyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Thêm khoảng cách xung quanh panel

        keyPanel.add(new JLabel("Enter 'a' (1-25, must have inverse mod 26):"));
        keyAField = new JTextField();
        keyAField.setFont(new Font("Arial", Font.PLAIN, 14)); // Thay đổi font chữ
        keyPanel.add(keyAField);

        keyPanel.add(new JLabel("Enter 'b' (0-25):"));
        keyBField = new JTextField();
        keyBField.setFont(new Font("Arial", Font.PLAIN, 14)); // Thay đổi font chữ
        keyPanel.add(keyBField);

        // Panel nhập và hiển thị văn bản
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1, 10, 10));
        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Thêm các panel vào giao diện
        add(keyPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
    }

    public void generateKeys() {
        Random rand = new Random();

        // Sinh khóa 'a' sao cho gcd(a, alphabetSize) = 1
        int a;
        do {
            a = rand.nextInt(25) + 1; // Sinh giá trị a trong khoảng từ 1 đến 25
        } while (gcd(a, 26) != 1); // Kiểm tra tính hợp lệ của 'a'

        // Sinh khóa 'b' trong phạm vi 0 đến 25
        int b = rand.nextInt(26);

        // Cập nhật giá trị khóa 'a' và 'b' vào các trường nhập liệu
        keyAField.setText(String.valueOf(a));
        keyBField.setText(String.valueOf(b));
    }

    // Mã hóa văn bản theo thuật toán Affine Cipher
    public String encrypt(String plaintext, String alphabet) {
        try {
            int a = Integer.parseInt(keyAField.getText());
            int b = Integer.parseInt(keyBField.getText());

            AffineCipher affineCipher = new AffineCipher(a, b, alphabet);
            return affineCipher.encrypt(plaintext);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for keys.", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

    // Giải mã văn bản
    public String decrypt(String ciphertext, String alphabet) {
        try {
            int a = Integer.parseInt(keyAField.getText());
            int b = Integer.parseInt(keyBField.getText());

            AffineCipher affineCipher = new AffineCipher(a, b, alphabet);
            return affineCipher.decrypt(ciphertext);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for keys.", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

    // Tính ước chung lớn nhất (GCD)
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

