package dragonball.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MapButtons extends JButton{
	ImageIcon p,e;
	public MapButtons(){
		e= new ImageIcon(this.getClass().getResource("WBGB.jpg"));
		e= new ImageIcon(this.getClass().getResource("player.jpg"));
	}
}
