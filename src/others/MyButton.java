package others;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.JButton;

import sound.Sound;

public class MyButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton t;

	public MyButton(final Icon icon1, final Icon icon2, final Icon icon3) {
		super(icon1);

		this.setFocusable(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		t = this;
		this.setSize(600, 120);
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setIcon(icon1);
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setIcon(icon3);
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setIcon(icon2);
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				t.setIcon(icon2);
				Runnable r = new R();
				Thread t = new Thread(r);
				t.start();
				repaint();
			}
		});

	}

	class R implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Sound sound;
			try {
				sound = new Sound("lib/sound/Track 2.wav");
				InputStream stream = new ByteArrayInputStream(sound
						.getSamples());
				sound.play(stream);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}