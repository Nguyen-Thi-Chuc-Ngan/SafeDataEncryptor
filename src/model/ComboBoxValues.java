package model;

public class ComboBoxValues {
    private String algorithm;
    private String[] keyLength;
    private String[] mode;
    private String[] padding;

    public ComboBoxValues(String algorithm) {
        this.algorithm = algorithm;

        // Set default values based on the selected algorithm
        configureAlgorithm(algorithm);
    }

    // Cấu hình các giá trị cho thuật toán
    private void configureAlgorithm(String algorithm) {
        if ("AES".equals(algorithm)) {
            this.keyLength = new String[]{"128", "192", "256"};
            this.mode = new String[]{"GCM", "ECB", "CBC", "CFB", "OFB", "CTR"};
            this.padding = new String[]{"PKCS5Padding", "PKCS7Padding", "NoPadding"};
        } else if ("DES".equals(algorithm)) {
            this.keyLength = new String[]{"56"};
            this.mode = new String[]{"ECB", "CBC", "CFB", "OFB"};
            this.padding = new String[]{"PKCS5Padding", "NoPadding"};
        } else if ("3DES".equals(algorithm)) {  // Thêm cấu hình cho Triple DES
            this.keyLength = new String[]{"168"};
            this.mode = new String[]{"ECB", "CBC", "CFB", "OFB"};
            this.padding = new String[]{"PKCS5Padding", "NoPadding"};
        } else {
            // Set default values for unsupported algorithms
            this.keyLength = new String[]{};
            this.mode = new String[]{};
            this.padding = new String[]{};
        }
    }

    // Getters and Setters
    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        configureAlgorithm(algorithm);  // Cập nhật lại các giá trị khi thay đổi thuật toán
    }

    public String[] getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(String[] keyLength) {
        this.keyLength = keyLength;
    }

    public String[] getMode() {
        return mode;
    }

    public void setMode(String[] mode) {
        this.mode = mode;
    }

    public String[] getPadding() {
        return padding;
    }

    public void setPadding(String[] padding) {
        this.padding = padding;
    }
}
