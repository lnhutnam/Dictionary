package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.screens;

import org.xml.sax.SAXException;
import vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components.*;
import vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions.DOMDictionaryXML.*;

/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.screens
 * @Author: Le Nhut Nam
 * @Date: 22/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class MainFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    private static final String titleProgram = "English Vietnamese Dictionary";

    private TypeDictionary typeDictionary = TypeDictionary.envi;
    private String typeDictString = "English Dictionary";
    private final JLabel textDictionaryType;

    private HashMap<String, String> dictionary;
    private String selectedWordMeaning;

    private final JPanel leftPane;
    private final JTextPane meaningTextPane;

    private FavoriteWordList favoriteWordList;
    private JToggleButton jToggleButtonLike;

    private ImageIcon getImage(String fileName){
        try {
            return new ImageIcon(
                   this.getClass().getResource(fileName));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void centerToJFrameParent(JFrame jFrameParent, JFrame jFrameChild){
        Dimension parentDimension = jFrameParent.getSize();
        Dimension childDimension = jFrameChild.getSize();
        int x = (int) (parentDimension.getWidth() - childDimension.getWidth()) / 2;
        int y = (int) (parentDimension.getHeight() - childDimension.getHeight()) / 2;

        jFrameChild.setLocation(x, y);
    }

    private void toolBar() {
        // Create toolBar
        JToolBar jToolBar = new JToolBar(JToolBar.VERTICAL);
        //
        JButton jButton;
        // Search word button
        jButton = new JButton(getImage("/icons/icons8-search-48.png"));
        jButton.setPreferredSize(new Dimension(48, 48));
        jToolBar.add(jButton);
        jButton.setToolTipText("Search word");
        jButton.addActionListener(e -> {
            // Do something
            SearchWord searchWord = new SearchWord();
            searchWord.jButtonSearch.addActionListener(e1 -> {
                // Do something
                if (searchWord.jTextFieldWord.getText().length() > 0){
                    String string = searchWord.jTextFieldWord.getText().toLowerCase();
                    searchWord.wordMeaning = dictionary.get(string);
                    if (searchWord.wordMeaning == null){
                        JOptionPane.showMessageDialog( searchWord, "Word  Not Found. Please try again!","Search Word", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(searchWord.wordMeaning != null) {
                        searchWord.jTextAreaMeaning.setText(searchWord.wordMeaning);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(searchWord, "Please enter word from dictionary!", "Search Word", JOptionPane.ERROR_MESSAGE);
                }
            });
            centerToJFrameParent(MainFrame.this, searchWord);
            searchWord.setVisible(true);
        });
        jToolBar.addSeparator();
        // Add word button
        jButton = new JButton(getImage("/icons/icons8-add-48.png"));
        jButton.setPreferredSize(new Dimension(48, 48));
        jToolBar.add(jButton);
        jButton.setToolTipText("Add word");
        jButton.addActionListener(e -> {
            // Do something
            AddWord addWord = new AddWord();
            addWord.jButtonAddWord.addActionListener(e1 -> {
                String word = addWord.jTextFieldWord.getText().toLowerCase();
                String meaning = addWord.jTextAreaMeaning.getText().toLowerCase();
                addWord.jTextFieldWord.setText("");
                addWord.jTextAreaMeaning.setText("");
                addWord.jTextFieldWord.requestFocus();
                String fileName;
                switch (typeDictionary) {
                    case envi:
                        fileName = "./src/data/English_Vietnamese/Anh_Viet.xml";
                        break;
                    case vien:
                        fileName = "./src/data/Vietnamese_English/Viet_Anh.xml";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + typeDictionary);
                }
                try {
                    String result = addWordToDictionaryXML(word, meaning, fileName);
                    JOptionPane.showMessageDialog( addWord, "Added Word Successfully!","Add Word", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException | SAXException | ParserConfigurationException | TransformerException ioException) {
                    ioException.printStackTrace();
                }
            });
            centerToJFrameParent(MainFrame.this, addWord);
            addWord.setVisible(true);
        });

        jToolBar.addSeparator();
        // Remove word button
        jButton = new JButton(getImage("/icons/icons8-remove-48.png"));
        jButton.setPreferredSize(new Dimension(48, 48));
        jToolBar.add(jButton);
        jButton.setToolTipText("Remove word");
        jButton.addActionListener(e -> {
            // Do something
            RemoveWord removeWord = new RemoveWord();
            removeWord.jButtonRemove.addActionListener(e1 -> {
                String word = removeWord.jTextFieldWord.getText().toLowerCase();
                removeWord.jTextFieldWord.setText("");
                removeWord.jTextFieldWord.requestFocus();
                String fileName;
                switch (typeDictionary) {
                    case envi:
                        fileName = "./src/data/English_Vietnamese/Anh_Viet.xml";
                        break;
                    case vien:
                        fileName = "./src/data/Vietnamese_English/Viet_Anh.xml";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + typeDictionary);
                }
                try {
                    if (word != null){
                        boolean done = removeWordFromXMLDictionary(word, fileName);
                        if(done){
                            JOptionPane.showMessageDialog( removeWord, "Word  Deleted Successfully!","Delete Word", JOptionPane.INFORMATION_MESSAGE);
                            this.loadDictionaryFromXMLFile();
                        }else {
                            JOptionPane.showMessageDialog( removeWord, "Word  Not Found. Please try again!","Delete Word", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog( removeWord, "Please enter word from dictionary!","Add Word", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | SAXException | ParserConfigurationException | TransformerException ioException) {
                    ioException.printStackTrace();
                }
            });
            centerToJFrameParent(MainFrame.this, removeWord);
            removeWord.setVisible(true);
        });
        jToolBar.addSeparator();
        // Add to favorites list
        jButton = new JButton(getImage("/icons/icons8-wish-list-48.png"));
        jButton.setPreferredSize(new Dimension(48, 48));
        jToolBar.add(jButton);
        jButton.setToolTipText("Favorites list");
        jButton.addActionListener(e -> {
            // Do
            FavoriteWordSubScreen favoriteWordSubScreen = new FavoriteWordSubScreen(MainFrame.this, typeDictString + " favorite list", favoriteWordList.getList());
            favoriteWordSubScreen.setLocationRelativeTo(MainFrame.this);
            favoriteWordSubScreen.setVisible(true);
        });
        jToolBar.addSeparator();
        // Load dictionary
        jButton = new JButton(getImage("/icons/icons8-load-48.png"));
        jButton.setPreferredSize(new Dimension(48, 48));
        jToolBar.add(jButton);
        jButton.setToolTipText("Load dictionary");
        jButton.addActionListener(e -> {
            // Do something
            try {
                this.loadDictionaryFromXMLFile();
                JOptionPane.showMessageDialog( MainFrame.this, "Loaded dictionary Successfully!","Load Dictionary", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParserConfigurationException | SAXException | IOException parserConfigurationException) {
                JOptionPane.showMessageDialog( MainFrame.this, "Load dictionary failed!","Load Dictionary", JOptionPane.INFORMATION_MESSAGE);
                parserConfigurationException.printStackTrace();
            }
        });
        jToolBar.addSeparator();
        // Save dictionary
        jButton = new JButton(getImage("/icons/icons8-save-48.png"));
        jButton.setPreferredSize(new Dimension(48, 48));
        jToolBar.add(jButton);
        jButton.setToolTipText("Save dictionary");
        jButton.addActionListener(e -> {
            // Do something
            String fileName;
            switch (typeDictionary) {
                case envi:
                    fileName = "./src/data/English_Vietnamese/Anh_Viet.xml";
                    break;
                case vien:
                    fileName = "./src/data/Vietnamese_English/Viet_Anh.xml";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + typeDictionary);
            }
            try {
                DOMDictionaryXML.saveDictionary(fileName);
                JOptionPane.showMessageDialog( MainFrame.this, "Saved dictionary Successfully!.","Save Dictionary", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParserConfigurationException | IOException | SAXException | TransformerException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
                JOptionPane.showMessageDialog( MainFrame.this, "Save dictionary failed!.","Save Dictionary", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jToolBar.addSeparator();
        // Statistics
        jButton = new JButton(getImage("/icons/icons8-statistics-48.png"));
        jButton.setPreferredSize(new Dimension(48, 48));
        jToolBar.add(jButton);
        jButton.setToolTipText("Statistics");
        jButton.addActionListener(e -> {
            // Do something
            FrequentWordSubScreen frequentWordSubScreen = new FrequentWordSubScreen(MainFrame.this, "Frequent Word Search for " + typeDictString, typeDictionary);
            frequentWordSubScreen.setLocationRelativeTo(MainFrame.this);
            frequentWordSubScreen.setVisible(true);
        });
        jToolBar.addSeparator();
        // Star
        jToggleButtonLike = new JToggleButton(getImage("/icons/icons8-star-48.png"));
        jToggleButtonLike.setPreferredSize(new Dimension(48, 48));
        jToolBar.add(jToggleButtonLike);
        jToggleButtonLike.setToolTipText("Star");
        jToggleButtonLike.setSelected(false);
        jToggleButtonLike.setEnabled(true);
        jToggleButtonLike.addActionListener(e -> {
            AbstractButton abstractButton = (AbstractButton) e.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            if(selected){
                favoriteWordList.addFavoriteWord(selectedWordMeaning);
                jToggleButtonLike.setSelected(false);
                jToggleButtonLike.setEnabled(true);
                try {
                    saveFavoriteList();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        getContentPane().add(jToolBar, BorderLayout.EAST);
    }

    private void addDictionaryMenu(JMenuBar jMenuBar){
        // Create Dictionary Menu
        JMenu jMenuDictionary = new JMenu("Dictionary");
        jMenuBar.add(jMenuDictionary);
        // - Change mode
        JMenuItem jMenuItem;
        // - Show list
        jMenuItem = new JMenuItem("List words");
        jMenuItem.setIcon(getImage("/icons/icons8-list-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("F1"));
        jMenuDictionary.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            ListWord listWord = new ListWord(dictionary);
            centerToJFrameParent(MainFrame.this, listWord);
            listWord.setVisible(true);
        });
        // Add Separator
        jMenuDictionary.addSeparator();
        // - Search word
        jMenuItem = new JMenuItem("Search word");
        jMenuItem.setIcon(getImage("/icons/icons8-search-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("F2"));
        jMenuDictionary.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            SearchWord searchWord = new SearchWord();
            searchWord.jButtonSearch.addActionListener(e1 -> {
                // Do something
                if (searchWord.jTextFieldWord.getText().length() > 0){
                    searchWord.wordMeaning = dictionary.get(searchWord.jTextFieldWord.getText().toLowerCase());
                    if (searchWord.wordMeaning == null){
                        JOptionPane.showMessageDialog( searchWord, "Word  Not Found. Please try again!","Search Word", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(searchWord.wordMeaning != null) {
                        searchWord.jTextAreaMeaning.setText(searchWord.wordMeaning);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(searchWord, "Please enter word from dictionary!", "Search Word", JOptionPane.ERROR_MESSAGE);
                }
            });
            centerToJFrameParent(MainFrame.this, searchWord);
            searchWord.setVisible(true);
        });
        // Add Separator
        jMenuDictionary.addSeparator();
        // - Add word
        jMenuItem = new JMenuItem("Add word");
        jMenuItem.setIcon(getImage("/icons/icons8-add-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("F3"));
        jMenuDictionary.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            AddWord addWord = new AddWord();
            addWord.jButtonAddWord.addActionListener(e1 -> {
                String word = addWord.jTextFieldWord.getText().toLowerCase();
                String meaning = addWord.jTextAreaMeaning.getText().toLowerCase();
                addWord.jTextFieldWord.setText("");
                addWord.jTextAreaMeaning.setText("");
                addWord.jTextFieldWord.requestFocus();
                String fileName;
                switch (typeDictionary) {
                    case envi:
                        fileName = "./src/data/English_Vietnamese/Anh_Viet.xml";
                        break;
                    case vien:
                        fileName = "./src/data/Vietnamese_English/Viet_Anh.xml";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + typeDictionary);
                }
                try {
                    if(word !=null && meaning != null){
                        String result = addWordToDictionaryXML(word, meaning, fileName);
                        JOptionPane.showMessageDialog( addWord, "Added Word Successfully!","Add Word", JOptionPane.INFORMATION_MESSAGE);
                        this.loadDictionaryFromXMLFile();
                    } else {
                        JOptionPane.showMessageDialog( addWord, "Please enter word and meaning!","Add Word", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | SAXException | ParserConfigurationException | TransformerException ioException) {
                    ioException.printStackTrace();
                }
            });
            centerToJFrameParent(MainFrame.this, addWord);
            addWord.setVisible(true);
        });
        // Add Separator
        jMenuDictionary.addSeparator();
        // - Remove word
        jMenuItem = new JMenuItem("Remove word");
        jMenuItem.setIcon(getImage("/icons/icons8-remove-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
        jMenuDictionary.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            RemoveWord removeWord = new RemoveWord();
            removeWord.jButtonRemove.addActionListener(e1 -> {
                String word = removeWord.jTextFieldWord.getText();
                removeWord.jTextFieldWord.setText("");
                removeWord.jTextFieldWord.requestFocus();
                String fileName;
                switch (typeDictionary) {
                    case envi:
                        fileName = "./src/data/English_Vietnamese/Anh_Viet.xml";
                        break;
                    case vien:
                        fileName = "./src/data/Vietnamese_English/Viet_Anh.xml";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + typeDictionary);
                }
                try {
                    if (word != null){
                        boolean done = removeWordFromXMLDictionary(word, fileName);
                        if(done){
                            JOptionPane.showMessageDialog( removeWord, "Word  Deleted Successfully!","Delete Word", JOptionPane.INFORMATION_MESSAGE);
                            this.loadDictionaryFromXMLFile();
                        }else {
                            JOptionPane.showMessageDialog( removeWord, "Word  Not Found. Please try again!","Delete Word", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog( removeWord, "Please enter word from dictionary!","Add Word", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | SAXException | ParserConfigurationException | TransformerException ioException) {
                    ioException.printStackTrace();
                }
            });
            centerToJFrameParent(MainFrame.this, removeWord);
            removeWord.setVisible(true);
        });
        // Add Separator
        jMenuDictionary.addSeparator();
        // - Exit
        jMenuItem = new JMenuItem("Exit");
        jMenuItem.setIcon(getImage("/icons/icons8-exit-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("F10"));
        jMenuDictionary.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            String fileName;
            switch (typeDictionary) {
                case envi:
                    fileName = "./src/data/English_Vietnamese/Anh_Viet.xml";
                    break;
                case vien:
                    fileName = "./src/data/Vietnamese_English/Viet_Anh.xml";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + typeDictionary);
            }
            int option = JOptionPane.showConfirmDialog(MainFrame.this, "You have some pending changes. Do you want to write them to disk and then exit?",
                    "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(option == JOptionPane.YES_OPTION){
                try {
                    saveDictionary(fileName);
                } catch (ParserConfigurationException | IOException | SAXException | TransformerException parserConfigurationException) {
                    parserConfigurationException.printStackTrace();
                }
                System.exit(0);
            }
            if(option == JOptionPane.NO_OPTION){
                System.exit(0);
            }
        });
    }

    private void addStorageMenu(JMenuBar jMenuBar) {
        JMenu jMenuStorage = new JMenu("Storage");
        jMenuBar.add(jMenuStorage);
        // - Load Dictionary
        JMenuItem jMenuItem = new JMenuItem("Load Dictionary");
        jMenuItem.setIcon(getImage("/icons/icons8-load-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("control O"));
        jMenuStorage.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            try {
                this.loadDictionaryFromXMLFile();
                JOptionPane.showMessageDialog( MainFrame.this, "Loaded dictionary Successfully!","Load Dictionary", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParserConfigurationException | SAXException | IOException parserConfigurationException) {
                JOptionPane.showMessageDialog( MainFrame.this, "Load dictionary failed!","Load Dictionary", JOptionPane.INFORMATION_MESSAGE);
                parserConfigurationException.printStackTrace();
            }
        });
        // Add Separator
        jMenuStorage.addSeparator();
        // - Save Dictionary
        jMenuItem = new JMenuItem("Save Dictionary");
        jMenuItem.setIcon(getImage("/icons/icons8-save-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("control S"));
        jMenuStorage.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            String fileName;
            switch (typeDictionary) {
                case envi:
                    fileName = "./src/data/English_Vietnamese/Anh_Viet.xml";
                    break;
                case vien:
                    fileName = "./src/data/Vietnamese_English/Viet_Anh.xml";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + typeDictionary);
            }
            try {
                DOMDictionaryXML.saveDictionary(fileName);
                JOptionPane.showMessageDialog( MainFrame.this, "Saved dictionary Successfully!.","Save Dictionary", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParserConfigurationException | IOException | SAXException | TransformerException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
                JOptionPane.showMessageDialog( MainFrame.this, "Save dictionary failed!.","Save Dictionary", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void addStatisticsMenu(JMenuBar jMenuBar) {
        JMenu jMenuStatistics = new JMenu("Statistics");
        jMenuBar.add(jMenuStatistics);

        // - Add to wish list
        JMenuItem jMenuItem = new JMenuItem("Favorites list");
        jMenuItem.setIcon(getImage("/icons/icons8-wish-list-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("F5"));
        jMenuStatistics.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            FavoriteWordSubScreen favoriteWordSubScreen = new FavoriteWordSubScreen(MainFrame.this, typeDictString + " favorite list", favoriteWordList.getList());
            favoriteWordSubScreen.setLocationRelativeTo(MainFrame.this);
            favoriteWordSubScreen.setVisible(true);
        });
        jMenuStatistics.addSeparator();
        jMenuItem = new JMenuItem("Frequent list");
        jMenuItem.setIcon(getImage("/icons/icons8-frequent-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("F6"));
        jMenuStatistics.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            FrequentWordSubScreen frequentWordSubScreen = new FrequentWordSubScreen(MainFrame.this, "Frequent Word Search for " + typeDictString, typeDictionary);
            frequentWordSubScreen.setLocationRelativeTo(MainFrame.this);
            frequentWordSubScreen.setVisible(true);

        });
        jMenuStatistics.addSeparator();
    }

    private void addHelpMenu(JMenuBar jMenuBar){
        JMenu jMenuHelp = new JMenu("Help");
        jMenuBar.add(jMenuHelp);
        // Help
        JMenuItem jMenuItem = new JMenuItem("Help");
        jMenuItem.setIcon(getImage("/icons/icons8-help-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("control H"));
        jMenuHelp.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            Help help = new Help();
        });
        // Add Separator
        jMenuHelp.addSeparator();
        // Check for update
        jMenuItem = new JMenuItem("Check for update");
        jMenuItem.setIcon(getImage("/icons/icons8-update-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("control U"));
        jMenuHelp.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            try {
                CheckForUpdate checkForUpdate = new CheckForUpdate();
                checkForUpdate.setLocation(MainFrame.this.getWidth()/2,MainFrame.this.getHeight()/ 2);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        // Add Separator
        jMenuHelp.addSeparator();
        // About
        jMenuItem = new JMenuItem("About");
        jMenuItem.setIcon(getImage("/icons/icons8-about-48.png"));
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("control A"));
        jMenuHelp.add(jMenuItem);
        jMenuItem.addActionListener(e -> {
            // Do something
            About about  = new About();
        });
    }

    private void loadFavoriteWordFromFile() throws IOException {
        switch (typeDictionary){
            case vien:
                favoriteWordList.loadFavoriteListFromFile("./src/data/favorite/favorite-Vietnamese-Word.txt");
                break;
            case envi:
                favoriteWordList.loadFavoriteListFromFile("./src/data/favorite/favorite-English-Word.txt");
                break;
        }
    }

    private void loadDictionaryFromXMLFile() throws ParserConfigurationException, SAXException, IOException {
        try {
            switch (typeDictionary){
                case envi:
                    dictionary = LoadDictionaryFromXML.loadDictionaryFromFile("./src/data/English_Vietnamese/Anh_Viet.xml");
                    break;
                case vien:
                    dictionary = LoadDictionaryFromXML.loadDictionaryFromFile("./src/data/Vietnamese_English/Viet_Anh.xml");
            }
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void prepareDataDictionary() throws IOException, SAXException, ParserConfigurationException {
        favoriteWordList = new FavoriteWordList();
        this.loadDictionaryFromXMLFile();
        this.loadFavoriteWordFromFile();
    }

    private JPanel prepareSearchPanel() {
        JPanel searchChoicePane = new JPanel();

        ArrayList<String> words = new ArrayList<String>();
        words.addAll(dictionary.keySet());
        DefaultListModel<String> listWordModel = new DefaultListModel<>();
        words.forEach(listWordModel::addElement);
        JList<String> jList = new JList<>(listWordModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jList.addListSelectionListener(listSelectionEvent -> {
            if(!listSelectionEvent.getValueIsAdjusting()) {
                JList<String> jLst = (JList<String>) listSelectionEvent.getSource();
                selectedWordMeaning = jList.getSelectedValue();
                meaningTextPane.setText(dictionary.get(selectedWordMeaning));
                switch (typeDictionary){
                    case vien:
                        try {
                            DOMDictionaryXML.addWordToHistoryDictionaryXML(selectedWordMeaning, "./src/data/history/History-Vietnamese.xml");
                        } catch (ParserConfigurationException | ParseException | IOException | SAXException | TransformerException e) {
                            e.printStackTrace();
                        }
                        break;
                    case envi:
                        try {
                            DOMDictionaryXML.addWordToHistoryDictionaryXML(selectedWordMeaning, "./src/data/history/History-English.xml");
                        } catch (ParserConfigurationException | ParseException | IOException | TransformerException | SAXException e) {
                            e.printStackTrace();
                        }
                }
            }
        });

        searchChoicePane = JListFilterDecorator.decorate(jList, MainFrame::wordFilter);
        searchChoicePane.setBorder(new EmptyBorder(0, 0, 0, 5));
        searchChoicePane.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));

        return searchChoicePane;
    }

    private JPanel prepareControlPanel() {
        JPanel controlButtons = new JPanel();
        controlButtons.setBorder(new EmptyBorder(5, 0, 5, 0));
        controlButtons.setLayout(new BoxLayout(controlButtons, BoxLayout.X_AXIS));

        JButton changeDict = new JButton("EN - VI");
        changeDict.setActionCommand("change");
        changeDict.addActionListener(e -> {
            AbstractButton abstractButton = (AbstractButton) e.getSource();
            switch (typeDictionary) {
                case vien:
                    abstractButton.setText("EN - VI");
                    typeDictionary = TypeDictionary.envi;
                    typeDictString = "English Dictionary";
                    break;
                case envi:
                    abstractButton.setText("VI - EN");
                    typeDictionary = TypeDictionary.vien;
                    typeDictString = "Vietnamese Dictionary";
                    break;
            }
            try {
                loadDictionaryFromXMLFile();
            } catch (ParserConfigurationException | IOException | SAXException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            }

            try {
                loadFavoriteWordFromFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            textDictionaryType.setText(typeDictString);

            leftPane.remove(2);
            leftPane.add(prepareSearchPanel());
        });
        controlButtons.add(changeDict);

        JButton favList = new JButton("List Favorite");
        favList.setActionCommand("favorite");
        favList.addActionListener(e -> {
            FavoriteWordSubScreen favoriteWordSubScreen = new FavoriteWordSubScreen(MainFrame.this, typeDictString + " favorite list", favoriteWordList.getList());
            favoriteWordSubScreen.setLocationRelativeTo(MainFrame.this);
            favoriteWordSubScreen.setVisible(true);
        });
        controlButtons.add(favList);

        JButton freq = new JButton("Frequent");
        freq.setActionCommand("frequent");
        freq.addActionListener(e -> {
            FrequentWordSubScreen frequentWordSubScreen = new FrequentWordSubScreen(MainFrame.this, "Frequent Word Search for " + typeDictString, typeDictionary);
            frequentWordSubScreen.setLocationRelativeTo(MainFrame.this);
            frequentWordSubScreen.setVisible(true);
        });
        controlButtons.add(freq);

        return controlButtons;
    }
    public MainFrame() throws Exception {
        // Create title and Icon main frame
        super(titleProgram);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setIconImage((new ImageIcon(getClass().getResource("/icons/icons8-google-translate-96.png"))).getImage());
        // Load data
        // Load data
        prepareDataDictionary();
        // Create menu bar and tool bar
        JMenuBar jMenuBar = new JMenuBar();
        // Create Dictionary Menu
        addDictionaryMenu(jMenuBar);
        // Create Storage Menu
        addStorageMenu(jMenuBar);
        // Create Statistics Menu
        addStatisticsMenu(jMenuBar);
        // Overall view
        // Create Help Menu
        addHelpMenu(jMenuBar);
        // Set menu
        setJMenuBar(jMenuBar);
        // Call tool bar


        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        leftPane = new JPanel();
        leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
        leftPane.add(this.prepareControlPanel());

        textDictionaryType = new JLabel(typeDictString);
        JPanel jPanel = new JPanel(new FlowLayout());
        jPanel.add(textDictionaryType);
        leftPane.add(jPanel);

        leftPane.add(this.prepareSearchPanel());
        leftPane.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
        contentPane.add(leftPane);

        meaningTextPane = new JTextPane();
        meaningTextPane.setEditable(false);
        JScrollPane displayMeaning = new JScrollPane(meaningTextPane);
        displayMeaning.setMinimumSize(new Dimension(300, Integer.MAX_VALUE));
        contentPane.add(displayMeaning);

        setContentPane(contentPane);
        toolBar();
    }

    private static boolean wordFilter(String word, String str) {
        return word.toLowerCase().contains(str.toLowerCase());
    }

    private void saveFavoriteList() throws IOException {
        switch (typeDictionary){
            case vien:
                favoriteWordList.saveFavoriteList("./src/data/favorite/favorite-Vietnamese-Word.txt");
                break;
            case envi:
                favoriteWordList.saveFavoriteList("./src/data/favorite/favorite-English-Word.txt");
                break;
        }
    }

    private void loadFavoriteList() throws IOException {
        switch (typeDictionary) {
            case vien:
                favoriteWordList.loadFavoriteListFromFile("./src/data/favorite/favorite-Vietnamese-Word.txt");
                break;
            case envi:
                favoriteWordList.loadFavoriteListFromFile("./src/data/favorite/favorite-English-Word.txt");
                break;
        }
    }


    // Main function
    private static void createAndShowGUI() throws Exception{
        // Start
        MainFrame mainFrame = new MainFrame();
        // Display the window.
        mainFrame.pack();
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
    }
    public static void main(String[] args){
       javax.swing.SwingUtilities.invokeLater(() -> {
           try {
               createAndShowGUI();
           } catch (Exception e) {
               e.printStackTrace();
           }
       });
    }

}
