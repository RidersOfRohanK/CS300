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

import jdk.swing.interop.SwingInterOpUtils;

/**
 * This class implements unit test methods to check the correctness of LinkedOrders and RestaurantOrders
 * classes defined in the CS300 Fall 2020 - P07 Restaurant Orders programming assignment.
 */
public class RestaurantOrdersTester {

  /**
   * This method should test and make use of at least the LinkedOrders constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedOrders class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedOrders() {
    boolean works = false;
    LinkedOrder one = new LinkedOrder(new Order("wiggle", 2));

    LinkedOrder two = new LinkedOrder(new Order("baba booey", 0));
    try {
      LinkedOrder three = new LinkedOrder(new Order("Chunky Chicken", -3));
    } catch (IllegalArgumentException e) {
      works = true;
    }
    if (works != true) return works;

    LinkedOrder twoPointFive = new LinkedOrder(new Order("uh oh", 1), two, one);

    try {
      LinkedOrder four = new LinkedOrder(new Order("THis should crash", -5), one, two);
    } catch (IllegalArgumentException e) {
      works = true;
    }
    if (works != true) return works;

    LinkedOrder five = new LinkedOrder(new Order("the one before one", 7), one, two);
    if (five.getPrevious().getOrder().compareTo(one.getOrder()) != 0) {
      return false;
    }
    if (!five.getNext().getOrder().equals(two.getOrder())) {
      return false;
    }

    five.setPrevious(twoPointFive);
    if (!five.getPrevious().getOrder().equals(twoPointFive.getOrder())) {
      return false;
    }

    return works;
  }

  /**
   * This method checks for the correctness of both RestaurantOrders constructors and the instance
   * method isEmpty() when called on an empty restaurant orders object.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorIsEmpty() {
    boolean works = true;
    RestaurantOrders r = new RestaurantOrders();
    if (r.capacity() != 20) {
      return false;
    }

    RestaurantOrders r2 = new RestaurantOrders(5000);
    if (r2.capacity() != 5000) {
      return false;
    }

    works = r.isEmpty();
    r2.placeOrder(new Order("order", 1));
    if (r2.isEmpty()) {
      return false;
    }

    return works;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders(int) constructor when passed a
   * negative int value for the capacity.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersConstructorBadInput() {
    boolean works = false;
    try {
      RestaurantOrders failure = new RestaurantOrders(-50);
    } catch (IllegalArgumentException e) {
      works = true;
    }
    if (!works) return works;
    works = false;
    try {
      RestaurantOrders failure = new RestaurantOrders(0);
    } catch (IllegalArgumentException e) {
      works = true;
    }


    return works;
  }


  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder()() method when it is passed bad
   * inputs. This method must consider at least the test scenarios provided in the detailed
   * description of these javadocs. (1) Try adding a null to the list; (2) Try adding an order which
   * carries a negative timestamp. (3) Try adding an order with an existing timestamp to the list.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAddBadInput() {
    boolean works = false;
    RestaurantOrders r = new RestaurantOrders();
    try {
      r.placeOrder(null);
    } catch (IllegalArgumentException e) {
      works = true;
    }


    if (!works) return works;
    works = false;

    try {
      r.placeOrder(new Order("failure", -5));
    } catch (IllegalArgumentException e) {
      works = true;
    }

    if (!works) return works;
    works = false;

    r.placeOrder(new Order("poopy", 1));
    try {
      r.placeOrder(new Order("failure", 1));
    } catch (IllegalArgumentException e) {
      works = true;
    }
    return works;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.placeOrder()() considering at least the test
   * scenarios provided in the detailed description of these javadocs. (1) Try adding an order to an
   * empty list; (2) Try adding an order which is expected to be added at the head of a non-empty
   * restaurant list; (3) Try adding an order which is expected to be added at the end of a non-empty
   * restaurant list; (4) Try adding an order which is expected to be added at the middle of a non-empty
   * restaurant list. For each of those scenarios, make sure that the size of the list is
   * appropriately updated after a call without errors of the add() method, and that the contents of
   * the list is as expected whatever if list is read in the forward or backward directions. You can
   * also check the correctness of RestaurantOrders.get(int), RestaurantOrders.indexOf(Order), and
   * RestaurantOrders.size() in this test method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersAdd() {
    boolean works;
    RestaurantOrders r = new RestaurantOrders();
    r.placeOrder(new Order("wiggle", 5));
    works = true;

    if (r.size() != 1) return false;


    r.placeOrder(new Order("new wiggle", 1));
    ;
    if (!r.readForward().equals("The list contains 2 order(s): [ new wiggle wiggle ]")) {
      return false;
    }

    if (r.size() != 2) return false;

    r.placeOrder(new Order("final wiggle", 10));
    if (!r.readForward().equals("The list contains 3 order(s): [ new wiggle wiggle final wiggle ]")) {
      return false;
    }
    if (r.size() != 3) return false;


    r.placeOrder(new Order("bababooey", 6));

    if (!r.readBackward().equals("The list contains 4 order(s): [ final wiggle bababooey wiggle new wiggle ]")) {
      return false;
    }


    if (r.size() != 4) return false;


    if (r.get(5) != null) {
      return false;
    }


    if (!r.get(1).getDishes().equals("wiggle")) return false;


    if (r.indexOf(new Order("bababooey", 6)) != 2) return false;
    if (r.indexOf(new Order("fail", 9)) != -1) return false;


    return works;
  }

  /**
   * This method checks for the correctness of the RestaurantOrders.removeOrder() considering at least the
   * test scenarios provided in the detailed description of these javadocs. (1) Try removing an order
   * from an empty list or pass a negative index to RestaurantOrders.removeOrder() method; (2) Try removing an
   * order (at position index 0) from a list which contains only one order; (3) Try to remove an order
   * (position index 0) from a list which contains at least 2 orders; (4) Try to remove an order from
   * the middle of a non-empty list containing at least 3 orders; (5) Try to remove the order at the
   * end of a list containing at least two orders. For each of those scenarios, make sure that the
   * size of the list is appropriately updated after a call without errors of the add() method,
   * and that the contents of the list is as expected whatever if list is read in the forward or
   * backward directions.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRestaurantOrdersRemove() {
    boolean works = false;
    RestaurantOrders r = new RestaurantOrders();

    r.removeOrder(0);
    works = true;


    try {
      r.removeOrder(-1);
    } catch (IndexOutOfBoundsException e) {
      works = true;
    }

    r.placeOrder(new Order("bert", 1));
    r.removeOrder(0);
    if (r.size() != 0) {
      return false;
    }
    r.placeOrder(new Order("bert", 1));
    r.placeOrder(new Order("ernie", 2));
    r.placeOrder(new Order("big bird", 3));

    r.removeOrder(0);
    if (r.size() != 2) {
      return false;
    }

    if (!r.readBackward().equals("The list contains 2 order(s): [ big bird ernie ]")) {
      return false;
    }

    r.placeOrder(new Order("bert", 0));
    r.removeOrder(1);


    if (r.size() != 2) {
      return false;
    }

    if (!r.readBackward().equals("The list contains 2 order(s): [ big bird bert ]")) {
      return false;
    }

    r.placeOrder(new Order("ernie", 2));
    r.placeOrder(new Order("Elmo", 4));
    r.removeOrder(3);
    if (r.size() != 3) return false;


    if (!r.readForward().equals(("The list contains 3 order(s): [ bert ernie big bird ]"))) return false;


    return works;
  }


  /**
   * This method calls all the test methods defined and implemented in your RestaurantOrdersTester
   * class.
   *
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    if (!testLinkedOrders()) return false;
    if (!testRestaurantOrdersConstructorBadInput()) return false;
    if (!testRestaurantOrdersConstructorIsEmpty()) return false;
    if (!testRestaurantOrdersAddBadInput()) return false;
    if (!testRestaurantOrdersAdd()) return false;
    if (!testRestaurantOrdersRemove()) return false;
    return true;
  }

  /**
   * Driver method defined in this RestaurantOrdersTester class
   *
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.println(testLinkedOrders());
    System.out.println(testRestaurantOrdersConstructorIsEmpty());
    System.out.println(testRestaurantOrdersConstructorBadInput());
    System.out.println(testRestaurantOrdersAddBadInput());
    System.out.println(testRestaurantOrdersAdd());
    System.out.println(testRestaurantOrdersRemove());
  }
}