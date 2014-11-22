package la.gui;

import context.ManagerFactory;
import la.application.Fassade.RLGlueService;
import la.common.State;
import la.persistence.database.impl.DatabaseImpl;

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
    private RLGlueService rlGlueService;

    public Main_Frame() {
        rlGlueService = ManagerFactory.getManager(RLGlueService.class);
        episodesTextField.setText("10");
        seedTextField.setText("0");

        for(String s: rlGlueService.getAllMarioModes()) {
            startmodeComboBox.addItem(s);
        }

        for(int i : rlGlueService.getAllDifficulties()) {
            difficultyComboBox.addItem(i);
        }

        for(String s : rlGlueService.getAllAgents()) {
            agentComboBox.addItem(s);
        }


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rlGlueService.setVisualisation(visualisationCheckBox.isSelected());
                rlGlueService.setFreezPolicy(freezPolicyCheckBox.isSelected());
                rlGlueService.setExploration(noExplorationCheckBox.isSelected());
                rlGlueService.setStartMode(startmodeComboBox.getSelectedIndex());
                rlGlueService.setDifficult(difficultyComboBox.getSelectedIndex());
                rlGlueService.setAgent(agentComboBox.getSelectedItem().toString());
                rlGlueService.setEpisodes(Integer.parseInt(episodesTextField.getText()));
                rlGlueService.setLevelSeed(Integer.parseInt(seedTextField.getText()));

                rlGlueService.startAgent();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rlGlueService.playMario();
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
                State state = new State(
                        Long.parseLong(modeTextField.getText()),
                        Long.parseLong(sceneTextField.getText()),
                        Long.parseLong(enemyTextField.getText()));
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
                    rlGlueService.resetDB();
                }
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rlGlueService.exportToPath(pathTextField.getText());
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
}
