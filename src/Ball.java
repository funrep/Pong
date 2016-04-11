import java.util.Random;

import org.newdawn.slick.Graphics;


public class Ball {
	private int posX;
	private int posY;
	private double velX;
	private double velY;
	private int size;
	private int offset;
	private double ballBounce;
	
	public Ball() {
		posX = Window.WIDTH / 2;
		posY = Window.HEIGHT / 2;
		size = 20;
		offset = size / 2;
		Random rnd = new Random();
		velY = 0.2 * rnd.nextDouble();
		if (velY > 0) {
			velX = 0.3;
		} else {
			velX = -0.3;
		}
		ballBounce = 0.4;
	}
	
	public void update(double dt, Pad left, Pad right) {
		if ((posX >= 640 - 10 - 20 - 10) && (posX <= 640)) {
			if ((posY >= right.getPosY()) && (posY <= right.getPosY() + 50)) {
				velX = -velX;
				if ((posY >= right.getPosY() + 15) && (posY <= right.getPosY() + 50 - 15)) {
					;
				} else if ((posY >= right.getPosY()) && (posY <= right.getPosY() + 15)) {
					velY = ballBounce;
				} else if ((posY >= right.getPosY() + 50 - 15) && (posY <= right.getPosY() + 50)) {
					velY = -ballBounce;
				}
				
			}
		}
		if ((posX >= 0) && (posX <= 40)) {
			if ((posY >= left.getPosY()) && (posY <= left.getPosY() + 50)) {
				velX = -velX;
				if ((posY >= left.getPosY() + 15) && (posY <= left.getPosY() + 50 - 15)) {
					;
				} else if ((posY >= left.getPosY()) && (posY <= left.getPosY() + 15)) {
					velY = ballBounce;
				} else if ((posY >= left.getPosY() + 50 - 15) && (posY <= left.getPosY() + 50)) {
					velY = -ballBounce;
				}
				
			}
		}
		if (posY <= 10)
			velY = -velY;
		if (posY >= 480 - 10)
			velY = -velY;
		
		posX += dt * velX;
		posY += dt * velY;
	}
	
	public void render(Graphics g) {
		g.drawOval(posX - offset, posY - offset, size, size);
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
}
