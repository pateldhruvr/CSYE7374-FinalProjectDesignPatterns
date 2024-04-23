package edu.neu.csye7374;

class OpenOrderState implements OrderState {
    public void processOrder(ShoeShop shop) {
        System.out.println("Order is open. Modifying is allowed.");
    }
}
