package la.gui;

import context.ManagerFactory;
import la.application.Fassade.RLGlueService;
import la.common.*;
import la.persistence.database.Database;
import la.persistence.database.impl.DatabaseImpl;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import jdk.nashorn.internal.runtime.regexp.joni.Config;


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
    private JTabbedPane visionFieldTabbedPane;
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
    private JButton randomSeedButton;
    private JTextField fpsTextField;
    private JLabel winLable;
    private JLabel lossLable;
    private JLabel hurtLable;
    private JLabel stompLable;
    private JLabel frameLable;
    private JLabel rightLable;
    private JLabel leftLable;
    private JLabel upLable;
    private JLabel downLable;
    private JCheckBox randomLevelsCheckBox;
    private JButton saveVisionButton;
    private JTextField textField06bl;
    private JTextField textField05bl;
    private JTextField textField04bl;
    private JTextField textField03bl;
    private JTextField textField02bl;
    private JTextField textField01bl;
    private JTextField textField00bl;
    private JTextField textField16bl;
    private JTextField textField15bl;
    private JTextField textField14bl;
    private JTextField textField13bl;
    private JTextField textField12bl;
    private JTextField textField11bl;
    private JTextField textField10bl;
    private JTextField textField26bl;
    private JTextField textField25bl;
    private JTextField textField24bl;
    private JTextField textField23bl;
    private JTextField textField22bl;
    private JTextField textField21bl;
    private JTextField textField20bl;
    private JTextField textField36bl;
    private JTextField textField35bl;
    private JTextField textField34bl;
    private JTextField textField33bl;
    private JTextField textField32bl;
    private JTextField textField31bl;
    private JTextField textField30bl;
    private JTextField textField46bl;
    private JTextField textField45bl;
    private JTextField textField44bl;
    private JTextField textField43bl;
    private JTextField textField42bl;
    private JTextField textField41bl;
    private JTextField textField40bl;
    private JTextField textField56bl;
    private JTextField textField55bl;
    private JTextField textField54bl;
    private JTextField textField53bl;
    private JTextField textField52bl;
    private JTextField textField51bl;
    private JTextField textField50bl;
    private JTextField textField66bl;
    private JTextField textField65bl;
    private JTextField textField64bl;
    private JTextField textField63bl;
    private JTextField textField62bl;
    private JTextField textField61bl;
    private JTextField textField60bl;
    private JTextField textField06br;
    private JTextField textField05br;
    private JTextField textField04br;
    private JTextField textField03br;
    private JTextField textField02br;
    private JTextField textField01br;
    private JTextField textField00br;
    private JTextField textField16br;
    private JTextField textField15br;
    private JTextField textField14br;
    private JTextField textField13br;
    private JTextField textField12br;
    private JTextField textField11br;
    private JTextField textField10br;
    private JTextField textField26br;
    private JTextField textField25br;
    private JTextField textField24br;
    private JTextField textField23br;
    private JTextField textField22br;
    private JTextField textField21br;
    private JTextField textField20br;
    private JTextField textField36br;
    private JTextField textField35br;
    private JTextField textField34br;
    private JTextField textField33br;
    private JTextField textField32br;
    private JTextField textField31br;
    private JTextField textField30br;
    private JTextField textField46br;
    private JTextField textField45br;
    private JTextField textField44br;
    private JTextField textField43br;
    private JTextField textField42br;
    private JTextField textField41br;
    private JTextField textField40br;
    private JTextField textField56br;
    private JTextField textField55br;
    private JTextField textField54br;
    private JTextField textField53br;
    private JTextField textField52br;
    private JTextField textField51br;
    private JTextField textField50br;
    private JTextField textField66br;
    private JTextField textField65br;
    private JTextField textField64br;
    private JTextField textField63br;
    private JTextField textField62br;
    private JTextField textField61br;
    private JTextField textField60br;
    private JTextField textField06e;
    private JTextField textField05e;
    private JTextField textField04e;
    private JTextField textField03e;
    private JTextField textField02e;
    private JTextField textField01e;
    private JTextField textField00e;
    private JTextField textField16e;
    private JTextField textField15e;
    private JTextField textField14e;
    private JTextField textField13e;
    private JTextField textField12e;
    private JTextField textField11e;
    private JTextField textField10e;
    private JTextField textField26e;
    private JTextField textField25e;
    private JTextField textField24e;
    private JTextField textField23e;
    private JTextField textField22e;
    private JTextField textField21e;
    private JTextField textField20e;
    private JTextField textField36e;
    private JTextField textField35e;
    private JTextField textField34e;
    private JTextField textField33e;
    private JTextField textField32e;
    private JTextField textField31e;
    private JTextField textField30e;
    private JTextField textField46e;
    private JTextField textField45e;
    private JTextField textField44e;
    private JTextField textField43e;
    private JTextField textField42e;
    private JTextField textField41e;
    private JTextField textField40e;
    private JTextField textField56e;
    private JTextField textField55e;
    private JTextField textField54e;
    private JTextField textField53e;
    private JTextField textField52e;
    private JTextField textField51e;
    private JTextField textField50e;
    private JTextField textField66e;
    private JTextField textField65e;
    private JTextField textField64e;
    private JTextField textField63e;
    private JTextField textField62e;
    private JTextField textField61e;
    private JTextField textField60e;
    private JTextField textField06de;
    private JTextField textField05de;
    private JTextField textField04de;
    private JTextField textField03de;
    private JTextField textField02de;
    private JTextField textField01de;
    private JTextField textField00de;
    private JTextField textField16de;
    private JTextField textField15de;
    private JTextField textField14de;
    private JTextField textField13de;
    private JTextField textField12de;
    private JTextField textField11de;
    private JTextField textField10de;
    private JTextField textField26de;
    private JTextField textField25de;
    private JTextField textField24de;
    private JTextField textField23de;
    private JTextField textField22de;
    private JTextField textField21de;
    private JTextField textField20de;
    private JTextField textField36de;
    private JTextField textField35de;
    private JTextField textField34de;
    private JTextField textField33de;
    private JTextField textField32de;
    private JTextField textField31de;
    private JTextField textField30de;
    private JTextField textField46de;
    private JTextField textField45de;
    private JTextField textField44de;
    private JTextField textField43de;
    private JTextField textField42de;
    private JTextField textField41de;
    private JTextField textField40de;
    private JTextField textField56de;
    private JTextField textField55de;
    private JTextField textField54de;
    private JTextField textField53de;
    private JTextField textField52de;
    private JTextField textField51de;
    private JTextField textField50de;
    private JTextField textField66de;
    private JTextField textField65de;
    private JTextField textField64de;
    private JTextField textField63de;
    private JTextField textField62de;
    private JTextField textField61de;
    private JTextField textField60de;
    private RLGlueService rlGlueService;

    private Database db;

    private ArrayList<JTextField> blList = new ArrayList<>();
    private ArrayList<JTextField> brList = new ArrayList<>();
    private ArrayList<JTextField> eList = new ArrayList<>();
    private ArrayList<JTextField> deList = new ArrayList<>();

    public Main_Frame() {
        rlGlueService = ManagerFactory.getManager(RLGlueService.class);
        episodesTextField.setText("10");
        seedTextField.setText("0");
        fpsTextField.setText("1000");
        winTextField.setText(String.valueOf(rlGlueService.getCurrentReward().getReward(winTextField.getName()).getReward()));
        lossTextField.setText(String.valueOf(rlGlueService.getCurrentReward().getReward(lossTextField.getName()).getReward()));
        hurtTextField.setText(String.valueOf(rlGlueService.getCurrentReward().getReward(hurtTextField.getName()).getReward()));
        rightTextField.setText(String.valueOf(rlGlueService.getCurrentReward().getReward(rightTextField.getName()).getReward()));
        leftTextField.setText(String.valueOf(rlGlueService.getCurrentReward().getReward(leftTextField.getName()).getReward()));
        stompTextField.setText(String.valueOf(rlGlueService.getCurrentReward().getReward(stompTextField.getName()).getReward()));
        frameTextField.setText(String.valueOf(rlGlueService.getCurrentReward().getReward(frameTextField.getName()).getReward()));
        upTextField.setText(String.valueOf(rlGlueService.getCurrentReward().getReward(upTextField.getName()).getReward()));
        downTextField.setText(String.valueOf(rlGlueService.getCurrentReward().getReward(downTextField.getName()).getReward()));

        db = ManagerFactory.getManager(Database.class);

        if(db.getLastRewardsGroup() == null) {
            rlGlueService.saveRewards(rlGlueService.getRewards());
        }

        rlGlueService.setRewards(db.getLastRewardsGroup().getRewards());

        for(String s: rlGlueService.getAllMarioModes()) {
            startmodeComboBox.addItem(s);
        }

        for(int i : rlGlueService.getAllDifficulties()) {
            difficultyComboBox.addItem(i);
        }

        for(String s : rlGlueService.getAllAgents()) {
            agentComboBox.addItem(s);
        }

        rlGlueService.setVisionField(createDefaultVisionField());

        blList.add(textField06bl);
        blList.add(textField05bl);
        blList.add(textField04bl);
        blList.add(textField03bl);
        blList.add(textField02bl);
        blList.add(textField01bl);
        blList.add(textField00bl);
        blList.add(textField16bl);
        blList.add(textField15bl);
        blList.add(textField14bl);
        blList.add(textField13bl);
        blList.add(textField12bl);
        blList.add(textField11bl);
        blList.add(textField10bl);
        blList.add(textField26bl);
        blList.add(textField25bl);
        blList.add(textField24bl);
        blList.add(textField23bl);
        blList.add(textField22bl);
        blList.add(textField21bl);
        blList.add(textField20bl);
        blList.add(textField36bl);
        blList.add(textField35bl);
        blList.add(textField34bl);
        blList.add(textField33bl);
        blList.add(textField32bl);
        blList.add(textField31bl);
        blList.add(textField30bl);
        blList.add(textField46bl);
        blList.add(textField45bl);
        blList.add(textField44bl);
        blList.add(textField43bl);
        blList.add(textField42bl);
        blList.add(textField41bl);
        blList.add(textField40bl);
        blList.add(textField56bl);
        blList.add(textField55bl);
        blList.add(textField54bl);
        blList.add(textField53bl);
        blList.add(textField52bl);
        blList.add(textField51bl);
        blList.add(textField50bl);
        blList.add(textField66bl);
        blList.add(textField65bl);
        blList.add(textField64bl);
        blList.add(textField63bl);
        blList.add(textField62bl);
        blList.add(textField61bl);
        blList.add(textField60bl);

        brList.add(textField06br);
        brList.add(textField05br);
        brList.add(textField04br);
        brList.add(textField03br);
        brList.add(textField02br);
        brList.add(textField01br);
        brList.add(textField00br);
        brList.add(textField16br);
        brList.add(textField15br);
        brList.add(textField14br);
        brList.add(textField13br);
        brList.add(textField12br);
        brList.add(textField11br);
        brList.add(textField10br);
        brList.add(textField26br);
        brList.add(textField25br);
        brList.add(textField24br);
        brList.add(textField23br);
        brList.add(textField22br);
        brList.add(textField21br);
        brList.add(textField20br);
        brList.add(textField36br);
        brList.add(textField35br);
        brList.add(textField34br);
        brList.add(textField33br);
        brList.add(textField32br);
        brList.add(textField31br);
        brList.add(textField30br);
        brList.add(textField46br);
        brList.add(textField45br);
        brList.add(textField44br);
        brList.add(textField43br);
        brList.add(textField42br);
        brList.add(textField41br);
        brList.add(textField40br);
        brList.add(textField56br);
        brList.add(textField55br);
        brList.add(textField54br);
        brList.add(textField53br);
        brList.add(textField52br);
        brList.add(textField51br);
        brList.add(textField50br);
        brList.add(textField66br);
        brList.add(textField65br);
        brList.add(textField64br);
        brList.add(textField63br);
        brList.add(textField62br);
        brList.add(textField61br);
        brList.add(textField60br);

        eList.add(textField06e);
        eList.add(textField05e);
        eList.add(textField04e);
        eList.add(textField03e);
        eList.add(textField02e);
        eList.add(textField01e);
        eList.add(textField00e);
        eList.add(textField16e);
        eList.add(textField15e);
        eList.add(textField14e);
        eList.add(textField13e);
        eList.add(textField12e);
        eList.add(textField11e);
        eList.add(textField10e);
        eList.add(textField26e);
        eList.add(textField25e);
        eList.add(textField24e);
        eList.add(textField23e);
        eList.add(textField22e);
        eList.add(textField21e);
        eList.add(textField20e);
        eList.add(textField36e);
        eList.add(textField35e);
        eList.add(textField34e);
        eList.add(textField33e);
        eList.add(textField32e);
        eList.add(textField31e);
        eList.add(textField30e);
        eList.add(textField46e);
        eList.add(textField45e);
        eList.add(textField44e);
        eList.add(textField43e);
        eList.add(textField42e);
        eList.add(textField41e);
        eList.add(textField40e);
        eList.add(textField56e);
        eList.add(textField55e);
        eList.add(textField54e);
        eList.add(textField53e);
        eList.add(textField52e);
        eList.add(textField51e);
        eList.add(textField50e);
        eList.add(textField66e);
        eList.add(textField65e);
        eList.add(textField64e);
        eList.add(textField63e);
        eList.add(textField62e);
        eList.add(textField61e);
        eList.add(textField60e);

        deList.add(textField06de);
        deList.add(textField05de);
        deList.add(textField04de);
        deList.add(textField03de);
        deList.add(textField02de);
        deList.add(textField01de);
        deList.add(textField00de);
        deList.add(textField16de);
        deList.add(textField15de);
        deList.add(textField14de);
        deList.add(textField13de);
        deList.add(textField12de);
        deList.add(textField11de);
        deList.add(textField10de);
        deList.add(textField26de);
        deList.add(textField25de);
        deList.add(textField24de);
        deList.add(textField23de);
        deList.add(textField22de);
        deList.add(textField21de);
        deList.add(textField20de);
        deList.add(textField36de);
        deList.add(textField35de);
        deList.add(textField34de);
        deList.add(textField33de);
        deList.add(textField32de);
        deList.add(textField31de);
        deList.add(textField30de);
        deList.add(textField46de);
        deList.add(textField45de);
        deList.add(textField44de);
        deList.add(textField43de);
        deList.add(textField42de);
        deList.add(textField41de);
        deList.add(textField40de);
        deList.add(textField56de);
        deList.add(textField55de);
        deList.add(textField54de);
        deList.add(textField53de);
        deList.add(textField52de);
        deList.add(textField51de);
        deList.add(textField50de);
        deList.add(textField66de);
        deList.add(textField65de);
        deList.add(textField64de);
        deList.add(textField63de);
        deList.add(textField62de);
        deList.add(textField61de);
        deList.add(textField60de);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rlGlueService.setVisualisation(visualisationCheckBox.isSelected());
                rlGlueService.setRandomLevels(randomLevelsCheckBox.isSelected());
                rlGlueService.setFreezPolicy(freezPolicyCheckBox.isSelected());
                rlGlueService.setExploration(noExplorationCheckBox.isSelected());
                rlGlueService.setStartMode(startmodeComboBox.getSelectedIndex());
                rlGlueService.setDifficult(difficultyComboBox.getSelectedIndex());
                rlGlueService.setAgent(agentComboBox.getSelectedItem().toString());
                rlGlueService.setEpisodes(Integer.parseInt(episodesTextField.getText()));
                rlGlueService.setLevelSeed(Integer.parseInt(seedTextField.getText()));
                rlGlueService.setFPS(Integer.parseInt(fpsTextField.getText()));


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
            public void actionPerformed(ActionEvent e)  {
                try {
                    int winsInt  = Integer.parseInt(winTextField.getText());
                    int lossInt  = Integer.parseInt(lossTextField.getText());
                    int hurtInt  = Integer.parseInt(hurtTextField.getText());
                    int stompInt = Integer.parseInt(stompTextField.getText());
                    int frameInt = Integer.parseInt(frameTextField.getText());
                    int rightInt = Integer.parseInt(rightTextField.getText());
                    int leftInt  = Integer.parseInt(leftTextField.getText());
                    int upInt    = Integer.parseInt(upTextField.getText());
                    int downInt  = Integer.parseInt(downTextField.getText());

                    List<Reward> rewards = new ArrayList<>();
                    rewards.add(new Reward(winLable.getText(),winsInt));
                    rewards.add(new Reward(lossLable.getText(),lossInt));
                    rewards.add(new Reward(hurtLable.getText(),hurtInt));
                    rewards.add(new Reward(stompLable.getText(),stompInt));
                    rewards.add(new Reward(frameLable.getText(),frameInt));
                    rewards.add(new Reward(rightLable.getText(),rightInt));
                    rewards.add(new Reward(leftLable.getText(),leftInt));
                    rewards.add(new Reward(upLable.getText(),upInt));
                    rewards.add(new Reward(downLable.getText(),downInt));

                    rlGlueService.saveRewards(rewards);
                    rlGlueService.setRewards(db.getLastRewardsGroup().getRewards());
                }catch (NumberFormatException nfe){

                }

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
        randomSeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random generator = new Random();
                seedTextField.setText(""+generator.nextInt());
            }
        });
        saveVisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Zone> visionField = new ArrayList<Zone>();
                visionField = createVisionField();

                rlGlueService.setVisionField(visionField);
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

    private ArrayList<Zone> createVisionField() {
        HashMap<Integer,ArrayList<Block>> blZones = createZone(blList);
        HashMap<Integer,ArrayList<Block>> brZones = createZone(brList);
        HashMap<Integer,ArrayList<Block>> eZones = createZone(eList);
        HashMap<Integer,ArrayList<Block>> deZones = createZone(deList);

        ArrayList<Zone> zones = new ArrayList<>();
        Set<Integer> blSet = blZones.keySet();
        Set<Integer> brSet = brZones.keySet();
        Set<Integer> eSet = eZones.keySet();
        Set<Integer> deSet = deZones.keySet();
        for (Integer i : blSet) {
            zones.add(new Zone(blZones.get(i), Type.BLOCK));
        }
        for (Integer i : brSet) {
            zones.add(new Zone(brZones.get(i), Type.BRIDGE));
        }
        for (Integer i : eSet) {
            zones.add(new Zone(eZones.get(i), Type.ENEMY));
        }
        for (Integer i : deSet) {
            zones.add(new Zone(deZones.get(i), Type.DETAILEDENEMY));
        }

        int envExp = eSet.size() + deSet.size();
        int marioExp = envExp + blSet.size() + brSet.size();

        rlGlueService.setEnvironmentMul(exp(envExp));
        rlGlueService.setMarioMul(exp(marioExp));

        return zones;
    }

    private HashMap<Integer,ArrayList<Block>> createZone(ArrayList<JTextField> textFields) {
        HashMap<Integer,ArrayList<Block>> zones = new HashMap<>();
        for (JTextField textField : textFields) {
            if (textField.getText() != "") {
                if (zones.get(Integer.parseInt(textField.getText())) != null) {
                    Block block = new Block((Integer.parseInt(textField.getName().substring(9,10))+(-3)),(Integer.parseInt(textField.getName().substring(10,11))+(-2)));
                    ArrayList<Block> blockList = zones.get(Integer.parseInt(textField.getText()));
                    blockList.add(block);
                    zones.put(Integer.parseInt(textField.getText()),blockList);
                } else {
                    ArrayList<Block> blockList = new ArrayList<>();
                    Block block = new Block((Integer.parseInt(textField.getName().substring(9,10))+(-3)),(Integer.parseInt(textField.getName().substring(10,11))+(-2)));
                    blockList.add(block);
                    zones.put(Integer.parseInt(textField.getText()),blockList);
                }
            }
        }
        return zones;
    }

    private long exp(int x) {
        long res = 1;
        for (int i=0;i<x;i++) {
            res *= 10;
        }
        return res;
    }

    private ArrayList<Zone> createDefaultVisionField(){

        Block s1b1 = new Block(0,4);
        Block s1b2 = new Block(0,3);
        Block s1b3 = new Block(0,2);

        Block s2b1 = new Block(1,2);

        Block s3b1 = new Block(1,1);

        Block s4b1 = new Block(1,0);

        Block s5b1 = new Block(1,-1);
        Block s5b2 = new Block(1,-2);

        Block s6b1 = new Block(0,-1);

        Block s7b1 = new Block(-1,2);

        Block s8b1 = new Block(0,2);

        Block s9b1 = new Block(1,2);

        Block e1b1 = new Block(0,2);
        Block e1b2 = new Block(1,2);

        Block e2b1 = new Block(1,1);
        Block e2b2 = new Block(1,0);
        Block e2b3 = new Block(1,-1);

        Block e3b1 = new Block(2,1);
        Block e3b2 = new Block(2,0);
        Block e3b3 = new Block(2,-1);

        Block e4b1 = new Block(0,-1);

        Block e5b1 = new Block(-1,1);
        Block e5b2 = new Block(-1,0);

        Block e6b1 = new Block(0,0);
        Block e6b2 = new Block(0,1);

        ArrayList<Block> s1b = new ArrayList<>();
        s1b.add(s1b1);
        s1b.add(s1b2);
        s1b.add(s1b3);

        ArrayList<Block> s2b = new ArrayList<>();
        s2b.add(s2b1);

        ArrayList<Block> s3b = new ArrayList<>();
        s3b.add(s3b1);

        ArrayList<Block> s4b = new ArrayList<>();
        s4b.add(s4b1);

        ArrayList<Block> s5b = new ArrayList<>();
        s5b.add(s5b1);
        s5b.add(s5b2);

        ArrayList<Block> s6b = new ArrayList<>();
        s6b.add(s6b1);

        ArrayList<Block> s7b = new ArrayList<>();
        s7b.add(s7b1);

        ArrayList<Block> s8b = new ArrayList<>();
        s8b.add(s8b1);

        ArrayList<Block> s9b = new ArrayList<>();
        s9b.add(s9b1);

        ArrayList<Block> e1b = new ArrayList<>();
        e1b.add(e1b1);
        e1b.add(e1b2);

        ArrayList<Block> e2b = new ArrayList<>();
        e2b.add(e2b1);
        e2b.add(e2b2);
        e2b.add(e2b3);

        ArrayList<Block> e3b = new ArrayList<>();
        e3b.add(e3b1);
        e3b.add(e3b2);
        e3b.add(e3b3);

        ArrayList<Block> e4b = new ArrayList<>();
        e4b.add(e4b1);

        ArrayList<Block> e5b = new ArrayList<>();
        e5b.add(e5b1);
        e5b.add(e5b2);

        ArrayList<Block> e6b = new ArrayList<>();
        e5b.add(e6b1);
        e5b.add(e6b2);

        Zone s1 = new Zone(s1b, Type.BRIDGE);
        Zone s2 = new Zone(s2b, Type.BLOCK);
        Zone s3 = new Zone(s3b, Type.BLOCK);
        Zone s4 = new Zone(s4b, Type.BLOCK);
        Zone s5 = new Zone(s5b, Type.BLOCK);
        Zone s6 = new Zone(s6b, Type.BLOCK);
        Zone s7 = new Zone(s7b, Type.BLOCK);
        Zone s8 = new Zone(s8b, Type.BLOCK);
        Zone s9 = new Zone(s9b, Type.BLOCK);

        Zone e1 = new Zone(e1b, Type.ENEMY);
        Zone e2 = new Zone(e2b, Type.DETAILEDENEMY);
        Zone e3 = new Zone(e3b, Type.DETAILEDENEMY);
        Zone e4 = new Zone(e4b, Type.DETAILEDENEMY);
        Zone e5 = new Zone(e5b, Type.ENEMY);
        Zone e6 = new Zone(e6b, Type.ENEMY);

        ArrayList<Zone> visionField = new ArrayList<>();

        visionField.add(s1);
        visionField.add(s2);
        visionField.add(s3);
        visionField.add(s4);
        visionField.add(s5);
        visionField.add(s6);
        visionField.add(s7);
        visionField.add(s8);
        visionField.add(s9);

        visionField.add(e1);
        visionField.add(e2);
        visionField.add(e3);
        visionField.add(e4);
        visionField.add(e5);
        visionField.add(e6);

        return visionField;

    }

}
