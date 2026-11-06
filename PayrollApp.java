import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PayrollApp {
    // Demonstrating Interface Polymorphism: List of Payable objects
    private static ArrayList<Payable> roster = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSampleEmployees();

        boolean running = true;
        System.out.println("=================================================");
        System.out.println("      ENTERPRISE EMPLOYEE PAYROLL SYSTEM         ");
        System.out.println("=================================================");

        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-5): ");
            int choice = readIntChoice();

            switch (choice) {
                case 1:
                    generatePayrollSummary();
                    break;
                case 2:
                    viewDetailedBreakdowns();
                    break;
                case 3:
                    addNewEmployee();
                    break;
                case 4:
                    runAutomatedTestSuite();
                    break;
                case 5:
                    running = false;
                    System.out.println("\nExiting Payroll System. Have a productive day!");
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice. Please select 1 through 5.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void initializeSampleEmployees() {
        try {
            // Salaried: (id, name, dept, weeklySalary, bonus)
            roster.add(new SalariedEmployee("EMP-101", "Aeron Irasusta", "Engineering", 1450.00, 250.00));

            // Hourly with standard hours: (id, name, dept, hourlyWage, hoursWorked)
            roster.add(new HourlyEmployee("EMP-102", "Sarah Jenkins", "Customer Support", 22.50, 40.0));

            // Hourly with overtime hours:
            roster.add(new HourlyEmployee("EMP-103", "Marcus Vance", "Operations", 28.00, 48.5));

            // Commission: (id, name, dept, baseSalary, grossSales, commissionRate)
            roster.add(new CommissionEmployee("EMP-104", "Elena Rostova", "Global Sales", 400.00, 18500.00, 0.07));
        } catch (InvalidPayrollException e) {
            System.out.println("[ERROR] Failed to populate sample employees: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("----------------- MAIN MENU ---------------------");
        System.out.println("1. Generate Company-Wide Payroll Summary");
        System.out.println("2. View Detailed Employee Compensation Breakdown");
        System.out.println("3. Register New Employee");
        System.out.println("4. Run Automated Payroll & Exception Test Suite");
        System.out.println("5. Exit");
        System.out.println("-------------------------------------------------");
    }

    private static void generatePayrollSummary() {
        System.out.println("\n=================================================");
        System.out.println("          COMPANY-WIDE PAYROLL REPORT            ");
        System.out.println("=================================================");

        if (roster.isEmpty()) {
            System.out.println("[INFO] No employees registered in the system.");
            return;
        }

        double totalPayroll = 0.0;
        Payable topEarner = roster.get(0);

        for (int i = 0; i < roster.size(); i++) {
            Payable p = roster.get(i);
            double earnings = p.calculateWeeklyEarnings();
            totalPayroll += earnings;

            if (earnings > topEarner.calculateWeeklyEarnings()) {
                topEarner = p;
            }

            // Polymorphic call to interface method getPaymentSummary()
            System.out.printf("%2d. %s%n", (i + 1), p.getPaymentSummary());
        }

        double averagePayroll = totalPayroll / roster.size();

        System.out.println("-------------------------------------------------");
        System.out.printf("Total Active Payees : %d%n", roster.size());
        System.out.printf("Total Weekly Payroll: $%,.2f%n", totalPayroll);
        System.out.printf("Average Compensation: $%,.2f%n", averagePayroll);
        System.out.printf("Top Earner          : %s ($%,.2f)%n", 
            (topEarner instanceof Employee ? ((Employee) topEarner).getName() : "Payable"),
            topEarner.calculateWeeklyEarnings());
        System.out.println("=================================================");
    }

    private static void viewDetailedBreakdowns() {
        System.out.println("\n--- DETAILED EMPLOYEE COMPENSATION BREAKDOWNS ---");
        for (Payable p : roster) {
            if (p instanceof Employee) {
                // Polymorphic call: executes the subclass's overridden displayEmployeeDetails()
                ((Employee) p).displayEmployeeDetails();
            }
        }
    }

    private static void addNewEmployee() {
        System.out.println("\n--- REGISTER NEW EMPLOYEE ---");
        System.out.println("Select Employee Type:");
        System.out.println("1. Salaried Employee");
        System.out.println("2. Hourly Employee");
        System.out.println("3. Commission Employee");
        System.out.print("Choice (1-3): ");
        int type = readIntChoice();

        System.out.print("Enter Employee ID (e.g. EMP-201): ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Department: ");
        String dept = scanner.nextLine().trim();

        try {
            switch (type) {
                case 1:
                    System.out.print("Enter Base Weekly Salary: $");
                    double salary = readDouble();
                    System.out.print("Enter Bonus: $");
                    double bonus = readDouble();
                    roster.add(new SalariedEmployee(id, name, dept, salary, bonus));
                    System.out.println("[SUCCESS] Salaried employee registered successfully!");
                    break;
                case 2:
                    System.out.print("Enter Hourly Wage: $");
                    double wage = readDouble();
                    System.out.print("Enter Hours Worked this week: ");
                    double hours = readDouble();
                    roster.add(new HourlyEmployee(id, name, dept, wage, hours));
                    System.out.println("[SUCCESS] Hourly employee registered successfully!");
                    break;
                case 3:
                    System.out.print("Enter Base Weekly Draw: $");
                    double base = readDouble();
                    System.out.print("Enter Gross Sales: $");
                    double sales = readDouble();
                    System.out.print("Enter Commission Rate (e.g. 0.05 for 5%): ");
                    double rate = readDouble();
                    roster.add(new CommissionEmployee(id, name, dept, base, sales, rate));
                    System.out.println("[SUCCESS] Commission employee registered successfully!");
                    break;
                default:
                    System.out.println("[ERROR] Invalid employee type selected.");
            }
        } catch (InvalidPayrollException e) {
            System.out.println("[CAUGHT InvalidPayrollException]: " + e.getMessage());
            System.out.println("Employee was not added due to invalid parameters.");
        } catch (IllegalArgumentException e) {
            System.out.println("[CAUGHT IllegalArgumentException]: " + e.getMessage());
        }
    }

    private static void runAutomatedTestSuite() {
        System.out.println("\n=================================================");
        System.out.println("     RUNNING AUTOMATED TEST & EXCEPTION SUITE    ");
        System.out.println("=================================================");

        // Test 1: Hourly Employee overtime calculation
        try {
            HourlyEmployee otEmp = new HourlyEmployee("T-1", "Overtime Tester", "QA", 20.00, 50.0);
            // 40 hrs * $20 = $800 | 10 hrs * $20 * 1.5 = $300 | Total = $1100
            double pay = otEmp.calculateWeeklyEarnings();
            System.out.printf("[Test 1] Overtime calculation (50 hrs @ $20/hr): $%.2f -> %s%n",
                pay, (pay == 1100.0 ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("[Test 1] FAIL with unexpected error: " + e.getMessage());
        }

        // Test 2: Validation of negative wage
        System.out.print("[Test 2] Testing negative wage (-$15.00): ");
        try {
            new HourlyEmployee("T-2", "Invalid Wage", "QA", -15.00, 40.0);
            System.out.println("FAIL (Exception was not thrown)");
        } catch (InvalidPayrollException e) {
            System.out.println("PASS -> Caught: " + e.getMessage());
        }

        // Test 3: Validation of impossible hours (> 168)
        System.out.print("[Test 3] Testing impossible hours (175.0 hrs): ");
        try {
            new HourlyEmployee("T-3", "Tired Worker", "QA", 25.00, 175.0);
            System.out.println("FAIL (Exception was not thrown)");
        } catch (InvalidPayrollException e) {
            System.out.println("PASS -> Caught: " + e.getMessage());
        }

        // Test 4: Validation of impossible commission rate (> 1.0)
        System.out.print("[Test 4] Testing invalid commission rate (1.25 / 125%): ");
        try {
            new CommissionEmployee("T-4", "Greedy Agent", "QA", 500.0, 10000.0, 1.25);
            System.out.println("FAIL (Exception was not thrown)");
        } catch (InvalidPayrollException e) {
            System.out.println("PASS -> Caught: " + e.getMessage());
        }

        System.out.println("=================================================");
        System.out.println("         ALL TESTS COMPLETED SUCCESSFULLY!       ");
        System.out.println("=================================================");
    }

    private static int readIntChoice() {
        try {
            int val = scanner.nextInt();
            scanner.nextLine();
            return val;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static double readDouble() {
        try {
            double val = scanner.nextDouble();
            scanner.nextLine();
            return val;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1.0;
        }
    }
}
