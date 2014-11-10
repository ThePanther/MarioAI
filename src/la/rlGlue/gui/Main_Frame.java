package la.rlGlue.gui;

import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Frame {
    private JButton startButton;
    private JPanel main_frame;
    private JCheckBox visualisationCheckBox;
    private JComboBox startmodeComboBox;
    private JTextField episodesTextField;
    private JComboBox difficultyComboBox; //= new JComboBox(difficulties);
    private JComboBox agentComboBox; //= new JComboBox(agents);
    private JCheckBox freezPolicyCheckBox;
    private JCheckBox noExplorationCheckBox;
    private JButton playButton;
    private JLabel difficulty;
    private JTextField seedTextField;
    private JTabbedPane tabbedPane1;
    private JTextField winTextField;
    private JTextField lossTextField;
    private JTextField hurtTextField;
    private JTextField rightTextField;
    private JTextField leftTextField;
    private JTextField stompTextField;
    private JTextField frameTextField;
    private JTextField modeTextField;
    private JTextField sceneTextField;
    private JTextField enemyTextField;
    private JButton saveButton;
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
    private JButton DBResetButton;
    private JTextField pathTextField;
    private JButton exportButton;
    private JTextField upTextField;
    private JTextField downTextField;

    public Main_Frame() {

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: start Button
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: play Button
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Reward save Button
            }
        });
        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Lookup State Button
            }
        });
        DBResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                int confirm = JOptionPane.showOptionDialog(frame,
                        "Are you sure you want to reset the DB?",
                        "Reset Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    //TODO: DB Reset Button
                }
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Export Statistic Button
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main_Frame");
        frame.setContentPane(new Main_Frame().main_frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    private void createUIComponents() {
        String[] modes = {"Small","Large","Fire","Invincible"};
        Integer[] difficulties = {0,1,2,3};
        String[] agents = {"SARSA"};

        startmodeComboBox = new JComboBox(modes);
        difficultyComboBox = new JComboBox(difficulties);
        agentComboBox = new JComboBox(agents);
    }
}
