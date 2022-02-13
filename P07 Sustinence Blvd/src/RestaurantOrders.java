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
public class RestaurantOrders implements SortedListADT<Order> {
  private LinkedOrder head;
  private LinkedOrder tail;
  private int size;
  private final int CAPACITY;

  /**
   * Constructor
   * init capacity at 20
   * init other vars
   */
  public RestaurantOrders() {
    CAPACITY = 20;
    head = null;
    tail = null;
  }

  /**
   * Constructor
   * inits other vals, checks if Capacity is valid otherwise throws error
   *
   * @param capacity sets the capacity
   */
  public RestaurantOrders(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity is 0 or negative");
    } else {
      this.CAPACITY = capacity;
    }

    head = null;
    tail = null;

  }

  /**
   * accessor
   *
   * @return CAPACITY
   */
  public int capacity() {
    return CAPACITY;
  }

  /**
   * For testing
   *
   * @returns string in specified order The list contains (size) order(s): [ (orders) ]
   */
  public String readForward() {

    LinkedOrder current = head;
    String finalResult = "The list contains " + size + " order(s): [ ";
    for (int i = 0; i < size; i++) {

      Order currentOrder = current.getOrder();
      finalResult += currentOrder.getDishes() + " ";

      current = current.getNext();
    }

    return finalResult + "]";
  }

  /**
   * For testing
   *
   * @returns string in specified order The list contains (size) order(s): [ (orders) ] but in reverse order
   */
  public String readBackward() {
    LinkedOrder current = tail;
    String finalResult = "The list contains " + size + " order(s): [ ";
    for (int i = size - 1; i >= 0; i--) {
      Order currentOrder = current.getOrder();
      finalResult += currentOrder.getDishes() + " ";

      current = current.getPrevious();
    }

    return finalResult + "]";
  }


  public void clear() {

    head = null;
    tail = null;
    size = 0;
  }


  /**
   * @param index of the element to return
   * @return the Order at specified index
   */
  public Order get(int index) {
    if (index <= size && index >= 0) {
      if (size > 0) {
        LinkedOrder current = head;
        for (int i = 0; i < index; i++) {

          current = current.getNext();
        }
        return current.getOrder();
      }
    }
    return null;
  }

  /**
   * @param toBeFound Order to be found
   * @return the index of the order
   */
  public int indexOf(Order toBeFound) {
    LinkedOrder current = head;
    for (int i = 0; i < size; i++) {
      Order currentOrder = current.getOrder();
      if (currentOrder.equals(toBeFound)) {
        return i;
      }
      current = current.getNext();

    }
    return -1;
  }

  /**
   * Checks if there are no orders
   *
   * @return true if Empty
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;

  }

  /**
   * Adds new order to list if there is space
   *
   * @param newOrder to add
   */
  public void placeOrder(Order newOrder) {
    if (newOrder == null) {
      throw new IllegalArgumentException("The new order is null");
    }
    if (size + 1 <= CAPACITY) {
      if (head == null) {//checks if head is null
        LinkedOrder first = new LinkedOrder(newOrder, null, null);
        head = first;//head and tail are set to the same thing because there is only 1 thing in the lsit
        tail = first;
        size++;
      } else if (head.getNext() == null) {//checks to see if we are making the second element not null
        if (head.getOrder().compareTo(newOrder) == -1) {
          LinkedOrder second = new LinkedOrder(newOrder, head, null);
          head.setNext(second);
          tail = second;
          size++;
        } else if (head.getOrder().compareTo(newOrder) == 1) {
          //checks where the new Order is in comparison to orders already in there
          LinkedOrder newHead = new LinkedOrder(newOrder, null, head);
          head.setPrevious(newHead);
          head = newHead;
          size++;
        } else {
          throw new IllegalArgumentException("The order has a duplicate timestamp");
        }
      } else {
        boolean hasBeenSet = false;
        LinkedOrder current = head;
        while (current.getNext() != null && !hasBeenSet) {
          if (current.getOrder().compareTo(newOrder) == 1) {
            if (current.equals(head)) {
              LinkedOrder newHead = new LinkedOrder(newOrder, null, current);
              head.setPrevious(newHead);
              head = newHead;
              size++;
              hasBeenSet = true;

            }
            LinkedOrder newLinkedOrder = new LinkedOrder(newOrder, current.getPrevious(), current);
            current.getPrevious().setNext(newLinkedOrder);
            current.setPrevious(newLinkedOrder);
            hasBeenSet = true;
            size++;

          } else if (current.getOrder().compareTo(newOrder) == 0) {
            throw new IllegalArgumentException("This has a duplicate Timestamp!");
          }
          current = current.getNext();
        }
        if (!hasBeenSet) {//if it hasnt been set yet check the tail
          if (current.getOrder().compareTo(newOrder) == 1) {
            LinkedOrder newLinkedOrder = new LinkedOrder(newOrder, current.getPrevious(), current);
            current.getPrevious().setNext(newLinkedOrder);
            current.setNext(null);
            current.setPrevious(newLinkedOrder);
            tail = current;
            size++;
          } else {
            LinkedOrder newLinkedOrder = new LinkedOrder(newOrder, tail, null);
            tail.setNext(newLinkedOrder);
            tail = newLinkedOrder;
            size++;
          }
        }
      }
    }

  }

  /**
   * Removes Order from the list
   * @param index of the element to be removed
   * @return the order that was removed
   */
  public Order removeOrder(int index) {
    if (head != null) {//checks that there is a head
      if (index >= 0 && index <= CAPACITY) {
        LinkedOrder current = head;
        if (index == 0) {
          if (head.getNext() != null) {
            head = current.getNext();

            current.getNext().setPrevious(null);


          } else {
            head = null;
            tail = null;
          }
          size--;
          return current.getOrder();
        } else if (index == size - 1) {//checks if its the tail
          current = tail;
          tail = current.getPrevious();
          current.getPrevious().setNext(null);
          size--;
          return current.getOrder();

        } else {//otherwise iterate until you find the index
          for (int i = 0; i < index; i++) {
            current = current.getNext();
          }
          current.getNext().setPrevious(current.getPrevious());
          current.getPrevious().setNext(current.getNext());
          size--;
          return current.getOrder();
        }
      } else {
        throw new IndexOutOfBoundsException("Invalid index to be removed");
      }
    }
    return null;


  }

  public int size() {
    return size;
  }


}
