package edu.neu.csye7374;//package edu.neu.csye7374;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//// State Pattern
//interface OrderState {
//    void processOrder(ShoeShop shop);
//}
//
//class OpenOrderState implements OrderState {
//    public void processOrder(ShoeShop shop) {
//        System.out.println("Order is open. Modifying is allowed.");
//    }
//}
//
//class LockedOrderState implements OrderState {
//    public void processOrder(ShoeShop shop) {
//        System.out.println("Order is locked. Modifications are not allowed.");
//    }
//}
//
//class OrderContext {
//    private OrderState currentState;
//
//    public OrderContext() {
//        currentState = new OpenOrderState();
//    }
//
//    public void setState(OrderState state) {
//        currentState = state;
//    }
//
//    public void processOrder(ShoeShop shop) {
//        currentState.processOrder(shop);
//    }
//}
//
//// Command Pattern
//interface Command {
//    double execute(List<Shoe> shoes);
//}
//
//class CalculatePriceCommand implements Command {
//    public double execute(List<Shoe> shoes) {
//        double totalPrice = 0;
//        for (Shoe shoe : shoes) {
//            double pricePerPair = shoe.getStrategy().getPricePerPair();
//            int quantity = shoe.getQuantity();
//            double pairPrice = pricePerPair * quantity;
//
//            if (shoe.getSize() <= 6) {
//                pairPrice += 20 * quantity;
//            } else if (shoe.getSize() <= 10) {
//                pairPrice += 25 * quantity;
//            } else {
//                pairPrice += 30 * quantity;
//            }
//            totalPrice += pairPrice;
//        }
//        return totalPrice;
//    }
//}
//
//class PriceCalculator {
//    private Command command;
//
//    public void setCommand(Command command) {
//        this.command = command;
//    }
//
//    public double calculatePrice(List<Shoe> shoes) {
//        if (command == null) {
//            throw new IllegalStateException("Command not set in PriceCalculator");
//        }
//        return command.execute(shoes);
//    }
//}
//
//class Shoe {
//    private final int size;
//    private final int quantity;
//    private final MakingStrategy strategy;
//
//    public Shoe(int size, int quantity, MakingStrategy strategy) {
//        this.size = size;
//        this.quantity = quantity;
//        this.strategy = strategy;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public MakingStrategy getStrategy() {
//        return strategy;
//    }
//}
//
//interface MakingStrategy {
//    double getPricePerPair();
//}
//
//class MachineMadeStrategy implements MakingStrategy {
//    public double getPricePerPair() {
//        return 30;
//    }
//}
//
//class HandMadeStrategy implements MakingStrategy {
//    public double getPricePerPair() {
//        return 25;
//    }
//}
//
//class CustomMadeStrategy implements MakingStrategy {
//    public double getPricePerPair() {
//        return 20;
//    }
//}
//
//class PolishDecorator {
//    private final Shoe shoe;
//    private final String polishColor;
//
//    public PolishDecorator(Shoe shoe, String polishColor) {
//        this.shoe = shoe;
//        this.polishColor = polishColor;
//    }
//
//    public String decorate() {
//        return shoe.getSize() + " size " + polishColor + " polished " + shoe.getQuantity() + " pairs of " +
//                shoe.getStrategy().getClass().getSimpleName() + " shoes";
//    }
//}
//
//class ShoeShop {
//    private static ShoeShop instance;
//    private PriceCalculator priceCalculator;
//    private OrderContext orderContext;
//    private final List<Shoe> cart = new ArrayList<>();
//
//    private ShoeShop() {
//        priceCalculator = new PriceCalculator();
//        orderContext = new OrderContext();
//    }
//
//    public static ShoeShop getInstance() {
//        if (instance == null) {
//            instance = new ShoeShop();
//        }
//        return instance;
//    }
//
//    public void addToCart(Shoe shoe, String polishColor) {
//        PolishDecorator decoratedShoe = new PolishDecorator(shoe, polishColor);
//        cart.add(decoratedShoe);
//    }
//
//    public double checkout() {
//        priceCalculator.setCommand(new CalculatePriceCommand());
//        double totalPrice = priceCalculator.calculatePrice(cart);
//        orderContext.setState(new LockedOrderState());
//        return totalPrice;
//    }
//}
//
//// Demo method to run all the methods
//public class MyProject {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ShoeShop shop = ShoeShop.getInstance();
//
//        // Initial shoe selection
//        System.out.println("Enter shoe size (1-13):");
//        int size = scanner.nextInt();
//        System.out.println("Select strategy:");
//        System.out.println("1. Machine-made");
//        System.out.println("2. Handcrafted");
//        System.out.println("3. Custom-made");
//        int strategyChoice = scanner.nextInt();
//        MakingStrategy strategy;
//        switch (strategyChoice) {
//            case 1:
//                strategy = new MachineMadeStrategy();
//                break;
//            case 2:
//                strategy = new HandMadeStrategy();
//                break;
//            case 3:
//                strategy = new CustomMadeStrategy();
//                break;
//            default:
//                System.out.println("Invalid option, using default (Machine-made)");
//                strategy = new MachineMadeStrategy();
//                break;
//        }
//        System.out.println("Select polish color:");
//        System.out.println("1. Black");
//        System.out.println("2. Brown");
//        System.out.println("3. Tan");
//        int colorChoice = scanner.nextInt();
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
//                System.out.println("Invalid option, using default (Black)");
//                color = "Black";
//                break;
//        }
//        Shoe shoe = new Shoe(size, 1, strategy);
//        shop.addToCart(shoe);
//
//        // Adding more shoes to the cart
//        while (true) {
//            System.out.println("Add another pair of shoes? (Y/N)");
//            String choice = scanner.next();
//            if (choice.equalsIgnoreCase("Y")) {
//                System.out.println("Enter shoe size (1-13):");
//                size = scanner.nextInt();
//                System.out.println("Select strategy:");
//                System.out.println("1. Machine-made");
//                System.out.println("2. Handcrafted");
//                System.out.println("3. Custom-made");
//                strategyChoice = scanner.nextInt();
//                switch (strategyChoice) {
//                    case 1:
//                        strategy = new MachineMadeStrategy();
//                        break;
//                    case 2:
//                        strategy = new HandMadeStrategy();
//                        break;
//                    case 3:
//                        strategy = new CustomMadeStrategy();
//                        break;
//                    default:
//                        System.out.println("Invalid option, using default (Machine-made)");
//                        strategy = new MachineMadeStrategy();
//                        break;
//                }
//                System.out.println("Select polish color:");
//                System.out.println("1. Black");
//                System.out.println("2. Brown");
//                System.out.println("3. Tan");
//                colorChoice = scanner.nextInt();
//                switch (colorChoice) {
//                    case 1:
//                        color = "Black";
//                        break;
//                    case 2:
//                        color = "Brown";
//                        break;
//                    case 3:
//                        color = "Tan";
//                        break;
//                    default:
//                        System.out.println("Invalid option, using default (Black)");
//                        color = "Black";
//                        break;
//                }
//                shoe = new Shoe(size, 1, strategy);
//                shop.addToCart(shoe);
//            } else if (choice.equalsIgnoreCase("N")) {
//                break;
//            } else {
//                System.out.println("Invalid choice. Please enter Y or N.");
//            }
//        }
//
//
//
//        // Checkout and place order
//        double totalPrice = shop.checkout();
//        System.out.println("Total price: $" + totalPrice);
//
//        scanner.close();
//    }
//}
//


//========= version 2 ===========

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//// State Pattern
//interface OrderState {
//    void processOrder(ShoeShop shop);
//}
//
//class OpenOrderState implements OrderState {
//    public void processOrder(ShoeShop shop) {
//        System.out.println("Order is open. Modifying is allowed.");
//    }
//}
//
//class LockedOrderState implements OrderState {
//    public void processOrder(ShoeShop shop) {
//        System.out.println("Order is locked. Modifications are not allowed.");
//    }
//}
//
//class OrderContext {
//    private OrderState currentState;
//
//    public OrderContext() {
//        currentState = new OpenOrderState();
//    }
//
//    public void setState(OrderState state) {
//        currentState = state;
//    }
//
//    public void processOrder(ShoeShop shop) {
//        currentState.processOrder(shop);
//    }
//}
//
//// Command Pattern
//interface Command {
//    double execute(List<Shoe> shoes);
//}
//
//class CalculatePriceCommand implements Command {
//    public double execute(List<Shoe> shoes) {
//        double totalPrice = 0;
//        for (Shoe shoe : shoes) {
//            double pricePerPair = shoe.getPrice();
//            int quantity = shoe.getQuantity();
//            double pairPrice = pricePerPair * quantity;
//            totalPrice += pairPrice;
//
//            System.out.println("  Shoe Description: " + shoe.getDescription());
//            System.out.println("  - Strategy Price: $" + shoe.getStrategy().getPricePerPair());
//            if (shoe instanceof PolishDecorator) {
//                double decorationPrice = (((PolishDecorator) shoe).getPolishPrice());
//                System.out.println("  - Decoration Price: $" + decorationPrice);
//            }
//            System.out.println("  - Quantity: " + quantity);
//            System.out.println("\nTotal Price for " + quantity + " pairs: $" + pairPrice);
//        }
//        return totalPrice;
//    }
//}
//
//class PriceCalculator {
//    private Command command;
//
//    public void setCommand(Command command) {
//        this.command = command;
//    }
//
//    public double calculatePrice(List<Shoe> shoes) {
//        if (command == null) {
//            throw new IllegalStateException("Command not set in PriceCalculator");
//        }
//        return command.execute(shoes);
//    }
//}
//
//interface Shoe {
//    String getDescription();
//    double getPrice();
//    int getSize();
//    int getQuantity();
//    MakingStrategy getStrategy();
//}
//
//class BaseShoe implements Shoe {
//    private final int size;
//    private final int quantity;
//    private final MakingStrategy strategy;
//
//    public BaseShoe(int size, int quantity, MakingStrategy strategy) {
//        this.size = size;
//        this.quantity = quantity;
//        this.strategy = strategy;
//    }
//
//    @Override
//    public String getDescription() {
//        return size + " size " + quantity + " pairs of " + strategy.getClass().getSimpleName() + " shoes";
//    }
//
//    @Override
//    public double getPrice() {
//        double pricePerPair = strategy.getPricePerPair();
//        double totalPrice = pricePerPair * quantity;
//        if (size <= 6) {
//            System.out.println("\nSize Price: $" + 20);
//            totalPrice += 20 * quantity;
//        } else if (size <= 10) {
//            System.out.println("\nSize Price: $" + 25);
//            totalPrice += 25 * quantity;
//        } else {
//            System.out.println("\nSize Price: $" + 30);
//            totalPrice += 30 * quantity;
//        }
//        return totalPrice;
//    }
//
//    @Override
//    public int getSize() {
//        return size;
//    }
//
//    @Override
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public MakingStrategy getStrategy() {
//        return this.strategy;
//    }
//}
//
//interface MakingStrategy {
//    double getPricePerPair();
//}
//
//class MachineMadeStrategy implements MakingStrategy {
//    public double getPricePerPair() {
//        return 20;
//    }
//}
//
//class HandMadeStrategy implements MakingStrategy {
//    public double getPricePerPair() {
//        return 25;
//    }
//}
//
//class CustomMadeStrategy implements MakingStrategy {
//    public double getPricePerPair() {
//        return 30;
//    }
//}
//
//class PolishDecorator implements Shoe {
//    private final Shoe shoe;
//    private final String polishColor;
//    private final double polishPrice = 10.0;
//    private final MakingStrategy strategy;
//
//    public PolishDecorator(Shoe shoe, String polishColor) {
//        this.shoe = shoe;
//        this.polishColor = polishColor;
//        this.strategy = shoe.getStrategy();
//    }
//
//    @Override
//    public String getDescription() {
//        return shoe.getDescription() + ", polished " + polishColor;
//    }
//
//    @Override
//    public double getPrice() {
//        if (polishColor == "None"){
//            return shoe.getPrice();
//        }
//        return shoe.getPrice() + polishPrice; // Additional cost for polish
//    }
//
//    public double getPolishPrice() {
//        return polishPrice;
//    }
//
//    @Override
//    public int getSize() {
//        return shoe.getSize();
//    }
//
//    @Override
//    public int getQuantity() {
//        return shoe.getQuantity();
//    }
//
//    @Override
//    public MakingStrategy getStrategy() {
//        return this.strategy;
//    }
//}
//
//
//class ShoeShop {
//    private static ShoeShop instance;
//    private PriceCalculator priceCalculator;
//    private OrderContext orderContext;
//    private final List<Shoe> cart = new ArrayList<>();
//
//    private ShoeShop() {
//        priceCalculator = new PriceCalculator();
//        orderContext = new OrderContext();
//    }
//
//    public static ShoeShop getInstance() {
//        if (instance == null) {
//            instance = new ShoeShop();
//        }
//        return instance;
//    }
//
//    public void addToCart(Shoe shoe) {
//        System.out.println(shoe.getDescription() + " added to cart");
//        cart.add(shoe);
//    }
//
//    public double checkout() {
//        priceCalculator.setCommand(new CalculatePriceCommand());
//        double totalPrice = priceCalculator.calculatePrice(cart);
//        orderContext.setState(new LockedOrderState());
//        return totalPrice;
//    }
//
//    public void processOrder() {
//        orderContext.processOrder(this);
//    }
//}
//
//public class MyProject {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ShoeShop shop = ShoeShop.getInstance();
//
//        while (true) {
//            System.out.println("Enter shoe size (1-13):");
//            int size = scanner.nextInt();
//            System.out.println("Select strategy:");
//            System.out.println("1. Machine-made");
//            System.out.println("2. Handcrafted");
//            System.out.println("3. Custom-made");
//            System.out.println("Any other option will take Machine-made default option.\n");
//            int strategyChoice = scanner.nextInt();
//            MakingStrategy strategy;
//            switch (strategyChoice) {
//                case 1:
//                    strategy = new MachineMadeStrategy();
//                    break;
//                case 2:
//                    strategy = new HandMadeStrategy();
//                    break;
//                case 3:
//                    strategy = new CustomMadeStrategy();
//                    break;
//                default:
//                    System.out.println("Invalid option, using default (Machine-made)");
//                    strategy = new MachineMadeStrategy();
//                    break;
//            }
//
//            System.out.println("Select polish color:");
//            System.out.println("1. Black - 10$");
//            System.out.println("2. Brown - 10$");
//            System.out.println("3. Tan - 10$");
//            System.out.println("Enter anything else for No Polish\n");
//            int colorChoice = scanner.nextInt();
//            String color;
//            switch (colorChoice) {
//                case 1:
//                    color = "Black";
//                    break;
//                case 2:
//                    color = "Brown";
//                    break;
//                case 3:
//                    color = "Tan";
//                    break;
//                default:
//                    System.out.println("No decoration added");
//                    color = "None";
//                    break;
//            }
//
//            Shoe shoe = new BaseShoe(size, 1, strategy);
//            shop.addToCart(new PolishDecorator(shoe, color));
//
//            System.out.println("\nAdd another pair of shoes? (Y/N)\n");
//            String choice = scanner.next();
//            if (choice.equalsIgnoreCase("N")) {
//                break;
//            }
//        }
//
//        //Checkout
//        double totalPrice = shop.checkout();
//        System.out.println("Total price: $" + totalPrice);
//        shop.processOrder();
//
//        scanner.close();
//    }
//}


// ========= version 3 =========

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//// State Pattern
//interface OrderState {
//    void processOrder(ShoeShop shop);
//}
//
//class OpenOrderState implements OrderState {
//    public void processOrder(ShoeShop shop) {
//        System.out.println("Order is open. Modifying is allowed.");
//    }
//}
//
//class LockedOrderState implements OrderState {
//    public void processOrder(ShoeShop shop) {
//        System.out.println("Order is locked. Modifications are not allowed.");
//    }
//}
//
//class OrderContext {
//    private OrderState currentState;
//
//    public OrderContext() {
//        currentState = new OpenOrderState();
//    }
//
//    public void setState(OrderState state) {
//        currentState = state;
//    }
//
//    public void processOrder(ShoeShop shop) {
//        currentState.processOrder(shop);
//    }
//}
//
//// Command Pattern
//interface Command {
//    double execute(List<Shoe> shoes);
//}
//
//class CalculatePriceCommand implements Command {
//    public double execute(List<Shoe> shoes) {
//        double totalPrice = 0;
//        for (Shoe shoe : shoes) {
//            double pricePerPair = shoe.getPrice();
//            int quantity = shoe.getQuantity();
//            double pairPrice = pricePerPair * quantity;
//            totalPrice += pairPrice;
//
//            System.out.println("  Shoe Description: " + shoe.getDescription());
//            System.out.println("  - Strategy Price: $" + shoe.getStrategy().getPricePerPair());
//            if (shoe instanceof PolishDecorator) {
//                double decorationPrice = (((PolishDecorator) shoe).getPolishPrice());
//                System.out.println("  - Decoration Price: $" + decorationPrice);
//            }
//            System.out.println("  - Quantity: " + quantity);
//            System.out.println("\nTotal Price for " + quantity + " pairs: $" + pairPrice);
//        }
//        return totalPrice;
//    }
//}
//
//class PriceCalculator {
//    private Command command;
//
//    public void setCommand(Command command) {
//        this.command = command;
//    }
//
//    public double calculatePrice(List<Shoe> shoes) {
//        if (command == null) {
//            throw new IllegalStateException("Command not set in PriceCalculator");
//        }
//        return command.execute(shoes);
//    }
//}
//
//interface Shoe {
//    String getDescription();
//    double getPrice();
//    int getSize();
//    int getQuantity();
//    MakingStrategy getStrategy();
//}
//
//class BaseShoe implements Shoe {
//    private final int size;
//    private final int quantity;
//    private final MakingStrategy strategy;
//
//    public BaseShoe(int size, int quantity, MakingStrategy strategy) {
//        this.size = size;
//        this.quantity = quantity;
//        this.strategy = strategy;
//    }
//
//    @Override
//    public String getDescription() {
//        return size + " size " + quantity + " pairs of " + strategy.getClass().getSimpleName() + " shoes";
//    }
//
//    @Override
//    public double getPrice() {
//        double pricePerPair = strategy.getPricePerPair();
//        double totalPrice = pricePerPair * quantity;
//        if (size <= 6) {
//            System.out.println("\nSize Price: $" + 20);
//            totalPrice += 20 * quantity;
//        } else if (size <= 10) {
//            System.out.println("\nSize Price: $" + 25);
//            totalPrice += 25 * quantity;
//        } else {
//            System.out.println("\nSize Price: $" + 30);
//            totalPrice += 30 * quantity;
//        }
//        return totalPrice;
//    }
//
//    @Override
//    public int getSize() {
//        return size;
//    }
//
//    @Override
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public MakingStrategy getStrategy() {
//        return this.strategy;
//    }
//}
//
//interface MakingStrategy {
//    double getPricePerPair();
//}
//
//class MachineMadeStrategy implements MakingStrategy {
//    public double getPricePerPair() {
//        return 20;
//    }
//}
//
//class HandMadeStrategy implements MakingStrategy {
//    public double getPricePerPair() {
//        return 25;
//    }
//}
//
//class CustomMadeStrategy implements MakingStrategy {
//    public double getPricePerPair() {
//        return 30;
//    }
//}
//
//class PolishDecorator implements Shoe {
//    private final Shoe shoe;
//    private final String polishColor;
//    private final double polishPrice = 10.0;
//    private final MakingStrategy strategy;
//
//    public PolishDecorator(Shoe shoe, String polishColor) {
//        this.shoe = shoe;
//        this.polishColor = polishColor;
//        this.strategy = shoe.getStrategy();
//    }
//
//    @Override
//    public String getDescription() {
//        return shoe.getDescription() + ", polished " + polishColor;
//    }
//
//    @Override
//    public double getPrice() {
//        if (polishColor.equals("None")) {
//            return shoe.getPrice();
//        }
//        return shoe.getPrice() + polishPrice; // Additional cost for polish
//    }
//
//    public double getPolishPrice() {
//        return polishPrice;
//    }
//
//    @Override
//    public int getSize() {
//        return shoe.getSize();
//    }
//
//    @Override
//    public int getQuantity() {
//        return shoe.getQuantity();
//    }
//
//    @Override
//    public MakingStrategy getStrategy() {
//        return this.strategy;
//    }
//}
//
//class ShoeShop {
//    private static ShoeShop instance;
//    private PriceCalculator priceCalculator;
//    private OrderContext orderContext;
//    private final List<Shoe> cart = new ArrayList<>();
//
//    private ShoeShop() {
//        priceCalculator = new PriceCalculator();
//        orderContext = new OrderContext();
//    }
//
//    public static ShoeShop getInstance() {
//        if (instance == null) {
//            instance = new ShoeShop();
//        }
//        return instance;
//    }
//
//    public void addToCart(Shoe shoe) {
//        System.out.println(shoe.getDescription() + " added to cart");
//        cart.add(shoe);
//    }
//
//    public double checkout() {
//        priceCalculator.setCommand(new CalculatePriceCommand());
//        double totalPrice = priceCalculator.calculatePrice(cart);
//        orderContext.setState(new LockedOrderState());
//        return totalPrice;
//    }
//
//    public void processOrder() {
//        orderContext.processOrder(this);
//    }
//}
//
//// Facade Design Pattern
//class ShoeShopFacade {
//    private ShoeShop shoeShop;
//
//    public ShoeShopFacade() {
//        this.shoeShop = ShoeShop.getInstance();
//    }
//
//    public void addShoeToCart(int size, int strategyChoice, int colorChoice) {
//        MakingStrategy strategy;
//        switch (strategyChoice) {
//            case 1:
//                strategy = new MachineMadeStrategy();
//                break;
//            case 2:
//                strategy = new HandMadeStrategy();
//                break;
//            case 3:
//                strategy = new CustomMadeStrategy();
//                break;
//            default:
//                System.out.println("Invalid option, using default (Machine-made)");
//                strategy = new MachineMadeStrategy();
//                break;
//        }
//
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
//
//        ShoeBuilder builder = new ShoeBuilder();
//        builder.setSize(size)
//                .setQuantity(1)
//                .setStrategy(strategy);
//        Shoe shoe = builder.build();
//        shoeShop.addToCart(new PolishDecorator(shoe, color));
//    }
//
//    public double checkout() {
//        return shoeShop.checkout();
//    }
//
//    public void processOrder() {
//        shoeShop.processOrder();
//    }
//}
//
//// Adapter Design Pattern
//class ShoeAdapter implements Shoe {
//    private final int size;
//    private final int quantity;
//    private final MakingStrategy strategy;
//
//    public ShoeAdapter(Shoe shoe) {
//        this.size = shoe.getSize();
//        this.quantity = shoe.getQuantity();
//        this.strategy = shoe.getStrategy();
//    }
//
//    @Override
//    public String getDescription() {
//        return size + " size " + quantity + " pairs of " + strategy.getClass().getSimpleName() + " shoes";
//    }
//
//    @Override
//    public double getPrice() {
//        return strategy.getPricePerPair();
//    }
//
//    @Override
//    public int getSize() {
//        return size;
//    }
//
//    @Override
//    public int getQuantity() {
//        return quantity;
//    }
//
//    @Override
//    public MakingStrategy getStrategy() {
//        return strategy;
//    }
//}
//
//// Builder Design Pattern
//class ShoeBuilder {
//    private int size;
//    private int quantity;
//    private MakingStrategy strategy;
//
//    public ShoeBuilder setSize(int size) {
//        this.size = size;
//        return this;
//    }
//
//    public ShoeBuilder setQuantity(int quantity) {
//        this.quantity = quantity;
//        return this;
//    }
//
//    public ShoeBuilder setStrategy(MakingStrategy strategy) {
//        this.strategy = strategy;
//        return this;
//    }
//
//    public Shoe build() {
//        return new BaseShoe(size, quantity, strategy);
//    }
//}
//
//public class MyProject {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ShoeShopFacade shoeShopFacade = new ShoeShopFacade();
//
//        while (true) {
//            System.out.println("Enter shoe size (1-13):");
//            int size = scanner.nextInt();
//            System.out.println("Select strategy:");
//            System.out.println("1. Machine-made");
//            System.out.println("2. Handcrafted");
//            System.out.println("3. Custom-made");
//            System.out.println("Any other option will take Machine-made default option.\n");
//            int strategyChoice = scanner.nextInt();
//
//            System.out.println("Select polish color:");
//            System.out.println("1. Black - 10$");
//            System.out.println("2. Brown - 10$");
//            System.out.println("3. Tan - 10$");
//            System.out.println("Enter anything else for No Polish\n");
//            int colorChoice = scanner.nextInt();
//
//            shoeShopFacade.addShoeToCart(size, strategyChoice, colorChoice);
//
//            System.out.println("\nAdd another pair of shoes? (Y/N)\n");
//            String choice = scanner.next();
//            if (choice.equalsIgnoreCase("N")) {
//                break;
//            }
//        }
//
//        // Checkout
//        double totalPrice = shoeShopFacade.checkout();
//        System.out.println("Total price: $" + totalPrice);
//        shoeShopFacade.processOrder();
//
//        scanner.close();
//    }
//}
//

// ================= version 4 ================

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyProject {
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        ShoeShopFacade shoeShopFacade = new ShoeShopFacade();

        while (true) {
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

            shoeShopFacade.addShoeToCart(size, strategyChoice, colorChoice);

            System.out.println("\nAdd another pair of shoes? (Y/N)\n");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        }

        // Checkout
        double totalPrice = shoeShopFacade.checkout();
        System.out.println("Total price: $" + totalPrice);
        shoeShopFacade.processOrder();

        scanner.close();
    }
}
