package la.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import context.ManagerFactory;
import la.application.Fassade.RLGlueService;
import la.common.Block;
import la.common.Reward;
import la.common.State;
import la.common.Type;
import la.common.Zone;
import la.persistence.database.Database;
import la.persistence.database.impl.DatabaseImpl;

public class MainFrame {
	private JFrame mainFrame = new JFrame();
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel contentPane = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel rewardsPanel = new JPanel();
	private JPanel visionFieldPanel = new JPanel();
	private JPanel lookupPanel = new JPanel();
	private JPanel statisticPanel = new JPanel();
	private JPanel mainCheckBoxPanel = new JPanel();
	private JLabel mainLabel = new JLabel("Mario AI");

	private JCheckBox visualisationCheckBox = new JCheckBox("Visualisation");
    private JCheckBox randomLevelsCheckBox = new JCheckBox("Random Levels");
    private JCheckBox freezPolicyCheckBox = new JCheckBox("Freez Policy");
    private JCheckBox noExplorationCheckBox = new JCheckBox("No Exploration");
    private JTextField episodesTextField = new JTextField("10");
    private JTextField seedTextField = new JTextField("0");
    private JTextField fpsTextField = new JTextField("1000");
    private JButton randomSeedButton = new JButton("Random Seed");
    private JButton startButton = new JButton("Start Agent");
    private JButton playButton = new JButton("Play");
    private JComboBox<String> startmodeComboBox;
    private JComboBox<Integer> difficultyComboBox;
    private JComboBox<String> agentComboBox;

    private JLabel winLable = new JLabel("Win");
    private JLabel lossLable = new JLabel("Loss");
    private JLabel hurtLable = new JLabel("Hurt");
    private JLabel stompLable = new JLabel("Stomp Enemy");
    private JLabel frameLable = new JLabel("per Frame");
    private JLabel rightLable = new JLabel("Move Right");
    private JLabel leftLable = new JLabel("Move Left");
    private JLabel upLable = new JLabel("Move Up");
    private JLabel downLable = new JLabel("Move Down");
    private JButton saveButton = new JButton("Save");
    private JTextField winTextField;
    private JTextField lossTextField;
    private JTextField hurtTextField;
    private JTextField stompTextField;
    private JTextField frameTextField;
    private JTextField rightTextField;
    private JTextField leftTextField;
    private JTextField upTextField;
    private JTextField downTextField;

    private ArrayList<JTextField> blList = new ArrayList<>();
    private ArrayList<JTextField> brList = new ArrayList<>();
    private ArrayList<JTextField> eList = new ArrayList<>();
    private ArrayList<JTextField> deList = new ArrayList<>();
    private JButton saveVisionButton = new JButton("Save");
    private JButton resetToDefaultButton = new JButton("Reset to Default");

    private JButton lookupButton = new JButton("Lookup");
    private JButton DBResetButton = new JButton("DB Reset");
    private JTextField modeTextField = new JTextField();
    private JTextField sceneTextField = new JTextField();
    private JTextField enemyTextField = new JTextField();
    private JTextField a1TextField = new JTextField();
    private JTextField a2TextField = new JTextField();
    private JTextField a3TextField = new JTextField();
    private JTextField a4TextField = new JTextField();
    private JTextField a5TextField = new JTextField();
    private JTextField a6TextField = new JTextField();
    private JTextField a7TextField = new JTextField();
    private JTextField a8TextField = new JTextField();
    private JTextField a9TextField = new JTextField();
    private JTextField a10TextField = new JTextField();
    private JTextField a11TextField = new JTextField();
    private JTextField a12TextField = new JTextField();

    private JButton exportButton = new JButton("Export");
    private JTextField pathTextField = new JTextField();

    private GridBagConstraints leftConstraints = new GridBagConstraints();
    private GridBagConstraints centerConstraints = new GridBagConstraints();
    private GridBagConstraints rightConstraints = new GridBagConstraints();

    private Database db;
    private RLGlueService rlGlueService;

    public MainFrame() {
    	db = ManagerFactory.getManager(Database.class);
    	rlGlueService = ManagerFactory.getManager(RLGlueService.class);

    	if(db.getLastRewardsGroup().getRewards().isEmpty()) {
            rlGlueService.saveRewards(rlGlueService.getRewards());
        }

        rlGlueService.setRewards(db.getLastRewardsGroup().getRewards());

    	contentPane.setLayout(new BorderLayout());
		contentPane.add(mainLabel, BorderLayout.NORTH);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);

		tabbedPane.setPreferredSize(new Dimension(550, 500));
		tabbedPane.addTab("Main", mainPanel);
		tabbedPane.addTab("Set Rewards", rewardsPanel);
		tabbedPane.addTab("VisionField", visionFieldPanel);
		tabbedPane.addTab("State DB Lookup", lookupPanel);
		tabbedPane.addTab("Statistic Export", statisticPanel);

		leftConstraints.fill = GridBagConstraints.HORIZONTAL;
		rightConstraints.weightx = 1.0;
		leftConstraints.weighty = 0.5;
		centerConstraints.fill = GridBagConstraints.HORIZONTAL;
		centerConstraints.weightx = 1.0;
		centerConstraints.weighty = 0.5;
		centerConstraints.gridwidth = 2;
		rightConstraints.fill = GridBagConstraints.HORIZONTAL;
		rightConstraints.weightx = 1.0;
		rightConstraints.weighty = 0.5;

		createMainPanel();
		createRewardsPanel();
		createVisionFieldPanel();
		createLookupPanel();
		createStatisticPanel();

		mainFrame.setTitle("Mario AI 2014/2015");
		mainFrame.setContentPane(contentPane);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

    private void createMainPanel() {
    	JLabel startmodeLabel = new JLabel("Mario Startmode");
    	startmodeLabel.setPreferredSize(new Dimension(100, 10));

    	startmodeComboBox = new JComboBox<String>(rlGlueService.getAllMarioModes());
    	difficultyComboBox = new JComboBox<Integer>(rlGlueService.getAllDifficulties());
    	agentComboBox = new JComboBox<String>(rlGlueService.getAllAgents());

    	startmodeComboBox.setSelectedIndex(rlGlueService.getMarioStartState());
    	difficultyComboBox.setSelectedIndex(rlGlueService.getDifficulty());
    	agentComboBox.setSelectedItem(rlGlueService.getAgentName());

    	centerConstraints.anchor = GridBagConstraints.CENTER;
		leftConstraints.gridx = 0;
		centerConstraints.gridx = 0;
		rightConstraints.gridx = 1;
		mainCheckBoxPanel.add(visualisationCheckBox);
		mainCheckBoxPanel.add(randomLevelsCheckBox);
		mainCheckBoxPanel.add(freezPolicyCheckBox);
		mainCheckBoxPanel.add(noExplorationCheckBox);
		mainPanel.setLayout(new GridBagLayout());
		rightConstraints.gridy = 0;
		mainPanel.add(mainCheckBoxPanel, rightConstraints);
		leftConstraints.gridy = 1;
		rightConstraints.gridy = 1;
		mainPanel.add(startmodeLabel, leftConstraints);
		mainPanel.add(startmodeComboBox, rightConstraints);
		leftConstraints.gridy = 2;
		rightConstraints.gridy = 2;
		mainPanel.add(new JLabel("Difficulty"), leftConstraints);
		mainPanel.add(difficultyComboBox, rightConstraints);
		leftConstraints.gridy = 3;
		rightConstraints.gridy = 3;
		mainPanel.add(new JLabel("Agent"), leftConstraints);
		mainPanel.add(agentComboBox, rightConstraints);
		leftConstraints.gridy = 4;
		rightConstraints.gridy = 4;
		mainPanel.add(new JLabel("Episodes"), leftConstraints);
		mainPanel.add(episodesTextField, rightConstraints);
		leftConstraints.gridy = 5;
		rightConstraints.gridy = 5;
		mainPanel.add(new JLabel("Level Seed"), leftConstraints);
		mainPanel.add(seedTextField, rightConstraints);
		leftConstraints.gridy = 6;
		rightConstraints.gridy = 6;
		mainPanel.add(new JLabel("FPS"), leftConstraints);
		mainPanel.add(fpsTextField, rightConstraints);
		rightConstraints.gridy = 7;
		mainPanel.add(randomSeedButton, rightConstraints);
		centerConstraints.anchor = GridBagConstraints.PAGE_END;
		centerConstraints.gridy = 8;
		mainPanel.add(startButton, centerConstraints);
		centerConstraints.gridy = 9;
		mainPanel.add(playButton, centerConstraints);

        randomSeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random generator = new Random();
                seedTextField.setText("" + generator.nextInt());
            }
        });

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
    }

    private void createRewardsPanel() {
    	winLable.setPreferredSize(new Dimension(100, 10));

    	winTextField = new JTextField(String.valueOf(rlGlueService.getCurrentReward().getReward(winLable.getText()).getReward()));
        lossTextField = new JTextField(String.valueOf(rlGlueService.getCurrentReward().getReward(lossLable.getText()).getReward()));
        hurtTextField = new JTextField(String.valueOf(rlGlueService.getCurrentReward().getReward(hurtLable.getText()).getReward()));
        stompTextField = new JTextField(String.valueOf(rlGlueService.getCurrentReward().getReward(stompLable.getText()).getReward()));
        frameTextField = new JTextField(String.valueOf(rlGlueService.getCurrentReward().getReward(frameLable.getText()).getReward()));
        rightTextField = new JTextField(String.valueOf(rlGlueService.getCurrentReward().getReward(rightLable.getText()).getReward()));
        leftTextField = new JTextField(String.valueOf(rlGlueService.getCurrentReward().getReward(leftLable.getText()).getReward()));
        upTextField = new JTextField(String.valueOf(rlGlueService.getCurrentReward().getReward(upLable.getText()).getReward()));
        downTextField = new JTextField(String.valueOf(rlGlueService.getCurrentReward().getReward(downLable.getText()).getReward()));

    	centerConstraints.anchor = GridBagConstraints.CENTER;
        leftConstraints.gridx = 0;
		centerConstraints.gridx = 0;
		rightConstraints.gridx = 1;
		rewardsPanel.setLayout(new GridBagLayout());
		leftConstraints.gridy = 0;
		rightConstraints.gridy = 0;
		rewardsPanel.add(winLable, leftConstraints);
		rewardsPanel.add(winTextField, rightConstraints);
		leftConstraints.gridy = 1;
		rightConstraints.gridy = 1;
		rewardsPanel.add(lossLable, leftConstraints);
		rewardsPanel.add(lossTextField, rightConstraints);
		leftConstraints.gridy = 2;
		rightConstraints.gridy = 2;
		rewardsPanel.add(hurtLable, leftConstraints);
		rewardsPanel.add(hurtTextField, rightConstraints);
		leftConstraints.gridy = 3;
		rightConstraints.gridy = 3;
		rewardsPanel.add(stompLable, leftConstraints);
		rewardsPanel.add(stompTextField, rightConstraints);
		leftConstraints.gridy = 4;
		rightConstraints.gridy = 4;
		rewardsPanel.add(frameLable, leftConstraints);
		rewardsPanel.add(frameTextField, rightConstraints);
		leftConstraints.gridy = 5;
		rightConstraints.gridy = 5;
		rewardsPanel.add(rightLable, leftConstraints);
		rewardsPanel.add(rightTextField, rightConstraints);
		leftConstraints.gridy = 6;
		rightConstraints.gridy = 6;
		rewardsPanel.add(leftLable, leftConstraints);
		rewardsPanel.add(leftTextField, rightConstraints);
		leftConstraints.gridy = 7;
		rightConstraints.gridy = 7;
		rewardsPanel.add(upLable, leftConstraints);
		rewardsPanel.add(upTextField, rightConstraints);
		leftConstraints.gridy = 8;
		rightConstraints.gridy = 8;
		rewardsPanel.add(downLable, leftConstraints);
		rewardsPanel.add(downTextField, rightConstraints);
		centerConstraints.anchor = GridBagConstraints.PAGE_END;
		centerConstraints.gridy = 9;
		rewardsPanel.add(saveButton, centerConstraints);

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
                    rewards.add(new Reward(winLable.getText(), winsInt));
                    rewards.add(new Reward(lossLable.getText(), lossInt));
                    rewards.add(new Reward(hurtLable.getText(), hurtInt));
                    rewards.add(new Reward(stompLable.getText(), stompInt));
                    rewards.add(new Reward(frameLable.getText(), frameInt));
                    rewards.add(new Reward(rightLable.getText(), rightInt));
                    rewards.add(new Reward(leftLable.getText(), leftInt));
                    rewards.add(new Reward(upLable.getText(), upInt));
                    rewards.add(new Reward(downLable.getText(), downInt));

                    rlGlueService.saveRewards(rewards);
                    rlGlueService.setRewards(db.getLastRewardsGroup().getRewards());
                } catch (NumberFormatException nfe) {
                }
            }
        });
    }

    private void createVisionFieldPanel() {
		ArrayList<Zone> visionField;

        GridBagConstraints constraints = new GridBagConstraints();
        GridBagConstraints centerConstraints = new GridBagConstraints();
    	JLabel emptyLabel = new JLabel("");
    	JLabel blockLabel = new JLabel("Block");
    	JLabel bridgeLabel = new JLabel("Bridge");
    	JLabel enemyLabel = new JLabel("Enemy");
    	JLabel detailedEnemyLabel = new JLabel("DetailedEnemy");
    	blockLabel.setMinimumSize(new Dimension(140, 20));
    	bridgeLabel.setMinimumSize(new Dimension(140, 20));
    	enemyLabel.setMinimumSize(new Dimension(140, 20));
    	detailedEnemyLabel.setMinimumSize(new Dimension(140, 20));
    	blockLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	bridgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	enemyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	detailedEnemyLabel.setHorizontalAlignment(SwingConstants.CENTER);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.5;
		centerConstraints.fill = GridBagConstraints.HORIZONTAL;
    	centerConstraints.anchor = GridBagConstraints.CENTER;
		centerConstraints.weighty = 0.5;
		centerConstraints.gridwidth = 7;
		visionFieldPanel.setLayout(new GridBagLayout());

		constraints.gridx = 7;
		constraints.gridy = 0;
		emptyLabel.setMinimumSize(new Dimension(20, 20));
		visionFieldPanel.add(emptyLabel, constraints);

		centerConstraints.gridx = 0;
		centerConstraints.gridy = 0;
		visionFieldPanel.add(blockLabel, centerConstraints);
		createTextFields(constraints, 0, 1, "bl", blList);
		centerConstraints.gridx = 8;
		visionFieldPanel.add(bridgeLabel, centerConstraints);
		createTextFields(constraints, 8, 1, "br", brList);
		centerConstraints.gridx = 0;
		centerConstraints.gridy = 8;
		visionFieldPanel.add(enemyLabel, centerConstraints);
		createTextFields(constraints, 0, 9, "e", eList);
		centerConstraints.gridx = 8;
		visionFieldPanel.add(detailedEnemyLabel, centerConstraints);
		createTextFields(constraints, 8, 9, "de", deList);
		centerConstraints.anchor = GridBagConstraints.PAGE_END;
		centerConstraints.gridwidth = 15;
		centerConstraints.gridx = 0;
		centerConstraints.gridy = 16;
		visionFieldPanel.add(saveVisionButton, centerConstraints);
		centerConstraints.gridy = 17;
		visionFieldPanel.add(resetToDefaultButton, centerConstraints);

		try {
			visionField = VisionFieldPersistence.loadVisionField();
		} catch(ClassNotFoundException | IOException e1) {
			visionField = createDefaultVisionField();
		}

		rlGlueService.setVisionField(visionField);
		setVisionTextFields(visionField);
        setKeyListeners();
        resetColours();

        saveVisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Zone> visionField = createVisionField();

                try {
					VisionFieldPersistence.saveVisionField(visionField);
				} catch(IOException e1) {
				}

                rlGlueService.setVisionField(visionField);

                resetColours();
            }
        });

        resetToDefaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ArrayList<Zone> visionField = createDefaultVisionField();

            	rlGlueService.setVisionField(visionField);

            	setVisionTextFields(visionField);

                resetColours();
            }
        });
    }

    private void createLookupPanel() {
    	JLabel modeLabel = new JLabel("Mario Mode");
    	JLabel actionsLabel = new JLabel("Actions");
    	modeLabel.setPreferredSize(new Dimension(100, 10));
    	actionsLabel.setHorizontalAlignment(SwingConstants.CENTER);

    	centerConstraints.anchor = GridBagConstraints.CENTER;
    	leftConstraints.gridx = 0;
		centerConstraints.gridx = 0;
		rightConstraints.gridx = 1;
		lookupPanel.setLayout(new GridBagLayout());
		leftConstraints.gridy = 0;
		rightConstraints.gridy = 0;
		lookupPanel.add(modeLabel, leftConstraints);
		lookupPanel.add(modeTextField, rightConstraints);
		leftConstraints.gridy = 1;
		rightConstraints.gridy = 1;
		lookupPanel.add(new JLabel("Scene"), leftConstraints);
		lookupPanel.add(sceneTextField, rightConstraints);
		leftConstraints.gridy = 2;
		rightConstraints.gridy = 2;
		lookupPanel.add(new JLabel("Enemy"), leftConstraints);
		lookupPanel.add(enemyTextField, rightConstraints);
		rightConstraints.gridy = 3;
		lookupPanel.add(actionsLabel, rightConstraints);
		leftConstraints.gridy = 4;
		rightConstraints.gridy = 4;
		lookupPanel.add(new JLabel("A1"), leftConstraints);
		lookupPanel.add(a1TextField, rightConstraints);
		leftConstraints.gridy = 5;
		rightConstraints.gridy = 5;
		lookupPanel.add(new JLabel("A2"), leftConstraints);
		lookupPanel.add(a2TextField, rightConstraints);
		leftConstraints.gridy = 6;
		rightConstraints.gridy = 6;
		lookupPanel.add(new JLabel("A3"), leftConstraints);
		lookupPanel.add(a3TextField, rightConstraints);
		leftConstraints.gridy = 7;
		rightConstraints.gridy = 7;
		lookupPanel.add(new JLabel("A4"), leftConstraints);
		lookupPanel.add(a4TextField, rightConstraints);
		leftConstraints.gridy = 8;
		rightConstraints.gridy = 8;
		lookupPanel.add(new JLabel("A5"), leftConstraints);
		lookupPanel.add(a5TextField, rightConstraints);
		leftConstraints.gridy = 9;
		rightConstraints.gridy = 9;
		lookupPanel.add(new JLabel("A6"), leftConstraints);
		lookupPanel.add(a6TextField, rightConstraints);
		leftConstraints.gridy = 10;
		rightConstraints.gridy = 10;
		lookupPanel.add(new JLabel("A7"), leftConstraints);
		lookupPanel.add(a7TextField, rightConstraints);
		leftConstraints.gridy = 11;
		rightConstraints.gridy = 11;
		lookupPanel.add(new JLabel("A8"), leftConstraints);
		lookupPanel.add(a8TextField, rightConstraints);
		leftConstraints.gridy = 12;
		rightConstraints.gridy = 12;
		lookupPanel.add(new JLabel("A9"), leftConstraints);
		lookupPanel.add(a9TextField, rightConstraints);
		leftConstraints.gridy = 13;
		rightConstraints.gridy = 13;
		lookupPanel.add(new JLabel("A10"), leftConstraints);
		lookupPanel.add(a10TextField, rightConstraints);
		leftConstraints.gridy = 14;
		rightConstraints.gridy = 14;
		lookupPanel.add(new JLabel("A11"), leftConstraints);
		lookupPanel.add(a11TextField, rightConstraints);
		leftConstraints.gridy = 15;
		rightConstraints.gridy = 15;
		lookupPanel.add(new JLabel("A12"), leftConstraints);
		lookupPanel.add(a12TextField, rightConstraints);
		centerConstraints.anchor = GridBagConstraints.PAGE_END;
		centerConstraints.gridy = 16;
		lookupPanel.add(lookupButton, centerConstraints);
		centerConstraints.gridy = 17;
		lookupPanel.add(DBResetButton, centerConstraints);

		lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State state = new State(Long.parseLong(modeTextField.getText()), Long.parseLong(sceneTextField.getText()), Long.parseLong(enemyTextField.getText()));
                DatabaseImpl db = new DatabaseImpl();

                double[] values = db.select(state, db.getLastRewardsGroup());

                a1TextField.setText("" + values[0]);
                a2TextField.setText("" + values[1]);
                a3TextField.setText("" + values[2]);
                a4TextField.setText("" + values[3]);
                a5TextField.setText("" + values[4]);
                a6TextField.setText("" + values[5]);
                a7TextField.setText("" + values[6]);
                a8TextField.setText("" + values[7]);
                a9TextField.setText("" + values[8]);
                a10TextField.setText("" + values[9]);
                a11TextField.setText("" + values[10]);
                a12TextField.setText("" + values[11]);
            }
        });

        DBResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();

                int confirm = JOptionPane.showOptionDialog(frame, "Are you sure you want to reset the DB?", "Reset Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (confirm == JOptionPane.YES_OPTION) {
                    rlGlueService.resetDB();
                }
            }
        });
    }

    private void createStatisticPanel() {
    	JLabel pathLabel = new JLabel("Path");
    	pathLabel.setPreferredSize(new Dimension(100, 10));

    	centerConstraints.anchor = GridBagConstraints.CENTER;
    	leftConstraints.gridx = 0;
		rightConstraints.gridx = 1;
		statisticPanel.setLayout(new GridBagLayout());
		leftConstraints.gridy = 0;
		rightConstraints.gridy = 0;
		statisticPanel.add(pathLabel, leftConstraints);
		statisticPanel.add(pathTextField, rightConstraints);
    	centerConstraints.anchor = GridBagConstraints.PAGE_END;
		centerConstraints.gridx = 0;
		centerConstraints.gridy = 1;
		statisticPanel.add(exportButton, centerConstraints);

		exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rlGlueService.exportToPath(pathTextField.getText());
            }
        });
    }

    private void createTextFields(GridBagConstraints constraints, int gridx, int gridy, String namePostfix, ArrayList<JTextField> list) {
		constraints.gridy = gridy;

		for(int y = 6; y >= 0; y--) {
			constraints.gridx = gridx;

			for(int x = 0; x < 7; x++) {
				JTextField textField = new JTextField();
				textField.setName("textField" + x + "" + y + namePostfix);
				textField.setPreferredSize(new Dimension(40, 20));
				list.add(textField);
				visionFieldPanel.add(textField, constraints);
				constraints.gridx++;
			}

			constraints.gridy++;
		}
    }

    private JTextField getTextField(ArrayList<JTextField> list, String name) {
    	JTextField returnTextField = null;

    	for(JTextField textField : list) {
			if(name.equals(textField.getName())) {
				returnTextField = textField;
			}
		}

    	return returnTextField;
    }

    private ArrayList<Zone> createVisionField() {
        HashMap<Integer, ArrayList<Block>> blZones = createZone(blList);
        HashMap<Integer, ArrayList<Block>> brZones = createZone(brList);
        HashMap<Integer, ArrayList<Block>> eZones = createZone(eList);
        HashMap<Integer, ArrayList<Block>> deZones = createZone(deList);

        ArrayList<Zone> zones = new ArrayList<>();
        Set<Integer> blSet = blZones.keySet();
        Set<Integer> brSet = brZones.keySet();
        Set<Integer> eSet = eZones.keySet();
        Set<Integer> deSet = deZones.keySet();

        for(Integer i : blSet) {
            zones.add(new Zone(blZones.get(i), Type.BLOCK));
        }

        for(Integer i : brSet) {
            zones.add(new Zone(brZones.get(i), Type.BRIDGE));
        }

        for(Integer i : eSet) {
            zones.add(new Zone(eZones.get(i), Type.ENEMY));
        }

        for(Integer i : deSet) {
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

        for(JTextField textField : textFields) {
            if(!textField.getText().equals("")) {
                if(zones.get(Integer.parseInt(textField.getText())) != null) {
                    Block block = new Block((Integer.parseInt(textField.getName().substring(9, 10)) + (-3)), (Integer.parseInt(textField.getName().substring(10, 11)) + (-2)));
                    ArrayList<Block> blockList = zones.get(Integer.parseInt(textField.getText()));

                    blockList.add(block);
                    zones.put(Integer.parseInt(textField.getText()),blockList);
                } else {
                    ArrayList<Block> blockList = new ArrayList<>();
                    Block block = new Block((Integer.parseInt(textField.getName().substring(9, 10)) + (-3)), (Integer.parseInt(textField.getName().substring(10, 11)) + (-2)));

                    blockList.add(block);
                    zones.put(Integer.parseInt(textField.getText()),blockList);
                }
            }
        }

        return zones;
    }

    private long exp(int x) {
        long res = 1;

        for(int i = 0; i < x; i++) {
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
        e6b.add(e6b1);
        e6b.add(e6b2);

        Zone s1 = new Zone(s1b, Type.BRIDGE);
        Zone s2 = new Zone(s2b, Type.BLOCK);
        Zone s3 = new Zone(s3b, Type.BLOCK);
        Zone s4 = new Zone(s4b, Type.BLOCK);
        Zone s5 = new Zone(s5b, Type.BLOCK);
        Zone s6 = new Zone(s6b, Type.BLOCK);
        Zone s7 = new Zone(s7b, Type.BLOCK);
        Zone s8 = new Zone(s8b, Type.BLOCK);

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

        visionField.add(e1);
        visionField.add(e2);
        visionField.add(e3);
        visionField.add(e4);
        visionField.add(e5);
        visionField.add(e6);

        return visionField;
    }

    private void setGreen(JTextField textField) {
        textField.setBackground(new Color(0,255,0));
        textField.setForeground(new Color(0,0,0));
    }

    private void setRed(JTextField textField) {
        textField.setBackground(new Color(255,0,0));
        textField.setForeground(new Color(0,0,0));
    }

    private void setYellow(JTextField textField) {
        textField.setBackground(new Color(255,255,0));
        textField.setForeground(new Color(0,0,0));
    }

    private void setWhite(JTextField textField) {
        textField.setBackground(new Color(255,255,255));
        textField.setForeground(new Color(0,0,0));
    }

    private void setKeyListeners() {
        for(final JTextField field : blList) {
            field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                	resetColours();
                }
            });
        }

        for(final JTextField field : brList) {
            field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                	resetColours();
                }
            });
        }

        for(final JTextField field : eList) {
            field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                	resetColours();
                }
            });
        }

        for(final JTextField field : deList) {
            field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                	resetColours();
                }
            });
        }
    }

    private void resetColours() {
	    setYellow(getTextField(blList, "textField33bl"));
	    setYellow(getTextField(blList, "textField32bl"));
	    setYellow(getTextField(brList, "textField33br"));
	    setYellow(getTextField(brList, "textField32br"));
	    setYellow(getTextField(eList, "textField33e"));
	    setYellow(getTextField(eList, "textField32e"));
	    setYellow(getTextField(deList, "textField33de"));
	    setYellow(getTextField(deList, "textField32de"));

        for(JTextField field : blList) {
            if(!(field.getName().equals("textField33bl") || field.getName().equals("textField32bl"))) {
                if(!field.getText().isEmpty()) {
                    setGreen(field);
                } else {
                    setWhite(field);
                }
            }
        }

        for(JTextField field : brList) {
            if(!(field.getName().equals("textField33br") || field.getName().equals("textField32br"))) {
                if(!field.getText().isEmpty()) {
                    setGreen(field);
                } else {
                    setWhite(field);
                }
            }
        }

        for(JTextField field : eList) {
            if(!(field.getName().equals("textField33e") || field.getName().equals("textField32e"))) {
                if(!field.getText().isEmpty()) {
                    setRed(field);
                } else {
                    setWhite(field);
                }
            }
        }

        for(JTextField field : deList) {
            if(!(field.getName().equals("textField33de") || field.getName().equals("textField32de"))) {
                if(!field.getText().isEmpty()) {
                    setRed(field);
                } else {
                    setWhite(field);
                }
            }
        }
    }

    private void setVisionTextFields(ArrayList<Zone> visionField) {
        for (JTextField field : blList) {
            field.setText("");
        }
        for (JTextField field : brList) {
            field.setText("");
        }
        for (JTextField field : eList) {
            field.setText("");
        }
        for (JTextField field : deList) {
            field.setText("");
        }

        int i = 0;

        for(Zone zone : visionField) {
        	ArrayList<JTextField> list = null;
    		String namePostfix = null;

    		i++;

    		switch (zone.getType()) {
				case BLOCK:
					list = blList;
					namePostfix = "bl";
					break;
				case BRIDGE:
					list = brList;
					namePostfix = "br";
					break;
				case ENEMY:
					list = eList;
					namePostfix = "e";
					break;
				case DETAILEDENEMY:
					list = deList;
					namePostfix = "de";
					break;
			}

    		for(Block block : zone.getBlocks()) {
        		getTextField(list, "textField" + (block.getX() + 3) + "" + (block.getY() + 2) + namePostfix).setText(Integer.toString(i));
			}
		}
    }

	public static void main(String[] args) {
		new MainFrame();
	}
}