import java.util.ArrayList;

public class Order {
    private int orderId;
    private String customerName;
    private ArrayList<OrderItem> items;
    private double total;
    private OrderStatus status;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.status = OrderStatus.PENDING;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // 1. Calculate total using mapToDouble and sum from Stream API
    public void calculateTotal() {
        this.total = items.stream()
                .mapToDouble(OrderItem::calculateSubtotal)
                .sum();
    }

    // 2. Add item using Stream API with findFirst and ifPresentOrElse
    public void addItem(MenuItem menuItem, int quantity) {
        items.stream()
                .filter(orderItem -> orderItem.getItem().getId() == menuItem.getId())
                .findFirst()
                .ifPresentOrElse(
                        orderItem -> orderItem.setQuantity(orderItem.getQuantity() + quantity),
                        () -> items.add(new OrderItem(menuItem, quantity))
                );

        calculateTotal();
    }

    // 3. Remove item using removeIf
    public boolean removeItem(int menuItemId) {
        boolean removed = items.removeIf(orderItem -> orderItem.getItem().getId() == menuItemId);
        if (removed) {
            calculateTotal();
        }
        return removed;
    }

    // 4. Display order details using forEach
    public void displayOrder() {
        System.out.println("----------------------------------------");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Status: " + status);
        System.out.println("Items:");
        if (items.isEmpty()) {
            System.out.println("  (No items added yet)");
        } else {
            items.forEach(orderItem -> System.out.println("  - " + orderItem.getItem().getName()
                    + " x " + orderItem.getQuantity()
                    + " = $" + orderItem.calculateSubtotal()));
        }
        System.out.println("Total Price: $" + total);
        System.out.println("----------------------------------------");
    }
}