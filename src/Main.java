import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Initialize main class extending Slick2D's BasicGame.
 * Slick2D takes care of:
 * + Game loop (ie calling Main.update and Main.render at specified FPS).
 * + Input from mouse and keyboard.
 * + Opening a window with a graphics context.
 * + Drawing figures in the graphics context.
 */
public class Main extends BasicGame
{
	// All program state is in the Pong object.
	Pong pongGame;

	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// Initialize the game.
		pongGame = new Pong();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// Update the game.
		pongGame.update(delta, gc.getInput());
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		// Render the game.
		pongGame.render(g);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			// Specify the window's title.
			appgc = new AppGameContainer(new Main("Pong"));
			// Set window's width and height.
			appgc.setDisplayMode(Window.WIDTH, Window.HEIGHT, false);
			// Set frames per second.
			appgc.setTargetFrameRate(Window.FPS);
			// Start the game.
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
