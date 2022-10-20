package application;
import java.util.ArrayList;
import java.util.Scanner;

import application.Decoration.MaterialType;
import application.Product.ProductType;

/*
 * Class Florist to implement all functionalities of a typical florist.
 */
@SuppressWarnings("resource")
public class Florist {

	private String name; 				// name of the florist
	private ArrayList<Product> stock; 	// list of products available in the florist. The quantities of each are inside Product class
	private ArrayList<Ticket> tickets; 	// list of tickets created in the florist.
	
	// Constructors
	
	public Florist(String name) {
		this.name = name;
		this.stock = new ArrayList<Product>();
		this.tickets = new ArrayList<Ticket>();
	}
	
	// Getters
	
	public String getName() {
		return name;
	}
	
	// Stock methods
	
	public void addProduct() {
		/*
		 * Method to add a product to the stock
		 */
		
		Scanner sc = new Scanner(System.in);
		
		// Ask the generic attributes of a product
		System.out.println("Introduce the name:");
		String name = sc.nextLine();
		
		System.out.println("Introduce the price:");
		float price = sc.nextFloat();
		
		System.out.println("Introduce the quantity:");
		int quantity = sc.nextInt();
		
		ProductType pType = askProductType();
		
		// depending on the type, add the product calling the corresponding method
		if (pType == ProductType.TREE) {
			this.addTree(name, price, quantity);
		} else if (pType == ProductType.FLOWER) {
			this.addFlower(name, price, quantity);
		} else if (pType == ProductType.DECORATION) {
			this.addDecoration(name, price, quantity);
		}	
	};

	public void addTree(String name, double price, int quantity) {
		/*
		 * Method to add a tree product to the stock
		 * 
		 * @param name - name of the product.
		 * @param price - price of the product
		 * @param quantity - quantity to add of the product
		 */
		Scanner sc = new Scanner(System.in);
		
		// Ask for the height of the tree
		System.out.println("Introduce the height:");
		float height = sc.nextFloat();
		
		// Take care of duplications
		Tree t = findTree(name, price, height);
		
		if (t == null) { 		// if there is no tree with these characteristics, create and add one.
			this.stock.add(new Tree(name, price, quantity, height));
			System.out.println("Tree created.");
			
		} else { 				// otherwise, just add the corresponding units to the one that already exists.
			t.setQuantity(t.getQuantity()+quantity);
			System.out.println("There already exists a tree with these properties. Adding " + quantity + " units.");
		}		
	};
	
	public void addFlower(String name, double price, int quantity) {
		/*
		 * Method to add a flower product to the stock
		 * 
		 * @param name - name of the product.
		 * @param price - price of the product
		 * @param quantity - quantity to add of the product
		 */
		Scanner sc = new Scanner(System.in);
		
		// Ask for the color of the flower
		System.out.println("Introduce the colour:");
		String colour = sc.nextLine();
				
		// Take care of duplications
		Flower f = findFlower(name, price, colour);
		
		if (f == null) {		// if there is no flower with these characteristics, create and add one.
			this.stock.add(new Flower(name, price, quantity, colour));
			System.out.println("Flower created.");
			
		} else {				// otherwise, just add the corresponding units to the one that already exists.
			System.out.println("There already exists a tree with these properties. Adding " + quantity + " units.");
			f.setQuantity(f.getQuantity()+quantity);
		}
	};
	
	public void addDecoration(String name, double price, int quantity) {
		/*
		 * Method to add a decoration product to the stock
		 * 
		 * @param name - name of the product.
		 * @param price - price of the product
		 * @param quantity - quantity to add of the product
		 */
		
		// Ask for the material of the decoration
		MaterialType mat = askMaterial();  
		
		// Take care of duplications
		Decoration d = findDecoration(name, price, mat);
		
		if (d == null) {		// if there is no decoration with these characteristics, create and add one.
			this.stock.add(new Decoration(name, price, quantity, mat));
			System.out.println("Decoration created.");
			
		} else {				// otherwise, just add the corresponding units to the one that already exists.
			System.out.println("There already exists a tree with these properties. Adding " + quantity + " units.");
			d.setQuantity(d.getQuantity()+quantity);
		}
	};
	
	public void removeProduct() {
		/*
		 * Method to remove a product from the stock
		 */
		
		// Find the product by id
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce the id of the product:");
		int id = sc.nextInt();
		
		Product p = findProductId(id);
		
		// if there exists a product, ask for the quantity and remove it
		if (p != null) {
			
			int currentQuantity = p.getQuantity(); // Save the current quantity of the product
			
			// Ask the desired quantity of products to remove
			System.out.println("Introduce the quantity to remove:");
			int quantity = sc.nextInt();
			
			// Take care of negative quantities. If there are less units than the ones to remove,
			// the quantity should be 0.
			if (currentQuantity > quantity) {
				p.setQuantity(currentQuantity-quantity);
				System.out.println("Removed " + quantity + " units of the product with id " + id);

			} else {
				System.out.println("There are less products than the number you want to remove. Setting the quantity to 0.");
				p.setQuantity(0);
			}
			
		// otherwise, tell the user the product doesn't exist
		} else {
			System.out.println("There not exists a product with id " + id);
		}
	};
	
	
	public void printStock() {
		/*
		 * Method to print all existing products in the stock
		 */
		int stockSize = stock.size();
		
		for (int i = 0; i < stockSize; i++) {
			System.out.println(stock.get(i));
		}
	};
	
	public void printStockQuantities() {
		/*
		 * Method to print all existing products in the stock with corresponding quantities
		 */
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			Product p = stock.get(i) ;
			System.out.println(p + " - " + p.getQuantity() + " units");
		}
	};
	
	// Ticket methods
	public void createTicket() {
		/*
		 * Method to create a new ticket and modify it before closing
		 */
		Ticket t = new Ticket();		
		this.tickets.add(t);
		
		boolean exit = false;
		
		while (!exit) {
			byte opt = ticketsMenu();
			
			switch(opt) {
			case 1: addTicketProduct(t); break;
			case 2: removeTicketProduct(t); break;
			case 3: t.computePrice(); break;
			case 4: 
				t.closeTicket(); 
				exit = true; 
				System.out.println("Ticket closed.");
				break;
			}
		}
		
	};
	
	public void printTickets() {
		/*
		 * Method to print all tickets
		 */
		for (Ticket t : tickets) {
			System.out.println(t);
		}
	};
	
	public byte ticketsMenu() {
		/**
		 * Menu for modifying a specific ticket.  
		 * 
		 * @return selected valid option
		**/
		final int MAX_OPTS = 4;
		byte opt = -1;
		
		Scanner sc = new Scanner(System.in);
		
		while (opt < 1 || opt > MAX_OPTS) {
			System.out.println();
			System.out.println("****Menu for ticket " + this.tickets.get(this.tickets.size()-1).getId() + "****");
			System.out.println();
			System.out.println("Choose the desired option:");
			System.out.println("    1. Add a product");
			System.out.println("    2. Remove a product");
			System.out.println("    3. Compute price");
			System.out.println("    4. Close ticket and exit");
			
			opt = sc.nextByte();
			
			if (opt < 1 || opt > MAX_OPTS) {
				System.out.println("This is not a valid option.");
			}
			
		}
		return opt;
	}
	
	// todo ticket methods on ticket
	public void addTicketProduct(Ticket t) {
		/*
		 * Method to add a product to a ticket
		 * 
		 * @param t - ticket to add the product
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the product id:");
		
		int id = sc.nextInt();
		
		Product prod = findProductId(id);
		
		if (prod != null) {
			
			int currentQuantity = prod.getQuantity();  // Save the current quantity of the product
			
			// Ask for the desired quantity of products to add
			System.out.println("Introduce the quantity:");
			int quantity = sc.nextInt();
			
			// Take care of negative quantities. If there are less units than the ones to add,
			// the quantity on stock should be 0 and on the ticket compensate the quantity. 
			if (currentQuantity >= quantity) {
				t.addProduct(prod, quantity);
				System.out.println(quantity + " product units with id " + id + " added to the ticket " + t.getId());
			} else {
				sc.nextLine();
				System.out.println("There are only " + quantity + " units left on stock. Do you want to add all of them? (y/n)");
				String opt = sc.nextLine().toLowerCase();
				if (opt.charAt(0) == 'y') {
					t.addProduct(prod, currentQuantity);
					System.out.println(currentQuantity + " product units with id " + id + " added to the ticket " + t.getId());

				} else {
					System.out.println("Exiting");
				}
			}
		} else {
			System.out.println("There are no products with this product id.");
		}
	};
	
	public void removeTicketProduct(Ticket t) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the product id:");
		
		int id = sc.nextInt();
		
		Product prod = t.findProductId(id);
		
		//todo falta aclarar aquesta part
		if (prod != null) {
			int currentQuantity = t.getProductQuantity(id);  // Save the current quantity of the product
			
			// Ask for the desired quantity of products to remove
			System.out.println("Introduce the quantity:");
			int quantity = sc.nextInt();
			
			// Take care of negative quantities. If there are less units than the ones to remove,
			// remove the complete product on the ticket.
			if (currentQuantity >= quantity) {
				t.removeProduct(prod, quantity);
				System.out.println(quantity + " product units with id " + id + " removed from the ticket " + t.getId());

			} else {
				sc.nextLine();
				System.out.println("There are only " + currentQuantity + " units left. Do you want to remove all of them? (y/n)");
				String opt = sc.nextLine().toLowerCase();
				
				if (opt.charAt(0) == 'y') {
					t.addProduct(prod, currentQuantity);
					System.out.println("All units of the product " + id + " removed from the ticket " + t.getId());

				} else {
					System.out.println("Exiting");
				}
			}
		} else {
			System.out.println("There are no products with this product id.");
		}
	};
	
	public void printTotalGain() {
		/*
		 * Method to print the total gain of the store
		 */
		int totalValue = 0;
		
		// The total gain would be the sum of every ticket price.
		for (Ticket t : tickets) {
			totalValue += t.getPrice();
		}
		
		System.out.println("Total value of the florist: " + totalValue + " €");
	}
	
	// Find methods
	public Product findProduct(String name, Float price, ProductType type){
		/*
		 * Method to find a product by its characteristics
		 * 
		 * @param name - name of the product.
		 * @param price - price of the product
		 * @param type - type of the product.
		 * 
		 * @returns the product if found or null otherwise
		 */
		Product p = null;
		
		int stockSize = stock.size();
		boolean found = false;
		
		int i = 0;
		Scanner sc = new Scanner(System.in);
		
		while (!found && i < stockSize) {
			Product _p = stock.get(i);
			
			// Check all generic conditions
			if (_p.getType() == type && _p.getPrice() == price && _p.getName().equalsIgnoreCase(name)) {
				
				//Check conditions dependig on the type of product
				boolean typeCond = false;
				
				if (type == ProductType.TREE) {
					Tree t = (Tree) _p;
					System.out.println("Introduce the height of the tree");
					double height = sc.nextDouble();
					typeCond = t.getHeight() == height;
		
				} else if (type == ProductType.FLOWER) {
					Flower f = (Flower) _p ;
					System.out.println("Introduce the colour of the flower");
					String colour = sc.nextLine();
					typeCond = f.getColour() == colour;
			
				} else if (type == ProductType.DECORATION) {
					Decoration d = (Decoration) _p ;
					MaterialType mat = askMaterial();
					typeCond = d.getMaterial() == mat;
				}
				
				// if all conditions are met, then _p is the corresponding product.
				if (typeCond) {
					p =_p;
					found = true;
				}
			}
			i++;
		}
		return p;
	}
	
	public Product findProductId(Integer id){
		/*
		 * Method to find a product by its id
		 * 
		 * @param id - id of the product.
		 * 
		 * @returns the product if found or null otherwise
		 */
		Product p = null;
		int stockSize = stock.size();
		int i = 0;
		boolean found = false;
		
		while (!found && i < stockSize) {
			Product _p = stock.get(i);
			
			if (_p.getId() == id) {
				p = _p;
				found = true;
			}
			i++;
		}
		return p;
	}
	
	public Tree findTree(String name, double price, Float height){
		/*
		 * Method to find a tree by its characteristics
		 * 
		 * @param name - name of the tree
		 * @param price - price of the tree
		 * @param height - height of the tree
		 * 
		 * @returns the tree if found or null otherwise
		 */
		Tree t = null;
		int stockSize = stock.size();
		
		for (int i = 0; i < stockSize; i++) {
			Product p = stock.get(i);
			if (p.getType() == ProductType.TREE && p.getName().equalsIgnoreCase(name) && p.getPrice() == price) {
				Tree _t = (Tree) p;
				if (_t.getHeight() == height) {
					t = _t;
				}
			}
		}
		return t;
	}
	
	public Flower findFlower(String name, double price, String colour){
		/*
		 * Method to find a flower by its characteristics
		 * 
		 * @param name - name of the flower
		 * @param price - price of the flower
		 * @param colour - colour of the flower
		 * 
		 * @returns the flower if found or null otherwise
		 */
		Flower f = null;
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			Product p = stock.get(i);
			if (p.getType() == ProductType.FLOWER && p.getName().equalsIgnoreCase(name) && p.getPrice() == price) {
				Flower _f = (Flower) p;
				if (_f.getColour() == colour) {
					f = _f;
				}
			}
		}
		return f;
	}
	
	public Decoration findDecoration(String name, double price, MaterialType mat){
		/*
		 * Method to find a decoration by its characteristics
		 * 
		 * @param name - name of the decoration
		 * @param price - price of the decoration
		 * @param mat - material of the decoration
		 * 
		 * @returns the decoration if found or null otherwise
		 */
		Decoration d = null;
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			Product p = stock.get(i);
			if (p.getType() == ProductType.DECORATION && p.getName().equalsIgnoreCase(name) && p.getPrice() == price) {
				Decoration _d = (Decoration) p;
				if (_d.getMaterial() == mat) {
					d = _d;
				}
			}
		}
		return d;
	}	
	
	// Enum methods
	
	public MaterialType askMaterial() {
		/*
		 * Method to ask the material type of a decoration
		 * 
		 * @returns the material type
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Select the material:");
		System.out.println("    1. Wood");
		System.out.println("    2. Plastic");
		
		boolean valid = false;
		
		MaterialType mat = null;
		
		while(!valid) {
			int matSelect = sc.nextInt();
			switch(matSelect) {
			case 1: mat = MaterialType.WOOD; valid = true; break;
			case 2: mat = MaterialType.PLASTIC; valid = true; break;
			default:
				System.out.println("Not a valid option");
			}
		}
		return mat;
	}
	
	public ProductType askProductType() {
		/*
		 * Method to ask the product type of a product
		 * 
		 * @returns the product type
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("Select the product type:");
		System.out.println("    1. Tree");
		System.out.println("    2. Flower");
		System.out.println("    3. Decoration");
		
		boolean valid = false;
		
		ProductType prod = null;
		
		while(!valid) {
			int prodSelect = sc.nextInt();
			switch(prodSelect) {
			case 1: prod = ProductType.TREE; valid = true; break;
			case 2: prod = ProductType.FLOWER; valid = true; break;
			case 3: prod = ProductType.DECORATION; valid = true; break;
			default:
				System.out.println("Not a valid option");
			}
		}
		return prod;
	}
	
	// toString method
	@Override
	public String toString() {
		return "Florist " + name + " with " + this.stock.size() + " products and " + this.tickets.size() + " tickets.";
	};
	

	
}
