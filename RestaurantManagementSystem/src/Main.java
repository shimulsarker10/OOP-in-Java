import java.io.*;
import java.util.*;

// Restaurant Management System with User Input
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Employee> employees = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<FoodItem> menu = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nRestaurant Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Customer");
            System.out.println("3. Add Menu Item");
            System.out.println("4. Place Order");
            System.out.println("5. View All Details");
            System.out.println("6. Save Orders to File");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();
            try {
                switch (choice) {
                    case 1: addEmployee(); break;
                    case 2: addCustomer(); break;
                    case 3: addMenuItem(); break;
                    case 4: placeOrder(); break;
                    case 5: viewAllDetails(); break;
                    case 6: saveOrdersToFile(); break;
                    case 7: System.out.println("Exiting..."); return;
                    default: System.out.println("Invalid option. Try again.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        return input;
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        double input = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        return input;
    }

    private static void addEmployee() throws InvalidInputException {
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = getDoubleInput();
        System.out.print("Is this a Manager (M) or Waiter (W)? ");
        String type = scanner.nextLine().toUpperCase();

        Employee emp;
        if (type.equals("M")) {
            emp = new Manager(empId, name, salary);
        } else if (type.equals("W")) {
            emp = new Waiter(empId, name, salary);
        } else {
            throw new InvalidInputException("Invalid employee type. Use 'M' for Manager or 'W' for Waiter.");
        }
        employees.add(emp);
        System.out.println("Employee added successfully!");
    }

    private static void addCustomer() throws InvalidInputException {
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        Customer customer = new Customer(customerId, name, phoneNumber);
        customers.add(customer);
        System.out.println("Customer added successfully!");
    }

    private static void addMenuItem() throws InvalidInputException {
        System.out.print("Enter Food ID: ");
        int foodId = getIntInput();
        System.out.print("Enter Food Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = getDoubleInput();

        FoodItem item = new FoodItem(foodId, name, price);
        menu.add(item);
        System.out.println("Menu item added successfully!");
    }

    private static void placeOrder() throws InvalidInputException {
        if (customers.isEmpty()) throw new InvalidInputException("No customers available.");
        if (menu.isEmpty()) throw new InvalidInputException("No menu items available.");

        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        System.out.println("Available Customers:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getCustomerId() + " - " + customers.get(i).getName());
        }
        System.out.print("Select Customer (number): ");
        int customerIndex = getIntInput() - 1;
        if (customerIndex < 0 || customerIndex >= customers.size()) {
            throw new InvalidInputException("Invalid customer selection.");
        }
        String customerId = customers.get(customerIndex).getCustomerId();

        List<FoodItem> orderedItems = new ArrayList<>();
        while (true) {
            System.out.println("Available Menu Items:");
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i).getName() + " - Taka:  " + menu.get(i).getPrice());
            }
            System.out.print("Select item to add (number, or 0 to finish): ");
            int itemIndex = getIntInput();
            if (itemIndex == 0) break;
            if (itemIndex < 1 || itemIndex > menu.size()) {
                System.out.println("Invalid item selection. Try again.");
                continue;
            }
            orderedItems.add(menu.get(itemIndex - 1));
        }

        Order order = new Order(orderId, customerId, orderedItems);
        orders.add(order);
        System.out.println("Order placed successfully!");
    }

    private static void viewAllDetails() {
        System.out.println("\nEmployees:");
        for (Employee emp : employees) emp.printDetails();
        System.out.println("\nCustomers:");
        for (Customer cust : customers) cust.printDetails();
        System.out.println("\nOrders:");
        for (Order ord : orders) ord.printDetails();
    }

    private static void saveOrdersToFile() {
        try (PrintWriter writer = new PrintWriter(new File("orders.txt"))) {
            for (Order order : orders) {
                // Find the customer associated with this order
                Customer customer = null;
                for (Customer c : customers) {
                    if (c.getCustomerId().equals(order.getCustomerId())) {
                        customer = c;
                        break;
                    }
                }

                // Write customer details
                if (customer != null) {
                    writer.println("Customer ID: " + customer.getCustomerId());
                    writer.println("Customer Name: " + customer.getName());
                    writer.println("Phone Number: " + customer.getPhoneNumber());
                } else {
                    writer.println("Customer ID: " + order.getCustomerId() + " (Customer details not found)");
                }

                // Write order details
                writer.println("Order ID: " + order.getOrderId());
                writer.println("Ordered Items:");
                for (FoodItem item : order.getOrderedItems()) {
                    writer.println(" - " + item.getName() + ": Taka:  " + item.getPrice());
                }
                writer.println("Total Price: Taka:  " + order.getTotalPrice());
                writer.println("------------------------"); // Separator between orders
            }
            System.out.println("Orders saved to orders.txt successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}