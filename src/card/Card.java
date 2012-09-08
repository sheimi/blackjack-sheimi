package card;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7182600727680007200L;
	private static String[] cardSuits = { "heart", "spade", "diamond", "club" };
	public String cardSuit;
	public String cardValue;
	public int cardNumber;
	transient public BufferedImage cardImage;
	transient public BufferedImage cardBack;
	public boolean iNew = true;
	public int x = 1200, y = 0;
	public int finalX; public int finalY;

	public Card(int x, int y) {
		finalX = x;
		finalY = y;
		int i = (int) (Math.random() * 4);
		cardSuit = cardSuits[i]; // generate the cardSuit
		cardNumber = (int) (Math.random() * 13) + 1;
		generateCardValue();
		generateCardImage();
	}
	

	/*
	 * generate the value of the card
	 */
	public void generateCardValue() {
		switch (cardNumber) {
		case 1:
			cardValue = "Ace";
			break;
		case 2:
			cardValue = "Two";
			break;
		case 3:
			cardValue = "Three";
			break;
		case 4:
			cardValue = "Four";
			break;
		case 5:
			cardValue = "Five";
			break;
		case 6:
			cardValue = "Six";
			break;
		case 7:
			cardValue = "Seven";
			break;
		case 8:
			cardValue = "Eight";
			break;
		case 9:
			cardValue = "Nine";
			break;
		case 10:
			cardValue = "Ten";
			break;
		case 11:
			cardValue = "Jack";
			cardNumber = 10;
			break;
		case 12:
			cardValue = "Queen";
			cardNumber = 10;
			break;
		case 13:
			cardValue = "King";
			cardNumber = 10;
			break;
		}
	}

	/*
	 * to set the face and back of the card
	 */
	public void generateCardImage() {
		String i1 = "lib/card/" + cardSuit + "/" + cardSuit + " "
				+ cardValue + ".PNG";
		String i2 = "lib/card/back2.PNG";
		
		File f1 = new File(i1);
		File f2 = new File(i2);
		
		try {
			cardImage = ImageIO.read(f1);
			cardBack = ImageIO.read(f2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(cardValue);
			System.out.println(cardSuit);
		}
	}

	/*
	 * to show the card's face on the table
	 */
	public void showCard(Graphics g, int x, int y) {
		g.drawImage(cardImage, x, y, null);
	}

	public void showCard(Graphics g, int x, int y, int width, int height) {
		g.drawImage(cardImage, x, y, width, height, null);
	}

	/*
	 * to show the card's back on the table
	 */
	public void showBack(Graphics g, int x, int y) {
		g.drawImage(cardBack, x, y, null);
	}

	public void showBack(Graphics g, int x, int y, int width, int height) {
		g.drawImage(cardBack, x, y, width, height, null);
	}
	
	public BufferedImage rotateImage(BufferedImage bufferedimage,
            int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}
