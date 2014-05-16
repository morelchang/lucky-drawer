package morel.luckydrawer;

import java.util.Arrays;
import java.util.List;

import morel.luckydrawer.drawer.DrawActivity;

import org.junit.Assert;
import org.junit.Test;

public class DrawActivityTest {
	
	@Test
	public void testDrawOneFor() {
		// prices for draw
		Price ipad = Price.builder().count(3).name("iPad").build();
		Price iphone = Price.builder().count(1).name("iPhone 6").build();
		Price cash = Price.builder().count(1).name("10,000 NTD Cash").build();
		List<Price> prices = Arrays.asList(ipad, iphone, cash);

		// tickets to draw
		List<Ticket> tickets = Arrays.asList(
				Ticket.builder().name("Morel").build(),
				Ticket.builder().name("Wilson").build(), 
				Ticket.builder().name("Roger").build(),
				Ticket.builder().name("Isaac").build());

		// draw all ipad
		DrawActivity d = new DrawActivity(tickets, prices);
		for (int i = 0; i < ipad.getCount(); i++) {
			Winning w = d.drawOneFor(ipad);
			System.out.println(w);
			Assert.assertTrue(tickets.contains(w.getTicket()));
			Assert.assertEquals(ipad, w.getPrice());
			
			int time = i + 1;
			Assert.assertEquals(ipad.getCount() - time, d.leftOf(ipad));
			Assert.assertEquals(time, d.drewOf(ipad));
			// check left tickets
			Assert.assertEquals(tickets.size() - time, d.getLeftTickets());
		}
		Assert.assertEquals(0, d.leftOf(ipad));
		Assert.assertEquals(ipad.getCount(), d.drewOf(ipad));
		
		// try to draw one more ipad and get exception
		try {
			d.drawOneFor(ipad);
			Assert.fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
		}
		
		// check remaining of other prices
		Assert.assertEquals(iphone.getCount(), d.leftOf(iphone));
		Assert.assertEquals(cash.getCount(), d.leftOf(cash));
	}

}
