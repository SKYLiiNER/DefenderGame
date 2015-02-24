import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Mutterschiff {

	private float SPAWN_CHANCE = 8;
	private int counter;
	protected int lives = 0;
	protected int DELAY = 500;
	private int delta;
	private LinkedList<Gegner> enemys;
	private Random r;
	
	// we need to know where the player is  
	
	public Mutterschiff ()
	{
		this.init();
	}
	
	// super + draw the enemy
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		for(Gegner enemy : enemys)
		{

			System.out.println("render");
			enemy.render(gc, g);
		}
	}
	
	// super + fire bullets with the given fire rate
	// + mouve towards the player
	public void update(GameContainer gc, int t, Projektil[] projektile) throws SlickException 
	{
		delta += t;		
		Iterator<Gegner> i = enemys.iterator();
		while( i.hasNext() )
		{
			System.out.println("gegner updaten");
			Gegner e = i.next();
			e.update(gc, t);
			// remove enemys that are dead or at the  player position
			// the player position is necessary because the enemys continue spawning even if the player is dead 
			// -> there would be unlimited without
			if( !e.isAlive() )
			{
				System.out.println("gegner löschen");
				//score += 10 * multi;
				i.remove();
			}
			//p.checkBulletCollision(e.getBullets());
			System.out.println(projektile);
			e.checkBulletCollision(projektile);
			
		}
		if(delta > 1000)
			{
				Gegner e = new Gegner();
				e.init(t);
				enemys.add( e );
				delta = 0;
			}
	}
	
	
	// init with some random values
	public void init()
	{
		enemys = new LinkedList<Gegner>();
	}
}