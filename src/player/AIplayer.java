package player;


public class AIplayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AIplayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void AIBet() {
		int i = (int) (Math.random() * 4);
		switch (i) {
		case 0:
			if (this.chip >= 1000) {
				placeBet(1000);
				break;
			}
		case 1:
			if (this.chip >= 500) {
				placeBet(500);
				break;
			}
		case 2:
			if (this.chip >= 200) {
				placeBet(200);
				break;
			}
		case 3:
			placeBet(100);
		}
	}

	public void AIInsurance() {
		if (!this.iBlackJack) {
			int i = (int) (Math.random() * 2);
			if (i == 0)
				super.getInsurance();
		}
	}
	
	public void AIDoubleDown() {
		int i = (int) (Math.random() * 2);
		if (i == 2)
			this.doubleDown();
	}
	
	public void AISplit() {
		this.split();
	}
	public void AInext() {
		if (this.iDoubleDown) {
			this.hit();
			this.stand();
			return;
		}
		
		if (sum < 17) {
			this.hit();
			AInext();
		} else {
			if (sum >= 20) {
				this.stand();
				return;
			} else {
				int i = (int) (Math.random() * 13);
				if (i <= (21 - 17)) {
					this.hit();
					AInext();
				} else {
					this.stand();
					return;
				}
			}
		}
		
		if (this.subPlayer != null) {
			if (subPlayer.sum < 17) {
				subPlayer.hit();
			} else {
				if (subPlayer.sum >= 20) {
					subPlayer.stand();
				} else {
					int i = (int) (Math.random() * 13);
					if (i <= (21 - 17)) {
						subPlayer.hit();
					} else {
						subPlayer.stand();
					}
				}
			}
		}
	}

}
