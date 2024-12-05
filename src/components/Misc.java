package components;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import components.sketchpad.SketchFrame;

import utils.Constants;

public class Misc {

    private static Color[] COLORS = Constants.COLORS;
    private static Font DEFAULT_FONT = Constants.DEFAULT_FONT;
    private static Font ITALIC_FONT = Constants.ITALIC_FONT;
    private static Font BOLD_FONT = Constants.BOLD_FONT;

    public static JButton sketchButton = createSketchButton();
    public static JButton closeButton = createCloseButton();
    public static JButton boldButton = createBoldButton();
    public static JButton italicButton = createItalicButton();
    public static JLabel wordCountField = createWordCountField();
    public static JLabel findLabel = createFindLabel();
    public static JTextField findField = createFindField();
    public static JLabel replaceLabel = createReplaceLabel();
    public static JTextField replaceField = createReplaceField();

    private static JLabel createWordCountField() {

        JLabel wordCountField = new JLabel("Character Count: 0 | Word Count: 0");
        wordCountField.setMaximumSize(new Dimension(450, 30));
        wordCountField.setMinimumSize(new Dimension(300, 30));
        VisualHelpers.labelFormat(wordCountField, COLORS[4]);

        return wordCountField;

    }

    public static void setCounts(JTextPane p) {

        String text = p.getText();
        String[] words = text.split("\\s+");
                
        int charCount = text.length();
        int wordCount = (words.length > 0 &&  text.length() > 0) ? words.length : 0;

        wordCountField.setText("Character Count : " + charCount + " | Word Count: " + wordCount);

    }

    private static JButton createBoldButton() {

        JButton boldButton = new JButton(" B ");
        boldButton.setFont(BOLD_FONT);
        VisualHelpers.buttonFormat(boldButton, COLORS[2], COLORS[1]);

        boldButton.addActionListener(l -> {

            int start = EditorFrame.textPane.getSelectionStart();
            int end = EditorFrame.textPane.getSelectionEnd();
        
            if (start == end) {
                SimpleAttributeSet attr = new SimpleAttributeSet(EditorFrame.textPane.getInputAttributes());
                boolean isCurrentlyBold = StyleConstants.isBold(attr);
        
                StyleConstants.setBold(attr, !isCurrentlyBold);
                EditorFrame.textPane.setCharacterAttributes(attr, false);
            } else {
                SimpleAttributeSet attr = new SimpleAttributeSet(EditorFrame.styledDocument.getCharacterElement(start).getAttributes());
                boolean isCurrentlyBold = StyleConstants.isBold(attr);
        
                StyleConstants.setBold(attr, !isCurrentlyBold);
                EditorFrame.styledDocument.setCharacterAttributes(start, end - start, attr, false);
            }
            
        });

        return boldButton;

    }

    private static JButton createItalicButton() {

        JButton italicButton = new JButton(" I ");
        italicButton.setFont(ITALIC_FONT);
        VisualHelpers.buttonFormat(italicButton, COLORS[2], COLORS[1]);

        italicButton.addActionListener(l -> {

            int start = EditorFrame.textPane.getSelectionStart();
            int end = EditorFrame.textPane.getSelectionEnd();

            if (start == end) {
                SimpleAttributeSet attr = new SimpleAttributeSet(EditorFrame.textPane.getInputAttributes());
                boolean isCurrentlyItalic = StyleConstants.isItalic(attr);
        
                StyleConstants.setItalic(attr, !isCurrentlyItalic);
                EditorFrame.textPane.setCharacterAttributes(attr, false);
            } else {
                SimpleAttributeSet attr = new SimpleAttributeSet(EditorFrame.styledDocument.getCharacterElement(start).getAttributes());
                boolean isCurrentlyItalic = StyleConstants.isItalic(attr);
        
                StyleConstants.setItalic(attr, !isCurrentlyItalic);
                EditorFrame.styledDocument.setCharacterAttributes(start, end - start, attr, false);
            } 

        });

        return italicButton;

    }

    private static JButton createCloseButton() {

        JButton closeButton = new JButton("✖");
        VisualHelpers.buttonFormat(closeButton, COLORS[1], COLORS[2]);

        closeButton.addActionListener(l -> {

            System.exit(0);

        });

        return closeButton;

    }

    private static JLabel createFindLabel() {

        JLabel findLabel = new JLabel("Find: ");
        VisualHelpers.labelFormat(findLabel, COLORS[2]);

        return findLabel;

    }

    private static JTextField createFindField() {

        JTextField findField = new JTextField();
        VisualHelpers.searchTextFieldFormat(findField);

        findField.addActionListener(l -> {
            
            Document searchText = EditorFrame.textPane.getDocument();
            Highlighter highlighter = EditorFrame.textPane.getHighlighter();
            String searchQuery = findField.getText();
            
            try {

                highlighter.removeAllHighlights();
                if (searchQuery == null || searchQuery.isEmpty()) return;

                int startPos = 0;
                String textString = searchText.getText(startPos, searchText.getLength());

                while (startPos >= 0) {

                    startPos = textString.indexOf(searchQuery, startPos);
                    if (startPos == -1) break; 
                    int endPos = startPos + searchQuery.length();
                    highlighter.addHighlight(startPos, endPos, new DefaultHighlighter.DefaultHighlightPainter(COLORS[5]));
                    startPos = endPos;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        });

        return findField;

    }

    private static JLabel createReplaceLabel() {

        JLabel replaceLabel = new JLabel("Replace: ");
        VisualHelpers.labelFormat(replaceLabel, COLORS[2]);

        return replaceLabel;

    }

    private static JTextField createReplaceField() {

        JTextField replaceField = new JTextField();
        VisualHelpers.searchTextFieldFormat(replaceField);
    
        replaceField.addActionListener(l -> {
            String searchText = EditorFrame.textPane.getText();
            Highlighter highlighter = EditorFrame.textPane.getHighlighter();
            String searchQuery = findField.getText();
            String replacementText = replaceField.getText();
    
            try {
                highlighter.removeAllHighlights();
                if (searchQuery == null || searchQuery.isEmpty()) return;
                EditorFrame.textPane.setText(searchText.replaceAll(searchQuery, replacementText));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    
        return replaceField;
        
    }    

    private static JButton createSketchButton() {

        JButton sketchButton = new JButton("SketchPad");
        sketchButton.setFont(DEFAULT_FONT);
        VisualHelpers.buttonFormat(sketchButton, COLORS[2], COLORS[1]);

        sketchButton.addActionListener(l -> {

            SketchFrame.sketchFrame.setVisible(true);

        });

        return sketchButton;

    }
    
}