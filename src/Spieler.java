import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class Spieler extends Waffe{

	@SuppressWarnings("unused")
	private Input mouseInput;
	private int DELAY = 100;
	private SpriteSheet spielerSheet;
	private Animation spieler;
	
	private Sound sound;
	
	public Spieler( Vector2f pos) throws SlickException {
		super(pos,100);
		init();
	}

	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
	    spielerSheet = new SpriteSheet("Dependency\\Images\\spieler.png", 25, 32);
	    spieler = new Animation(spielerSheet, 300);
	    sound = new Sound("Dependency\\shot.wav");
	}



	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		spieler.draw(100,400,60, 60);
		super.render(gc, g);
	}

	public void update(GameContainer gc, int t) throws SlickException {
		this.mouseInput = gc.getInput();
		super.update(gc, t);
		if(delta >DELAY && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && (gc.getInput().getMouseX() >200))
		{
			fireBullet(gc, new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()), new Projektil());
			sound.play();
		}
	}
}