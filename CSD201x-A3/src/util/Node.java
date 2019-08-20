/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author NguyenQuocTuan
 */
//public class Node<T> {
//    
//    public T info;
//    public Node<T> left, right;
//
//    public Node() {
//    }
//
//    public Node(T info, Node<T> left, Node<T> right) {
//        this.info = info;
//        this.left = left;
//        this.right = right;
//    }
//    
//    public Node(T info) {
//        this(info,null,null);
//    }
//}

public class Node<T> {

public T info;
public Node<T> left, right;
public int height;
public Node() {
}

public Node(T info, Node<T> left, Node<T> right, int height) {
  this.info = info;
  this.left = left;
  this.right = right;
  this.height = height;
}

public Node(T info) {
  this(info,null,null, 0);
}
}