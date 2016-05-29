
 
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.io.*;
import java.awt.Component;
import sun.audio.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.HashMap;
public class PlayBoard extends JPanel implements ActionListener 
{
    private Mario player;
    private ArrayList<Gumba> enemies;
    private Timer mainTimer; 
    private boolean spawnMore = false;
    private int killCount;
    private AudioPlayer bgMusic;
    private HashMap<String, AudioPlayer> sfx;
    
    
    static void main(String[] args) 
    {
        JFrame f = new JFrame("Survive!");
        f.add(new PlayBoard());
        f.setSize(1600, 900);
        f.setVisible(true);
    }

    public PlayBoard() 
    {
        this.setFocusable(true);
        enemies = new ArrayList<Gumba>();
        setBackground(Color.BLACK);
        bgMusic = new AudioPlayer("/Music/Megalovania.wav");
        bgMusic.play();
        sfx = new HashMap<String, AudioPlayer>();     
        sfx.put("MonsterDeath", new AudioPlayer("/SFX/MonsterDeath.wav"));
        player = new Mario(800,450);
        makeEnemies(3);
        mainTimer = new Timer(8, this);
        mainTimer.start();
        addKeyListener(new Default(this.player));
    }
    
    public void makeEnemies(int amount)
    {
        for(int i = 0; i <= amount; i++)
        {
            int location = (int) (Math.random()*4+1);
            if(location == 1) //top left
            {
                int xRand = (int)(Math.random()*-200+1);
                int yRand = (int)(Math.random()*-200+1);
                Gumba enemy = new Gumba(xRand, yRand);
                enemies.add(enemy);
            }
              if(location == 2) //bottom left
            {
                int xRand = (int)(Math.random()*-200+1);
                int yRand = (int)(Math.random()*9000+800);
                Gumba enemy = new Gumba(xRand, yRand);
                enemies.add(enemy);
            }
              if(location == 3) //top right
            {
                int xRand = (int)(Math.random()*1400+1200);
                int yRand = (int)(Math.random()*-200+1);
                Gumba enemy = new Gumba(xRand, yRand);
                enemies.add(enemy);
            }
            if(location == 4) //bottom right
            {
                int xRand = (int)(Math.random()*1400+1200);
                int yRand = (int)(Math.random()*800+600);
                Gumba enemy = new Gumba(xRand, yRand);
                enemies.add(enemy);
            }
        }
    }
    
    public void spawn(boolean spawnMore)
    {
        if(spawnMore == true)
        {
            int location = (int) (Math.random()*4+1);
            if(location == 1) //top left
            {    
                for(int i = 0; i < 2; i++)
                {
                    int xRand = (int)(Math.random()*-200+1);
                    int yRand = (int)(Math.random()*-200+1);    
                    Gumba enemy = new Gumba(xRand, yRand);
                    enemies.add(enemy);
                    spawnMore = false;
                }
            }
            if(location == 2) //bottom left
            {
               for(int i = 0; i < 2; i++)
               {
                    int xRand = (int)(Math.random()*-200+1);
                    int yRand = (int)(Math.random()*9000+800);
                    Gumba enemy = new Gumba(xRand, yRand);               
                    enemies.add(enemy);
                    spawnMore = false;
               }
            }
            if(location == 3) //top right
            {
                 for(int i = 0; i < 2; i++)
                {
                    int xRand = (int)(Math.random()*1400+1200);
                    int yRand = (int)(Math.random()*-200+1);
                    Gumba enemy = new Gumba(xRand, yRand);
                    enemies.add(enemy);
                    spawnMore = false;
                }
            }
            if(location == 4) //bottom right
            {
               for(int i = 0; i < 2; i++)
                {
                    int xRand = (int)(Math.random()*1400+1200);
                    int yRand = (int)(Math.random()*800+600);
                    Gumba enemy = new Gumba(xRand, yRand);
                    enemies.add(enemy);
                    spawnMore = false;
               }
            }
        }
    }    
   
    public Mario getMario() 
    {
        return this.player;
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;        
        if (!player.isDead())
            player.draw(g2d);  
        for(int i = 0; i < enemies.size()-1; i++)
        {
            enemies.get(i).draw(g2d);
        }

    }
   
    //@Override
    public void actionPerformed(ActionEvent e) 
    {
        player.update();
        
        for(int i = 0; i < enemies.size()-1; i++)
            enemies.get(i).move(player);
        for(int i = enemies.size()-1; i >= 0; i--)
        {
            Gumba enemy = enemies.get(i);
            enemy.update();
            if(enemy.gethitBox().intersects(player.gethitBox()))
            {
                player.setDead(true);
                bgMusic.close();                
            }
            player.detectCollide(enemy); 
            if(player.boom(enemy))
            {
               EndFrame.displayFrame();
            }
        }
        ArrayList<Fireball> projectile = player.getFireballs();
        for (int i = projectile.size() - 1; i >= 0; i --)
        {
            Fireball bullet = projectile.get(i);
            projectile.get(i).update();
            
            // Checks for intersection with any Gumba
            for (int j = enemies.size() - 1; j >= 0; j--)
            {
                Gumba enemy = enemies.get(j);
                if (bullet.gethitBox().intersects(enemy.gethitBox()))
                {
                    enemies.remove(j);
                    sfx.get("MonsterDeath").play();
                    killCount ++;
                    spawnMore = true;
                    if(spawnMore)
                        spawn(spawnMore);
                    projectile.remove(i);
                    break;
                }
            }
        }
        repaint();
        System.out.println("Score:" + killCount);
    }
}   
    

