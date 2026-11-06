public class SalariedEmployee extends Employee {
    private double weeklySalary;
    private double bonus;

    public SalariedEmployee(String employeeId, String name, String department, double weeklySalary, double bonus)
            throws InvalidPayrollException {
        super(employeeId, name, department);

        if (weeklySalary < 0) {
            throw new InvalidPayrollException("weeklySalary", weeklySalary, "Weekly salary cannot be negative.");
        }
        if (bonus < 0) {
            throw new InvalidPayrollException("bonus", bonus, "Bonus cannot be negative.");
        }

        this.weeklySalary = weeklySalary;
        this.bonus = bonus;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) throws InvalidPayrollException {
        if (weeklySalary < 0) {
            throw new InvalidPayrollException("weeklySalary", weeklySalary, "Weekly salary cannot be negative.");
        }
        this.weeklySalary = weeklySalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) throws InvalidPayrollException {
        if (bonus < 0) {
            throw new InvalidPayrollException("bonus", bonus, "Bonus cannot be negative.");
        }
        this.bonus = bonus;
    }

    @Override
    public String getEmployeeType() {
        return "Salaried Employee";
    }

    @Override
    public double calculateWeeklyEarnings() {
        return weeklySalary + bonus;
    }

    @Override
    public void displayEmployeeDetails() {
        super.displayEmployeeDetails();
        System.out.printf("  [Breakdown] Base Salary: $%.2f | Bonus: $%.2f%n", weeklySalary, bonus);
    }
}
