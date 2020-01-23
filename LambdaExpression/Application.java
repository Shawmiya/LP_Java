package comparatorSample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Application implements Comparator<Employee> {

	public static void main(String[] args) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("Vijay", 87));
		employees.add(new Employee("Ajay", 35));
		employees.add(new Employee("Jai", 55));

		Comparator<Employee> employeeComparator = (e1,
				e2) -> (e1.getMarks() > e2.getMarks() ? +1 : e1.getMarks() < e2.getMarks() ? -1 : 0);

		Collections.sort(employees, employeeComparator);

		System.out.println(employees);
	}

	@Override
	public int compare(Employee emp1, Employee emp2) {
		if (emp1.getMarks() > emp2.getMarks())
			return +1;
		if (emp1.getMarks() < emp2.getMarks())
			return -1;
		return 0;
	}

}
