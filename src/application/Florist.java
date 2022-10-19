package application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Scanner;

import application.Decoration.MaterialType;
import application.Product.ProductType;

public class Florist {
	private String name;
	private ArrayList<Product> stock;
	private ArrayList<Ticket> tickets;
	
	public Florist(String name) {
		this.name = name;
		this.stock = new ArrayList<Product>();
		this.tickets = new ArrayList<Ticket>();
	}
	
	public String getName() {
		return name;
	}
	
	
	public void addProduct() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name:");
		
		String name = sc.nextLine();
		
		System.out.println("Introduce the price:");
		
		float price = sc.nextFloat();
		
		ProductType pType = askProductType();
		
		if (pType == ProductType.TREE) {
			this.addTree(name, price);
		} else if (pType == ProductType.FLOWER) {
			this.addFlower(name, price);
		} else if (pType == ProductType.DECORATION) {
			this.addDecoration(name, price);
		}	
	};

	public void addTree(String name, Float price) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce the height:");
		float height = sc.nextFloat();
		
		Tree t = findTree(name, price, height);
		
		if (t == null) {
			
			System.out.println("Introduce the quantity:");
			int quantity = sc.nextInt();
			
			this.stock.add(new Tree(name, price, quantity, height));
		} else {
			System.out.println("There already exists a tree with these properties.");
		}
		
	};
	
	public void addFlower(String name, Float price) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce the color:");
		String colour = sc.nextLine();
				
		Flower t = findFlower(name, price, colour);
		
		if (t == null) {
			
			System.out.println("Introduce the quantity:");
			int quantity = sc.nextInt();
			
			this.stock.add(new Flower(name, price, quantity, colour));
		} else {
			System.out.println("There already exists a flower with these properties.");
		}
	};
	
	public void addDecoration(String name, Float price) {
		Scanner sc = new Scanner(System.in);
		
		MaterialType mat = askMaterial();
			
		Decoration d = findDecoration(name, price, mat);
		
		if (d == null) {
			
			System.out.println("Introduce the quantity:");
			int quantity = sc.nextInt();
			
			this.stock.add(new Decoration(name, price, quantity, mat));
		} else {
			System.out.println("There already exists a flower with these properties.");
		}
			
	};
	
	public void removeProduct() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the id of the product:");
		
		int id = sc.nextInt();
		
		System.out.println("Introduce the quantity to remove:");
		int quantity = sc.nextInt();
		
		Product p = findProductId(id);
		
		int currentQuantity = p.getQuantity();
		
		if (currentQuantity > quantity) {
				p.setQuantity(currentQuantity-quantity);
		} else {
			System.out.println("There are less products than the number you want to remove. Setting the quantity to 0.");
			p.setQuantity(0);
		}
	};
	
	
	public void printStock() {
		
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			System.out.println(stock.get(i));
		}
	};
	
	public void printStockQuantities() {
		
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			Product p = stock.get(i) ;
			System.out.println(p + " - " + p.getQuantity() + " units");
		}
	};
	
	public void createTicket() {
		Ticket t = new Ticket();		
		this.tickets.add(t);
		
		boolean exit = false;
		
		while (!exit) {
			byte opt = ticketsMenu();
			
			switch(opt) {
			case 1: addTicketProduct(t); break;
			case 2: removeTicketProduct(t); break;
			case 3: t.computePrice(); break;
			case 4: t.closeTicket(); break;
			case 5: exit = true; break;
			}
		}
		
	};
	
	public void printTickets() {
		
		for (Ticket t : tickets) {
			System.out.println(t);
		}
	};
	
	public void printTotalGain() {
		int totalValue = 0;
		
		for (Ticket t : tickets) {
			
			totalValue += t.getPrice();
		}
		
		System.out.println("Total value of the florist: " + totalValue + " €");
	}


	@Override
	public String toString() {
		return "Florist " + name;
	};
	
	public Tree findTree(String name, Float price, Float height){
		
		Tree t = null;
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			Product p = stock.get(i);
			if (p.getType() == ProductType.TREE) {
				Tree _t = (Tree) p;
				if (_t.getName().equalsIgnoreCase(name) &&
						_t.getPrice() == price && _t.getHeight() == height) {
					t = _t;
				}
			}
		}
		return t;
	}
	
	public Flower findFlower(String name, Float price, String colour){
		
		Flower f = null;
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			Product p = stock.get(i);
			if (p.getType() == ProductType.FLOWER) {
				Flower _f = (Flower) p;
				if (_f.getName().equalsIgnoreCase(name) &&
						_f.getPrice() == price && _f.getColour() == colour) {
					f = _f;
				}
			}
		}
		return f;
	}

	
	public Decoration findDecoration(String name, Float price, MaterialType mat){
		
		Decoration d = null;
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			Product p = stock.get(i);
			if (p.getType() == ProductType.DECORATION) {
				Decoration _d = (Decoration) p;
				if (_d.getName().equalsIgnoreCase(name) &&
						_d.getPrice() == price && _d.getMaterial() == mat) {
					d = _d;
				}
			}
		}
		return d;
	}

	
	public MaterialType askMaterial() {
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
	
	
	public byte ticketsMenu() {
		final int MAX_OPTS = 4;
		byte opt = -1;
		
		Scanner sc = new Scanner(System.in);
		
		while (opt < 0 || opt > MAX_OPTS) {
			System.out.println("Choose the desired option:");
			System.out.println("    1. Add a product");
			System.out.println("    2. Remove a product");
			System.out.println("    3. Compute price");
			System.out.println("    4. Close ticket");
			System.out.println("    0. Exit");
			
			opt = sc.nextByte();
			
			if (opt < 0 || opt > MAX_OPTS) {
				System.out.println("This is not a valid option.");
			}
			
		}
		return opt;
	}
	
	
	public void addTicketProduct(Ticket t) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the product id:");
		
		int id = sc.nextInt();
		
		Product prod = findProductId(id);
		
		//todo falta aclarar aquesta part
		if (prod != null) {
			System.out.println("Introduce the quantity:");
			
			int quantity = sc.nextInt();
			
			int currentQuantity = prod.getQuantity();
			
			if (currentQuantity >= quantity) {
				t.addProduct(prod, quantity);
			} else {
				
				
				sc.nextLine();
				System.out.println("There are only " + quantity + " units left. Do you want to add all of them? (y/n)");
				String opt = sc.nextLine().toLowerCase();
				if (opt.charAt(0) == 'y') {
					t.addProduct(prod, currentQuantity);
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
			System.out.println("Introduce the quantity:");
			
			int quantity = sc.nextInt();
			
			int currentQuantity = t.getProductQuantity(id);
			
			if (currentQuantity >= quantity) {
				t.removeProduct(prod, quantity);
			} else {
				sc.nextLine();
				System.out.println("There are only " + currentQuantity + " units left. Do you want to add all of them? (y/n)");
				String opt = sc.nextLine().toLowerCase();
				if (opt.charAt(0) == 'y') {
					t.addProduct(prod, currentQuantity);
				} else {
					System.out.println("Exiting");
				}
			}
		} else {
			System.out.println("There are no products with this product id.");
		}
	};
	
	
	public Product findProduct(String name, Float price, ProductType type){
		Product p = null;
		int stockSize = stock.size();
	
		
		for (int i=0; i<stockSize; i++) {
			p = stock.get(i);
			
			
			if (p.getType() == type) {
				
				boolean typeCond = false;
				if (type == ProductType.TREE) {
					Tree t = (Tree) p ;
					if (t.getName().equalsIgnoreCase(name) &&
							t.getPrice() == price) {
						p = t;
					}
				} else if (type == ProductType.FLOWER) {
					Flower f = (Flower) p ;
					if (f.getName().equalsIgnoreCase(name) &&
							f.getPrice() == price) {
						p = f;
					}
				} else if (type == ProductType.DECORATION) {
					Decoration d = (Decoration) p ;
					if (d.getName().equalsIgnoreCase(name) &&
							d.getPrice() == price) {
						p = d;
					}
				}
			}
		}
		return p;
	}
	
	
	public Product findProductId(Integer id){
		Product p = null;
		int stockSize = stock.size();
		int counter = 0;
		boolean found = false;
		
		while (counter<stockSize && !found) {
			Product _p = stock.get(counter);
			
			
			if (_p.getId() == id) {
				p = _p;
				found = true;
			}
			counter++;
		}
		return p;
	}
	
	

	
}
