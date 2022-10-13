package application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Scanner;

import application.Decoration.MaterialType;

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

	public void addTree() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name:");
		
		String name = sc.nextLine();
		
		System.out.println("Introduce the price:");
		
		float price = sc.nextFloat();
		
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
	
	public void addFlower() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name:");
		
		String name = sc.nextLine();
		
		System.out.println("Introduce the price:");
		
		float price = sc.nextFloat();
		
		sc.nextLine();
		
		System.out.println("Introduce the color:");
		sc.nextLine(); //Clear buffer
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
	
	public void addDecoration() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name:");
		
		String name = sc.nextLine();
		
		System.out.println("Introduce the price:");
		
		float price = sc.nextFloat();	
		
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
	
	public void removeTree() {
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
	
	public void removeFlower() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce the name of the flower:");
		String name = sc.nextLine();
		
		System.out.println("Introduce the colour of the flower:");
		String colour = sc.nextLine();
		
		System.out.println("Introduce the price of the flower:");
		Float price = sc.nextFloat();
		
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
	
	public void removeDecoration() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce the name of the decoration:");
		String name = sc.nextLine();
		
		System.out.println("Introduce the colour of the decoration:");
		String colour = sc.nextLine();
		
		System.out.println("Introduce the price of the decoration:");
		Float price = sc.nextFloat();
		
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
		this.tickets.add(new Ticket());
		
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
			if (p.getType().equalsIgnoreCase("Tree")) {
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
			if (p.getType().equalsIgnoreCase("Flower")) {
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
			if (p.getType().equalsIgnoreCase("Decoration")) {
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
	
	
	
	
	
	
	

	
}
