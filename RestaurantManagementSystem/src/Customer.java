// Customer class
public class Customer implements Printable {
    private String customerId;
    private String name;
    private String phoneNumber;

    public Customer(String customerId, String name, String phoneNumber) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("Name cannot be empty");
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void printDetails() {
        System.out.println("Customer ID: " + customerId + ", Name: " + name + ", Phone: " + phoneNumber);
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; } // Added getter for phoneNumber
}