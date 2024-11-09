package view.component.classical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    // Mã hóa văn bản theo thuật toán Affine Cipher
    public String encrypt(String plaintext, String alphabet) {
        try {
            int a = Integer.parseInt(keyAField.getText());
            int b = Integer.parseInt(keyBField.getText());

            // Kiểm tra tính hợp lệ của 'a'
            if (gcd(a, alphabet.length()) != 1) {
                JOptionPane.showMessageDialog(this, "'a' must be coprime with the size of the alphabet.", "Invalid Key", JOptionPane.ERROR_MESSAGE);
                return "";
            }

            StringBuilder ciphertext = new StringBuilder();

            // Mã hóa từng ký tự
            for (char c : plaintext.toCharArray()) {
                if (Character.isLetter(c)) {
                    int x = alphabet.indexOf(Character.toUpperCase(c)); // Lấy chỉ số của ký tự trong bảng chữ cái
                    int y = (a * x + b) % alphabet.length(); // Áp dụng công thức mã hóa Affine
                    char encryptedChar = alphabet.charAt(y); // Lấy ký tự mã hóa
                    ciphertext.append(encryptedChar);
                } else {
                    ciphertext.append(c); // Ký tự không phải chữ cái giữ nguyên
                }
            }

            return ciphertext.toString();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for keys.", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

    // Giải mã văn bản theo thuật toán Affine Cipher
    public String decrypt(String ciphertext, String alphabet) {
        try {
            int a = Integer.parseInt(keyAField.getText());
            int b = Integer.parseInt(keyBField.getText());

            int alphabetSize = alphabet.length();

            // Kiểm tra tính hợp lệ của 'a'
            if (gcd(a, alphabetSize) != 1) {
                JOptionPane.showMessageDialog(this, "'a' must be coprime with the size of the alphabet.", "Invalid Key", JOptionPane.ERROR_MESSAGE);
                return "";
            }

            // Tìm nghịch đảo của 'a'
            int aInverse = modInverse(a, alphabetSize);

            StringBuilder plaintext = new StringBuilder();

            // Giải mã từng ký tự
            for (char c : ciphertext.toCharArray()) {
                if (Character.isLetter(c)) {
                    int y = alphabet.indexOf(Character.toUpperCase(c)); // Lấy chỉ số của ký tự trong bảng chữ cái
                    int x = (aInverse * (y - b + alphabetSize)) % alphabetSize; // Áp dụng công thức giải mã Affine
                    char decryptedChar = alphabet.charAt(x); // Lấy ký tự giải mã
                    plaintext.append(decryptedChar);
                } else {
                    plaintext.append(c); // Ký tự không phải chữ cái giữ nguyên
                }
            }

            return plaintext.toString();

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

    // Tìm nghịch đảo của a mod alphabetSize
    private int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;  // Nếu không có nghịch đảo
    }
}

