package edu.neu.csye7374;

class BaseShoe implements Shoe {
    private final int size;
    private final int quantity;
    private final MakingStrategy strategy;

    public BaseShoe(int size, int quantity, MakingStrategy strategy) {
        this.size = size;
        this.quantity = quantity;
        this.strategy = strategy;
    }

    @Override
    public String getDescription() {
        return size + " size " + quantity + " pairs of " + strategy.getClass().getSimpleName() + " shoes";
    }

    @Override
    public double getPrice() {
        double pricePerPair = strategy.getPricePerPair();
        double totalPrice = pricePerPair * quantity;
        if (size <= 6) {
            System.out.println("\nSize Price: $" + 20);
            totalPrice += 20 * quantity;
        } else if (size <= 10) {
            System.out.println("\nSize Price: $" + 25);
            totalPrice += 25 * quantity;
        } else {
            System.out.println("\nSize Price: $" + 30);
            totalPrice += 30 * quantity;
        }
        return totalPrice;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public MakingStrategy getStrategy() {
        return this.strategy;
    }
}
