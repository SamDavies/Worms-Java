import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Bullet implements ActionListener {
	ArrayList<Image> destructionAnnimation=new ArrayList<Image>();
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
	int trajectoryIndex,currentImageIndex=0;
	
	public Bullet(MainPlayer p,MainPlayer enemy,ArrayList<StaticObjects> s,ArrayList<ReactiveObjects> r, boolean l){
		
		this.trajectoryIncrements=this.calculateTrajectory();
		this.visible=true;
		this.staticobjects=s;
		this.reactiveobjects=r;
		this.player=p;
		this.enemy=enemy;	
		this.launchDirectionRight=l;
		
		if(l==true){
			this.x=p.x+p.playerImage.getWidth(null);
			this.y=p.y;
			ImageIcon tempImageIcon= new ImageIcon("images/weapons/bullet-right.png");
			this.currentImage=tempImageIcon.getImage();
		}else{
			this.x=p.x-p.playerImage.getWidth(null);
			this.y=p.y;
			ImageIcon tempImageIcon= new ImageIcon("images/weapons/bullet-left.png");
			this.currentImage=tempImageIcon.getImage();
		}
		
		this.rectangle=new Rectangle(this.x,this.y,this.currentImage.getWidth(null),this.currentImage.getHeight(null));
		this.imageHeight=this.currentImage.getHeight(null);
		this.imageWidth=this.currentImage.getWidth(null);
		
		timer=new Timer(10,this);
		timer.start();
	}
	
	
	private int[][] calculateTrajectory() {
		int[][] trajectoryIncrements = new int[400][2];;
		for(int i=0;i<400;i++)
		{
			trajectoryIncrements[i][0]=3;
			trajectoryIncrements[i][1]=0;
		}
		return trajectoryIncrements;
	}


	public void updateSystemVariables(ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r)
	{
		staticobjects=s;
		reactiveobjects=r;
	}
	
	public void updateRectangle(){
		this.rectangle=new Rectangle(this.x,this.y,this.currentImage.getWidth(null),this.currentImage.getHeight(null));
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
		enemy.getsHit(5);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		   if(isDestroyed==false){
			if(this.checkCollisionStatic()==false){
			  if(this.checkCollisionReactive()==false){
				  if(this.checkCollisionEnemy()==false){
		          if(trajectoryIndex<trajectoryIncrements.length){
		        	  move(this.trajectoryIncrements[this.trajectoryIndex]);
		        	  updateRectangle();
		        	  	}
		        	 trajectoryIndex++;
		           }else destroy();
		         }else destroy();
		      }else destroy();
		  }else
		   {
			   if(this.currentImageIndex>=this.destructionAnnimation.size()){
			   		  		this.visible=false;
			   		  		timer.stop();
			   	          }
			   else {
				   		this.currentImage=this.destructionAnnimation.get(currentImageIndex);
				   		this.currentImageIndex++;
			   		}
		   }
		}
	private void destroy() {
		this.isDestroyed=true;
		this.x-=10;
		this.y-=10;
		this.timer.stop();
		this.currentImageIndex=0;
		loadDestructionImages();
		this.timer.setDelay(200);
		this.timer.start();
	}


	private void loadDestructionImages() {
		ImageIcon temp=new ImageIcon("images/weapons/bullet-burst.png");
		this.destructionAnnimation.add(temp.getImage());
		temp=new ImageIcon("images/weapons/bullet-burst.png");
		this.destructionAnnimation.add(temp.getImage());
	}


	private void move(int[] nextPosIncrements) {
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


	/**
	 * @param args
	 */
	

}
