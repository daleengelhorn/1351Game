package finalproject;

import basicgraphics.BasicFrame;
import basicgraphics.CollisionEventType;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Dimension;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

class Rim extends Sprite {

    public static int score = 0;
    Picture basePic;

    Rim() throws IOException {
        basePic = new Picture("rim.jpg");
        setPicture(basePic);
    }

    public void processEvent(SpriteCollisionEvent se) {
        if (se.sprite2 instanceof BasketBall) {

            score++;
      
        }

    }
}

class Backboard extends Sprite {

    Picture basePic;

    Backboard() throws IOException {
        basePic = new Picture("backboard.jpg");
        setPicture(basePic);
    }
}

class Background extends Sprite {

    Picture basePic;

    Background() throws IOException {
        basePic = new Picture("background.jpg");
        setPicture(new Picture("background.jpg"));
    }
}

class BasketBall extends Sprite {

    public static double newVelX;
    public static double newVelY;
    Picture basePic;

    BasketBall() throws IOException {
        basePic = new Picture("basketball2.png");
        setPicture(basePic);
    }

    public void preMove() {

        this.setVelY(this.getVelY() + 0.03);
    }

    @Override
    public void processEvent(SpriteCollisionEvent ce) {
        if (ce.eventType == CollisionEventType.WALL) {
            if (ce.xlo) {
                setVelX(Math.abs(getVelX()));
            }
            if (ce.xhi) {
                setVelX(-Math.abs(getVelX()));
            }
            if (ce.ylo) {
                setVelY(Math.abs(getVelY()));
            }
            if (ce.yhi) {
                setVelY(-Math.abs(getVelY()));
            }} 
                
if (ce.eventType == CollisionEventType.SPRITE) {
                if (ce.sprite2 instanceof Backboard) {
                      setVelX(-getVelX());
                      //System.out.println("Collides");
            }
        }
    }
}
   

public class Main {

    public static enum STATE {
        MENU,
        GAME;
    };

    public static STATE State = STATE.MENU;

    public void run() throws IOException {

        final BasicFrame bball = new BasicFrame("BasketBall");
        

        SpriteComponent sc = new SpriteComponent();

        final BasketBall ball = new BasketBall();
        ball.setX(350);
        ball.setY(280);

        Background court = new Background();
        court.setX(0);
        court.setY(0);

        Rim rim = new Rim();
        rim.setX(163);
        rim.setY(186);

        Backboard backboard = new Backboard();
        backboard.setX(159);
        backboard.setY(140);

        bball.add("BasketBall", sc, 0, 0, 1, 1);

        sc.setPreferredSize(new Dimension(575, 400));

        sc.addSprite(court);
  
        sc.addSprite(rim);
        sc.addSprite(backboard);
        sc.addSprite(ball);

        Score score = new Score();
        sc.addSprite(score);
        score.setX(100);
        score.setY(0);

        Countdown cn = new Countdown();
        sc.addSprite(cn);
        cn.setX(0);
        cn.setY(0);

        if (State == STATE.MENU) {
            List<String> optionList = new ArrayList<String>();
            optionList.add("Start");
            optionList.add("Quit");
            

            Object[] options = optionList.toArray();
            int value = JOptionPane.showOptionDialog(
                    null,
                    "Please click 'start' to begin the \ngame and 'quit' to exit!\n\n You have 60 seconds\n to score as much as possible!",
                    "Basketball Mania Menu",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    optionList.get(0));

            String opt = optionList.get(value);
            System.out.println("You picked " + opt);
            if (value == 0) {
                State = STATE.GAME;
            }
            if (value == 1) {
                System.exit(0);
            }
            
        }

        if (State == STATE.GAME) {
            bball.show();
        }
        
        
        sc.start(0, 5);

        bball.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                
                if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                    ball.setVelX(ball.getVelX() + -0.2);

                } 
                else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                    ball.setVelX(ball.getVelX() + 0.2);

                } 
                else if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    ball.setVelY(ball.getVelY() - 1.0);

                }

            }
        });
    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        m.run();

    }
}
