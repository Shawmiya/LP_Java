public class MultiplicationTable {
     
    public static void printMultiplicationTable(int n) {
        System.out.format("      ");
        for(int i = 1; i<=n;i++ ) {
            System.out.format("%4d",i);
        }
        System.out.println();
        System.out.println("------------------------------------------");
         
        for(int i = 1 ;i<=n;i++) {
            System.out.format("%4d |",i);
            for(int j=1;j<=n;j++) {
                System.out.format("%4d",i*j);
            }
            System.out.println();
        }
    }
	
	public static void main(String[] args) {
        int n = 9;
        printMultiplicationTable(n);
    }
     
     
}