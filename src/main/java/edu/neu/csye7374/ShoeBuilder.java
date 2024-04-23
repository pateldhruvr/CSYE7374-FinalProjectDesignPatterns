package edu.neu.csye7374;

// Builder Design Pattern
class ShoeBuilder {
    private int size;
    private int quantity;
    private MakingStrategy strategy;

    public ShoeBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public ShoeBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ShoeBuilder setStrategy(MakingStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public Shoe build() {
        return new BaseShoe(size, quantity, strategy);
    }
}
