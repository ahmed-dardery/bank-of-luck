package main;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private JTextField txt5;
	private JTextField txt6;
	private JButton btnNewButton;
	boolean pressed = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{50, 31, 249, 0};
		gbl_contentPane.rowHeights = new int[]{14, 20, 20, 20, 20, 20, 20, 31, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblEnterTheNames = new JLabel("Please enter the names of the players:");
		GridBagConstraints gbc_lblEnterTheNames = new GridBagConstraints();
		gbc_lblEnterTheNames.anchor = GridBagConstraints.NORTH;
		gbc_lblEnterTheNames.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEnterTheNames.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterTheNames.gridwidth = 3;
		gbc_lblEnterTheNames.gridx = 0;
		gbc_lblEnterTheNames.gridy = 0;
		contentPane.add(lblEnterTheNames, gbc_lblEnterTheNames);
		
		JLabel lblPlayer = new JLabel("Player 1:");
		lblPlayer.setOpaque(true);
		lblPlayer.setBackground(Color.GRAY);
		lblPlayer.setForeground(Manager.COLORS[0]);
		GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
		gbc_lblPlayer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayer.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer.gridx = 0;
		gbc_lblPlayer.gridy = 1;
		contentPane.add(lblPlayer, gbc_lblPlayer);
		
		txt1 = new JTextField();
		//txt1.setText("a");
		GridBagConstraints gbc_txt1 = new GridBagConstraints();
		gbc_txt1.anchor = GridBagConstraints.NORTH;
		gbc_txt1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt1.insets = new Insets(0, 0, 5, 0);
		gbc_txt1.gridx = 2;
		gbc_txt1.gridy = 1;
		contentPane.add(txt1, gbc_txt1);
		txt1.setColumns(10);
		
		JLabel lblPlayer_1 = new JLabel("Player 2:");
		lblPlayer_1.setOpaque(true);
		lblPlayer_1.setBackground(Color.GRAY);
		lblPlayer_1.setForeground(Manager.COLORS[1]);
		GridBagConstraints gbc_lblPlayer_1 = new GridBagConstraints();
		gbc_lblPlayer_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayer_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer_1.gridx = 0;
		gbc_lblPlayer_1.gridy = 2;
		contentPane.add(lblPlayer_1, gbc_lblPlayer_1);
		
		txt2 = new JTextField();
		//txt2.setText("b");
		GridBagConstraints gbc_txt2 = new GridBagConstraints();
		gbc_txt2.anchor = GridBagConstraints.NORTH;
		gbc_txt2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt2.insets = new Insets(0, 0, 5, 0);
		gbc_txt2.gridx = 2;
		gbc_txt2.gridy = 2;
		contentPane.add(txt2, gbc_txt2);
		txt2.setColumns(10);
		
		JLabel lblPlayer_2 = new JLabel("Player 3:");
		lblPlayer_2.setOpaque(true);
		lblPlayer_2.setBackground(Color.GRAY);
		lblPlayer_2.setForeground(Manager.COLORS[2]);
		GridBagConstraints gbc_lblPlayer_2 = new GridBagConstraints();
		gbc_lblPlayer_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayer_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer_2.gridx = 0;
		gbc_lblPlayer_2.gridy = 3;
		contentPane.add(lblPlayer_2, gbc_lblPlayer_2);
		
		txt3 = new JTextField();
		//txt3.setText("c");
		GridBagConstraints gbc_txt3 = new GridBagConstraints();
		gbc_txt3.anchor = GridBagConstraints.NORTH;
		gbc_txt3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt3.insets = new Insets(0, 0, 5, 0);
		gbc_txt3.gridx = 2;
		gbc_txt3.gridy = 3;
		contentPane.add(txt3, gbc_txt3);
		txt3.setColumns(10);
		
		JLabel lblPlayer_3 = new JLabel("Player 4:");
		lblPlayer_3.setOpaque(true);
		lblPlayer_3.setBackground(Color.GRAY);
		lblPlayer_3.setForeground(Manager.COLORS[3]);
		GridBagConstraints gbc_lblPlayer_3 = new GridBagConstraints();
		gbc_lblPlayer_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayer_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer_3.gridx = 0;
		gbc_lblPlayer_3.gridy = 4;
		contentPane.add(lblPlayer_3, gbc_lblPlayer_3);
		
		txt4 = new JTextField();
		//txt4.setText("d");
		GridBagConstraints gbc_txt4 = new GridBagConstraints();
		gbc_txt4.anchor = GridBagConstraints.NORTH;
		gbc_txt4.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt4.insets = new Insets(0, 0, 5, 0);
		gbc_txt4.gridx = 2;
		gbc_txt4.gridy = 4;
		contentPane.add(txt4, gbc_txt4);
		txt4.setColumns(10);
		
		JLabel lblPlayer_4 = new JLabel("Player 5:");
		lblPlayer_4.setOpaque(true);
		lblPlayer_4.setBackground(Color.GRAY);
		lblPlayer_4.setForeground(Manager.COLORS[4]);
		GridBagConstraints gbc_lblPlayer_4 = new GridBagConstraints();
		gbc_lblPlayer_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayer_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer_4.gridx = 0;
		gbc_lblPlayer_4.gridy = 5;
		contentPane.add(lblPlayer_4, gbc_lblPlayer_4);
		
		txt5 = new JTextField();
		//txt5.setText("e");
		GridBagConstraints gbc_txt5 = new GridBagConstraints();
		gbc_txt5.anchor = GridBagConstraints.NORTH;
		gbc_txt5.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt5.insets = new Insets(0, 0, 5, 0);
		gbc_txt5.gridx = 2;
		gbc_txt5.gridy = 5;
		contentPane.add(txt5, gbc_txt5);
		txt5.setColumns(10);
		
		JLabel lblPlayer_5 = new JLabel("Player 6:");
		lblPlayer_5.setOpaque(true);
		lblPlayer_5.setBackground(Color.GRAY);
		lblPlayer_5.setForeground(Manager.COLORS[5]);
		GridBagConstraints gbc_lblPlayer_5 = new GridBagConstraints();
		gbc_lblPlayer_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayer_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer_5.gridx = 0;
		gbc_lblPlayer_5.gridy = 6;
		contentPane.add(lblPlayer_5, gbc_lblPlayer_5);
		
		txt6 = new JTextField();
		//txt6.setText("f");
		GridBagConstraints gbc_txt6 = new GridBagConstraints();
		gbc_txt6.anchor = GridBagConstraints.NORTH;
		gbc_txt6.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt6.insets = new Insets(0, 0, 5, 0);
		gbc_txt6.gridx = 2;
		gbc_txt6.gridy = 6;
		contentPane.add(txt6, gbc_txt6);
		txt6.setColumns(10);
		
		btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pressed) return;
				pressed = true;
				JTextField txts[] = {txt1,txt2,txt3,txt4,txt5,txt6};
				
				Color colors[] = new Color[6];
				String names[] = new String[6];
				int nPlayers = 0;
				String curr;
				for(int i = 0; i < 6; i++){
					curr = txts[i].getText();
					if (curr.isEmpty()) continue;
					colors[nPlayers] = Manager.COLORS[i];
					names[nPlayers++] = curr;
				}
				
				if (nPlayers == 1){
					Message.show(null,"مبروك يسطا كسبت نفسك!", "مبروك");
					pressed = false;
					return;
				}
				if (nPlayers == 0){
					Message.show(null,"هلعب مع نفسي يعني؟", "ايه ياهبل");
					pressed = false;
					return;
				}
				try {
					setVisible(false);
					Manager manager = new Manager();
					manager.nPlayers = nPlayers;
					
					for(int i = 0; i < nPlayers; i++){
						manager.players[i] = new Player(names[i],colors[i],manager);
					}
					
					Board nframe = new Board(manager);
					manager.frame = nframe;
					
					
					
					
					nframe.setVisible(true);
					manager.isLoading = false;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		final int charLim = 10;
		((AbstractDocument)txt1.getDocument()).setDocumentFilter(new LimitDocumentFilter(charLim));
		((AbstractDocument)txt2.getDocument()).setDocumentFilter(new LimitDocumentFilter(charLim));
		((AbstractDocument)txt3.getDocument()).setDocumentFilter(new LimitDocumentFilter(charLim));
		((AbstractDocument)txt4.getDocument()).setDocumentFilter(new LimitDocumentFilter(charLim));
		((AbstractDocument)txt5.getDocument()).setDocumentFilter(new LimitDocumentFilter(charLim));
		((AbstractDocument)txt6.getDocument()).setDocumentFilter(new LimitDocumentFilter(charLim));
		

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 8;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}
	 public class LimitDocumentFilter extends DocumentFilter {

	        private int limit;

	        public LimitDocumentFilter(int limit) {
	            if (limit <= 0) {
	                throw new IllegalArgumentException("Limit can not be <= 0");
	            }
	            this.limit = limit;
	        }

	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	            int currentLength = fb.getDocument().getLength();
	            int overLimit = (currentLength + text.length()) - limit - length;
	            if (overLimit > 0) {
	                text = text.substring(0, text.length() - overLimit);
	            }
	            if (text.length() > 0) {
	                super.replace(fb, offset, length, text, attrs); 
	            }
	        }

	    }
}
