package view.custom;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class Theme {

    public void setup() {
        FlatIntelliJLaf.setup();

        UIManager.put("Button.arc", 10);

        UIManager.put("Button.margin", new Insets(10, 20, 10, 20));

        UIManager.put("Component.arc", 10);

        UIManager.put("ProgressBar.arc", 10);

        UIManager.put("TextComponent.arc", 10);

        UIManager.put("TextField.margin", new Insets(8, 8, 8, 8));

        UIManager.put("ComboBox.padding", new Insets(8, 8, 8, 8));

        UIManager.put("TabbedPane.selectedBackground", Color.white);

        UIManager.put("Button.background", new Color(70, 130, 180));

        UIManager.put("Button.foreground", Color.WHITE);

        UIManager.put("Panel.background", new Color(240, 240, 240));

        UIManager.put("Label.foreground", Color.DARK_GRAY);

        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));

        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 12));

        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 12));

        UIManager.put("ScrollBar.thumb", new Color(200, 200, 200));

        UIManager.put("ScrollBar.track", Color.LIGHT_GRAY);

        UIManager.put("ScrollBar.width", 12);

        UIManager.put("Button.select", new Color(100, 149, 237));

        UIManager.put("ComboBox.selectionBackground", new Color(173, 216, 230));

        UIManager.put("List.selectionBackground", new Color(135, 206, 250));
    }
}