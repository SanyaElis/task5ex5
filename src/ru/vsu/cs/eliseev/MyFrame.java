package ru.vsu.cs.eliseev;

import util.ArrayUtils;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class MyFrame extends JFrame {

    private JPanel mainPanel;
    private JButton solution;
    private JButton readFromFile;
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JFileChooser fileChooserOpen;

    public MyFrame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        fileChooserOpen = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);

        solution.addActionListener(e -> {
            String s = inputArea.getText();
            String[] split = s.split("\\n");
            SimpleTree<String> tree = new SimpleTree<>();
            try {
                tree.fromStrNotation(split);
            } catch (Exception ex) {
                SwingUtils.showInfoMessageBox("Неверный формат дерева", "ERROR");
            }
            outputArea.setText(SimpleTreeAlgorithms.toBracketStr(tree.getRoot()));
        });
        readFromFile.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    String[] str = ArrayUtils.readLinesFromFile(fileChooserOpen.getSelectedFile().getPath());
                    inputArea.selectAll();
                    inputArea.replaceSelection("");
                    for (String W : str)
                        inputArea.append(W + "\n");
                }
            } catch (Exception ex) {
                SwingUtils.showInfoMessageBox("Некорректный файл", "ERROR");
            }
        });
    }

}
