import org.newdawn.slick.Graphics;


public class Pad {
	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;
	private int offset;
	private double vel;
	
	public Pad(boolean leftOrRight, double padSpeed) {
		vel = padSpeed;
		sizeX = 20;
		sizeY = 50;
		offset = 10;
		if (leftOrRight) { // left
			posX = offset;
			posY = Window.HEIGHT / 2 - 25;
		} else { // right
			posX = Window.WIDTH - offset - sizeX;
			posY = Window.HEIGHT / 2 - (sizeY / 2);
		}
	}
	
	public void update(double dt, boolean up, boolean down) {
		if (up) {
			posY -= dt * vel;
		} else if (down) {
			posY += dt * vel;
		}
	}
	
	public void render(Graphics g) {
		g.drawRect(posX, posY, sizeX, sizeY);
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public int getOffset() {
		return offset;
	}
}
