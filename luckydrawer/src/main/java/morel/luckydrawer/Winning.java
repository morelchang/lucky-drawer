package morel.luckydrawer;

import lombok.Value;
import lombok.experimental.Builder;

@Value
@Builder
public class Winning {

	private Ticket ticket;
	private Price price;

}
