package edu.neu.csye7374;

// Adapter Design Pattern
class ShoeAdapter implements Shoe {
    private final int size;
    private final int quantity;
    private final MakingStrategy strategy;

    public ShoeAdapter(Shoe shoe) {
        this.size = shoe.getSize();
        this.quantity = shoe.getQuantity();
        this.strategy = shoe.getStrategy();
    }

    @Override
    public String getDescription() {
        return size + " size " + quantity + " pairs of " + strategy.getClass().getSimpleName() + " shoes";
    }

    @Override
    public double getPrice() {
        return strategy.getPricePerPair();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public MakingStrategy getStrategy() {
        return strategy;
    }
}
