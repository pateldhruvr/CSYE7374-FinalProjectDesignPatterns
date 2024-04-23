package edu.neu.csye7374;

class LockedOrderState implements OrderState {
    public void processOrder(ShoeShop shop) {
        System.out.println("Order is locked. Modifications are not allowed.");
    }
}
