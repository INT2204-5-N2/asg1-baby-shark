package data;

import java.io.*;
import java.util.TreeMap;

public class IOData {
    public TreeMap<String,String> getFile(String pathname) {
        TreeMap<String,String> listWord = new TreeMap<String, String>();
        try {
            File f = new File(pathname);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split("\\t",2);
                listWord.put(temp[0],temp[1]);
            }
            fr.close();
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return listWord;
    }
}
