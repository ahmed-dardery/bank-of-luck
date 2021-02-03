package main;

import main.Manager.ClickAction;

public class Lucky extends CardsBase {
	public Lucky(Manager manager) {
		super(manager);

		//index = 8;
	}
	@Override
	public void next(Player pl) {
		int i = this.cards.get(index);
		if (++index >= 15) {
			this.reShuffle();
		}
		switch (i) {
		case 0:
			Message.show(manager.frame, "حصل صدام بسيارتك المؤمنة تدفع لك شركة التأمين 200 جنيه تصليحها وارجع 5 خطوات للخلف","محكمة");
			pl.changeMoney(200);
			pl.moveBy(-5);
			return;
		case 1:
			Message.show(manager.frame, "حماتك تحبك. أرسلت لك 100 جنيه حوالة. اذهب علي البحرين لتقبضها","محكمة");
			pl.moveTo(manager.countries[7]);
			pl.changeMoney(100);
			return;
		case 2:
			Message.show(manager.frame, "حظك من السماء رزقت بطفل جميل خذ 50 جنيه من كل لاعب بدل الهدية","محكمة");
			pl.takeFromOthers(50);
			break;
		case 3:
			Message.show(manager.frame, "تقدم اربع خانات يدفع عنك البنك نصف ما قد تدفع حتي نهاية الدور","محكمة");
			pl.discount = 0.5;
			pl.moveBy(4);
			return;
		case 4:
			Message.show(manager.frame, "عندك أربع اطفال ادفع 25 جنيه عن كل طفل مصاريف مدرسة","محكمة");
			pl.changeMoney(-100);
			break;
		case 5:
			Message.show(manager.frame, 
					"أنت طيب القلب تبرعت ب 100 جنيه للاسعاف ادفع المبلغ ثم انتقل لنفطة البداية وخذ معاشك","محكمة");
			pl.changeMoney(Manager.START_PAYMENT - 100);
			pl.moveTo(0);
			return;
		case 6:
			Message.show(manager.frame, "أنت وطني مخلص ربحت جائزة كيف تخدم امتك خد خمسين جنيه","محكمة");
			pl.changeMoney(50);
			break;
		case 7:
			Message.show(manager.frame, "أنت هادئ ومحب لأسرتك ادفع 150 جنيه فواتير خياطة عن زوجتك","محكمة");
			pl.changeMoney(-150);
			break;
		case 8:
			Message.show(manager.frame, 
					"ربحت جائزة الاذاعة اخر احدى المدن الاتية واذهب اليها غزة او عمان او دمشق وخد 100 جنيه من البنك","محكمة");
			pl.changeMoney(100);
			manager.setCountryAction(new int[] { 2, 22, 26 }, ClickAction.MOVETO);
			
			return;
		case 9:
			Message.show(manager.frame, "أنت محب للأشياء الجميلة ادفع 100 جنيه ثمن ملابس لك ولاسرتك","محكمة");
			pl.changeMoney(-200);
			break;
		case 10:
			Message.show(manager.frame, "عيد ميلادك خد من كل لاعب 30 جنيه","محكمة");
			pl.takeFromOthers(30);
			break;
		case 11:
			Message.show(manager.frame, "ربحت سحب الادخار خذ 300 من البنك","محكمة");
			pl.changeMoney(300);
			break;
		case 12:
			Message.show(manager.frame, "أنت كثير الهزار ادفع 100 جنيه لما سببته بهزارك","محكمة");
			pl.changeMoney(-100);
			break;
		case 13:
			Message.show(manager.frame, "أنت داهية استطعت أن تقنع حماتك علي تركك والسفر خذ 100 جنيه مصاريف السفر معرفش ازاي بس خدهم","محكمة");
			pl.changeMoney(100);
			break;
		case 14:
			Message.show(manager.frame, "كرت يدفع عنك اللعبة القادمة احفظه للعبة القادمة فقط ولا تبيعه","محكمة");
			pl.discount = 1;
			pl.nextTurnDiscount = true;
			break;
		}
		
		manager.nextPlayer();

	}

}
