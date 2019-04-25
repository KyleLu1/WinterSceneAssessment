package WinterScene;

// (c) A+ Computer Science
// www.apluscompsci.com
// Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class WinterScenePanel extends JPanel implements KeyListener, Runnable {

    private List<AbstractShape> shapes;
    private AbstractShape sMan;
    private boolean[] keys;

    public WinterScenePanel() {
        keys = new boolean[1];
        this.addKeyListener(this);
        setVisible(true);
        shapes = new ArrayList<AbstractShape>();
        for (int i = 0; i < 50; i++) {
            int y = (int) (Math.random() * 600);
            int s = (int) (Math.random() * 30) + 20;
            shapes.add(new SimpleSnowFlake(i * 14, y, s, s));
            shapes.add(new FancySnowFlake(i * 14, y, s, s));
        }
        sMan = new SnowMan(500, 350, 200, 150);
        new Thread(this).start();
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        window.setColor(Color.BLUE);
        window.fillRect(0, 0, getWidth(), getHeight());
        window.setColor(Color.WHITE);
        window.drawRect(20, 20, getWidth() - 40, getHeight() - 40);
        window.setFont(new Font("TAHOMA", Font.BOLD, 18));
        window.drawString("MAKE A WINTER SCENE!", 40, 40);

        sMan.draw(window);
        for (AbstractShape sh : shapes) {
            sh.moveAndDraw(window);
            if (sh.getYPos() >= getHeight()) {
                sh.setYPos((int)(Math.random()*500)-100);
                if(sh.getXPos() >= getWidth()){
                    sh.setXPos((int)(Math.random()*600)-100);
                }
            }
            
            
            if (keys[0] == true) {
            for (int i = 0; i < 50; i++) {
            int a = (int) (Math.random() * 600);
            int b = (int) (Math.random() * 30) + 20;
            shapes.add(new StormySnowFlake(i * 14, a, b, b));
        }
        }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keys[0] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keys[0] = false;
     
        repaint();
    }
    }
    
     public void keyTyped(KeyEvent e) {
        //no code needed here
    }
    
    
    
    
    
    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(35);
                repaint();
            }
        } catch (Exception e) {
        }
    }
    
    
}

