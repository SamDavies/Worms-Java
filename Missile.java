import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Missile implements ActionListener{

	Image[] annimationImages;
	Image currentImage;
	boolean launchDirectionRight,visible; //true if it is launched to the right and false to the left
	int x,y; //x and y coordinate for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the image of the object
	Rectangle rectangle;
	Timer timer;
	MainPlayer player;
	ArrayList<StaticObjects> staticobjects;     //just pointers to the two arraylists that are created 
	ArrayList<ReactiveObjects> reactiveobjects; // in class MainClass
	int [][] trajectoryIncrements;              //used to store the trajectory
	int trajectoryIndex;						//this represents the index in trajectoryIncrements that we are currently at
	
	public Missile(MainPlayer p,ArrayList<StaticObjects> s,ArrayList<ReactiveObjects> r, boolean l,int trajectoryRadius) {
		this.trajectoryIncrements=this.calculateTrajectory(trajectoryRadius);
		this.visible=true;
		this.staticobjects=s;
		this.reactiveobjects=r;
		this.player=p;
		loadAnnimationImages();
		if(l==true){
			this.x=p.x+p.playerImage.getWidth(null);
			this.y=p.y;
		}
		else{
			this.x=p.x-p.playerImage.getWidth(null);
			this.y=p.y;
		}
			
		this.launchDirectionRight=l;
		this.rectangle=new Rectangle(this.x,this.y,this.currentImage.getWidth(null),this.currentImage.getHeight(null));
		this.imageHeight=this.currentImage.getHeight(null);
		this.imageWidth=this.currentImage.getWidth(null);
		timer=new Timer(5,this);
		timer.start();
	}

	
	public void loadAnnimationImages(){
		ImageIcon tempImageIcon=new ImageIcon(this.getClass().getResource("missile80.png"));
		this.currentImage=tempImageIcon.getImage();
	}
	
	
	public boolean checkCollisionStatic(){
		for(int i=0;i<staticobjects.size();i++)
				if(rectangle.intersects(staticobjects.get(i).rectangle))
				  return true;
	    return false;
	}
	
	public boolean checkCollisionReactive(){
		for(int i=0;i<reactiveobjects.size();i++)
				if(rectangle.intersects(reactiveobjects.get(i).rectangle))
				  return true;
		return false;
	}
	
	
	public void move(int[] nextPosIncrements ){
		if(this.launchDirectionRight==true)
		{
			this.x+=nextPosIncrements[0];
			this.y-=nextPosIncrements[1];
		}
		if(this.launchDirectionRight==false)
		{
			this.x-=nextPosIncrements[0];
			this.y-=nextPosIncrements[1];
		}
		
	}
	
	public void destroy(){
		this.timer.stop();
		this.visible=false;
	}
	
	public void destroy(ReactiveObjects r){
		
	}
	
	public int[][] calculateTrajectory(int circleRadius)
	{   
		double y,y1;
        y=0;
		y1=0;
		int[][] traj=new int[circleRadius*2][2];
		traj[0][0]=0;
		traj[0][1]=0;
		int i=circleRadius;
		for(int j=1;j<circleRadius;j++)
		{
			traj[j][0]=1;
			traj[circleRadius*2-j][0]=1;
		    y1=Math.round(Math.sqrt(circleRadius*circleRadius-i*i));
		    traj[j][1]=(int) Math.round(y1-y);
		    traj[circleRadius*2-j][1]=-(int) Math.round(y1-y);
		    y=y1;
		    i--;
		}
		this.trajectoryIndex=0;
	    return traj;
	}
	
	public void updateSystemVariables(ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r)
	{
		staticobjects=s;
		reactiveobjects=r;
	}
	
	public void updateRectangle(){
		this.rectangle=new Rectangle(this.x,this.y,this.currentImage.getWidth(null),this.currentImage.getHeight(null));
	}
	
	public static void main(String[] args) {     //we are going to move the missile here
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if(this.checkCollisionStatic()==false){
		  if(this.checkCollisionReactive()==false){
	          if(trajectoryIndex<trajectoryIncrements.length){
	        	  move(this.trajectoryIncrements[this.trajectoryIndex]);
	        	  updateRectangle();
	        	  trajectoryIndex++;
	          }else visible=false;
	      }else destroy();
	    }else destroy();

	}

}
