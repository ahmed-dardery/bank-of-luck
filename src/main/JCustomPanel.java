package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JPanel;

import main.Board.JSpace;
import main.Board.Orient;
import main.Country.Building;

public class JCustomPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public int die1 = -1;
	public int die2 = -1;
	public Country country = null;
	
	public BufferedImage background;
	public BufferedImage dieImages[];
	public BufferedImage carsImages[];
	public BufferedImage assetsImages[];
	
	Manager manager;
	
    public JSpace boxes[];

    Player orderList[];


	public JCustomPanel(GridBagLayout gridBagLayout, Manager manager2) {
		super(gridBagLayout);
		manager = manager2;
		orderList = new Player[manager.nPlayers];
		for(int i = 0; i < manager.nPlayers; ++i){
			orderList[i] = manager.players[i];
		}
	}
	@Override
    public Dimension getPreferredSize() {
		double ratio = 4000/2884.0; 
		Dimension d = this.getParent().getSize();
	    int w = d.width;
	    int h = d.height;
	    double r = ((double)w)/h;
	    if (r > ratio)
	      	w = h * 4000 / 2884;
	    else
	       	h = w * 2884 / 4000;	
	    
        return new Dimension(w, h);
    }
    
	@Override
    protected void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D)gg;
		g.drawImage(background,0,0,this.getWidth(),this.getHeight(),null);
		
		drawPlayerTurn(g);
		drawPlayerList(g);
		drawCountryDetails(g);
		drawOwnerIdentification(g);
		drawDie(g);
		drawCars(g);
    }
	private void drawCars(Graphics2D g) {
		Arrays.sort(orderList);
		int last = -2;
		int priority = 0;
		for(int i = 0; i < manager.nPlayers; i++){
			Player pl = orderList[i];
			if (pl.position == last)
				priority++;
			else{
				priority = 0;
				last = pl.position;
			}
    		   	
			drawSingleCar(pl.color, pl.position,pl.unavailable || pl.jailed, priority, g);
		}
	}
	private void drawDie(Graphics2D g) {
		if (die1 != -1){
			int x = 3180 * this.getWidth() / 4000;
			int y = 500 * this.getWidth() / 4000;
			int w = 300 * this.getWidth() / 4000;
			g.drawImage(dieImages[die1],x,y,w,w,null);   
		}
		if (die2 != -1){
			int x = 2800 * this.getWidth() / 4000;
			int y = 500 * this.getWidth() / 4000;
			int w = 300 * this.getWidth() / 4000;
			g.drawImage(dieImages[die2],x,y,w,w,null);   
		}
	}
	void drawPlayerTurn(Graphics2D g) {
		Font myFont = new Font(Font.SANS_SERIF,Font.PLAIN,200 * this.getWidth()/4000);
		g.setFont(myFont);
		
		g.setColor(Color.BLACK);
		g.setXORMode(Color.WHITE);
		int turnWidth = 0;
		for(int i = 0; i < manager.nPlayers; ++i)
			turnWidth = Math.max(turnWidth, g.getFontMetrics().stringWidth(manager.players[i].name));
		
		turnWidth+=g.getFontMetrics().stringWidth("   دور   ");
		
		int turnBoxX = (int)manager.frame.middlePanel.getBounds().getCenterX() - turnWidth / 2;
		g.fillRect(turnBoxX, 470*this.getWidth()/4000, turnWidth, 270*this.getWidth()/4000);
		g.setPaintMode();
		
		g.setColor(manager.players[manager.playerTurn].color);
       
       
       
       	String current = "دور " + manager.players[manager.playerTurn].name;
       	int xm = (int)manager.frame.middlePanel.getBounds().getCenterX() - g.getFontMetrics().stringWidth(current) / 2;
       
       	g.drawString(current,xm,640*this.getWidth()/4000);
	}
	void drawOwnerIdentification(Graphics2D g){
		for(int i = 0; i < Manager.mapSize; ++i){
			
			if (manager.map[i].type == Types.Country){
				Country co = manager.map[i].country;
				if (co.owner == null) continue;
			
				int x, y, sz;
				if (boxes[i].orientation == Orient.TOP || boxes[i].orientation == Orient.BOTTOM){
					sz = 275 * boxes[i].getHeight() / 1176;
				}
				else{
					sz = 275 * boxes[i].getWidth() / 1176;
				}
				
				if (boxes[i].orientation != Orient.ANTI_CLOCKWISE){
					x = boxes[i].getX() + boxes[i].getWidth() - sz - sz/10;
				}
				else{
					x = boxes[i].getX() + sz/10;
				}
				
				y = boxes[i].getY() + sz / 10;
				
				if (co.establishment == Building.NONE){
					g.setColor(co.owner.color);
					g.fillRoundRect(x, y, sz, sz, 100, 100);
					
					g.setStroke(new BasicStroke(15 * this.getWidth() / 4000));
					g.setColor(Color.BLACK);
					g.drawRoundRect(x, y, sz, sz, 100, 100);
				}
				else{			
					g.setColor(Color.BLACK);
					g.fillRoundRect(x, y, sz, sz,100,100);
					g.drawImage(Manager.dye(assetsImages[co.establishment.ordinal()-1],co.owner.color),x,y,sz,sz,null);
				}
			}
		}
	}
    void drawCountryDetails(Graphics2D g){
    	if (manager.getChoices() != null){
    		for(int i = 0; i < manager.getChoices().length; i++){
    			Rectangle bo = boxes[manager.getChoices()[i]].getBounds();
    			g.setColor(Color.BLACK);
    			g.setStroke(new BasicStroke(15 * this.getWidth() / 4000));
    			g.drawRect(bo.x,bo.y,bo.width,bo.height);
    		}
    	}
    	if (country != null){
	    	Font myFont = new Font(Font.SANS_SERIF,Font.PLAIN,100 * this.getWidth()/4000);
	    	g.setFont(myFont);
	    	g.setColor(Color.BLACK);
	    	g.drawString(country.name,600*this.getWidth()/4000,600*this.getWidth()/4000);
	    	myFont = new Font(Font.SANS_SERIF,Font.PLAIN,60 * this.getWidth()/4000);
	    	g.setFont(myFont);
	    	int margin = 70 * this.getWidth()/4000;
	    	int offy = 700 * this.getWidth()/4000;
	    	int offx = 1000 * this.getWidth()/4000;
	    	int i = 0;
	    	drawRightAligned("السعر : ",country.price, offx,offy + (i++ * margin),g);
	    	if (country.owner != null && country.establishment == Building.NONE) g.setColor(country.owner.color);
	    	drawRightAligned("الإيجار : ",country.rent,offx,offy + (i++ * margin),g);
	    	g.setColor(Color.BLACK);
	    	if (country.owner != null && country.establishment == Building.GARAGE) g.setColor(country.owner.color);
	    	drawRightAligned("الجراج : ",country.garageRate,offx,(offy + (i++ * margin)),g);
	    	g.setColor(Color.BLACK);
	    	if (country.owner != null && country.establishment == Building.REST) g.setColor(country.owner.color);
	    	drawRightAligned("الاستراحة : ", country.restRate,offx,(offy + (i++ * margin)),g);
	    	g.setColor(Color.BLACK);
	    	if (country.owner != null && country.establishment == Building.MARKET) g.setColor(country.owner.color);
	    	drawRightAligned("السوق : ", country.marketRate,offx,(offy + (i++ * margin)),g);
	    	g.setColor(Color.BLACK);
	    	drawRightAligned("سعر الجراج : ", country.garagePrice,offx,(offy + (i++ * margin)),g);
	    	drawRightAligned("سعر الاستراحة: ", country.restPrice,offx,(offy + (i++ * margin)),g);
	    	drawRightAligned("سعر السوق : ", country.marketPrice,offx,(offy + (i++ * margin)),g);
	    	if (country.owner != null){
	    		myFont = new Font(Font.SANS_SERIF,Font.PLAIN,80*this.getWidth()/4000);
	    		g.setFont(myFont);
		    	g.setColor(country.owner.color);
		    	drawRightAligned("المالك : " + country.owner.name,offx,(offy + (++i * margin)),g);
	    	}
    	}
    }
    void drawPlayerList(Graphics2D g){
    	g.setColor(Color.BLACK);
    	Font myFont = new Font(Font.SANS_SERIF,Font.PLAIN,70 * this.getWidth()/4000);
    	g.setFont(myFont);
    	int margin = 95;
    	g.setXORMode(Color.WHITE);
    	int w = 0;
    	for(int i = 0; i < manager.nPlayers; ++i)
    		w = Math.max(w, g.getFontMetrics().stringWidth(manager.players[i].name));
    	
    	w+=g.getFontMetrics().stringWidth("X : 999999 + 9999     ");
		g.fillRect(468 * this.getWidth()/4000, 1650 * this.getWidth()/4000, w, 770 * this.getWidth()/4000);
		g.setPaintMode();
    	for(int i = 0; i < manager.nPlayers; ++i){
    		Player pl = manager.players[i];
    		g.setColor(pl.color);
    		String extra = "";
    		if (pl.moneyChange > 0) extra = " + ";
    		else if (pl.moneyChange < 0) extra = " - ";
    		if (pl.moneyChange != 0) extra += Math.abs(pl.moneyChange);
    		
    		if (pl.discount != 0) extra += "  " + (int)(pl.discount * 100) + "% off!";
    		String state = "   ";
    		if (pl.unavailable) state = "X ";
    		else if (pl.jailed) state = "II ";
    		drawConcatString(state, pl.name , " : " + pl.priorMoney + extra,500*this.getWidth()/4000,(1800 + (i * margin))*this.getWidth()/4000,g);
    	}
    }
	void drawSingleCar(Color color, int pos,boolean drawBroken,int priority, Graphics2D g){
		if (pos < 0) return;
		pos %= 34;
		AffineTransform def = g.getTransform();
		
		Rectangle bo = boxes[pos].getBounds();
		g.translate(bo.x + bo.width/2, bo.y + bo.height/2);
		
		Orient or = boxes[pos].orientation;
		
		if (or == Orient.TOP || or == Orient.BOTTOM){
			int tmp = bo.width;
			bo.width = bo.height;
			bo.height = tmp;
		}
		double rotate = or.ordinal() * Math.PI / 2;;
		int x = 00, y = 41;
		switch (priority){
		case 0:
			x = 550;
			break;
		case 1:
			x = 250;
			break;
		case 2:
			x = -50;
			break;
		case 3:
			x = 850;
			break;
		}
		
		x = x * bo.width /1176;
		y = y * bo.height / 821;
		
		int carWidth = this.getWidth() / 28;
	
		int w = carWidth;
		int h = 520 * w / 260;
		
		if (or == Orient.TOP) x = bo.width - x - w;
		
		g.rotate(rotate);
		g.translate(-bo.width/2, -bo.height/2);
		
		int i;
		for(i = 0; i < 6; i++){
			if (Manager.COLORS[i] == color) break;
		}
		g.drawImage(carsImages[i], x, y, w, h, null);
		if (drawBroken) g.drawImage(carsImages[6], x, y, w, h, null);
		
		g.setTransform(def);
		
	}
	void drawRightAligned(String text, int x, int y, Graphics2D g){
		int w = g.getFontMetrics().stringWidth(text);
		g.drawString(text, x - w, y);
	}
	void drawConcatString(String text1, String text2, String text3, int x, int y, Graphics2D g){
		int w1 = g.getFontMetrics().stringWidth(text1);
		int w2 = g.getFontMetrics().stringWidth(text2);
		g.drawString(text1, x, y);
		g.drawString(text2, x + w1, y);
		g.drawString(text3, x + w1 + w2, y);
		
	}
	void drawRightAligned(String title,int num, int x, int y, Graphics2D g){
		int w = g.getFontMetrics().stringWidth(title);
		g.drawString(title, x - w, y);
		g.drawString(String.valueOf(num), x*3/5, y);
	}
}
