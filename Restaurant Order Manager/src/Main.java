import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        Scanner scanner = new Scanner(System.in);

        // إضافة بيانات المنيو الأساسية للتجربة المباشرة
        restaurant.addMenuItem(1, "Burger", 150.0, "Main Course");
        restaurant.addMenuItem(2, "Pizza", 200.0, "Main Course");
        restaurant.addMenuItem(3, "Pasta", 180.0, "Main Course");
        restaurant.addMenuItem(4, "Cola", 40.0, "Drinks");

        int choice = -1;

        while (choice != 15) {
            printMenuOptions();
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice! Please enter a number.");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Item ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Item Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String cat = scanner.nextLine();
                    restaurant.addMenuItem(id, name, price, cat);
                    break;

                case 2:
                    System.out.print("Enter Item ID to remove: ");
                    int removeId = scanner.nextInt();
                    restaurant.removeMenuItem(removeId);
                    break;

                case 3:
                    restaurant.displayMenu();
                    break;

                case 4:
                    System.out.print("Enter Item ID to search: ");
                    int searchId = scanner.nextInt();
                    MenuItem item = restaurant.searchMenuItem(searchId);
                    if (item != null) {
                        System.out.println("Found: " + item);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Order ID: ");
                    int oId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String custName = scanner.nextLine();
                    restaurant.createOrder(oId, custName);
                    break;

                case 6:
                    System.out.print("Enter Order ID: ");
                    int addOId = scanner.nextInt();
                    System.out.print("Enter Menu Item ID: ");
                    int addMId = scanner.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    restaurant.addItemToOrder(addOId, addMId, qty);
                    break;

                case 7:
                    System.out.print("Enter Order ID: ");
                    int remOId = scanner.nextInt();
                    System.out.print("Enter Menu Item ID to remove: ");
                    int remMId = scanner.nextInt();
                    restaurant.removeItemFromOrder(remOId, remMId);
                    break;

                case 8:
                    System.out.print("Enter Order ID to display: ");
                    int dispOId = scanner.nextInt();
                    restaurant.displayOrder(dispOId);
                    break;

                case 9:
                    System.out.print("Enter Order ID to add to Kitchen Queue: ");
                    int qOId = scanner.nextInt();
                    restaurant.addOrderToKitchenQueue(qOId);
                    break;

                case 10:
                    restaurant.processNextOrder();
                    break;

                case 11:
                    System.out.print("Enter Order ID to search: ");
                    int sOId = scanner.nextInt();
                    Order searchedOrder = restaurant.searchOrder(sOId);
                    if (searchedOrder != null) {
                        searchedOrder.displayOrder();
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;

                case 12:
                    System.out.print("Enter Order ID: ");
                    int chkOId = scanner.nextInt();
                    restaurant.checkOrderStatus(chkOId);
                    break;

                case 13:
                    restaurant.displayCompletedOrders();
                    break;

                case 14:
                    System.out.print("Enter Order ID to cancel: ");
                    int cOId = scanner.nextInt();
                    restaurant.cancelOrder(cOId);
                    break;

                case 15:
                    System.out.println("Exiting Restaurant Order Manager. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMenuOptions() {
        System.out.println("========== RESTAURANT ORDER MANAGER ==========");
        System.out.println("1. Add Menu Item");
        System.out.println("2. Remove Menu Item");
        System.out.println("3. Display Menu");
        System.out.println("4. Search Menu Item");
        System.out.println("5. Create Order");
        System.out.println("6. Add Item to Order");
        System.out.println("7. Remove Item from Order");
        System.out.println("8. Display Order");
        System.out.println("9. Add Order to Kitchen Queue");
        System.out.println("10. Process Next Order");
        System.out.println("11. Search Order");
        System.out.println("12. Check Order Status");
        System.out.println("13. Display Completed Orders");
        System.out.println("14. Cancel Order");
        System.out.println("15. Exit");
    }
}