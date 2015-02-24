import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Waffe {

	public Image bulletImage;
	protected Projektil[] bullets;
	//private static int FIRE_RATE = 250;
	private int currentIndex = 0;
	protected Vector2f pos = new Vector2f(124,426);
	protected int delta = 0; // delat Time since the last shoot
	
	public Waffe(Vector2f pos, int maxBullets ) {
		this.pos = pos;
		bullets = new Projektil[maxBullets];
		for( int i = 0 ; i < bullets.length ; i++  )
		{
			bullets[i] = new Projektil();
		}
	}
	public void init() throws SlickException {
		// TODO Auto-generated method stub
		bullets = new Projektil[10000];
		for(int i = 0;i<bullets.length;i++)
		{
			bullets[i] = new Projektil();
		}
	}
	
	public void fireBullet (GameContainer gc, Vector2f vec , Projektil b) throws SlickException
	{
		// Delta to 0, because we have just shot
		delta = 0;
		// Get the normalised direction ( Vector in that direction with lenght 1 )
		vec.sub(pos);
		vec.normalise();
		// Add the new bullet
		bullets[currentIndex] = b.init(gc, pos.copy(),vec);
		currentIndex++; 
		if( currentIndex >= bullets.length ) currentIndex = 0;
	}
	
	
	public Projektil[] getProjektile()
	{
		System.out.println("Projektil");
		return bullets;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		for(Projektil b : bullets)
		{
			b.render(gc, g);
		}

	}
	public void update(GameContainer gc, int t) throws SlickException {
		delta +=t;
		
		for(Projektil b : bullets)
		{
			b.update(gc ,t);
		}
	}
}