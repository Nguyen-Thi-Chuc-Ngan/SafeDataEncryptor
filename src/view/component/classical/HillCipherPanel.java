package view.component.classical;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HillCipherPanel extends JPanel {

    private JComboBox<String> matrixSizeComboBox; // ComboBox để chọn kích thước ma trận
    private JPanel matrixPanel; // Panel chứa các ô nhập liệu cho ma trận
    private JLabel errorLabel; // Label để hiển thị thông báo lỗi

    public HillCipherPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // Thêm Label cho ma trận
        add(new JLabel("Nhập khóa Hill Cipher:"));

        // ComboBox để người dùng chọn kích thước ma trận (2x2, 3x3, 4x4)
        String[] matrixSizes = {"2x2", "3x3", "4x4"};
        matrixSizeComboBox = new JComboBox<>(matrixSizes);
        matrixSizeComboBox.addActionListener(e -> updateMatrixPanel()); // Lắng nghe sự kiện thay đổi

        add(matrixSizeComboBox);

        // Panel chứa ma trận
        matrixPanel = new JPanel();
        matrixPanel.setPreferredSize(new Dimension(590, 150)); // Cố định kích thước panel chứa ma trận
        matrixPanel.setLayout(new GridLayout(2, 2)); // Khởi tạo mặc định với 2x2

        add(matrixPanel);

        // Label thông báo lỗi (bây giờ không cần thiết nữa)
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        add(errorLabel);

        updateMatrixPanel(); // Cập nhật panel với kích thước ma trận ban đầu
    }

    private void updateMatrixPanel() {
        // Lấy kích thước ma trận từ ComboBox
        String selectedSize = (String) matrixSizeComboBox.getSelectedItem();
        int rows = 0;
        int cols = 0;

        switch (selectedSize) {
            case "2x2":
                rows = 2;
                cols = 2;
                break;
            case "3x3":
                rows = 3;
                cols = 3;
                break;
            case "4x4":
                rows = 4;
                cols = 4;
                break;
        }

        // Đảm bảo rằng số hàng và số cột đều phải lớn hơn 0
        if (rows > 0 && cols > 0) {
            matrixPanel.setLayout(new GridLayout(rows, cols));  // Cập nhật lại GridLayout cho ma trận
        } else {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }

        // Xóa tất cả các thành phần cũ trong panel
        matrixPanel.removeAll();

        // Thêm các JTextField vào ma trận
        for (int i = 0; i < rows * cols; i++) {
            JTextField textField = new JTextField(5);
            textField.setHorizontalAlignment(JTextField.CENTER); // Căn giữa nội dung trong ô

            // Lắng nghe sự kiện thay đổi và kiểm tra giá trị nhập vào
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    validateInput(textField);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    validateInput(textField);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    validateInput(textField);
                }
            });
            matrixPanel.add(textField);
        }

        matrixPanel.revalidate(); // Cập nhật lại layout của panel
        matrixPanel.repaint();    // Vẽ lại panel
    }

    // Kiểm tra xem giá trị nhập vào có phải là số hợp lệ không
    private void validateInput(JTextField textField) {
        String text = textField.getText().trim();
        try {
            // Kiểm tra nếu là số nguyên hoặc số thực
            if (!text.isEmpty() && !text.matches("-?\\d+(\\.\\d+)?")) {
                // Hiển thị thông báo lỗi dưới dạng hộp thoại
                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập số nguyên hoặc số thực hợp lệ.",
                        "Lỗi nhập liệu",
                        JOptionPane.ERROR_MESSAGE);
                textField.setBackground(Color.PINK); // Đánh dấu ô nhập liệu sai
            } else {
                textField.setBackground(Color.WHITE); // Đặt lại màu nền khi nhập đúng
            }
        } catch (NumberFormatException e) {
            // Nếu có lỗi trong việc chuyển đổi giá trị
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập số hợp lệ.",
                    "Lỗi nhập liệu",
                    JOptionPane.ERROR_MESSAGE);
            textField.setBackground(Color.PINK); // Đánh dấu ô nhập liệu sai
        }
    }
}
