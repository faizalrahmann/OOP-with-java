import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Student Data Input ===");
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student address: ");
        String studentAddress = scanner.nextLine();

        Student student = new Student(studentName, studentAddress);

        System.out.print("How many courses will the student take? ");
        int studentCourseCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < studentCourseCount; i++) {
            System.out.print("Course " + (i + 1) + " name: ");
            String course = scanner.nextLine();
            System.out.print("Grade for " + course + ": ");
            int grade = Integer.parseInt(scanner.nextLine());
            student.addCourseGrade(course, grade);
        }

        System.out.println();
        System.out.println(student);
        student.printGrades();
        System.out.printf("Average grade: %.2f%n", student.getAverageGrade());

        System.out.println();
        System.out.println("=== Teacher Data Input ===");
        System.out.print("Enter teacher name: ");
        String teacherName = scanner.nextLine();
        System.out.print("Enter teacher address: ");
        String teacherAddress = scanner.nextLine();

        Teacher teacher = new Teacher(teacherName, teacherAddress);

        System.out.print("How many courses does the teacher teach? ");
        int teacherCourseCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < teacherCourseCount; i++) {
            System.out.print("Course " + (i + 1) + " name: ");
            String course = scanner.nextLine();
            if (!teacher.addCourse(course)) {
                System.out.println("Course already exists: " + course);
            }
        }

        System.out.println();
        System.out.println(teacher);
        teacher.printCourses();

        System.out.println();
        System.out.print("Enter a course to remove from the teacher: ");
        String removeCourse = scanner.nextLine();
        if (teacher.removeCourse(removeCourse)) {
            System.out.println("Removed course: " + removeCourse);
        } else {
            System.out.println("Course not found: " + removeCourse);
        }

        System.out.println();
        teacher.printCourses();
        scanner.close();
    }
}
