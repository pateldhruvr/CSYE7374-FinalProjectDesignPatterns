package edu.neu.csye7374;

import java.util.Scanner;

public class MyProject {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        ShoeShopFacade shoeShopFacade = new ShoeShopFacade();

        while (true) {
            try{
            System.out.println("Enter shoe size (1-13):");
            int size = scanner.nextInt();
            System.out.println("Select strategy:");
            System.out.println("1. Machine-made");
            System.out.println("2. Handcrafted");
            System.out.println("3. Custom-made");
            System.out.println("Any other option will take Machine-made default option.\n");
            int strategyChoice = scanner.nextInt();

            System.out.println("Select polish color:");
            System.out.println("1. Black - 10$");
            System.out.println("2. Brown - 10$");
            System.out.println("3. Tan - 10$");
            System.out.println("Enter anything else for No Polish\n");
            int colorChoice = scanner.nextInt();

            Shoe baseShoe = new ShoeBuilder()
                    .setSize(size)
                    .setQuantity(1)
                    .setStrategy(strategyChoice)
                    .build();

            shoeShopFacade.addShoeToCart(baseShoe, colorChoice);

            System.out.println("\nAdd another pair of shoes? (Y/N)\n");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }}catch (Exception e){
                System.out.println("Please enter valid Input");
            }
        }

        // Checkout
        double totalPrice = shoeShopFacade.checkout();
        System.out.println("Total price: $" + totalPrice);
        shoeShopFacade.processOrder();

        scanner.close();
    }
}
