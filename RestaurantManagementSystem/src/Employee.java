import java.io.*;
import java.util.*;

// Abstract Employee class
public abstract class Employee implements Printable {
    private String empId;
    private String name;
    private double salary;

    public Employee(String empId, String name, double salary) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("Name cannot be empty");
        if (salary < 0) throw new InvalidInputException("Salary cannot be negative");
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    public abstract double calculateBonus();

    public double getSalary() { return salary; }
    public String getName() { return name; }
    public String getEmpId() { return empId; }

    @Override
    public void printDetails() {
        System.out.println("Employee ID: " + empId + ", Name: " + name + ", Salary: " + salary + ", Bonus: " + calculateBonus());
    }
}