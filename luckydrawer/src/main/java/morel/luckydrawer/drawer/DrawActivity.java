package morel.luckydrawer.drawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import lombok.Getter;
import morel.luckydrawer.Drawer;
import morel.luckydrawer.Price;
import morel.luckydrawer.Ticket;
import morel.luckydrawer.Winning;

public class DrawActivity implements Drawer {

	@Getter
	private List<Ticket> tickets = new ArrayList<Ticket>();
	@Getter
	private List<Price> prices = new ArrayList<Price>();
	
	private Map<Price, Integer> drewPriceCountMap = new HashMap<Price, Integer>();
	private List<Ticket> drewTickets = new ArrayList<Ticket>();
	private List<Ticket> ticketsInBox = new ArrayList<Ticket>();

	public DrawActivity(List<Ticket> tickets, List<Price> prices) {
		this.tickets.addAll(tickets);
		this.prices.addAll(prices);
		this.ticketsInBox.addAll(tickets);
	}

	@Override
	public Winning drawOneFor(Price price) {
		ensurePriceExists(price);
		
		// make sure price count consistent
		int left = leftOf(price);
		if (left <= 0) {
			throw new IllegalArgumentException("there is no left for price:" + price);
		}
		drewPriceCountMap.put(price, drewOf(price) + 1);
		
		// draw ticket
		Ticket winner = drawTicket();
		drewTickets.add(winner);

		// record winning
		Winning winning = Winning.builder().ticket(winner).price(price).build();
		return winning;
	}

	/**
	 * ensure a price exists in price list of current draw game, or throws
	 * {@link IllegalArgumentException}
	 * 
	 * @param price
	 */
	private void ensurePriceExists(Price price) {
		if (!prices.contains(price)) {
			throw new IllegalArgumentException("currentPrice is not in activity. currentPrice:" + price); 
		}
	}

	public Ticket drawTicket() {
		Random r = new Random();
		int number = r.nextInt(ticketsInBox.size());
		Ticket ticket = ticketsInBox.get(number);
		// add/remove ticket from buckets
		drewTickets.add(ticket);
		ticketsInBox.remove(ticket);
		return ticket;
	}

	@Override
	public int leftOf(Price price) {
		ensurePriceExists(price);
		return price.getCount() - drewOf(price);
	}

	public int drewOf(Price price) {
		Integer drew = drewPriceCountMap.get(price);
		return  (drew == null) ? (0) : (drew);
	}

	public int getLeftTickets() {
		return ticketsInBox.size();
	}

}
