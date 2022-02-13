//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LIFO Inbox Reader
// Course:   CS 300 Fall 2020
//
// Author:   (Rohan Kale)
// Email:    (rkale2@wisc.edu)
// Lecturer: (Hobbes LeGault)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (None)
// Online Sources:  (None)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of the implementation of the
 * MessageStack, Inbox, and MessageStackIterator classes defined in the CS300 Fall 2020 - P08 LIFO
 * Inbox Reader programming assignment.
 */
public class InboxReaderTester {

  /**
   * Calls your tester methods
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(testStackPop());
    System.out.println(testStackConstructorIsEmptyPushPeek());
    System.out.println(testInboxReadMessage());
    System.out.println(testInboxReceiveMessage());
    System.out.println(testInboxMarkAllMessagesAsRead());
    System.out.println(testMessageStackIterator());
  }

  // add the runInboxReaderTestSuite() method and your additional tester methods

  /**
   * Checks for the correctness of the constructor of the MessageStack, MessageStack.push(),
   * MessageStack.peek(), MessageStack.isEmpty(), and MessageStack.size() methods. This method must
   * consider at least the test scenarios provided in the detailed description of these javadocs.
   * (1) Create a new MessageStack object. The new created stack must be empty and its size must be
   * zero. (2) You can consider calling peek method on the empty stack. An EmptyStackException is
   * expected to be thrown by the peek method call. (3) Then, push a Message onto the stack and then
   * use peek to verify that the correct item is at the top of the stack. Make sure also to check
   * that isEmpty() must return false and the size of the stack is one. The peek() method call
   * should not make any change to the contents of the stack. (4) You can further consider pushing
   * other elements onto the stack. Then, each call of peek() method should return the correct
   * Message object that should be at the top of the stack. After pushing a new message to the stack
   * double check that the size of the stack was incremented by one.
   *
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackConstructorIsEmptyPushPeek() {
    boolean works = false;

    MessageStack m = new MessageStack();

    if (m.size() != 0) return false;
    try {
      m.pop();
    } catch (EmptyStackException e) {
      works = true;
    }
    if (!works) return works;

    if (!m.isEmpty()) return false;

    try {
      m.peek();
    } catch (EmptyStackException e) {
      works = true;
    }
    if (!works) return works;


    m.push(new Message("wombo", "combo"));


    if (!m.peek().getSUBJECT().equals("wombo") || !m.peek().getTEXT().equals("combo")) return false;
    if (m.size() != 1) return false;
    if (m.isEmpty()) return false;

    m.push(new Message("wiggle", "wobble"));

    if (!m.peek().getTEXT().equals("wobble") || !m.peek().getSUBJECT().equals("wiggle")) return false;
    if (m.size() != 2) return false;
    if (m.isEmpty()) return false;


    m.push(new Message("i like", "trains"));

    if (!m.peek().getTEXT().equals("trains") || !m.peek().getSUBJECT().equals("i like")) return false;
    if (m.size() != 3) return false;
    if (m.isEmpty()) return false;

    works = false;
    try {
      m.push(null);
    } catch (IllegalArgumentException e) {
      works = true;
    }

    return works;
  }


  /**
   * Checks for the correctness of MessageStack.pop(). It calls MessageStack.push() and
   * MessageStack.peek() methods. This method must consider at least the test scenarios provided in
   * the detailed description of these javadocs. (1) Try to create a new empty MessageStack. Then,
   * try calling pop method on the empty stack. An EmptyStackException is expected to be thrown as a
   * result. (2) Try to push a message to the stack. Then, try to call the pop method on the stack
   * which contains only one element. Make sure that the pop() operation returned the expected
   * message, and that the stack is empty and its size is zero. (3) Then, try to push at least three
   * messages, then call pop method on the stack which contains 3 elements, the element at the top
   * of the stack must be returned and removed from the stack and its size must be decremented by
   * one. You can further keep popping the elements of the stack one by one until it becomes empty
   * and check each time that the pop operation performs appropriately.This test method must return
   * false if it detects at least one defect.
   *
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackPop() {
    boolean works = false;
    MessageStack m = new MessageStack();
    try {
      Message mes = m.pop();
    } catch (EmptyStackException e) {
      works = true;
    }

    if (!works) return works;

    m.push(new Message("chicken little", "The sky is falling"));
    Message mem;
    try {
      mem = m.pop();
    } catch (EmptyStackException e) {
      return false;
    }
    if (mem == null || !mem.getSUBJECT().equals("chicken little") || !mem.getTEXT().equals("The sky is falling"))
      return false;

    if (m.size() != 0) return false;

    works = false;

    try {
      m.pop();
    } catch (EmptyStackException e) {
      works = true;
    }


    m.push(new Message("you got it", "set to M for mini"));
    m.push(new Message("When you should've", "set it to W for Wumbo"));
    m.push(new Message("wombo", "combo"));

    try {
      mem = m.pop();
    } catch (EmptyStackException e) {
      return false;
    }
    if (mem == null || !mem.getSUBJECT().equals("wombo") || !mem.getTEXT().equals("combo")) return false;
    if (m.size() != 2) return false;

    try {
      mem = m.pop();
    } catch (EmptyStackException e) {
      return false;
    }
    if (mem == null || !mem.getSUBJECT().equals("When you should've") || !mem.getTEXT().equals("set it to W for Wumbo"))
      return false;
    if (m.size() != 1) return false;

    try {
      mem = m.pop();
    } catch (EmptyStackException e) {
      return false;
    }
    if (mem == null || !mem.getSUBJECT().equals("you got it") || !mem.getTEXT().equals("set to M for mini"))
      return false;
    if (m.size() != 0) return false;


    return works;
  }

  /**
   * Checks for the correctness of the Inbox.ReadMessage() method.
   * <p>
   * Define your own test scenarios to check the correctness of Inbox.ReadMessage()
   * Your test method must return false if it detects at least one defect
   * Vary your test scenarios. Make sure to consider at least two test scenarios:
   * (1) when Inbox.unreadMessageBox is empty
   * (2) when Inbox.unreadMessageBox is not empty. You have to make sure the read message
   * has been popped off the Inbox.unreadMessageBox and pushed into the Inbox.readMessageBox
   * You can rely on Inbox.peekReadMessage() and Inbox.getStatistics() to check the method
   * behavior was as expected.
   *
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReadMessage() {
    boolean works = false;
    Inbox inbox = new Inbox();
    String response = inbox.readMessage();
    if (!response.equals("Nothing in Unread")) return false;

    works = true;


    inbox.receiveMessage(new Message("you", "wumbo"));
    inbox.receiveMessage(new Message("I", "Wumbo"));
    inbox.receiveMessage(new Message("Wumbology?", "The study of Wumbo?"));
    inbox.receiveMessage(new Message("wombo", "combo"));


    inbox.readMessage();
    if (!inbox.peekReadMessage().equals("[" + 11 + "] " + "wombo" + ": " + "combo")) {
      return false;
    }

    if (!inbox.getStatistics().equals("Unread " + 3 + "\n" + "Read " + 1))
      return false;

    inbox.readMessage();
    if (!inbox.peekReadMessage().equals("[" + 10 + "] " + "Wumbology?" + ": " + "The study of Wumbo?")) {
      return false;
    }

    if (!inbox.getStatistics().equals("Unread " + 2 + "\n" + "Read " + 2)) {
      return false;
    }
    inbox.readMessage();
    inbox.readMessage();
    if (!inbox.readMessage().equals("Nothing in Unread")) return false;
    if (!inbox.getStatistics().equals("Unread " + 0 + "\n" + "Read " + 4)) {
      return false;
    }

    works = false;
    try {
      inbox.receiveMessage(null);
      //checks if the input is passed as null the program will throw correct exception
    } catch (IllegalArgumentException e) {
      works = true;
    }

    return works;
  }


  /**
   * Checks for the correctness of the Inbox.ReceiveMessage() method
   * Define your own test scenarios to check the correctness of Inbox.receiveMessage()
   * Your test method must return false if it detects at least one defect
   *
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReceiveMessage() {
    boolean works = false;
    Inbox inbox = new Inbox();
    inbox.receiveMessage(new Message("you", "wumbo"));
    inbox.receiveMessage(new Message("I", "Wumbo"));

    if (!inbox.getStatistics().equals("Unread " + 2 + "\n" + "Read " + 0))
      return false;

    inbox.receiveMessage(new Message("Wombology?", "The study of Wumbo?"));
    inbox.receiveMessage(new Message("wombo", "combo"));

    if (!inbox.getStatistics().equals("Unread " + 4 + "\n" + "Read " + 0))
      return false;


    inbox.readMessage();

    if (!inbox.peekReadMessage().equals("[" + 15 + "] " + "wombo" + ": " + "combo")) {
      return false;
    }

    if (!inbox.getStatistics().equals("Unread " + 3 + "\n" + "Read " + 1))
      return false;

    works = false;
    try {
      inbox.receiveMessage(null);
      //checks if the input is passed as null the program will throw correct exception
    } catch (IllegalArgumentException e) {
      works = true;
    }

    if (!inbox.getStatistics().equals("Unread " + 3 + "\n" + "Read " + 1))
      return false;

    return works;
  }

  /**
   * Checks for the correctness of the Inbox.markAllMessagesAsRead() method
   * Define your own test scenarios to check the correctness of Inbox.markAllMessagesAsRead()
   * Your test method must return false if it detects at least one defect
   *
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxMarkAllMessagesAsRead() {
    boolean works = true;
    Inbox inbox = new Inbox();

    inbox.receiveMessage(new Message("you", "wumbo"));
    inbox.receiveMessage(new Message("I", "Wumbo"));
    inbox.receiveMessage(new Message("Wumbology?", "The study of Wumbo?"));
    inbox.receiveMessage(new Message("wombo", "combo"));

    if (inbox.markAllMessagesAsRead() != 4) return false;
    if (!inbox.getStatistics().equals("Unread " + 0 + "\n" + "Read " + 4))
      return false;

    if (inbox.markAllMessagesAsRead() != 0) return false;


    return works;
  }

  /**
   * Checks for the correctness of MessageStackIterator.hasNext() and MessageStackIterator.next()
   * methods. You can rely on the constructor of the LinkedNode class which takes two input
   * parameters (setting both data and next instance fields) to create a chain of linked nodes (at
   * least 3 linked nodes) which carry messages as data fields. Then, create a new
   * MessageStackIterator() and pass it the head of the chain of linked nodes that you created. We
   * recommend that you consider at least the following test scenarios in this tester method. (1)
   * Try to call next() on the iterator. The first call of next() must return the message at the
   * head of your chain of linked nodes. (2) Try to call hasNext() on your iterator, it must return
   * true. (3) The second call of next() must return the message which has been initially at index 1
   * of your chain of linked nodes. (4) The third call of next() on your iterator must return the
   * message initially at index 2 of your chain of linked nodes. (4) If you defined a chain of 3
   * linked nodes in this scenario, hasNext() should return false, and the fourth call of next() on
   * the iterator must throw a NoSuchElementException.
   *
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testMessageStackIterator() {

    LinkedNode<Message> m4 = new LinkedNode<>(new Message("i like", "trains"), null);
    LinkedNode<Message> m3 = new LinkedNode<>(new Message("chicken", "little"), m4);
    LinkedNode<Message> m2 = new LinkedNode<>(new Message("wombo", "combo"), m3);
    LinkedNode<Message> m1 = new LinkedNode<>(new Message("omg", "who cares"), m2);


    MessageStackIterator iterator = new MessageStackIterator(m1);


    if (!iterator.hasNext()) return false;

    try {
      Message m = iterator.next();
      if (m == null || !m.getSUBJECT().equals("omg") || !m.getTEXT().equals("who cares")) return false;
    } catch (NoSuchElementException e) {
      return false;
    }

    if (!iterator.hasNext()) return false;

    try {
      Message m = iterator.next();
      if (m == null || !m.getSUBJECT().equals("wombo") || !m.getTEXT().equals("combo")) return false;
    } catch (NoSuchElementException e) {
      return false;
    }

    if (!iterator.hasNext()) return false;


    try {
      Message m = iterator.next();
      if (m == null || !m.getSUBJECT().equals("chicken") || !m.getTEXT().equals("little")) return false;
    } catch (NoSuchElementException e) {
      return false;
    }

    try {
      Message m = iterator.next();
      if (m == null || !m.getSUBJECT().equals("i like") || !m.getTEXT().equals("trains")) return false;
    } catch (NoSuchElementException e) {
      return false;
    }
    if(iterator.hasNext())return false;

    try {
      Message m = iterator.next();

    } catch (NoSuchElementException e) {
      return true;
    }




    return false;

  }


}