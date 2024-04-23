package edu.neu.csye7374;

import java.awt.*;

// Facade Design Pattern
class ShoeShopFacade {
    private ShoeShop shoeShop;

    public ShoeShopFacade() {
        this.shoeShop = ShoeShop.getInstance();
    }

    public void addShoeToCart(Shoe baseShoe, int colorChoice) {
//        Shoe shoe = shoeShop.createShoe(size, strategyChoice);

//        String color;
//        switch (colorChoice) {
//            case 1:
//                color = "Black";
//                break;
//            case 2:
//                color = "Brown";
//                break;
//            case 3:
//                color = "Tan";
//                break;
//            default:
//                System.out.println("No decoration added");
//                color = "None";
//                break;
//        }

        shoeShop.addToCart(new PolishDecorator(baseShoe, ColorChoice.values()[colorChoice]));
    }

    public double checkout() {
        return shoeShop.checkout();
    }

    public void processOrder() {
        shoeShop.processOrder();
    }
}
