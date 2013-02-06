package testgame;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
public class Object {





    private int x, y;
    private Image image;
    boolean visible;
    private int width, height;


    public Object(int x, int y,ImageIcon i) {
        this.setImage(i);
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.x = x;
        this.y = y;
    }

    private void setImage(ImageIcon objectImage){
       this.image=objectImage.getImage();
        }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

     public int getHeight() {
        return height;
    }
    
      public int getWidth() {
        return width;
    }
      
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
