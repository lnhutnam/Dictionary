package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions;
// Import section

// Header section

import java.io.*;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions
 * @Author: Le Nhut Nam
 * @Date: 26/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class FavoriteWordList {
    private TreeSet<String> favoriteWordList;

    public FavoriteWordList() {
        Collator collator = Collator.getInstance(new Locale("VN"));
        this.favoriteWordList = new TreeSet<>(collator);
    }

    public TreeSet<String> getFavoriteWordList() {
        return favoriteWordList;
    }

    public ArrayList<String> getList() {
        return new ArrayList<String>(this.favoriteWordList);
    }

    public void clear() {
        this.favoriteWordList.clear();
    }

    public void addFavoriteWord(String favoriteWord) {
        this.favoriteWordList.add(favoriteWord);
    }

    public void removeWord(String word){
        this.favoriteWordList.remove(word);
    }

    public void loadFavoriteListFromFile(String fileName) throws IOException {
        this.clear();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(true) {
            assert bufferedReader != null;
            String stringLine = bufferedReader.readLine();
            if (stringLine == null || stringLine.equals("") || stringLine.length() == 0){
                break;
            }
            stringLine = stringLine.trim();
            this.addFavoriteWord(stringLine);
        }
        bufferedReader.close();
    }

    public void saveFavoriteList(String fileName) throws IOException {
        BufferedWriter bufferedWriter = null;

        bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        for(String favoriteWord : favoriteWordList){
            bufferedWriter.write(favoriteWord);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
