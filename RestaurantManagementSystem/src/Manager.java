// Manager class inheriting Employee
public class Manager extends Employee {
    public Manager(String empId, String name, double salary) throws InvalidInputException {
        super(empId, name, salary);
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.20; // 20% bonus
    }
}