package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.scene.shape.Box;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;

import com.sun.xml.internal.ws.api.Component;

@SuppressWarnings("serial")
public class DragonBallView extends JFrame{
	private MainMenu mainMenu;
	private DragonMenu dragonMenu;
	private CreateFighter createFighter;
	private CreateFighter1 createFighter1;
	private String previousMenu;
	private ButtonGroup Character = new ButtonGroup();
	private WorldView worldView;
	private Info info;
	private BattleView battleView;
	private MeStats meStats;
	private FoeStats foeStats;
	private FightersView fightersView;
	private DragonDialog dragonDialog;
	private AssignAttack assignAttack;
	private AttackView superAttackView;
	private AttackView ultimateAttackView;
	private JDialog attackMenu;
	private JDialog ballFound;
	private DragonView dragonView;
	private DragonBallPanel ballF;

	public DragonBallView() throws IOException{
		//Frame Info
		setTitle("DragonBall Adventures");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setSize(1024,768);
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1280,720));
		setVisible(true);
	
		// initializing panels
		worldView = new WorldView();
		mainMenu = new MainMenu();
		dragonMenu = new DragonMenu();
		createFighter = new CreateFighter();
		info = new Info();
		battleView = new BattleView();
		meStats = new MeStats();
		foeStats = new FoeStats();
		fightersView = new FightersView();
		dragonView = new DragonView();
		createFighter1 = new CreateFighter1();
		dragonDialog = new DragonDialog();
		assignAttack = new AssignAttack();
		attackMenu = new JDialog();
		ballFound = new JDialog();
		superAttackView = new AttackView();
		ultimateAttackView = new AttackView();
		ballF = new DragonBallPanel();
		
		//initializing visibility
		invisibleAll();
		mainMenu.setVisible(true);
		
		
		//setting layouts
		setLayout(new BorderLayout());
		worldView.setLayout(new GridLayout(0,10));
		createFighter.setLayout(new GridBagLayout());
		createFighter1.setLayout(new GridBagLayout());
		dragonMenu.setLayout(new GridLayout(4,0));
		mainMenu.setLayout(new GridLayout(3,0));
		info.setLayout(new GridBagLayout());
		battleView.setLayout(new BorderLayout());
		meStats.setLayout(new GridBagLayout());
		foeStats.setLayout(new GridBagLayout());
		fightersView.setLayout(new GridBagLayout());
		dragonDialog.setLayout(new GridBagLayout());
		assignAttack.setLayout(new GridBagLayout());
		superAttackView.setLayout(null);
		ultimateAttackView.setLayout(null);
		dragonView.setLayout(new GridBagLayout());
		ballFound.setLayout(new BorderLayout());
		attackMenu.setLayout(new BorderLayout());
		ballF.setLayout(new BorderLayout());
		getContentPane().add(mainMenu,BorderLayout.CENTER);
		
		previousMenu = "mainMenu";
		
		
	    this.validate();
	    this.repaint();
	}
	public void addCombotoView(JComboBox<String> combo, int i, int j, int k,
			int l) {
		superAttackView.remove(combo);
		combo.setBounds(i, j, k, l);
		combo.setBackground(new Color(0,0,0,0));
		superAttackView.add(combo);
		
	}
	public void addCombotouView(JComboBox<String> combo, int i, int j, int k,
			int l) {
		combo.setBounds(i, j, k, l);
		combo.setBackground(new Color(0,0,0,0));
		ultimateAttackView.add(combo);
	}
	public void ButtontouAttckView(JButton button, int i, int j) {
		button.setBounds(i,j,200,70);
		ultimateAttackView.add(button);
		
	}
	public void ultimateAttack(){
		superAttackView.setVisible(false);
		attackMenu.setBounds(500,500,600,500);
		attackMenu.setUndecorated(true);
		attackMenu.getContentPane().removeAll();
		attackMenu.add(ultimateAttackView);
		ultimateAttackView.setVisible(true);
		attackMenu.setLocationRelativeTo(this);
		attackMenu.setVisible(true);
		
		
		this.validate();
		this.repaint();
	}
	
	public void addWishToDragonView(JButton button, int x, int y,GridBagConstraints c) {
		dragonView.add(button,c);
		this.validate();
		this.repaint();
		
	}

	public void AttackMenu(){
		ultimateAttackView.setVisible(false);
		attackMenu.setBounds(500,500,600,500);
		attackMenu.setUndecorated(true);
		attackMenu.getContentPane().removeAll();
		attackMenu.add(superAttackView);
		superAttackView.setVisible(true);
		attackMenu.setLocationRelativeTo(this);
		attackMenu.setVisible(true);
		
		
		this.validate();
		this.repaint();
	}
	public void addCollect(JButton button) {
		ballF.add(button,BorderLayout.SOUTH);
		this.validate();
		this.repaint();
		
		
	}
	public void closeDragon(){
		ballFound.dispose();
	}
	public void onDragonBallFound(){
		java.awt.Image image = new ImageIcon("DragonBallBall.gif").getImage();
		ballF.setBackgroundImage(image);
		openFoundMenu();
	}
	public void openFoundMenu(){
		ballFound.setPreferredSize(new Dimension(400,400));
		ballFound.setBounds(400,400,600,500);
		ballFound.setUndecorated(true);
		ballFound.getContentPane().add(ballF);
		ballF.setVisible(true);
		ballFound.setLocationRelativeTo(this);
		ballFound.setVisible(true);
		
		this.validate();
		this.repaint();
	}
	public void onSenzuBeanFound(){
		java.awt.Image image = new ImageIcon("SenzuBean.gif").getImage();
		ballF.setBackgroundImage(image);
		openFoundMenu();
	}
	
	public void createFighter1View(){
		getContentPane().add(createFighter1,BorderLayout.CENTER);
		fightersView.setVisible(false);
		createFighter1.setVisible(true);
		
	}
	
	public void AssignAttack(){
		getContentPane().add(assignAttack);
		invisibleAll();
		assignAttack.setVisible(true);
		this.validate();
		this.repaint();
	}
	
	public void fightersView(){
		getContentPane().removeAll();
		getContentPane().add(fightersView);
		invisibleAll();
		fightersView.setVisible(true);
		this.validate();
		this.repaint();
	}
	public void addTextBox(JTextField text){
		createFighter.add(text);
		this.validate();
		this.repaint();
	}
	public void ViewWorld(JTextArea txtArea){
		invisibleAll();
		getContentPane().removeAll();
		getContentPane().add(worldView, BorderLayout.CENTER);
		getContentPane().add(info, BorderLayout.EAST);
		info.setPreferredSize(new Dimension(300,this.getHeight()));
		worldView.setVisible(true);
		info.setVisible(true);
		
		
		this.validate();
		this.repaint();
		
	}
	public void addTextArea(JTextArea txtArea, GridBagConstraints c){
		txtArea.setVisible(true);
		txtArea.setBackground(Color.YELLOW);
		info.add(txtArea,c);
	}
	
	
	public void addtoBag(JRadioButton x, GridBagConstraints c){
		Character.add(x);
		createFighter.add(x,c);
		this.validate();
		this.repaint();
		
	}
	public void addtoBag1(JRadioButton x, GridBagConstraints c){
		Character.add(x);
		createFighter1.add(x,c);
		this.validate();
		this.repaint();
		
	}
	public void addMenuButtons(JButton x, JPanel View){
	    	View.add(x);
	    	this.validate();
	    	this.repaint();
	    }
	public void changeView(){
		
		this.getContentPane().add(BorderLayout.CENTER, dragonMenu);
		mainMenu.setVisible(false);
		dragonMenu.setVisible(true);
		this.validate();
		this.repaint();
	}
	public void previousMenu(){
		if(previousMenu.equals("mainMenu")){
			invisibleAll();
			getContentPane().add(mainMenu);
			mainMenu.setVisible(true);
		}
	}
	
	public void NewGame(){
		getContentPane().removeAll();
		getContentPane().add(BorderLayout.CENTER,createFighter);
		invisibleAll();
		createFighter.setVisible(true);
		this.validate();
		this.repaint();
		
	}
	public void showDragonView() {
		invisibleAll();
		getContentPane().add(dragonView, BorderLayout.CENTER);
		dragonView.setVisible(true);
		this.validate();
		this.repaint();
		
	}
	public void invisibleAll(){
		worldView.setVisible(false);
		mainMenu.setVisible(false);
		dragonMenu.setVisible(false);
		createFighter.setVisible(false);
		info.setVisible(false);
		battleView.setVisible(false);
		meStats.setVisible(false);
		foeStats.setVisible(false);
		fightersView.setVisible(false);
		createFighter1.setVisible(false);
		assignAttack.setVisible(false);
		dragonView.setVisible(false);
	}
	 public MainMenu getMainMenu() {
			return mainMenu;
		}
	 public DragonMenu getDragonMenu(){
		 return dragonMenu;
	 }

	public CreateFighter getCreateFighter() {
		return createFighter;
	}
	
	public WorldView getWorldView(){
		return worldView;
	}
	
	public Info getInfo() {
		return info;
	}
	
	public BattleView getBattleView(){
		return battleView;
	}
	
	public CreateFighter1 getCreateFighter1(){
		return createFighter1;
	}
	
	public void addMap(JButton button) throws IOException {
		if(button.getName().equals("99")){
			BufferedImage Image = ImageIO.read(new File("Char1.png"));
			button.setIcon(new ImageIcon(Image));
		}
		if(button.getName().equals("00")){
			BufferedImage Image = ImageIO.read(new File("Boss1.png"));
			button.setIcon(new ImageIcon(Image));
		}
		button.setVisible(true);
		worldView.add(button);
		this.validate();
		this.repaint();
		
	}
public void changeView(JButton button) throws IOException{
		
		BufferedImage Image = ImageIO.read(new File("Char1.png"));
		button.setIcon(new ImageIcon(Image));
		this.validate();
		this.repaint();
	}
public void addFighterName(JTextField fighterName, GridBagConstraints c) {
	createFighter.add(fighterName, c);
	this.validate();
	this.repaint();
	
	
}
public void changeBoss(JButton Button) throws IOException {
	BufferedImage Image = ImageIO.read(new File("Boss1.png"));
	Button.setIcon(new ImageIcon(Image));
	this.validate();
	this.repaint();
	
}
public void addButtonstoinfo(JButton button, GridBagConstraints c) {
	info.add(button, c);
	this.validate();
	this.revalidate();
	this.repaint();
	
}
public void addButtontoCF(JButton button, GridBagConstraints c) {
	createFighter.add(button,c);
	this.validate();
	this.revalidate();
	this.repaint();
	
}
public void NewBattle() {
	getContentPane().removeAll();
	invisibleAll();
	getContentPane().add(battleView, BorderLayout.CENTER);
	getContentPane().add(meStats, BorderLayout.WEST);
	getContentPane().add(foeStats, BorderLayout.EAST);
	meStats.setPreferredSize(new Dimension(250,this.getHeight()));
	meStats.setVisible(true);
	foeStats.setPreferredSize(new Dimension(250,this.getHeight()));
	foeStats.setVisible(true);
	battleView.setVisible(true);
	this.validate();
	this.repaint();
	
}
public void addProgressBars(JProgressBar pb, GridBagConstraints c) {
	meStats.add(pb,c);
	this.validate();
	this.repaint();
}
public void addText(JLabel playerfighterinfo, GridBagConstraints c) {
	meStats.add(playerfighterinfo,c);
	this.validate();
	this.repaint();
	
}
public void addButtontostats(JButton button, GridBagConstraints c) {
	meStats.add(button,c);
	
}
public void addProgressBarsFoe(JProgressBar pb, GridBagConstraints c) {
	foeStats.add(pb,c);
	this.validate();
	this.repaint();
}
public void addTextBattleFoe(JLabel label, GridBagConstraints c) {
	foeStats.add(label,c);
	this.validate();
	this.repaint();
	
}

public void addplayerinfot(JTextArea playerinfo, GridBagConstraints c) {
	fightersView.add(playerinfo,c);
	this.validate();
	this.repaint();
}

public void addPictoF(JLabel fighter, GridBagConstraints c) {
	fightersView.add(fighter,c);
	this.validate();
	this.repaint();
}

public void addButtontoF(JButton button, GridBagConstraints c) {
	fightersView.add(button,c);
	this.validate();
	this.repaint();
	
}
public void addCharsToBattle(JLabel fighterChar,String p) {
	battleView.add(fighterChar,p);
	this.validate();
	this.repaint();
	
}
public void addTurn(JLabel turn, GridBagConstraints c) {
	meStats.add(turn,c);
	this.validate();
	this.repaint();
	
}

public void addButtontoCF1(JButton button, GridBagConstraints c) {
	createFighter1.add(button,c);
	this.validate();
	this.revalidate();
	this.repaint();	
}

public void addFighterName1(JTextField fighterName, GridBagConstraints c) {
	createFighter1.add(fighterName, c);
	this.validate();
	this.repaint();
	
}
public void addtoAssign(JComboBox<String> combobox,int x , int y ) {
	GridBagConstraints c = new GridBagConstraints();
	c.gridx = x;
	c.gridy = y;
	combobox.setPreferredSize(new Dimension(200,70));
	combobox.setMaximumSize( combobox.getPreferredSize() );
	combobox.setOpaque(false);
	combobox.setVisible(true);
	combobox.setBackground(new Color(0,0,0,0));
	assignAttack.add(combobox,c);	
}
public void addtoBAssign(JButton button, int i, int j) {
	button.setPreferredSize(new Dimension(200,70));
	button.setMaximumSize( button.getPreferredSize() );
	GridBagConstraints c = new GridBagConstraints();
	c.gridx = i;
	c.gridy = j;
	assignAttack.add(button,c);
	
}
public void ButtontoAttckView(JButton button, int i, int j) {
	button.setBounds(i,j,200,70);
	superAttackView.add(button);
	
}
public void disposeView() {
	attackMenu.dispose();
	
}
public void Transformed() {
	
	
}



	
	
}
