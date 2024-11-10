package algorithm.classical;

import utils.Alphabet;

import javax.swing.*;
import java.util.Arrays;

public class HillCipher {

    public static String encryptText(String plaintext, int[][] key, String selectedLanguage) {
        System.out.println("Encrypting text...");

        // Kiểm tra khóa có hợp lệ hay không (ví dụ kiểm tra determinant)
        int det = determinant(key, selectedLanguage) % getMod(selectedLanguage);
        if (det < 0) det += getMod(selectedLanguage);

        int detInverse = modInverse(det, getMod(selectedLanguage));
        if (detInverse == -1) {
            // Hiển thị thông báo lỗi nếu không thể tính nghịch đảo
            JOptionPane.showMessageDialog(null, "Cannot invert matrix due to unsuitable determinant. Please generate the key again.", "Key Error", JOptionPane.ERROR_MESSAGE);
            return ""; // Trả về chuỗi rỗng nếu khóa không hợp lệ
        }
        // Chia văn bản thành các từ
        String[] words = plaintext.split(" ");

        StringBuilder ciphertext = new StringBuilder();

        // Duyệt qua từng từ
        for (String word : words) {
            System.out.println("Encrypting word: " + word);

            // Thực hiện mã hóa cho từng từ
            String encryptedWord = encryptWord(word, key, selectedLanguage);

            // Thêm từ đã mã hóa vào ciphertext
            ciphertext.append(encryptedWord).append(" ");
        }

        // Loại bỏ khoảng trắng cuối cùng nếu có
        return ciphertext.toString().trim();
    }

    public static String encryptWord(String word, int[][] key, String selectedLanguage) {
        char[] alphabet = getAlphabet(selectedLanguage);
        System.out.println(alphabet);
        int n = key.length;
        int padding = n - word.replace(" ", "").length() % n; // Tính padding nếu cần

        // Thêm padding nếu cần
        if (padding != n) {
            word += "X".repeat(padding);
        }

        StringBuilder encryptedWord = new StringBuilder();
        int[] block = new int[n];
        int blockIndex = 0;

        // Mã hóa từng ký tự trong từ
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (Character.isLetter(c)) {
                // Tìm chỉ số của ký tự trong bảng chữ cái
                int index = findCharacterIndex(c, alphabet);
                block[blockIndex++] = index;

                if (blockIndex == n) {
                    int[] encryptedBlock = multiplyMatrix(key, block, selectedLanguage);
                    for (int val : encryptedBlock) {
                        encryptedWord.append(alphabet[val]); // Mã hóa và chuyển thành ký tự
                    }
                    blockIndex = 0;
                }
            } else {
                // Giữ nguyên ký tự không phải chữ cái
                encryptedWord.append(c);
            }
        }

        // Xử lý block cuối cùng nếu cần
        if (blockIndex > 0) {
            for (int i = blockIndex; i < n; i++) {
                block[i] = findCharacterIndex('X', alphabet); // Thêm padding cho block chưa đầy đủ
            }
            int[] encryptedBlock = multiplyMatrix(key, block, selectedLanguage);
            for (int val : encryptedBlock) {
                encryptedWord.append(alphabet[val]); // Mã hóa và chuyển thành ký tự
            }
        }

        return encryptedWord.toString();
    }

    public static String decryptWord(String word, int[][] inverseKey, String selectedLanguage) {
        char[] alphabet = getAlphabet(selectedLanguage);
        int n = inverseKey.length;
        StringBuilder decryptedWord = new StringBuilder();
        int[] block = new int[n];
        int blockIndex = 0;

        // Giải mã từng ký tự trong từ
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (Character.isLetter(c)) {
                // Tìm chỉ số của ký tự trong bảng chữ cái
                block[blockIndex++] = HillCipher.findCharacterIndex(c, alphabet);

                // Khi đủ kích thước block, giải mã
                if (blockIndex == n) {
                    int[] decryptedBlock = multiplyMatrix(inverseKey, block, selectedLanguage);
                    for (int val : decryptedBlock) {
                        decryptedWord.append(alphabet[val]); // Giải mã và chuyển thành ký tự
                    }
                    blockIndex = 0;
                }
            } else {
                // Giữ nguyên ký tự không phải chữ cái (dấu câu, khoảng trắng, v.v.)
                decryptedWord.append(c);
            }
        }

        // Xử lý block cuối cùng nếu cần
        if (blockIndex > 0) {
            int[] decryptedBlock = multiplyMatrix(inverseKey, block, selectedLanguage);
            for (int val : decryptedBlock) {
                decryptedWord.append(alphabet[val]); // Giải mã và chuyển thành ký tự
            }
        }

        // Loại bỏ padding "X" ở cuối
        String decryptedText = decryptedWord.toString().replaceAll("X*$", "");

        return decryptedText;
    }



    public static String decryptText(String ciphertext, int[][] key, String selectedLanguage) {
        System.out.println("Decrypting text...");
        int mod = getMod(selectedLanguage);
        // Tính toán ma trận ngược của ma trận khóa
        int det = determinant(key, selectedLanguage) % mod;
        if (det < 0) det += mod;

        int detInverse = modInverse(det, mod);
        if (detInverse == -1) {
            // Hiển thị thông báo lỗi nếu không thể tính nghịch đảo
            JOptionPane.showMessageDialog(null, " \"Unable to invert the matrix due to an unsuitable determinant. Please check and regenerate the key. Make sure you've selected the correct language for the key.", "Key Error", JOptionPane.ERROR_MESSAGE);
            return ""; // Trả về một chuỗi rỗng hoặc có thể ném ra một ngoại lệ nếu cần
        }

        int[][] adjointMatrix = adjoint(key, selectedLanguage);
        int n = key.length;
        int[][] inverseKey = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseKey[i][j] = (adjointMatrix[i][j] * detInverse) % mod;
                if (inverseKey[i][j] < 0) {
                    inverseKey[i][j] += mod;
                }
            }
        }

        // Tách ciphertext thành các từ
        String[] words = ciphertext.split(" ");

        StringBuilder decryptedText = new StringBuilder();

        // Duyệt qua từng từ
        for (String word : words) {
            System.out.println("Decrypting word: " + word);

            // Giải mã cho từng từ
            String decryptedWord = decryptWord(word, inverseKey, selectedLanguage);

            // Thêm từ đã giải mã vào văn bản cuối cùng
            decryptedText.append(decryptedWord).append(" ");
        }

        // Loại bỏ khoảng trắng cuối cùng nếu có
        return decryptedText.toString().trim();
    }


    private static int[] multiplyMatrix(int[][] key, int[] block, String selectedLanguage) {
        int n = key.length;
        int mod = getMod(selectedLanguage);
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = 0;
            for (int j = 0; j < n; j++) {
                result[i] += key[i][j] * block[j];
            }
            result[i] %= mod;
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

        det = det % getMod(selectedLanguage);
        return (det + getMod(selectedLanguage)) % getMod(selectedLanguage);
    }

    public static int modInverse(int a, int mod) {
        a = a % mod;
        for (int x = 1; x < mod; x++) {
            if ((a * x) % mod == 1) {
                return x;
            }
        }
        return -1;  // Return -1 if inverse doesn't exist
    }

    public static int findCharacterIndex(char c, char[] alphabet) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    // Hàm để lấy bảng chữ cái theo ngôn ngữ
    public static char[] getAlphabet(String selectedLanguage) {
        if ("Vietnamese".equalsIgnoreCase(selectedLanguage)) {
            return Alphabet.VIETNAME_ALPHABET.getAlphabet();  // Trả về bảng chữ cái tiếng Việt
        } else if ("English".equalsIgnoreCase(selectedLanguage)) {
            return Alphabet.ENGLISH_ALPHABET.getAlphabet(); // Giữ nguyên bảng chữ cái tiếng Anh
        } else {
            // Fallback nếu ngôn ngữ không hỗ trợ
            System.err.println("Unsupported language: " + selectedLanguage + ". Defaulting to English alphabet.");
            return Alphabet.ENGLISH_ALPHABET.getAlphabet();
        }
    }

    public static int getMod(String selectedLanguage) {
        if ("Vietnamese".equalsIgnoreCase(selectedLanguage)) {
            return 29; // Số lượng ký tự trong bảng chữ cái tiếng Việt (bao gồm chữ hoa, chữ thường và ký tự đặc biệt)
        } else {
            return 26; // Số lượng ký tự trong bảng chữ cái tiếng Anh
        }
    }
    private static int[][] adjoint(int[][] matrix, String selectedLanguage) {
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
                adjoint[i][j] = (int) Math.pow(-1, i + j) * determinant(minor, selectedLanguage);
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
}