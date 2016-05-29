import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.HashMap;
public class Mario extends Default
{
    private int velX = 0;
    private int velY = 0;
    private Image img;
    private ArrayList<KeyEvent> pressed;
    private ArrayList<Fireball> projectile;
    private ArrayList<Gumba> enemies;
    private boolean dead;
    private boolean playingDeathSound;
    private HashMap<String, AudioPlayer> sfx;
    public Mario(int x, int y) 
    {
        super(x,y);
        direction = 'N';
        projectile = new ArrayList<Fireball>();
        hitBox = new Rectangle(this.x, this.y, 28, 38);
        //hitBox.setColor(Color.GREEN);
        //hitBox.setStroke(Color.GREEN);
        img = Toolkit.getDefaultToolkit().getImage("Images//Mario_STANDING.png");
        sfx = new HashMap<String, AudioPlayer>();
        sfx.put("fireball", new AudioPlayer("/SFX/fireball.wav"));
        sfx.put("Death", new AudioPlayer("/SFX/Death.wav"));
    }
    
    public void update() 
    {
        y += velY;
        x += velX; 
        hitBox = new Rectangle(this.x, this.y, 28, 38);
        if (dead && !playingDeathSound)
        {
            sfx.get("Death").play();
            playingDeathSound = true;
        }
    }
    public boolean boom(Gumba enemy)
    {
        if(this.hitBox.intersects(enemy.gethitBox()))
            return true;
        else
            return false;
    }
    public ArrayList<Fireball> getFireballs()
    {
        return projectile;
    }
       
    public void setDead(boolean dead)
    {
       this.dead = dead;
    }
    
    public boolean isDead()
    {
        return dead;
    }
    
    public void detectCollide(Gumba enemy)
    {
        if(this.hitBox.intersects(enemy.gethitBox()))
        {
            if(direction == 'N')
                img = Toolkit.getDefaultToolkit().getImage("Images//Mario_UP.png");
            if(direction == 'S')
                img = Toolkit.getDefaultToolkit().getImage("Images//Mario_DOWN.png");
            if(direction == 'E')
                img = Toolkit.getDefaultToolkit().getImage("Images//Mario_RIGHT.png");
            if(direction == 'W')
                img = Toolkit.getDefaultToolkit().getImage("Images//Mario_LEFT.png");
            velY = 0;
            velX = 0;
        }
    }
    
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(img, this.x, this.y, null);
        g2d.draw(hitBox);
        for(Fireball a: projectile)
             a.draw(g2d);
    }

    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W)
        {
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_UP.png");
            velY = -5;
            direction = 'N';
        }
        if (key == KeyEvent.VK_A)
        {
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_LEFT.png");
            velX = -5;
            direction = 'W';
        }
        if (key == KeyEvent.VK_S)
        {
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_DOWN.png");
            velY = 5;
            direction = 'S';
        }
        if (key == KeyEvent.VK_D)
        {   
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_RIGHT.png");
            velX = 5;
            direction = 'E';
        }
        if(key == KeyEvent.VK_SPACE)
        {
            projectile.add(new Fireball(this.x,this.y,this.getDirection()));
            sfx.get("fireball").play();
        }
    }
    
    public void keyReleased(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W)
            velY = 0;
        else if (key == KeyEvent.VK_A)
            velX = 0;
        else if (key == KeyEvent.VK_S)
            velY = 0;
        else if (key == KeyEvent.VK_D)
            velX = 0;
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

