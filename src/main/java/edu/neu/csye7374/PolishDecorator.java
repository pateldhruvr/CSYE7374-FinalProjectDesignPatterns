package edu.neu.csye7374;

class PolishDecorator implements Shoe {
    private final Shoe shoe;
    private final String polishColor;
    private final double polishPrice = 10.0;
    private final MakingStrategy strategy;

    public PolishDecorator(Shoe shoe, String polishColor) {
        this.shoe = shoe;
        this.polishColor = polishColor;
        this.strategy = shoe.getStrategy();
    }

    @Override
    public String getDescription() {
        return shoe.getDescription() + ", polished " + polishColor;
    }

    @Override
    public double getPrice() {
        if (polishColor.equals("None")) {
            return shoe.getPrice();
        }
        return shoe.getPrice() + polishPrice; // Additional cost for polish
    }

    public double getPolishPrice() {
        return polishPrice;
    }

    @Override
    public int getSize() {
        return shoe.getSize();
    }

    @Override
    public int getQuantity() {
        return shoe.getQuantity();
    }

    @Override
    public MakingStrategy getStrategy() {
        return this.strategy;
    }
}
