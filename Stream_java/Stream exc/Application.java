import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
	public static void main(String[] args) {
		List<Employee> employee = new ArrayList<>();
		employee.add(new Employee("Jack", 25));
		employee.add(new Employee("Ann", 22));
		employee.add(new Employee("Kenn", 30));
		employee.add(new Employee("John", 24));	
				
		List<Employee> filter_employee = employee.stream().filter(e -> e.getName().contains("n")).collect(Collectors.toList());

		List<Employee> map_employee = employee.stream().map(e -> new Employee("Dr." + e.getName(), e.getAge()))
				.collect(Collectors.toList());

		List<Employee> sort_employee = employee.stream().sorted(Comparator.comparing(Employee::getName))
				.collect(Collectors.toList());

		System.out.println(filter_employee);
		System.out.println(map_employee);
		System.out.println(sort_employee);
	}
}