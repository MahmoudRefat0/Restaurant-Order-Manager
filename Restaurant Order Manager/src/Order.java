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

    public void calculateTotal() {
        double sum = 0.0;
        for (OrderItem orderItem : items) {
            sum += orderItem.calculateSubtotal();
        }
        this.total = sum;
    }

    public void addItem(MenuItem menuItem, int quantity) {

        boolean found = false;
        for (OrderItem orderItem : items) {
            if (orderItem.getItem().getId() == menuItem.getId()) {
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            items.add(new OrderItem(menuItem, quantity));
        }

        calculateTotal();
    }

    public boolean removeItem(int menuItemId) {
        OrderItem itemToRemove = null;
        for (OrderItem orderItem : items) {
            if (orderItem.getItem().getId() == menuItemId) {
                itemToRemove = orderItem;
                break;
            }
        }

        if (itemToRemove != null) {
            items.remove(itemToRemove);
            calculateTotal();
            return true;
        }

        return false;
    }

    public void displayOrder() {
        System.out.println("----------------------------------------");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Status: " + status);
        System.out.println("Items:");
        if (items.isEmpty()) {
            System.out.println("  (No items added yet)");
        } else {
            for (OrderItem orderItem : items) {
                System.out.println("  - " + orderItem.getItem().getName()
                        + " x " + orderItem.getQuantity()
                        + " = $" + orderItem.calculateSubtotal());
            }
        }
        System.out.println("Total Price: $" + total);
        System.out.println("----------------------------------------");
    }
}