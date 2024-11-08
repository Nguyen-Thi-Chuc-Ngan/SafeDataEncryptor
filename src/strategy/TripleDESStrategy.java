package strategy;

import model.ComboBoxValues;

public class TripleDESStrategy implements EncryptionStrategy {

    @Override
    public void configure(ComboBoxValues comboBoxValues) {
        // Cấu hình các giá trị cho Triple DES (3DES)
        comboBoxValues.setAlgorithm("3DES");

        // Triple DES sử dụng chiều dài khóa 168 bit (3 khóa 56 bit)
        comboBoxValues.setKeyLength(new String[]{"168"});

        // Các chế độ mã hóa cho 3DES
        comboBoxValues.setMode(new String[]{"ECB", "CBC", "CFB", "OFB"});

        // Các phương thức padding cho 3DES
        comboBoxValues.setPadding(new String[]{"PKCS5Padding", "NoPadding"});
    }

    @Override
    public String generateKey() {
        // Giả sử tạo một khóa 3DES tạm thời (khoá dài 168 bit)
        return "3DESGeneratedKey";
    }

    @Override
    public String encrypt(String input, String key) {
        // Logic mã hóa cho 3DES
        // Ở đây bạn có thể tích hợp mã hóa thực tế hoặc trả về dữ liệu giả để kiểm tra
        return "EncryptedDataWith3DES";  // Mã hóa giả tạm thời
    }
}
