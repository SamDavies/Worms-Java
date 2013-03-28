import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.sound.sampled.Line;
import javax.swing.Timer;
import javax.swing.ImageIcon;

public class MainPlayer implements ActionListener { // the class for the user
													// controlled player

	Image playerImage; // the image that is associated with the object and is
						// drawn to the screen
	Image[] annimationImages;
	int currentImageIndex; // the index of the current image in annimationImages
	int x, y; // x and y coordinates for drawing the image on the screen
	int jumpSpeed, gravitySpeed; // the current speeds for jump and gravity
	final int jumpSpeedBound = 10, gravitySpeedBound = 7; // this are the
															// maximum speeds
															// for jump and
															// gravity
	int playerHealth = 100;
	Rectangle playerRectangleDown, playerRectangleLeft, playerRectangleRight,
			playerRectangleJump, CollisionRectangle;
	Line2D.Double lineBottom, lineTop; // this is a Line2D object used to check
										// for collision with the bottom of the
										// screen
	boolean injump = false, directionRight = true; // true if the object faces
													// right and false if it
													// faces left
	ArrayList<StaticObjects> staticobjects; // just pointers to the two
											// arraylists that are created
	ArrayList<ReactiveObjects> reactiveobjects; // in class MainClass
	ArrayList<MainPlayer> enemies = new ArrayList<MainPlayer>();
	Timer timer;
	int gravityIncrment = 0;	
	int jumpQuantity;
	boolean canDoubleJump = false;
	private int grenadesAvailable = 50;
	private int missilesAvailable = 5;

	public MainPlayer(int xvalue, int yvalue, ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, ArrayList<MainPlayer> enemies) {
		x = xvalue;
		y = yvalue;
		jumpSpeed = 0; // this function sets the variables of this object
		gravitySpeed = 0; // it is called when the object is created
		injump = false;
		loadAnnimationImages();
		playerImage = annimationImages[0];
		playerRectangleLeft = new Rectangle(x - 5, y,
				playerImage.getWidth(null) + 5, playerImage.getHeight(null));
		playerRectangleRight = new Rectangle(x, y,
				playerImage.getWidth(null) + 5, playerImage.getHeight(null));
		playerRectangleDown = new Rectangle(x, y, playerImage.getWidth(null),
				playerImage.getHeight(null) + 2);
		CollisionRectangle = new Rectangle(x, y, playerImage.getWidth(null),
				playerImage.getHeight(null));		
		staticobjects = s;
		reactiveobjects = r;
		lineBottom = new Line2D.Double(0.0, 600.0, 800.0, 600.0);
		lineTop = new Line2D.Double(0.0, 0.0, 800.0, 0.0);
		timer = new Timer(50, this);
		timer.addActionListener(this);
		timer.start();
		
		this.enemies = enemies;
		
				
		
	}
	

	public void loadAnnimationImages() {
		ImageIcon tempImageIcon;
		annimationImages = new Image[6]; // this function allocates space in the
											// memory for the images of
		tempImageIcon = new ImageIcon("images/player/Worm4.png"); // the player and
															// retrieves the
															// images from
															// the file system
		annimationImages[0] = tempImageIcon.getImage(); // images with index 0
														// and 1 are for moving
														// right and 2 and 3 are
		tempImageIcon = new ImageIcon("images/player/Worm5.png"); // for moving left
		annimationImages[1] = tempImageIcon.getImage();
		tempImageIcon = new ImageIcon("images/player/Worm6.png");
		annimationImages[2] = tempImageIcon.getImage();
		tempImageIcon = new ImageIcon("images/player/Worm1.png");
		annimationImages[3] = tempImageIcon.getImage();
		tempImageIcon = new ImageIcon("images/player/Worm2.png");
		annimationImages[4] = tempImageIcon.getImage();
		tempImageIcon = new ImageIcon("images/player/Worm3.png");
		annimationImages[5] = tempImageIcon.getImage();

	}

	// getters and setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void addX(int i) {
		this.x += i;
	}

	public void addY(int i) {
		this.y += i;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setInjump(boolean x) {
		this.injump = x;
	}

	public void setJumpSpeed(int x) {
		this.jumpSpeed = x;
	}

	public void setGravitySpeed(int x) {
		this.gravitySpeed = x;
	}

	public void setCurrentImageIndex(int x) {
		this.currentImageIndex = x;
	}

	public void setDirectionRight(boolean x) {
		this.directionRight = x;
	}

	public int getGrenadesAvailable() {
		return grenadesAvailable;
	}

	public void setGrenadesAvailable(int grenadesAvailable) {
		if (grenadesAvailable >= 0)
			this.grenadesAvailable = grenadesAvailable;
		else
			this.grenadesAvailable = 0;
	}

	public int getMissilesAvailable() {
		return missilesAvailable;
	}

	public void setMissilesAvailable(int missilesAvailable) {
		if (missilesAvailable >= 0)
			this.missilesAvailable = missilesAvailable;
		else
			this.missilesAvailable = 0;
	}

	// getters and setters

	public void hasHit(String type) {
		int a =0;
		if(type=="gold"){
			a=1;
		}else{if(type=="diamond"){
			a=2;
		}else{if(type=="heart"){
			a=3;
		}}}
		
		switch (a) {
		case 1:
			this.grenadesAvailable += 2;
			this.missilesAvailable += 5;
			break;
		case 2:
			this.grenadesAvailable += 5;
			this.missilesAvailable += 10;
			break;
		case 3:
			if (this.playerHealth <= 80)
				this.playerHealth += 20;
			else
				this.playerHealth = 100;
			break;
		}

	}

	public void getsHit(int damage) {
		SoundEffect.DAMAGE.play();
		this.playerHealth -= damage;
		if (this.playerHealth < 0)
			this.playerHealth = 0;
	}

	public boolean checkCollisionRight(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		
		for(int i = 0; i<4; i++){
			if (enemies.get(i) != null){
				if (playerRectangleRight.intersects(enemies.get(i).CollisionRectangle)){
					return true;
				}
			}	
		}
		for (int i = 0; i < s.size(); i++) {
			if (playerRectangleRight.intersects(s.get(i).rectangle)) {

				// this function checks for collision
				return true; // to the right of the player
			} // it uses playerRectangleRight rectangle
		} // which extends to the right of the player
		for (int i = 0; i < r.size(); i++){ 
			if (playerRectangleRight.intersects(r.get(i).rectangle)) {

				// new
				Rectangle temp = new Rectangle(x, y,
						playerImage.getWidth(null) + 5,
						playerImage.getHeight(null) - 6);
				boolean sum = true;
				for (int j = 0; j < r.size(); j++) {

					if (temp.intersects(r.get(j).rectangle)) {
						sum = sum & false;
						return true;
					}
				}
				if (sum) {
					this.addX(3);
					this.addY(-6);
					this.updateRectangle();
				}
				// new

				return true;
			}
		}
		return false;
	}

	public boolean checkCollisionLeft(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		
		for(int i = 0; i<4; i++){
			if (enemies.get(i) != null){
				if (playerRectangleLeft.intersects(enemies.get(i).CollisionRectangle)){
					return true;
				}
			}
		}
			
		for (int i = 0; i < s.size(); i++) { // same as checkCollisonRight
			if (playerRectangleLeft.intersects(s.get(i).rectangle)) {

				return true;
			}
		}
		for (int i = 0; i < r.size(); i++) {
			if (playerRectangleLeft.intersects(r.get(i).rectangle)) {
				// new
				Rectangle temp = new Rectangle(x - 5, y,
						playerImage.getWidth(null) + 5,
						playerImage.getHeight(null)-6);
				boolean sum = true;
				for (int j = 0; j < r.size(); j++) {

					if (temp.intersects(r.get(j).rectangle)) {
						sum = sum & false;
						return true;
					}
				}
				if (sum) {
					this.addX(-3);
					this.addY(-6);
					this.updateRectangle();
				}
				// new
				return true;
			}
		}
		return false;
	}

	public boolean checkCollisionJump(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		if (this.playerRectangleJump.intersectsLine(this.lineTop))
			return true;

		for(int i = 0; i<4; i++){
			if (enemies.get(i) != null){
				if (playerRectangleJump.intersects(enemies.get(i).CollisionRectangle)) {
					this.setY(enemies.get(i).y + enemies.get(i).playerImage.getHeight(null) + 1);
					return true;
				}
			}
		}

		for (int i = 0; i < s.size(); i++) {
			if (playerRectangleJump.intersects(s.get(i).rectangle)) // this
																	// function
																	// is
																	// similar
																	// to the
																	// functions
																	// used to
																	// check for
																	// right and
																	// left
			{ // collision
				this.setY(s.get(i).y + s.get(i).imageHeight + 1);
				return true;
			}
		}

		for (int i = 0; i < r.size(); i++) {
			if (playerRectangleJump.intersects(r.get(i).rectangle)) {
				this.setY(r.get(i).y + r.get(i).imageHeight + 1);
				return true;
			}
		}
		return false;
	}

	public boolean checkCollisionDown(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		if (this.playerRectangleDown.intersectsLine(this.lineBottom))
			return true;
		// same as for checkCollisionDown
		for(int i = 0; i<4; i++){
			if (enemies.get(i) != null){
				if (playerRectangleDown.intersects(enemies.get(i).CollisionRectangle)) {
				
				return true;
				}
			}
		}

		for (int i = 0; i < s.size(); i++) {
			if (playerRectangleDown.intersects(s.get(i).rectangle)) {
				
				return true;
			}
		}

		for (int i = 0; i < r.size(); i++) {
			if (playerRectangleDown.intersects(r.get(i).rectangle)) {
				
				return true;
			}
		}
		return false;
	}

	public void moveRight(int amount) {
		this.setDirectionRight(true);

		if (playerImage.equals(annimationImages[0]) | playerImage.equals(annimationImages[3]) | playerImage.equals(annimationImages[4]) | playerImage.equals(annimationImages[5])) {
			playerImage = annimationImages[1];
		}else if (playerImage.equals(annimationImages[1])) {
			playerImage = annimationImages[2];				
		}else if (playerImage.equals(annimationImages[2])) {
			playerImage = annimationImages[0];	
		}
		
					
		
		

		if (this.x < 950 & this.x>0) // this functions moves the player to the right
			if (checkCollisionRight(staticobjects, reactiveobjects) == false) {
				this.addX(amount);
				this.updateRectangle();

			}

	}

	public void moveLeft(int amount) {
		directionRight = false;
		if (playerImage.equals(annimationImages[3]) | playerImage.equals(annimationImages[0]) | playerImage.equals(annimationImages[1]) | playerImage.equals(annimationImages[2]))
			playerImage = annimationImages[4];
		else if(playerImage.equals(annimationImages[4])){
			playerImage = annimationImages[5]; 
		}else if(playerImage.equals(annimationImages[5])){
			playerImage = annimationImages[3]; 
		}											
		
		if (this.x > 0 & this.x < 950)
			if (checkCollisionLeft(staticobjects, reactiveobjects) == false) {
				this.addX(-amount);
				this.updateRectangle();

			}

	}

	public void startJump(int a) {
		this.setInjump(true); // this starts a jump of the player
		if (a == 0) {
			SoundEffect.WORMPOP.play();
			this.setJumpSpeed(jumpSpeedBound);
			jumpQuantity = 1;
			canDoubleJump = true;
		} else if (a == 1 && canDoubleJump == true) {
			SoundEffect.BACKFLIP.play();
			jumpQuantity = -2;
			jumpSpeed += jumpSpeedBound;
			canDoubleJump = false;
		}
	}

	public void jump() {
		if (jumpQuantity == 1 | jumpQuantity == -2) {
			if (jumpSpeed != 0)
				if (checkCollisionJump(staticobjects, reactiveobjects) == false) // this
																					// is
																					// actually
																					// the
																					// function
																					// that
																					// implements
																					// the
																					// logic
																					// behind
				{ // the jump
					this.addY(-jumpSpeed);
					if (directionRight == true) {
						moveRight(jumpQuantity);
					} else {
						moveLeft(jumpQuantity);
					}
					this.setJumpSpeed(jumpSpeed - 1);
					this.updateRectangle();
				} else
					this.setJumpSpeed(0);

			else
				this.setInjump(false);
		}
	}

	public void applyGravity() {
		if (checkCollisionDown(staticobjects, reactiveobjects) == false) // this
																			// applies
																			// gravity
																			// to
																			// the
																			// player
		{
			gravityIncrment = 0;
			this.addY(gravitySpeed);
			if (gravitySpeed < gravitySpeedBound)
				this.setGravitySpeed(gravitySpeed + 1);
			this.updateRectangle();
		} else{
			if(gravitySpeed>0){
		//		SoundEffect.WORMIMPACT.play();
			}
			this.setGravitySpeed(0);
		}

	}

	public void updateRectangle() {
		playerRectangleLeft = new Rectangle(x - 5, y,
				playerImage.getWidth(null) + 5, playerImage.getHeight(null));
		playerRectangleRight = new Rectangle(x, y,
				playerImage.getWidth(null) + 5, playerImage.getHeight(null));
		playerRectangleDown = new Rectangle(x + 4, y,
				playerImage.getWidth(null) - 8, playerImage.getHeight(null) + 5); // collision
		playerRectangleJump = new Rectangle(this.x + 4, this.y - jumpSpeed,
				playerImage.getWidth(null) - 8, 10);
		CollisionRectangle = new Rectangle(this.x, this.y,
				this.playerImage.getWidth(null),
				this.playerImage.getHeight(null));
	}

	public void updateSystemVariables(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, ArrayList<MainPlayer> enemies ) {
		this.staticobjects = s;
		this.reactiveobjects = r;
		
		this.enemies = enemies;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateRectangle();
		if (injump == false) {
			gravityIncrment++;
			applyGravity();
			jumpQuantity = 0;
		} else
			jump();
	}

}
