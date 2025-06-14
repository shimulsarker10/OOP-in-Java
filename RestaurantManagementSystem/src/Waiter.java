// Waiter class inheriting Employee
public class Waiter extends Employee {
    public Waiter(String empId, String name, double salary) throws InvalidInputException {
        super(empId, name, salary);
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.10; // 10% bonus
    }
}