
package finalproject;

import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;


    public class Countdown extends Sprite{
    
   
    public Countdown() {
        updatePicture();

    }
    long start = System.currentTimeMillis();
    @Override
            public void preMove() {
                tm = (int) ((System.currentTimeMillis() - start)/1000);
                updatePicture();
            }
    int tm;
    void updatePicture() {
            BufferedImage im = BasicFrame.createImage(500, 25);
            Graphics g = im.getGraphics();
            g.drawString(String.format("%02d",tm), 280, 25);
            setPicture(new Picture(im));
            if (tm >= 60)
            {
            
                System.out.println("You scored: " + Rim.score);
                System.exit(0);
                
            }
                
    }
    
    
}
