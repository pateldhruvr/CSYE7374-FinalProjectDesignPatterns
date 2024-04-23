package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

class ShoeShop {
    private static ShoeShop instance;
    private PriceCalculator priceCalculator;
    private OrderContext orderContext;
    private final List<Shoe> cart = new ArrayList<>();

    private ShoeShop() {
        priceCalculator = new PriceCalculator();
        orderContext = new OrderContext();
    }

    public static ShoeShop getInstance() {
        if (instance == null) {
            instance = new ShoeShop();
        }
        return instance;
    }

    // Factory Pattern
    public Shoe createShoe(int size, int strategyChoice) {
        MakingStrategy strategy;
        switch (strategyChoice) {
            case 1:
                strategy = new MachineMadeStrategy();
                break;
            case 2:
                strategy = new HandMadeStrategy();
                break;
            case 3:
                strategy = new CustomMadeStrategy();
                break;
            default:
                System.out.println("Invalid option, using default (Machine-made)");
                strategy = new MachineMadeStrategy();
                break;
        }
        return new BaseShoe(size, 1, strategy);
    }

    // Builder Pattern
    public ShoeBuilder createShoeBuilder() {
        return new ShoeBuilder();
    }

    public void addToCart(Shoe shoe) {
        System.out.println(shoe.getDescription() + " added to cart");
        cart.add(shoe);
    }

    public double checkout() {
        priceCalculator.setCommand(new CalculatePriceCommand());
        double totalPrice = priceCalculator.calculatePrice(cart);
        orderContext.setState(new LockedOrderState());
        return totalPrice;
    }

    public void processOrder() {
        orderContext.processOrder(this);
    }
}
