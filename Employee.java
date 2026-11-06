public abstract class Employee implements Payable {
    private String employeeId;
    private String name;
    private String department;

    public Employee(String employeeId, String name, String department) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty.");
        }
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }

        this.employeeId = employeeId.trim();
        this.name = name.trim();
        this.department = department.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    /**
     * Subclasses must return their specific role designation
     * (e.g. "Salaried Employee", "Hourly Employee", "Commission Employee").
     */
    public abstract String getEmployeeType();

    // calculateWeeklyEarnings() is inherited from Payable and left abstract
    // for subclasses to provide specific mathematical formulas.

    @Override
    public String getPaymentSummary() {
        return String.format("[%s] %s (ID: %s, Dept: %s) -> Weekly Pay: $%.2f",
            getEmployeeType(), name, employeeId, department, calculateWeeklyEarnings());
    }

    public void displayEmployeeDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("Role        : " + getEmployeeType());
        System.out.println("ID          : " + employeeId);
        System.out.println("Name        : " + name);
        System.out.println("Department  : " + department);
        System.out.printf("Weekly Pay  : $%.2f%n", calculateWeeklyEarnings());
        System.out.println("--------------------------------------------------");
    }
}
