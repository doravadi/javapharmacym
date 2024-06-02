public class EmployeePerformance {
    private int employeeId;
    private String name;
    private String position;
    private String performanceStatus;

    public EmployeePerformance(int employeeId, String name, String position, String performanceStatus) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.performanceStatus = performanceStatus;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getPerformanceStatus() {
        return performanceStatus;
    }
}
