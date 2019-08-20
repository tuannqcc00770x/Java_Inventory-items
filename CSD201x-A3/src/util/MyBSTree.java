/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author NguyenQuocTuan
 */
public class MyBSTree {

    //a root of tree
    Node<Product> root;
    
    //MyBSTree newTree = new MyBSTree();
  
    
    public MyBSTree() {
        root = null;
        //newTree = null;
    }

    //visit a node of a tree -> output information of visited node
    public void visit(Node<Product> p) {
        System.out.println(p.info);
    }

    //return true if tree is empty otherwise return false
    public boolean isEmpty() {
    	return root == null;
    }

    //inorder a tree
    public void inOrder() {
       printInorder(root); 
    }
    
    void printInorder(Node<Product> node) { 
        if (node == null) return; 
  
        printInorder(node.left); 
  
        visit(node);
  
        printInorder(node.right); 
    } 

    //count number of products
    public int count() {
        if (root==null) return 0;  
          
        Queue<Node<Product>> queue = new LinkedList<Node<Product>>(); 
          
        queue.add(root); 
          
        int count=1;
        while (!queue.isEmpty())  { 
            Node<Product> temp = queue.poll();
  
            if (temp.left != null) { 
                queue.add(temp.left);
                count++;
            } 
  
            if (temp.right != null) { 
                queue.add(temp.right);
                count++;
            } 
        } 
        return count; 
    }
    
    //breadth-first traverse a tree
    public void BFT() {
    	if (root == null) {
            return;
        }
     
        Queue<Node<Product>> nodes = new LinkedList<Node<Product>>();
        nodes.add(root);
     
        while (!nodes.isEmpty()) {
     
            Node<Product> node = nodes.remove();
     
            visit(node);
     
            if (node.left != null) {
                nodes.add(node.left);
            }
     
            if (node.right!= null) {
                nodes.add(node.right);
            }
        }
    }
    
  //insert a new Product to a tree
    
    public void insert(Product x) {
    	if(root==null){
			root = new Node<Product>(x);
			return;
    	}

    	Node<Product> f,p;

    	p=root;f=null;

    	while(p!=null){
    		if(p.info.getCode().equalsIgnoreCase(x.getCode())) {
    			System.out.println(" The code " + x.getCode() + " already exists, no insertion");
    			return;
    		}
    	f=p;
    	if(x.getCode().compareToIgnoreCase(p.info.getCode())<0) p=p.left;
    	else p=p.right;
    	}

    	if(x.getCode().compareToIgnoreCase(f.info.getCode())<0) {
    		f.left=new Node<Product>(x);
    	}

    	else {
    		f.right=new Node<Product>(x);
    	}

    }

    //balance a tree
    //step 1: traverse inorder tree and copy all item on tree to an arraylist
    private void buildArray(List<Node<Product>> list, Node<Product> p) {
        if (p == null) return;
        buildArray(list, p.left);
        list.add(p);
        buildArray(list, p.right);
    }

    //step 2: insert all items of list to a tree
    private void balance(List<Node<Product>> list, int f, int l) {
        if (f > l) return;
        int mid = (f + l)/2;
        Node<Product> p = list.get(mid);
        insert(p.info);
        balance(list, f, mid - 1);
        balance(list, mid + 1, l);
;    }

    public void balance() {
    	List<Node<Product>> list = new ArrayList<>();
    	buildArray(list, root);
    	MyBSTree tree = new MyBSTree();
    	tree.balance(list,  0, list.size()-1);
    	root = tree.root;
    }

    //search a Node of tree by product code
    //return null if given code does not exists
    public Node<Product> search(String code) {
        if (isEmpty()) return null;
        Node<Product> p = root;
        while (p != null) {
        	if (p.info.getCode().equalsIgnoreCase(code)) break;
        	if (p.info.getCode().compareToIgnoreCase(code) < 0) p = p.right;
        	else p = p.left;
        }
        return p;
    }
 
    //delete a node by a given product code
    public void delete(String code) {
    	root = deleteRec(root, search(code)); 
    }

    Node<Product> deleteRec(Node<Product> root, Node<Product> key) { 
    
        if (root == null)  return root; 
  
        if (key.info.getCode().compareToIgnoreCase(root.info.getCode()) < 0) 
            root.left = deleteRec(root.left, key); 
        else if (key.info.getCode().compareToIgnoreCase(root.info.getCode()) > 0) 
            root.right = deleteRec(root.right, key); 
        else{ 
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left;
            root.info = minValue(root.right).info; 
            root.right = deleteRec(root.right, root); 
        } 
  
        return root; 
    } 
    Node<Product> minValue(Node<Product> root) { 
    	Node<Product>  minv = root; 
        while (root.left != null) { 
            minv = root.left; 
            root = root.left; 
        } 
        return minv; 
    }
    
    //search by price >= input
    List<Product> list = new ArrayList<>();
    
    public List<Product> searchByPrice(double price) {
    	list = new ArrayList<>();
    	if (isEmpty()) {
    		System.out.println("No product to search!");
    	}
    	else {
       	 	list = searchByPrice(root, price);
    	}
    	return list;
    }
    public List<Product> searchByPrice(Node<Product> root, double price){
    	
    	if (root.info.getPrice() >= price) {
    		list.add(root.info);
    	}
    	
    	if (root.left != null) {
    		searchByPrice(root.left, price);
    	}
    	if (root.right != null) {
    		searchByPrice(root.right, price);
    	}
    	return list;
    }
    
    //assign height to node
    public void setHeight(){
    	if (isEmpty()) return;
    	root.height = 0;
    	setHeight(root);
    }
    public void setHeight(Node<Product> root){
    	
    	if (root.left != null) {
    		root.left.height = root.height + 1;
    		setHeight(root.left);
    	}
    	if (root.right != null) {
    		root.right.height = root.height + 1;
    		setHeight(root.right);
    	} 
    }
    
    //Traverse Product and height of node
    public void traverseNewTree() {
    	if (isEmpty()) System.out.println("No product to show!");
    	else {
    		System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s%-10s", "Code","Name",
                    "Quantity","Saled","Price","Height"));
    		 traverseNewTree(root);
    	}
     }
     
    public void traverseNewTree(Node<Product> node) { 
         if (node == null) return; 
   
         traverseNewTree(node.left); 
   
         System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s%-10s", node.info.getCode(),node.info.getName(),
        		 node.info.getQuantity(),node.info.getSaled(),node.info.getPrice(), node.height));
   
         traverseNewTree(node.right); 
     } 
}
