package player;

import game.Round;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import card.Card;
import sound.Sound;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7069576047037753998L;
	public boolean iStop = false;
	public boolean bust = false;
	public boolean iAce = false;
	public boolean iBlackJack = false;
	public boolean iSplit = false;
	public boolean iDoubleDown = false;
	public boolean iInsurance = false;
	public String name;
	public int sum;
	public int numberOfCard = 0;
	public Card[] card = new Card[10];
	public int chip;
	public int bet = 0;
	transient public Player subPlayer = null;
	public boolean iLoser = false;
	public int x;
	public int y;
	transient JPanel panel;
	transient Round round;

	public Player(String name) {
		chip = 10000;
		this.name = name;
	}

	public void setFinalLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void placeBet(int bet) {
		this.bet += bet;
		chip -= bet;
	}

	public void delt(JPanel panel, Round round) {
		this.panel = panel;
		this.round = round;
		if (this.chip <= 100) {
			iLoser = true;
			return;
		}
		for (int i = 0; i < 10; i++)
			card[i] = null;
		card[0] = new Card(this.x, this.y);
		card[1] = new Card(this.x, this.y + 20);
		numberOfCard = 2;
		add();
		if (sum == 21) {
			iBlackJack = true;
			iStop = true;
		}

		if (card[1].cardValue.equals(card[0].cardValue))
			iSplit = true;
		move();
	}

	public void hit() {
		card[numberOfCard] = new Card(this.x, this.y + 20 * numberOfCard);
		numberOfCard++;
		add();
		move();
	}

	public void stand() {
		iStop = true;
	}

	public void getInsurance() {
		iInsurance = true;
		bet /= 2;
		placeBet(-bet);
	}

	public void doubleDown() {
		placeBet(bet);
		iDoubleDown = true;
	}

	public void split() {
		subPlayer = new Player("sub");
		subPlayer.chip = bet;
		this.chip -= bet;

		subPlayer.placeBet(bet);
		subPlayer.card[0] = this.card[1];
		subPlayer.card[0].iNew = true;
		this.card[1] = null;
		this.numberOfCard = 1;
		subPlayer.numberOfCard = 1;
		subPlayer.x = this.x;
		subPlayer.y = 0;
		subPlayer.card[0].finalX = this.x;
		subPlayer.card[0].finalY = 0;
		subPlayer.panel = panel;
		this.add();
		subPlayer.add();
		subPlayer.move();
	}

	public void surrender() {
		getInsurance();
		iStop = true;
	}

	private void add() {
		iAce = false;
		sum = 0;
		for (Card i : card) {
			if (i == null)
				break;
			if (i.cardNumber == 1)
				iAce = true;
			sum += i.cardNumber;
		}
		if (iAce && sum <= 11) {
			sum = sum + 10;
		}
		if (sum > 21) {
			bust = true;
			iStop = true;
		}
	}

	public void cashIn(String type) {
		if (subPlayer != null) {
			this.chip += subPlayer.chip;
		}
		if (type == "Lose") {
			return;
		}
		if (type == "Push") {
			this.chip += this.bet;
			return;
		}
		if (type == "BlackJack") {
			this.chip += this.bet * 2.5;
			return;
		}
		if (type == "Win") {
			this.chip += this.bet * 2;
			return;
		}
	}

	public void reset() {
		iStop = false;
		bust = false;
		iAce = false;
		iBlackJack = false;
		iSplit = false;
		iDoubleDown = false;
		iInsurance = false;
		sum = 0;
		numberOfCard = 0;
		bet = 0;
		subPlayer = null;
		for (int i = 0; i < 10; i++) {
			card[i] = null;
		}
	}

	public void move() {
		Runnable r = new R1();
		Thread t = new Thread(r);

		t.start();
	}

	class R1 implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (Card i : card) {
				if (i != null && i.iNew) {

					Runnable r = new R2();
					Thread t = new Thread(r);
					t.start();

					while (i.x > i.finalX || i.y != i.finalY) {
						panel.repaint();
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (i.x > i.finalX) {
							i.x--;
						} else {
							if (i.y < i.finalY) {
								i.y++;
							}
							if (i.y > i.finalY) {
								i.y--;
							}
						}
					}
					i.iNew = false;
				}
			}
			panel.repaint();
		}

	}

	class R2 implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Sound sound;
			try {
				sound = new Sound("lib/sound/Track 1.wav");
				InputStream stream = new ByteArrayInputStream(sound
						.getSamples());
				sound.play(stream);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}

	}

}
