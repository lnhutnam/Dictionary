package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components;

// Import section
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

// Header section
/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components
 * @Author: Le Nhut Nam
 * @Date: 22/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */

// class AddWord section
public class AddWord extends JFrame{
    private static final long serialVersionUID = 1L;
    public JTextField jTextFieldWord;
    public JTextArea  jTextAreaMeaning;
    public JButton jButtonAddWord;
    public String wordNeedToAdd;

    public AddWord() {
        super("Add word");
        setIconImage((new ImageIcon(getClass().getResource("/icons/icons8-add-48.png"))).getImage());
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5,5,5,5);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        jTextFieldWord = new JTextField(30);
        jTextAreaMeaning = new JTextArea();
        jButtonAddWord = new JButton("Add word");
        jButtonAddWord.setMnemonic(KeyEvent.VK_ENTER);

        Container container = getContentPane();
        container.setLayout(gridBagLayout);

        // Add jTextFieldWord
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        container.add(new JLabel("Add word : "), gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(jTextFieldWord);
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        container.add(jButtonAddWord);

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
