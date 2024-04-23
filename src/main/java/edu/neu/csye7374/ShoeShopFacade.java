package edu.neu.csye7374;

// Facade Design Pattern
class ShoeShopFacade {
    private ShoeShop shoeShop;

    public ShoeShopFacade() {
        this.shoeShop = ShoeShop.getInstance();
    }

    public void addShoeToCart(int size, int strategyChoice, int colorChoice) {
        Shoe shoe = shoeShop.createShoe(size, strategyChoice);

        String color;
        switch (colorChoice) {
            case 1:
                color = "Black";
                break;
            case 2:
                color = "Brown";
                break;
            case 3:
                color = "Tan";
                break;
            default:
                System.out.println("No decoration added");
                color = "None";
                break;
        }

        ShoeBuilder builder = shoeShop.createShoeBuilder();
        builder.setSize(size)
                .setQuantity(1)
                .setStrategy(shoe.getStrategy());
        shoeShop.addToCart(new PolishDecorator(shoe, color));
    }

    public double checkout() {
        return shoeShop.checkout();
    }

    public void processOrder() {
        shoeShop.processOrder();
    }
}
