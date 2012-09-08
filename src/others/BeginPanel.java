package others;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import card.Card;

public class BeginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Card[] card;;
	public float t = 0.01f;
	public boolean[] c1;
	public int[] x;
	public int[] y;
	public int n;
	public static BeginPanel panel;
	public Card[] card2;
	public float t2 = 0.01f;
	public int[] x2;
	public int[] y2;
	public int n2;
	int x1 = 186;
	int y1 = 254;
	boolean f2 = false;
	public boolean iBegin = true;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = new ImageIcon("lib/background2.jpg").getImage();
		g.drawImage(image, 0, 0, this);
		if (t >= 0 && t <= 1) {
			AlphaComposite ac = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, t);
			((Graphics2D) g).setComposite(ac);
		}
		if (card != null) {
			for (int i = 0; i < n; i++) {
				if (card[i] != null) {
					if (c1[i])
						card[i].showBack(g, x[i], y[i]);
					else
						card[i].showCard(g, x[i], y[i]);
				}
			}
		}

		if (t2 >= 0 && t2 <= 1) {
			AlphaComposite ac = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, t2);
			((Graphics2D) g).setComposite(ac);
		}
		if (card2 != null) {
			for (int i = 0; i < n2; i++) {
				if (card2[i] != null) {
					if (f2)
						card2[i].showCard(g, x2[i] - x1 / 2, y2[i] - y1 / 2,
								x1, y1);
					else
						card2[i].showBack(g, x2[i] - x1 / 2, y2[i] - y1 / 2,
								x1, y1);

				}
			}
		}
	}

	public BeginPanel() {
		// TODO Auto-generated method stub
		panel = this;
	}

	public void go() {
		Runnable r = new R();
		Thread t = new Thread(r);
		t.start();

		Runnable r1 = new R1();
		Thread t1 = new Thread(r1);
		t1.start();

		Runnable r2 = new R2();
		Thread t2 = new Thread(r2);
		t2.start();
	}

	class R implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (panel.iBegin) {
				Random x = new Random();
				panel.n = x.nextInt(15) + 5;
				panel.c1 = new boolean[panel.n];
				panel.x = new int[panel.n];
				panel.y = new int[panel.n];
				panel.card = new Card[panel.n];
				for (int i = 0; i < panel.n; i++) {
					panel.card[i] = new Card(0, 0);
					if (card[i] != null && card[i].cardBack != null
							&& card[i].cardImage != null) {
						try {
							panel.card[i].cardBack = panel.card[i].rotateImage(
									panel.card[i].cardBack, x.nextInt(360));
							panel.card[i].cardImage = panel.card[i]
									.rotateImage(panel.card[i].cardImage, x
											.nextInt(360));
						} catch (Exception e) {
						}
					}
					panel.x[i] = x.nextInt(1450) - 100;
					panel.y[i] = x.nextInt(820) - 100;
					if (x.nextInt(2) == 1) {
						panel.c1[i] = true;
					} else {
						panel.c1[i] = false;
					}
				}
				while (panel.t < 1) {
					panel.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.t = (float) (panel.t + 0.01f);
				}

				while (panel.t > 0.01) {
					panel.repaint();
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.t = (float) (panel.t - 0.01f);
				}
			}
		}

	}

	class R1 implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (panel.iBegin) {
				Random x = new Random();
				panel.n2 = x.nextInt(3) + 1;
				panel.x2 = new int[panel.n2];
				panel.y2 = new int[panel.n2];
				panel.card2 = new Card[panel.n2];
				for (int i = 0; i < panel.n2; i++) {
					int j = x.nextInt(360);
					panel.card2[i] = new Card(0, 0);
					if (panel.card2[i] != null
							&& panel.card2[i].cardBack != null
							&& panel.card2[i].cardImage != null) {
						try {
							panel.card2[i].cardBack = panel.card2[i]
									.rotateImage(panel.card2[i].cardBack, j);
							panel.card2[i].cardImage = panel.card2[i]
									.rotateImage(panel.card2[i].cardImage, j);
						} catch (Exception e) {
						}
					}
					panel.x2[i] = x.nextInt(1450) - 100;
					panel.y2[i] = x.nextInt(820) - 100;
				}
				while (panel.t2 < 1) {
					panel.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.t2 = (float) (panel.t2 + 0.01f);
				}

				while (panel.t2 > 0.01) {
					panel.repaint();
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.t2 = (float) (panel.t2 - 0.01f);
				}
			}
		}
	}

	class R2 implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			boolean t = true;
			while (panel.iBegin) {
				if (t) {
					panel.x1--;
					if (panel.x1 < -186) {
						t = false;
					}
				} else {
					panel.x1++;
					if (panel.x1 > 186) {
						t = true;
					}
				}
				if (panel.x1 < 0)
					panel.f2 = false;
				else
					panel.f2 = true;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				panel.repaint();
			}

		}

	}
}
