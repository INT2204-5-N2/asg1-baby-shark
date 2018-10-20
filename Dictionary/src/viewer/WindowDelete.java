package viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowDelete extends JFrame{
    private JButton btAccept;
    private JButton btCancel;
    private JTextField tfWord;
    private JTextField tfDetail;
    private JLabel lbWord;
    private JLabel lbDetail;
    private JLabel lbHead;

    public WindowDelete() {

        initCompenents();
    }

    private void initCompenents() {
        Container c = getContentPane();
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add");
        setBounds(600,300,400,170);
        setResizable(false);

        btAccept = new JButton("Accept");
        btCancel = new JButton("Cancel");
        btAccept.setBounds(75,100,100,20);
        btAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btAcceptActionListener(e);
            }
        });
        btCancel.setBounds(200, 100,100,20);
        btCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btCancelActionListener(e);
            }
        });
        c.add(btAccept);
        c.add(btCancel);

        lbWord = new JLabel("Word");
        lbWord.setBounds(15,60,50,20);
        c.add(lbWord);


        tfWord = new JTextField();
        tfWord.setBounds(75, 60,225,20);
        c.add(tfWord);

        lbHead = new JLabel("Delete the word!!");
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

        if(word.equals(""))
            JOptionPane.showMessageDialog(null,"You must import the word!!!","Error",JOptionPane.ERROR_MESSAGE);
        else if(Dictionary.map.get(word) == null)
            JOptionPane.showMessageDialog(null,"The word didd't exist in Dictionary ","Error",JOptionPane.ERROR_MESSAGE);
        else {
            JOptionPane.showMessageDialog(null, "The word deleted in Dictionary!!!");
            Dictionary.map.remove(word);
            dispose();
        }
    }

}
