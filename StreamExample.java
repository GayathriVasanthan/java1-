import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    int age;
    double grade;

    Student(String n, int a, double g) {
        name = n;
        age = a;
        grade = g;
    }

    public String toString() {
        return name + " (" + age + ") - " + grade;
    }
}

public class StreamExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 85.5),
            new Student("Bob", 22, 90.0),
            new Student("Charlie", 20, 70.0),
            new Student("David", 22, 90.0),
            new Student("Eve", 21, 88.0)
        );

        // Filter students older than 20 and sort by grade descending
        List<Student> filtered = students.stream()
                .filter(s -> s.age > 20)
                .sorted(Comparator.comparingDouble(Student::getGrade).reversed())
                .collect(Collectors.toList());

        System.out.println("Filtered & Sorted:");
        filtered.forEach(System.out::println);

        // Group students by age
        Map<Integer, List<Student>> groupedByAge = students.stream()
                .collect(Collectors.groupingBy(s -> s.age));

        System.out.println("\nGrouped by age:");
        groupedByAge.forEach((age, group) -> {
            System.out.println(age + ": " + group);
        });
    }
}
