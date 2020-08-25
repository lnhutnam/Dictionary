package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components;

// Import section
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

// Header section
/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.screens
 * @Author: Le Nhut Nam
 * @Date: 22/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */

public class SearchWord extends JFrame{
    private static final long serialVersionUID = 1L;
    public JTextField jTextFieldWord;
    public JTextArea  jTextAreaMeaning;
    public JButton jButtonSearch;
    public String wordMeaning;


    public SearchWord() {
        super("Search Word");
        setIconImage((new ImageIcon(getClass().getResource("/icons/icons8-search-48.png"))).getImage());
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10,10,10,10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        jTextFieldWord = new JTextField(20);
        jTextAreaMeaning = new JTextArea();
        jButtonSearch = new JButton("Search word");

        Container container = getContentPane();
        container.setLayout(gridBagLayout);

        // Add jTextFieldWord
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        container.add(new JLabel("Search word : "), gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(jTextFieldWord);
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        container.add(jButtonSearch);

        // Add jTextAreaMeaning
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        container.add(new JLabel("Meaning : "), gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        jTextAreaMeaning.setRows(10);
        jTextAreaMeaning.setColumns(100);
        JScrollPane jScrollPane = new JScrollPane(jTextAreaMeaning, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        container.add(jScrollPane, gridBagConstraints);

        pack();
    }
}
