package la.rlGlue.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Benjamin on 29.10.2014.
 */
public class Reward_Frame {
    private JPanel reward_frame;
    private JTextField winTextField;
    private JTextField lossTextField;
    private JTextField hitTextField;
    private JTextField rightTextField;
    private JTextField leftTextField;
    private JTextField stompTextField;
    private JTextField frameTextField;
    private JButton saveButton;


    public Reward_Frame() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: save Button
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reward_Frame");
        frame.setContentPane(new Reward_Frame().reward_frame);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
