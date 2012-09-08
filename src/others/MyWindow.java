package others;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NewPanel panel;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public MyWindow() {
		super();
		this.setUndecorated(true);
		this.setBounds((screenSize.width - 600)/2, (screenSize.height - 480)/2, 600,
				480);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		panel = new NewPanel();
		this.getContentPane().add(panel);
		// this.setSize(600, 480);
		// this.setLocation(400, 200);

	}

	public JPanel getPanel() {
		return panel;
	}

	class NewPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image image = new ImageIcon("lib/12.jpg").getImage();
			g.drawImage(image, 0, 0, this);
		}
	}
}
