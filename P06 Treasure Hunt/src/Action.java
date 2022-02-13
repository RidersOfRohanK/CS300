//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Action.java
// Course:   CS 300 Fall 2020
//
// Author:   Rohan Kale
// Email:    rkale2@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * Action that happens
 */
public class Action {
  private String message; // message printed by this action (or null to do nothing)
  private InteractiveObject object; //object that is triggered

  /**
   *
   * @param object being triggered
   */
  public Action(InteractiveObject object) {
    this.object = object;
  }

  /**
   *
   * @param message printed when triggered
   */
  public Action(String message) {
    this.message = message;
  }

  /**
   *
   * @param message printed when triggered
   * @param object appears or dissapears when triggered
   */
  public Action(String message, InteractiveObject object) {
    this.message = message;
    this.object = object;
  }



  /**
   * // when message is not null, message is printed to System.out
   * @param objects arraylist of all objects
   */
  public void act(ArrayList<InteractiveObject> objects) {
    if (object != null) {
      object.activate();//activates said object
      objects.add(object);
      object = null;
    }
    if (message != null) {
      System.out.println(message);
    }
  }

}
