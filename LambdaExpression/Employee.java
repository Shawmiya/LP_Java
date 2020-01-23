package comparatorSample;

public class Employee {
	String name;
	int Marks;
	
	public Employee(String name,int marks) {
		this.name = name;
		Marks = marks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getMarks() {
		return Marks;
	}

	public void setMarks(int marks) {
		Marks = marks;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", Marks=" + Marks + "]";
	}

}
