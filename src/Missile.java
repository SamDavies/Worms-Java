import java.util.ArrayList;

import javax.swing.ImageIcon;



public class Missile extends Weapon {	
				
	
	

		public Missile(MainPlayer p, ArrayList<MainPlayer> enemies, ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, boolean l, double velocity,
			double angle) {
		super(p, enemies, s, r, l, velocity, angle);
		
		loadAnnimationImagesLeft();
		loadAnnimationImagesRight();
		
		}
		
		@Override
		public void loadAnnimationImages(){
			ImageIcon tempImageIcon=new ImageIcon("images/weapons/missile100.png");
			this.currentImage=tempImageIcon.getImage();
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
		

	
}
