package main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Manager.ClickAction;

public class Board extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	public JCustomPanel grid;
	
	JButton Accept, Decline;
	JButton Roll, Manage;
	
	Manager manager;
	private JSpace boxes[] = new JSpace[Manager.mapSize];
	
	public enum ButtonAction{
		NONE,
		BUY_COUNTRY,
		REGISTER_CLUB,
		BUY_STATION,
		BRIBE_JAIL,
		CONFIRM_TRADE
	}
	private ButtonAction currentButtonAction = ButtonAction.NONE;

	ButtonAction getButtonAction() {
		return currentButtonAction;
	}
	
	void setButtonAction(ButtonAction currentButtonAction) {
		if (currentButtonAction != ButtonAction.NONE){
			this.currentButtonAction = currentButtonAction;
			Accept.setVisible(true);
			Decline.setVisible(true);
			setAcceptDecline();
			
			switch(currentButtonAction){
			case BUY_COUNTRY:
				Accept.setText("شراء الدولة");
				Decline.setText("ترك الدولة");
				break;
			case REGISTER_CLUB:
				Accept.setText("الاشتراك في النادي");
				Decline.setText("دفع التذكرة");
				break;
			case BUY_STATION:
				Accept.setText("شراء البنزينة");
				Decline.setText("ترك البنزينة");
				break;
			case BRIBE_JAIL:
				Accept.setText("دفع كفالة 50 جنيه");
				Decline.setText("عشش في السجن");
				break;
			case CONFIRM_TRADE:
				Accept.setText("قبول المقايضة");
				Decline.setText("رفض المقايضة");
				break;
			case NONE:
				break;
			}
		}
		else{
			Accept.setVisible(false);
			Decline.setVisible(false);
		}
		repaint();
	}

	JPanel middlePanel;


	public void setAcceptDecline(){
		if (Accept.isVisible()){
			int w = 500*grid.getWidth()/4000;
			int h = 200*grid.getWidth()/4000;
			int midx = (int)middlePanel.getBounds().getCenterX();
			int midy = 1350*grid.getWidth()/4000;
			Decline.setSize(w,h);
			Accept.setSize(w,h);
			Decline.setLocation(midx-w,midy);
			Accept.setLocation(midx-2*w,midy);
		}
		repaint();
		
	}
	
	public void setRollManage(){
		if (Manage.isVisible()){
			int x = 2700 * grid.getWidth() / 4000;
			int y = 350 * grid.getWidth() / 4000;
			int w = 325*grid.getWidth()/4000, h = 125*grid.getWidth()/4000;
			Roll.setLocation(x,y);
			Roll.setSize(w, h);
			x = 2300 * grid.getWidth() / 4000;
			Manage.setLocation(x ,y);
			Manage.setSize(w, h);			
		}
		repaint();

	}
	
	public Board(Manager manager2) throws IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				setAcceptDecline();
				setRollManage();
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				if (Accept.isVisible()) setAcceptDecline();
				if (Manage.isVisible()) setRollManage();
 			}
		});
		manager = manager2;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{};
		gridBagLayout.rowWeights = new double[]{};
		getContentPane().setLayout(gridBagLayout);
		
		grid = new JCustomPanel(gridBagLayout,manager);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(grid, gbc_panel);
		
		grid.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		double sizeratio = 821 / 1176.0;
		GridBagLayout gbl_grid = new GridBagLayout();
		gbl_grid.columnWeights = new double[]{1.0, sizeratio, sizeratio, sizeratio, sizeratio, 1.0, sizeratio, sizeratio, sizeratio, sizeratio, 1.0};
		gbl_grid.rowWeights = new double[]{1.0, sizeratio, sizeratio, sizeratio, sizeratio, sizeratio, sizeratio, 1.0};
		grid.setLayout(gbl_grid);
		
		initiateMap();
		
		middlePanel = new JPanel();
		middlePanel.setBackground(new Color(0,0,0,0));
		middlePanel.setLayout(null);
		GridBagConstraints gbc_middlePanel = new GridBagConstraints();
		gbc_middlePanel.gridheight = 6;
		gbc_middlePanel.gridwidth = 9;
		gbc_middlePanel.fill = GridBagConstraints.BOTH;
		gbc_middlePanel.gridx = 1;
		gbc_middlePanel.gridy = 1;
		grid.add(middlePanel, gbc_middlePanel);
		
		Accept = new JButton();
		middlePanel.add(Accept);
		Accept.setVisible(false);
		Accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent args0){
				Player pl = manager.players[manager.playerTurn];
				
				ButtonAction currentAction = getButtonAction();
				setButtonAction(ButtonAction.NONE);
				switch(currentAction){
					case BUY_COUNTRY:
						Country co = manager.map[pl.position].country;
						pl.changeMoney(- co.price);
						co.owner = pl;
						pl.countries.add(co);
						break;
					case BUY_STATION:
						pl.changeMoney(- Manager.STATION_PRICE);
						manager.stationOwner = pl;
						break;
					case REGISTER_CLUB:
						pl.changeMoney(- Manager.CLUB_PRICE);
						if (manager.clubMember1 == null)
							manager.clubMember1 = pl;
						else
							manager.clubMember2 = pl;
						break;
					case BRIBE_JAIL:
						pl.changeMoney(- Manager.JAIL_BRIBE);
						manager.setReadyToRoll(true);
						break;
					case CONFIRM_TRADE:
						manager.swapCountries();
						cancelTrade();
						break;
					case NONE:
						break;
				}
				if(currentAction != ButtonAction.BRIBE_JAIL && currentAction != ButtonAction.CONFIRM_TRADE)
					manager.nextPlayer();
			}
		});
		Decline = new JButton();

		middlePanel.add(Decline);
		Decline.setVisible(false);
		Decline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent args0){
				Player pl = manager.players[manager.playerTurn];
				
				ButtonAction currentAction = getButtonAction();
				
				setButtonAction(ButtonAction.NONE);
				
				switch(currentAction){
					case REGISTER_CLUB:
						pl.changeMoney(-Manager.CLUB_FARE);
						manager.nextPlayer();
						break;
					case BRIBE_JAIL:
						pl.unavailable = true;
						pl.jailed = false;
						manager.nextPlayer();
						break;
					case CONFIRM_TRADE:
						cancelTrade();
						break;
					default:
						manager.nextPlayer();
						break;
				}
				
			}
		});
		
		Roll = new JButton("ارمي الزهر");
		middlePanel.add(Roll);
		
		Roll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent args0){
				manager.getRoll(manager.players[manager.playerTurn].index);
			}
		});
		
		Manage = new JButton("الممتلكات");
		middlePanel.add(Manage);
		
		Manage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent args0){
				if (Manage.getText().equals("الغاء الامر")){
					cancelTrade();
					return;
				}
				Player pl = manager.players[manager.playerTurn];
				
				JOptionPane optionPane = new JOptionPane();
				optionPane.setMessage("عايز تعمل مقايضة ام تبني جراج او استراحة او سوق؟");
				optionPane.setOptions(new String[]{"مقايضة","بناء"});
				JDialog dialog = optionPane.createDialog(manager.frame, "بنك الحظ");
			    dialog.setVisible(true);
			    if (optionPane.getValue() == null) return; 
			    
			    if (optionPane.getValue().toString() == "بناء"){
			    	int[] groupNums = new int[9]; 
			    	final Color[] colors = new Color[]
			    		{Color.CYAN, Color.GREEN, Color.ORANGE, Color.BLUE, 
			    		 Color.LIGHT_GRAY,Color.MAGENTA,Color.YELLOW,Color.WHITE,Color.PINK};
			    	
			    	ArrayList<Country> res = new ArrayList<Country>();
			    	
			    	for(Country co : pl.countries)
			    		for(int j = 0; j < 9; ++j)
			    			if (colors[j] == co.groupColor)
			    				groupNums[j] += co.groupId;
			    		
			    	for(int i = 0; i < 9; ++i)
			    		if (groupNums[i] == 6)
			    			for (Country co: pl.countries)
			    				if (co.groupColor == colors[i])
			    					res.add(co);
			    	
			    	int n = res.size();
			    	
			    	if (n == 0){
			    		Message.show(manager.frame,"أنت لا تمتلك اي بلاد يمكن البناء عليها", "بنك الحظ");
			    		return;			    		
			    	}
			    	int choices[] = new int[n];
			    	for(int i = 0; i < n; ++i)
			    		choices[i] = res.get(i).indexOnMap;
			    	
			    	Manage.setText("الغاء الامر");
			    	Roll.setVisible(false);
			    	manager.setCountryAction(choices,ClickAction.BUILD);
			    	return;
			    	
			    }
			    else{
			    	int n = pl.countries.size();
			    	if (n == 0) {
			    		Message.show(manager.frame,"أنت لا تمتلك اي بلاد للمقايضة", "بنك الحظ");
			    		return;
			    	}
			    	int choices[] = new int[n];
			    	for(int i = 0; i < n; ++i){
			    		choices[i] = pl.countries.get(i).indexOnMap;
			    	}
			    	Manage.setText("الغاء الامر");
			    	Roll.setVisible(false);
			    	manager.setCountryAction(choices, ClickAction.TRADE);
					return;
			    }
			}
		});
		
		
		loadImages();
	}
	private void cancelTrade() {
		setButtonAction(ButtonAction.NONE);
		manager.resetCountryAction();
		manager.setReadyToRoll(true);
		repaint();
		return;
	}
	
	private void initiateMap() {
		GridBagConstraints gbc_boxes[] = new GridBagConstraints[Manager.mapSize];
		int x = 0;
		int y = 7;
		int xch = 0;
		int ych = -1;
		for (int i = 0; i < Manager.mapSize; i++){
			boxes[i] = new JSpace();
			boxes[i].index = i;
			//boxes[i].setOpaque(true);
			gbc_boxes[i] = new GridBagConstraints();
			gbc_boxes[i].fill = GridBagConstraints.BOTH;
			gbc_boxes[i].insets = new Insets(0, 0, 0, 0);
			grid.add(boxes[i], gbc_boxes[i]);
			
			if (i < 7){
				xch = 0;
				ych = -1;
				boxes[i].orientation = Orient.CLOCKWISE;
			}
			else if (i < 17){
				xch = 1;
				ych = 0;
				boxes[i].orientation = Orient.TOP;
			}
			else if (i < 24){
				xch = 0;
				ych = 1;
				boxes[i].orientation = Orient.ANTI_CLOCKWISE;
			}
			else{
				xch = -1;
				ych = 0;
				boxes[i].orientation = Orient.BOTTOM;
			}
				
			gbc_boxes[i].gridx = x;
			gbc_boxes[i].gridy = y;
			
			y+=ych;
			x+=xch;
			
			grid.add(boxes[i], gbc_boxes[i]);
			
			boxes[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					int i = ((JSpace)e.getSource()).index;
					if (manager.map[i].type == Types.Country)
						manager.changeCountry(manager.map[i].country);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					manager.hideCountry();
				}
				@Override
				public void mousePressed(MouseEvent e){
					
					int i = ((JSpace)e.getSource()).index;
					manager.sendSpaceClick(i);
//					if (e.getClickCount() == 2 && !e.isConsumed() && Roll.isVisible()) {
//					     e.consume();
//					     manager.players[manager.playerTurn].moveTo(((JSpace)e.getSource()).index);
//					}
				}
			});
		}
	}

	public void loadImages() throws IOException{
		//ImageIO.setUseCache(false);
		
		BufferedImage carImage[] = new BufferedImage[7];
		BufferedImage diceImage[] = new BufferedImage[7];
		BufferedImage assetsImages[] = new BufferedImage[3];
		
		grid.background = ImageIO.read(getClass().getResource("/board.jpg"));
		
		carImage[0] = ImageIO.read(getClass().getResource("/cars/0.png"));
		carImage[1] = ImageIO.read(getClass().getResource("/cars/1.png"));
		carImage[2] = ImageIO.read(getClass().getResource("/cars/2.png"));
		carImage[3] = ImageIO.read(getClass().getResource("/cars/3.png"));
		carImage[4] = ImageIO.read(getClass().getResource("/cars/4.png"));
		carImage[5] = ImageIO.read(getClass().getResource("/cars/5.png"));
		carImage[6] = ImageIO.read(getClass().getResource("/cars/cracks.png"));
		
		diceImage[0] = ImageIO.read(getClass().getResource("/dice/0.png"));
		diceImage[1] = ImageIO.read(getClass().getResource("/dice/1.png"));
		diceImage[2] = ImageIO.read(getClass().getResource("/dice/2.png"));
		diceImage[3] = ImageIO.read(getClass().getResource("/dice/3.png"));
		diceImage[4] = ImageIO.read(getClass().getResource("/dice/4.png"));
		diceImage[5] = ImageIO.read(getClass().getResource("/dice/5.png"));
		diceImage[6] = ImageIO.read(getClass().getResource("/dice/6.png"));
		
		assetsImages[0] = ImageIO.read(getClass().getResource("/assets/garage.png"));
		assetsImages[1] = ImageIO.read(getClass().getResource("/assets/rest.png"));
		assetsImages[2] = ImageIO.read(getClass().getResource("/assets/market.png"));
		
		grid.assetsImages = assetsImages;
		grid.carsImages = carImage;
		grid.boxes = boxes;
		grid.dieImages = diceImage;
	}
	public class JSpace extends JLabel{
		private static final long serialVersionUID = 1L;
		public int index;
		public Orient orientation;
	}
	enum Orient{
		//Order matters, DON'T MODIFY IT
		CLOCKWISE,
		TOP,
		ANTI_CLOCKWISE,
		BOTTOM
	}
	
}
