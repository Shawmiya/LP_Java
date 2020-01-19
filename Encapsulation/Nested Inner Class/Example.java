class Outer{
	int num;
	private class Inner{
		public void print(){
			System.out.println("It is an inner class");
		}
	}
	void displayInner(){
		Inner inner = new Inner();
		inner.print();
	}
}

public class Example{
	public static void main(String args[]){
		Outer outer = new Outer();
		outer.displayInner();
	}
}