import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int ID;
    private ArrayList<Course> enrolledCourses;
    private ArrayList<Integer> grades; // Store grades for enrolled courses

    public Student(String name, int ID) {
        this.name = name;
        this.ID = ID;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    // Method to enroll a student in a course
    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
        course.enrollStudent();
    }

    // Method to assign a grade to a student for a specific course
    public void assignGrade(Course course, int grade) {
        if (enrolledCourses.contains(course)) {
            int courseIndex = enrolledCourses.indexOf(course);
            grades.set(courseIndex, grade);
            System.out.println("Grade assigned successfully for " + name + " in " + course.getCourseCode());
        } else {
            System.out.println(name + " is not enrolled in " + course.getCourseCode());
        }
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int enrolledStudentsCount;
    private static int totalEnrolledStudents = 0; // Track total enrolled students

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudentsCount = 0;
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    // Method to enroll a student in a course
    public void enrollStudent() {
        if (enrolledStudentsCount < maxCapacity) {
            enrolledStudentsCount++;
            totalEnrolledStudents++;
        } else {
            System.out.println("Course " + courseCode + " is full. Cannot enroll more students.");
        }
    }
}

class CourseManagement {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();

    // Method to add a new course
    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        Course newCourse = new Course(courseCode, courseName, maxCapacity);
        courses.add(newCourse);
    }

    // Method to enroll a student in a course
    public static void enrollStudent(String studentName, int studentID, String courseCode) {
        Course course = findCourse(courseCode);
        if (course != null) {
            Student student = findOrCreateStudent(studentName, studentID);
            if (!student.getEnrolledCourses().contains(course)) {
                student.enrollCourse(course);
                students.add(student);
                System.out.println(studentName + " enrolled in " + courseCode + " successfully.");
            } else {
                System.out.println(studentName + " is already enrolled in " + courseCode + ".");
            }
        } else {
            System.out.println("Course " + courseCode + " not found.");
        }
    }

    // Method to assign a grade to a student for a specific course
    public static void assignGrade(String studentName, String courseCode, int grade) {
        Course course = findCourse(courseCode);
        if (course != null) {
            Student student = findStudentByName(studentName);
            if (student != null) {
                student.assignGrade(course, grade);
            } else {
                System.out.println("Student " + studentName + " not found.");
            }
        } else {
            System.out.println("Course " + courseCode + " not found.");
        }
    }

    // Helper methods...

    // Method to find a specific course by its code
    private static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Method to find or create a student based on name and ID
    private static Student findOrCreateStudent(String studentName, int studentID) {
        for (Student student : students) {
            if (student.getName().equals(studentName) && student.getID() == studentID) {
                return student;
            }
        }
        return new Student(studentName, studentID);
    }

    // Method to find a student by name
    private static Student findStudentByName(String studentName) {
        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                return student;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after reading integer

            switch (choice) {
                case 1:
                    System.out.println("Enter course code:");
                    String code = scanner.nextLine();
                    System.out.println("Enter course name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter max capacity:");
                    int capacity = scanner.nextInt();
                    CourseManagement.addCourse(code, name, capacity);
                    break;
                case 2:
                    System.out.println("Enter student name:");
                    String studentName = scanner.nextLine();
                    System.out.println("Enter student ID:");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.println("Enter course code to enroll:");
                    String courseCode = scanner.nextLine();
                    CourseManagement.enrollStudent(studentName, studentID, courseCode);
                    break;
                case 3:
                    System.out.println("Enter student name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter course code:");
                    String code = scanner.nextLine();
                    System.out.println("Enter grade:");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    CourseManagement.assignGrade(name, code, grade);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}
