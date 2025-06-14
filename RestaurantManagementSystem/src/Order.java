import java.util.List;

// Order class
public class Order implements Printable {
    private String orderId;
    private String customerId;
    private List<FoodItem> orderedItems;
    private double totalPrice;

    public Order(String orderId, String customerId, List<FoodItem> orderedItems) throws InvalidInputException {
        if (orderedItems == null || orderedItems.isEmpty()) throw new InvalidInputException("Order must contain items");
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedItems = orderedItems;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (FoodItem item : orderedItems) {
            total += item.getPrice();
        }
        return total;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public double getTotalPrice() { return totalPrice; }
    public List<FoodItem> getOrderedItems() { return orderedItems; } // Added getter for orderedItems

    @Override
    public void printDetails() {
        System.out.println("Order ID: " + orderId + ", Customer ID: " + customerId + ", Total Price: " + totalPrice);
        System.out.println("Ordered Items:");
        for (FoodItem item : orderedItems) {
            System.out.println(" - " + item.getName() + ": Taka:  " + item.getPrice());
        }
    }
}