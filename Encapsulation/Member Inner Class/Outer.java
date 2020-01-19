public class Outer{
	void my_Method(){
		int num = 10;
		class MethodInner{
			public void print(){
				System.out.println ("Method for inner classes "+num);
			}
		}
		MethodInner inner = new MethodInner();
		inner.print();
	}
	public static void main(String args[]){
		Outer o = new Outer();
		o.my_Method();
	}
}