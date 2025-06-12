
/**
 * This is the Teacher class used to store the details of a teacher.
 * author@ Ramesh Sapkota
 * ID: np05cp4a230104 London Met ID: 23049378
 */
public class Teacher {
    // Declaration of attributes of the Teacher class
    private int teacherId;
    private String teacherName;
    private String address;
    private String workingType;
    private String employmentStatus;
    private int workingHours;

    // Constructor of the Teacher class used for initialization of instance
    // variables.
    public Teacher(int teacherId, String teacherName, String address, String workingType, String employmentStatus) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.address = address;
        this.workingType = workingType;
        this.employmentStatus = employmentStatus;
    }

    // Accessor method for each attribute
    public int getTeacherId() {
        return this.teacherId;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getWorkingType() {
        return this.workingType;
    }

    public String getEmploymentStatus() {
        return this.employmentStatus;
    }

    public int getWorkingHours() {
        return this.workingHours;
    }

    // Setter method for workingHours to set the working hours.
    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    // Display method to display the values of attributes
    public void display() {
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Teacher Name: " + teacherName);
        System.out.println("Address: " + address);
        System.out.println("Working Type: " + workingType);
        System.out.println("Employment Status: " + employmentStatus);
        // If working hours is less than or equal to 0, print "Not assigned" else print
        // working hours
        if (workingHours <= 0) {
            System.out.println("Working Hours: Not assigned or enter a valid workingHours.");
        } else {
            System.out.println("Working Hours: " + workingHours);
        }
    }
}