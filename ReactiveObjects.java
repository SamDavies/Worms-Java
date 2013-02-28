import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class ReactiveObjects {

	Image[] annimationImages;
	Image currentImage;
	int x,y; //x and y coordinate for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the image of the object
	Rectangle rectangle;
	boolean visible;
	public ReactiveObjects(int xvalue, int yvalue){		//constructor
		this.x=xvalue;
		this.y=yvalue;
		visible=true;
		ImageIcon tempImageIcon=new ImageIcon(this.getClass().getResource("enemy1.png"));
		this.currentImage=tempImageIcon.getImage();
		this.rectangle=new Rectangle(xvalue+4,yvalue,this.currentImage.getWidth(null)-8,this.currentImage.getHeight(null));
		this.imageHeight=this.currentImage.getHeight(null);
		this.imageWidth=this.currentImage.getWidth(null);
	}
	
	public void destroy(){
		this.visible=false;
	}

	public int getX(){return x;}
	public int getY(){return y;}
}
	
	

