package algorithm.classical;

import javax.swing.*;
import java.security.SecureRandom;
import java.util.Random;

public class AffineCipher {
    private int a; // Khóa 'a'
    private int b; // Khóa 'b'
    private String alphabet;

    public AffineCipher(int a, int b, String alphabet) {
        this.a = a;
        this.b = b;
        this.alphabet = alphabet;
    }
    // Mã hóa văn bản theo thuật toán Affine Cipher
    // Mã hóa văn bản theo thuật toán Affine Cipher
    public String encrypt(String plaintext) {
        if (gcd(a, alphabet.length()) != 1) {
            throw new IllegalArgumentException("'a' must be coprime with the size of the alphabet.");
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
    }

    // Giải mã văn bản theo thuật toán Affine Cipher
    public String decrypt(String ciphertext) {
        if (gcd(a, alphabet.length()) != 1) {
            throw new IllegalArgumentException("'a' must be coprime with the size of the alphabet.");
        }

        // Tìm nghịch đảo của 'a'
        int aInverse = modInverse(a, alphabet.length());

        StringBuilder plaintext = new StringBuilder();

        // Giải mã từng ký tự
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                int y = alphabet.indexOf(Character.toUpperCase(c)); // Lấy chỉ số của ký tự trong bảng chữ cái
                int x = (aInverse * (y - b + alphabet.length())) % alphabet.length(); // Áp dụng công thức giải mã Affine
                char decryptedChar = alphabet.charAt(x); // Lấy ký tự giải mã
                plaintext.append(decryptedChar);
            } else {
                plaintext.append(c); // Ký tự không phải chữ cái giữ nguyên
            }
        }

        return plaintext.toString();
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
