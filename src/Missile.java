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

	ArrayList<Image> annimationImages=new ArrayList<Image>();
	Image currentImage;
	boolean launchDirectionRight,visible,isDestroyed=false; //true if it is launched to the right and false to the left
	int x,y; //x and y coordinate for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the image of the object
	Rectangle rectangle;
	Timer timer,timerDestroy;
	MainPlayer player, enemy;
	ArrayList<StaticObjects> staticobjects;     //just pointers to the two arraylists that are created 
	ArrayList<ReactiveObjects> reactiveobjects; // in class MainClass
	int [][] trajectoryIncrements;              //used to store the trajectory
	int trajectoryIndex,currentImageIndex=0,ImageIncrementPeriod;						//this represents the index in trajectoryIncrements that we are currently at
	
	public Missile(MainPlayer p,MainPlayer enemy,ArrayList<StaticObjects> s,ArrayList<ReactiveObjects> r, boolean l,int trajectoryRadius) {
		this.trajectoryIncrements=this.calculateTrajectory(trajectoryRadius);
		this.visible=true;
		this.staticobjects=s;
		this.reactiveobjects=r;
		this.player=p;
		this.enemy=enemy;
		if(l==true){
			this.x=p.x+p.playerImage.getWidth(null);
			this.y=p.y;
			loadAnnimationImagesRight();
		}
		else{
			this.x=p.x-p.playerImage.getWidth(null);
			this.y=p.y;
			loadAnnimationImagesLeft();
		}
			
		this.launchDirectionRight=l;
		this.rectangle=new Rectangle(this.x,this.y,this.currentImage.getWidth(null),this.currentImage.getHeight(null));
		this.imageHeight=this.currentImage.getHeight(null);
		this.imageWidth=this.currentImage.getWidth(null);
		this.ImageIncrementPeriod=this.trajectoryIncrements.length/this.annimationImages.size();
		timer=new Timer(10,this);
		timer.start();
	}

	
	public void loadAnnimationImagesLeft(){
		ImageIcon tempImageIcon=new ImageIcon("images/weapons/missile100.png");
		this.currentImage=tempImageIcon.getImage();
		tempImageIcon=new ImageIcon("images/weapons/missile120.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile145.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile165.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile180.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile195.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile210.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile225.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile260.png");
		this.annimationImages.add(tempImageIcon.getImage());
	}
	
	public void loadAnnimationImagesRight(){
		ImageIcon tempImageIcon=new ImageIcon("images/weapons/missile80.png");
		this.currentImage=tempImageIcon.getImage();
		tempImageIcon=new ImageIcon("images/weapons/missile60.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile45.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile30.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile15.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile0.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile-15.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile-30.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile-45.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile-60.png");
		this.annimationImages.add(tempImageIcon.getImage());
		tempImageIcon=new ImageIcon("images/weapons/missile-80.png");
		this.annimationImages.add(tempImageIcon.getImage());
	}
	
	public void loadDestructionImages(){
		ImageIcon tempImageIcon=new ImageIcon("images/explosion/explosion-1.png");
		this.currentImage=tempImageIcon.getImage();
		
		for(int i = 1; i<13; i++){
			String exp = "images/explosion/explosion-" + i + ".png";
			tempImageIcon=new ImageIcon(exp);
			this.annimationImages.add(tempImageIcon.getImage());
		}
		
	}
	
	public boolean checkCollisionStatic(){
		for(int i=0;i<staticobjects.size();i++)
				if(rectangle.intersects(staticobjects.get(i).rectangle)){
				  return true;
				}
	    return false;
	}
	
	public boolean checkCollisionEnemy(){
		if(enemy!=null)
	 if(rectangle.intersects(enemy.CollisionRectangle))
	 {
		enemy.getsHit(10);
		 return true;
	 }		
	    return false;
	}
	
	public boolean checkCollisionReactive(){
		for(int i=0;i<reactiveobjects.size();i++)
				if(rectangle.intersects(reactiveobjects.get(i).rectangle)){
					reactiveobjects.get(i).destroy(player);
				  return true;
				}
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
		this.isDestroyed=true;
		this.timer.stop();
		this.annimationImages.clear();
		this.currentImageIndex=0;
		loadDestructionImages();
		this.timerDestroy=new Timer(100,this);
		this.timerDestroy.start();
	}
	
	
	public int[][] calculateTrajectory(int circleRadius)
	{   
		double y,y1;
        y=0;
		y1=0;
		int[][] traj=new int[400][2];
		traj[0][0]=0;
		traj[0][1]=0;
		int i=circleRadius;
		for(int j=1;j<circleRadius;j++)
		{
			traj[j][0]=2;
			traj[circleRadius-j][0]=2;
		    y1=Math.round(Math.sqrt(circleRadius*circleRadius-i*i));
		    traj[j][1]=(int) Math.round(y1-y);
		    traj[circleRadius-j][1]=-(int) Math.round(y1-y);
		    y=y1;
		    i-=2;
		}
		for(int j=circleRadius;j<400;j++){
			traj[j][0]=2;
		    traj[j][1]=traj[j-1][1];}
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
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
	   if(isDestroyed==false){
		if(this.checkCollisionStatic()==false){
		  if(this.checkCollisionReactive()==false){
			  if(this.checkCollisionEnemy()==false){
	          if(trajectoryIndex<trajectoryIncrements.length){
	        	  move(this.trajectoryIncrements[this.trajectoryIndex]);
	        	  updateRectangle();
	        	  if(trajectoryIndex%ImageIncrementPeriod==0&&trajectoryIndex!=0){
	        		  currentImage=annimationImages.get(currentImageIndex+1);
	        		  currentImageIndex++;  
	        	  	}
	        	 trajectoryIndex++;
	           }else destroy();
	         }else destroy();
	      }else destroy();
	    }else destroy();
	  }else
	   {
		   if(this.currentImageIndex<this.annimationImages.size())
			   {currentImage=this.annimationImages.get(currentImageIndex);
		   	   currentImageIndex++;
		   	   } else {
		   		  		this.visible=false;
		   		  		timerDestroy.stop();
		   	          }
	   }
	}

}
