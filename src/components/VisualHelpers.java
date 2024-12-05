package components;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import utils.Constants;

public class VisualHelpers {

    private static Font DEFAULT_FONT = Constants.DEFAULT_FONT;
    private static Color[] COLORS = Constants.COLORS;

    public static JMenu menuFormat(String name) {

        UIManager.put("Menu.selectionBackground", COLORS[3]);
        UIManager.put("Menu.selectionForeground", COLORS[2]);
        UIManager.put("MenuItem.selectionBackground", COLORS[3]);
        UIManager.put("MenuItem.selectionForeground", COLORS[2]);

        JMenu menu = new JMenu(name);

        menu.setFont(DEFAULT_FONT);
        menu.setForeground(COLORS[1]);
        menu.setBorder(BorderFactory.createEmptyBorder());
        menu.getPopupMenu().setBorder(BorderFactory.createEmptyBorder());
        menu.getPopupMenu().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, COLORS[2]));

        return menu;

    }

    public static JMenuItem createTextMenuItem(String s) {

        JMenuItem menuItem = new JMenuItem(s);

        menuItem.setFont(DEFAULT_FONT);
        menuItem.setBackground(COLORS[0]);
        menuItem.setForeground(COLORS[1]);
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setPreferredSize(new Dimension(120, 30));
        menuItem.setOpaque(true);

        return menuItem;

    }

    public static JMenuItem createSketchMenuItem(String s) {

        JMenuItem menuItem = new JMenuItem(s);
    
        menuItem.setFont(DEFAULT_FONT);
        menuItem.setBackground(COLORS[0]);
        menuItem.setForeground(COLORS[2]);
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setPreferredSize(new Dimension(120, 30));
        menuItem.setOpaque(true);
    
        return menuItem;
    
    }

    public static void buttonFormat(JButton b, Color nonhoveredColor, Color hoveredColor) {

        b.setBackground(COLORS[0]);
        b.setForeground(nonhoveredColor);
        b.setBorder(BorderFactory.createEmptyBorder());
        b.setContentAreaFilled(false);

        b.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                b.setForeground(hoveredColor);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                b.setForeground(nonhoveredColor);

            }

        });

    }

    public static void searchTextFieldFormat(JTextField f) {

        f.setMaximumSize(new Dimension(105000, 25));
        f.setFont(DEFAULT_FONT);
        f.setSelectionColor(COLORS[3]);
        f.setSelectedTextColor(COLORS[2]);
        f.setBackground(COLORS[0]);
        f.setForeground(COLORS[1]);
        f.setBorder(BorderFactory.createEmptyBorder());
        f.setBorder(BorderFactory.createLineBorder(COLORS[2], 1));

    }

    public static void labelFormat(JLabel l, Color foreground) {

        l.setFont(DEFAULT_FONT);
        l.setBackground(COLORS[0]);
        l.setForeground(foreground);
        l.setBorder(BorderFactory.createEmptyBorder());

    }

}
