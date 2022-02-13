//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ClickableObject.java
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
 * A Visible object that can be interacted with
 */
public class ClickableObject extends VisibleObject {
  private Action action; // action returned from update when this object is clicked
  private boolean mouseWasPressed; // tracks whether the mouse was pressed

  // during the last update()
// initializes this new object

  /**
   *
   * @param name of obj
   * @param x coordinate
   * @param y coordinate
   * @param action being done
   */
  public ClickableObject(String name, int x, int y, Action action) {
    super(name, x, y);
    this.action = action;
  }

  @Override
  // calls VisibleObject update, then returns
  // action only when mouse is first clicked on this object
  /**
   * Action is done if the object is being clicked
   */
  public Action update() {
    super.update();
    if (getProcessing().mousePressed && super.isOver(super.getProcessing().mouseX, super.getProcessing().mouseY)) {
      return action;
    }
    return null;
  }

}
