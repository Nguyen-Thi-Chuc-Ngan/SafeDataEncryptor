package strategy;

import model.ComboBoxValues;

public interface EncryptionStrategy {
    void configure(ComboBoxValues comboBoxValues);
    String generateKey();
    String encrypt(String input, String key);
}