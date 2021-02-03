package main;

import main.Country.Building;
import main.Manager.ClickAction;

public class Judgement extends CardsBase {
	public Judgement(Manager manager) {
		super(manager);
	}
	@Override
	public void next(Player pl) {
		int i = this.cards.get(index);
		if (++index >= 15) {
			this.reShuffle();
		}
		switch (i) {
		case 0:
			Message.show(manager.frame, "القيت زباله فى الشارع ادفع 50 جنيه غرامه","حظك");
			pl.changeMoney(-50);
			break;
		case 1:
			Message.show(manager.frame, "خسرت قضيتين ادفع 15 جنيه اتعاب محاماه لكل واحدة","حظك");
			pl.changeMoney(-30);
			break;
		case 2:
			Message.show(manager.frame, "ربحت قضيتك ضد زملائك خذ 25 جنيه من كل لاعب","حظك");
			pl.takeFromOthers(25);
			break;
		case 3:
			Message.show(manager.frame, "كسرت عمود النور بسيارتك ادفع 40 جنيه مخالفه","حظك");
			pl.changeMoney(-40);
			break;
		case 4:
			Message.show(manager.frame, "شاهد زور ادفع 100 جنيه","حظك");
			pl.changeMoney(-100);
			break;
		case 5:
			
			int r[] = new int[3];
			r[0] = DieRoller.rand(0, Manager.nCountry - 1);
			do{
				r[1] = DieRoller.rand(0, Manager.nCountry - 1);
			}while (r[0] == r[1]);
			
			do{
				r[2] = DieRoller.rand(0, Manager.nCountry - 1);
			}while (r[0] == r[2] || r[2] == r[1]);
			Country cos[] = {manager.countries[r[0]],manager.countries[r[1]],manager.countries[r[2]]};
			Message.show(manager.frame, "خد من اصحاب المدن التاليه 50 جنيه " + cos[2].name+ " - " +cos[1].name+" - " + cos[0].name,"حظك");
			int count = 0;
			for(Country co : cos){
				if (co.owner != null && co.owner != pl){
					co.owner.changeMoney(-50);
					count++;
				}
			}
			pl.changeMoney(50*count);
			break;
		case 6:
			Message.show(manager.frame, "خد 25 جنيه عن كل جراج او استراحه اوو سوق","حظك");
			int mon = 0;
			for(Country co: pl.countries){
				if (co.establishment != Building.NONE){
					mon += 25;
				}
			}
			pl.changeMoney(mon);
			break;
		case 7:
			Message.show(manager.frame, "هارب من العسكريه اذهب للسجن حالا","حظك");
			pl.moveTo(24);
			return;
		case 8:
			Message.show(manager.frame, "ادفع 50 جنيه عن كل سوق او جراج او استراحه","حظك");
			int monx = 0;
			for(Country co: pl.countries){
				if (co.establishment != Building.NONE){
					monx -= 50;
				}
			}
			pl.changeMoney(monx);
			break;
		case 9:
			Message.show(manager.frame, "خسرت قضيتك ادفع 15 جنيه اتعاب محاماه","حظك");
			pl.changeMoney(-15);
			break;
		case 10:
			Message.show(manager.frame, "انت موقوف بتهمه اقلاق الراحه اخسر دورك القادم وادفع 50 جنيه","حظك");
			pl.changeMoney(-50);
			pl.unavailable = true;
			break;
		case 11:
			Message.show(manager.frame, "تذكره امان للخروج من السجن اذهب للسجن حالا","حظك");
			pl.safeFromJail = true;
			pl.moveTo(24);
			return;
		case 12:
			Message.show(manager.frame,"ربحت قضيتك ضد البنك اختر مدينة لا يزيد ثمنها عن 150 او اضغط علي البداية وخذ 200 جنيه","حظك");
			int clickable[] = new int[38];
			int lim = 0;
			clickable[lim++] = 0;
			for(Country co : manager.countries){
				if (co.price <= 150 && co.owner == null) clickable[lim++] = co.indexOnMap;
			}
			int choices[] = new int[lim];
			System.arraycopy( clickable, 0, choices, 0, lim );
			manager.setCountryAction(choices, ClickAction.BUYFREE);
			return;
		case 13:
			Message.show(manager.frame, "اتهمت غدرا و ظهرت برائتك (الصحاب فاجازه) خد 200 جنيه تعويض","حظك");
			pl.changeMoney(200);
			break;
		case 14:
			Message.show(manager.frame, "شاهد زور اذهب للسجن","حظك");
			pl.moveTo(24);
			return;
		}

		manager.nextPlayer();
	}

}
