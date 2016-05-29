
import java.awt.*;

public class Gumba extends Default
{
    private Image img;

    private int velX;
    private int velY;
    private boolean dead;
    public Gumba(int x, int y)
    {
        super(x,y);
        hitBox = new Rectangle(this.x + 5, this.y, 22, 34);

    }
    
    public void update()
    {
        x+=velX;
        y+=velY;
        velY = 0;
        velX = 0;
        hitBox = new Rectangle(this.x + 5, this.y, 22, 34);
    }
   
    public void move(Mario player)
    {
        int xRandOffset = player.x + (int)(Math.random() * 35);
        int yRandOffset = player.y + (int)(Math.random() * 35); 
        if (x < xRandOffset)
        {
            velX += 1;
            img = Toolkit.getDefaultToolkit().getImage("Images//Gumba_RIGHT.png");
        }
        else if (x > xRandOffset)
        {
            velX -= 1;
            img = Toolkit.getDefaultToolkit().getImage("Images//Gumba_LEFT.png");
        }
        if (y < yRandOffset)
        {
            velY += 1;
            img = Toolkit.getDefaultToolkit().getImage("Images//Gumba_DOWN.png");
        }
        else if (y > yRandOffset)
        {
            velY -= 1;
            img = Toolkit.getDefaultToolkit().getImage("Images//Gumba_UP.png");
        }
    }
    
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(img,this.x, this.y, null);
        g2d.draw(hitBox);
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
