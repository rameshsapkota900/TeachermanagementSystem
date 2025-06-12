
/**
 * This is the Tutor class used to store the details of a Tutor.
 * author@ Ramesh Sapkota
 * ID: np05cp4a230104 London Met ID: 23049378
 */
public class Tutor extends Teacher {
    // Declaration of attributes of the Tutor class
    private double salary;
    private String specialization;
    private String academicQualifications;
    private int performanceIndex;
    private boolean isCertified;

    // Constructor of the Tutor class used for initialization of instance variables.
    public Tutor(int teacherId, String teacherName, String address, String workingType, String employmentStatus,
            int workingHours, double salary, String specialization, String academicQualifications,
            int performanceIndex) {

        super(teacherId, teacherName, address, workingType, employmentStatus);
        super.setWorkingHours(workingHours);
        this.salary = salary;
        this.specialization = specialization;
        this.academicQualifications = academicQualifications;
        this.performanceIndex = performanceIndex;
        this.isCertified = false;
    }

    // Accessor method for each attribute
    public double getSalary() {
        return this.salary;
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public String getAcademicQualifications() {
        return this.academicQualifications;
    }

    public int getPerformanceIndex() {
        return this.performanceIndex;
    }

    public boolean getIsCertified() {
        return this.isCertified;
    }

    /*
     * *Sets the salary of the Tutor based on the performance index and working
     * hours.
     * If the performance index is greater than 5 and working hours are greater than
     * 20,
     * the salary is increased based on the performance index.
     */

    public void setSalary(double salary, int performanceIndex) {
        if (performanceIndex > 5 && super.getWorkingHours() > 20) {
            double appraisal = 0;

            if (performanceIndex >= 5 && performanceIndex <= 7) {
                appraisal = 5.0 / 100; // 5/100 = 0.05

            } else if (performanceIndex >= 8 && performanceIndex <= 9) {
                appraisal = 10.0 / 100; // 10/100 = 0.1

            } else if (performanceIndex == 10) {
                appraisal = 20.0 / 100; // 20/100 = 0.2
            }
            this.salary = salary + (appraisal * salary);
            this.isCertified = true;

        } else {
            System.out.println("Salary cannot be approved. Tutor is not certified yet.");
        }
    }

    // RemoveTutor method to remove the values of attributes
    public void removeTutor() {
        if (!isCertified) {
            this.salary = 0;
            this.specialization = "";
            this.academicQualifications = "";
            this.performanceIndex = 0;
            this.isCertified = false;
        } else {
            System.out.println("Tutor cannot be removed. Tutor is certified.");
        }
    }

    // Display method to display the values of attributes
    public void display() {
        super.display(); // Calling the display method of the superclass (Teacher)
        if (isCertified) {
            System.out.println("Salary: $" + salary);
            System.out.println("Specialization: " + specialization);
            System.out.println("Academic Qualifications: " + academicQualifications);
            System.out.println("Performance Index: " + performanceIndex);
        }
    }
}