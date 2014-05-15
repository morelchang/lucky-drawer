package morel.luckydrawer.drawer;

import java.util.List;
import java.util.Random;

import morel.luckydrawer.DrawActivity;
import morel.luckydrawer.Drawer;
import morel.luckydrawer.Price;
import morel.luckydrawer.Ticket;
import morel.luckydrawer.Winning;

public class GeneralDrawer implements Drawer {

	private List<Ticket> tickets;
	private DrawActivity activity;

	public GeneralDrawer(List<Ticket> tickets, DrawActivity activity) {
		this.tickets = tickets;
		this.activity = activity;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public DrawActivity getActivity() {
		return activity;
	}

	@Override
	public Winning drawOneFor(Price currentPrice) {
		if (!activity.getPrices().contains(currentPrice)) {
			throw new IllegalArgumentException("currentPrice is not in activity. currentPrice:" + currentPrice); 
		}
		Ticket winner = drawTicketWithoutDuplicated();
		return Winning.builder().ticket(winner).price(currentPrice).build();
	}

	/**
	 * draw a ticket from current tickets, no duplicated ticket would be happened.
	 * 
	 * @return
	 */
	private Ticket drawTicketWithoutDuplicated() {
		Ticket t = drawTicket();
		// TODO not implemented
		return t;
	}

	public Ticket drawTicket() {
		Random r = new Random();
		int number = r.nextInt(tickets.size());
		return tickets.get(number);
	}

}
