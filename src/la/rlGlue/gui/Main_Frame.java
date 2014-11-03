package la.rlGlue.gui;

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
    private JButton stateviewerButton;
    private JButton setRewardsButton;
    private JButton statisticsButton;
    private JTabbedPane tabbedPane1;

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
        stateviewerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State_Frame.main(null);
            }
        });
        setRewardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reward_Frame.main(null);
            }
        });
        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statistic_Frame.main(null);
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
