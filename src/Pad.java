import org.newdawn.slick.Graphics;

public class Pad {
	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;
	private int offset;
	private double vel;

	/**
	 * Initialize a Pad.
	 * @param leftOrRight	True is left, false is right.
	 * @param padSpeed		Movement speed of the pad.
	 */
	public Pad(boolean leftOrRight, double padSpeed) {
		vel = padSpeed;
		sizeX = 20;
		sizeY = 50;
		offset = 10;
		if (leftOrRight) { // left
			posX = offset;
			posY = Window.HEIGHT / 2 - (sizeY / 2);
		} else { // right
			posX = Window.WIDTH - offset - sizeX;
			posY = Window.HEIGHT / 2 - (sizeY / 2);
		}
	}
	
	/**
	 * Move the pad up and down, and checks
	 * if it has crossed the screen.
	 * @param dt	Delta time.
	 * @param up	Should it move up or not.
	 * @param down	Should it move down or not.
	 */
	public void update(double dt, boolean up, boolean down) {
		// Update positions relative to delta time.
		if (up) {
			posY -= dt * vel;
		} else if (down) {
			posY += dt * vel;
		}
		// If the pad's gets out of the screen,
		// move it to the other side.
		if (posY > Window.HEIGHT) {
			posY = 20;
		} else if (posY < 0) {
			posY = Window.HEIGHT - 20;
		}
	}
	
	/**
	 * Render the pad on the screen as a rectangle.
	 * @param g	Graphics object.
	 */
	public void render(Graphics g) {
		g.drawRect(posX, posY, sizeX, sizeY);
	}
	
	/**
	 * Get pad's X position.
	 * @return X position.
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * Get pad's Y position.
	 * @return Y position.
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Set pad's Y position.
	 * @param Y position.
	 */
	public void setPosY(int y) {
		posY = y;
	}
	
	/**
	 * Get pad's X-side's length.
	 * @return X size.
	 */
	public int getSizeX() {
		return sizeX;
	}
	
	/**
	 * Get pad's Y-side's length.
	 * @return Y size.
	 */
	public int getSizeY() {
		return sizeY;
	}
	
	/**
	 * Get pad's offset to the wall.
	 * @return Offset.
	 */
	public int getOffset() {
		return offset;
	}
}