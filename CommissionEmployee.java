public class CommissionEmployee extends Employee {
    private double baseSalary;
    private double grossSales;
    private double commissionRate; // e.g. 0.08 for 8%

    public CommissionEmployee(String employeeId, String name, String department, 
                              double baseSalary, double grossSales, double commissionRate)
            throws InvalidPayrollException {
        super(employeeId, name, department);

        validateBaseSalary(baseSalary);
        validateGrossSales(grossSales);
        validateCommissionRate(commissionRate);

        this.baseSalary = baseSalary;
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    private void validateBaseSalary(double salary) throws InvalidPayrollException {
        if (salary < 0) {
            throw new InvalidPayrollException("baseSalary", salary, "Base salary cannot be negative.");
        }
    }

    private void validateGrossSales(double sales) throws InvalidPayrollException {
        if (sales < 0) {
            throw new InvalidPayrollException("grossSales", sales, "Gross sales cannot be negative.");
        }
    }

    private void validateCommissionRate(double rate) throws InvalidPayrollException {
        if (rate < 0.0 || rate > 1.0) {
            throw new InvalidPayrollException("commissionRate", rate, "Commission rate must be between 0.0 and 1.0 (0% to 100%).");
        }
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) throws InvalidPayrollException {
        validateBaseSalary(baseSalary);
        this.baseSalary = baseSalary;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public void setGrossSales(double grossSales) throws InvalidPayrollException {
        validateGrossSales(grossSales);
        this.grossSales = grossSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) throws InvalidPayrollException {
        validateCommissionRate(commissionRate);
        this.commissionRate = commissionRate;
    }

    @Override
    public String getEmployeeType() {
        return "Commission Employee";
    }

    @Override
    public double calculateWeeklyEarnings() {
        return baseSalary + (grossSales * commissionRate);
    }

    @Override
    public void displayEmployeeDetails() {
        super.displayEmployeeDetails();
        System.out.printf("  [Breakdown] Base: $%.2f | Sales: $%.2f | Comm Rate: %.2f%% (Comm: $%.2f)%n",
            baseSalary, grossSales, commissionRate * 100, (grossSales * commissionRate));
    }
}
