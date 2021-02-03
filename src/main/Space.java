package main;

enum Types{
	Start,
	Country,
	LuckyJudgement,
	LuckClub,
	Judgement,
	Lucky,
	Station,
	QuickBus,
	Jail
};

public class Space {
	Types type;
	Country country;
	public Space(Types type){
		this.type = type;
	}
	public Space(Country coun, int index){
		this.type=Types.Country;
		this.country = coun;
		coun.indexOnMap = index;
	}
}
