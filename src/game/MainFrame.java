package game;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import others.BeginPanel;
import others.MyButton;
import others.MyWindow;
import sound.Sound;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuItem jmi1;
	private JMenuItem jmi2;
	private JMenuItem jmi3;
	private JMenuItem jmi4;
	private JMenuItem jmih;
	private Game game = null;
	private MultiGame multigame = null;
	private JPanel panel;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Container c;
	private BeginPanel begin;
	private MyWindow w;
	private static ArrayList<String> musicList = new ArrayList<String>();
	static String [] name = {"Cat", "Dog", "Pig", "Mouse", "Duck"};

	public static void main(String[] args) {
		File file = new File("music list.txt");
		try {
			FileReader reader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(reader);

			String s1;
			do {
				s1 = bReader.readLine();
				if (s1 != null && s1 != "\n" && s1 != "\r" && !s1.equals("")) {
					musicList.add(s1);
				}
			} while (s1 != null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new MainFrame("BlackJack");
	}

	MainFrame(String i) {
		super(i);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();

		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		jmb.add(fileMenu);
		jmb.add(helpMenu);
		fileMenu.add(this.jmi1 = new JMenuItem("New Game"));
		fileMenu.add(this.jmi2 = new JMenuItem("Save Game"));
		fileMenu.add(this.jmi3 = new JMenuItem("Load Game"));
		fileMenu.add(this.jmi4 = new JMenuItem("Exit"));
		helpMenu.add(this.jmih = new JMenuItem("Help"));
		jmi1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				w.setVisible(true);
			}

		});
		jmi2.addActionListener(new act2());
		jmi3.addActionListener(new act3());
		jmi4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		jmih.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = "1. single game: you can play with a dealer and 0 or 1 or 2 or 3 or 4 or 5 other players\n";
				s += "2. multiplay game: you can create 1 to 6 role to play with the dealer(computer)\n";
				s += "3. save and load: you can load the game that you have saved before\n";
				s += "4. music: you can put the music you like in the 'lib/sound' folder and add it to the file 'music list.txt', the music will be played randomly\n";
				s += "just enjoy it";
				JOptionPane.showMessageDialog(null, s, "Help", 1);
			}
		});
		setVisible(true);
		setSize(1350, 720);
		this.setLocation((screenSize.width - this.getWidth()) / 2,
				(screenSize.height - this.getHeight()) / 2 - 20);
		setResizable(false);
		begin = new BeginPanel();
		begin.go();
		c.add(begin);

		Runnable r = new RSound();
		Thread t = new Thread(r);
		t.start();
		addButton();
	}

	private void addButton() {
		w = new MyWindow();
		Container c1 = w.getPanel();
		c1.setLayout(null);
		Icon icon1 = new ImageIcon(MainFrame.class
				.getResource("diamond Ace 1.png"));
		Icon icon2 = new ImageIcon(MainFrame.class
				.getResource("diamond Ace 2.png"));
		Icon icon3 = new ImageIcon(MainFrame.class
				.getResource("diamond Ace 3.png"));
		MyButton button1 = new MyButton(icon1, icon2, icon3);
		c1.add(button1);
		button1.setLocation(0, 0);
		button1.addActionListener(new act1());

		Icon icon11 = new ImageIcon(MainFrame.class
				.getResource("club Ace 1.png"));
		Icon icon21 = new ImageIcon(MainFrame.class
				.getResource("club Ace 2.png"));
		Icon icon31 = new ImageIcon(MainFrame.class
				.getResource("club Ace 3.png"));
		MyButton button2 = new MyButton(icon11, icon21, icon31);
		c1.add(button2);
		button2.setLocation(0, 120);
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				begin.iBegin = false;
				if (w != null) {
					w.setVisible(false);
				}
				String number = JOptionPane.showInputDialog(null,
						"Please enter the number of hum players",
						"please a number between 2 to 6");
				String num = "23456";

				if (number == null) {
					begin.iBegin = true;
					begin.go();
					w.setVisible(true);
					return;
				}

				if ((num.contains(number)) && (!number.equals(""))) {
					int n = Integer.parseInt(number);
					String[] name = new String[n];
					for (int i = 0; i < n; i++) {
						do {
							name[i] = JOptionPane.showInputDialog(null,
									"CREAT ROLES" + (i + 1),
									"please enter your name");
						} while (name[i] == null || name[i].equals(""));
					}
					multigame = new MultiGame(name);
					game = null;
					c.removeAll();
					panel = new MyPanel();
					c.add(panel);
					multigame.begin(panel);

				} else {
					begin.iBegin = true;
					begin.go();
					JOptionPane.showMessageDialog(null, "wrong input");
					w.setVisible(true);
				}
			}
		});

		Icon icon12 = new ImageIcon(MainFrame.class
				.getResource("heart Ace 1.png"));
		Icon icon22 = new ImageIcon(MainFrame.class
				.getResource("heart Ace 2.png"));
		Icon icon32 = new ImageIcon(MainFrame.class
				.getResource("heart Ace 3.png"));
		MyButton button3 = new MyButton(icon12, icon22, icon32);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				w.setVisible(false);
				JFileChooser jfc = new JFileChooser(new File("."));
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"BlackJackFile", "blj");
				jfc.setFileFilter(filter);
				if (jfc.showOpenDialog(null) == 0)
					open(jfc.getSelectedFile());
				else
					w.setVisible(true);
			}

			private void open(File file) {
				try {
					FileInputStream fs = new FileInputStream(file);
					ObjectInputStream os = new ObjectInputStream(fs);
					Object one = os.readObject();
					if (one instanceof Game) {
						game = ((Game) one);
						multigame = null;

						c.removeAll();
						panel = new MyPanel();
						c.add(panel);
						panel.repaint();
						game.begin(panel);
					}
					if (one instanceof MultiGame) {
						multigame = ((MultiGame) one);
						game = null;

						c.removeAll();
						panel = new MyPanel();
						c.add(panel);
						panel.repaint();
						multigame.begin(panel);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		c1.add(button3);
		button3.setLocation(0, 240);

		Icon icon13 = new ImageIcon(MainFrame.class
				.getResource("spade Ace 1.png"));
		Icon icon23 = new ImageIcon(MainFrame.class
				.getResource("spade Ace 2.png"));
		Icon icon33 = new ImageIcon(MainFrame.class
				.getResource("spade Ace 3.png"));
		MyButton button4 = new MyButton(icon13, icon23, icon33);
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});

		c1.add(button4);
		button4.setLocation(0, 360);

		c1.repaint();
	}

	private class act1 implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			begin.iBegin = false;
			if (w != null) {
				w.setVisible(false);
			}
			String name = JOptionPane.showInputDialog(null, "CREATE A ROLE",
					"please enter your name");
			panel = new MyPanel();
			String num = "123456";

			String n = JOptionPane
					.showInputDialog(null, "MORE PLAYER",
							"please choose the number of other players (1-6 including the dealer)");
			if (n == null) {
				begin.iBegin = true;
				begin.go();
				w.setVisible(true);
				return;
			}

			if ((num.contains(n)) && n.length() == 1) {
				int i = Integer.parseInt(n);

				c.remove(begin);
				c.add(panel);
				panel.repaint();
				if ((name.equals("")) || (name == null))
					game = new Game(i);
				else {
					game = new Game(name, i);
				}
				multigame = null;
				game.begin(panel);
			} else {
				begin.iBegin = true;
				begin.go();
				JOptionPane.showMessageDialog(null, "wrong input");
				w.setVisible(true);
			}
		}
	}

	private class act2 implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (game != null || multigame != null) {
				String i = JOptionPane.showInputDialog(null, "SAVE",
						"please enter the name");
				if (i == null || i.equals("")) {
					return;
				}
				try {
					FileOutputStream fs = new FileOutputStream(i + ".blj");
					ObjectOutputStream os = new ObjectOutputStream(fs);
					if (game != null) {
						os.writeObject(game);
					}
					if (multigame != null) {
						os.writeObject(multigame);
					}
					os.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"you should start the game first");
			}
		}
	}

	private class act3 implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			JFileChooser jfc = new JFileChooser(new File("."));
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"BlackJackFile", "blj");
			jfc.setFileFilter(filter);
			if (jfc.showOpenDialog(null) == 0)
				open(jfc.getSelectedFile());
		}

		private void open(File file) {
			try {
				FileInputStream fs = new FileInputStream(file);
				ObjectInputStream os = new ObjectInputStream(fs);
				Object one = os.readObject();
				if (one instanceof Game) {
					game = ((Game) one);
					multigame = null;
					c.removeAll();
					panel = new MyPanel();
					c.add(panel);
					panel.repaint();
					game.begin(panel);
				}
				if (one instanceof MultiGame) {
					multigame = ((MultiGame) one);
					game = null;
					c.removeAll();
					panel = new MyPanel();
					c.add(panel);
					panel.repaint();
					multigame.begin(panel);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private class MyPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7874724124358612176L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image image = new ImageIcon("lib/background2.jpg").getImage();
			Image image2 = new ImageIcon("lib/card/back.PNG").getImage();
			g.drawImage(image, 0, 0, this);
			g.drawImage(image2, 1150, 0, this);
			if (game != null) {
				game.round.drawNew(g);
				game.round.drawAll(g);
			}
			if (multigame != null && multigame.round != null) {
				multigame.round.drawNew(g);
				multigame.round.drawAll(g);
			}
		}
	}

	private class RSound implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (musicList.size() != 0) {
				while (true) {
					int i = (int) (Math.random() * musicList.size());
					String s = "lib/sound/" + musicList.get(i);
					Sound sound;
					try {
						sound = new Sound(s);

						InputStream stream = new ByteArrayInputStream(sound
								.getSamples());
						// play the sound
						sound.play(stream);
					} catch (UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					}
				}
			}
		}

	}
}