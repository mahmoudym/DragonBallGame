package dragonball.view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class CreateFighter extends JPanel {

    private Image image;
    
    public CreateFighter(){
    	setSize(1024,768);
    	image = new ImageIcon("MainMenuV2.jpg").getImage();
    	
    }

    public void setBackgroundImage(Image image) {
        this.image = image;
        this.validate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
   

}