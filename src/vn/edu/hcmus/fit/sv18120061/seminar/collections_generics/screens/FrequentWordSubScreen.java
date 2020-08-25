package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.screens;
// Import section

// Header section
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jdatepicker.impl.*;

import vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions.DOMDictionaryXML;
import vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions.DateLabelFormatter;
import vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions.FrequentTableModel;
import vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions.TypeDictionary;

/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.screens
 * @Author: Le Nhut Nam
 * @Date: 26/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */

// Class FrequentWordSubScreen section
public class FrequentWordSubScreen extends JDialog{
    private static final long serialVersionUID = 1L;
    private JTable jTableFrequentWordSubScreen;
    Properties properties;
    private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePickerFrom, datePickerTo;
    private TypeDictionary typeDictionary;

    private void showWarning(String warnMessage, String warnTitle) {
        JOptionPane.showMessageDialog(this, warnMessage, warnTitle, JOptionPane.WARNING_MESSAGE);
    }

    private void loadFrequentWordList(){
        FrequentTableModel frequentTableModel = (FrequentTableModel) jTableFrequentWordSubScreen.getModel();

        Date from = (Date) datePickerFrom.getModel().getValue();
        Date to = (Date) datePickerTo.getModel().getValue();

        if (from.compareTo(to) > 0) {
            showWarning("From date > To date", "ERROR WARNING");
            frequentTableModel.setFreq(new HashMap<>());
        }

        HashMap<String, Integer> freqStringIntegerHashMap = null;
        try {
            switch (typeDictionary) {
                case vien:
                    freqStringIntegerHashMap = DOMDictionaryXML.getFrequentWordList(from, to, "./src/data/history/History-Vietnamese.xml");
                    break;
                case envi:
                    freqStringIntegerHashMap = DOMDictionaryXML.getFrequentWordList(from, to,"./src/data/history/History-English.xml");
                    break;
            }
            frequentTableModel.setFreq(freqStringIntegerHashMap);
            // resizeColumnWidth(tableFrequentOfWord);
        } catch (Exception e) {
            showWarning(e.getMessage(), "Loading frequent of word warning");
        }
    }

    public FrequentWordSubScreen (JFrame jFrameParent, String stringTitle, TypeDictionary typeDictionary) {
        super(jFrameParent, stringTitle, true);
        this.typeDictionary = typeDictionary;
        setIconImage((new ImageIcon(getClass().getResource("/icons/icons8-list-48.png"))).getImage());
        setBounds(100, 100, 450, 300);

        JPanel jPanelContent = new JPanel();
        jPanelContent.setBorder(new EmptyBorder(5,5,5,5));
        jPanelContent.setLayout(new BoxLayout(jPanelContent, BoxLayout.Y_AXIS));

        properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        model = new UtilDateModel();
        jPanelContent.add(new JLabel("From date"));
        datePickerFrom = new JDatePickerImpl(new JDatePanelImpl(model, properties),  new DateLabelFormatter());
        jPanelContent.add(datePickerFrom);

        jPanelContent.add(new JLabel("To date"));
        datePickerTo = new JDatePickerImpl(new JDatePanelImpl(model, properties), new DateLabelFormatter());
        jPanelContent.add(datePickerTo);

        jTableFrequentWordSubScreen = new JTable();

        jTableFrequentWordSubScreen.setModel(new FrequentTableModel());
        jTableFrequentWordSubScreen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableFrequentWordSubScreen.setAutoCreateRowSorter(true);
        jTableFrequentWordSubScreen.setShowVerticalLines(true);
        jTableFrequentWordSubScreen.setShowHorizontalLines(true);
        jTableFrequentWordSubScreen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableFrequentWordSubScreen.getColumnModel().getColumn(0).setResizable(false);
        jTableFrequentWordSubScreen.getTableHeader().setReorderingAllowed(false);

        JScrollPane jScrollPaneTable = new JScrollPane(jTableFrequentWordSubScreen, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneTable.getViewport().setBackground(Color.white);
        jPanelContent.add(jScrollPaneTable);

        ActionListener actionListener = e -> {
            // Do something
            loadFrequentWordList();
        };

        datePickerTo.addActionListener(actionListener);
        datePickerFrom.addActionListener(actionListener);

        setContentPane(jPanelContent);
    }


}
