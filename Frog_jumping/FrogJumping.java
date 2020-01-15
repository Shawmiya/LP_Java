import java.util.Scanner;
class FrogJumping{	
	public static void calculateTime(int distance){
		int time = 0;	
		 if (distance <= 5){
			time = 0;
		 } 
		 while(distance > 5){
			if(distance > 0){
				distance = distance - 5;
				if(distance > 0){
					time = time + 1;
				} 		
			}
			if(distance > 0){
				distance = distance - 3;
				if(distance > 0){
					time = time + 2;
				}
			}
			if(distance > 0){
				distance = distance - 1;
				if(distance > 0){
					time = time + 5;
				}
			}
		 }
		System.out.println("Total time to finish the journey is " + time + "sec");
	}
	
	public static void calculateDistance(int time ){
		
		int distance = 0;
			time =  time - 3;
			int value  = time / 8;
			int mod  = time % 8;
			if(value >= 1){
				distance  = 9 * value;
			}
			if(mod == 5){
				distance = distance + 5;
			} else if(mod == 6 || mod == 7){
				distance = distance + 8;
			} 
			distance = distance + 9;
			System.out.println("Total distance is " + distance + "m");
		}
		
	public static void main(String [] args){
		Scanner s = new Scanner(System.in);
		System.out.print("Do you want to calculate the distance(d) or time(t)? ");
		char ans = s.next().charAt(0);
		
		if(ans == 'd'){
			System.out.print("Enter the distance to calculate the time: ");
			int distance = s.nextInt();
			FrogJumping.calculateTime(distance);
			
		} 
		else if(ans == 't'){
			System.out.print("Enter the time to calculate the distance: ");
			int time  = s.nextInt();
			FrogJumping.calculateDistance(time);	
		}							
	}
}
