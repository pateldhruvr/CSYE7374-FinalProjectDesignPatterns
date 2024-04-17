package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * STUDENT SKELETON
 * GrubHub model:
 * 
 * 100 TOTAL POINTS
 * 
 * USE AND COMPLETE CODING THIS SINGLE (partially coded) CLASS (add inner
 * classes)
 * to Model GrubHub service demonstrating the correct use of all following
 * design patterns:
 * 
 * Adapter, Builder, Bridge, Composite, Decorator, Singleton, Factory Method,
 * Strategy
 * 
 * Demonstrate Point of Sale (POS) by displaying (on console stdout)
 * ALL orders, totaled with all discounts as follows:
 * 
 * Show ALL Orders NormalDiscount (0% decrease applied to each item price and
 * Total),
 * Show ALL Orders MemberDiscount (10% decrease applied to each item price and
 * Total),
 * Show ALL Orders SpecialDiscount (20% decrease applied to each item price and
 * Total),
 * Show ALL Orders SuperBowlDiscount (20% increase applied to each item price
 * and Total),
 * 
 * 
 * 20 POINTS
 * 1. Create following food items using the following design patterns:
 * Builder,
 * Decorator,
 * Singleton Factory (Eager, Lazy and Enum implementations)
 * AND with the following attributes:
 * 
 * $ 0.99 French Fries
 * $ 1.29 Medium Drink
 * $ 3.49 Hamburger
 * $ 1.49 Hot Dog
 * $ 3.79 Cheeseburger
 * $ 4.49 Buffalo Wings
 * $ 2.49 Salad
 * $ 5.49 Pizza ($1 OPTIONS: cheese, mushrooms, onions, pepperoni, sausage)
 * 
 * 
 * 30 POINTS
 * 2. Create the following customer orders using the following design patterns:
 * Builder,
 * Singleton Factory (Eager, Lazy and Enum implementations)
 * AND with the following attributes:
 * 
 * Dan's Customer Door Dash Order ID #1 Hamburger, Medium Drink
 * Pam's Customer Door Dash Order ID #2 HotDogCombo (HotDog, French Fries,
 * Medium Drink)
 * Jim's Customer Door Dash Order ID #3 BurgerCombo (Hamburger, French Fries,
 * Medium Drink)
 * Jen's Customer Door Dash Order ID #4 Cheeseburger, French Fries
 * Len's Customer Door Dash Order ID #5 WingCombo (Buffalo Wings, Medium Drink,
 * Salad)
 * Sam's Customer Door Dash Order ID #6 PizzaCombo (Buffalo Wings, French Fries,
 * Salad, Medium Drink, Pizza)
 * 
 * 
 * 40 POINTS
 * 3. Develop TWO Point of Sale APIs for Order purchases with DISCOUNTS (to
 * change prices):
 * NormalDiscount (0% decrease),
 * MemberDiscount (10% decrease),
 * SpecialDiscount (20% decrease),
 * SuperBowlDiscount (20% increase)
 * 
 * MUST DEVELOPE APIs (PosAPI and CalculatorAPI) IN TWO PHASES (NOTE: all names
 * with 1 & 2)
 * using Bridge design pattern:
 * 
 * PosAPI Point of Sale API (PosAPI1 & PosAPI2)
 * 
 * Phase I class Pos1 and interface PosAPI1 with methods:
 * double sumTotal(List<Double> itemList); // add customer total
 * void setStrategy(PriceStrategyAPI s); // set strategy for price adjustments
 * double getSum(); // return sum total
 * String toStringSumTotal(); // return String representation of sum total
 *
 * Phase II class Pos2 and interface PosAPI2 with methods:
 * double getChange(double cash); // accept cash, return change
 * String toStringCash(); // return String representation of customer cash
 * submitted
 * String toStringChange(); // return String representation of customer change
 * received
 *
 * Calculator API (CalculatorAPI1 & CalculatorAPI2)
 * 
 * PHASE I class Calculator1 and interface CalculatorAPI1 with method:
 * double add(double a, double b);
 * 
 * PHASE II class Calculator2 and interface CalculatorAPI2 with methods:
 * double sub(double a, double b);
 * double mult(double a, double b);
 * double div(double a, double b);
 * 
 * 
 * 10 POINTS
 * 4. Develop class OrderAdapter using Adapter design pattern,
 * to adapt Order to use an ItemAPI object
 * 
 * @author dpeters
 *
 */

public class GrubHub {
	/**
	 * static initialization block REQUIRED FOR DevelopmentLogEnum
	 * executed only once
	 */
	static {
		/**
		 * Set outer class (replace Driver)
		 */
		DevelopmentLogEnum.LOG.setOuterClass(GrubHub.class);
	}

	/**
	 * object initialization block REQUIRED FOR DevelopmentLogEnum
	 * executed with each constructor
	 */
	{
		/**
		 * Set outer class (use DevelopmentLogEnum defined below)
		 */
		DevelopmentLogEnum.LOG.setOuterClass(this.getClass());
	}

	/**
	 * test Point of Sale (POS) Bridge Abstraction Phase I Pos1 (Calculator1) and
	 * Phase II Pos2 (Calculator2)
	 */
	public static void demoPos(Double... a) {
		System.out.println("\n\t" + GrubHub.class.getName() + ".demoPos()...");
		{
			System.out.println("TESTING Pos1 (sumTotal(" + a[0] + ", " + a[1] + ", " + a[2] + "):...");

			Pos1 obj = new Pos1(new Calculator1());

			double val = obj.sumTotal(Arrays.asList(a));
			System.out.println(val);

			System.out.println("applying 0% Normal Discount");
			obj.setStrategy(new NormalDiscount());
			obj.sumTotal(Arrays.asList(a));
			System.out.println(obj);

			System.out.println("applying 10% Member Discount");
			obj.setStrategy(new MemberDiscount());
			obj.sumTotal(Arrays.asList(a));
			System.out.println(obj);

			System.out.println("applying 20% Special Discount");
			obj.setStrategy(new SpecialDiscount());
			obj.sumTotal(Arrays.asList(a));
			System.out.println(obj);

			System.out.println("applying 20% SuperBowl MarkUp");
			obj.setStrategy(new SuperBowlDiscount());
			obj.sumTotal(Arrays.asList(a));
			System.out.println(obj);
		}
		{
			System.out.println("TESTING Pos2 (sumTotal(" + a[0] + ", " + a[1] + ", " + a[2] + "):...");

			Pos2 obj = new Pos2(new Calculator2());

			obj.sumTotal(Arrays.asList(a));
			obj.getChange(20.0d);
			System.out.println(obj);

			System.out.println("applying 10% Normal Discount");
			obj.setStrategy(new NormalDiscount());
			obj.sumTotal(Arrays.asList(a));
			obj.getChange(20.0d);
			System.out.println(obj);

			System.out.println("applying 10% Member Discount");
			obj.setStrategy(new MemberDiscount());
			obj.sumTotal(Arrays.asList(a));
			obj.getChange(20.0d);
			System.out.println(obj);

			System.out.println("applying 20% Special Discount");
			obj.setStrategy(new SpecialDiscount());
			obj.sumTotal(Arrays.asList(a));
			obj.getChange(20.0d);
			System.out.println(obj);

			System.out.println("applying 20% SuperBowl MarkUp");
			obj.setStrategy(new SuperBowlDiscount());
			obj.sumTotal(Arrays.asList(a));
			obj.getChange(20.0d);
			System.out.println(obj);
		}
		System.out.println("\n\t" + GrubHub.class.getName() + ".demoPos()... done!");
	}

	/**
	 * test the Point of Sale (POS) Bridge Abstraction
	 */
	public static void testPos() {
		System.out.println("\n\t" + GrubHub.class.getName() + ".testPos()...");

		/*
		 * Using: 1.1,2.2,3.1
		 * Test Pos1: sumTotal
		 * Test Pos2: getChange
		 */
		Double array[] = { 1.1, 2.2, 3.3 };

		GrubHub.demoPos(array);

		System.out.println("\n\t" + GrubHub.class.getName() + ".testPos()... done! \n\n");
	}

	/**
	 * test Point of Sale (POS) Bridge Implementor
	 */
	public static void testCalculator() {
		System.out.println("\n\t" + GrubHub.class.getName() + ".testCalculator()...");

		/*
		 * Using: a = 4.2, b = 2.1
		 * Test Calculator1: add
		 * Test Calculator2: sub, mult, div
		 */
		double a = 4.2;
		double b = 2.1;
		GrubHub.demoCalculator(a, b);

		System.out.println("\n\t" + GrubHub.class.getName() + ".testCalculator()... done!");
	}

	/**
	 * Student ToDO:
	 * Adapter Design Pattern:
	 * <p>
	 * 4. Develop inner class OrderAdapter using Adapter design pattern,
	 * to adapt Order to use an ItemAPI object
	 */

	public static void demoAdapterPattern() {
		Customer cus1 = new Customer(1, "Dan Adapter Order");
		System.out.println("--------------------------------");

		// Creating items using Builder
		Item frenchFries = new Item.ItemBuilder("French Fries", 1)
				.build();

		Item mediumDrink = new Item.ItemBuilder("Medium Drink", 2)
				.build();

		Item hamburger = new Item.ItemBuilder("Hamburger", 5)
				.build();

		// Creating order menu
		// Items list of the Door Dash Rst.
		List<ItemAPI> items = new ArrayList<>();
		items.add(frenchFries);
		items.add(mediumDrink);
		items.add(hamburger);

		System.out.println("Ordering items for Customer....");
		List<ItemAPI> orderedItem1 = new ArrayList<>();
		orderedItem1.add(items.get(0));
		orderedItem1.add(items.get(1));

		// Ordering for Customer 1
		Order cust1Order = new Order(1, cus1.getName() + " Order", "Description");
		cust1Order.setOrderedItem(orderedItem1);

		// Create an Order object
		// Adapt the Order object to an ItemAPI object using OrderAdapter
		ItemAPI orderAdapter = new OrderAdapter(cust1Order);

		// Use the ItemAPI object to get the price and name of the order
		double totalPrice = orderAdapter.getPrice();
		String orderName = orderAdapter.getName();

		// Print out the order details
		System.out.println("Order Name: " + orderName);
		System.out.println("Total Price: " + totalPrice);
	}

	/**
	 * Student ToDO:
	 * Builder Design Pattern:
	 * <p>
	 * Using Builder design pattern, develop inner class ItemBuilder
	 * to custom configure an Item object
	 */

	// Builder pattern to create objects
	public static void builderPattern() {
		System.out.println("Using Builder Pattern to create Items");
		Item frenchFries = new Item.ItemBuilder("French Fries", 0.99)
				.build();

		Item mediumDrink = new Item.ItemBuilder("Medium Drink", 1.29)
				.build();

		Item hamburger = new Item.ItemBuilder("Hamburger", 3.49)
				.build();

		Item hotDog = new Item.ItemBuilder("Hot Dog", 1.49)
				.build();

		Item cheeseburger = new Item.ItemBuilder("Cheeseburger", 3.79)
				.build();

		Item buffaloWings = new Item.ItemBuilder("Buffalo Wings", 4.49)
				.build();

		Item salad = new Item.ItemBuilder("Salad", 2.49)
				.build();

		System.out.println("Print Items from Builder Pattern");
		System.out.println(frenchFries);
		System.out.println(mediumDrink);
		System.out.println(hamburger);
		System.out.println(hotDog);
		System.out.println(cheeseburger);
		System.out.println(buffaloWings);
		System.out.println(salad);
		System.out.println("======================================");
		System.out.println("");
	}

	private static void createOrderUsingBuilder() {
		Customer cus1 = new Customer(1, "Dan");
		Customer cus2 = new Customer(2, "Jim");
		System.out.println(cus1);
		System.out.println(cus2);
		System.out.println("Customer Created..");
		System.out.println("--------------------------------");

		// Creating items using Builder
		Item frenchFries = new Item.ItemBuilder("French Fries", 0.99)
				.build();

		Item mediumDrink = new Item.ItemBuilder("Medium Drink", 1.29)
				.build();

		Item hamburger = new Item.ItemBuilder("Hamburger", 3.49)
				.build();

		Item hotDog = new Item.ItemBuilder("Hot Dog", 1.49)
				.build();
		// Creating order menu
		// Items list of the Door Dash Rst.
		List<ItemAPI> items = new ArrayList<>();
		items.add(frenchFries);
		items.add(mediumDrink);
		items.add(hamburger);

		System.out.println("Ordering items for Customer 1....");
		List<ItemAPI> orderedItem1 = new ArrayList<>();
		orderedItem1.add(items.get(0));
		orderedItem1.add(items.get(1));

		System.out.println("Ordering items for Customer 2....");
		List<ItemAPI> orderedItem2 = new ArrayList<>();
		orderedItem1.add(items.get(2));
		orderedItem1.add(items.get(1));

		// Ordering for Customer 1
		Order cust1Order = new Order(1, cus1.getName() + " Order", "Description");
		cust1Order.setOrderedItem(orderedItem1);

		// Ordering for Customer 2
		Order cust2Order = new Order(2, cus2.getName() + " Order", "Description");
		cust2Order.setOrderedItem(orderedItem2);

		System.out.println(Arrays.toString(items.toArray()));
		System.out.println("===========================================");

	}

	/**
	 * Student ToDO:
	 * Composite Design Pattern:
	 *
	 * Using Composite and Builder design pattern,
	 * develop inner classes:
	 *
	 * Order, IndividualOrder, IndividualOrderBuilder,
	 * ComboOrder and ComboOrderBuilder
	 *
	 */

	/**
	 * Student ToDO:
	 * Decorator Design Pattern:
	 * <p>
	 * Using Decorator design pattern,
	 * develop inner classes:
	 * <p>
	 * ItemDecoratorAPI, and others as needed for all ItemAPI options
	 */

	public static void decoratorPattern() {
		System.out.println("Using decoratorPattern...");
		ItemAPI pizza = new Sausage(new Mushrooms(new Onions(new Pepperoni(new Cheese(new Pizza())))));
		System.out.println(pizza.getName() + " $" + pizza.getPrice());
		System.out.println("======================================");
		System.out.println("");
	}

	/**
	 * /**
	 * Student ToDO:
	 * Factory Design Pattern:
	 * <p>
	 * Using Factory design pattern and supplied ItemFactoryAPI,
	 * develop inner classes:
	 * <p>
	 * ItemFactory, ItemFactoryEnumSingleton,
	 * ItemFactoryEagerSingleton and ItemFactoryLazySingleton
	 * <p>
	 * Using Factory design pattern and supplied OrderComponentFactoryAPI,
	 * develop inner classes:
	 * <p>
	 * IndividualOrderComponentFactory,
	 * IndividualOrderComponentFactoryEnumSingleton,
	 * IndividualOrderComponentFactoryEagerSingleton and
	 * IndividualOrderComponentFactoryLazySingleton
	 * <p>
	 * ComboOrderComponentFactory, ComboOrderComponentFactoryEnumSingleton,
	 * ComboOrderComponentFactoryEagerSingleton and
	 * ComboOrderComponentFactoryLazySingleton
	 */

	public static void factoryItem() {
		System.out.println("Creating Items using Factory Patterns...");
		Item item1 = ItemFactoryEagerSingleton.getInstance();
		Item item2 = ItemFactoryLazySingleton.getInstance();
		Item item3 = ItemFactoryEnumSingleton.INSTANCE.getObject();

		item1.setNameAndPrice("French Fries", 0.99);
		item2.setNameAndPrice("Medium Drink", 1.29);
		item3.setNameAndPrice("Hamburger", 3.49);

		System.out.println(item1);
		System.out.println(item2);
		System.out.println(item3);

		System.out.println("======================================");
		System.out.println("");
	}

	private static void createOrderUsingFactory() {
		// Creating customers
		Customer cus1 = new Customer(1, "Dan");
		Customer cus2 = new Customer(2, "Jim");
		System.out.println(cus1);
		System.out.println(cus2);
		System.out.println("Customer Created..");
		System.out.println("--------------------------------");

		// Creating items using Factory
		Item item1 = ItemFactoryEagerSingleton.getInstance();
		Item item2 = ItemFactoryLazySingleton.getInstance();
		Item item3 = ItemFactoryEnumSingleton.INSTANCE.getObject();

		item1.setNameAndPrice("French Fries", 0.99);
		item2.setNameAndPrice("Medium Drink", 1.29);
		item3.setNameAndPrice("Hamburger", 3.49);

		// Creating order menu
		// Items list of the Door Dash Rst.
		List<ItemAPI> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		System.out.println("Ordering items for Customer 1....");
		List<ItemAPI> orderedItem1 = new ArrayList<>();
		orderedItem1.add(items.get(0));
		orderedItem1.add(items.get(1));

		System.out.println("Ordering items for Customer 2....");
		List<ItemAPI> orderedItem2 = new ArrayList<>();
		orderedItem1.add(items.get(2));
		orderedItem1.add(items.get(1));

		// Ordering for Customer 1
		Order cust1Order = new Order(1, cus1.getName() + " Order", "Description");
		cust1Order.setOrderedItem(orderedItem1);

		// Ordering for Customer 2
		Order cust2Order = new Order(2, cus2.getName() + " Order", "Description");
		cust2Order.setOrderedItem(orderedItem2);

		System.out.println(Arrays.toString(items.toArray()));
		System.out.println("--------------------------------");

	}

	/**
	 * test Point of Sale Bridge (POS) Implementor Phase I Calculator1 and Phase II
	 * Calculator2
	 */
	public static void demoCalculator(double a, double b) {
		System.out.println("\n\t" + GrubHub.class.getName() + ".demoCalculator()...");
		{
			System.out.println("TESTING Calculator1 (add " + a + "," + b + "):...");
			Calculator1 obj = new Calculator1();
			double val = obj.add(a, b);
			System.out.println(val);
		}
		{
			System.out.println("TESTING Calculator2 (add,sub,mult,div " + a + "," + b + "):...");
			Calculator2 obj = new Calculator2();
			double val1 = obj.add(a, b);
			System.out.println(val1);
			double val2 = obj.sub(a, b);
			System.out.println(val2);
			double val3 = obj.mult(a, b);
			System.out.println(val3);
			double val4 = obj.div(a, b);
			System.out.println(val4);
		}
		System.out.println("\n\t" + GrubHub.class.getName() + ".demoCalculator()... done!");
	}

	/**
	 * demonstrate the use of class GrubHub
	 */
	public static void demo() {
		System.out.println("\n" + GrubHub.class.getName() + "demo()...");
		System.out.println(DevelopmentLogEnum.LOG.getDevelopmentLog()); // REMOVE MY SOLUTION

		/**
		 * test Calculator
		 */
		GrubHub.testCalculator();

		/**
		 * test Point of Sale (PoS)
		 */
		GrubHub.testPos();

		/**
		 * Student ToDO:
		 * use Point of Sale (PoS) to total all orders with all discounts
		 */

		/*
		 * 20 POINTS
		 * 1. Create following food items using the following design patterns:
		 * Builder,
		 * Decorator,
		 * Singleton Factory (Eager, Lazy and Enum implementations)
		 * AND with the following attributes:
		 */
		System.out.println("Creating Item Object using various patterns\n");
		builderPattern();
		decoratorPattern();
		factoryItem();

		/*
		 * * 30 POINTS
		 * 2. Create the following customer orders using the following design patterns:
		 * Builder,
		 * Singleton Factory (Eager, Lazy and Enum implementations)
		 */
		System.out.println("Creating orders for customer using Builder Pattern..");
		createOrderUsingBuilder();
		System.out.println("Creating orders for customer using Factory Pattern..");
		createOrderUsingFactory();

		/*
		 * 3.Develop TWO Point of Sale APIs for Order purchases with DISCOUNTS (to
		 * change prices):
		 * NormalDiscount (0% decrease),
		 * MemberDiscount (10% decrease),
		 * SpecialDiscount (20% decrease),
		 * SuperBowlDiscount (20% increase)
		 */
		// Demo for Pos1
		demoStrategyPos1();
		// Demo for Pos2
		demoPos2();

		/*
		 * 4. Develop class OrderAdapter using Adapter design pattern,
		 * to adapt Order to use an ItemAPI object
		 */
		demoAdapterPattern();

		System.out.println("\n\t" + GrubHub.class.getName() + "demo()... done!");
	}

	/**
	 * OrderComponentFactoryAPI for Factory method design pattern
	 * 
	 * @author dpeters
	 *
	 */
	// private interface OrderComponentFactoryAPI {
	// OrderComponentAPI getObject();
	// OrderComponentAPI getObject(OrderBuilderAPI b);
	// }
	//

	/**
	 * Student ToDO:
	 * Strategy Design Pattern:
	 * <p>
	 * Using Strategy design pattern to change retail prices:
	 * NormalDiscount (0% decrease), MemberDiscount (10% decrease),
	 * SpecialDiscount (20% discount), SuperBowlDiscount (20% increase)
	 */
	private static void demoStrategyPos1() {
		System.out.println("Pos1 Demo...");

		Customer cus1 = new Customer(1, "Dan");
		Customer cus2 = new Customer(2, "Jim");
		System.out.println(cus1);
		System.out.println(cus2);
		System.out.println("Customer Created..");
		System.out.println("--------------------------------");

		// Creating items using Builder
		Item frenchFries = new Item.ItemBuilder("French Fries", 0.99)
				.build();

		Item mediumDrink = new Item.ItemBuilder("Medium Drink", 1.29)
				.build();

		Item hamburger = new Item.ItemBuilder("Hamburger", 3.49)
				.build();

		// Creating order menu
		// Items list of the Door Dash Rst.
		List<ItemAPI> menuItems = new ArrayList<>();
		menuItems.add(frenchFries);
		menuItems.add(mediumDrink);
		menuItems.add(hamburger);

		System.out.println("Ordering menuItems for Customer 1....");
		List<ItemAPI> orderedItem1 = new ArrayList<>();
		orderedItem1.add(menuItems.get(0));
		orderedItem1.add(menuItems.get(1));

		// Ordering for Customer 1
		Order cust1Order = new Order(1, cus1.getName() + " Order", "Description");
		cust1Order.setOrderedItem(orderedItem1);

		System.out.println("--------------------------------");

		Pos1 pos = new Pos1(new Calculator1());

		// Apply no discount
		pos.setStrategy(new NormalDiscount());
		List<Double> items = Arrays.asList(cust1Order.getPrice());
		pos.sumTotal(items);
		System.out.println("Order Price with NormalDiscount : " + pos.toStringSumTotal());

		// Apply 10% member discount
		pos.setStrategy(new MemberDiscount());
		pos.sumTotal(items);
		System.out.println("Order Price with MemberDiscount : " + pos.toStringSumTotal());

		// Apply 20% special discount
		pos.setStrategy(new SpecialDiscount());
		pos.sumTotal(items);
		System.out.println("Order Price with SpecialDiscount : " + pos.toStringSumTotal());

		// Apply 20% Super Bowl markup
		pos.setStrategy(new SuperBowlDiscount());
		pos.sumTotal(items);
		System.out.println("Order Price with SuperBowlDiscount : " + pos.toStringSumTotal());
		System.out.println("======================================-");
	}

	private static void demoPos2() {
		System.out.println("Pos2 Demo...");
		Pos2 pos = new Pos2(new Calculator2());
		List<Double> items = Arrays.asList(10.0, 20.0, 30.0);
		pos.sumTotal(items);
		System.out.println(pos.toStringSumTotal());

		double cash = 50.0;
		pos.getChange(cash);
		System.out.println("Cash By Customer : " + pos.toStringCash());
		System.out.println("Return back Change amount: " + pos.toStringChange());
		System.out.println("======================================-");
	}

	/**
	 * Student ToDO:
	 * Bridge Design Pattern:
	 *
	 * Develop TWO Point of Sale APIs for Order purchases with DISCOUNTS (to change
	 * prices):
	 * Using Bridge design pattern to in TWO design phases
	 * developing inner interfaces PosAPI1, PosAPI2, CalculatorAPI1, CalculatorAPI2
	 * and
	 * developing inner classes Pos1, Pos2, Calculator1 and Calculator2
	 *
	 */

	/**
	 * Testing Code for GrubHub class
	 */

	/**
	 * DevelopmentLogEnum manages development log entries for this class
	 *
	 * @author dgpeters
	 */
	public static enum DevelopmentLogEnum {
		LOG;

		private static final int MAJOR_REVISION;
		private static final int MINOR_REVISION;
		private static final String REVISION;
		private static final int DEV_LOG_LENGTH;
		private final static List<String> DEVELOPMENT_LOG_ENTRY_LIST;

		/**
		 * static initialization block
		 * executed once for this class
		 */
		static {
			MAJOR_REVISION = 4;
			MINOR_REVISION = 6;
			DEV_LOG_LENGTH = 43;
			REVISION = MAJOR_REVISION + "." + MINOR_REVISION + "." + DEV_LOG_LENGTH;
			/**
			 * Detailed development log for this class
			 *
			 * NOTE: Everything here is contained in the specification of this enum
			 * DevelopmentLogEnum
			 * and is compiled to be initialized BEFORE any code execution
			 * EVERYTHING IN THIS LOG IS CODE TO BE COMPILED AND NEVER EXECUTES
			 * EXCEPT TO DISPLAY
			 * DEVELOPMENT_LOG_ENTRY_LIST
			 * Strings AND
			 * REVISION
			 * ON THE CONSOLE (STDOUT) OUTPUT.
			 *
			 *
			 * FOR EACH CHANGE to this class during development
			 * (e.g., bug fixes or new features, inner classes or inner interfaces),
			 * add a detailed descriptive Literal String (with trailing comma) to this list
			 * BEFORE " **End of Log Entry List **" String
			 * e.g.
			 *
			 * "1.2: made changes to blah blah to fix bug blah",
			 * " **End of Log Entry List **"
			 *
			 * OR
			 *
			 * "3.7: created new inner class blah to add new blah blah feature",
			 * " **End of Log Entry List **"
			 *
			 */
			DEVELOPMENT_LOG_ENTRY_LIST = new ArrayList<>(Arrays.asList(
					"1.0: initial version of class created",
					"** End of Log Entry List **"));
		} // end static block

		private Class outerClass = null;

		public Class getOuterClass() {
			return outerClass;
		}

		public void setOuterClass(Class outerClass) {
			this.outerClass = outerClass;
		}

		public String getRevision() {
			return DevelopmentLogEnum.REVISION + "." + DevelopmentLogEnum.DEVELOPMENT_LOG_ENTRY_LIST.size();
		}

		/**
		 * revisionLog shows all the entries in the development log made during the
		 * development of this class
		 *
		 * @return
		 */
		public String getDevelopmentLog() {
			StringBuilder sb = new StringBuilder(outerClass.getSimpleName());

			sb.append(" Development Log ").append(this.getRevision()).append("\n");
			DevelopmentLogEnum.DEVELOPMENT_LOG_ENTRY_LIST.forEach((e) -> sb.append(e).append("\n"));

			return sb.toString();
		}
	} // end of enum DevelopmentLogEnum

	public enum ItemFactoryEnumSingleton implements ItemFactoryAPI {
		INSTANCE;

		@Override
		public Item getObject() {
			return new Item();
		}

		@Override
		public Item getObject(Item.ItemBuilder b) {
			return new Item();
		}
	}

	/**
	 * ItemAPI implemented by all Item objects for sale
	 *
	 * @author dpeters
	 */
	private interface ItemAPI {
		double getPrice();

		String getName();
	}

	/**
	 * OrderAPI implemented by all Order objects for customer orders
	 *
	 * @author dpeters
	 */
	private interface OrderAPI {
		int getId();

		double getPrice();

		String getName();

		String getDescription();
	}

	/**
	 * ItemFactoryAPI for Factory method design pattern
	 *
	 * @author dpeters
	 */
	private interface ItemFactoryAPI {
		ItemAPI getObject();

		ItemAPI getObject(Item.ItemBuilder b);
	}

	// interface PriceStrategyAPI {
	// double applyDiscount(double price);
	// }

	public interface CalculatorAPI1 {
		double add(double a, double b);
	}

	public interface CalculatorAPI2 {
		double sub(double a, double b);

		double mult(double a, double b);

		double div(double a, double b);
	}

	interface PosAPI1 {
		double sumTotal(List<Double> itemList); // add customer total

		void setStrategy(PriceStrategyAPI s); // set strategy for price adjustments

		double getSum(); // return sum total

		String toStringSumTotal(); // return String representation of sum total
	}

	interface PosAPI2 {
		double getChange(double cash); // accept cash, return change

		String toStringCash(); // return String representation of customer cash submitted

		String toStringChange(); // return String representation of customer change received
	}

	public interface PriceStrategyAPI {
		public double calculatePrice(double price);
	}

	public static class Item implements ItemAPI {

		private double price;
		private String name;

		public Item() {
		}

		public Item(double price, String name) {
			this.price = price;
			this.name = name;
		}

		@Override
		public double getPrice() {
			return this.price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setNameAndPrice(String name, double price) {
			this.name = name;
			this.price = price;
		}

		@Override
		public String toString() {
			return "Item{" +
					"price=" + price +
					", name='" + name + '\'' +
					'}';
		}

		static class ItemBuilder {
			private String name;
			private double price;

			public ItemBuilder(String name, double price) {
				this.name = name;
				this.price = price;
			}

			public Item build() {
				Item item = new Item();
				item.name = this.name;
				item.price = this.price;

				return item;
			}
		}
	}

	static class Order implements OrderAPI {

		private int id;
		private String name;

		private String description;

		private List<ItemAPI> orderedItem;

		public Order(int id, String name, String description) {
			this.id = id;
			this.name = name;
			this.description = description;
		}

		public Order() {
		}

		@Override
		public double getPrice() {
			double totalPrice = 0.0;
			for (ItemAPI item : this.orderedItem) {
				totalPrice += item.getPrice();
			}
			return totalPrice;
		}

		@Override
		public int getId() {
			return this.id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String getDescription() {
			return this.description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "Order{" +
					"id=" + id +
					", name='" + name + '\'' +
					", description='" + description + '\'' +
					'}';
		}

		public List<ItemAPI> getOrderedItem() {
			return orderedItem;
		}

		public void setOrderedItem(List<ItemAPI> orderedItem) {
			this.orderedItem = orderedItem;
		}
	}

	static class Calculator1 implements CalculatorAPI1 {

		@Override
		public double add(double a, double b) {
			return a + b;
		}

		@Override
		public String toString() {
			return "Calculator1";
		}

	}

	static class Calculator2 extends Calculator1 implements CalculatorAPI2 {
		public Calculator2() {
		}

		@Override
		public double sub(double a, double b) {
			return a - b;
		}

		@Override
		public double mult(double a, double b) {
			return a * b;
		}

		@Override
		public double div(double a, double b) {
			return a / b;
		}

		@Override
		public String toString() {
			return "Calculator2";
		}
	}

	static class Customer {
		int id;
		String name;

		List<Order> orderList;

		public Customer(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public Customer(int id, String name, List<Order> orderList) {
			this.id = id;
			this.name = name;
			this.orderList = orderList;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void addOrder(Order o) {
			orderList.add(o);
		}

		public void removeOrder(Order o) {
			orderList.remove(o);
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", name=" + name + " ]";
		}

	}

	public static abstract class ItemDecoratorAPI implements ItemAPI {
		private final ItemAPI item;

		public ItemDecoratorAPI(ItemAPI item) {
			this.item = item;
		}

		@Override
		public double getPrice() {
			return item.getPrice() + getPriceAdditional();
		}

		@Override
		public String getName() {
			return item.getName() + ", " + getNameAdditional();
		}

		protected abstract double getPriceAdditional();

		protected abstract String getNameAdditional();
	}

	public static class Sausage extends ItemDecoratorAPI {
		private final double priceAdditional = 1.00;
		private final String nameAdditional = "sausage";

		public Sausage(ItemAPI item) {
			super(item);
		}

		@Override
		protected double getPriceAdditional() {
			return priceAdditional;
		}

		@Override
		protected String getNameAdditional() {
			return nameAdditional;
		}
	}

	public static class Pizza implements ItemAPI {
		private final double price = 5.49;
		private final String name = "$5.49 Pizza";

		@Override
		public double getPrice() {
			return price;
		}

		@Override
		public String getName() {
			return name;
		}
	}

	public static class Cheese extends ItemDecoratorAPI {
		private final double priceAdditional = 1.00;
		private final String nameAdditional = "cheese";

		public Cheese(ItemAPI item) {
			super(item);
		}

		@Override
		protected double getPriceAdditional() {
			return priceAdditional;
		}

		@Override
		protected String getNameAdditional() {
			return nameAdditional;
		}
	}

	public static class Mushrooms extends ItemDecoratorAPI {
		private final double priceAdditional = 1.00;
		private final String nameAdditional = "mushrooms";

		public Mushrooms(ItemAPI item) {
			super(item);
		}

		@Override
		protected double getPriceAdditional() {
			return priceAdditional;
		}

		@Override
		protected String getNameAdditional() {

			return nameAdditional;
		}
	}

	public static class Onions extends ItemDecoratorAPI {
		private final double priceAdditional = 1.00;
		private final String nameAdditional = "onions";

		public Onions(ItemAPI item) {
			super(item);
		}

		@Override
		protected double getPriceAdditional() {
			return priceAdditional;
		}

		@Override
		protected String getNameAdditional() {
			return nameAdditional;
		}
	}

	public static class Pepperoni extends ItemDecoratorAPI {
		private final double priceAdditional = 1.00;
		private final String nameAdditional = "pepperoni";

		public Pepperoni(ItemAPI item) {
			super(item);
		}

		@Override
		protected double getPriceAdditional() {
			return priceAdditional;
		}

		@Override
		protected String getNameAdditional() {
			return nameAdditional;
		}
	}

	public abstract static class ItemFactory implements ItemFactoryAPI {
	}

	public static class ItemFactoryEagerSingleton extends ItemFactory {

		private static Item instance = null;

		static {
			instance = new Item();
		}

		private ItemFactoryEagerSingleton() {
		}

		public static Item getInstance() {
			return instance;
		}

		@Override
		public ItemAPI getObject() {
			return instance;
		}

		@Override
		public ItemAPI getObject(Item.ItemBuilder b) {
			return instance;
		}
	}

	public static class ItemFactoryLazySingleton extends ItemFactory {

		private static Item instance = null;

		private ItemFactoryLazySingleton() {
		}

		public static synchronized Item getInstance() {
			if (instance == null)
				instance = new Item();
			return instance;
		}

		@Override
		public ItemAPI getObject() {
			return instance;
		}

		@Override
		public ItemAPI getObject(Item.ItemBuilder b) {
			return instance;
		}
	}

	public static class OrderAdapter implements ItemAPI {

		private OrderAPI order;

		public OrderAdapter(OrderAPI order) {
			this.order = order;
		}

		@Override
		public double getPrice() {
			return order.getPrice();
		}

		@Override
		public String getName() {
			return order.getName();
		}
	}

	public static class NormalDiscount implements PriceStrategyAPI {

		@Override
		public double calculatePrice(double price) {
			return price;
		}

		@Override
		public String toString() {
			return "NormalDiscount (0% decrease)";
		}

	}

	public static class MemberDiscount implements PriceStrategyAPI {

		@Override
		public double calculatePrice(double price) {
			return price * 0.9;
		}

		@Override
		public String toString() {
			return "MemberDiscount (10% decrease)";
		}

	}

	public static class SpecialDiscount implements PriceStrategyAPI {

		@Override
		public double calculatePrice(double price) {
			return price * 0.8;
		}

		@Override
		public String toString() {
			return "SpecialDiscount (20% decrease)";
		}

	}

	public static class SuperBowlDiscount implements PriceStrategyAPI {

		@Override
		public double calculatePrice(double price) {
			return price * 1.2;
		}

		@Override
		public String toString() {
			return "SuperBowlDiscount (20% increase)";
		}

	}

	public static class Pos2 extends Pos1 implements PosAPI2 {

		private double sum;
		private CalculatorAPI2 calculator;
		private PriceStrategyAPI strategy;
		private double cash;

		public Pos2(CalculatorAPI2 calculator) {
			this.calculator = calculator;
		}

		@Override
		public double sumTotal(List<Double> itemList) {
			sum = 0;
			for (Double item : itemList) {
				sum += item;
			}
			if (strategy != null) {
				sum = strategy.calculatePrice(sum);
			}
			return sum;
		}

		@Override
		public void setStrategy(PriceStrategyAPI s) {
			strategy = s;
		}

		@Override
		public double getSum() {
			return sum;
		}

		@Override
		public double getChange(double cash) {
			this.cash = cash;
			return calculator.sub(cash, sum);
		}

		@Override
		public String toStringCash() {
			return String.format("%.2f", cash);
		}

		@Override
		public String toStringChange() {
			return String.format("%.2f", calculator.sub(cash, sum));
		}

		@Override
		public String toString() {
			return "Pos2{" +
					"sum=" + sum +
					", strategy=" + strategy +
					", cash=" + cash +
					'}';
		}
	}

	public static class Pos1 implements PosAPI1 {
		private List<Double> itemList;
		private double sum;
		private CalculatorAPI1 calculator;
		private PriceStrategyAPI discount;

		public Pos1() {
		}

		public Pos1(CalculatorAPI1 calculator) {
			this.itemList = new ArrayList<>();
			this.sum = 0;
			this.calculator = calculator;
			this.discount = new NormalDiscount();
		}

		@Override
		public double sumTotal(List<Double> itemList) {
			this.itemList = itemList;
			this.sum = 0;
			for (Double item : itemList) {
				this.sum = calculator.add(this.sum, item);
			}
			this.sum = discount.calculatePrice(this.sum);
			return this.sum;
		}

		@Override
		public void setStrategy(PriceStrategyAPI s) {
			this.discount = s;
		}

		@Override
		public double getSum() {
			return this.sum;
		}

		@Override
		public String toStringSumTotal() {
			return "Total: $" + String.format("%.2f", this.sum);
		}

		@Override
		public String toString() {
			return "Pos1 [itemList=" + itemList + ", sum=" + sum + ", calculator=" + calculator + ", discount="
					+ discount + "]";
		}

	}

	public abstract class ItemDecorator implements ItemAPI {
		protected ItemAPI item;

		public ItemDecorator(ItemAPI item) {
			this.item = item;
		}
	}
}
