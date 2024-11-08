package strategy;

import model.ComboBoxValues;

public class AESStrategy implements EncryptionStrategy {

    @Override
    public void configure(ComboBoxValues comboBoxValues) {
        // Cấu hình cho AES với các giá trị có sẵn
        comboBoxValues.setAlgorithm("AES");

        // Các chiều dài khóa cho AES
        comboBoxValues.setKeyLength(new String[]{"128", "192", "256"});

        // Các chế độ mã hóa cho AES
        comboBoxValues.setMode(new String[]{"ECB", "CBC", "CFB", "OFB", "GCM", "CTR"});

        // Các phương thức padding cho AES
        comboBoxValues.setPadding(new String[]{"PKCS5Padding", "PKCS7Padding", "NoPadding"});
    }

    @Override
    public String generateKey() {
        // Tạo key AES (có thể sử dụng thư viện như KeyGenerator)
        return "AESGeneratedKey123"; // Giả sử tạo key tạm
    }

    @Override
    public String encrypt(String input, String key) {
        // Logic mã hóa AES
        return "EncryptedDataWithAES"; // Giả sử mã hóa tạm
    }
}
