
package WormsGame;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Craft   {

    int dx;
    int dy;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;
    boolean  injump=false;
    int jumpspeed=0;
    ArrayList<Object> staticObjects=new ArrayList<Object>();

    private int[][] pos = { 
        {100, 400}, {190, 400}, {300, 20},
        {15, 50}, {500, 70}
        };


    public Craft() {
            for(int i=0;i<pos.length;i++)
        {   
            ImageIcon ii=new ImageIcon(this.getClass().getResource("brick.jpg"));
            staticObjects.add(new Object(pos[i][0],pos[i][1],ii));
        }
        ImageIcon ii = new ImageIcon(this.getClass().getResource("craft.jpg"));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        x = 100;
        y = 200;
    }


    public void move() {

        x += dx;
        y +=jumpspeed;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
        if(x>750)
        {
            x=750;
        }
        if(y>550){
            y=550;
        }
            
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }



    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            jump(staticObjects,jumpspeed-1);
        }

        if (key == KeyEvent.VK_LEFT) {
            dx-= 2;
            this.checkCollisions(e, staticObjects);
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx+= 2;
            this.checkCollisions(e, staticObjects);
        }
       
    }

    public void jump(ArrayList<Object> s,int speed) {
       for(int i=0;i<s.size();i++)
     {
         if(((s.get(i).getX()<this.getX())&&((s.get(i).getX()+s.get(i).getWidth())>this.getX()))
          ||((s.get(i).getX()<(this.getX()+this.width))&&((s.get(i).getX()+s.get(i).getWidth())>(this.getX()+this.width)))) 
            if(this.getY()<=(s.get(i).getY()+s.get(i).getHeight()))
            {
                jumpspeed=0;
            }
     }
       if(injump==false){
       injump=true; 
       jumpspeed=4;
        }else jumpspeed=speed;
       if(jumpspeed>4){
           jumpspeed=0;
           this.injump=false;
       }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

      }
    
      public void checkCollisions(KeyEvent e,ArrayList<Object> s) {
   
    if(e.getKeyCode()==KeyEvent.VK_LEFT)
       for(int i=0;i<s.size();i++)
     {
         if(((s.get(i).getY()<this.getY())&&((s.get(i).getY()+s.get(i).getHeight())>this.getY()))
           ||((s.get(i).getY()<(this.getY()+this.height))&&((s.get(i).getY()+s.get(i).getHeight())>(this.getY()+this.height))))
            if(this.getX()<=(s.get(i).getX()+s.get(i).getWidth())&&(this.getX()>=s.get(i).getX()))
            {
                this.x=s.get(i).getX()+s.get(i).getWidth()+5;
            }
         
     }
     
       if(e.getKeyCode()==KeyEvent.VK_RIGHT)
       for(int i=0;i<s.size();i++)
     {
         if(((s.get(i).getY()<this.getY())&&((s.get(i).getY()+s.get(i).getHeight())>this.getY()))
          ||((s.get(i).getY()<(this.getY()+this.height))&&((s.get(i).getY()+s.get(i).getHeight())>(this.getY()+this.height))) )
            if((this.getX()>=s.get(i).getX())&&(this.getX()<=(s.get(i).getX()+s.get(i).getWidth())))
            {
                 this.x=s.get(i).getX()+this.width+5;
            }
     }
       
   }

    public void keyTyped(KeyEvent e) {
    }
}
