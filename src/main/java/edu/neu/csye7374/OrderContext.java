package edu.neu.csye7374;

class OrderContext {
    private OrderState currentState;

    public OrderContext() {
        currentState = new OpenOrderState();
    }

    public void setState(OrderState state) {
        currentState = state;
    }

    public void processOrder(ShoeShop shop) {
        currentState.processOrder(shop);
    }
}
