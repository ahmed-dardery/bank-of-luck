package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class CardsBase {
	protected int index=0;
	public Manager manager;
	List<Integer> cards;
	public CardsBase(Manager manager){
		this.manager = manager;
		cards = new ArrayList<Integer>();
		
		for (int i = 0; i <15; ++i)
			cards.add(i);
		Collections.shuffle(cards);	
	}
	protected void reShuffle(){
		index = 0;
		Collections.shuffle(cards);	
	}
	
	public abstract void next(Player pl);
}
