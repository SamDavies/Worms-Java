import java.util.ArrayList;


public class Grenade extends Weapon {
	
	
	
	public Grenade(MainPlayer p, ArrayList<MainPlayer> enemies,
			ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r,
			boolean l, double velocity, double angle) {
		super(p, enemies, s, r, l, velocity, angle);
		// TODO Auto-generated constructor stub
		
		
		
		
		
	}

	@override
	public void destroy() { // applies the explosion images and loops through
		if(timeToExplode<100){
		SoundEffect.WORMIMPACT.play();		
		this.y=y-8;
		updateRectangle();						
		trajectoryIncrements = calculateTrajectory(1000, angle);
		trajectoryIndex = 0;
		}else{
			grenadeDestroy();
		}
		
	}
	
	public void grenadeDestroy(){		
		destroyRadius();
		isDestroyed = true;
		timer.stop();
		this.x -= 50;
		this.y -= 50;
		timer.setDelay(100);
		timer.start();
	}
	
	@override
	public boolean checkCollisionEnemy() {
		for(int i=0; i<4; i++){
			if (rectangle.intersects(enemies.get(i).CollisionRectangle)) {
				if (this.isDestroyed == false) {					
					this.hasHitEnemyDirectly = true;
				//} else if (this.hasHitEnemyDirectly == false){
				//	enemy.getsHit(15);					
				}
				return true;
			}
		}
		return false;
	}
	
}
