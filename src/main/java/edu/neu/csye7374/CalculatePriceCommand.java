package edu.neu.csye7374;

import java.util.List;

class CalculatePriceCommand implements Command {
    public double execute(List<Shoe> shoes) {
        double totalPrice = 0;
        for (Shoe shoe : shoes) {
            double pricePerPair = shoe.getPrice();
            int quantity = shoe.getQuantity();
            double pairPrice = pricePerPair * quantity;
            totalPrice += pairPrice;

            System.out.println("  Shoe Description: " + shoe.getDescription());
            System.out.println("  - Strategy Price: $" + shoe.getStrategy().getPricePerPair());
            if (shoe instanceof PolishDecorator) {
                double decorationPrice = (((PolishDecorator) shoe).getPolishPrice());
                System.out.println("  - Decoration Price: $" + decorationPrice);
            }
            System.out.println("  - Quantity: " + quantity);
            System.out.println("\nTotal Price for " + quantity + " pairs: $" + pairPrice);
        }
        return totalPrice;
    }
}
