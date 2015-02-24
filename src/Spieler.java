import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.*;

public class Spieler extends Waffe{

	//private int score;
	//private int animation;
	private Image playerImage;
	private Input mouseInput;
	private float mouseX;
	private float mouseY;
	private float xDistance;
	private float yDistance;
	private static float position_X = 100;
	private static float position_Y = 400;
	private int DELAY = 100;
	public Spieler( Vector2f pos) throws SlickException {
		super(pos,100);
		init();
	}

	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		playerImage = new Image("C:\\Users\\Sebastian\\Documents\\Studium\\DefenderGame\\Dependency\\imagesPlayer\\ak47;m4_klkein.png");
	}



	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		playerImage.drawCentered(100,400);
		super.render(gc, g);
	}

	public void update(GameContainer gc, int t) throws SlickException {
		this.mouseInput = gc.getInput();
		mouseX = mouseInput.getMouseX();
		mouseY = mouseInput.getMouseY();
		xDistance = mouseX - position_X;
		yDistance = mouseY - position_Y;
		float angleToTurn = (float)Math.toDegrees(Math.atan2(yDistance , xDistance));
		playerImage.setRotation( angleToTurn);
		super.update(gc, t);
		if(delta >DELAY && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && (gc.getInput().getMouseX() >200))
		{
			fireBullet(gc, new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()), new Projektil());
		}
	}
}