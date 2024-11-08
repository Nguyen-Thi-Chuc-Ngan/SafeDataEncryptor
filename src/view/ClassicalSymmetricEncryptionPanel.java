package view;

import view.component.ActionPanel;
import view.component.InputPanel;
import view.component.ResultPanel;
import view.component.classical.*;

import javax.swing.*;
import java.awt.*;

public class ClassicalSymmetricEncryptionPanel extends JPanel {

    private JComboBox<String> algorithmComboBox;
    private JPanel algorithmPanel;
    private JComboBox<String> languageComboBox;
    private JButton genKeyButton;  // Nút Gen Key

    public ClassicalSymmetricEncryptionPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new BorderLayout());  // Sắp xếp theo chiều ngang

        // Panel cho các lựa chọn thuật toán và ngôn ngữ
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.setPreferredSize( new Dimension(590, 150));

        selectionPanel.add(createAlgorithmSelectionPanel());
        selectionPanel.add(createLanguageSelectionPanel());

        // Tạo nút Gen Key
        genKeyButton = new JButton("Generate");
        genKeyButton.setPreferredSize(new Dimension(120, 40));
        selectionPanel.add(genKeyButton);

        // Đường phân cách
        JPanel separator = new JPanel();
        separator.setPreferredSize(new Dimension(1, 150));
        separator.setBackground(Color.GRAY);

        // Tạo panel cho nội dung thay đổi theo thuật toán
        algorithmPanel = new JPanel();
        algorithmPanel.setLayout(new BoxLayout(algorithmPanel, BoxLayout.Y_AXIS));

        // Đảm bảo algorithmPanel có kích thước cố định
        algorithmPanel.setPreferredSize(new Dimension(590, 200));  // Đặt kích thước cho algorithmPanel
        comboPanel.add(selectionPanel, BorderLayout.WEST);
        comboPanel.add(separator, BorderLayout.CENTER);
        comboPanel.add(algorithmPanel, BorderLayout.EAST);

        // Các panel phụ
        InputPanel inputPanel = new InputPanel();
        ResultPanel resultPanel = new ResultPanel();
        ActionPanel actionPanel = new ActionPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, resultPanel);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);

        // Thêm các thành phần vào giao diện theo thứ tự từ trên xuống
        add(comboPanel, BorderLayout.NORTH); // Thuật toán và ngôn ngữ
        add(splitPane, BorderLayout.CENTER); // Panel chia đôi nội dung
        add(actionPanel, BorderLayout.SOUTH); // Panel hành động

        updatePanel(); // Cập nhật panel ban đầu

        // Lắng nghe sự kiện thay đổi thuật toán
        algorithmComboBox.addActionListener(e -> updatePanel());
    }

    private JPanel createAlgorithmSelectionPanel() {
        String[] algorithms = {"Hill", "Substitution", "Vigence", "Affine", "Transposition"};
        algorithmComboBox = createComboBox(algorithms);

        // Sử dụng BoxLayout để kiểm soát bố cục
        JPanel algorithmPanel = new JPanel();
        algorithmPanel.setLayout(new BoxLayout(algorithmPanel, BoxLayout.X_AXIS));  // Chỉnh bố cục ngang
        algorithmPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Thêm các thành phần vào panel
        algorithmPanel.add(new JLabel("Algorithm:"));
        algorithmPanel.add(Box.createHorizontalStrut(10)); // Khoảng cách giữa label và comboBox
        algorithmPanel.add(algorithmComboBox);

        // Đặt kích thước mong muốn cho algorithmPanel
        algorithmPanel.setPreferredSize(new Dimension(590, 40)); // Điều chỉnh chiều cao cho phù hợp

        // Đảm bảo layout của panel được cập nhật
        algorithmPanel.revalidate();
        algorithmPanel.repaint();

        return algorithmPanel;
    }


    private JPanel createLanguageSelectionPanel() {
        String[] languages = {"English", "Tiếng Việt"};
        languageComboBox = createComboBox(languages);

        // Sử dụng BoxLayout để dễ dàng kiểm soát kích thước
        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new BoxLayout(languagePanel, BoxLayout.X_AXIS));
        languagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        languagePanel.add(new JLabel("Language:"));
        languagePanel.add(Box.createHorizontalStrut(10));
        languagePanel.add(languageComboBox);

        // Cập nhật kích thước panel
        languagePanel.setPreferredSize(new Dimension(590, 40));

        // Đảm bảo layout của panel cha được cập nhật
        languagePanel.revalidate();
        languagePanel.repaint();

        return languagePanel;
    }


    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setPreferredSize(new Dimension(150, 30));
        return comboBox;
    }

    private void updatePanel() {
        String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
        algorithmPanel.removeAll(); // Xóa các thành phần cũ trong panel

        // Thêm các trường nhập liệu khác nhau dựa trên thuật toán đã chọn
        switch (selectedAlgorithm) {
            case "Hill":
                algorithmPanel.add(new HillCipherPanel());
                break;
            case "Substitution":
                algorithmPanel.add(new SubstitutionCipherPanel());
                break;
            case "Vigence":
                algorithmPanel.add(new VigeneceCipherPanel());
                break;
            case "Affine":
                algorithmPanel.add(new AffineCipherPanel());
                break;
            case "Transposition":
                algorithmPanel.add(new TranspositionCipherPanel());
                break;
        }

        algorithmPanel.revalidate(); // Cập nhật lại layout của panel
        algorithmPanel.repaint(); // Vẽ lại panel
    }
}
