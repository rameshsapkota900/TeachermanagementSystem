/**
 * This is the Lecturer class used to store the details of a Lecturer.
 * author@ Ramesh Sapkota
 * ID: np05cp4a230104 London Met ID: 23049378
 */
public class Lecturer extends Teacher {
    // Declaration of attributes of the Lecturer class
    private String department;
    private int yearsOfExperience;
    private int gradedScore;
    private boolean hasGraded;

    // Constructor of the Lecturer class used for initialization of instance
    // variables.
    public Lecturer(int teacherId, String teacherName, String address, String workingType, int workingHours,
            String employmentStatus, String department, int yearsOfExperience) {
        super(teacherId, teacherName, address, workingType, employmentStatus);
        super.setWorkingHours(workingHours); // setting the working hours using the setter method from the superclass
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
        this.gradedScore = 0;
        this.hasGraded = false;
    }

    // Accessor method for each attribute
    public String getDepartment() {
        return this.department;
    }

    public int getYearsOfExperience() {
        return this.yearsOfExperience;
    }

    public int getGradedScore() {
        return this.gradedScore;
    }

    public boolean getHasGraded() {
        return this.hasGraded;
    }

    // Setter method for gradedScore to set the gradedScore.
    public void setGradedScore(int gradedScore) {
        this.gradedScore = gradedScore;
    }

    // Creating a gradeAssignment method to grade a score.
    public void gradeAssignment(int gradedScore, String department, int yearsOfExperience) {
        if (yearsOfExperience >= 5 && this.department.equals(department)) {
            if (gradedScore >= 70) {
                this.gradedScore = gradedScore;
                System.out.println("Assignments graded: A");

            } else if (gradedScore >= 60) {
                this.gradedScore = gradedScore;
                System.out.println("Assignments graded: B");

            } else if (gradedScore >= 50) {
                this.gradedScore = gradedScore;
                System.out.println("Assignments graded: C");

            } else if (gradedScore >= 40) {
                this.gradedScore = gradedScore;
                System.out.println("Assignments graded: D");

            } else {
                this.gradedScore = gradedScore;
                System.out.println("Assignments graded: E");
            }
            hasGraded = true;
            System.out.println("Assignments have been successfully graded.");

        } else {
            System.out.println("Assignment not graded yet.");
        }
    }

    // Display method to display the values of attributes
    public void display() {
        super.display(); // Calling the display method of the superclass (Teacher)
        System.out.println("Department: " + department);
        System.out.println("Years of Experience: " + yearsOfExperience);
        if (hasGraded) {
            System.out.println("Graded Score: " + gradedScore);
        } else {
            System.out.println("Graded Score: Not graded yet");
        }
    }
}