import java.util.Scanner;
public class Factorial
{
    
    static int fact(int x)
    {
        if(x == 0)
        {
            return 1;
        }
		else
			return(x * fact(x - 1));
        
    }
	
	public static void main(String[] args) 
    {
       
        Scanner s = new Scanner(System.in);
        System.out.print("Enter an integer:");
        int num = s.nextInt();
        
        int factorial = fact(num);
        System.out.println("Factorial of "+num+" is "+factorial);
    }
}