import java.util.ArrayList;
import java.util.HashMap;


public class Maps {

    int mapIndex;
    ArrayList<Integer> objectPositionsXAtIndex=new ArrayList<Integer>();
    ArrayList<Integer> objectPositionsYAtIndex=new ArrayList<Integer>();
    ArrayList<Integer> objectTypeAtIndex=new ArrayList<Integer>();
	public Maps(int mapIndex){
		this.mapIndex=mapIndex;
		switch(mapIndex){
		case 1: loadMap1();
		case 2: loadMap2();
		case 3: loadMap3();
		
		}
		
	}
	
	public void loadMap1(){
		int[] temp=new int[2];
		//adding static objects
		{
		
		
			
			temp[0]=100;temp[1]=350;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(0);
		temp[0]=100;temp[1]=100;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(0);
		temp[0]=250;temp[1]=470;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(1);
		temp[0]=600;temp[1]=450;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(1);
		temp[0]=0;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=60;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=120;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=180;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=240;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=300;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=360;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=420;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=480;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=540;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=600;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=660;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);
		temp[0]=720;temp[1]=570;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(2);}
		//adding static objects
		
		//adding reactive objects
		{
			for(int j=570; j>400; j=j-11){	
				for(int i=400; i<710; i=i+11){
					temp[0]=i;temp[1]=j;
					this.objectPositionsXAtIndex.add(temp[0]);
					this.objectPositionsYAtIndex.add(temp[1]);
					this.objectTypeAtIndex.add(6);
				}
			}	
			temp[0]=200;temp[1]=470;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(3);
		temp[0]=200;temp[1]=220;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(4);
		temp[0]=0;temp[1]=140;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(5);
		temp[0]=600;temp[1]=100;
		this.objectPositionsXAtIndex.add(temp[0]);
		this.objectPositionsYAtIndex.add(temp[1]);
		this.objectTypeAtIndex.add(4);}
		//adding reactive objects
	}
	
	public void loadMap2(){
		
		
	}
	public void loadMap3(){
		
		
	}
	
	

}