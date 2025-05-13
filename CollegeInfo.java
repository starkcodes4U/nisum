package DAY_2;

class College {
    String collegeName;
    String studentName;

    private int subject1;
    private int subject2;
    private int subject3;

    public void setCollegeInfo(String collegeName, String studentName) {
        this.collegeName = collegeName;
        this.studentName = studentName;
    }

    public void setMarks(int s1, int s2, int s3) {
        subject1 = s1;
        subject2 = s2;
        subject3 = s3;
    }

    public int getTotalMarks() {
        return subject1 + subject2 + subject3;
    }

    public double getAverageMarks() {
        return getTotalMarks() / 3.0;
    }

    public void displayStudentInfo() {
        System.out.println("College: " + collegeName);
        System.out.println("Student: " + studentName);
        System.out.println("Total Marks: " + getTotalMarks());
        System.out.println("Average Marks: " + getAverageMarks());
    }
}

public class CollegeInfo {
    public static void main(String[] args) {
        College student1 = new College();
        student1.setCollegeInfo("KIIT University", "Jagannath Tripathy");
        student1.setMarks(85, 90, 88);

        student1.displayStudentInfo();
    }
}
// Output:
// College: KIIT University
// Student: Jagannath Tripathy
// Total Marks: 263
// Average Marks: 87.66666666666667
// The College class encapsulates the college name, student name, and marks.
// It provides methods to set college information, set marks, calculate total and average marks,
// and display student information.
// The CollegeInfo class creates an instance of the College class, sets the college and student information,
// sets the marks, and displays the student information.
// The program demonstrates the use of encapsulation, methods, and basic arithmetic operations.
// The College class has private fields for subject marks and public methods to set and get information.
// The setMarks method allows setting marks for three subjects, and the getTotalMarks and getAverageMarks
// methods calculate the total and average marks respectively.
