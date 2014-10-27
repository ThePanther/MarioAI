package la.rlGlue.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Frame {
    private JButton startButton;
    private JTextField textField1;
    private JPanel main;

    public Main_Frame() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("Hallo");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main_Frame");
        frame.setContentPane(new Main_Frame().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
