public class Teacher extends Person {
    private int numCourses = 0;
    private String[] courses = new String[10];

    public Teacher(String name, String address) {
        super(name, address);
    }

    public boolean addCourse(String course) {
        if (hasCourse(course)) {
            return false;
        }
        if (numCourses < courses.length) {
            courses[numCourses++] = course;
            return true;
        }
        return false;
    }

    public boolean removeCourse(String course) {
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                for (int j = i; j < numCourses - 1; j++) {
                    courses[j] = courses[j + 1];
                }
                courses[--numCourses] = null;
                return true;
            }
        }
        return false;
    }

    private boolean hasCourse(String course) {
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }

    public void printCourses() {
        if (numCourses == 0) {
            System.out.println("No courses assigned.");
            return;
        }
        System.out.println("Courses taught by " + getName() + ":");
        for (int i = 0; i < numCourses; i++) {
            System.out.println("- " + courses[i]);
        }
    }

    @Override
    public String toString() {
        return "Teacher: " + super.toString();
    }
}
