package morel.luckydrawer;

import java.util.List;

import lombok.Value;
import lombok.experimental.Builder;

@Value
@Builder
public class DrawActivity {

	private List<Price> prices;

}
