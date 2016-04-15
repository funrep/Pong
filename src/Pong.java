import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class Pong {
	private GameState state;
	private Rectangle[] buttons;
	private int scoreRight;
	private int scoreLeft;
	private double padSpeed;
	private Ball ball;
	private Pad padLeft;
	private Pad padRight;
	private boolean spaceBar;
	
	public Pong() {
		state = GameState.MENU;
		buttons = new Rectangle[4];
		// Play button
		buttons[0] = new Rectangle(Window.WIDTH / 2 - 50, 200, 100, 20);
		// Controls button
		buttons[1] = new Rectangle(Window.WIDTH / 2 - 50, 225, 100, 20);
		// Options button
		buttons[2] = new Rectangle(Window.WIDTH / 2 - 50, 250, 100, 20);
		// Quit button
		buttons[3] = new Rectangle(Window.WIDTH / 2 - 50, 275, 100, 20);
		scoreRight = 0;
		scoreLeft = 0;
		padSpeed = 0.3;
		ball = new Ball();
		padLeft = new Pad(true, padSpeed);
		padRight = new Pad(false, padSpeed);
		spaceBar = false;
	}
	
	public void update(int dt, Input inp) {
		if (state == GameState.MENU) {
			int mouseX = inp.getMouseX();
			int mouseY = inp.getMouseY();
			if (inp.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				if (buttons[0].contains(mouseX, mouseY)) {
					// Play
					state = GameState.BEGIN;
				} else if (buttons[1].contains(mouseX, mouseY)) {
					// Controls
				} else if (buttons[2].contains(mouseX, mouseY)) {
					// Options
				} else if (buttons[3].contains(mouseX, mouseY)) {
					// Quit
					System.exit(0);
				}
			}
		} else if (state == GameState.BEGIN) {
			ball = new Ball();
			state = GameState.PAUSE;
		} else if (state == GameState.PLAY) { 
			boolean w = inp.isKeyDown(Input.KEY_W);
			boolean s = inp.isKeyDown(Input.KEY_S);
			padLeft.update(dt, w, s);
	
			boolean up = inp.isKeyDown(Input.KEY_UP);
			boolean down = inp.isKeyDown(Input.KEY_DOWN);
			padRight.update(dt, up, down);
			
			ball.update(dt, padLeft, padRight);
			if (ball.getPosX() > Window.WIDTH + ball.getSize()) {
				scoreRight += 1;
				state = GameState.BEGIN;
			} else if (ball.getPosX() < 0) {
				scoreLeft += 1;
				state = GameState.BEGIN;
			}
		} else if (state == GameState.PAUSE) {
			if (!spaceBar) {
				spaceBar = inp.isKeyDown(Input.KEY_SPACE);
				if (spaceBar) {
					state = GameState.PLAY;
					spaceBar = false;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if (state == GameState.MENU) {
			g.drawString("Pong", Window.WIDTH / 2 - 20, 100);
			g.drawString("Menu", Window.WIDTH / 2 - 20, 175);
			g.drawString("Play", Window.WIDTH / 2 - 20, 200);
			g.drawRect(buttons[0].getX(), buttons[0].getY(), 100, 20);
			g.drawString("Controls", Window.WIDTH / 2 - 35, 225);
			g.drawRect(buttons[1].getX(), buttons[1].getY(), 100, 20);
			g.drawString("Options", Window.WIDTH / 2 - 30, 250);
			g.drawRect(buttons[2].getX(), buttons[2].getY(), 100, 20);
			g.drawString("Quit", Window.WIDTH / 2 - 20, 275);
			g.drawRect(buttons[3].getX(), buttons[3].getY(), 100, 20);
		} else {
			g.drawString("Score: " + scoreLeft + " " + scoreRight,
					Window.WIDTH / 2 - 60, 20);
			padLeft.render(g);
			padRight.render(g);
			ball.render(g);
			if (state == GameState.PAUSE) {
				g.drawString("Press space to begin.", Window.WIDTH / 2 - 95,
						Window.HEIGHT - 50);
			}
		}
	}
}