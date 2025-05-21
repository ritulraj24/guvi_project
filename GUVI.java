import java.util.ArrayList;
import java.util.Scanner;

// Represents a Product with id, name, and price
class Product {
    private int id;
    private String name;
    private double price;

    // Constructor
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter methods
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // To display product info
    @Override
    public String toString() {
        return id + ". " + name + " - $" + price;
    }
}

// Represents a single item in the cart (product + quantity)
class CartItem {
    private Product product;
    private int quantity;

    // Constructor
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Returns total price for this item
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    // To display cart item info
    @Override
    public String toString() {
        return product.getName() + " x " + quantity + " = $" + getTotalPrice();
    }
}

// Represents the shopping cart which holds multiple cart items
class ShoppingCart {
    private ArrayList<CartItem> items = new ArrayList<>();

    // Add a product with quantity to the cart
    public void addProduct(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    // Display all items in the cart
    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n===== Your Shopping Cart =====");
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.println("================================");
        System.out.println("Total Amount: $" + getTotalAmount());
    }

    // Calculate total cart value
    public double getTotalAmount() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
}

// Main class to run the program
public class GUVI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        // Creating multiple product objects
        Product[] products = {
            new Product(1, "Laptop", 75000.00),
            new Product(2, "Smartphone", 40000.00),
            new Product(3, "Headphones", 1500.00),
            new Product(4, "Keyboard", 800.00),
            new Product(5, "Mouse", 600.00),
            new Product(6, "Monitor", 12000.00),
            new Product(7, "USB Cable", 150.00),
            new Product(8, "Power Bank", 2000.00)
        };

        // User input loop
        while (true) {
            System.out.println("\nAvailable Products:");
            for (Product p : products) {
                System.out.println(p);
            }

            System.out.print("\nEnter product ID to add to cart (0 to finish): ");
            int id = scanner.nextInt();
            if (id == 0) break;

            // Find the product by ID
            Product selected = null;
            for (Product p : products) {
                if (p.getId() == id) {
                    selected = p;
                    break;
                }
            }

            if (selected == null) {
                System.out.println("Invalid product ID. Please try again.");
                continue;
            }

            // Quantity input
            System.out.print("Enter quantity for " + selected.getName() + ": ");
            int qty = scanner.nextInt();
            if (qty <= 0) {
                System.out.println("Quantity must be greater than 0.");
                continue;
            }

            // Add product to cart
            cart.addProduct(selected, qty);
            System.out.println(selected.getName() + " added to cart.");
        }

        // Show the cart and total
        cart.displayCart();
        scanner.close();
    }
}
