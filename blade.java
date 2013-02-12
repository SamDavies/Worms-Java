
public class blade {
	public void message(String name){ //
	 
	 System.out.println("This is another class: your name is " + name);
	}
	
	private String girlName; //makes a variable for uses only in this class
	
	public blade(String gName){ //sets the girlName equal to the construct for objects in this class
		girlName = gName;
	}
	
	public void saying(){ //prints with %s as the constructor of the object
		 System.out.printf("Your girlfriend is called %s", girlName);
	}
	 	 
 
}
