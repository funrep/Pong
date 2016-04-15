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
		if (rnd.nextBoolean()) {
			velY = 0.15 * rnd.nextDouble();
		} else {
			velY = 0.15 * rnd.nextDouble();
		}
		if (rnd.nextBoolean()) {
			velX = 0.3;
		} else {
			velX = -0.3;
		}
		ballBounce = 0.4;
	}
	
	public void update(double dt, Pad left, Pad right) {
		if ((posX >= right.getPosX()) && (posX <= Window.WIDTH - right.getOffset())) {
			if ((posY >= right.getPosY()) && (posY <= right.getPosY() + right.getSizeY())
					&& (velX > 0)) {
				velX = -velX;
				if ((posY >= right.getPosY() + 15) && (posY <= right.getPosY() + 50 - 15)) {
					;
				} else if ((posY >= right.getPosY()) && (posY <= right.getPosY() + 15)) {
					velY = -ballBounce;
				} else if ((posY >= right.getPosY() + 50 - 15) && (posY <= right.getPosY() + 50)) {
					velY = ballBounce;
				}
			}
		}
		if ((posX >= left.getOffset()) && (posX <= left.getOffset() + left.getSizeX())) {
			if ((posY >= left.getPosY()) && (posY <= left.getPosY() + 50)
					&& (velX < 0)) {
				velX = -velX;
				if ((posY >= left.getPosY() + 15) && (posY <= left.getPosY() + 50 - 15)) {
					;
				} else if ((posY >= left.getPosY()) && (posY <= left.getPosY() + 15)) {
					velY = -ballBounce;
				} else if ((posY >= left.getPosY() + 50 - 15) && (posY <= left.getPosY() + 50)) {
					velY = ballBounce;
				}
				
			}
		}
		if ((posY <= 10) && (velY < 0))
			velY = -velY;
		if ((posY >= 480 - 10) && (velY > 0))
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
	
	public int getSize() {
		return size;
	}
}
