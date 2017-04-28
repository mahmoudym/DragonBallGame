package dragonball.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DragonDialog extends JDialog implements ActionListener{
	private JButton collect;
    private Image image;
    
    public DragonDialog(){
    	setSize(500,400);
    	setModal(true);
    	JLabel label = new JLabel(new ImageIcon("dragonball.gif"));
    	getContentPane().setLayout(new BorderLayout());
    	getContentPane().add(label);
    	this.setUndecorated(true);
    }

    public void setBackgroundImage(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
   

}