
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.*;
public class Fireball extends Default
{
    private int velX = 0;
    private int velY = 0;
    private Image image;
    public Fireball(int x, int y, char direction)
    {
        super(x,y);
        this.direction = direction;
        hitBox = new Rectangle(this.x, this.y, 15, 15);
        //hitBox.setStroke(new BasicStroke(5));
        image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
    }
    
    public void update()
    {
        x+=velX;
        y+=velY;
        hitBox = new Rectangle(this.x, this.y, 15, 15);
        move();        
    }
    
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(image, this.x, this.y, null);
        g2d.draw(hitBox);
    }
    
    public void move()
    {
        if(direction == 'N')
        {
            velY = -25;
            velX = 0;
            image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
        }
        if(direction == 'W')
        {
            velY = 0;
            velX = -25;
            image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
        }
        if(direction == 'E')
        {
            velY = 0;
            velX = 25;
            image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
        }
        if(direction == 'S')
        {
            velY = 25;
            velX = 0;
            image = Toolkit.getDefaultToolkit().getImage("Images//Fireball.gif");
        }
    }
    
    public Rectangle gethitBox()
    {
        return hitBox;
    }
    
    public char getDirection()
    {
        return direction;
    }
}
