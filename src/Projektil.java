import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Projektil{

	private String pBulletImage = "Dependency\\Images\\fireball.png";
	public Image bulletImage;
	private Input mouseInput;	
	private float mouseX;
	private float mouseY;
	private float xDistance;
	private float yDistance;
	private static float position_X = 100;
	private static float position_Y = 400;
	
	protected Vector2f pos;
	protected Vector2f dir;
	protected Vector2f fixedEnemyPos;
	protected int lived = 0;
	
	protected boolean aktiv = true;
	
	protected int MAX_LIFETIME = 10000;
	protected int DAMAGE = 10;
	protected int RADIUS_SQARED = 360;
	
	
	public Projektil ( Vector2f pos, Vector2f dir ) throws SlickException
	{
		bulletImage = new Image(pBulletImage);
		this.pos = pos;
		this.dir = dir;
		dir.scale(500);
	}
	
	public Projektil init (GameContainer gc, Vector2f pos, Vector2f dir ) throws SlickException
	{
		bulletImage = new Image(pBulletImage);
		this.pos = pos;
		this.dir = dir;
		
		dir.scale(500);
		setAktiv(true);
		
		this.mouseInput = gc.getInput();
		mouseX = mouseInput.getMouseX();
		mouseY = mouseInput.getMouseY();
		xDistance = mouseX - position_X;
		yDistance = mouseY - position_Y;
		float angleToTurn = (float)Math.toDegrees(Math.atan2(yDistance , xDistance));
		bulletImage.setRotation( angleToTurn);
		return this;
	}
	
	public Projektil ()
	{
		aktiv = false;
	}
	
	public void update(GameContainer gc, int t )
	{
		if( aktiv )
		{
			Vector2f realSpeed = dir.copy();
			realSpeed.scale( (t/500.0f) );
			pos.add( realSpeed );
			
			lived += t;
			if( lived > MAX_LIFETIME ) aktiv = false;

		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		if( aktiv )
		{
			bulletImage.drawCentered(pos.getX(), pos.getY());
		}
	}
	
	public void setAktiv( boolean aktiv )
	{
		this.aktiv = aktiv;
	}
	
	public int getDamage()
	{
		return DAMAGE;
	}

	
	public boolean collideWith ( Vector2f otherPos , int otherRadiusSqared )
	{
		fixedEnemyPos = new Vector2f(otherPos.getX()+30, otherPos.getY()+30);
		int dis = (int) fixedEnemyPos.copy().sub(pos).lengthSquared();
		
		if( dis < ( otherRadiusSqared + RADIUS_SQARED ) )
		{
			return true;
		}
		return false;
	}
	
	
	public boolean isAktiv()
	{
		return aktiv;
	}
	
}