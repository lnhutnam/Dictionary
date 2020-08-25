package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components;

// Import section
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Header section
/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components
 * @Author: Le Nhut Nam
 * @Date: 26/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */

// Class RemoveWord section
public class RemoveWord extends JFrame{
    private static final long serialVersionUID = 1L;
    public JTextField jTextFieldWord;
    public JButton jButtonRemove;

    public RemoveWord () {
        super("Remove word");
        setIconImage((new ImageIcon(getClass().getResource("/icons/icons8-remove-48.png"))).getImage());

        jTextFieldWord = new JTextField(20);
        jButtonRemove = new JButton("Remove");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(new JLabel("Word to remove: "));
        container.add(jTextFieldWord);
        container.add(jButtonRemove);

        pack();
    }
}
