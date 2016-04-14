import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame
{
	Pong pongGame;

	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		pongGame = new Pong();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		pongGame.update(delta, gc.getInput());
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		pongGame.render(g);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Pong"));
			appgc.setDisplayMode(Window.WIDTH, Window.HEIGHT, false);
			appgc.setTargetFrameRate(Window.FPS);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
