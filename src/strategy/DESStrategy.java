package strategy;

import model.ComboBoxValues;

public class DESStrategy implements EncryptionStrategy {

    @Override
    public void configure(ComboBoxValues comboBoxValues) {
        comboBoxValues.setAlgorithm("DES");

        // DES có chiều dài khóa cố định là 56 bit
        comboBoxValues.setKeyLength(new String[]{"56"});

        // Các chế độ mã hóa cho DES
        comboBoxValues.setMode(new String[]{"ECB", "CBC", "CFB", "OFB"});

        // Các phương thức padding cho DES
        comboBoxValues.setPadding(new String[]{"PKCS5Padding", "NoPadding"});
    }

    @Override
    public String generateKey() {
        return "DESGeneratedKey"; // Giả sử tạo key tạm cho DES
    }

    @Override
    public String encrypt(String input, String key) {
        // Logic mã hóa DES
        return "EncryptedDataWithDES"; // Giả sử mã hóa tạm
    }
}
