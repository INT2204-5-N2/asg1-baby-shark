package viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowEdit extends JFrame{
    private JButton btAccept;
    private JButton btCancel;
    private JLabel lb1;
    private JLabel lb2;
    private JTextField tfWord;
    private JTextField tfDetail;
    private JLabel lbHead;

    public WindowEdit(){
        initCompenents();
    }

    private void initCompenents() {
        Container c = getContentPane();
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Edit");
        setBounds(600,300,400,200);
        setResizable(false);


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

        lb1 = new JLabel("Word : ");
        lb1.setBounds(15,50,60,20);
        lb2 = new JLabel("Explain : ");
        lb2.setBounds(15,90,60,20);
        c.add(lb1);
        c.add(lb2);

        tfWord = new JTextField();
        tfDetail = new JTextField();
        tfWord.setBounds(75, 50,225,20);
        tfDetail.setBounds(75, 90, 225,20);
        c.add(tfDetail);
        c.add(tfWord);

        lbHead = new JLabel("Edit the word!!");
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
        else if(Dictionary.map.get(word) == null)
            JOptionPane.showMessageDialog(null,"The word didn't exist in Dictionary ","Error",JOptionPane.ERROR_MESSAGE);
        else {
            JOptionPane.showMessageDialog(null, "The word edited in Dictionary!!!");
            Dictionary.map.remove(word);
            Dictionary.map.put(word,detail);
            dispose();
        }
    }
    }

