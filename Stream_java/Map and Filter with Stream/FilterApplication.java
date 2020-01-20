import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilterApplication {
	public static void main(String[] args) {

		List<Student> student = new ArrayList<>();
		student.add(new Student("Jack", 90));
		student.add(new Student("Ann", 20));
		student.add(new Student("Daisy", 80));
		student.add(new Student("charles", 60));
		student.add(new Student("John", 40));
		

		List<Student> students = student.stream().map(s -> new Student(s.getName(), s.getMark()))
				.filter(s -> s.getMark() > 60).sorted(Comparator.comparing(Student::getName))
				.collect(Collectors.toList());

		System.out.println(students);

	}
}