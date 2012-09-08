package others;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyLabel(String i) {
		super(i);
		Font font = new Font("Colonna MT", Font.BOLD, 25);
		this.setFont(font);
		this.setForeground(Color.yellow);
	}
}
