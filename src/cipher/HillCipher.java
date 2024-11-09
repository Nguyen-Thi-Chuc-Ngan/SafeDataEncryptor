package cipher;

import java.util.Arrays;

public class HillCipher {
    private static final int MOD = 26;

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
                for (int k = 0, row = 0; k < n; k++) {
                    if (k == i) continue;
                    for (int l = 0, col = 0; l < n; l++) {
                        if (l == j) continue;
                        minor[row][col++] = matrix[k][l];
                    }
                    row++;
                }
                adjoint[i][j] = (int) Math.pow(-1, i + j) * determinant(minor);
            }
        }
        return transpose(adjoint);
    }

    private static int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] key = {{6, 24, 1}, {13, 16, 10}, {20, 17, 15}};
        String plaintext = "POINT";
        String ciphertext = encryptText(plaintext, key);
        System.out.println("The Encrypted Text: " + ciphertext);
        String decrypted = decryptText(ciphertext, key);
        System.out.println("The Decrypted Text: " + decrypted);
    }
}