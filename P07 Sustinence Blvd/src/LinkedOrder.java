//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Restaurant Order Tester
// Course:   CS 300 Fall 2020
//
// Author:   Rohan Kale
// Email:    rkale2@wisc.edu
// Lecturer: (Hobbes LeGault)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
public class LinkedOrder {
  private final Order ORDER;
  private LinkedOrder previous;
  private LinkedOrder next;

  /**
   * init the Linked order with an order
   * @param order to be init
   */
  public LinkedOrder(Order order){
    if(order.compareTo(new Order(null,0))==-1){
      throw new IllegalArgumentException("timestamp is negative");
    }
    else {
      this.ORDER = order;
      previous = null;
      next = null;
    }

  }

  /**
   * to init
   * @param order to init
   * @param previous linkedOrder
   * @param next linkedOrder
   */
  public LinkedOrder (Order order, LinkedOrder previous, LinkedOrder next) {
    if(order.compareTo(new Order(null,0))==-1){
      throw new IllegalArgumentException("timestamp is negative");
    }
    else {
      this.ORDER = order;
      this.next = next;
      this.previous = previous;
    }
  }

  /**
   * accessor
   * @return order
   */
  public Order getOrder(){
    return ORDER;
  }

  /**
   * accessor
   * @return previous LinkedOrder
   */
  public LinkedOrder getPrevious(){
    return previous;
  }

  /**
   * accessor
   * @return next Linked Order
   */
  public LinkedOrder getNext(){
    return next;
  }

  /**
   * mutator
   * @param previous Linked Order
   */
  public void setPrevious(LinkedOrder previous){
    this.previous  = previous;
  }

  /**
   * mutator
   * @param next Linked Order
   */
  public void setNext(LinkedOrder next){
    this.next = next;
  }
}
