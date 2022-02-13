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
import java.util.Iterator;

public class MessageStack implements StackADT<Message>, Iterable<Message> {
  private LinkedNode<Message> top; //top of the stack
  private int size; // current size of stack

  @Override
  /**
   * puts a new non null element at the top of the stack
   */
  public void push(Message element) {
    if(element == null){
      throw new IllegalArgumentException("input is null!");
    }
    else {
      LinkedNode<Message> prevHead = top;
      top = new LinkedNode(element, prevHead);
      size++;
    }


  }

  @Override
  /**
   * @returns the Message on top of the stack if there is one and removes it from the stack
   */
  public Message pop() {
    if(size!=0) {
      LinkedNode<Message> popped = top;
      top = popped.getNext();
      size--;
      return popped.getData();
    }
    else{
      throw new EmptyStackException();
    }
  }

  @Override
  /**
   * @return the Message on the top of the stack
   */
  public Message peek() {
    if(size!=0){
      return top.getData();
    }
    throw new EmptyStackException();//only triggers if the size is 0/stack is empty
  }

  @Override
  /**
   * @returns true if empty false otherwise
   */
  public boolean isEmpty() {
    return size==0;
  }

  @Override
  /**
   * @return size of stack
   */
  public int size() {
    return size;
  }

  @Override
  /**
   * @returns the top of the stack of the thing
   */
  public Iterator<Message> iterator() {
    return new MessageStackIterator(top);
  }
}
