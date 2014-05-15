package morel.luckydrawer;

import lombok.Value;
import lombok.experimental.Builder;

@Value
@Builder
public class Price {

	private int count;
	private String name;

}
