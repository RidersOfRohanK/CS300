//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    VisibleObject.java
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
import processing.core.PImage;

import java.io.File;

/**
 * Visible objects that cannot be interacted with necessarily
 */
public class VisibleObject extends InteractiveObject {
  private PImage image; // the graphical representation of this object
  private int x; // the horizontal position (in pixels of this object’s left side)
  private int y; // the vertical position (in pixels of this object’s top side)

  // initialize this new VisibleObject
  // the image for this visible object should be loaded from :
  // "images"+File.separator+ name +".png"
  public VisibleObject(String name, int x, int y) {
    super(name);
    this.x = x;
    this.y = y;
    image = super.getProcessing().loadImage("images" + File.separator + name + ".png");
  }

  @Override
  // draws image at its position before returning null
  public Action update() {
    super.getProcessing().image(image, x, y);
    return null;
  }

  // changes x by subtracting dx to it (and y by dy)

  /**
   *
   * @param dx the amount to be added to X
   * @param dy the amount to be added to Y
   */
  public void move(int dx, int dy) {
    x -= dx;
    y -= dy;
  }

  // return true only when point x,y is over image

  /**
   *
   * @param xCurrent the X cordinate of the thing being checked
   * @param yCurrent the Y cordinate of the thing being checked
   * @return true if isOver
   */
  public boolean isOver(int xCurrent, int yCurrent) {
    int width = image.width;
    int height = image.height;


    float imgX = this.x;
    float imgY = this.y;
    if (xCurrent >= (imgX) && xCurrent <= imgX + (width) && yCurrent >= (imgY) && yCurrent <= imgY + (height)) {
      return true;
    } else {
      return false;
    }
  }

  // return true only when other’s image
  // overlaps this one’s

  /**
   *
   * @param other object to check if the object is over it
   * @return true if object is over it
   */
  public boolean isOver(VisibleObject other) {
    if (isOver(other.x, other.y)) {
      return true;
    }
    return false;
  }

}
