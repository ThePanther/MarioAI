package la.rlGlue.gui;

import ch.idsia.scenarios.Play;
import com.sun.xml.internal.bind.v2.TODO;
import la.rlGlue.application.Fassade.RLGlueService;
import la.rlGlue.application.rlmarioaimanagement.Config;
import la.rlGlue.common.State;
import la.rlGlue.persistence.database.Database;
import la.rlGlue.persistence.database.impl.DatabaseImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Frame {
    private JButton startButton;
    private JPanel main_frame;
    private JCheckBox visualisationCheckBox;
    private JComboBox startmodeComboBox;
    private JTextField episodesTextField;
    private JComboBox difficultyComboBox;
    private JComboBox agentComboBox;
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
                Config.VISUALIZATION = visualisationCheckBox.isSelected();
                Config.FREEZE_POLICY = freezPolicyCheckBox.isSelected();
                Config.FREEZE_EXPLORATION = noExplorationCheckBox.isSelected();
                Config.MARIO_STARTMODE = startmodeComboBox.getSelectedIndex();
                Config.DIFFICULTY = difficultyComboBox.getSelectedIndex();
                Config.AGENT = (String) agentComboBox.getSelectedItem();
                Config.EDISODES = Integer.getInteger(episodesTextField.getText());
                Config.LEVEL_SEED = Integer.getInteger(seedTextField.getText());

                RLGlueService.startAgent();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] args = new String[0];
                Play.main(args);
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
                State state = new State(Long.getLong(modeTextField.getText()),Long.getLong(sceneTextField.getText()),Long.getLong(enemyTextField.getText()));
                DatabaseImpl db = new DatabaseImpl();
                double[] values = db.select(state,db.getLastRewardsGroup());
                a1TextField.setText(""+values[0]);
                a2TextField.setText(""+values[1]);
                a3TextField.setText(""+values[2]);
                a4TextField.setText(""+values[3]);
                a5TextField.setText(""+values[4]);
                a6TextField.setText(""+values[5]);
                a7TextField.setText(""+values[6]);
                a8TextField.setText(""+values[7]);
                a9TextField.setText(""+values[8]);
                a10TextField.setText(""+values[9]);
                a11TextField.setText(""+values[10]);
                a12TextField.setText(""+values[11]);
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
                    DatabaseImpl db = new DatabaseImpl();
                    db.reset();
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
