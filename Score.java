/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Score extends Sprite{
    
    int score;
    String sco = "Score: ";
    
    public Score() {
        updatePicture();

    }
    
    @Override
            public void preMove() {
                score = Rim.score;
                updatePicture();
            }
    
    void updatePicture() {
            BufferedImage im = BasicFrame.createImage(500, 25);
            Graphics g = im.getGraphics();
            g.drawString(String.format("Score: %d", Rim.score), 0, 25);
            setPicture(new Picture(im));
    }
    
    
}

