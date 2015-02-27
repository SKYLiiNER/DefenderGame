import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Gegner {

	private float health;
	private int speed = 15;
	public Vector2f startPos; 
	private Vector2f endPos;
	private Vector2f realSpeed;
	private int spawn;
	
	//private Image enemyImage;
	private SpriteSheet enemySheet;
	private Animation enemyAnimation;
	

	
	private boolean alive = true;
	
	protected int RADIUS_SQARED = 600;
	
	public Gegner (/*int enemyCounter*/) throws SlickException
	{
		//this.enemyCounter = enemyCounter;
	}
	
	// super + draw the enemy
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		//enemyImage.drawCentered(startPos.getX(), startPos.getY());
		enemyAnimation.draw(startPos.getX(), startPos.getY(), 60, 60);
//	    	Image image = enemyAnimation.getCurrentFrame();
//	    	image.setRotation(90);
//	    	image.draw(startPos.getX(), startPos.getY(), 60, 60);

	}
	
	// super + fire bullets with the given fire rate
	// + mouve towards the player
	public void update(GameContainer gc, int t) throws SlickException 
	{
		startPos.add( realSpeed );
		
	}
	
	// init with some random values
	public void init(float multiplikator) throws SlickException 
	{
		Random r = new Random();
		spawn = r.nextInt(700);
		startPos = new Vector2f(1270, spawn);
		endPos = new Vector2f(210, spawn);
		endPos.sub(startPos).normalise(); 
		realSpeed = new Vector2f((endPos.getX()-startPos.getX()),0);
		realSpeed.scale( (speed/10000.0f) );
		//enemyImage = new Image("D:\\Downloads\\SE\\DefenderGame\\Dependency\\Zombie-D - Kopie.png");
		enemySheet = new SpriteSheet("Dependency\\Zombie-D.png",32,32);
		enemyAnimation = new Animation(enemySheet, 100);
		
		health = 10;
	}
	
	public void checkBulletCollision ( Projektil[] projektile ) throws SlickException 
	{
		for( Projektil p : projektile )
		{
			if ( p.isAktiv() && p.collideWith(startPos, RADIUS_SQARED) )
			{
				// Delete the bullet if it hits something
				p.setAktiv(false);
				health -= p.getDamage();
				if( health < 1 && alive) die();
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