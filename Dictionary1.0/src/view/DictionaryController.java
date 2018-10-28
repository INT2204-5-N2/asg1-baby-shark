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
            FileInputStream fileInPutStream = new FileInputStream(pathname);
            Reader reader = new InputStreamReader(fileInPutStream, "utf8");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split("\t",2);
                if (temp.length == 2){
                dictionary.put(temp[0],temp[1]);}
            }
            fileInPutStream.close();
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void setDataToFile(TreeMap<String,String> dictionary){
        try {
            File file = new File("/Dictionary1.0/src/data/dict.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String key: dictionary.keySet()) {
                bw.write(key + "\t" + Dictionary.getDetail(key) + "\n");
            }
            fw.close();
            bw.close();
        } catch (IOException e){

        } catch (Exception e){

        }
    }
    public static void insert1WordToFile(String word, String detail) {
        try {
            File file = new File("/Dictionary1.0/src/data/dict.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(word + "\t" + detail);
            fw.close();
            pw.close();
        } catch (IOException e){

        } catch (Exception e){

        }
    }
    public static void addWord(String word,String detail) {
        dictionary.put(word,detail);
        Dictionary.insert1WordToFile(word,detail);
    }
    public static void removeWord(String word){
        dictionary.remove(word);
        setDataToFile(dictionary);
    }
    public static void editWord(String word, String detail){
        dictionary.remove(word);
        dictionary.put(word,detail);
        setDataToFile(dictionary);
    }
    public static String getDetail(String word){
        if (dictionary.get(word) != null){
        String detail = "";
        String[] temp = dictionary.get(word).split("\t");
        for (int i=0; i<temp.length; i++)
        {
            detail = detail.concat(temp[i]+"\n");
        }
        return detail;}
        else return null;
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
