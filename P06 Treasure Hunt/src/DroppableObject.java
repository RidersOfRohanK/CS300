//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DroppableObject.java
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

/**
 * An interactive object that can be dragged and dropped
 */
public class DroppableObject extends DraggableObject {

  private VisibleObject target; // object over which this object can be dropped
  private Action action; // action that results from dropping this object
  //over target
  // initialize new object

  /**
   *
   * @param name of object
   * @param x coordinate
   * @param y coordinate
   * @param target to be dropped on
   * @param action to be done when reaches target
   */
  public DroppableObject(String name, int x, int y, VisibleObject target, Action action) {
    super(name, x, y);
    this.target = target;
    this.action = action;

  }


  @Override
  // returns action and deactivates objects
  // in response to successful drop
  // When this object is over its target and its target is active:
  // deactivate both this object and the target object, and return action,
  // otherwise return null

/**
 * returns an Action if its over the target, otherwise null
 */
  protected Action drop() {
    super.update();
    if (target.isOver(this) && target.isActive()) {
      target.deactivate();//deactivates both of the objects
      this.deactivate();
      return action;
    }
    return null;
  }
}
