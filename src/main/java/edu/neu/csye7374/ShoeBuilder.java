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

    public ShoeBuilder setStrategy(int strategyChoice) {

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



        this.strategy = strategy;
        return this;
    }

    public Shoe build() {
        return new BaseShoe(size, quantity, strategy);
    }
}
