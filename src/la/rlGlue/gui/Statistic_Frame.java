package la.rlGlue.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Benjamin on 29.10.2014.
 */
public class Statistic_Frame {
    private JPanel statistic_frame;
    private JTextField pathTextField;
    private JButton exportButton;

    public Statistic_Frame() {
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: export Button
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Statistic_Frame");
        frame.setContentPane(new Statistic_Frame().statistic_frame);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
