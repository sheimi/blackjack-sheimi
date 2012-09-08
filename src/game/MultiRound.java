package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import others.MyButton;
import others.MyLabel;
import player.Dealer;
import player.Player;

import card.Card;

public class MultiRound extends Round {

	static String s = "Colonna MT";
	Dealer dealer = null;
	JInternalFrame p = null;
	private static final long serialVersionUID = 1L;
	ArrayList<Player> playerList;
	JPanel panel;
	MultiGame g;
	boolean f = true;
	boolean iBegin = true;
	MyLabel l;

	public MultiRound(ArrayList<Player> playerList, JPanel panel,
			MultiGame multiGame) {
		// TODO Auto-generated constructor stub
		this.g = multiGame;
		this.playerList = playerList;
		this.panel = panel;
		this.findDealer();
		placeBet(1);
	}

	private void placeBet(final int num) {
		// TODO Auto-generated method stub
		final Player hum = playerList.get(num);
		p = new JInternalFrame();
		panel.add(p);
		l = new MyLabel(hum.name + "'s  Bet :");

		Icon icon1 = new ImageIcon(MainFrame.class.getResource("b1n.png"));
		Icon icon2 = new ImageIcon(MainFrame.class.getResource("b1e.png"));
		Icon icon3 = new ImageIcon(MainFrame.class.getResource("b1p.png"));
		MyButton b1 = new MyButton(icon1, icon2, icon3);
		JLabel le1 = new MyLabel(" $ 1000 ");
		MyButton b2 = new MyButton(icon1, icon2, icon3);
		JLabel le2 = new MyLabel(" $ 500 ");
		MyButton b3 = new MyButton(icon1, icon2, icon3);
		JLabel le3 = new MyLabel(" $ 200 ");
		MyButton b4 = new MyButton(icon1, icon2, icon3);
		JLabel le4 = new MyLabel(" $ 100 ");

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hum.placeBet(1000);
				panel.removeAll();
				if (num == playerList.size() - 1) {
					deal();
					check(1);
				} else {
					placeBet(num + 1);
				}
			}

		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hum.placeBet(500);
				panel.removeAll();
				if (num == playerList.size() - 1) {
					deal();
					check(1);
				} else {
					placeBet(num + 1);
				}
			}

		});
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hum.placeBet(200);
				panel.removeAll();
				if (num == playerList.size() - 1) {
					deal();
					check(1);
				} else {
					placeBet(num + 1);
				}
			}

		});
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hum.placeBet(100);
				panel.removeAll();
				if (num == playerList.size() - 1) {
					deal();
					check(1);
				} else {
					placeBet(num + 1);
				}
			}

		});

		panel.add(l);
		panel.add(b1);
		panel.add(le1);
		panel.add(b2);
		panel.add(le2);
		panel.add(b3);
		panel.add(le3);
		panel.add(b4);
		panel.add(le4);
		p.setVisible(false);
		panel.repaint();

	}

	private void check(final int num) {
		final Player hum = playerList.get(num);
		p = new JInternalFrame();
		panel.add(p);
		l = new MyLabel(hum.name + "  choose your action :");
		Icon icon1 = new ImageIcon(MainFrame.class.getResource("b2n.png"));
		Icon icon2 = new ImageIcon(MainFrame.class.getResource("b2e.png"));
		Icon icon3 = new ImageIcon(MainFrame.class.getResource("b2p.png"));
		MyButton m1 = new MyButton(icon1, icon2, icon3);
		JLabel le1 = new MyLabel(" getInsurance ");
		MyButton m2 = new MyButton(icon1, icon2, icon3);
		JLabel le2 = new MyLabel(" split ");
		MyButton m3 = new MyButton(icon1, icon2, icon3);
		JLabel le3 = new MyLabel(" double down ");
		MyButton m4 = new MyButton(icon1, icon2, icon3);
		JLabel le4 = new MyLabel(" do nothing ");

		m1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				hum.getInsurance();
				panel.removeAll();
				if (num == playerList.size() - 1) {
					check2();
				} else {
					check(num + 1);
				}
			}

		});

		m2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hum.split();
				panel.removeAll();
				if (num == playerList.size() - 1) {
					check2();
				} else {
					check(num + 1);
				}
			}

		});

		m3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hum.doubleDown();
				panel.removeAll();
				if (num == playerList.size() - 1) {
					check2();
				} else {
					check(num + 1);
				}
			}

		});

		m4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panel.removeAll();
				if (num == playerList.size() - 1) {
					check2();
				} else {
					check(num + 1);
				}
			}

		});

		panel.add(l);
		if (dealer.card[1].cardNumber == 1) {
			panel.add(m1);
			panel.add(le1);
		}

		if (hum.iSplit) {
			panel.add(m2);
			panel.add(le2);
		}
		panel.add(m3);
		panel.add(le3);
		panel.add(m4);
		panel.add(le4);

		panel.repaint();
	}
	
	private void deal() {
		// TODO Auto-generated method stub
		for (Player i : playerList) {
			i.delt(panel, this);
			panel.repaint();
		}
	}
	
	private void check2() {
		if (dealer.iBlackJack) {
			for (Player i : playerList) {
				if (i != dealer) {
					if (!i.iBlackJack) {
						i.cashIn("Lose");
					} else {
						i.cashIn("Push");
					}
				}
			}
			f = false;
			g.ask();
			return;
		} else {
			for (Player i : playerList) {
				if (i != dealer) {
					if (i.iInsurance) {
						i.chip -= i.bet;
						i.bet *= 2;
					}
				}
			}
			next(1);
		}
	}
	
	private void next(final int num) {
		final Player hum = playerList.get(num);
		if (hum.iDoubleDown) {
			hum.hit();
			hum.stand();
			if (num == playerList.size() - 1) {
				dealer.AInext();
				cashIn();
			} else {
				next(num + 1);
			}
			return;
		}

		p = new JInternalFrame();
		l = new MyLabel(hum.name + "  choose your action :");
		panel.add(p);
		
		Icon icon1 = new ImageIcon(MainFrame.class.getResource("b3n.png"));
		Icon icon2 = new ImageIcon(MainFrame.class.getResource("b3e.png"));
		Icon icon3 = new ImageIcon(MainFrame.class.getResource("b3p.png"));
		MyButton m1 = new MyButton(icon1, icon2, icon3);
		JLabel le1 = new MyLabel(" Hit ");
		MyButton m2 = new MyButton(icon1, icon2, icon3);
		JLabel le2 = new MyLabel(" Stand ");
		MyButton m3 = new MyButton(icon1, icon2, icon3);
		JLabel le3 = new MyLabel(" Surrender ");

		m1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hum.hit();
				// p.dispose();
				panel.removeAll();
				if (!hum.iStop) {
					next(num);
				} else {
					checkSubPlayer(num);
				}

			}

		});

		m2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hum.stand();
				// p.dispose();
				panel.removeAll();
				if (!hum.iStop) {
					next(num);
				} else {
					checkSubPlayer(num);
				}
			}

		});

		m3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hum.surrender();
				// p.dispose();
				panel.removeAll();
				if (!hum.iStop) {
					next(num);
				} else {
					checkSubPlayer(num);
				}
			}

		});
		panel.add(l);
		panel.add(m1);
		panel.add(le1);
		panel.add(m2);
		panel.add(le2);
		panel.add(m3);
		panel.add(le3);

		panel.repaint();

	}
	
	private void checkSubPlayer(final int num) {
		final Player hum = playerList.get(num);
		if (hum.subPlayer != null) {
			p = new JInternalFrame();
			
			l = new MyLabel(hum.name + "  choose your action \nfor your second hand :");
			panel.add(p);
			Icon icon1 = new ImageIcon(MainFrame.class.getResource("b3n.png"));
			Icon icon2 = new ImageIcon(MainFrame.class.getResource("b3e.png"));
			Icon icon3 = new ImageIcon(MainFrame.class.getResource("b3p.png"));
			MyButton m1 = new MyButton(icon1, icon2, icon3);
			JLabel le1 = new MyLabel(" Hit ");
			MyButton m2 = new MyButton(icon1, icon2, icon3);
			JLabel le2 = new MyLabel(" Stand ");
			MyButton m3 = new MyButton(icon1, icon2, icon3);
			JLabel le3 = new MyLabel(" Surrender ");

			m1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					hum.subPlayer.hit();
					// p.dispose();
					panel.removeAll();
					if (!hum.subPlayer.iStop) {
						checkSubPlayer(num);
					} else {
						if (num == playerList.size() - 1) {
							dealer.AInext();
							cashIn();
						} else {
							next(num + 1);
						}
					}
				}

			});

			m2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					hum.subPlayer.stand();
					// p.dispose();
					panel.removeAll();
					if (!hum.subPlayer.iStop) {
						checkSubPlayer(num);
					} else {
						if (num == playerList.size() - 1) {
							dealer.AInext();
							cashIn();
						} else {
							next(num + 1);
						}
					}
				}

			});

			m3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					hum.subPlayer.surrender();
					// p.dispose();
					panel.removeAll();
					if (!hum.subPlayer.iStop) {
						checkSubPlayer(num);
					} else {
						if (num == playerList.size() - 1) {
							dealer.AInext();
							cashIn();
						} else {
							next(num + 1);
						}
					}
				}

			});
			panel.add(l);
			panel.add(m1);
			panel.add(le1);
			panel.add(m2);
			panel.add(le2);
			panel.add(m3);
			panel.add(le3);
			panel.repaint();

		} else {
			if (num == playerList.size() - 1) {
				dealer.AInext();
				cashIn();
			} else {
				next(num + 1);
			}
		}

	}
	
	private void cashIn() {
		f = false;
		for (Player i : playerList) {
			if (i != dealer) {
				if (i.subPlayer != null && i.subPlayer.iBlackJack)
					i.subPlayer.cashIn("BlackJack");
				if (i.iBlackJack)
					i.cashIn("BlackJack");
			}
		}

		if (dealer.bust) {
			for (Player i : playerList) {
				if (i != dealer) {
					if (i.subPlayer != null && !i.subPlayer.iBlackJack) {
						if (i.subPlayer.bust) {
							i.subPlayer.cashIn("Push");
						} else {
							i.subPlayer.cashIn("Win");
						}
					}
					if (!i.iBlackJack) {
						if (i.bust) {
							i.cashIn("Push");
						} else {
							i.cashIn("Win");
						}
					}
				}
			}
		} else {
			for (Player i : playerList) {
				if (i != dealer) {
					if (i.subPlayer != null && !i.subPlayer.iBlackJack) {
						if (i.subPlayer.bust || i.subPlayer.sum < dealer.sum)
							i.subPlayer.cashIn("Lose");
						else {
							if (i.subPlayer.sum > dealer.sum)
								i.subPlayer.cashIn("Win");
							else
								i.subPlayer.cashIn("Push");
						}
					}
					if (!i.iBlackJack) {
						if (i.bust || i.sum < dealer.sum)
							i.cashIn("Lose");
						else {
							if (i.sum > dealer.sum)
								i.cashIn("Win");
							else
								i.cashIn("Push");
						}
					}
				}
			}
		}
		g.ask();
	}

	private void findDealer() {
		// TODO Auto-generated method stub
		for (Player i : playerList) {
			if (i instanceof Dealer) {
				dealer = (Dealer) i;
				break;
			}
		}
	}

	public void drawNew(Graphics g) {
		// TODO Auto-generated method stub
		for (Player i : playerList) {
			for (Card j : i.card) {
				if (j != null && j.iNew) {
					j.showBack(g, j.x, j.y);
					break;
				}
			}
			if (i.subPlayer != null) {
				for (Card j : i.subPlayer.card) {
					if (j != null && j.iNew) {
						j.showBack(g, j.x, j.y);
						break;
					}
				}
			}

		}
	}

	public void drawAll(Graphics page) {
		// TODO Auto-generated method stub
		if (iBegin) {
			Font font1 = new Font(s, Font.BOLD, 25);
			page.setFont(font1);
			page.setColor(Color.yellow);
			page.drawString("NAME:", 0, 320);
			page.drawString("CHIP:", 0, 355);
			page.drawString("BET :", 0, 390);
			for (Player i : playerList) {
				for (Card j : i.card) {
					if (j != null && !j.iNew) {
						if (j == i.card[0] && i == dealer && f) {
							j.showBack(page, j.x, j.y);
						} else
							j.showCard(page, j.x, j.y);
					}
				}
				if (i.subPlayer != null) {
					for (Card j : i.subPlayer.card) {
						if (j != null && !j.iNew)
							j.showCard(page, j.x, j.y);
					}
				}
				Font font = new Font(s, Font.BOLD, 25);
				page.setFont(font);
				page.drawString(i.name, i.x + 80, 320);
				if (!(i instanceof Dealer)) {
					page.drawString(Integer.toString(i.chip), i.x + 75,
							355);
					page.drawString(Integer.toString(i.bet), i.x + 75, 390);
				}
			}
		}
	}

}
