package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.screens;

// Import section
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

// Header section
/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.screens
 * @Author: Le Nhut Nam
 * @Date: 26/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */

// Class FavoriteWordSubScreen section
public class FavoriteWordSubScreen extends JDialog{
    private static final long serialVersionUID = -6429530604264500008L;
    private JPanel jContentPanel;

    public FavoriteWordSubScreen(JFrame parent, String title, ArrayList<String> listWord) {
        super(parent, title, true);
        setIconImage((new ImageIcon(getClass().getResource("/icons/icons8-wish-list-48.png"))).getImage());
        setBounds(100, 100, 450, 300);

        jContentPanel = new JPanel();
        jContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        jContentPanel.setLayout(new BoxLayout(jContentPanel, BoxLayout.Y_AXIS));

        DefaultListModel<String> listWordModel = new DefaultListModel<>();
        listWord.forEach(listWordModel::addElement);
        JList<String> jList = new JList<>(listWordModel);

        JScrollPane displayMeaning = new JScrollPane(jList);
        jContentPanel.add(displayMeaning);

        setContentPane(jContentPanel);
    }
}
