import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
public class Default extends KeyAdapter
{
	public int x, y;
	public Rectangle hitBox;
	public char direction;
	private Mario player;
	public Default(int x, int y) 
	{
		this.x = x;
		this.y = y;
		direction = 'N';
		hitBox = new Rectangle(this.x, this.y, 35, 35);
	}
	
    public void keyPressed(KeyEvent e) 
    {
        player.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e) 
    {
        player.keyReleased(e);
    }
    
    public Default(Mario player) 
    {
        this.player = player;
    }
}