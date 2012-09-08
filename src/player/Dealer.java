package player;

public class Dealer extends AIplayer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String rule = "Stand on all 17's";

	public Dealer() {
		super("Dealer");
		// TODO Auto-generated constructor stub
	}

	public void AInext() {
		if (sum < 17) {
			this.hit();
			AInext();
		} else {
			if (!iAce) {
				this.stand();
				return;
			} else {
				if (sum > 17) {
					this.stand();
					return;
				} else {
					if (rule == "Stand on all 17's") {
						this.stand();
						return;
					} else {
						this.hit();
						AInext();
					}
				}
			}
		}
	}

}
