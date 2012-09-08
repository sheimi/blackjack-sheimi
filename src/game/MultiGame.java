package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
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


public class MultiGame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142146349650247430L;

	public MultiGame(String[] name) {
		// TODO Auto-generated constructor stub
		playerList.add(new Dealer());
		for (String i : name) {
			playerList.add(new Player(i));
		}
	}

	transient public MultiRound round;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	transient private JPanel panel;

	public void begin(JPanel panel) {
		// TODO Auto-generated method stub
		int x = 0;
		int y = 350;
		for (Player i : playerList) {
			i.setFinalLocation(x, y);
			x += 200;
		}
		this.panel = panel;
		round = new MultiRound(playerList, panel, this);
		
	}
	
	public void ask() {
		final JInternalFrame p = new JInternalFrame();
		panel.add(p);
		JLabel l = new MyLabel(" Continue?  ");

		Icon icon1 = new ImageIcon(MainFrame.class.getResource("b2n.png"));
		Icon icon2 = new ImageIcon(MainFrame.class.getResource("b2e.png"));
		Icon icon3 = new ImageIcon(MainFrame.class.getResource("b2p.png"));
		MyButton m1 = new MyButton(icon1, icon2, icon3);
		JLabel le1 = new MyLabel(" Yes ");
		MyButton m2 = new MyButton(icon1, icon2, icon3);
		JLabel le2 = new MyLabel(" Quit ");

		panel.add(l);
		panel.add(m1);
		panel.add(le1);
		panel.add(m2);
		panel.add(le2);

		m1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// p.dispose();
				panel.removeAll();
				begin(panel);
				reset();
				panel.repaint();

			}

		});

		m2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// p.dispose();
				panel.removeAll();
				reset();
				round.iBegin = false;
				panel.repaint();
			}

		});

		p.setVisible(false);
		panel.repaint();
	}

	public void reset() {
		for (Player i : playerList) {
			i.reset();
		}
	}
}
