import java.util.ArrayList;
import java.util.HashMap;

public class Maps {

	int mapIndex;
	ArrayList<Integer> objectPositionsXAtIndex = new ArrayList<Integer>();
	ArrayList<Integer> objectPositionsYAtIndex = new ArrayList<Integer>();
	ArrayList<Integer> objectTypeAtIndex = new ArrayList<Integer>();

	public Maps(int mapIndex) {
		this.mapIndex = mapIndex;
		switch (mapIndex) {
		case 1:
			loadMap1();
		case 2:
			loadMap2();
		case 3:
			loadMap3();

		}

	}

	public void loadMap1() {
		int[] temp = new int[2];
		// adding static objects
		{

			temp[0] = 100;
			temp[1] = 350;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(0);
			temp[0] = 100;
			temp[1] = 100;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(0);
			temp[0] = 250;
			temp[1] = 470;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(1);
			temp[0] = 600;
			temp[1] = 450;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(1);
			temp[0] = 0;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 60;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 120;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 180;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 240;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 300;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 360;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 420;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 480;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 540;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 600;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 660;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
			temp[0] = 720;
			temp[1] = 570;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(2);
		}
		// adding static objects

		// adding reactive objects
		{
				//map equations ***************************************************
				//*****************************************************************
			for (int j = 570; j > 0; j = j - 2) {
				for (int i = 0; i < 800; i = i + 2) {
					double x = (int) i;
					if ((j>10*Math.sin(x/30)+500) 
							|
							((j > 500 - 150 * Math
							.sqrt((2 * Math.PI) * Math.exp((-x * x / 80000))) & (j < 500 - 1000 * Math
							.sqrt((2 * Math.PI) * Math.exp((-(x-1000) * (x-600) / 80000))))))
							|
							(j>300-(0.0001*(x-700)*(x-700)*(x-700)-0.003*(x-700)*(x-700)))
							|
							((j>38)&(j<250-(0.01*(x-400)*(x-400))))) {

				//*****************************************************************	
				//map equations ***************************************************
						//if(i%2==0 & j%2==0){
							temp[0] = i;
							temp[1] = j;
							this.objectPositionsXAtIndex.add(temp[0]);
							this.objectPositionsYAtIndex.add(temp[1]);
							this.objectTypeAtIndex.add(6);
						//}else if((i+2)%4==0 & (j+2)%4==0){
						//	temp[0] = i;
						//	temp[1] = j;
						//	this.objectPositionsXAtIndex.add(temp[0]);
						//	this.objectPositionsYAtIndex.add(temp[1]);
						//	this.objectTypeAtIndex.add(8);	
						}
					}
				}
			}
			//map equations ***************************************************
			//*****************************************************************
		/*{
		for (int j = 570; j > 0; j = j - 2) {
			for (int i = 0; i < 800; i = i + 2) {
				double x = (int) i;
				if ((j>10*Math.sin(x/30)+500) 
						|
						((j > 500 - 150 * Math
						.sqrt((2 * Math.PI) * Math.exp((-x * x / 80000))) & (j < 500 - 1000 * Math
						.sqrt((2 * Math.PI) * Math.exp((-(x-1000) * (x-600) / 80000))))))
						|
						(j>300-(0.0001*(x-700)*(x-700)*(x-700)-0.003*(x-700)*(x-700)))
						|
						((j>38)&(j<250-(0.01*(x-400)*(x-400))))) {

			//*****************************************************************	
			//map equations ***************************************************
					//if(i%2==0 & j%2==0){
					//	temp[0] = i;
					//	temp[1] = j;
					//	this.objectPositionsXAtIndex.add(temp[0]);
					//	this.objectPositionsYAtIndex.add(temp[1]);
					//	this.objectTypeAtIndex.add(6);
					//}else if((i+2)%4==0 & (j+2)%4==0){
						temp[0] = i;
						temp[1] = j;
						this.objectPositionsXAtIndex.add(temp[0]);
						this.objectPositionsYAtIndex.add(temp[1]);
						this.objectTypeAtIndex.add(8);	
					}
				}
			}
		}	*/
		{
			temp[0] = 200;
			temp[1] = 470;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(3);
			temp[0] = 200;
			temp[1] = 220;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(4);
			temp[0] = 0;
			temp[1] = 140;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(5);
			temp[0] = 600;
			temp[1] = 100;
			this.objectPositionsXAtIndex.add(temp[0]);
			this.objectPositionsYAtIndex.add(temp[1]);
			this.objectTypeAtIndex.add(4);
		}
		// adding reactive objects
	}

	public void loadMap2() {

	}

	public void loadMap3() {

	}

}