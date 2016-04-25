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
	
	/**
	 * Initializes the ball.
	 */
	public Ball(double extraSpeed) {
		posX = Window.WIDTH / 2;
		posY = Window.HEIGHT / 2;
		size = 20;
		offset = size / 2;
		Random rnd = new Random();
		if (rnd.nextBoolean()) {
			velY = 0.15 * rnd.nextDouble() + extraSpeed;
		} else {
			velY = -0.15 * rnd.nextDouble() + extraSpeed;
		}
		if (rnd.nextBoolean()) {
			velX = 0.3 + extraSpeed;
		} else {
			velX = -0.3 - extraSpeed;
		}
		if (extraSpeed < 0) {
			ballBounce = 0.2;
		} else {
			ballBounce = 0.4;
		}
	}
	
	/**
	 * Update the ball's position and checks
	 * any collision with the pads or the environment.
	 * @param dt	Delta time.
	 * @param left	Left pad.
	 * @param right Right pad.
	 */
	public void update(double dt, Pad left, Pad right) {
		// Right pad collision detection.
		if ((posX >= right.getPosX()) && (posX <= Window.WIDTH - right.getOffset())) {
			if ((posY >= right.getPosY()) && (posY <= right.getPosY() + right.getSizeY())
					&& (velX > 0)) {
				velX = -velX;
				if ((posY >= right.getPosY() + 15) && (posY <= right.getPosY() + 50 - 15)) {
					;
				} else if ((posY >= right.getPosY()) && (posY <= right.getPosY() + 15)) {
					if (velY > 0) {
						velY = -ballBounce;
					}
				} else if ((posY >= right.getPosY() + 50 - 15) && (posY <= right.getPosY() + 50)) {
					if (velY < 0) {
						velY = ballBounce;
					}
				}
			}
		}
		// Left pad collision detection.
		if ((posX >= left.getOffset()) && (posX <= left.getOffset() + left.getSizeX())) {
			if ((posY >= left.getPosY()) && (posY <= left.getPosY() + 50)
					&& (velX < 0)) {
				velX = -velX;
				if ((posY >= left.getPosY() + 15) && (posY <= left.getPosY() + 50 - 15)) {
					;
				} else if ((posY >= left.getPosY()) && (posY <= left.getPosY() + 15)) {
					if (velY > 0) {
						velY = -ballBounce;
					}
				} else if ((posY >= left.getPosY() + 50 - 15) && (posY <= left.getPosY() + 50)) {
					if (velY < 0) {
						velY = ballBounce;
					}
				}
				
			}
		}
		// Wall collision detection.
		if ((posY <= 10) && (velY < 0))
			velY = -velY;
		if ((posY >= 480 - 10) && (velY > 0))
			velY = -velY;
		
		// Updates position relative to delta time.
		posX += dt * velX;
		posY += dt * velY;
	}
	
	/**
	 * Renders the ball on the screen.
	 * @param g Graphics object.
	 */
	public void render(Graphics g) {
		g.drawOval(posX - offset, posY - offset, size, size);
	}
	
	/**
	 * Get ball's X position.
	 * @return X position.
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * Get ball's Y position.
	 * @return Y position.
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Get the ball's size.
	 * @return Diameter.
	 */
	public int getSize() {
		return size;
	}
}