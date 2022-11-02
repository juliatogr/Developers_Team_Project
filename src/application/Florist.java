package application;

import java.util.ArrayList;
import java.util.Optional;

import application.Decoration.MaterialType;
import application.Product.ProductType;

/*
 * Class Florist to implement all functionalities of a typical florist.
 */

public class Florist {

	private String name; // name of the florist
	private ArrayList<Product> stock; // list of products available in the florist. The quantities of each are inside
										// Product class
	private ArrayList<Ticket> tickets; // list of tickets created in the florist.
	private Input input;
	private TxtPersistence txtpersistence;
	
	// Constructors

	public Florist(String name) {
		this.name = name;
		this.stock = new ArrayList<Product>();
		this.tickets = new ArrayList<Ticket>();
		this.input = MainClass.getInput();
		this.txtpersistence = MainClass.getTxtPersistence();
	}

	// Getters

	public String getName() {
		return this.name;
	}
	
	public ArrayList<Product> getStock() {
		return stock;
	}
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	
	//Setters
	
	public void setStock(ArrayList<Product> stock) {
		this.stock = stock;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	// Stock methods

	public void addProduct() {
		/*
		 * Method to add a product to the stock
		 */

		// Ask the generic attributes of a product
		ProductType pType = askProductType();

		String name = input.askString("Introduce the name:");

		double price = input.askDouble("Introduce the price:");

		Product p = null;

		// depending on the type, add the product calling the corresponding method
		if (pType == ProductType.TREE) {
			// Ask for the height of the tree
			double height = input.askDouble("Introduce the height:");
			p = new Tree(name, price, 0, height);

		} else if (pType == ProductType.FLOWER) {
			// Ask for the color of the flower
			String colour = input.askString("Introduce the colour:");
			p = new Flower(name, price, 0, colour);
		} else if (pType == ProductType.DECORATION) {
			// Ask for the material of the decoration
			MaterialType mat = askMaterial();
			p = new Decoration(name, price, 0, mat);
		}

		Product prod = findProduct(p);

		int quantity = input.askInt("Introduce the quantity to add:");

		if (prod != null) {
			prod.setQuantity(prod.getQuantity() + quantity);
		} else {
			p.setQuantity(quantity);
			this.stock.add(p);
			System.out.println("Product added");
		}
		this.txtpersistence.writeTxt(".\\utils\\" + this.name + "Stock.txt", stock);
	};

	public void removeProduct() {
		/*
		 * Method to remove a product from the stock
		 */

		// Find the product by id

		int id = input.askInt("Introduce the id of the product:");

		Product p = findProductId(id);

		// if there exists a product, ask for the quantity and remove it
		if (p != null) {

			int currentQuantity = p.getQuantity(); // Save the current quantity of the product

			// Ask the desired quantity of products to remove
			int quantity = input.askInt("Introduce the quantity to remove:");

			// Take care of negative quantities. If there are less units than the ones to
			// remove,
			// the quantity should be 0.
			if (currentQuantity > quantity) {
				p.setQuantity(currentQuantity - quantity);
				System.out.println("Removed " + quantity + " units of the product with id " + id);

			} else {
				System.out.println(
						"There are less products than the number you want to remove. Setting the quantity to 0.");
				p.setQuantity(0);
			}

			// otherwise, tell the user the product doesn't exist
		} else {
			System.out.println("There not exists a product with id " + id);
		}
		this.txtpersistence.writeTxt(".\\utils\\" + this.name + "Stock.txt", stock);
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
		 * Method to print all existing products in the stock with corresponding
		 * quantities
		 */
		int stockSize = stock.size();

		for (int i = 0; i < stockSize; i++) {
			Product p = stock.get(i);
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

			switch (opt) {
			case 1:
				addTicketProduct(t);
				break;
			case 2:
				removeTicketProduct(t);
				break;
			case 3:
				t.computePrice();
				break;
			case 4:
			
				if (t.getProducts().size() != 0) {
					
					t.closeTicket();
					this.txtpersistence.writeTxt(".\\utils\\" + this.name + "Stock.txt", stock);
					this.txtpersistence.writeTxt(".\\utils\\" + this.name + "Ticket.txt", tickets);
					System.out.println("Ticket closed.");
					
				} else {
					this.tickets.remove(t);
					Ticket.setIdIncrement(Ticket.getIdIncrement()-1);
					System.out.println("The ticket has no products. It won't be added to the florist.");
				}
				exit = true;
				break;
			}
		}
		this.txtpersistence.writeTxt(".\\utils\\" + this.name + "Stock.txt", stock);
		this.txtpersistence.writeTxt(".\\utils\\" + this.name + "Ticket.txt", tickets);
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

		while (opt < 1 || opt > MAX_OPTS) {

			opt = input.askByte("****Menu for ticket " + this.tickets.get(this.tickets.size() - 1).getId()
					+ "****\n\nChoose the desired option:\n    1. Add a product\n    2. Remove a product\n    3. Compute price\n    4. Close ticket and exit");

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

		int id = input.askInt("Introduce the product id:");

		Product stockProduct = findProductId(id);

		if (stockProduct != null) {

			Product ticketProduct = t.findProductId(stockProduct.getId());
			
			int currentQuantity = stockProduct.getQuantity(); // Save the current quantity of the product
			
			// Ask for the desired quantity of products to add
			int wantedQuantity = -1;
			while (wantedQuantity <=0) {
				wantedQuantity = input.askInt("Introduce the quantity:");
				
				if (wantedQuantity <=0) {
					System.out.println("The quantity should be > 0");
				}
			}
			
			int neededQuantity = wantedQuantity;
			
			if (ticketProduct != null) {
				neededQuantity += t.getProductQuantity(stockProduct.getId());
			}
			
			// Take care of negative quantities. If there are less units than the ones to add,
			// the quantity on stock should be 0 and on the ticket compensate the quantity.
			if (currentQuantity >= neededQuantity) {
				t.addProduct(stockProduct, wantedQuantity);
				System.out.println(wantedQuantity + " product units with id " + id + " added to the ticket " + t.getId());
			} else {

				String opt = input.askString(
						"There are only " + currentQuantity + " units left on stock. Do you want to add all of them? (y/n)")
						.toLowerCase();
				if (opt.charAt(0) == 'y') {
					t.addProduct(stockProduct, currentQuantity);
					System.out.println(
							currentQuantity + " product units with id " + id + " added to the ticket " + t.getId());

				} else {
					System.out.println("Exiting");
				}
			}			
			
		} else {
			System.out.println("There are no products with this product id.");
		}
	};

	public void removeTicketProduct(Ticket t) {

		int id = input.askInt("Introduce the product id:");

		Product prod = t.findProductId(id);

		// todo falta aclarar aquesta part
		if (prod != null) {
			int currentQuantity = t.getProductQuantity(id); // Save the current quantity of the product

			// Ask for the desired quantity of products to remove
			int quantity = input.askInt("Introduce the quantity:");

			// Take care of negative quantities. If there are less units than the ones to
			// remove,
			// remove the complete product on the ticket.
			if (currentQuantity >= quantity) {
				t.removeProduct(prod, quantity);
				System.out.println(quantity + " product units with id " + id + " removed from the ticket " + t.getId());

			} else {

				String opt = input.askString(
						"There are only " + currentQuantity + " units left. Do you want to remove all of them? (y/n)")
						.toLowerCase();

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
	public Product findProduct(Product prod) {
		/*
		 * Method to find a product by its characteristics
		 * 
		 * @param name - name of the product.
		 * 
		 * @param price - price of the product
		 * 
		 * @param type - type of the product.
		 * 
		 * @returns the product if found or null otherwise
		 */

		Optional<Product> pOpt = this.stock.stream().filter(_p -> _p.equals(prod)).findFirst();

		Product p = null;
		if (pOpt.isPresent()) {
			p = pOpt.get();
		}
		return p;
	}

	public Product findProductId(Integer id) {
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

	// Enum methods

	public MaterialType askMaterial() {
		/*
		 * Method to ask the material type of a decoration
		 * 
		 * @returns the material type
		 */

		boolean valid = false;

		MaterialType mat = null;

		while (!valid) {
			int matSelect = input.askInt("Select the material:\n    1. Wood\n    2. Plastic");
			switch (matSelect) {
			case 1:
				mat = MaterialType.WOOD;
				valid = true;
				break;
			case 2:
				mat = MaterialType.PLASTIC;
				valid = true;
				break;
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

		boolean valid = false;

		ProductType prod = null;

		while (!valid) {
			int prodSelect = input.askInt("Select the product type:\n    1. Tree\n    2. Flower\n    3. Decoration");
			switch (prodSelect) {
			case 1:
				prod = ProductType.TREE;
				valid = true;
				break;
			case 2:
				prod = ProductType.FLOWER;
				valid = true;
				break;
			case 3:
				prod = ProductType.DECORATION;
				valid = true;
				break;
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
