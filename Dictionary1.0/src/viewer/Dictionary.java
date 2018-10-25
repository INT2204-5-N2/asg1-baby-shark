package viewer;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.TreeMap;

public class Dictionary extends JFrame{
    //Variables declaration
    private JLabel lb1;
    private JTextField tfSearch;
    private JButton btSearch;
    private JButton btAdd;
    private JButton btCorrect;
    private JButton btRemove;
    private JButton btClean;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea text;
    private JList<String> jlistWord;
    private JButton btSpeak;
    private JLabel background;
    private JLabel lbHead;
    //End of variables

    private DefaultListModel<String> model;
    public static TreeMap<String,String> map;
    //private IOData data = new IOData();
    private JButton button1;
    private JPanel panel1;
    public Dictionary(){
        //map = data.getFile("dict.txt");
        initCompenents();
    }

    private void initCompenents() {
        Container c = getContentPane();
        setResizable(false);
        tfSearch = new JTextField();
        tfSearch.setBounds(5,70,150,20);
        tfSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfSearchActionPerformed(e);
            }
        });
        tfSearch.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tfSearchKeyPressed(e);
            }
        });
        tfSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tfSearchMouseClicked(e);
            }
        });

        lbHead = new JLabel("ENG - VIET DICTIONARY");
        Font font = new Font("Tahoma",1,20);
        lbHead.setBounds(200,0,400,40);
        lbHead.setFont(font);
        lbHead.setForeground(Color.BLUE);
        c.add(lbHead);

        lb1 = new JLabel("Please typed the word:");
        lb1.setForeground(Color.DARK_GRAY);
        lb1.setBounds(5,45,200,20);

        btSpeak = new JButton();
        btSpeak.setBounds(160,230,20,20);
        btSpeak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSpeakActionPerformed(e);
            }
        });
        c.add(btSpeak);

        btSearch = new JButton(new ImageIcon(getClass().getResource("/photo/Search.png")));
        btSearch.setBounds(160,70, 20,20);
        btSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btSearchActionPerformed(e);
            }
        });
        ;

        btAdd = new JButton(new ImageIcon(getClass().getResource("/photo/Icon_Add.png")));
        btAdd.setBounds(160,130,20,20);
        btAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btAddActionPerformed(e);
            }
        });

        btCorrect = new JButton(new ImageIcon(getClass().getResource("/photo/Edit.png")));
        btCorrect.setBounds(160,160,20,20);
        btCorrect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCorrectActionPerformed(e);
            }
        });

        btRemove = new JButton(new ImageIcon(getClass().getResource("/photo/Icon_Delete.png")));
        btRemove.setBounds(160,190,20,20);
        btRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btRemoveActionPerformed(e);
            }
        });


        btClean = new JButton(new ImageIcon(getClass().getResource("/photo/Icon_Clean.png")));
        btClean.setBounds(160,100,20,20);
        btClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCleanActionPerformed(e);
            }
        });


        text = new JTextArea("");
        text.setColumns(10);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setRows(5);
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(text);
        jScrollPane1.setBounds(190,70, 500, 280);

        model = new DefaultListModel<>();
        jlistWord = new JList<>();
        jlistWord.setBounds(5,100,150,250);
        jlistWord.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                jlistWordValueChanged(e);
            }
        });
        jScrollPane2 = new JScrollPane();
        jScrollPane2.setViewportView(jlistWord);
        jScrollPane2.setBounds(5,100, 150,250);

        c.add(tfSearch);
        c.add(lb1);
        c.add(btSearch);
        c.add(btClean);
        c.add(btAdd);
        c.add(btCorrect);
        c.add(btRemove);
        c.add(jScrollPane1);
        c.add(jScrollPane2);

        setTitle("BabyShark Dictionary");
        setSize(700,400);
        setLocation(500,200);
        setLayout(null);
        //pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void btRemoveActionPerformed(ActionEvent e) {
        new WindowDelete();
    }

    private void btCorrectActionPerformed(ActionEvent e) {
        new WindowEdit();
    }

    private void btSpeakActionPerformed(ActionEvent e) {
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = vm.getVoice("kevin16");
        voice.allocate();
        voice.speak(tfSearch.getText());
        voice.deallocate();
    }

    private void btCleanActionPerformed(ActionEvent e) {
        text.setText("");
        tfSearch.setText("");
        initData("");
    }

    private void jlistWordValueChanged(ListSelectionEvent e) {
        try {
            String word = jlistWord.getSelectedValue();
            text.setText(map.get(word));
        } catch (NullPointerException e1){
            model.removeAllElements();
        }
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

    private void tfSearchKeyPressed(KeyEvent e) {
        //TODO: add code here
        try {
            String str = tfSearch.getText();
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (str != null)
                    initData(str.substring(0, str.length() - 1));
            } else {
                char ch = e.getKeyChar();
                if ((ch >= 'a' && ch <='z') || (ch >= 'A' && ch <='Z')) str = str + ch;
                initData(str);
            }
        } catch (StringIndexOutOfBoundsException e1){
            tfSearch.setText("");
        }
    }

    private void tfSearchMouseClicked(MouseEvent e){
        //TODO: add code here
        tfSearch.setText("");
        text.setText("");
        model.removeAllElements();
        //jScrollPane2.getVerticalScrollBar().setValue(0);
    }
    private void btSearchActionPerformed(ActionEvent e){
        //TODO: add code here
        text.setText("");
        String str = tfSearch.getText().trim().toLowerCase();
        if (!str.equals("")) {
            if (map.get(str) == null) {
                JOptionPane.showMessageDialog(null, "Not found the word!!!");
            } else {
                text.setText(map.get(str));
            }
        }

    }

    private void btAddActionPerformed(ActionEvent e){
        new WindowAdd();
    }

    public void initData(String word) {
        model.removeAllElements();
        if (word.equals("")) {
            jlistWord.setModel(model);
        } else {
            for (String key : map.keySet()) {
                if (key.startsWith(word)) model.addElement(key);
            }
            jlistWord.setModel(model);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Dictionary();
            }
        });
    }

}
