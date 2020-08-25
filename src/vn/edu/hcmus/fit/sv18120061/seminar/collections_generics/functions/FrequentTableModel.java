package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions;

import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions
 * @Author: Le Nhut Nam
 * @Date: 27/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class FrequentTableModel extends AbstractTableModel {
    /**
     *
     */
    private static final long serialVersionUID = 6705986070517256588L;
    private HashMap<String, Integer> freqStringIntegerHashMap;
    private String[] words;
    private String[] columns = { "Word", "Frequent" };

    public FrequentTableModel() {
        this(new HashMap<>());
    }

    public FrequentTableModel(HashMap<String, Integer> freq) {
        this.freqStringIntegerHashMap = freq;
        this.words = (String[]) (freq.keySet().toArray(new String[freq.size()]));
    }

    public Object getValueAt(int row, int column) {
        String word = words[row];
        switch (column) {
            case 0:
                return word;
            case 1:
                return freqStringIntegerHashMap.get(word).toString();
            default:
                System.err.println("Logic Error");
        }
        return "";
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
        }
        return String.class;
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public int getRowCount() {
        return words.length;
    }

    public String getWord(int row) {
        return words[row];
    }

    public void setFreq(HashMap<String, Integer> freq) {
        this.freqStringIntegerHashMap = freq;
        this.words = (String[]) (freq.keySet().toArray(new String[freq.size()]));
        fireTableDataChanged();
    }
}