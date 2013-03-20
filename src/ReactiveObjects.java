import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;


public class ReactiveObjects implements ActionListener {

	Image[] annimationImages;
	Image currentImage;
	String type;
	int x,y; //x and y coordinate for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the image of the object
	Timer timer=new Timer(200,this);
	Rectangle rectangle;
	boolean visible;
	public ReactiveObjects(int xvalue, int yvalue, int type){		//constructor
		this.x=xvalue;
		this.y=yvalue;
		visible=true;
		ImageIcon tempImageIcon;
		switch(type){
		case 0:
			this.type="heart";
			tempImageIcon=new ImageIcon("images/reactive/heart.png");
			this.currentImage=tempImageIcon.getImage();
			break;
		case 1:
			this.type="diamond";
			tempImageIcon=new ImageIcon("images/reactive/diamond.png"); 
			this.currentImage=tempImageIcon.getImage();
			break;
		case 2:
			this.type="gold";
			tempImageIcon=new ImageIcon("images/reactive/gold.png"); 
			this.currentImage=tempImageIcon.getImage();
			break;
		
		}
		
		this.rectangle=new Rectangle(xvalue+4,yvalue,this.currentImage.getWidth(null)-8,this.currentImage.getHeight(null));
		this.imageHeight=this.currentImage.getHeight(null);
		this.imageWidth=this.currentImage.getWidth(null);
	}
	
	public void destroy(MainPlayer player){
		player.hasHit(type);
		timer.start();
	}

	public int getX(){return x;}
	public int getY(){return y;}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.stop();
		this.visible=false;
		
	}
}
	
	
