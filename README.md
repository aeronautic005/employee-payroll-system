# Employee Payroll & Compensation System

An Object-Oriented Java application modeling an enterprise payroll system utilizing Abstract Classes, Interfaces, Subclass Polymorphism, Encapsulation, and Custom Exceptions.

## Architecture & Concepts

- **Interface (`Payable.java`):** Declares contract for any entity that receives payment (`calculateWeeklyEarnings()`).
- **Custom Exception (`InvalidPayrollException.java`):** Validates salary, wage, sales, and working hours constraints.
- **Abstract Base Class (`Employee.java`):** Encapsulates core employee metadata (`id`, `name`, `department`) and mandates salary calculation.
- **Subclasses:**
  - `SalariedEmployee.java`: Fixed weekly rate.
  - `HourlyEmployee.java`: Hourly wage with 1.5x overtime multiplier for hours exceeding 40.
  - `CommissionEmployee.java`: Base pay plus percentage commission on gross sales.
- **Main Driver (`PayrollApp.java`):** Interactive menu, company-wide payroll reporting, and polymorphic paycheck processing.

## How to Compile & Run

```bash
# Compile all files
javac *.java

# Run the program
java PayrollApp
```
