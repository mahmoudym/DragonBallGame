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

public class pic extends JPanel {

    private Image image;
    
    public pic(){
    	setSize(400,725);
    	image = new ImageIcon("vegeta.png").getImage();
    	
    }
    public void setImage(String image){
    	this.image = new ImageIcon(image).getImage();
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