import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class Pong {
	private GameState state;
	private Rectangle[] buttons;
	private int scoreRight;
	private int scoreLeft;
	private double padSpeed;
	private Pad padLeft;
	private Pad padRight;
	private double extraBallSpeed;
	private Ball ball;
	private boolean spaceBar;
	private String winner;
	
	/**
	 * Initializes the game and spawns the menu.
	 */
	public Pong() {
		state = GameState.MENU;
		buttons = new Rectangle[5];
		buttons[0] = new Rectangle(Window.WIDTH / 2 - 50, 200, 100, 20);
		buttons[1] = new Rectangle(Window.WIDTH / 2 - 50, 225, 100, 20);
		buttons[2] = new Rectangle(Window.WIDTH / 2 - 50, 250, 100, 20);
		buttons[3] = new Rectangle(Window.WIDTH / 2 - 50, 275, 100, 20);
		buttons[4] = new Rectangle(Window.WIDTH / 2 - 50, 300, 100, 20);
		scoreRight = 0;
		scoreLeft = 0;
		padSpeed = 0.3;
		extraBallSpeed = 0;
		ball = new Ball(extraBallSpeed);
		padLeft = new Pad(true, padSpeed);
		padRight = new Pad(false, padSpeed);
		spaceBar = false;
		winner = "";
	}
	
	/**
	 * Updates the game depending on which state its currently in.
	 * Movement functions (Pad.update and Ball.update) depends on delta time,
	 * which is the time elapsed between each rendered frame, this results in
	 * FPS-independent (Frames per second) updates so the game can be optimized
	 * for different performing computers.
	 * @param dt	Delta time.
	 * @param inp 	Input devices.
	 */
	public void update(int dt, Input inp) {
		int mouseX = inp.getMouseX();
		int mouseY = inp.getMouseY();
		boolean w = inp.isKeyDown(Input.KEY_W);
		boolean s = inp.isKeyDown(Input.KEY_S);
		boolean up = inp.isKeyDown(Input.KEY_UP);
		boolean down = inp.isKeyDown(Input.KEY_DOWN);
		// Menu
		if (state == GameState.MENU) {
			if (inp.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				if (buttons[0].contains(mouseX, mouseY)) {
					// Play
					state = GameState.BEGIN;
				} else if (buttons[1].contains(mouseX, mouseY)) {
					// Controls
					state = GameState.CONTROLS;
				} else if (buttons[2].contains(mouseX, mouseY)) {
					// Options
					state = GameState.OPTIONS;
				} else if (buttons[4].contains(mouseX, mouseY)) {
					// Quit
					System.exit(0);
				}
			}
		// Controls
		} else if (state == GameState.CONTROLS) {
			if (inp.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				if (buttons[3].contains(mouseX, mouseY)) {
					// Back
					state = GameState.MENU;
				}
			}
		// Options
		} else if (state == GameState.OPTIONS) {
			if (inp.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				if (buttons[0].contains(mouseX, mouseY)) {
					// Easy
					extraBallSpeed = -0.1;
				} else if (buttons[1].contains(mouseX, mouseY)) {
					// Normal
					extraBallSpeed = 0;
				} else if (buttons[2].contains(mouseX, mouseY)) {
					// Insane
					extraBallSpeed = 0.15;
				} else if (buttons[3].contains(mouseX, mouseY)) {
					// Back
					state = GameState.MENU;
				}
			}
		// Special state to spawn a new ball, ie start a new game.
		} else if (state == GameState.BEGIN) {
			ball = new Ball(extraBallSpeed);
			state = GameState.PAUSE;
		// In-game
		} else if (state == GameState.PLAY) { 
			padLeft.update(dt, w, s);
			padRight.update(dt, up, down);
			
			ball.update(dt, padLeft, padRight);
			if (ball.getPosX() > Window.WIDTH + ball.getSize()) {
				scoreLeft += 1;
				state = GameState.BEGIN;
			} else if (ball.getPosX() < 0) {
				scoreRight += 1;
				state = GameState.BEGIN;
			}
			if (scoreRight == 10 || scoreLeft == 10) {
				state = GameState.RESULTS;
			}
		// Pause, happens when the game starts
		} else if (state == GameState.PAUSE) {
			if (!spaceBar) {
				spaceBar = inp.isKeyDown(Input.KEY_SPACE);
				if (spaceBar) {
					state = GameState.PLAY;
					spaceBar = false;
				}
			}
		// Results
		} else if (state == GameState.RESULTS) {
			if (scoreRight == 10) {
				winner = "right side won!";
			} else {
				winner = "left side won!";
			}
			if (inp.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				if (buttons[3].contains(mouseX, mouseY)) {
					// Back
					state = GameState.MENU;
				}
			}
		}
	}
	
	/**
	 * Render the game depending on the current state.
	 * @param g Graphics object.
	 */
	public void render(Graphics g) {
		// Menu
		if (state == GameState.MENU) {
			g.drawString("Pong", Window.WIDTH / 2 - 20, 100);
			g.drawString("Menu", Window.WIDTH / 2 - 20, 175);
			g.drawString("Play", Window.WIDTH / 2 - 20, 200);
			g.drawRect(buttons[0].getX(), buttons[0].getY(), 100, 20);
			g.drawString("Controls", Window.WIDTH / 2 - 35, 225);
			g.drawRect(buttons[1].getX(), buttons[1].getY(), 100, 20);
			g.drawString("Options", Window.WIDTH / 2 - 30, 250);
			g.drawRect(buttons[2].getX(), buttons[2].getY(), 100, 20);
			g.drawString("Quit", Window.WIDTH / 2 - 20, 300);
			g.drawRect(buttons[4].getX(), buttons[4].getY(), 100, 20);
		// Controls
		} else if (state == GameState.CONTROLS) {
			g.drawString("Pong", Window.WIDTH / 2 - 20, 100);
			g.drawString("Menu", Window.WIDTH / 2 - 20, 175);
			g.drawString("Player on the left, W and S.",
					Window.WIDTH / 2 - 130, 200);
			g.drawString("Player on the right, UP and DOWN.",
					Window.WIDTH / 2 - 150, 225);
			g.drawString("Back", Window.WIDTH / 2 - 20, 275);
			g.drawRect(buttons[3].getX(), buttons[3].getY(), 100, 20);
		// Options
		} else if (state == GameState.OPTIONS) {
				g.drawString("Pong", Window.WIDTH / 2 - 20, 100);
				g.drawString("Menu", Window.WIDTH / 2 - 20, 175);
				g.drawString("Easy", Window.WIDTH / 2 - 20, 200);
				g.drawRect(buttons[0].getX(), buttons[0].getY(), 100, 20);
				g.drawString("Normal", Window.WIDTH / 2 - 28, 225);
				g.drawRect(buttons[1].getX(), buttons[1].getY(), 100, 20);
				g.drawString("Insane", Window.WIDTH / 2 - 30, 250);
				g.drawRect(buttons[2].getX(), buttons[2].getY(), 100, 20);
				g.drawString("Back", Window.WIDTH / 2 - 20, 275);
				g.drawRect(buttons[3].getX(), buttons[3].getY(), 100, 20);
		// Results
		} else if (state == GameState.RESULTS) {
			g.drawString("Congratulations,",
					Window.WIDTH / 2 - 70, 200);
			g.drawString(winner,
					Window.WIDTH / 2 - 70, 225);
			g.drawString("Back", Window.WIDTH / 2 - 20, 275);
			g.drawRect(buttons[3].getX(), buttons[3].getY(), 100, 20);
		// Playing
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