import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 *
 * @author NguyenQuocTuan
 */
public class Main {
    
	public static void main(String[] args) {
    	createMainMenu();
    }
    
    public static void createMainMenu() {
    	Scanner sc = new Scanner(System.in);
    	MyProduct myProduct = new MyProduct();
    	boolean keepRunning = true;
    	while (keepRunning) {
    		System.out.println("Product List");
    		System.out.println("1. Insert a new product");
    		System.out.println("2. In-order traverse");
    		System.out.println("3. Breath first traverse");
    		System.out.println("4. Search by a product code");
    		System.out.println("5. Delete by a product code");
    		System.out.println("6. Simple balancing");
    		System.out.println("7. Count number of product");
    		System.out.println("8. Search by price (>= input)");
    		System.out.println("0. Exit");
    		System.out.println("Enter your choice: ");
    		int choice = inputInt(sc, 0, 8);
    		switch (choice) {
			case 1:
				myProduct.insert();
				myProduct.BFT();
				break;
			case 2:
				myProduct.inOrder();
				break;
			case 3:
				myProduct.BFT();
				break;
			case 4:
				myProduct.search(sc);
				break;
			case 5:
				myProduct.delete(sc);
				myProduct.BFT();
				break;
			case 6:
				myProduct.balance();
				myProduct.BFT();
				break;
			case 7:
				myProduct.size();
				break;
			case 8:
				myProduct.searchByPrice();
				break;
			case 0:
				keepRunning = false;
				break;
			default:
				break;
			}
    	}
    }
  //Validate input Integer
    public static int inputInt(Scanner scan, int a, int b) {//validate: input integer (menu option)
		try {
			int min, max;
			if (a<b) {
				min = a;
				max = b;
			} else {
				max = a;
				min = b;
			}
			int result = Integer.parseInt(scan.nextLine());
			if (result < min || result > max) {
				System.out.print("You have to input integer from " + a + " to " + b +". Retry: ");
				return inputInt(scan, a, b);
			} else return result;
		} catch (Exception e) {
			System.out.print("You have to input integer from " + a + " to " + b +". Retry: ");
			return inputInt(scan, a, b);
		}
	} 
}
