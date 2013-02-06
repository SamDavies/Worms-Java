package testgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.EventListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener,KeyListener  {

    private Timer timer;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    private int FRAME_WIDTH=800;
    private int FRAME_HEIGHT=600;
    Craft player=new Craft();

    public Board() {
       
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        ingame = true;
        this.addKeyListener(this);
        timer = new Timer(20, this);
        timer.start();
        
    }

    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();   
    }


    public void paint(Graphics g) {
        super.paint(g);
        if (ingame) {
            Graphics2D g2d = (Graphics2D)g;
           
            for(int i=0;i<player.staticObjects.size();i++){
            g2d.drawImage(player.staticObjects.get(i).getImage(),player.staticObjects.get(i).getX(), player.staticObjects.get(i).getY(), this);
            }
            g2d.drawImage(player.getImage(), player.getX()+player.dx, player.getY()+player.dy, this);
             
             } else {
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
                         B_HEIGHT / 2);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void actionPerformed(ActionEvent e) {
       if(player.injump&&(player.jumpspeed>-4))
       {
         player.jump(player.staticObjects, player.jumpspeed-1);
       }
       player.move();
        repaint();
    }

  
    
 
    public void keyPressed(KeyEvent e){
          player.keyPressed(e);
        }
    public void keyReleased(KeyEvent e){
          player.keyReleased(e);
        
    }
    public void keyTyped(KeyEvent e) {}
    
   
    
   
    
        
    public static void main(String[] args){
      Board controlBoard=new Board();
      JFrame frame=new JFrame();
      frame.setTitle("Game");
      frame.setSize(controlBoard.FRAME_WIDTH, controlBoard.FRAME_HEIGHT);
      frame.setVisible(true);
      frame.add(controlBoard);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
