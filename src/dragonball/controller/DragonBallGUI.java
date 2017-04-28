package dragonball.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;



import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import sun.net.www.content.audio.x_aiff;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.NonPlayableCharacter;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.view.DragonBallView;
import dragonball.view.MapButtons;
import dragonball.model.character.fighter.*;

@SuppressWarnings("serial")
public class DragonBallGUI implements GameListener, ActionListener, KeyListener,MouseListener, Serializable {
	private transient Game game;
	private transient Battle battle;
	private transient Dragon dragon;
	private transient ImageIcon icon;
	private transient DragonBallView dragonBallView;
	private transient ArrayList<PlayableFighter> fighterslist= new ArrayList<>();
	private transient JButton NewGame;
	private transient JButton Load;
	private transient JButton Exit;
	private transient JButton Mute;
	private transient JButton Back;
	private transient JButton AB;
	private transient JButton SB;
	private transient JButton UA;
	private transient JButton SA;
	private transient JButton Save;
	private transient JButton Fighters;
	private transient JButton Collect;
	private transient JRadioButton Earthling;
	private transient JRadioButton Frieza;
	private transient JRadioButton Majin;
	private transient JRadioButton Namekian;
	private transient JRadioButton Saiyan;
	private transient JButton Start;
	private transient JButton back2;
	private transient JButton Attack;
	private transient JButton UAttack;
	private transient JButton Block;
	private transient JButton Use;
	private transient JButton Next;
	private transient JButton SetActive;
	private transient JButton Previous;
	private transient JButton UpH;
	private transient JButton UpKi;
	private transient JButton UpStamina;
	private transient JButton UpPhy;
	private transient JButton UpBlast;
	private transient JButton CreateFighter;
	private transient JButton Esc;
	private transient JButton AssignAttack;
	private transient JButton mapButtons[][] = new JButton[10][10];
	private transient JButton PhysicalAttack;
	private transient JButton SuperAttack;
	private transient JButton UltimateAttack;
	private transient JTextField text;
	private transient JTextField fighterName;
	private transient JTextField fighterName2;
	private transient PlayableFighter fighterSelected;
	private transient PlayableFighter activeFighter1;
	private transient JButton createFighter;
	//private transient JTextField info;
	private transient JTextArea txtArea;
	private transient JTextArea playerinfo;
	private transient String chosenRace;
	private transient JProgressBar health;
	private transient JProgressBar ki;
	private transient JProgressBar stamina;
	private transient JProgressBar health1;
	private transient JProgressBar ki1;
	private transient JProgressBar stamina1;
	private transient JLabel FoeHealth;
	private transient JLabel FoeKi;
	private transient JLabel FoeStamina;
	private transient JLabel myHealth;
	private transient JLabel myKi;
	private transient JLabel myStamina;
	private transient JLabel fighter;
	private transient JLabel fighterChar;
	private transient JLabel foeChar;
	private transient JLabel playername;
	private transient JLabel playerlevel;
	private transient JLabel foeLabel;
	private transient JLabel foeLevel;
	private transient JLabel turn;
	private transient JLabel Action;
	private transient JLabel ActionFoe;
	private transient JLabel SS;
	private transient JComboBox<String> superPlayer;
	private transient JComboBox<String> ultimatePlayer;
	private transient JComboBox<String> superFoe;
	private transient JComboBox<String> ultimateFoe;
	private transient JComboBox<String> superAttacks;
	private transient JComboBox<String> ultimateAttacks;
	private transient JButton addSuper;
	private transient JButton addUltimate;
	private transient JButton replaceSuper;
	private transient JButton replaceUltimate;
	private transient JButton Close;
	private transient JButton CloseU;
	private transient JButton senzuBeansWish;
	private transient JButton abilityPointsWish;
	private transient JButton superAttackWish;
	private transient JButton ultimateAttackWish;
	private transient AudioPlayer MGP = AudioPlayer.player;
	private transient AudioStream BGM;
	private transient AudioData MD;
	private transient ContinuousAudioDataStream loop = null;
	
	
	public DragonBallGUI() throws IOException {
		game = new Game();
		game.setListener(this);
		dragonBallView = new DragonBallView();
		fighter = new JLabel();
		fighterChar = new JLabel();
		foeChar = new JLabel();
		playerinfo = new JTextArea();
		playername= new JLabel();
		playerlevel= new JLabel();
		health = new JProgressBar();
		ki= new JProgressBar();
		stamina= new JProgressBar();
		health1= new JProgressBar();
		ki1= new JProgressBar();
		stamina1= new JProgressBar();
		FoeHealth = new JLabel();
		FoeKi = new JLabel();
		FoeStamina = new JLabel();
		myHealth = new JLabel();
		turn = new JLabel();
		myKi = new JLabel();
		myStamina = new JLabel();
		foeLabel = new JLabel();
		foeLevel = new JLabel();
		superAttacks = new JComboBox();
		ultimateAttacks = new JComboBox();
		UAttack = new JButton();
		Action = new JLabel();
		ActionFoe = new JLabel();
		SS = new JLabel();
		back2 = new JButton();
		
		superAttacks.setForeground(Color.WHITE);
		 ultimateAttacks.setForeground(Color.WHITE);
		addText2();
		AssignAttack();
		
		addradio(Earthling,"Earthling", 0,3);
		addradio(Frieza,"Frieza",0,4);
		addradio(Majin,"Majin",0,5);
		addradio(Namekian,"Namekian",0,6);
		addradio(Saiyan,"Saiyan",0,7);
		addradioToCF1(Earthling,"Earthling", 0,3);
		addradioToCF1(Frieza,"Frieza",0,4);
		addradioToCF1(Majin,"Majin",0,5);
		addradioToCF1(Namekian,"Namekian",0,6);
		addradioToCF1(Saiyan,"Saiyan",0,7);
		
		buttonCreator(NewGame ,"New Game","NewGame.png","NewGameOver.png",(JPanel)(dragonBallView.getMainMenu()));
		
		buttonCreator(Load,"Load","Load.png","LoadOver.png", (JPanel)(dragonBallView.getMainMenu()));
		
		buttonCreator(Exit,"Exit","Exit.png","ExitOver.png",(JPanel)(dragonBallView.getMainMenu()));
		
		addButtonstoinfo(Back, 0,4, "Back1.png", "Back","BackOver1.png");
		addButtonstoinfo(Save, 0, 2, "Save.png", "Save", "SaveOver.png");
		addButtonstoinfo(Fighters, 0, 3, "Fighters.png", "Fighters","FightersOver.png");
		addButtonstoinfo(AssignAttack,0,5,"AssignAttack.png","AssignAttack","AssignAttackOver.png");
		addButtonstoCF(Back, 0,9, "Back.png", "Back", "BackOver.png");
		addButtonstoCF(Start, 0, 8, "StartGame.png", "Start", "StartGameOver.png");
		addbuttonstostats(Block,0,12,"Block.png","BlockOver.png","Block");
		addbuttonstostats(Use,0,13,"Use.png","UseOver.png","Use");
		addbuttonstostats(PhysicalAttack,0,9,"PhysicalAttack.png","PhysicalAttackOver.png","PhysicalAttack");
		addbuttonstostats(SuperAttack,0,10,"SuperAttack.png","SuperAttackOver.png","SuperAttack");
		addbuttonstostats(UltimateAttack,0,11,"UltimateAttack.png","UltimateAttackOver.png","UltimateAttack");
		addButtonstoF(Next,1,2,"Next.png","NextOver.png", "Next");
		addButtonstoF(Previous,0,2,"Previous.png","PreviousOver.png","Previous");
		addButtonstoF(SetActive,2,2,"SetActiveFighter.png","SetActiveFighterOver.png","SetActive");
		addButtonstoF(UpH,0,3,"UpHealth.png","UpHealthOver.png","UpH");
		addButtonstoF(UpKi,1,3,"UpKi.png","UpKiOver.png","UpKi");
		addButtonstoF(UpStamina,2,3,"UpStamina.png","UpStaminaOver.png","UpStamina");
		addButtonstoF(UpPhy,3,3,"UpPhysicalAttack.png","UpPhysicalAttackOver.png","UpPhy");
		addButtonstoF(UpBlast,4,3,"UpBlastDamage.png","UpBlastDamageOver.png","UpBlast");
		addButtonstoF(CreateFighter,2,4,"CreateFighter.png","CreateFighterOver.png","CreateFighter");
		addButtonstoF(Esc,2,5,"Escape.png","EscapeOver.png","Esc");
		addButtonstoCF1(createFighter,0,8,"StartGame.png","createFighter");
		addButtonstoCF1(Back,0,9,"Back.png","Back");
		addWishToDragonView(senzuBeansWish, 0,1, "SB.png","SenzuBeansWish");
		addWishToDragonView(abilityPointsWish, 1, 1, "AB.png","AbilityPointsWish");
		addWishToDragonView(superAttackWish, 2, 1,"SA.png","SuperAttackWish");
		addWishToDragonView(ultimateAttackWish, 3, 1,"UA.png","UltimateAttackWish");
		addCollections(Collect,"Collect.png","CollectOver.png","CollectD");
		addText();
		addText3();
		Mappics();
		addTextArea();
	}
	
	private void addCollections(JButton button, String path,String pathOver, String name) throws IOException {
		BufferedImage Image = ImageIO.read(new File(path));
		BufferedImage mOver = ImageIO.read(new File(pathOver));
		button = new JButton(new ImageIcon(Image));
		button.setRolloverIcon(new ImageIcon(mOver));
		button.addActionListener(this);
		button.addMouseListener(this);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.setName(name);
		dragonBallView.addCollect(button);
		
	}

	public void addWishToDragonView(JButton button, int x, int y, String MouseO,String Name) throws IOException{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		BufferedImage Image = ImageIO.read(new File("WishBall.png"));
		BufferedImage mOver = ImageIO.read(new File(MouseO));
		button = new JButton(new ImageIcon(Image));
		button.setRolloverIcon(new ImageIcon(mOver));
		button.addMouseListener(this);
		button.addActionListener(this);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.setName(Name);
		
		dragonBallView.addWishToDragonView(button,x,y,c);
	}
	
	public void addCharsToBattle(JLabel Char,String path, String p){
		ImageIcon icon = new ImageIcon(path);
		Char.setIcon(icon);
		Char.setMaximumSize(new Dimension(300,300));
		dragonBallView.addCharsToBattle(Char, p);
		
	}
	public void AssignAttack() throws IOException{
		superPlayer = new JComboBox<>();
		ultimatePlayer= new JComboBox<>();
		superFoe= new JComboBox<>();
		ultimateFoe= new JComboBox<>();
		superPlayer.setForeground(Color.WHITE);
		ultimatePlayer.setForeground(Color.WHITE);
		superFoe.setForeground(Color.WHITE);
		ultimateFoe.setForeground(Color.WHITE);
		superPlayer.addActionListener(this);
		ultimateFoe.addActionListener(this);
		superFoe.addActionListener(this);
		ultimateFoe.addActionListener(this);
		
		
		addButtontoass(addSuper,0 ,3,"AddSuperAttack.png","AddSuperAttackOver.png", "addSuper");
		addButtontoass(addUltimate,2 ,3,"AddUltimateAttack.png","AddUltimateAttackOver.png", "addUltimate");
		addButtontoass(replaceSuper,4 ,3,"ReplaceSuperAttack.png","ReplaceSuperAttackOver.png", "replaceSuper");
		addButtontoass(replaceUltimate,6 ,3,"ReplaceUltimateAttack.png","ReplaceUltimateAttackOver.png", "replaceUltimate");
		addButtontoass(back2, 3,4, "Escape.png","EscapeOver.png","Esc");
		
		
		dragonBallView.addtoAssign(superPlayer,0,0);

		dragonBallView.addtoAssign(ultimatePlayer,2,0);
		
		dragonBallView.addtoAssign(superFoe,4,0);
		
		dragonBallView.addtoAssign(ultimateFoe,6,0);
	}
	
	private void addButtontoass(JButton button, int i, int j, String path, String Pathover, String name) throws IOException {
	
		BufferedImage Image = ImageIO.read(new File(path));
		BufferedImage mOver = ImageIO.read(new File(Pathover));
		button = new JButton(new ImageIcon(Image));
		button.setRolloverIcon(new ImageIcon(mOver));
		button.addActionListener(this);
		button.addMouseListener(this);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.setName(name);
		
		dragonBallView.addtoBAssign(button,i ,j);
	}

	public void playerinfo(){
		String c = 
				"Fighter Name: " + fighterSelected.getName() + "\n" +
				"Fighter Level: " +fighterSelected.getLevel() + "\n" +
				"MaxHealthPoints: " +fighterSelected.getMaxHealthPoints() + "\n" +
				"PhysicalDamage: " + fighterSelected.getPhysicalDamage() + "\n" +
				"BlastDamage: " + fighterSelected.getBlastDamage() + "\n" +
				"MaxKi: "+ fighterSelected.getMaxKi() + "\n" +
				"MaxStamina: " + fighterSelected.getMaxStamina() + "\n";
				
				playerinfo.removeAll();
				playerinfo.setText(c);
				dragonBallView.validate();
				dragonBallView.repaint();
	}
	private void addbuttonstostats(JButton button, int i, int j,String path,String pathOver, String name) throws IOException {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = i;
		c.gridy = j;
		BufferedImage Image = ImageIO.read(new File(path));
		BufferedImage mOver = ImageIO.read(new File(pathOver));
		button = new JButton(new ImageIcon(Image));
		button.setRolloverIcon(new ImageIcon(mOver));
		button.addMouseListener(this);
		button.addActionListener(this);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.setName(name);
		dragonBallView.addButtontostats(button, c);
		
	}
	private void addplayerinfot(){
		addplayerinf();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		dragonBallView.addplayerinfot(playerinfo,c);
		c.gridx= 0;
		c.gridy = 1;
		dragonBallView.addPictoF(fighter,c);
	}
	private void changeplayer() {
		if(fighterSelected instanceof Saiyan)
			icon = new ImageIcon("Saiyan.png");
			else
			if(fighterSelected instanceof Majin)
				icon = new ImageIcon("Majin.png");
			else
				if(fighterSelected instanceof Namekian)
					icon = new ImageIcon("NamekianP.png");
				else
					if(fighterSelected instanceof Frieza)
						icon = new ImageIcon("Frieza.png");
					else
						if(fighterSelected instanceof Earthling)
							icon = new ImageIcon("Earthling.png");
						else
							icon = new ImageIcon("");
		fighter.removeAll();
		fighter.setIcon(icon);
		playerinfo();
		validate();
		
	}
	private void addplayerinf(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		
		// force it to have a width of 200 and the same height of the JFrame
		// prevent its contents from being edited
		playerinfo.setEditable(false);
		// use a monospaced font to make it look cooler 
		playerinfo.setFont(new Font("Verdana", Font.BOLD, 12));
		// add it to the right of the JFrame
		playerinfo.setVisible(true);
		playerinfo.setOpaque(false);
		playerinfo.setForeground(Color.WHITE);
		playerinfo();
		
		
		if(fighterSelected instanceof Saiyan)
			icon = new ImageIcon("Saiyan.png");
			else
			if(fighterSelected instanceof Majin)
				icon = new ImageIcon("Majin.png");
			else
				if(fighterSelected instanceof Namekian)
					icon = new ImageIcon("NamekianP.png");
				else
					if(fighterSelected instanceof Frieza)
						icon = new ImageIcon("Frieza.png");
					else
						if(fighterSelected instanceof Earthling)
							icon = new ImageIcon("Earthling.png");
						else
							icon = new ImageIcon("");
		
		fighter.removeAll();
		fighter.setIcon(icon);
		validate();
		c.gridx= 0;
		c.gridy = 1;
		
		
	}
	private void addButtonstoF(JButton button,int x, int y, String path,String pathOver, String name) throws IOException {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		BufferedImage Image = ImageIO.read(new File(path));
		BufferedImage mOver = ImageIO.read(new File(pathOver));
		button = new JButton(new ImageIcon(Image));
		button.setRolloverIcon(new ImageIcon(mOver));
		button.addMouseListener(this);
		button.addActionListener(this);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.setName(name);
		dragonBallView.addButtontoF(button, c);
		
	}
	private void addplayerinfo() {
		
		GridBagConstraints c = new GridBagConstraints();
		String s ="Fighter Name: " + game.getPlayer().getActiveFighter().getName();
		String l="Fighter Level: " + game.getPlayer().getActiveFighter().getLevel() ;
		playername.setText(s);
		playername.setForeground(Color.WHITE);
		playerlevel.setForeground(Color.WHITE);
		playerlevel.setText(l);
		c.gridx = 0;
		c.gridy = 1;
		dragonBallView.addText(playername,c);
		c.gridx = 0;
		c.gridy = 2;
		dragonBallView.addText(playerlevel,c);
		
	}
	public void addTextBattleMe(JLabel label, String text, int x, int y){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		
		label.removeAll();
		label.setText(text);
		label.setForeground(Color.WHITE);		
		dragonBallView.addText(label,c);
	}
	public void addprogressBars(JProgressBar pb, int current,int max, int x, int y) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		
		pb.removeAll();
		pb.setString(current + "/"+max);
		pb.setStringPainted(true);
		pb.setValue(current);
		pb.setMaximum(max);
		pb.setMinimum(0);
		pb.setVisible(true);
		
		pb.setBackground(Color.BLACK);
		pb.setForeground(Color.RED);
		
		dragonBallView.addProgressBars(pb,c);
	}
	public void addprogressBarsFoe(JProgressBar pb, int current,int max, int x, int y) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		
		pb.removeAll();
		
		pb.setString(current + "/"+max);
		pb.setStringPainted(true);
		pb.setValue(current);
		pb.setMaximum(max);
		pb.setMinimum(10);
		pb.setVisible(true);
		
		pb.setBackground(Color.BLACK);
		pb.setForeground(Color.RED);
		
		dragonBallView.addProgressBarsFoe(pb,c);
	}
	public void addTextBattleFoe(JLabel label, String text, int x, int y){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		
		label.removeAll();
		label.setText(text);
		label.setForeground(Color.WHITE);	
		dragonBallView.addTextBattleFoe(label,c);
	}
	public void addButtonstoinfo(JButton button, int x, int y, String path, String name, String over) throws IOException{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		BufferedImage Image = ImageIO.read(new File(path));
		button = new JButton(new ImageIcon(Image));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.addActionListener(this);
		button.setName(name);
		button.setRolloverIcon(new ImageIcon(over));
		dragonBallView.addButtonstoinfo(button, c);
	}
	
	public void addButtonstoCF(JButton button, int x, int y, String path, String name, String over) throws IOException{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		BufferedImage Image = ImageIO.read(new File(path));
		button = new JButton(new ImageIcon(Image));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.addActionListener(this);
		button.setName(name);
		button.setRolloverIcon(new ImageIcon(over));
		dragonBallView.addButtontoCF(button, c);
	}
	
	public void addButtonstoCF1(JButton button, int x, int y, String path, String name) throws IOException{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		BufferedImage Image = ImageIO.read(new File(path));
		button = new JButton(new ImageIcon(Image));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.addActionListener(this);
		button.setName(name);
		dragonBallView.addButtontoCF1(button, c);
	}
	public void SetText(){
		int index = game.getPlayer().getFighters().indexOf(game.getPlayer().getActiveFighter());
		fighterSelected =game.getPlayer().getFighters().get(index);
		addplayerinfot();
		String c = "Player Name: " + game.getPlayer().getName() + "\n" +
		"Fighter Name: " + game.getPlayer().getActiveFighter().getName() + "\n" +
		"Fighter Level: " + game.getPlayer().getActiveFighter().getLevel() + "\n" +
		"Dragon Balls: " + game.getPlayer().getDragonBalls() + "\n" +
		"Senzu Beans: " + game.getPlayer().getSenzuBeans() + "\n" +
		"MaxHealthPoints: " + game.getPlayer().getActiveFighter().getMaxHealthPoints() + "\n" +
		"PhysicalDamage: " + game.getPlayer().getActiveFighter().getPhysicalDamage() + "\n" +
		"BlastDamage: " + game.getPlayer().getActiveFighter().getBlastDamage() + "\n" +
		"MaxKi: "+ game.getPlayer().getActiveFighter().getMaxKi() + "\n" +
		"MaxStamina: " + game.getPlayer().getActiveFighter().getMaxStamina() + "\n"+
		"AbilityPoints: " + game.getPlayer().getActiveFighter().getAbilityPoints();
		txtArea.setText(c);
		
	}
	public void addTextArea(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		txtArea = new JTextArea();
		// force it to have a width of 200 and the same height of the JFrame
		// prevent its contents from being edited
		txtArea.setEditable(false);
		// use a monospaced font to make it look cooler 
		txtArea.setFont(new Font("Verdana", Font.BOLD, 12));
		// add it to the right of the JFrame
		txtArea.setForeground(Color.WHITE);
		txtArea.setOpaque(false);
		txtArea.setVisible(false);
		dragonBallView.addTextArea(txtArea,c);
	}
	
	public void addText() {
		text = new JTextField("Enter Your Name",20);
		text.setEditable(true);
		text.setSize(100,100);
		text.setOpaque(true);
		text.setForeground(Color.WHITE);
		text.setBackground(Color.BLACK );
		text.setBorder(new LineBorder(Color.WHITE));
		text.setVisible(true);
		text.addActionListener(this);
		dragonBallView.addTextBox(text);
	}
	public void addText2() {
		fighterName = new JTextField("Enter Your Fighter Name",20);
		fighterName.setEditable(true);
		fighterName.setSize(100,100);
		fighterName.setOpaque(true);
		fighterName.setForeground(Color.WHITE);
		fighterName.setBackground(Color.BLACK );
		fighterName.setBorder(new LineBorder(Color.WHITE));
		fighterName.setVisible(true);
		fighterName.addActionListener(this);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		dragonBallView.addFighterName(fighterName,c);
	}
	
	public void addText3() {
		fighterName2 = new JTextField("Enter Your Fighter Name",20);
		fighterName2.setEditable(true);
		fighterName2.setSize(100,100);
		fighterName2.setOpaque(true);
		fighterName2.setForeground(Color.WHITE);
		fighterName2.setBackground(Color.BLACK );
		fighterName2.setBorder(new LineBorder(Color.WHITE));
		fighterName2.setVisible(true);
		fighterName2.addActionListener(this);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		dragonBallView.addFighterName1(fighterName2,c);
	}
public void Mappics() throws IOException{
		
		for (int i = 0; i < 10; i++){
			for(int j=0;j<10;j++){
				JButton button = new JButton();
				button.setContentAreaFilled(false);
				button.setOpaque(true);
				button.setBackground(new Color(0,0,0,0));
				button.setVisible(true);
				button.addActionListener(this);
				button.addKeyListener(this);
				button.setName(i+""+j);
				mapButtons[i][j] = button;
				button.setOpaque(false);
				dragonBallView.addMap(button);
				
			}
		}
	}
	public void addradio(JRadioButton Rb,String name,int gridx, int gridy){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gridx;
		c.gridy = gridy;
		Rb = new JRadioButton(name);
		Rb.setName(name);
		Rb.setForeground(new Color(0xffffff));
		Rb.setBackground(new Color(0x9e2604));
		dragonBallView.addtoBag(Rb,c);
		Rb.addActionListener(this);
	}
	
	public void addradioToCF1(JRadioButton Rb,String name,int gridx, int gridy){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gridx;
		c.gridy = gridy;
		Rb = new JRadioButton(name);
		Rb.setName(name);
		Rb.setForeground(new Color(0xffffff));
		Rb.setBackground(new Color(0x9e2604));
		dragonBallView.addtoBag1(Rb,c);
		Rb.addActionListener(this);
	}
	
	public void buttonCreator(JButton button,String name, String path, String over, JPanel panel) throws IOException{
		BufferedImage Image = ImageIO.read(new File(path));
		button = new JButton(new ImageIcon(Image));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setRolloverIcon(new ImageIcon(over));
		button.setVisible(true);
		button.addActionListener(this);
		button.setName(name);
		dragonBallView.addMenuButtons(button,panel);
	}
	public void music(String filename) 
    {       
        

        try
        {
            InputStream test = new FileInputStream(filename);
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
     
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
        
    }
	public void stopMusic(){
		AudioPlayer.player.stop(BGM);
	}
	public void startGame(){
		
		for(int i=0;i<(game.getPlayer().getSuperAttacks().size());i++){
			if(!Exist(game.getPlayer().getSuperAttacks().get(i).getName(),superPlayer)){
			superPlayer.addItem(game.getPlayer().getSuperAttacks().get(i).getName());
			}
		}
		for(int i=0;i<(game.getPlayer().getUltimateAttacks().size());i++){
			if(!Exist(game.getPlayer().getUltimateAttacks().get(i).getName(),ultimatePlayer)){
			ultimatePlayer.addItem(game.getPlayer().getUltimateAttacks().get(i).getName());
			}
		}
		try{
		if(!(text.getText().isEmpty()) && !(fighterName.getText().isEmpty())){
		game.getPlayer().setName(text.getText());
		
		game.getPlayer().createFighter(chosenRace.charAt(0), fighterName.getText());
		
		
		dragonBallView.ViewWorld(txtArea);
	
		
		SetText();
		}else{
			JOptionPane.showMessageDialog(null,"Please Enter Both Your Name And Your Fighter's Name");
		}
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null,"Please Choose A Race");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() instanceof JButton){
		JButton s = (JButton)e.getSource();
		switch(s.getName()){
		case "New Game" : dragonBallView.NewGame();break;
		case "Load": try {
				game.load("GameSave");
				game.setListener(this);
				dragonBallView.ViewWorld(txtArea);
				SetText();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,"You have no saves to load");
			}
			break;
		case "Ultimate Attack": dragonBallView.previousMenu(); break;
		case "Start": startGame(); break;
		case "Back": dragonBallView.previousMenu(); break;
		case "Save": try {
				game.save("GameSave");
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,"Failed to save Game.");
			} break;
		case "Fighters": dragonBallView.fightersView(); break;
		case "Next" : nextFighter(); break;
		case "Previous": previousFighter();break;
		case "SetActive":
			game.getPlayer().setActiveFighter(fighterSelected);break;
		case "UpH": try {
				game.getPlayer().upgradeFighter(fighterSelected, 'H');
				SetText();
			} catch (NotEnoughAbilityPointsException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			} break;
		case "UpKi":try {
			game.getPlayer().upgradeFighter(fighterSelected, 'K');
			SetText();
		} catch (NotEnoughAbilityPointsException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
		} break;
		case "UpStamina":try {
			game.getPlayer().upgradeFighter(fighterSelected, 'S');
			SetText();
		} catch (NotEnoughAbilityPointsException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
		} break;
		case "UpPhy":try {
			game.getPlayer().upgradeFighter(fighterSelected, 'P');
			SetText();
		} catch (NotEnoughAbilityPointsException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
		} break;
		case "UpBlast":try {
			game.getPlayer().upgradeFighter(fighterSelected, 'B');
			SetText();
		} catch (NotEnoughAbilityPointsException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
		} break;
		case "CreateFighter": dragonBallView.createFighter1View(); 
		fighterName2.setText("Enter Your Fighter Name");break;
		case "createFighter": createFighter(); break;
		case "AssignAttack" : dragonBallView.AssignAttack(); break;
		case "PhysicalAttack": 
			dragonBallView.disposeView();
			try {
				battle.attack(new PhysicalAttack());
				updateV();
			} catch (NotEnoughKiException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
				e1.printStackTrace();
			} break;
		case "SuperAttack": 
			SuperAttack();
			dragonBallView.AttackMenu(); break;
		case "UltimateAttack":UltimateAttack();
		dragonBallView.ultimateAttack();break;
		case "UAttack": try {
			ultimateAttack();
			updateV();
		} catch (NotEnoughKiException e2) {
			JOptionPane.showMessageDialog(null,e2.getMessage());
			e2.printStackTrace();
		}break;
		case "CloseU": dragonBallView.disposeView();break;
		case "Attack": try {
				superAttack();
				updateV();
				
			} catch (NotEnoughKiException e2) {
				JOptionPane.showMessageDialog(null,e2.getMessage());
				e2.printStackTrace();
			}
		break;
		case "addSuper": try {
				addSuper();
			} catch (MaximumAttacksLearnedException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			} catch (DuplicateAttackException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			} catch (NotASaiyanException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			}break;
		case "addUltimate": try {
				addUltimate();
		} catch (MaximumAttacksLearnedException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			} catch (DuplicateAttackException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			} catch (NotASaiyanException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			}break;
		case "replaceSuper": try {
				replaceSuper();
		} catch (MaximumAttacksLearnedException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			} catch (DuplicateAttackException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			} catch (NotASaiyanException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			}break;
		case "replaceUltimate": try {
				replaceUltimate();
		} catch (MaximumAttacksLearnedException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			} catch (DuplicateAttackException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			} catch (NotASaiyanException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
			}break;
		case "Block": dragonBallView.disposeView();battle.block(); updateV();break;
		case "Use" : try {
			dragonBallView.disposeView();
				battle.use(game.getPlayer(), Collectible.SENZU_BEAN);
				updateV();
			} catch (NotEnoughSenzuBeansException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
				e1.printStackTrace();
			}break;
		case "Close": dragonBallView.disposeView();break;
		case "SenzuBeansWish": game.getPlayer().chooseWish(dragon.getWishes()[0]);	dragonBallView.ViewWorld(txtArea); SetText(); break;
		case "AbilityPointsWish":game.getPlayer().chooseWish(dragon.getWishes()[1]); 	dragonBallView.ViewWorld(txtArea); SetText();break; 
		case "SuperAttackWish":game.getPlayer().chooseWish(dragon.getWishes()[2]); 	dragonBallView.ViewWorld(txtArea); SetText();break; 
		case "UltimateAttackWish":game.getPlayer().chooseWish(dragon.getWishes()[3]);	dragonBallView.ViewWorld(txtArea); SetText();break; 
		case "Esc":viewWorld(); break;
		case "CollectD": dragonBallView.closeDragon();break;
		case "Exit": int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your Previous Note First?");
		if (dialogResult == JOptionPane.YES_OPTION){
			try {
				game.save("GameSave");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}else
			if(dialogResult ==JOptionPane.NO_OPTION){
				System.exit(0);
			}
			
		break;
		default: break;	
		}
		}else
			if(e.getSource()instanceof JRadioButton){
				JRadioButton s = (JRadioButton)e.getSource();
				chosenRace = s.getName();
				switch(s.getName()){
				
				case "Earthling": dragonBallView.getCreateFighter().setBackgroundImage(new ImageIcon("Earthling.jpg").getImage());
				dragonBallView.getCreateFighter1().setBackgroundImage(new ImageIcon("Earthling.jpg").getImage());
					//game.getPlayer().setActiveFighter(new dragonball.model.character.fighter.Earthling(.getText()));
				break;
				case "Namekian": dragonBallView.getCreateFighter().setBackgroundImage(new ImageIcon("Namekian.jpg").getImage());
				dragonBallView.getCreateFighter1().setBackgroundImage(new ImageIcon("Namekian.jpg").getImage());
				//game.getPlayer().setActiveFighter(new dragonball.model.character.fighter.Namekian(fighterName.getText()));
				break;
				case "Frieza": dragonBallView.getCreateFighter().setBackgroundImage(new ImageIcon("Frieza.jpg").getImage());
				dragonBallView.getCreateFighter1().setBackgroundImage(new ImageIcon("Frieza.jpg").getImage());
				//game.getPlayer().setActiveFighter(new dragonball.model.character.fighter.Frieza(fighterName.getText()));
				break;
				case "Majin": dragonBallView.getCreateFighter().setBackgroundImage(new ImageIcon("Majin.jpg").getImage());
				dragonBallView.getCreateFighter1().setBackgroundImage(new ImageIcon("Majin.jpg").getImage());
				//game.getPlayer().setActiveFighter(new dragonball.model.character.fighter.Majin(fighterName.getText()));
				break;
				case "Saiyan": dragonBallView.getCreateFighter().setBackgroundImage(new ImageIcon("Saiyan.jpg").getImage());
				dragonBallView.getCreateFighter1().setBackgroundImage(new ImageIcon("Saiyan.jpg").getImage());
				//game.getPlayer().setActiveFighter(new dragonball.model.character.fighter.Saiyan(fighterName.getText()));
				break;
				
				default: break;
				}
			}
					
					
		}

	private void ultimateAttack() throws NotEnoughKiException {
		for(int i=0;i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();i++){
			if(ultimateAttacks.getSelectedItem().toString() ==game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName() ){
				battle.attack(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i));
			}
		}
		
	}

	private void superAttack() throws NotEnoughKiException {
		for(int i=0;i<game.getPlayer().getActiveFighter().getSuperAttacks().size();i++){
			if(superAttacks.getSelectedItem().toString() ==game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName() ){
				battle.attack(game.getPlayer().getActiveFighter().getSuperAttacks().get(i));
				break;
			}
		}
		
	}

	private void SuperAttack() {
		superAttacks.addActionListener(this);
		for(int i =0;i<game.getPlayer().getActiveFighter().getSuperAttacks().size();i++){
			if(!Exist(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName(),superAttacks)){
			superAttacks.addItem(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName());
			}
		}
		dragonBallView.addCombotoView(superAttacks,225,200,200,70);
		try {
			addButtontoAttack(Attack,"Attack",225,300,"Attack.png","AttackOver.png");
			addButtontoAttack(Close,"Close",225,400,"Close.png","CloseOver.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void UltimateAttack() {
		ultimateAttacks.addActionListener(this);
		for(int i =0;i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();i++){
			if(!Exist(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName(),superAttacks)){
			ultimateAttacks.addItem(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName());
			}
		}
		dragonBallView.addCombotouView(ultimateAttacks,225,200,200,70);
		try {
			addButtontouAttack(UAttack,"UAttack",225,300,"Attack.png","AttackOver.png");
			addButtontouAttack(CloseU,"CloseU",225,400,"Close.png","CloseOver.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addButtontouAttack(JButton button, String Name, int x,int y, String path, String pathOver) throws IOException {
		BufferedImage Image = ImageIO.read(new File(path));
		BufferedImage mOver = ImageIO.read(new File(pathOver));
		button = new JButton(new ImageIcon(Image));
		button.setRolloverIcon(new ImageIcon(mOver));
		button.addActionListener(this);
		button.addMouseListener(this);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.setName(Name);
		dragonBallView.ButtontouAttckView(button,x,y);
		
	}

	private void addButtontoAttack(JButton button,String Name,int x,int y,String path, String pathOver) throws IOException {
		BufferedImage Image = ImageIO.read(new File(path));
		BufferedImage mOver = ImageIO.read(new File(pathOver));
		button = new JButton(new ImageIcon(Image));
		button.setRolloverIcon(new ImageIcon(mOver));
		button.addActionListener(this);
		button.addMouseListener(this);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setVisible(true);
		button.setName(Name);
		dragonBallView.ButtontoAttckView(button,x,y);
	}

	private void updateV() {
		if(battle.getMe() instanceof Saiyan){
			if(((Saiyan) battle.getMe()).isTransformed()){
				ImageIcon icon = new ImageIcon("SS.png");
				fighterChar.setIcon(icon);
				dragonBallView.Transformed();
				addTextBattleMee(this.SS,"TransFormed",0,14);
		}else{
			ImageIcon icon = new ImageIcon("GokuP.png");
			fighterChar.setIcon(icon);
			addTextBattleMee(this.SS,"",0,14);
		}
		}
		int currentHealthFoe = ((NonPlayableFighter)battle.getFoe()).getHealthPoints();
		int maxHealthFoe = ((NonPlayableFighter)battle.getFoe()).getMaxHealthPoints();
		int cHF = (currentHealthFoe*100)/maxHealthFoe;
		
		int currentKiFoe = ((NonPlayableFighter)battle.getFoe()).getKi();
		int maxKiFoe = ((NonPlayableFighter)battle.getFoe()).getMaxKi();
		int cKF = (currentKiFoe*100)/maxKiFoe;
		
		int currentStaminaFoe = ((NonPlayableFighter)battle.getFoe()).getStamina();
		int maxStaminaFoe = ((NonPlayableFighter)battle.getFoe()).getMaxStamina();
		int cSF = (currentStaminaFoe*100)/maxStaminaFoe;
		
		int currentHealth = game.getPlayer().getActiveFighter().getHealthPoints();
		int maxHealth = game.getPlayer().getActiveFighter().getMaxHealthPoints();
		int cH = (currentHealth*100)/maxHealth;
		
		int currentKi = game.getPlayer().getActiveFighter().getKi();
		int maxKi = game.getPlayer().getActiveFighter().getMaxKi();
		int cK = (currentKi*100)/maxKi;
		
		int currentStamina = game.getPlayer().getActiveFighter().getStamina();
		int maxStamina = game.getPlayer().getActiveFighter().getMaxStamina();
		int cS = (currentStamina*100)/maxStamina;
		
		addTextBattleMe(myHealth,"Health: " + Integer.toString(currentHealth) + " / " + Integer.toString(maxHealth),0,3);
		addprogressBars(health,cH ,100,0,4);
		addTextBattleMe(myKi,"Ki: " + Integer.toString(currentKi) + " / " + Integer.toString(maxKi),0,5);
		addprogressBars(ki,cK,100,0,6);
		addTextBattleMe(myStamina,"Stamina: " + Integer.toString(currentStamina) + " / " + Integer.toString(maxStamina),0,7);
		addprogressBars(stamina,cS,100,0,8);
		
		
		addTextBattleFoe(foeLabel,"Name: " + ((NonPlayableFighter)battle.getFoe()).getName(),0,1);
		addTextBattleFoe(foeLevel,"Level: "+ ((NonPlayableFighter)battle.getFoe()).getLevel(),0,2);
		addTextBattleFoe(FoeHealth,"Health: " + Integer.toString(currentHealthFoe) + " / " + Integer.toString(maxHealthFoe),0,3);
		addprogressBarsFoe(health1,cHF ,100,0,4);
		addTextBattleFoe(FoeKi,"Ki: " + Integer.toString(currentKiFoe) + " / " + Integer.toString(maxKiFoe),0,5);
		addprogressBarsFoe(ki1,cKF,100,0,6);
		addTextBattleFoe(FoeStamina,"Stamina: " + Integer.toString(currentStaminaFoe) + " / " + Integer.toString(maxStaminaFoe),0,7);
		addprogressBarsFoe(stamina1,cSF,100,0,8);
		
		
	}

	private void replaceUltimate() throws MaximumAttacksLearnedException, DuplicateAttackException, NotASaiyanException {
		int j;
		UltimateAttack oldAttack = null;
		UltimateAttack NewAttack = null;
		for(j=0;j<game.getPlayer().getActiveFighter().getUltimateAttacks().size();j++){
			if(game.getPlayer().getActiveFighter().getUltimateAttacks().get(j).getName()== ultimateFoe.getSelectedItem().toString()){
				oldAttack = game.getPlayer().getActiveFighter().getUltimateAttacks().get(j);
				break;
			}
		}
		for(int i =0;i< (game.getPlayer().getUltimateAttacks().size());i++){
			if(game.getPlayer().getUltimateAttacks().get(i).getName()== ultimatePlayer.getSelectedItem().toString()){
				game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(),game.getPlayer().getUltimateAttacks().get(i), oldAttack);
				JOptionPane.showMessageDialog(null,"You replaced the Attack Correctly");
				break;
			}
			
			
			}
			ultimateFoe.removeItem(oldAttack.getName());
			ultimateFoe.addItem(NewAttack.getName());
		
	}

	private void replaceSuper() throws MaximumAttacksLearnedException, DuplicateAttackException, NotASaiyanException {
		int j;
		SuperAttack oldAttack = null;
		SuperAttack NewAttack = null;
		for(j=0;j<game.getPlayer().getActiveFighter().getSuperAttacks().size();j++){
			if(game.getPlayer().getActiveFighter().getSuperAttacks().get(j).getName()== superFoe.getSelectedItem().toString()){
				oldAttack = game.getPlayer().getActiveFighter().getSuperAttacks().get(j);
				break;
			}
		}
		for(int i =0;i< (game.getPlayer().getSuperAttacks().size());i++){
			if(game.getPlayer().getSuperAttacks().get(i).getName()== superPlayer.getSelectedItem().toString()){
				game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(),game.getPlayer().getSuperAttacks().get(i), oldAttack);
				NewAttack = game.getPlayer().getSuperAttacks().get(i);
				JOptionPane.showMessageDialog(null,"You replaced the Attack Correctly");
				break;
			}
			
			
			}
			superFoe.removeItem(oldAttack.getName());
			superFoe.addItem(NewAttack.getName());
		
	}

	private void addUltimate() throws MaximumAttacksLearnedException, DuplicateAttackException, NotASaiyanException {
		for(int i =0;i< game.getPlayer().getUltimateAttacks().size();i++){
			if(game.getPlayer().getUltimateAttacks().get(i).getName()== ultimatePlayer.getSelectedItem().toString()){
				game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), game.getPlayer().getUltimateAttacks().get(i), null);
				JOptionPane.showMessageDialog(null,"You Added the Attack Correctly");
				break;
			}
			
			
			}
		for(int i=0;i<(game.getPlayer().getActiveFighter().getUltimateAttacks().size());i++){
			ultimateFoe.addItem(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName());
		}
		
	}

	private void addSuper() throws MaximumAttacksLearnedException, DuplicateAttackException, NotASaiyanException {
		for(int i =0;i< game.getPlayer().getSuperAttacks().size();i++){
		if(game.getPlayer().getSuperAttacks().get(i).getName()== superPlayer.getSelectedItem().toString()){
			game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), game.getPlayer().getSuperAttacks().get(i), null);
			JOptionPane.showMessageDialog(null,"You Added the Attack Correctly");
			break;
		}
		
		
		}
		superFoe.removeAll();
		for(int i=0;i<(game.getPlayer().getActiveFighter().getSuperAttacks().size());i++){
			superFoe.addItem(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName());
		}

	}

	public void createFighter() {
		game.getPlayer().createFighter(chosenRace.charAt(0), fighterName2.getText());
		dragonBallView.fightersView();
	}

	private void viewWorld() {
		SetText();
		dragonBallView.ViewWorld(txtArea);
		
	}
	private void previousFighter() {
		fighterslist = game.getPlayer().getFighters();
		int index = fighterslist.indexOf(fighterSelected);
		
		if(index >0){
			fighterSelected = fighterslist.get(index-1);
			changeplayer();
			validate();		
			}
		
	}
	private void nextFighter() {
		fighterslist = game.getPlayer().getFighters();
		int index = fighterslist.indexOf(fighterSelected);
		if(index < (fighterslist.size()-1)){
			fighterSelected = fighterslist.get(index+1);
			changeplayer();
			validate();		
			}
		
	}

	private void validate() {
		dragonBallView.validate();
		dragonBallView.repaint();
		
	}
	@Override
	public void onDragonCalled(Dragon dragon) {
		this.dragon = dragon;
		dragonBallView.showDragonView();

		
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		if(collectible == Collectible.DRAGON_BALL){
			dragonBallView.onDragonBallFound();
		}else{
			dragonBallView.onSenzuBeanFound();
		}
			SetText();
			dragonBallView.validate();
			dragonBallView.repaint();
			
	}
	

	@Override
	public void onBattleEvent(BattleEvent e) {
		
		battle= (Battle) e.getSource();
		if(e.getType()==BattleEventType.STARTED){
			music("dbz-3.au");
			if(battle.getMe() instanceof Saiyan){
				if(((Saiyan) battle.getMe()).isTransformed()){
					ImageIcon icon = new ImageIcon("SS.png");
					fighterChar.setIcon(icon);
					addTextBattleMee(this.SS,"TransFormed",0,14);
				}	
			}
			int currentHealthFoe = ((NonPlayableFighter)battle.getFoe()).getHealthPoints();
			int maxHealthFoe = ((NonPlayableFighter)battle.getFoe()).getMaxHealthPoints();
			int cHF = (currentHealthFoe*100)/maxHealthFoe;
			
			int currentKiFoe = ((NonPlayableFighter)battle.getFoe()).getKi();
			int maxKiFoe = ((NonPlayableFighter)battle.getFoe()).getMaxKi();
			int cKF = (currentKiFoe*100)/maxKiFoe;
			
			int currentStaminaFoe = ((NonPlayableFighter)battle.getFoe()).getStamina();
			int maxStaminaFoe = ((NonPlayableFighter)battle.getFoe()).getMaxStamina();
			int cSF = (currentStaminaFoe*100)/maxStaminaFoe;
			
			int currentHealth = game.getPlayer().getActiveFighter().getHealthPoints();
			int maxHealth = game.getPlayer().getActiveFighter().getMaxHealthPoints();
			int cH = (currentHealth*100)/maxHealth;
			
			int currentKi = game.getPlayer().getActiveFighter().getKi();
			int maxKi = game.getPlayer().getActiveFighter().getMaxKi();
			int cK = (currentKi*100)/maxKi;
			
			int currentStamina = game.getPlayer().getActiveFighter().getStamina();
			int maxStamina = game.getPlayer().getActiveFighter().getMaxStamina();
			int cS = (currentStamina*100)/maxStamina;
			addplayerinfo();
			
			addTextBattleMe(myHealth,"Health: " + Integer.toString(currentHealth) + " / " + Integer.toString(maxHealth),0,3);
			addprogressBars(health,cH ,100,0,4);
			addTextBattleMe(myKi,"Ki: " + Integer.toString(currentKi) + " / " + Integer.toString(maxKi),0,5);
			addprogressBars(ki,cK,100,0,6);
			addTextBattleMe(myStamina,"Stamina: " + Integer.toString(currentStamina) + " / " + Integer.toString(maxStamina),0,7);
			addprogressBars(stamina,cS,100,0,8);
			
			
			addTextBattleFoe(foeLabel,"Name: " + ((NonPlayableFighter)battle.getFoe()).getName(),0,1);
			addTextBattleFoe(foeLevel,"Level: "+ ((NonPlayableFighter)battle.getFoe()).getLevel(),0,2);
			addTextBattleFoe(FoeHealth,"Health: " + Integer.toString(currentHealthFoe) + " / " + Integer.toString(maxHealthFoe),0,3);
			addprogressBarsFoe(health1,cHF ,100,0,4);
			addTextBattleFoe(FoeKi,"Ki: " + Integer.toString(currentKiFoe) + " / " + Integer.toString(maxKiFoe),0,5);
			addprogressBarsFoe(ki1,cKF,100,0,6);
			addTextBattleFoe(FoeStamina,"Stamina: " + Integer.toString(currentStaminaFoe) + " / " + Integer.toString(maxStaminaFoe),0,7);
			addprogressBarsFoe(stamina1,cSF,100,0,8);
			
			switch(((NonPlayableFighter)battle.getFoe()).getName()){
			case "Goku" : addCharsToBattle(foeChar,"Goku.png",BorderLayout.EAST);break;
			case "Gohan (Kid)": addCharsToBattle(foeChar,"Gohan.png",BorderLayout.EAST);break;
			case "Krillin" : addCharsToBattle(foeChar,"Krillin.png", BorderLayout.EAST);break;
			case "Yamcha" : addCharsToBattle(foeChar,"Yamcha.png",BorderLayout.EAST);break;
			case "Tien" : addCharsToBattle(foeChar,"Tien.png",BorderLayout.EAST);break;
			case "Piccolo" : addCharsToBattle(foeChar,"Piccolo.png", BorderLayout.EAST);break;
			case "Raditz": addCharsToBattle(foeChar,"Raditz.png", BorderLayout.EAST);break;
			case "Nappa" : addCharsToBattle(foeChar,"Nappa.png",BorderLayout.EAST);break;
			case "Vegeta" : addCharsToBattle(foeChar,"Vegeta.png", BorderLayout.EAST);break;
			case "Saibaman" : addCharsToBattle(foeChar,"Saibaman.png", BorderLayout.EAST);break;
			case "Tennenmen" : addCharsToBattle(foeChar,"Saibaman.png", BorderLayout.EAST);break;
			case "Jinkoumen" : addCharsToBattle(foeChar,"Saibaman.png", BorderLayout.EAST);break;
			case "Raspberry" : addCharsToBattle(foeChar,"Raspberry.png", BorderLayout.EAST);break;
			case "Navel" : addCharsToBattle(foeChar,"Raspberry.png", BorderLayout.EAST);break;
			case "Monre" : addCharsToBattle(foeChar,"Raspberry.png",BorderLayout.EAST);break;
			case "Ramon" : addCharsToBattle(foeChar,"Orlen.png",BorderLayout.EAST);break;
			case "Orlen" : addCharsToBattle(foeChar,"Orlen.png", BorderLayout.EAST);break;
			default: addCharsToBattle(foeChar,"beerus.png", BorderLayout.EAST);break;
			}
			activeFighter1 = game.getPlayer().getActiveFighter();
			
			if(activeFighter1 instanceof Saiyan){
				addCharsToBattle(fighterChar,"GokuP.png",BorderLayout.WEST);
			}else
				if(activeFighter1 instanceof Majin)
					addCharsToBattle(fighterChar,"MajinP.png",BorderLayout.WEST);
				else
					if(activeFighter1 instanceof Namekian)
						addCharsToBattle(fighterChar,"NamekianP.png",BorderLayout.WEST);
					else
						if(activeFighter1 instanceof Frieza)
							addCharsToBattle(fighterChar,"FriezaP.png", BorderLayout.WEST);
						else
							if(activeFighter1 instanceof Earthling)
								addCharsToBattle(fighterChar,"EarthlingP.png", BorderLayout.WEST);
			
		
		dragonBallView.NewBattle();
		JOptionPane.showMessageDialog(null,"Ops, A foe");
		}else
			if(e.getType()==BattleEventType.ENDED){
				stopMusic();
				dragonBallView.disposeView();
				SetText();
				String info = "";
				if(e.getWinner()==game.getPlayer().getActiveFighter()){
					info+="Xp: "+ game.getPlayer().getActiveFighter().getXp() + "\n" + "SuperAttack: ";
					for(int i =0;i<((NonPlayableFighter) battle.getFoe()).getSuperAttacks().size();i++){
						info+=  ((NonPlayableFighter) battle.getFoe()).getSuperAttacks().get(i).getName();
					}
					info+= "\n" + "UltimateAttacks: ";
					for(int i =0;i<((NonPlayableFighter) battle.getFoe()).getUltimateAttacks().size();i++){
						info+= ((NonPlayableFighter) battle.getFoe()).getUltimateAttacks().get(i).getName() ;
					}
					if(game.getPlayer().getActiveFighter().getLeveled()){
						info+= "\n" + "New Level: " + game.getPlayer().getActiveFighter().getLevel() +"\n" +
					"TargetXp: " + game.getPlayer().getActiveFighter().getTargetXp() + "\n"
					+ "Gained Ability Points: " + game.getPlayer().getActiveFighter().getAbilityPoints();
					}
					if(((NonPlayableFighter) battle.getFoe()).isStrong()){
						JOptionPane.showMessageDialog(null,"You Beated The Boss And Explored A New Map");
					}else{
						JOptionPane.showMessageDialog(null,"You Won The Battle");
					}
				for(int i=0;i<(game.getPlayer().getSuperAttacks().size());i++){
					if(!Exist(game.getPlayer().getSuperAttacks().get(i).getName(),superPlayer)){
					superPlayer.addItem(game.getPlayer().getSuperAttacks().get(i).getName());
					}
				}
				for(int i=0;i<(game.getPlayer().getUltimateAttacks().size());i++){
					if(!Exist(game.getPlayer().getUltimateAttacks().get(i).getName(),ultimatePlayer)){
					ultimatePlayer.addItem(game.getPlayer().getUltimateAttacks().get(i).getName());
					}
				}
				JOptionPane.showMessageDialog(null,info);
				}else{
					
					JOptionPane.showMessageDialog(null,"You Lost the Battle, You will return to the beginning cell");
				}
				try {
					clearAll();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dragonBallView.ViewWorld(txtArea);
			}
		if(e.getType()==BattleEventType.NEW_TURN){
			 new Timer().schedule(new TimerTask() 
		       {
		            public void run() 
		            {
		            	updateV();
		            }
		       }, 1000);
			String x = "Player Turn: " + ((Fighter) battle.getAttacker()).getName() ;
			turn.removeAll();
			turn.setText(x);
			turn.setFont(new Font("Dragonfly", Font.BOLD, 20));
			turn.setForeground(Color.WHITE);
			addCharsToBattle(turn,null,BorderLayout.NORTH);
			
			updateV();
			if(battle.getAttacker() instanceof NonPlayableFighter){
				try {
					battle.play();
				} catch (NotEnoughKiException e1) {
					battle.block();
				}
			}
			
		}else
			if(e.getType()==BattleEventType.ATTACK){
				if(battle.getAttacker() instanceof PlayableFighter)
				addTextBattleMee(this.Action,"ATTACK",0,0);
				else
					addTextBattleFoee(this.ActionFoe,"ATTACK",0,0);
				
			}else
				if(e.getType()==BattleEventType.BLOCK){
					if(battle.getAttacker() instanceof PlayableFighter)
					addTextBattleMee(this.Action,"BLOCK",0,0);
					else
						addTextBattleFoee(this.ActionFoe,"BLOCK",0,0);
				}else
					if(e.getType()==BattleEventType.USE){
						if(battle.getAttacker() instanceof PlayableFighter)
						addTextBattleMee(this.Action,"USE",0,0);
						else
							addTextBattleFoee(this.ActionFoe,"USE",0,0);
					}
		}

	public void addTextBattleFoee(JLabel label, String text, int x, int y){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		
		label.removeAll();
		label.setText(text);
		label.setFont(new Font("Dragonfly", Font.BOLD, 20));
		label.setForeground(Color.RED);	
		dragonBallView.addTextBattleFoe(label,c);
	}
	public void addTextBattleMee(JLabel label, String text, int x, int y){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		
		label.removeAll();
		label.setText(text);
		label.setFont(new Font("Dragonfly", Font.BOLD, 20));
		label.setForeground(Color.RED);		
		dragonBallView.addText(label,c);
	}
	private boolean Exist(String item, JComboBox<String> myComboBox) {
		 boolean exists = false;
		 for (int index = 0; index < myComboBox.getItemCount() && !exists; index++) {
		   if (item.equals(myComboBox.getItemAt(index))) {
		     exists = true;
		   }
		 }
		 return exists;
	}

	public static void main(String[] args) throws IOException {
		new DragonBallGUI();
	}
	public void clearAll() throws IOException{
		for (int i = 0; i < 10; i++){
			for(int j=0;j<10;j++){
				
				BufferedImage Image = null;
				if(i==0 && j ==0){
					Image = ImageIO.read(new File("Boss1.png"));
					mapButtons[i][j].setIcon(new ImageIcon(Image));
				}else
					if(i==game.getWorld().getPlayerRow() && j ==game.getWorld().getPlayerColumn()){
						Image = ImageIO.read(new File("Char1.png"));
						mapButtons[i][j].setIcon(new ImageIcon(Image));
					}else{
						mapButtons[i][j].setIcon(null);
					}
				
			}
		}
		
	}
	
	
	public void up() throws IOException{
		
		game.getWorld().moveUp();
		clearAll();
		//dragonBallView.changeView(mapButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]);
	}
	public void down() throws IOException{
	
		game.getWorld().moveDown();
		clearAll();
		//dragonBallView.changeView(mapButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]);

	}
	public void left() throws IOException{
		
		game.getWorld().moveLeft();
		clearAll();
		//dragonBallView.changeView(mapButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]);

	}
	public void right() throws IOException{
		
		game.getWorld().moveRight();
		clearAll();
		//dragonBallView.changeView(mapButtons[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]);
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code== KeyEvent.VK_UP){
			try {
				up();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch( MapIndexOutOfBoundsException e2){
			}
		}else
			if(code == KeyEvent.VK_DOWN){
				try {
					down();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch( MapIndexOutOfBoundsException e2){
				}
			}else
				if(code == KeyEvent.VK_LEFT){
					try {
						left();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch( MapIndexOutOfBoundsException e2){
					}
				}else
					if(code == KeyEvent.VK_RIGHT){
						try {
							right();
						} catch (IOException e1 ) {
							
						}catch( MapIndexOutOfBoundsException e2){
						}
					}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton x = (JButton)(e.getSource());
		x.setForeground(Color.GREEN);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton x = (JButton)(e.getSource());
		x.setForeground(Color.RED);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
