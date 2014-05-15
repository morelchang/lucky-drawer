package morel.luckydrawer;

import java.util.Arrays;
import java.util.List;

import morel.luckydrawer.drawer.GeneralDrawer;

import org.junit.Assert;
import org.junit.Test;

public class DrawerTest {

	@Test
	public void testDrawOneFor() {
		// prices for draw
		List<Price> prices = Arrays.asList(
				Price.builder().count(10).name("iPad").build(), 
				Price.builder().count(5).name("iPhone 6").build(), 
				Price.builder().count(1).name("10,000 NTD Cash").build());
		DrawActivity activity = DrawActivity.builder().prices(prices).build();
		System.out.println(prices);
		
		// tickets to draw
		List<Ticket> tickets = Arrays.asList(
				Ticket.builder().name("Morel").build(),
				Ticket.builder().name("Wilson").build(), 
				Ticket.builder().name("Roger").build(),
				Ticket.builder().name("Isaac").build());

		// test the drawer with first price
		Price currentPrice = prices.get(0);
		Drawer d = new GeneralDrawer(tickets, activity);
		Winning w = d.drawOneFor(currentPrice);
		Assert.assertEquals(currentPrice, w.getPrice());
		Assert.assertTrue(tickets.contains(w.getTicket()));
		System.out.println(w);
	}
	
	@Test
	public void testDraw() {
		// prices for draw
		List<Price> prices = Arrays.asList(
				Price.builder().count(10).name("iPad").build(), 
				Price.builder().count(5).name("iPhone 6").build(), 
				Price.builder().count(1).name("10,000 NTD Cash").build());
		DrawActivity activity = DrawActivity.builder().prices(prices).build();
		System.out.println(prices);
		
		// tickets to draw
		List<Ticket> tickets = Arrays.asList(
				Ticket.builder().name("Morel").build(),
				Ticket.builder().name("Wilson").build(), 
				Ticket.builder().name("Roger").build(),
				Ticket.builder().name("Isaac").build());

		GeneralDrawer d = new GeneralDrawer(tickets, activity);
		Assert.assertTrue(tickets.contains(d.drawTicket()));
	}

}
