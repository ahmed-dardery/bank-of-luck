package main;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import main.Board.ButtonAction;

public class Player implements Comparable<Player> {
	static public int INDEX = 0;
	public int index;
	public String name;
	public ArrayList<Country> countries;
	public int nCountries = 0;
	public int money = 1200;
	public int priorMoney = 1200;
	public int out = 0;
	public int position = 0;
	public Color color;
	public boolean quickBus;
	public boolean unavailable = false;
	public boolean jailed = false;
	public boolean safeFromJail = false;
	public Manager manager;
	public boolean firstMove = true;
	public boolean nextTurnDiscount = false;
	public double discount = 0;
	public int moneyChange = 0;

	public Player(String name, Color color, Manager manager) {
		index = INDEX++;
		position = -1;// index * 3;
		countries = new ArrayList<Country>();
		this.name = name;
		this.color = color;
		this.manager = manager;
		// performSpace();
	}
	public void moveBy(int pos) {
		moveBy(pos,false);
	}
	public void moveBy(int pos, boolean same) {
		if (jailed){
			if (same){
				Message.show(manager.frame,"مبروك تقدر تخرج من السجن", "بنك الحظ");
				manager.setReadyToRoll(true);
			}
			else if (this.money >= Manager.JAIL_BRIBE){
				manager.frame.setButtonAction(ButtonAction.BRIBE_JAIL);
			}
			jailed = false;
			return;
		}
		if (pos == 0 && position == -1)
			pos = 1;
		
		if (quickBus)
			pos *= 2;

		OneByOne tmp = new OneByOne(this, pos, quickBus);
		tmp.execute();
		quickBus = false;
	}

	public class OneByOne extends SwingWorker<Integer, Integer> {
		int left;
		Player pl;
		int waiting;

		public OneByOne(Player pl, int n, boolean quick) {
			left = n;
			this.pl = pl;
			waiting = quick ? 100 : 200;
		}

		@Override
		protected Integer doInBackground() throws Exception {
			while (left > 0) {
				left--;
				pl.position = (pl.position + 1) % Manager.mapSize;
				manager.frame.grid.repaint();
				if (pl.position == 0 && !firstMove)
					pl.wentToStart();

				Thread.sleep(waiting);
			}
			while (left < 0) {
				left++;
				pl.position = (pl.position - 1 + Manager.mapSize) % Manager.mapSize;
				manager.frame.grid.repaint();

				Thread.sleep(waiting);
			}

			return null;
		}

		@Override
		protected void done() {
			firstMove = false;
			performSpace();

		}

	}

	public void performSpace() {
		if (manager.map[position].type == Types.QuickBus) {
			quickBus = true;
		} 
		else if (manager.map[position].type == Types.Country) {
			Country co = manager.map[position].country;
			if (co.owner == null && co.price <= money) {
				manager.frame.setButtonAction(ButtonAction.BUY_COUNTRY);
				return;
			} else if (co.owner == null) {
				Message.show(manager.frame,"لا تملك ما يكفي لشراء هذه الدولة","بنك الفقر");
			} else if (co.owner != this) {
				int ch = co.getRate();
				changeMoney(-ch);
				co.owner.changeMoney(ch);
			}
		}
		else if (manager.map[position].type == Types.Jail) {
			if (safeFromJail) 
				safeFromJail = false;
			else 
				jailed = true;
		}
		else if (manager.map[position].type == Types.Lucky) {
			manager.lucky.next(this);
			return;
		} 
		else if (manager.map[position].type == Types.Judgement) {
			manager.judgement.next(this);
			return;
		}
		else if (manager.map[position].type == Types.LuckyJudgement) {
			manager.luckyJudgement = true;
			manager.lucky.next(this);
			
			return;
		}
		else if (manager.map[position].type == Types.Station){
			if (manager.stationOwner == null && this.money >= Manager.STATION_PRICE){
				manager.frame.setButtonAction(ButtonAction.BUY_STATION);
				return;
			}
			else if (manager.stationOwner != this){
				this.changeMoney(-Manager.STAION_FARE);
				manager.stationOwner.changeMoney(Manager.STAION_FARE);
			}
		}
		else if (manager.map[position].type == Types.LuckClub){
			if (manager.clubMember1 != this && manager.clubMember2 != this){				
				if (manager.clubMember1 == null || manager.clubMember2 == null && this.money >= Manager.CLUB_PRICE){
					manager.frame.setButtonAction(ButtonAction.REGISTER_CLUB);
					return;
				}
				else{
					this.changeMoney(-Manager.CLUB_FARE);
				}
			}
		}
		manager.nextPlayer();
		manager.frame.grid.repaint();

	}

	public void moveTo(int spaceInd) {
		this.position = spaceInd;
		performSpace();
		manager.frame.grid.repaint();

	}

	public void moveTo(Country country) {
		for (int i = 0; i < Manager.mapSize; ++i) {
			if (manager.map[i].type == Types.Country && manager.map[i].country.equals(country)) {
				moveTo(i);
				break;
			}
		}
	}

	public void wentToStart() {
		int mon = Manager.START_PAYMENT;
		this.money += mon;
		MoneyChangeDisplay work = new MoneyChangeDisplay(index, mon, 750);
		work.run();
	}

	public void changeMoney(int mon) {
		if (mon < 0)
			mon = (int) (mon * (1 - discount));
		// TODO: bankruptcy
		this.money += mon;
		MoneyChangeDisplay work = new MoneyChangeDisplay(index, mon);
		work.execute();
		if (money < 0) manager.sellUntilSatisfied();
	}

	public class MoneyChangeDisplay extends SwingWorker<Void, Integer> {
		int mon;
		Player pl;
		int waiting;

		public MoneyChangeDisplay(int pl, int mon) {
			this(pl, mon, 1500);
		}

		public MoneyChangeDisplay(int pl, int mon, int waiting) {
			this.mon = mon;
			this.pl = manager.players[pl];
			this.waiting = waiting;
		}

		@Override
		protected Void doInBackground() throws Exception {
			pl.moneyChange = mon;
			manager.frame.repaint();
			Thread.sleep(waiting);
			return null;
		}

		@Override
		protected void done() {
			pl.priorMoney = pl.money;
			pl.moneyChange = 0;
			manager.frame.repaint();
		}

	}

	public void startTurn() {
		if (jailed){
			Message.show(manager.frame,"العب الزهر ولو طلع الزهرين زي بعض تخرج من السجن ببلاش","بنك الحظ");
			manager.getRoll(index);
			return;
		}
		if (unavailable) {
			unavailable = false;
			Message.show(manager.frame,"موقوف عن اللعب","بنك الحظ");
			manager.nextPlayer();
		}
		manager.frame.repaint();
	}

	public void endTurn() {
		firstMove = false;
		if (!nextTurnDiscount)
			discount = 0;
		nextTurnDiscount = false;
		manager.frame.repaint();
	}

	public void takeFromOthers(int amount) {
		for (int i = 0; i < manager.nPlayers; ++i) {
			if (this == manager.players[i])
				this.changeMoney((manager.nPlayers - 1) * amount);
			else
				manager.players[i].changeMoney(-amount);
		}
	}
	public int compareTo(Player b)
    {
    	if (this.position != b.position)
    		return this.position - b.position;
    	else if (this.index == manager.playerTurn)
    		return -1;
    	else if (b.index == manager.playerTurn)
    		return 1;
    	else
    		return 0;
    }
}