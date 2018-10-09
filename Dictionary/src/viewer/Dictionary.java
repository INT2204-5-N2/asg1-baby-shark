package viewer;

import data.IOData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Set;
public class Dictionary extends JFrame{
    //Variables declaration
    private JLabel lb1;
    private JTextField tfSearch;
    private JButton btSearch;
    private JButton btAdd;
    private JButton btCorrect;
    private JButton btRemove;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea text;
    private JList<String> jlistWord;
    //End of variables

    private DefaultListModel<String> model;
    private TreeMap<String,String> map;
    private IOData data = new IOData();
    public Dictionary(){
        map = data.getFile("dictionary.txt");
        initCompenents();
    }

    private void initCompenents() {
        Container c = getContentPane();

        tfSearch = new JTextField();
        tfSearch.setBounds(5,70,150,20);
        tfSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfSearchActionPerformed(e);
            }
        });
        tfSearch.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                tfSearchKeyTyped(e);
            }

            public void keyPressed(KeyEvent e) {
                tfSearchKeyPressed(e);
            }
        });
        c.add(tfSearch);

        lb1 = new JLabel("Nhập từ cần tra:");
        lb1.setBounds(5,45,100,20);
        c.add(lb1);

        btSearch = new JButton("Search");
        btSearch.setBounds(160,70, 20,20);
        c.add(btSearch);

        btAdd = new JButton("Add");
        btAdd.setBounds(160,100,20,20);
        c.add(btAdd);

        btCorrect = new JButton("Corect");
        btCorrect.setBounds(160,130,20,20);
        c.add(btCorrect);

        btRemove = new JButton("Remove");
        btRemove.setBounds(160,160,20,20);
        c.add(btRemove);

        text = new JTextArea("");
        text.setColumns(10);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setRows(5);
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(text);
        jScrollPane1.setBounds(190,70, 500, 280);
        c.add(jScrollPane1);

        model = new DefaultListModel<>();
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        while (iter.hasNext())
        {
            model.addElement(iter.next());
        }
        jlistWord = new JList<>(model);
        jlistWord.setBounds(5,100,150,250);
        jScrollPane2 = new JScrollPane();
        jScrollPane2.setViewportView(jlistWord);
        jScrollPane2.setBounds(5,100, 150,250);
        c.add(jScrollPane2);



        setTitle("BabyShark Dictionary");
        setSize(700,400);
        setLayout(null);
        //pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void tfSearchActionPerformed(ActionEvent e) {
        text.setText("");
        String str = tfSearch.getText().trim().toLowerCase();
        if (!str.equals("")) {
            if (map.get(str) == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy từ bạn vừa nhập.");
            } else {
                text.setText(map.get(str));
            }
        }
    }

    private void tfSearchKeyTyped(KeyEvent e) {
        //TODO: add code here
    }

    private void tfSearchKeyPressed(KeyEvent e){
        //TODO: add code here
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dictionary();
            }
        });
    }
}
