package morel.luckydrawer;

public interface Drawer {

	Winning drawOneFor(Price currentPrice);

	int leftOf(Price currentPrice);

}
