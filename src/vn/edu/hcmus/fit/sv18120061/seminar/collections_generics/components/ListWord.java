package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components;
// Import section
import java.awt.Container;
import java.util.Vector;
import javax.swing.*;
import java.util.HashMap;
// Header section
/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components
 * @Author: Le Nhut Nam
 * @Date: 26/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class ListWord extends JFrame {
    private static final long serialVersionUID = 1L;
    private HashMap<String, String> listOfDictionary;
    public ListWord(HashMap<String, String> listOfDictionary) {
        super("List of words");
        this.listOfDictionary = listOfDictionary;
        setIconImage((new ImageIcon(getClass().getResource("/icons/icons8-list-48.png"))).getImage());

        Container container = getContentPane();

        Vector<String> headings = new Vector<String>();
        headings.add("Word");
        headings.add("Meaning");
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        for(String word : listOfDictionary.keySet()){
            Vector<String> row= new Vector<String>();
            row.add(word);
            row.add(listOfDictionary.get(word));
            rows.add(row);
        }
        JTable jTable = new JTable(rows,headings);

        JScrollPane jScrollPane = new JScrollPane(jTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        container.add(jScrollPane);

        pack();
    }
}
