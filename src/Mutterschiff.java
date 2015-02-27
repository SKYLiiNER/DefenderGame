import java.awt.Font;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

public class Mutterschiff {
    protected int lives = 0;
    protected int DELAY = 500;
    private int delta;
    private int spawnrate = 1000;
    public LinkedList<Gegner> enemys;

    protected int score = 0;
    private int framecount = 0;
    private float multi = 1.0f;
    private long time = 0;
    private int multiCounter = 0;

    private boolean enemyHit = false;
    private Vector2f hitPos;
    private SpriteSheet explosionSheet;
    private Animation explosion;
    private TrueTypeFont ufont;

    // we need to know where the player is

    public Mutterschiff() throws SlickException {
	this.init();
    }

    // super + draw the enemy
    public void render(GameContainer gc, Graphics g) throws SlickException {
	for (Gegner enemy : enemys) {
	    enemy.render(gc, g);
	}

	NumberFormat nf = NumberFormat.getInstance();
	nf.setMinimumFractionDigits(2);
	nf.setMaximumFractionDigits(2);

	if (enemyHit) {
	    explosion.draw(hitPos.getX(), hitPos.getY(), 100, 100);
	    framecount++;
	    if (framecount == 40) {
		enemyHit = false;
		framecount = 0;
	    }
	}

	g.setColor(Color.white);
	g.setFont(ufont);
	g.drawString("Score : " + score, 10, 5);
	g.drawString("Multiplikator : " + nf.format(multi) , 650, 5 );
	g.drawString("Time : " + nf.format((double) time / 1000), 1080, 5);
    }

    // super + fire bullets with the given fire rate
    // + mouve towards the player
    public void update(GameContainer gc, int t, Projektil[] projektile)
	    throws SlickException {
	delta += t;
	Iterator<Gegner> i = enemys.iterator();
	while (i.hasNext()) {
	    Gegner e = i.next();
	    e.update(gc, t);
	    if (!e.isAlive()) {
		score += 10 * multi;
		hitPos = e.startPos;
		enemyHit = true;
		explosion.restart();
		i.remove();
		multiCounter++;
		if (multiCounter == 2) {
		    multi += (float) 10 / 100;
		    multiCounter = 0;
		}
	    }
	    e.checkBulletCollision(projektile);

	}

	if (delta > spawnrate) {
	    Gegner e = new Gegner();
	    e.init(multi);
	    enemys.add(e);
	    delta = 0;
	    System.out.println(spawnrate);
	    if (spawnrate > 500)
		spawnrate = spawnrate - 5;
	    ;
	}
	// muss noch überarbeitet werden

	time += t;
    }

    // init with some random values
    public void init() throws SlickException {
	enemys = new LinkedList<Gegner>();
	explosionSheet = new SpriteSheet("Dependency\\Images\\explode.png",23, 23);
	explosion = new Animation(explosionSheet, 100);
	Font font = new Font("Arial", Font.BOLD, 20);
	ufont = new TrueTypeFont(font, false);
    }
}