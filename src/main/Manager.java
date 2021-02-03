package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Board.ButtonAction;
import main.Country.Building;

public class Manager {
	public static final int STATION_PRICE = 300;
	public static final int STAION_FARE = 40;
	public static final int CLUB_PRICE = 150;
	public static final int CLUB_FARE = 30;
	public static final int START_PAYMENT = 350;
	public  static final int JAIL_BRIBE = 50;
	
	public static final Color COLORS[] = {
			new Color(229,229,229),
			new Color(59,253,154),
			new Color(253,67,59),
			new Color(128,70,209),
			new Color(59,101,253),
			new Color(253,210,59)
			};
	public static final int mapSize = 34;
	public static final int nCountry = 23;
	public static enum ClickAction{
		NONE,
		MOVETO,
		BUYFREE,
		TRADE, TRADEWITH,
		BUILD
	}

	boolean isLoading = true;
	boolean luckyJudgement = false;
	public final Space[] map = new Space[mapSize];
	public final Country[] countries = new Country[nCountry];
	public Player[] players = new Player[6];
	public int nPlayers = 0;
	public Board frame;
	public DieRoller dieRoller;
	public int playerTurn = 0;
	public Lucky lucky;
	public Judgement judgement;
	
	private ClickAction countryAction = ClickAction.NONE;
	public final ClickAction getCountryAction() {
		return countryAction;
	}
	private int choices[] = null;
	public final int[] getChoices() {
		return choices;
	}
	public void setCountryAction(int choices[],ClickAction clickAction) {
		this.choices = choices;
		this.countryAction = clickAction;

		frame.repaint();
	}
	public void resetCountryAction(){
		this.choices = null;
		this.countryAction = ClickAction.NONE;
		frame.repaint();
	}
	public Player stationOwner = null;
	public Player clubMember1 = null;
	public Player clubMember2 = null;
	
	public Country co1 = null;
	public Country co2 = null;
	
	public Manager() {
		CountryList();
		MapList();
		lucky = new Lucky(this);
		judgement = new Judgement(this);
	}
	public void setReadyToRoll(boolean value){
		frame.Roll.setVisible(value);
		frame.Manage.setVisible(value);
		frame.Manage.setText("الممتلكات");
		frame.repaint();
		frame.setRollManage();
	}
	public void nextPlayer() {
		if (luckyJudgement){
			luckyJudgement = false;
			judgement.next(players[playerTurn]);
			return;
		}
		setReadyToRoll(true);
		
		if (!isLoading) {
			players[playerTurn].endTurn();
			playerTurn = (playerTurn + 1) % nPlayers;
			players[playerTurn].startTurn();
		}
	}
	
	public void CountryList() {
		countries[0] = new Country("القدس", 300, 35, 190, 900, 1550, 250, 750, 1000, 3, Color.CYAN);
		countries[1] = new Country("غزة", 250, 30, 130, 650, 910, 200, 600, 800, 3, Color.CYAN);
		countries[2] = new Country("بيروت", 300, 32, 180, 850, 1500, 200, 750, 1000, 2, Color.GREEN);
		countries[3] = new Country("الرياض", 250, 29, 130, 650, 600, 200, 600, 800, 2, Color.GREEN);
		countries[4] = new Country("بغداد", 250, 28, 120, 600, 850, 200, 600, 800, 2, Color.GREEN);
		countries[5] = new Country("بني غازي", 150, 22, 75, 275, 650, 130, 440, 550, 2, Color.ORANGE);
		countries[6] = new Country("عدن", 100, 17, 72, 380, 600, 100, 310, 450, 2, Color.ORANGE);
		countries[7] = new Country("البحرين", 90, 15, 60, 300, 480, 80, 250, 350, 2, Color.ORANGE);
		countries[8] = new Country("الدار البيضاء", 250, 30, 130, 600, 850, 200, 550, 750, 2, Color.BLUE);
		countries[9] = new Country("تونس", 200, 25, 120, 600, 800, 165, 360, 490, 2, Color.BLUE);
		countries[10] = new Country("الجزائر", 300, 35, 185, 850, 1000, 520, 750, 1000, 2, Color.BLUE);
		countries[11] = new Country("الاسكندرية", 325, 45, 220, 1000, 1700, 300, 850, 1200, 3, Color.LIGHT_GRAY);
		countries[12] = new Country("حلب", 300, 35, 185, 850, 1500, 250, 750, 1000, 3, Color.LIGHT_GRAY);
		countries[13] = new Country("أسوان", 200, 27, 130, 700, 900, 165, 360, 490, 2, Color.MAGENTA);
		countries[14] = new Country("دمشق", 250, 50, 250, 1200, 1850, 320, 900, 1250, 2, Color.MAGENTA);
		countries[15] = new Country("القاهرة", 450, 55, 320, 1500, 2400, 400, 1200, 1600, 2, Color.MAGENTA);
		countries[16] = new Country("الخرطوم", 200, 27, 130, 630, 850, 170, 370, 500, 2, Color.YELLOW);
		countries[17] = new Country("عمان", 250, 30, 130, 600, 850, 200, 550, 750, 2, Color.YELLOW);
		countries[18] = new Country("الأقصر", 200, 30, 75, 100, 150, 250, 550, 750, 6, Color.WHITE);
		countries[19] = new Country("بور سعيد", 250, 30, 140, 700, 900, 210, 550, 800, 2, Color.YELLOW);
		countries[20] = new Country("صنعاء", 250, 28, 125, 600, 900, 200, 550, 750, 2, Color.PINK);
		countries[21] = new Country("الكويت", 250, 30, 125, 620, 925, 220, 600, 820, 2, Color.PINK);
		countries[22] = new Country("قطر", 150, 20, 75, 370, 600, 120, 260, 480, 2, Color.PINK);
	}

	public void MapList() {
		int curr = 0;
		map[0] = new Space(Types.Start);
		map[1] = new Space(countries[curr++],1);
		map[2] = new Space(countries[curr++],2);
		map[3] = new Space(Types.LuckyJudgement);
		map[4] = new Space(countries[curr++],4);
		map[5] = new Space(countries[curr++],5);
		map[6] = new Space(countries[curr++],6);
		map[7] = new Space(Types.LuckClub);
		map[8] = new Space(countries[curr++],8);
		map[9] = new Space(countries[curr++],9);
		map[10] = new Space(Types.Judgement);
		map[11] = new Space(countries[curr++],11);
		map[12] = new Space(Types.Lucky);
		map[13] = new Space(countries[curr++],13);
		map[14] = new Space(Types.Station);
		map[15] = new Space(countries[curr++],15);
		map[16] = new Space(countries[curr++],16);
		map[17] = new Space(Types.QuickBus);
		map[18] = new Space(countries[curr++],18);
		map[19] = new Space(countries[curr++],19);
		map[20] = new Space(Types.Judgement);
		map[21] = new Space(countries[curr++],21);
		map[22] = new Space(countries[curr++],22);
		map[23] = new Space(countries[curr++],23);
		map[24] = new Space(Types.Jail);
		map[25] = new Space(countries[curr++],25);
		map[26] = new Space(countries[curr++],26);
		map[27] = new Space(countries[curr++],27);
		map[28] = new Space(countries[curr++],28);
		map[29] = new Space(Types.Lucky);
		map[30] = new Space(countries[curr++],30);
		map[31] = new Space(Types.Judgement);
		map[32] = new Space(countries[curr++],32);
		map[33] = new Space(countries[curr++],33);
	}


	public void sendSpaceClick(int click) {
		Player pl = players[playerTurn];
		
		if (getChoices() == null)
			return;
		boolean succ = false;
		for (int i = 0; i < getChoices().length; i++) {
			if (getChoices()[i] == click) {
				succ = true;
				break;
			}
		}
		if (!succ)
			return;
		ClickAction currentAction = getCountryAction();
		resetCountryAction();

		if (currentAction == ClickAction.MOVETO){
			pl.moveTo(click);
		}
		else if (currentAction == ClickAction.BUYFREE){
			if (click == 0){
				pl.changeMoney(200);
				
			}
			else{
				map[click].country.owner = pl;
				pl.countries.add(map[click].country);
			}
			frame.grid.repaint();
			nextPlayer();
			
		}
		else if (currentAction== ClickAction.TRADE){
			co1 = map[click].country;
			if (!canSellCountry(co1)){
				Message.show(frame,"لا يمكنك مقايضة بلد في مجموعة عليها منشئات", "بنك الحظ");

				frame.Manage.getActionListeners()[0].actionPerformed(null);
				frame.repaint();
			}
			else{
				
				
				ArrayList<Country> tradewiths = new ArrayList<Country>();
				for(Country co : countries){
					if (co.owner != pl && co.owner != null)
						tradewiths.add(co);
				}
				int n = tradewiths.size();
				int choices[] = new int[n];
				for(int i = 0; i < n; i++){
					choices[i] = tradewiths.get(i).indexOnMap;
				}
				setCountryAction(choices,ClickAction.TRADEWITH);
				return;
			}
		}
		else if (currentAction == ClickAction.TRADEWITH){
			co2 = map[click].country;
			if (!canSellCountry(co2)){
				Message.show(frame,"لا يمكنك المقايضة مع بلد في مجموعة عليها منشئات", "بنك الحظ");
				frame.Manage.getActionListeners()[0].actionPerformed(null);
				frame.repaint();
			}
			
			else{				
				frame.setButtonAction(ButtonAction.CONFIRM_TRADE);
			}
		}
		else if (currentAction == ClickAction.BUILD){
			
			Building res = Message.build(this, "عايز تبني ايه؟", "بنك الحظ");
			if (res == Building.NOT_CHANGED){
			}
			else if (res != Building.NONE){
				int mon = map[click].country.getUnitPrice(res);
				mon -= map[click].country.getUnitPrice();
				if (pl.money >= mon){
					pl.changeMoney(-mon);
					map[click].country.establishment = res;
				}
				else
					Message.show(frame,"لا تملك ما يكفي لشراء ما تريد", "بنك الفقر");
			}
			else{
				int mon = map[click].country.getUnitPrice();
				if (mon != 0){
					pl.changeMoney(mon);
					map[click].country.establishment = Building.NONE;
				}
			}

			frame.Manage.getActionListeners()[0].actionPerformed(null);
			frame.repaint();
		}
		// return choices[0];
	}
	public void getRoll(int pl) {
		setReadyToRoll(false);
		
		dieRoller = new DieRoller(this, pl);
		dieRoller.execute();
	}

	public void changeDie(int die1, int die2) {
		if (die1 >= 0 && die1 <= 6 && die2 >= 0 && die2 <= 6) {
			frame.grid.die1 = die1;
			frame.grid.die2 = die2;
			frame.grid.repaint();
		}
	}

	public void hideDie() {
		frame.grid.die1 = -1;
		frame.grid.die2 = -1;
		frame.grid.repaint();
	}

	public void changeCountry(Country c) {
		frame.grid.country = c;
		frame.grid.repaint();
	}

	public void hideCountry() {
		frame.grid.country = null;
		frame.grid.repaint();
	}
	
	public boolean canSellCountry(Country country){
		Color curr = country.groupColor;
		boolean ret = true;
		for(Country co : this.countries){
			if (co.groupColor == curr && co.establishment != Building.NONE){
				ret = false;
				break;
			}
		}
		return ret;
	}

	public static BufferedImage dye(BufferedImage image, Color color)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
        g.dispose();
        return dyed;
    }
	public void swapCountries() {
		for(int i = 0; i < co1.owner.countries.size(); ++i){
			if (co1.owner.countries.get(i) == co1){
				co1.owner.countries.remove(i);
				break;
			}
		}
		co1.owner.countries.add(co2);
		for(int i = 0; i < co2.owner.countries.size(); ++i){
			if (co2.owner.countries.get(i) == co2){
				co2.owner.countries.remove(i);
				break;
			}
		}
		co2.owner.countries.add(co1);
		
		Player tmp = co1.owner;
		co1.owner = co2.owner;
		co2.owner = tmp;
	}
	public void sellUntilSatisfied() {
		// TODO Auto-generated method stub
		
	}
	
	
}
