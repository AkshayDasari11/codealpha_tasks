import java.util.ArrayList;
import java.util.Scanner;

class Student{
    private int studentid;
    private String studentname;
    private double marks;
    Student(int studentid,String studentname,double marks){
        this.studentid=studentid;
        this.studentname=studentname;
        this.marks=marks;
    }

    int getStudentId() {
        return studentid;
    }

    String getStudentName() {
        return studentname;
    }

    double getMarks() {
        return marks;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();
        int Choice=0;
        Scanner sc = new Scanner(System.in);
        do {

            System.out.println("=======================");
            System.out.println(" STUDENT GRADE TRACKER");
            System.out.println("=======================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Calculate Average");
            System.out.println("4. Show Highest Marks");
            System.out.println("5. Show Lowest Marks");
            System.out.println("6. Student Summary Report");
            System.out.println("7. Exit");
            System.out.println("=======================");
            System.out.println("Enter your choice:");

            if(sc.hasNextInt()){
                Choice = sc.nextInt();

            }else{
                System.out.println("Invalid input! Enter a number.");
                sc.next();
                continue;
            }


            switch (Choice) {

                case 1:

                    int id;
                    boolean exists;

                    while(true){
                        System.out.println("Enter Student ID:");
                        id = sc.nextInt();
                        exists=false;

                        for(Student student: students){
                            if(student.getStudentId()==id){

                                exists=true;
                                break;
                            }
                        }
                        if(!exists){
                            break;
                        }
                        System.out.println("Student ID already exists! Try another ID.");

                    }
                    sc.nextLine();

                    System.out.println("Enter Student Name:");
                    String name = sc.nextLine();

                    double marks;

                    while (true) {
                        System.out.println("Enter Marks (0-100):");
                        marks = sc.nextDouble();

                        if (marks >= 0 && marks <= 100) {
                            break;
                        } else {
                            System.out.println("Invalid marks! Enter marks between 0 and 100.");
                        }
                    }


                    Student s = new Student(id, name, marks);

                    students.add(s);

                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students found!");
                    } else {
                        System.out.println("========== STUDENT LIST ==========");

                        for (Student student : students) {
                            System.out.println("ID: " + student.getStudentId());
                            System.out.println("Name: " + student.getStudentName());
                            System.out.println("Marks: " + student.getMarks());
                            System.out.println("--------------------------------");
                        }
                    }
                        break;

                        case 3:

                            if(students.isEmpty()){
                                System.out.println("No students found!");
                            }
                            else{
                                double total=0;

                                for(Student student:students){
                                    total+=student.getMarks();
                                }
                                double average=total/students.size();
                                System.out.println("Average Marks: "+average);
                            }
                            break;

                        case 4:
                            if(students.isEmpty()){
                                System.out.println("No students found!");
                            }
                            else{
                                double highest=students.get(0).getMarks();

                                for(Student student:students){
                                    if(student.getMarks()>highest){
                                        highest=student.getMarks();
                                    }
                                }
                                System.out.println("Highest Marks: "+highest);
                            }
                            break;

                        case 5:
                            if(students.isEmpty()){
                                System.out.println("No students found!");
                            }
                            else{
                                double lowest=students.get(0).getMarks();

                                for(Student student:students){
                                    if(student.getMarks()<lowest){
                                        lowest=student.getMarks();
                                    }
                                }
                                System.out.println("Lowest Marks: "+lowest);
                            }
                            break;

                        case 6:
                            if(students.isEmpty()){
                                System.out.println("No students found!");
                            }
                            else{
                                System.out.println("========== STUDENT SUMMARY REPORT ==========");
                                for(Student student:students){
                                    System.out.println("ID : "+student.getStudentId());
                                    System.out.println("Name : "+student.getStudentName());
                                    System.out.println("Marks : "+student.getMarks());
                                    System.out.println("-----------------------------");
                                }
                            }

                            int totalStudents = students.size();
                            double totalMarks = 0;

                            for (Student student : students) {
                                totalMarks += student.getMarks();
                            }

                            double average = totalMarks / totalStudents;

                            System.out.println("Total Students: " + totalStudents);
                            System.out.printf("Average Marks: %.2f%n",average);

                            double highest = students.get(0).getMarks();
                            double lowest = students.get(0).getMarks();

                            for (Student student : students) {

                                if (student.getMarks() > highest) {
                                    highest = student.getMarks();
                                }

                                if (student.getMarks() < lowest) {
                                    lowest = student.getMarks();
                                }

                            }
                            System.out.println("Highest Marks: " + highest);
                            System.out.println("Lowest Marks: " + lowest);

                            break;

                        case 7:
                            System.out.println("Exiting Program..");
                              break;

                default:
                            System.out.println("Invalid Choice");



            }


        }while(Choice != 7) ;
    }
}
