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

public class Inbox {
  private MessageStack readMessageBox; // stack which stores read messages
  private MessageStack unreadMessageBox; // stack which stores unread messages


  // This no-argument constructor creates a new empty inbox
  // and initializes its instance fields.
  // Both unreadMessageBox and readMessageBox stacks of this
  // inbox must be initially empty.
  public Inbox() {
    readMessageBox = new MessageStack();
    unreadMessageBox = new MessageStack();

  }


  /**
   * Reads the message at the top of the unreadMessageBox.
   * Once read, the message must be moved from the unreadMessageBox to the readMessageBox.
   * This method returns the string representation of the message at
   * the top of the unreadMessageBox, or "Nothing in Unread"
   * if the unreadMessageBox of this inbox is empty.
   *
   * @return the string representation of the first unread message or nothing in unread
   */
  public String readMessage() {
    if (!unreadMessageBox.isEmpty()) {
      Message toRead = unreadMessageBox.pop();
      readMessageBox.push(toRead);
      return toRead.toString();
    } else {
      return "Nothing in Unread";
    }


  }

  /**
   * Returns the string representation of the message at the top of the readMessageBox.
   * This method returns the string representation of the message at the top
   * readMessageBox and "Nothing in Read" if the readMessageBox is empty.
   *
   * @return the string representation of the message at the top
   * readMessageBox and "Nothing in Read" if the readMessageBox is empty.
   */
  public String peekReadMessage() {
    if (!readMessageBox.isEmpty()) {
      Message toRead = readMessageBox.peek();
      return toRead.toString();
    } else {
      return "Nothing in Read";
    }
  }

  /**
   * Marks all messages in the unread message box as read.
   * The unread message box must be empty after this method returns.
   * Every message marked read must be moved to the read messages box.
   * This method returns the total number of messages marked as read.
   *
   * @return number of previously unread messages
   */
  public int markAllMessagesAsRead() {
    int numUnread = unreadMessageBox.size();
    for (int i = 0; i < numUnread; i++) {
      readMessage();//just does this n number of times
    }
    return numUnread;
  }

  /**
   * Pushes a newMessage into the unread message box
   * newMessage represents a reference to the received message
   * Note that this method can be invoked each time a new message
   * will be received and pushed to the unreadMessageBox.
   *
   * @param newMessage new Message being passed
   */
  public void receiveMessage(Message newMessage) {
    unreadMessageBox.push(newMessage);
  }

  /**
   * Removes permanently all the messages from the readMessageBox
   * This method returns the total number of the removed messages
   *
   * @return
   */
  public int emptyReadMessageBox() {
    int numRead = readMessageBox.size();
    for (int i = 0; i < numRead; i++) {
      readMessageBox.pop();//pops things n times

    }
    return numRead;
  }

  /**
   * Gets the statistics of this inbox
   * Returns a String formatted as follows:
   * "Unread (size1)" + "\n" + "Read (size2)",
   * where size1 and size2 represent the number of unread and read
   * messages respectively.
   *
   * @return the message string specified above
   */
  public String getStatistics() {
    return "Unread " + unreadMessageBox.size() + "\n" + "Read " + readMessageBox.size();
  }


  /**
   * Traverses all the unread messages and return a list of their
   * ID + " " + SUBJECT, as a string. Every string representation of a message is provided in a new line.
   * This method returns a String representation of the contents of
   * This method returns a String representation of the contents of the unread message box.
   * The returned output has the following format: Unread(unreadMessageBox_size)\n + list of the messages in
   * unreadMessageBox (ID + " " + SUBJECT) each in a line.
   *
   * @return the representative string
   */
  public String traverseUnreadMessages() {
    Iterator<Message> m = unreadMessageBox.iterator();
    String response = "Unread (" + unreadMessageBox.size() + ")\n";
    for (int i = 0; i < unreadMessageBox.size(); i++) {
      Message mes = m.next();
      response += mes.getID() + " " + mes.getSUBJECT() + "\n";//prints the requisite thing n times
    }
    return response;
  }


  /**
   * // Traverses all the read messages and return a list of their string
   * // representations, ID + " " + SUBJECT, each per new line, as a string
   * // This method returns a String representation of the contents of
   * // the read message box
   * // The returned output has the following format:
   * // Read(readMessageBox_size)\n + list of the messages in
   * // readMessageBox (ID + " " + SUBJECT) each in a line.
   *
   * @return the representative String
   */
  public String traverseReadMessages() {

    Iterator<Message> m = readMessageBox.iterator();
    String response = "Read (" + readMessageBox.size() + ")\n";
    for (int i = 0; i < readMessageBox.size(); i++) {
      Message mes = m.next();
      response += mes.getID() + " " + mes.getSUBJECT() + "\n";//prints the right thing n times
    }
    return response;//returns the string

  }
}
