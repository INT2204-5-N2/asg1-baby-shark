package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.TreeMap;

public class Dictionary {
    private static TreeMap<String,String> dictionary;
    public static void getDataFromFile(String pathname){
        dictionary = new TreeMap<String, String>();
        try {
            File f = new File(pathname);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split("\t",2);
                String detail = "";
                String[] temp2 = temp[1].split("\t");
                for (int i=0; i<temp2.length; i++)
                {
                    detail = detail.concat(temp2[i]+"\n");
                }
                dictionary.put(temp[0],detail);
            }
            fr.close();
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void addWord(String word,String detail){
        dictionary.put(word,detail);
    }
    public static void removeWord(String word){
        dictionary.remove(word);
    }
    public static void editWord(String word, String detail){
        dictionary.remove(word);
        dictionary.put(word,detail);
    }
    public static String getDetail(String word){
        return dictionary.get(word);
    }
    public static ObservableList<String> getWordSearch(String word){
        ObservableList<String> temp = FXCollections.observableArrayList();
        word = word.trim().toLowerCase();
        for (String key : dictionary.keySet()){
            if (key.startsWith(word)) temp.add(key);
        }
        return temp;
    }
    public static ObservableList<String> getAllWord(){
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (String key : dictionary.keySet()){
            temp.add(key);
        }
        return temp;
    }

}
