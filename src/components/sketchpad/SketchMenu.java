package components.sketchpad;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

import components.VisualHelpers;
import utils.Constants;

public class SketchMenu {
    
    private static Color[] COLORS = Constants.COLORS;
    private static Font DEFAULT_FONT = Constants.DEFAULT_FONT;

    private static JComboBox<String> shapeMenu = createShapeMenu();
    private static JButton closeButton = createCloseButton();
    public static JMenuBar menuBar = createMenuBar();
    public static ShapeType currentShapeType = ShapeType.RECTANGLE;
    public enum ShapeType {RECTANGLE, OVAL, LINE, TRIANGLE, PENTAGON};

    private static JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(COLORS[0]);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLORS[1]));
        menuBar.setPreferredSize(new Dimension(325, 40));

        menuBar.add(Box.createHorizontalStrut(10));
        menuBar.add(shapeMenu);

        menuBar.add(Box.createHorizontalGlue());

        menuBar.add(closeButton);
        menuBar.add(Box.createHorizontalStrut(20));

        return menuBar;

    }

    private static JButton createCloseButton() {
        
        JButton closeButton = new JButton("✖");
        closeButton.setBackground(COLORS[0]);
        VisualHelpers.buttonFormat(closeButton, COLORS[2], COLORS[1]);

        closeButton.addActionListener(l -> {

            SketchFrame.sketchFrame.setVisible(false);

        });

        return closeButton;

    }

    private static JComboBox<String> createShapeMenu() {

        UIManager.put("ComboBox.selectionBackground", COLORS[3]);
        UIManager.put("ComboBox.selectionForeground", COLORS[1]);
        UIManager.put("ComboBox.background", COLORS[3]);
        UIManager.put("ComboBox.foreground", COLORS[1]);
        UIManager.put("ComboBox.border", BorderFactory.createEmptyBorder());

        String[] shapeOptions = {"Rectangle", "Oval", "Line", "Triangle", "Pentagon"};
        JComboBox<String> shapeMenu = new JComboBox<>(shapeOptions);

        shapeMenu.setUI(new BasicComboBoxUI() {

            @Override
            public void paint(Graphics g, JComponent c) {

                super.paint(g, c);
                
            }
    
            @Override
            protected JButton createArrowButton() {
                
                JButton arrowButton = new JButton("v");
                VisualHelpers.buttonFormat(arrowButton, COLORS[1], COLORS[2]);
                return arrowButton;
            }
    
        });

        shapeMenu.setRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(DEFAULT_FONT);
                
                if (isSelected) {
                    label.setBackground(COLORS[3]);
                    label.setForeground(COLORS[1]);
                } else {
                    label.setBackground(COLORS[0]);
                    label.setForeground(COLORS[2]);
                }
    
                return label;
            }

        });

        shapeMenu.setFont(DEFAULT_FONT);
        shapeMenu.setBackground(COLORS[0]);
        shapeMenu.setForeground(COLORS[2]);
        shapeMenu.setBorder(BorderFactory.createEmptyBorder());
        shapeMenu.setMaximumSize(new Dimension(200, 30));

        shapeMenu.addActionListener(e -> {
            int index = shapeMenu.getSelectedIndex();
            currentShapeType = ShapeType.values()[index];
        });

        return shapeMenu;

    }

}