package main;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	
	private static final long serialVersionUID = 1L;
	
	private JPanel grid;
	private JLabel box0;
	private JLabel box1;
	private JLabel box2;
	private JLabel box3;
	private JLabel box4;
	private JLabel box5;
	private JLabel box6;
	private JLabel box7;
	private JLabel box8;
	private JLabel box9;
	private JLabel box10;
	private JLabel box11;
	private JLabel box12;
	private JLabel box13;
	private JLabel box14;
	private JLabel box15;
	private JLabel box16;
	private JLabel box17;
	private JLabel box18;
	private JLabel box19;
	private JLabel box20;
	private JLabel box21;
	private JLabel box22;
	private JLabel box23;
	private JLabel box24;
	private JLabel box25;
	private JLabel box26;
	private JLabel box27;
	private JLabel box28;
	private JLabel box29;
	private JLabel box30;
	private JLabel box31;
	private JLabel box32;
	private JLabel box33;

	/**
	 * Create the frame.
	 */
	void drawImage(Rectangle bounds, BufferedImage img, Graphics g){
		 g.drawImage(img.getScaledInstance(
					bounds.width, bounds.height
					,Image.SCALE_SMOOTH),bounds.x,bounds.y,null);
		
		//g.drawImage(img,bounds.x,bounds.y,null);
		 
	}
	public void drawBoard(){
		 Graphics g = grid.getGraphics();
		 drawImage(box0.getBounds(),spaceImg[0],g);
		 drawImage(box7.getBounds(),spaceImg[1],g);
		 drawImage(box17.getBounds(),spaceImg[2],g);
		 drawImage(box24.getBounds(),spaceImg[3],g);
		 
		 drawImage(box12.getBounds(),spaceImg[4],g);
		 drawImage(box29.getBounds(),spaceImg[4],g);
		 
		 drawImage(box10.getBounds(),spaceImg[5],g);
		 drawImage(box31.getBounds(),spaceImg[5],g);
		 drawImage(box20.getBounds(),spaceImg[8],g);
		 
		 drawImage(box3.getBounds(),spaceImg[6],g);
		 drawImage(box14.getBounds(),spaceImg[7],g);
		 
		 for(int i = 0; i < 23; ++i)
			 drawImage(countriesBox[i].getBounds(),countryImg[i],g);
         g.dispose();
         
	}
	public GUI() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				drawBoard();
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 432);
		
		grid = new JPanel();
		grid.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(grid);
		GridBagLayout gbl_grid = new GridBagLayout();
		gbl_grid.columnWeights = new double[]{1.0, 1.0, 0.7, 0.7, 0.7, 1.0, 0.7, 0.7, 0.7, 0.7, 1.0};
		gbl_grid.rowWeights = new double[]{1.0, 1.0, 0.7, 0.7, 0.7, 0.7, 0.7, 1.0};
		grid.setLayout(gbl_grid);
		
		panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 6;
		gbc_panel.gridwidth = 9;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		grid.add(panel, gbc_panel);
		

		box0 = new JLabel("");
	
		GridBagConstraints gbc_box0 = new GridBagConstraints();
		gbc_box0.fill = GridBagConstraints.BOTH;
		gbc_box0.insets = new Insets(0, 0, 0, 5);
		gbc_box0.gridx = 0;
		gbc_box0.gridy = 7;
		grid.add(box0, gbc_box0);
		
		box1 = new JLabel("");
		
		GridBagConstraints gbc_box1 = new GridBagConstraints();
		gbc_box1.fill = GridBagConstraints.BOTH;
		gbc_box1.insets = new Insets(0, 0, 5, 5);
		gbc_box1.gridx = 0;
		gbc_box1.gridy = 6;
		grid.add(box1, gbc_box1);
	
		box2 = new JLabel("");	
		GridBagConstraints gbc_box2 = new GridBagConstraints();
		gbc_box2.fill = GridBagConstraints.BOTH;
		gbc_box2.insets = new Insets(0, 0, 5, 5);
		gbc_box2.gridx = 0;
		gbc_box2.gridy = 5;
		grid.add(box2, gbc_box2);
				
		box3 = new JLabel("");
		GridBagConstraints gbc_box3 = new GridBagConstraints();
		gbc_box3.fill = GridBagConstraints.BOTH;
		gbc_box3.insets = new Insets(0, 0, 5, 5);
		gbc_box3.gridx = 0;
		gbc_box3.gridy = 4;
		grid.add(box3, gbc_box3);

		box4 = new JLabel("");
		GridBagConstraints gbc_box4 = new GridBagConstraints();
		gbc_box4.fill = GridBagConstraints.BOTH;
		gbc_box4.insets = new Insets(0, 0, 5, 5);
		gbc_box4.gridx = 0;
		gbc_box4.gridy = 3;
		grid.add(box4, gbc_box4);
		
		box5 = new JLabel("");
		GridBagConstraints gbc_box5 = new GridBagConstraints();
		gbc_box5.insets = new Insets(0, 0, 5, 5);
		gbc_box5.fill = GridBagConstraints.BOTH;
		gbc_box5.gridx = 0;
		gbc_box5.gridy = 2;
		grid.add(box5, gbc_box5);
		
		box6 = new JLabel("");
		GridBagConstraints gbc_box6 = new GridBagConstraints();
		gbc_box6.fill = GridBagConstraints.BOTH;
		gbc_box6.insets = new Insets(0, 0, 5, 5);
		gbc_box6.gridx = 0;
		gbc_box6.gridy = 1;
		grid.add(box6, gbc_box6);
	
		box7 = new JLabel("");	
		GridBagConstraints gbc_box7 = new GridBagConstraints();
		gbc_box7.fill = GridBagConstraints.BOTH;
		gbc_box7.insets = new Insets(0, 0, 5, 5);
		gbc_box7.gridx = 0;
		gbc_box7.gridy = 0;
		grid.add(box7, gbc_box7);
		
		box8 = new JLabel("");
		
		GridBagConstraints gbc_box8 = new GridBagConstraints();
		gbc_box8.fill = GridBagConstraints.BOTH;
		gbc_box8.insets = new Insets(0, 0, 5, 5);
		gbc_box8.gridx = 1;
		gbc_box8.gridy = 0;
		grid.add(box8, gbc_box8);
		
		box9 = new JLabel("");
		
		GridBagConstraints gbc_box9 = new GridBagConstraints();
		gbc_box9.fill = GridBagConstraints.BOTH;
		gbc_box9.insets = new Insets(0, 0, 5, 5);
		gbc_box9.gridx = 2;
		gbc_box9.gridy = 0;
		grid.add(box9, gbc_box9);
		
		box10 = new JLabel("");
		GridBagConstraints gbc_box10 = new GridBagConstraints();
		gbc_box10.fill = GridBagConstraints.BOTH;
		gbc_box10.insets = new Insets(0, 0, 5, 5);
		gbc_box10.gridx = 3;
		gbc_box10.gridy = 0;
		grid.add(box10, gbc_box10);
		
		box11 = new JLabel("");
		GridBagConstraints gbc_box11 = new GridBagConstraints();
		gbc_box11.fill = GridBagConstraints.BOTH;
		gbc_box11.insets = new Insets(0, 0, 5, 5);
		gbc_box11.gridx = 4;
		gbc_box11.gridy = 0;
		grid.add(box11, gbc_box11);
		
		box12 = new JLabel("");
		GridBagConstraints gbc_box12 = new GridBagConstraints();
		gbc_box12.fill = GridBagConstraints.BOTH;
		gbc_box12.insets = new Insets(0, 0, 5, 5);
		gbc_box12.gridx = 5;
		gbc_box12.gridy = 0;
		grid.add(box12, gbc_box12);
		
		box13 = new JLabel("");
		GridBagConstraints gbc_box13 = new GridBagConstraints();
		gbc_box13.fill = GridBagConstraints.BOTH;
		gbc_box13.insets = new Insets(0, 0, 5, 5);
		gbc_box13.gridx = 6;
		gbc_box13.gridy = 0;
		grid.add(box13, gbc_box13);
		
		box14 = new JLabel("");
		GridBagConstraints gbc_box14 = new GridBagConstraints();
		gbc_box14.fill = GridBagConstraints.BOTH;
		gbc_box14.insets = new Insets(0, 0, 5, 5);
		gbc_box14.gridx = 7;
		gbc_box14.gridy = 0;
		grid.add(box14, gbc_box14);
		
		box15 = new JLabel("");
		GridBagConstraints gbc_box15 = new GridBagConstraints();
		gbc_box15.fill = GridBagConstraints.BOTH;
		gbc_box15.insets = new Insets(0, 0, 5, 5);
		gbc_box15.gridx = 8;
		gbc_box15.gridy = 0;
		grid.add(box15, gbc_box15);
		
		box16 = new JLabel("");
		GridBagConstraints gbc_box16 = new GridBagConstraints();
		gbc_box16.fill = GridBagConstraints.BOTH;
		gbc_box16.insets = new Insets(0, 0, 5, 5);
		gbc_box16.gridx = 9;
		gbc_box16.gridy = 0;
		grid.add(box16, gbc_box16);
		
		box17 = new JLabel("");
		GridBagConstraints gbc_box17 = new GridBagConstraints();
		gbc_box17.fill = GridBagConstraints.BOTH;
		gbc_box17.insets = new Insets(0, 0, 5, 0);
		gbc_box17.gridx = 10;
		gbc_box17.gridy = 0;
		grid.add(box17, gbc_box17);
		
		box18 = new JLabel("");
		GridBagConstraints gbc_box18 = new GridBagConstraints();
		gbc_box18.fill = GridBagConstraints.BOTH;
		gbc_box18.insets = new Insets(0, 0, 5, 0);
		gbc_box18.gridx = 10;
		gbc_box18.gridy = 1;
		grid.add(box18, gbc_box18);
				
		box19 = new JLabel("");
		GridBagConstraints gbc_box19 = new GridBagConstraints();
		gbc_box19.fill = GridBagConstraints.BOTH;
		gbc_box19.insets = new Insets(0, 0, 5, 0);
		gbc_box19.gridx = 10;
		gbc_box19.gridy = 2;
		grid.add(box19, gbc_box19);
		
		box20 = new JLabel("");
		GridBagConstraints gbc_box20 = new GridBagConstraints();
		gbc_box20.fill = GridBagConstraints.BOTH;
		gbc_box20.insets = new Insets(0, 0, 5, 0);
		gbc_box20.gridx = 10;
		gbc_box20.gridy = 3;
		grid.add(box20, gbc_box20);
		
		box21 = new JLabel("");
		GridBagConstraints gbc_box21 = new GridBagConstraints();
		gbc_box21.fill = GridBagConstraints.BOTH;
		gbc_box21.insets = new Insets(0, 0, 5, 0);
		gbc_box21.gridx = 10;
		gbc_box21.gridy = 4;
		grid.add(box21, gbc_box21);

		box22 = new JLabel("");		
		GridBagConstraints gbc_box22 = new GridBagConstraints();
		gbc_box22.fill = GridBagConstraints.BOTH;
		gbc_box22.insets = new Insets(0, 0, 5, 0);
		gbc_box22.gridx = 10;
		gbc_box22.gridy = 5;
		grid.add(box22, gbc_box22);

		box23 = new JLabel("");
		GridBagConstraints gbc_box23 = new GridBagConstraints();
		gbc_box23.fill = GridBagConstraints.BOTH;
		gbc_box23.insets = new Insets(0, 0, 5, 0);
		gbc_box23.gridx = 10;
		gbc_box23.gridy = 6;
		grid.add(box23, gbc_box23);
		
		box24 = new JLabel("");
		GridBagConstraints gbc_box24 = new GridBagConstraints();
		gbc_box24.fill = GridBagConstraints.BOTH;
		gbc_box24.insets = new Insets(0, 0, 0, 0);
		gbc_box24.gridx = 10;
		gbc_box24.gridy = 7;
		grid.add(box24, gbc_box24);
		
		box25 = new JLabel("");
		GridBagConstraints gbc_box25 = new GridBagConstraints();
		gbc_box25.fill = GridBagConstraints.BOTH;
		gbc_box25.insets = new Insets(0, 0, 0, 5);
		gbc_box25.gridx = 9;
		gbc_box25.gridy = 7;
		grid.add(box25, gbc_box25);

		box26 = new JLabel("");
		GridBagConstraints gbc_box26 = new GridBagConstraints();
		gbc_box26.fill = GridBagConstraints.BOTH;
		gbc_box26.insets = new Insets(0, 0, 0, 5);
		gbc_box26.gridx = 8;
		gbc_box26.gridy = 7;
		grid.add(box26, gbc_box26);
		
		box27 = new JLabel("");
		GridBagConstraints gbc_box27 = new GridBagConstraints();
		gbc_box27.fill = GridBagConstraints.BOTH;
		gbc_box27.insets = new Insets(0, 0, 0, 5);
		gbc_box27.gridx = 7;
		gbc_box27.gridy = 7;
		grid.add(box27, gbc_box27);
		
		box28 = new JLabel("");
		GridBagConstraints gbc_box28 = new GridBagConstraints();
		gbc_box28.fill = GridBagConstraints.BOTH;
		gbc_box28.insets = new Insets(0, 0, 0, 5);
		gbc_box28.gridx = 6;
		gbc_box28.gridy = 7;
		grid.add(box28, gbc_box28);
		
		box29 = new JLabel("");
		GridBagConstraints gbc_box29 = new GridBagConstraints();
		gbc_box29.fill = GridBagConstraints.BOTH;
		gbc_box29.insets = new Insets(0, 0, 0, 5);
		gbc_box29.gridx = 5;
		gbc_box29.gridy = 7;
		grid.add(box29, gbc_box29);
		
		box30 = new JLabel("");
		GridBagConstraints gbc_box30 = new GridBagConstraints();
		gbc_box30.fill = GridBagConstraints.BOTH;
		gbc_box30.insets = new Insets(0, 0, 0, 5);
		gbc_box30.gridx = 4;
		gbc_box30.gridy = 7;
		grid.add(box30, gbc_box30);
		
		box31 = new JLabel("");
		GridBagConstraints gbc_box31 = new GridBagConstraints();
		gbc_box31.fill = GridBagConstraints.BOTH;
		gbc_box31.insets = new Insets(0, 0, 0, 5);
		gbc_box31.gridx = 3;
		gbc_box31.gridy = 7;
		grid.add(box31, gbc_box31);
		
		box32 = new JLabel("");
		GridBagConstraints gbc_box32 = new GridBagConstraints();
		gbc_box32.fill = GridBagConstraints.BOTH;
		gbc_box32.insets = new Insets(0, 0, 0, 5);
		gbc_box32.gridx = 2;
		gbc_box32.gridy = 7;
		grid.add(box32, gbc_box32);
		
		box33 = new JLabel("");
		GridBagConstraints gbc_box33 = new GridBagConstraints();
		gbc_box33.fill = GridBagConstraints.BOTH;
		gbc_box33.insets = new Insets(0, 0, 0, 5);
		gbc_box33.gridx = 1;
		gbc_box33.gridy = 7;
		grid.add(box33, gbc_box33);
		
		ImageList();
		int i = 0;
		countriesBox[i++] = box1;
		countriesBox[i++] = box2;
		
		countriesBox[i++] = box4;
		countriesBox[i++] = box5;
		countriesBox[i++] = box6;
		
		countriesBox[i++] = box8;
		countriesBox[i++] = box9;
		countriesBox[i++] = box11;
		
		countriesBox[i++] = box13;
		countriesBox[i++] = box15;
		countriesBox[i++] = box16;
		
		countriesBox[i++] = box18;
		countriesBox[i++] = box19;
		
		countriesBox[i++] = box21;
		countriesBox[i++] = box22;
		countriesBox[i++] = box23;
		
		countriesBox[i++] = box25;
		countriesBox[i++] = box26;
		countriesBox[i++] = box27;
		countriesBox[i++] = box28;
		
		countriesBox[i++] = box30;
		countriesBox[i++] = box32;
		countriesBox[i++] = box33;
		
		//setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
	}
	public JLabel countriesBox[] = new JLabel[Manager.nCountry]; 
	public final String imagePath = "images\\spaces\\";
	public BufferedImage countryImg[] = new BufferedImage[Manager.nCountry];
	public BufferedImage spaceImg[] = new BufferedImage[9];
	private JPanel panel;
	public void ImageList(){
		try {
			
			for(int i = 1; i < Manager.nCountry; ++i)
				countryImg[i-1] = ImageIO.read(new File(imagePath.concat(Integer.toString(i)).concat(".jpg")));
			int i = 0;
			spaceImg[i++] = ImageIO.read(new File(imagePath.concat("start.jpg")));
			spaceImg[i++] = ImageIO.read(new File(imagePath.concat("club.jpg")));
			spaceImg[i++] = ImageIO.read(new File(imagePath.concat("train.jpg")));
			spaceImg[i++] = ImageIO.read(new File(imagePath.concat("jail.jpg")));
			spaceImg[i++] = ImageIO.read(new File(imagePath.concat("lucky.jpg")));
			spaceImg[i++] = ImageIO.read(new File(imagePath.concat("judgement.jpg")));
			spaceImg[i++] = ImageIO.read(new File(imagePath.concat("luckyjudge.jpg")));
			spaceImg[i++] = ImageIO.read(new File(imagePath.concat("gas station.jpg")));
			spaceImg[i++] = ImageIO.read(new File(imagePath.concat("judgement270.jpg")));
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}
}
