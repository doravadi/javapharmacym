public class Employee {
    private int employeeId;
    private String name;
    private String position;
    private String contactInfo;

    public Employee(int employeeId, String name, String position, String contactInfo) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.contactInfo = contactInfo;
    }

    // Getter and Setter methods
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
