package view;

import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class InstructionsForUse extends JPanel {
    public InstructionsForUse() {
        setLayout(new BorderLayout());

        JTextArea instructions = new JTextArea();
        instructions.setText("1. Chọn loại mã hóa bạn muốn sử dụng.\n" +
                "2. Nhập thông tin cần mã hóa.\n" +
                "3. Nhấn nút 'Mã hóa' để thực hiện.\n" +
                "4. Nếu cần giải mã, nhấn nút 'Giải mã'.\n" +
                "5. Kiểm tra kết quả ở khu vực bên phải.");
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(instructions);
        add(scrollPane, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Hướng dẫn sử dụng", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
    }
}
