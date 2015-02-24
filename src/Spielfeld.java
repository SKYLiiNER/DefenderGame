import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.*;

public class Spielfeld extends BasicGame {

	Image bg;
	Spieler sp;
	Mutterschiff ms;
	
	public Spielfeld(String gamename)
	{
		super(gamename);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
        bg = new Image("C:\\Users\\Sebastian\\Documents\\Studium\\DefenderGame\\Dependency\\Images\\background.png");
		gc.setMaximumLogicUpdateInterval(60);
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		gc.setVSync(true);
		
		sp = new Spieler(new Vector2f(124,422));
		sp.init(gc);
		ms = new Mutterschiff();
	}


	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(bg, 0, 0);
		sp.render(gc, g);
		ms.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		// TODO Auto-generated method stub
		sp.update(gc, t);
		ms.update(gc, t, sp.bullets);
	}
	
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Spielfeld("Simple Slick Game"));
			appgc.setDisplayMode(1260, 780 , false);
			appgc.start();
		}
		catch(SlickException ex)
		{
			Logger.getLogger(Spielfeld.class.getName()).log(Level.SEVERE,null,ex);
		}
	}

}
