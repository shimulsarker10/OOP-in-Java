// FoodItem class
public class FoodItem {
    private int foodId;
    private String name;
    private double price;

    public FoodItem(int foodId, String name, double price) throws InvalidInputException {
        if (price < 0) throw new InvalidInputException("Price cannot be negative");
        this.foodId = foodId;
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }
    public String getName() { return name; }
}