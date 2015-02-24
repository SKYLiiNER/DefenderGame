import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Gegner {

	private int health;
	private int animation;
	private int enemyCounter = 0;
	private Vector2f pos;
	private Vector2f startPos; 
	private Vector2f endPos;
	private Vector2f realSpeed;
	private int spawn;
	private Image enemyImage;
	private boolean alive = true;
	
	protected int RADIUS_SQARED = 3600;
	
	public Gegner (/*int enemyCounter*/) throws SlickException
	{
		//this.enemyCounter = enemyCounter;
	}
	
	// super + draw the enemy
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		enemyImage.draw(startPos.getX(), startPos.getY());
	}
	
	// super + fire bullets with the given fire rate
	// + mouve towards the player
	public void update(GameContainer gc, int t) throws SlickException 
	{
		startPos.add( realSpeed );
		
	}
	
	// init with some random values
	public void init(int t) throws SlickException 
	{
		Random r = new Random();
		spawn = r.nextInt(700);
		startPos = new Vector2f(1270, spawn);
		endPos = new Vector2f(210, spawn);
		endPos.sub(startPos).normalise(); 
		realSpeed = new Vector2f((endPos.getX()-startPos.getX()),0);
		realSpeed.scale( (t/10000.0f) );
		enemyImage = new Image("C:\\Users\\Sebastian\\Documents\\Studium\\DefenderGame\\Dependency\\Zombie-D - Kopie.png");
		health = 1 + r.nextInt(10);
	}
	
	public void checkBulletCollision ( Projektil[] projektile )
	{
		System.out.println("checkbo");
		for( Projektil p : projektile )
		{
			if ( p.isAktiv() && p.collideWith(startPos, RADIUS_SQARED) )
			{
				// Delete the bullet if it hits something
				p.setAktiv(false);
				health -= p.getDamage();
				//if( health < 1 && alive) die();
			}	
		}
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public void die()
	{
		alive = false;
	}
}