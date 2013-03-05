import java.awt.Image;
 import java.awt.Rectangle;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.util.ArrayList;

import javax.swing.ImageIcon;
 import javax.swing.Timer;

public class Grenade implements ActionListener {
Image[] annimationImages;             //all of the images for the object
Image currentImage;                   //the image to currently draw on the screen
boolean launchDirectionRight,visible; //true if it is launched to the right and false to the left
int x,y; //x and y coordinate for drawing the image on the screen
int imageWidth,imageHeight; //width and height of the image of the object
Rectangle rectangle; //rectangle to check for collision
Timer timer;
MainPlayer player, enemy;                   //pointers for the two players on the screen -   player is  launching and enemy is the enemy player. 
ArrayList<StaticObjects> staticobjects;     //just pointers to the two arraylists that are created 
ArrayList<ReactiveObjects> reactiveobjects; // in class MainClass
int [][] trajectoryIncrements;              //used to store the trajectory
int trajectoryIndex;                        //this represents the index in trajectoryIncrements that we are currently at i.e int[trajectoryIndex][]

public Grenade(MainPlayer player,MainPlayer enemy,ArrayList<StaticObjects> s,ArrayList<ReactiveObjects> r, boolean launchedRight,int trajectoryRadius) {

}


public void loadAnnimationImages(){

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

public boolean checkCollisionEnemy(){
        if(rectangle.intersects(enemy.playerRectangleRight))
          return true;
    return false;
}


public void move(int[] nextPosIncrements ){   //here you move the object
    }

public void destroy(){
}

public void destroy(ReactiveObjects r){
}

public int[][] calculateTrajectory(int circleRadius)
{   
   //x and y increments for the trajectory hint:you get a length/radius for the trajectory which can be any graph
   //you should return a two-dimensional int array - int[i][j] where i is the index of the current coordinates in relation to the begining of the trajectory
   //int[i][0] is the x-coordinate at index i of the trajectory and int[i][1] is y - now keep in mind that this is an increment i.e. int[i][0] is the increase
   //in x relative to int[i-1][0] and the same for y. for this to be easier to code I reccomend you put int[0][0] and int[0][1] =0 like in the missile class.
   //there is also another way - you can return the points on the screen as the trajectory. In this case you just start with int[0][0]=player.x+player.imageWidth
   //and int[0][1]=player.y +/- c where c is how much below or above the level of the player the weapon should start from and can be 0
   //However in this case you have to check whether the weapon is launched right or left because you are returning the exact points on the screen
   //In class Missile we use the increments method and we don't care if the missile if launched left or right while we are calculating the trajectory
   //but we do that check in the move method whereas in the exact positions method you don't need to do it.Either way it doen's matter so do it the way you like.
    return null;
}

public void updateSystemVariables(ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r,MainPlayer enemyPlayer)
{
    staticobjects=s;
    reactiveobjects=r;                  //this updates the pointers to the objects on the screen - it probably isn't needed but I do it just
    enemy=enemyPlayer;                  //for good measure because it doen't take much computational power
}

public void updateRectangle(){
    this.rectangle=new Rectangle(this.x,this.y,this.currentImage.getWidth(null),this.currentImage.getHeight(null)); //update the rectangle used to check for collision
}

public static void main(String[] args) {    

}

@Override
public void actionPerformed(ActionEvent e) {                //this is called whenever the timer creates an event
                                                            //for example if you set the timer to Timer(20,this)
}                                                           //it's going to fire every 20 milliseconds

 }
