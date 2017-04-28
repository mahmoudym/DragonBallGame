package dragonball.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DragonBallPanel extends JPanel{
private Image image;
    
    public DragonBallPanel(){
    	setSize(500,500);
    	image = new ImageIcon("dragonball.gif").getImage();
    	
    }

    public void setBackgroundImage(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
