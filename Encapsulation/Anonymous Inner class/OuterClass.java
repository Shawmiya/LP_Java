abstract class AnonymousInner{
	public abstract void mymethod();
}

public class OuterClass{
	public static void main(String args[]){
		AnonymousInner inner = new AnonymousInner(){
			public void mymethod(){
				System.out.println("This is Anonymous inner class");
			}
		};
		inner.mymethod();
	}
}