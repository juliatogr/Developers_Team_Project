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
		System.out.println("Introduce the name:");
		
		String name = sc.nextLine();
		
		System.out.println("Introduce the price:");
		
		float price = sc.nextFloat();
		
		ProductType pType = askProductType();
		
		if (pType == ProductType.TREE) {
			this.removeTree(name, price);
		} else if (pType == ProductType.FLOWER) {
			this.removeFlower(name, price);
		} else if (pType == ProductType.DECORATION) {
			this.removeDecoration(name, price);
		}	
	};
	
	public void removeTree(String name, Float price) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce the height of the tree:");
		Float height = sc.nextFloat();
		
		Tree t = findTree(name, height, price);
		
		if (t!= null) {
			System.out.println("Introduce the quantity to remove:");
			int quantity = sc.nextInt();
			
			int currentQuantity = t.getQuantity();
			
			if (currentQuantity > quantity) {
					t.setQuantity(t.getQuantity()-quantity);
			} else {
				System.out.println("There are less trees than the number you want to delete. Deleting all.");
				t.setQuantity(0);
			}
		
		} else {
			System.out.println("There no exists a tree with these properties.");
		}
	};
	
	public void removeFlower(String name, Float price) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce the colour of the flower:");
		String colour = sc.nextLine();
		
		Flower f = findFlower(name, price, colour);
		
		if (f!= null) {
			System.out.println("Introduce the quantity to remove:");
			int quantity = sc.nextInt();
			
			int currentQuantity = f.getQuantity();
			
			if (currentQuantity > quantity) {
					f.setQuantity(f.getQuantity()-quantity);
			} else {
				System.out.println("There are less flowers than the number you want to delete. Deleting all.");
				f.setQuantity(0);
			}
		
		} else {
			System.out.println("There no exists a flower with these conditions.");
		}
	};
	
	public void removeDecoration(String name, Float price) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce the colour of the decoration:");
		String colour = sc.nextLine();
		
		boolean valid = false;
		
		MaterialType mat = askMaterial();
		
		Decoration d = findDecoration(name, price, mat);
		
		if (d!= null) {
			System.out.println("Introduce the quantity to remove:");
			int quantity = sc.nextInt();
			
			int currentQuantity = d.getQuantity();
			
			if (currentQuantity > quantity) {
					d.setQuantity(d.getQuantity()-quantity);
			} else {
				System.out.println("There are less decorations than the number you want to delete. Deleting all.");
				d.setQuantity(0);
			}
		
		} else {
			System.out.println("There no exists a decoration with these conditions.");
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
		
		byte opt = ticketsMenu();
		
		boolean exit = false;
		
		while (!exit) {
			switch(opt) {
			case 1: addTicketProduct(); break;
			case 2: removeTicketProduct(); break;
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
			case 1: mat = MaterialType.WOOD; break;
			case 2: mat = MaterialType.PLASTIC; break;
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
			case 1: prod = ProductType.TREE; break;
			case 2: prod = ProductType.FLOWER; break;
			case 3: prod = ProductType.DECORATION; break;
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
	
	
	
	public void addTicketProduct() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name:");
		
		String name = sc.nextLine();
		
		System.out.println("Introduce the price:");
		
		float price = sc.nextFloat();	
		
		ProductType prod = askProductType();
		
		if (prod == ProductType.DECORATION) {
			MaterialType mat = askMaterial();
				
			Decoration d = findDecoration(name, price, mat);
			
			if (d == null) {
				
				System.out.println("Introduce the quantity:");
				
				int quantity = sc.nextInt();
				
				this.stock.add(new Decoration(name, price, quantity, mat));
			} else {
				System.out.println("There already exists a flower with these properties.");
			}
		}

			
	};
	
	public void removeTicketProduct() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce the name of the tree:");
		String name = sc.nextLine();
		
		System.out.println("Introduce the height of the tree:");
		Float height = sc.nextFloat();
		
		System.out.println("Introduce the price of the tree:");
		Float price = sc.nextFloat();
		
		Tree t = findTree(name, height, price);
		
		if (t!= null) {
			System.out.println("Introduce the quantity to remove:");
			int quantity = sc.nextInt();
			
			int currentQuantity = t.getQuantity();
			
			if (currentQuantity > quantity) {
					t.setQuantity(t.getQuantity()-quantity);
			} else {
				System.out.println("There are less trees than the number you want to delete. Deleting all.");
				t.setQuantity(0);
			}
		
		} else {
			System.out.println("There no exists a tree with these properties.");
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
	

	
}
