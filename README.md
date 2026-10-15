# 🍽️ Restaurant Order Manager

A console-based Java application that manages a restaurant's menu, customer orders and kitchen queue, built to practise the **Java Collections Framework** and **OOP**.

## Features
- Add, remove, search and display menu items (with categories and prices)
- Create orders, add/remove items and calculate totals automatically
- Send orders to a **FIFO kitchen queue** and process them in order
- Track order status: `PENDING → IN_KITCHEN → COMPLETED` (or cancelled)
- Search orders, check their status and list completed orders

## Concepts used
| Concept | Where |
| --- | --- |
| `HashMap` / `LinkedHashMap` | Fast lookup of menu items and orders by ID, keeping insertion order |
| `ArrayList` | Items inside each order |
| `Queue` (`LinkedList`) | Kitchen queue (first in, first out) |
| `Optional` | Safe searching without `null` checks |
| Enums | `OrderStatus` |
| Encapsulation | Private fields with getters/setters in every model |

## Project structure
```
Restaurant Order Manager/src
├── Main.java          # Console menu (15 options)
├── Restaurant.java    # Business logic: menu, orders, kitchen queue
├── MenuItem.java
├── Order.java
├── OrderItem.java
└── OrderStatus.java
```

## How to run
1. Open the `Restaurant Order Manager` folder in IntelliJ IDEA (JDK 17+).
2. Run `Main.java`.

Or from the terminal:
```bash
cd "Restaurant Order Manager/src"
javac *.java && java Main
```
