package main;

import java.util.Random;

import javax.swing.SwingWorker;

public class DieRoller extends SwingWorker<Integer, Integer> {
	public Manager manager;
	int pl = 0;
	public boolean same = false;
	static Random rn = new Random();
	public DieRoller(Manager m, int pl){
		this.manager = m;
		this.pl = pl;
	}
	static int rand (int minimum , int maximum) //end is inclusive
	{
		int range = maximum - minimum + 1;
		int number =  rn.nextInt(range) + minimum;
		if (number == 0) 
		{
			number =  rn.nextInt(range) + minimum;
		}
		return number;
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
		int rep = rand(20,25);
		for (int i = 0 ; i < rep ; ++i)
		{
			manager.changeDie(rand(1,6),rand(1,6));
			Thread.sleep(50);
		}
		int n1 = rand(0,6), n2 = rand(0,6);
		
		manager.changeDie(n1, n2);
		
		Thread.sleep(1000);
		if (n1 == n2) same = true;
		return n1 + n2;
	}

	@Override
	protected void done(){
		int x = 0;
		try {
			x = this.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (x == 0){
			Message.show(manager.frame,"مبروك انت افقر واحد انا شوفته. البنك هيديك 750 جنيه جائزة", "بنك النحس");
			manager.players[pl].changeMoney(750);
		}
		manager.players[pl].moveBy(x,same);
		
	}
}