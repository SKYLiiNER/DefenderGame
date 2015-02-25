import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Mutterschiff {
	protected int lives = 0;
	protected int DELAY = 500;
	private int delta;
	private int spawnrate = 1000;
	private LinkedList<Gegner> enemys;
	
	private int score = 0;
	private float multi = 1.0f;
	private long time = 0;
	private int multiCounter = 0;
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

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		
		
		g.setColor(Color.white);
		g.drawString("Score : " + score , 10, 5 );
		g.drawString("Multiplicator : " + nf.format(multi) , 600, 5 );
		g.drawString("Time : " + nf.format( (double)time/1000 ), 1080, 5 );
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
				score += 10 * multi;
				i.remove();
				multiCounter++;
				if(multiCounter == 10)
				{
					multi += (float)multiCounter/100;
					multiCounter = 0;
				}
			}
			//p.checkBulletCollision(e.getBullets());
			System.out.println(projektile);
			e.checkBulletCollision(projektile);

		}
		
		if(delta > spawnrate)
			{
				Gegner e = new Gegner();
				e.init(t);
				enemys.add( e );
				delta = 0;
				if (spawnrate > 500)
				spawnrate = spawnrate - 10;;
			}
		//muss noch überarbeitet werden
		
			time += t;		
	}
	
	
	// init with some random values
	public void init()
	{
		enemys = new LinkedList<Gegner>();
	}
}