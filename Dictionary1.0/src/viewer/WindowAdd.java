package viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowAdd extends JFrame{
    private JButton btAccept;
    private JButton btCancel;
    private JTextField tfWord;
    private JTextField tfDetail;
    private JLabel lbWord;
    private JLabel lbDetail;
    private JLabel lbHead;

    public WindowAdd() {

        initCompenents();
    }

    private void initCompenents() {
        Container c = getContentPane();
        setResizable(false);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add");
        setBounds(600,300,400,200);

        btAccept = new JButton("Accept");
        btCancel = new JButton("Cancel");
        btAccept.setBounds(75,130,100,20);
        btAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btAcceptActionListener(e);
            }
        });
        btCancel.setBounds(200, 130,100,20);
        btCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelActionListener(e);
            }
        });
        c.add(btAccept);
        c.add(btCancel);

        lbWord = new JLabel("Word");
        lbWord.setBounds(15,50,50,20);
        lbDetail = new JLabel("Explain");
        lbDetail.setBounds(15,90,50,20);
        c.add(lbDetail);
        c.add(lbWord);

        
        tfWord = new JTextField();
        tfDetail = new JTextField();
        tfWord.setBounds(75, 50,225,20);
        tfDetail.setBounds(75, 90, 225,20);
        c.add(tfDetail);
        c.add(tfWord);

        lbHead = new JLabel("Add the word!!");
        Font font = new Font("Tahoma",1,20);
        lbHead.setBounds(100,0,200,40);
        lbHead.setFont(font);
        lbHead.setForeground(Color.BLUE);
        c.add(lbHead);
    }

    private void btCancelActionListener(ActionEvent e) {
        int click = JOptionPane.showConfirmDialog(null, "Do you want to exit.", "Question", JOptionPane.YES_NO_OPTION);
        if (click == JOptionPane.YES_OPTION) {
            super.dispose();
        }
    }

    private void btAcceptActionListener(ActionEvent e) {
        String word = tfWord.getText().trim().toLowerCase();
        String detail = tfDetail.getText().trim().toLowerCase();

        if(word.equals("") || detail.equals(""))
            JOptionPane.showMessageDialog(null,"You must import the word and explain!!!","Error",JOptionPane.ERROR_MESSAGE);
        else if(Dictionary.map.get(word) != null)
            JOptionPane.showMessageDialog(null,"The word existed in Dictionary ","Error",JOptionPane.ERROR_MESSAGE);
        else {
            JOptionPane.showMessageDialog(null, "The word added in Dictionary!!!");
            Dictionary.map.put(word, detail);
            dispose();
        }
    }

}
