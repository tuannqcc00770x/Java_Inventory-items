/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Product;
import util.MyBSTree;

/**
 *
 * @author NguyenQuocTuan
 */
public class MyProduct {
    
    //a list of products
    MyBSTree tree;
    public MyProduct() {
        tree = new MyBSTree();
    }
    
    //1.1 input and insert a new product to tree
    public void insert() {
    	String pCode, name;
    	int quantity, saled;
    	double price;
    	Scanner sc = new Scanner(System.in);
        System.out.println("Enter infomation of product: ");
        pCode = inputProductCode(sc);
        System.out.println("Name: ");
        name = sc.nextLine();
        quantity = inputQuantity(sc);
        saled = inputSaled(sc);
        price = inputPrice(sc);
        Product product = new Product(pCode, name, quantity, saled, price);
        tree.insert(product);
    }
    
    //1.2 in-order traverse
    public void inOrder() {
    	System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s", "Code","Name",
                "Quantity","Saled","Price"));
        tree.inOrder();
    }
    //1.3 BFT a tree
    public void BFT() {
    	System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s", "Code","Name",
                "Quantity","Saled","Price"));
        tree.BFT();
    }
    //1.4 search a product by product code
    public void search(Scanner sc) {
    	 System.out.println("Enter product code to search:");
         String pCode = sc.nextLine();
         if (tree.search(pCode)==null) System.out.println("No product found");
         else {
        	 System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s", "Code","Name",
                     "Quantity","Saled","Price"));
        	 System.out.println(tree.search(pCode).info); 
         }
    }
    //1.5 delete a product by product code
    public void delete(Scanner sc) {
    	System.out.println("Enter product code to delete:");
        String pCode = sc.nextLine();
        if (tree.search(pCode)==null) System.out.println("No product found to delete");
        else {
        	tree.delete(pCode);
        	System.out.println("Product code "+ pCode +" has been deleted!");
        }
    }
    //1.6 simply balancing a tree
    public void balance() {
        tree.balance();
    }
    //1.7 count the number of products in the tree
    public int size() {
    	System.out.println("Number of products: " + tree.count());
        return tree.count();
    }
    
    //Search by price >= input
    public void searchByPrice() {
    	Scanner sc = new Scanner(System.in);
    	double price = inputPrice(sc);
    	List<Product> list = new ArrayList<>();
    	MyBSTree newTree = new MyBSTree();
    	list = tree.searchByPrice(price);
    	System.out.println(list.size());
    	for (Product product : list) {
    		newTree.insert(product);
		}
    	newTree.setHeight();
    	newTree.traverseNewTree();
    }
    
    //validate product code
    String inputProductCode(Scanner sc) {
	  System.out.println("Product Code:");
	  String pCode = sc.nextLine();
	  if (!pCode.matches("[A-Z][0-9]{2}")) {
		  System.out.println("Product code must be at least 1 upper case character and 2 number. Ex: A01, B02,...");
		  return inputProductCode(sc);
	  }
	  if (tree.search(pCode)!=null) {
		  System.out.println("Product code must be unique!");
		  return inputProductCode(sc);
	  }
	  return pCode;
   }
    
    //validate quantity
    int inputQuantity(Scanner sc) {
    	try {
    		System.out.println("Product Quantity:");
    		int quantity = Integer.parseInt(sc.nextLine());
    		if (quantity < 0) {
    			System.out.println("Quantity must be Integer and greater than or equal to zero!");
    			return inputQuantity(sc);
    		}
        	return quantity;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Quantity must be Integer and greater than or equal to zero!");
			return inputQuantity(sc);
		}
    	
    }
    
    //validate sale number
    int inputSaled(Scanner sc) {
    	try {
    		System.out.println("Product Saled:");
    		int lended = Integer.parseInt(sc.nextLine());
    		if (lended < 0) {
    			System.out.println("Saled must be Integer and greater than or equal to zero!");
    			return inputSaled(sc);
    		}
        	return lended;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Saled must be Integer and greater than or equal to zero!");
			return inputSaled(sc);
		}
    	
    } 
    
    //validate price
    double inputPrice(Scanner sc) {
    	try {
    		System.out.println("Product Price:");
    		double price = Double.parseDouble(sc.nextLine());
    		if (price < 0) {
    			System.out.println("Price must be number and greater than or equal to zero!");
    			return inputPrice(sc);
    		}
        	return price;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Price must be number and greater than or equal to zero!");
			return inputPrice(sc);
		}
    	
    }
}
