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

import java.util.Random;

public class Weapon implements ActionListener {

	ArrayList<Image> annimationImages = new ArrayList<Image>();
	ArrayList<Image> destructionImages = new ArrayList<Image>();
	Image currentImage;
	boolean launchDirectionRight, visible,isDestroyed=false, hasHitEnemyDirectly=false; // true if it is launched to the
											// right and false to the left
	int x, y; // x and y coordinate for drawing the image on the screen
	private int imageWidth, imageHeight; // width and height of the image of the
											// object
	private Rectangle rectangle;
	private Timer timer;
	private MainPlayer player,enemy;
	ArrayList<StaticObjects> staticobjects; // just pointers to the two
											// arraylists that are created
	ArrayList<ReactiveObjects> reactiveobjects; // in class MainClass
	private int[][] trajectoryIncrements; // used to store the trajectory
	private int trajectoryIndex; // this represents the index in
	private int expIndex; // trajectoryIncrements

	// that we are currently at

	public Weapon(MainPlayer p,MainPlayer enemy, ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, boolean l, double velocity,
			double angle) {
		trajectoryIncrements = calculateTrajectory(velocity, angle);
		visible = true;
		staticobjects = s;
		reactiveobjects = r;

		loadAnnimationImages();
        loadDestructionImages();
		player = p;
		player.setGrenadesAvailable(player.getGrenadesAvailable()-1);
		this.enemy=enemy;
		if (l == true) {
			x = p.x + p.playerImage.getWidth(null);
			y = p.y;
		} else {
			x = p.x - p.playerImage.getWidth(null);
			y = p.y;
		}

		launchDirectionRight = l;
		rectangle = new Rectangle(x, y, currentImage.getWidth(null),
				currentImage.getHeight(null));
		imageHeight = currentImage.getHeight(null);
		imageWidth = currentImage.getWidth(null);
		timer = new Timer(20, this);
		timer.start();
	}

	public void loadAnnimationImages() {

		for (int i = 0; i < 8; i++) {
			ImageIcon tempImageIcon = new ImageIcon("images/grenade/grenade" + i + ".png");
			annimationImages.add(tempImageIcon.getImage());
		}

		currentImage = annimationImages.get(0);

	}
	
	public void loadDestructionImages(){
		for (int i = 0; i < 18; i++) {
			ImageIcon tempImageIcon = new ImageIcon("images/grenade/explosion" + i + ".png");
			destructionImages.add(tempImageIcon.getImage());
		}

		
	}

	public boolean checkCollisionEnemy(){
		if(enemy!=null)
	 if(rectangle.intersects(enemy.CollisionRectangle))
	 {
		if(this.isDestroyed==false){
			enemy.getsHit(20);
			this.hasHitEnemyDirectly=true;
		}	else if(this.hasHitEnemyDirectly==false)
			enemy.getsHit(1);
		 return true;
	 }		
	    return false;
	}
	
	public boolean checkCollisionStatic() {
		if(y>575) return true;
		for (int i = 0; i < staticobjects.size(); i++)
			if (rectangle.intersects(staticobjects.get(i).rectangle))
				return true;
		return false;
	}

	public boolean checkCollisionReactive() {
		for (int i = 0; i < reactiveobjects.size(); i++)
			if (rectangle.intersects(reactiveobjects.get(i).rectangle)){
			    reactiveobjects.get(i).destroy(player);
				return true;
			}
		return false;
	}

	public void move() {
		int[][] xyPos = trajectoryIncrements;
		int index = trajectoryIndex;

		if (launchDirectionRight == true) {
			x += xyPos[index][0];
			y -= xyPos[index][1];
		}
		if (launchDirectionRight == false) {
			x -= xyPos[index][0];
			y -= xyPos[index][1];
		}

	}

	public void destroy() { // applies the explosion images and loops through
        isDestroyed=true;
        timer.stop();
        this.x-=50;
        this.y-=50;
        timer.setDelay(100);
        timer.start();
	}

	public void expolsionSound() {

	}

	public void explosion() {
	if (expIndex == 0) { //plays once a random explosion
			Random rand = new Random();
			int r = rand.nextInt(2);
			if (r == 0) {
				//SoundEffect.EXPLODE1.play(); // invokes the sound effect only
												// once
			} else if (r == 1) {
				//SoundEffect.EXPLODE2.play(); // invokes the sound effect only
												// once
			} else if (r == 2) {
				//SoundEffect.EXPLODE3.play(); // invokes the sound effect only
												// once
			}
		}

		currentImage = destructionImages.get(expIndex);
		expIndex++;
		updateRectangle();
		this.checkCollisionEnemy();
	}


	public int[][] calculateTrajectory(double speed, double angle) {
		double vertSpeed = speed * Math.sin(angle);
		double t = 0.005; // timer updates every 0.005 therefore must be
							// calculated at those intrvals
		double horrSpeed = t * speed * Math.cos(angle);

		int[][] xyPos = new int[1000][2]; // calculates x and y for 1000
											// intervals ([i][0] = x [i][0] = y

		xyPos[0][1] = 0;
		xyPos[0][0] = 0;

		for (int i = 1; i < 1000; i++) {

			double s = vertSpeed * t + 0.5 * -200 * i * t * i * t; // slower
																	// gravity
																	// set from
																	// real
																	// -980m/s2
																	// to
																	// -200m/s2

			xyPos[i][1] = (int) Math.round(s) - xyPos[i - 1][1];
			xyPos[i][0] = (int) Math.round(horrSpeed);
		}

		return xyPos;
	}

	public void updateSystemVariables(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		staticobjects = s;
		reactiveobjects = r;
		this.enemy=enemy;
	}

	public void updateRectangle() {
		this.rectangle = new Rectangle(this.x, this.y,
				currentImage.getWidth(null), currentImage.getHeight(null));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isDestroyed==false)
		if (checkCollisionStatic() == false) {
			if (checkCollisionReactive() == false) {
				if (checkCollisionEnemy() == false){
				if (trajectoryIndex < trajectoryIncrements.length) {
					move();
					updateRectangle();
					currentImage = annimationImages.get(trajectoryIndex % 7);
					trajectoryIndex++;
				} else destroy();
				}else destroy();
			}else destroy();
		}else destroy();
		if(isDestroyed==true)
		{
		      if(expIndex<annimationImages.size())
		    	  explosion();
		      else {
		    	   timer.stop();
					visible = false;
		      	   }
		    	  
		   	}	   

	}

}
