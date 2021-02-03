package main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import main.Country.Building;

public class Message {
	public static void show(Component display,String msg, String title){
		JOptionPane.showMessageDialog(display,msg, title,JOptionPane.INFORMATION_MESSAGE);
	}
	public static Building build(Manager manager, String msg, String title){
		

		
		JOptionPane optionPane = new JOptionPane();
	    optionPane.setMessage(msg);
	    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
	    
		JButton choices[] = new JButton[]{
				getButton(optionPane,"جراج",manager,1,50),
				getButton(optionPane,"استراحة",manager,2,50),
				getButton(optionPane,"سوق",manager,3,50),
				getButton(optionPane,"لا شيء",4,50),
		};
		//choices[3].setVerticalTextPosition(SwingConstants.CENTER);
	    
	    optionPane.setOptions(choices);
	    JDialog dialog = optionPane.createDialog(manager.frame, title);
	    dialog.setVisible(true);
	    if (optionPane.getValue() == null)
	    	optionPane.setValue(-1);
	    
	    int x = (int) optionPane.getValue();
	    
		if (x == 1) return Building.GARAGE;
		else if (x == 2) return Building.REST;
		else if (x == 3) return Building.MARKET;
		else if (x == 4) return Building.NONE;
		else return Building.NOT_CHANGED;
	}
	public static ImageIcon toColorizedIcon(BufferedImage image,Color color,int width,int height){
        BufferedImage dyed = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.drawImage(Manager.dye(image, color), 0,0,width,height, null);
        g.dispose();
        return new ImageIcon(dyed);
		
	} 
	  public static JButton getButton(final JOptionPane optionPane, String text, int index, int size) {
		  final JButton button = new JButton(text,new ImageIcon(new BufferedImage(size,size, BufferedImage.TYPE_INT_ARGB)));
		  setup(button,optionPane,index);
		  return button;
		  
	  }
	  public static JButton getButton(final JOptionPane optionPane, String text, Manager manager,int index,int size) {
		    final JButton button = new JButton(
		    		text, 
		    		toColorizedIcon(manager.frame.grid.assetsImages[index - 1],manager.players[manager.playerTurn].color,size,size)
		    		);
		    setup(button,optionPane,index);
		    return button;
	  }
	  private static void setup(JButton button, JOptionPane optionPane, int index){
		  button.addActionListener(e-> optionPane.setValue(index));

		  button.setHorizontalTextPosition(SwingConstants.CENTER);
		  button.setVerticalTextPosition(SwingConstants.BOTTOM);
	  }
}
