import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Spielfeld extends BasicGame {

    Image bg, gameover;
    Spieler sp;
    Mutterschiff ms;
    static AppGameContainer appgc;
    boolean GameState;
    int countdown = 10000;
    Color rot = new Color(255, 0, 0);

    public Spielfeld(String gamename) {
	super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

	countdown = 10000;
	bg = new Image("Dependency\\Images\\background.png");
	gameover = new Image("Dependency\\Images\\GameOver.png");
	gc.setMaximumLogicUpdateInterval(60);
	gc.setTargetFrameRate(60);
	gc.setAlwaysRender(true);
	gc.setShowFPS(false);
	gc.setVSync(true);
	GameState = true;
	sp = new Spieler(new Vector2f(124, 422));
	sp.init(gc);
	ms = new Mutterschiff();

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMinimumFractionDigits(2);
	nf.setMaximumFractionDigits(2);

	if (GameState) {
	    g.drawImage(bg, 0, 0);
	    g.setLineWidth(5);
	    g.setColor(rot);
	    g.drawLine(210, 0, 210, 780);
	    g.drawString(" <== Do not let them cross this LINE !! ", 215, 5);
	    sp.render(gc, g);
	    ms.render(gc, g);
	} else {
	    g.drawImage(gameover, 0, 0);
	    g.setColor(rot);
	    g.drawString("GAME OVER! ", 570, 390);
	}

    }

    @Override
    public void update(GameContainer gc, int t) throws SlickException {
	// TODO Auto-generated method stub

	if (GameState) {
	    sp.update(gc, t);
	    ms.update(gc, t, sp.bullets);
	    checkGameOver();
	}

    }

    public static void main(String[] args) {
	try {
	    appgc = new AppGameContainer(new Spielfeld("Simple Slick Game"));
	    appgc.setDisplayMode(1260, 780, false);
	    appgc.start();
	} catch (SlickException ex) {
	    Logger.getLogger(Spielfeld.class.getName()).log(Level.SEVERE, null,
		    ex);
	}
    }

    public void checkGameOver() {
	for (Gegner g : ms.enemys) {
	    if (g.startPos.getX() < 200)
		GameState = false;
	}
    }
}
