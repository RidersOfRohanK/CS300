//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DraggableObject.java
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
 * general object that is visible and draggable
 */
public class DraggableObject extends VisibleObject {
  private boolean mouseWasPressed; // similar to use in ClickableObject
  private boolean isDragging; // true when this object is being dragged by the user
  private int oldMouseX; // horizontal position of mouse during last update
  private int oldMouseY; // vertical position of mouse during last update


  // initializes new draggable object

  /**
   *
   * @param name of object
   * @param x coordinate
   * @param y coordinate
   */
  public DraggableObject(String name, int x, int y) {
    super(name, x, y);
    oldMouseX = 0;
    oldMouseY = 0;
    isDragging = false;
  }

  @Override
  // calls VisibleObject update() first, then moves according to mouse drag
  // each time isDragging changes from true to false, the drop() method below will be
  // called once and any action objects returned from that method should then be returned from update()
  public Action update() {
    super.update();

    if (getProcessing().mousePressed && isOver(getProcessing().mouseX, getProcessing().mouseY) && !mouseWasPressed) {
      isDragging = true;
      mouseWasPressed = true;
      oldMouseX = getProcessing().mouseX;
      oldMouseY = getProcessing().mouseY;
    } else if (getProcessing().mousePressed && isDragging) {
      int dx = oldMouseX - getProcessing().mouseX;
      int dy = oldMouseY - getProcessing().mouseY;
      super.move(dx, dy);
      oldMouseX = getProcessing().mouseX;
      oldMouseY = getProcessing().mouseY;

    } else if (isDragging && !getProcessing().mousePressed) {
      isDragging = false;
      mouseWasPressed = false;
      return drop();
    }
    mouseWasPressed = false;

    return null;
  }

  // this method returns null.
  // subclass types will override this drop() method to perform more interesting behavior
  protected Action drop() {
    return null;
  }
}
