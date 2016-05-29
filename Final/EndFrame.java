
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EndFrame extends JFrame 
{
    public static void displayFrame()
    {
        long startTime = System.currentTimeMillis();

        long total = 0;
        for (int i = 0; i < 10000000; i++) 
        {
            total += i;
        }
        
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        JFrame frame = new JFrame("Game Over");
        JLabel info = new JLabel(("YOU SUCK"), JLabel.CENTER);
        
        int height = 670;
        int width = 630;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int ex = (screen.width / 2) - (width / 4); // Center horizontally.
        int ey = (screen.height / 2) - (height / 4); // Center vertically.

        frame.setTitle("Game Over");
        frame.setBounds(ex, ey, width / 2, height / 2);
        //frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        //info.setBounds(ex, ey, width/2, height/2);

        frame.add(info);
        frame.setVisible(true);
    }
}
