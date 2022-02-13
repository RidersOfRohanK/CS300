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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MessageStackIterator implements Iterator<Message> {
  private LinkedNode<Message> top;


  public MessageStackIterator(LinkedNode<Message> top) {
    this.top = top;

  }

  @Override
  /**
   * @return true if there is another item up next
   */
  public boolean hasNext() {

    if (top == null)
      return false;
    return true;
  }

  @Override
  /**
   * returns the next message if there is one
   */
  public Message next() {
    if (hasNext()) {
      Message m = top.getData();
      top = top.getNext();
      return m;//iterates to the next one then returns the value of the previous
    } else {
      throw new NoSuchElementException("There is no next item");
    }
  }

}
