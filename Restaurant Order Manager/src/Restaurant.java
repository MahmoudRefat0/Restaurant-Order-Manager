import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Restaurant {
    private ArrayList<MenuItem> menu;
    private LinkedList<Order> kitchenQueue;
    private HashMap<Integer, Order> orders;
    private LinkedHashMap<Integer, Order> completedOrders;

    public Restaurant() {
        this.menu = new ArrayList<>();
        this.kitchenQueue = new LinkedList<>();
        this.orders = new HashMap<>();
        this.completedOrders = new LinkedHashMap<>();
    }

    // 1. Add Menu Item
    public boolean addMenuItem(int id, String name, double price, String category) {
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                System.out.println("Error: Menu item ID already exists!");
                return false;
            }
        }
        menu.add(new MenuItem(id, name, price, category));
        System.out.println("Menu item added successfully.");
        return true;
    }

    // 2. Remove Menu Item
    public boolean removeMenuItem(int id) {
        MenuItem toRemove = null;
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                toRemove = item;
                break;
            }
        }
        if (toRemove != null) {
            menu.remove(toRemove);
            System.out.println("Menu item removed successfully.");
            return true;
        }
        System.out.println("Error: Menu item not found.");
        return false;
    }

    // 3. Display Menu
    public void displayMenu() {
        System.out.println("\n=== RESTAURANT MENU ===");
        if (menu.isEmpty()) {
            System.out.println("Menu is currently empty.");
            return;
        }
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    // 4. Search Menu Item
    public MenuItem searchMenuItem(int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    // 5. Create Order
    public boolean createOrder(int orderId, String customerName) {
        if (orders.containsKey(orderId)) {
            System.out.println("Error: Order ID already exists!");
            return false;
        }
        Order newOrder = new Order(orderId, customerName);
        orders.put(orderId, newOrder);
        System.out.println("Order created successfully with status: " + newOrder.getStatus());
        return true;
    }

    // 6. Add Item to Order
    public boolean addItemToOrder(int orderId, int menuItemId, int quantity) {
        Order order = orders.get(orderId);
        if (order == null) {
            System.out.println("Error: Order not found.");
            return false;
        }

        if (order.getStatus() == OrderStatus.COMPLETED || order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("Error: Cannot modify COMPLETED or CANCELLED orders.");
            return false;
        }

        MenuItem menuItem = searchMenuItem(menuItemId);
        if (menuItem == null) {
            System.out.println("Error: Menu item not found.");
            return false;
        }

        order.addItem(menuItem, quantity);
        System.out.println("Item added to order successfully.");
        return true;
    }

    // 7. Remove Item from Order
    public boolean removeItemFromOrder(int orderId, int menuItemId) {
        Order order = orders.get(orderId);
        if (order == null) {
            System.out.println("Error: Order not found.");
            return false;
        }

        if (order.getStatus() == OrderStatus.COMPLETED || order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("Error: Cannot modify COMPLETED or CANCELLED orders.");
            return false;
        }

        if (order.removeItem(menuItemId)) {
            System.out.println("Item removed from order successfully.");
            return true;
        } else {
            System.out.println("Error: Item was not found in this order.");
            return false;
        }
    }

    // 8. Display Order
    public void displayOrder(int orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.displayOrder();
        } else {
            System.out.println("Error: Order not found.");
        }
    }

    // 9. Add Order to Kitchen Queue
    public boolean addOrderToKitchenQueue(int orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            System.out.println("Error: Order not found.");
            return false;
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            System.out.println("Error: Only PENDING orders can be added to the kitchen queue.");
            return false;
        }

        order.setStatus(OrderStatus.IN_KITCHEN);
        kitchenQueue.addLast(order);
        System.out.println("Order added to kitchen queue successfully.");
        return true;
    }

    // 10. Process Next Order
    public boolean processNextOrder() {
        if (kitchenQueue.isEmpty()) {
            System.out.println("Error: Kitchen queue is empty.");
            return false;
        }

        Order firstOrder = kitchenQueue.peekFirst();

        if (firstOrder.getItems().isEmpty()) {
            System.out.println("Error: Order #" + firstOrder.getOrderId() + " has no items and cannot be processed.");
            return false;
        }

        kitchenQueue.removeFirst();
        firstOrder.setStatus(OrderStatus.COMPLETED);
        completedOrders.put(firstOrder.getOrderId(), firstOrder);

        System.out.println("Order #" + firstOrder.getOrderId() + " has been processed and COMPLETED.");
        return true;
    }

    // 11. Search Order
    public Order searchOrder(int orderId) {
        return orders.get(orderId);
    }

    // 12. Check Order Status
    public void checkOrderStatus(int orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            System.out.println("Order #" + orderId + " Status: " + order.getStatus());
        } else {
            System.out.println("Error: Order not found.");
        }
    }

    // 13. Display Completed Orders
    public void displayCompletedOrders() {
        System.out.println("\n=== COMPLETED ORDERS ===");
        if (completedOrders.isEmpty()) {
            System.out.println("No completed orders yet.");
            return;
        }
        for (Order order : completedOrders.values()) {
            order.displayOrder();
        }
    }

    // 14. Cancel Order
    public boolean cancelOrder(int orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            System.out.println("Error: Order not found.");
            return false;
        }

        if (order.getStatus() == OrderStatus.COMPLETED || order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("Error: Cannot cancel an order that is already COMPLETED or CANCELLED.");
            return false;
        }

        if (order.getStatus() == OrderStatus.IN_KITCHEN) {
            kitchenQueue.remove(order);
        }

        order.setStatus(OrderStatus.CANCELLED);
        System.out.println("Order #" + orderId + " cancelled successfully.");
        return true;
    }
}