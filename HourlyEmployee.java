public class HourlyEmployee extends Employee {
    public static final double STANDARD_HOURS = 40.0;
    public static final double OVERTIME_MULTIPLIER = 1.5;

    private double hourlyWage;
    private double hoursWorked;

    public HourlyEmployee(String employeeId, String name, String department, double hourlyWage, double hoursWorked)
            throws InvalidPayrollException {
        super(employeeId, name, department);

        validateHourlyWage(hourlyWage);
        validateHoursWorked(hoursWorked);

        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    private void validateHourlyWage(double wage) throws InvalidPayrollException {
        if (wage < 0) {
            throw new InvalidPayrollException("hourlyWage", wage, "Hourly wage cannot be negative.");
        }
    }

    private void validateHoursWorked(double hours) throws InvalidPayrollException {
        if (hours < 0 || hours > 168.0) {
            throw new InvalidPayrollException("hoursWorked", hours, "Hours worked must be between 0.0 and 168.0 hours per week.");
        }
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) throws InvalidPayrollException {
        validateHourlyWage(hourlyWage);
        this.hourlyWage = hourlyWage;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) throws InvalidPayrollException {
        validateHoursWorked(hoursWorked);
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getEmployeeType() {
        return "Hourly Employee";
    }

    @Override
    public double calculateWeeklyEarnings() {
        if (hoursWorked <= STANDARD_HOURS) {
            return hoursWorked * hourlyWage;
        } else {
            double regularPay = STANDARD_HOURS * hourlyWage;
            double overtimeHours = hoursWorked - STANDARD_HOURS;
            double overtimePay = overtimeHours * hourlyWage * OVERTIME_MULTIPLIER;
            return regularPay + overtimePay;
        }
    }

    @Override
    public void displayEmployeeDetails() {
        super.displayEmployeeDetails();
        double regularHours = Math.min(hoursWorked, STANDARD_HOURS);
        double overtimeHours = Math.max(0, hoursWorked - STANDARD_HOURS);
        System.out.printf("  [Breakdown] Rate: $%.2f/hr | Reg Hours: %.1f | OT Hours (1.5x): %.1f%n",
            hourlyWage, regularHours, overtimeHours);
    }
}
