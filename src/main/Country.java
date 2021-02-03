package main;
import java.awt.Color;

public class Country {
	public String name;
	public int price;
	public int rent;
	public int garagePrice;
	public int restPrice;
	public int marketPrice;
	public int garageRate;
	public int restRate;
	public int marketRate;
	public int groupId;
	public int indexOnMap;
	public Color groupColor;
	
	enum Building{
		NONE,
		GARAGE,
		REST,
		MARKET,
		NOT_CHANGED
	}	
	public Building establishment = Building.NONE;

	public Player owner = null;
	public Country(String name,  int price,  int rent,
			int garageRate,  int restRate,  int marketRate,
			int garagePrice,  int restPrice, int marketPrice,
			int groupId,  Color groupColor){
		
		this.name = name;
		this.price = price;
		this.rent = rent;
		this.garagePrice = garagePrice;
		this.restPrice = restPrice;
		this.marketPrice = marketPrice;
		this.garageRate = garageRate;
		this.restRate = restRate;
		this.marketRate = marketRate;
		this.groupId = groupId;
		this.groupColor = groupColor;
	}
	public int getRate(){
		if (establishment == Building.REST) return restRate;
		else if (establishment == Building.GARAGE) return garageRate;
		else if (establishment == Building.MARKET) return marketRate;
		else return rent;
		
	}
	public int getUnitPrice(){
		if (establishment == Building.REST) return restPrice;
		else if (establishment == Building.GARAGE) return garagePrice;
		else if (establishment == Building.MARKET) return marketPrice;
		else return 0;
	}
	public int getUnitPrice(Building establishment){
		if (establishment == Building.REST) return restPrice;
		else if (establishment == Building.GARAGE) return garagePrice;
		else if (establishment == Building.MARKET) return marketPrice;
		else return 0;
	}
}
