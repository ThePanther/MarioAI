package la.rlGlue.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Benjamin on 29.10.2014.
 */
public class State_Frame {
    private JPanel state_frame;
    private JTextField modeTextField;
    private JTextField sceneTextField;
    private JTextField enemyTextField;
    private JTextField a1TextField;
    private JTextField a2TextField;
    private JTextField a3TextField;
    private JTextField a4TextField;
    private JTextField a5TextField;
    private JTextField a6TextField;
    private JTextField a7TextField;
    private JTextField a8TextField;
    private JTextField a9TextField;
    private JTextField a10TextField;
    private JTextField a11TextField;
    private JTextField a12TextField;
    private JButton lookupButton;

    public State_Frame() {
        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: lookup Button
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("State_Frame");
        frame.setContentPane(new State_Frame().state_frame);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
