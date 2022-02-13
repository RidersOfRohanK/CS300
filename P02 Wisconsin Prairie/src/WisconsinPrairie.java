//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO2 Wisconsin Prairie
// Course:   CS 300 Fall 2020
//
// Author:   Rohan Kale
// Email:    rkale2@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

public class WisconsinPrairie {
  private static PApplet processing; // PApplet object that represents the graphic
  // interface of the WisconsinPrairie application
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Cow[] cows; // array storing the current cows present
  // in the Prairie
  private static Random randGen; // Generator of random numbers

  public static void main(String[] args) {
    Utility.startApplication();
  }

  /**
   * Defines the initial environment properties of the application
   *
   * @param processingObj represents a reference to the graphical interface of
   *                      the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initialize the processing field to the one passed
// into the input argument parameter.
    backgroundImage = processing.loadImage("images/background.png");

    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
// width [resp. height]: System variable of the processing library that stores
// the width [resp. height] of the display window.

    randGen = new Random();

    cows = new Cow[10];
    for (int i = 0; i < cows.length; i++) {
      cows[i] = null;
    }
  }

  /**
   * Draws and updates the application display window.
   * This callback method called in an infinite loop.
   */
  public static void draw() {
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);

    for (int i = 0; i < cows.length; i++) {
      if (cows[i] != null) {
        cows[i].draw();
      }
    }
  }

  /**
   * Checks if the mouse is over a given cow whose reference is provided
   * as input parameter
   *
   * @param cow reference to a given cow object
   * @return true if the mouse is over the given cow object (i.e. over
   * the image of the cow), false otherwise
   */
  public static boolean isMouseOver(Cow cow) {
    float mouseX = processing.mouseX;
    float mouseY = processing.mouseY;

    int width = cow.getImage().width;
    int height = cow.getImage().height;

    float imgX = cow.getPositionX();
    float imgY = cow.getPositionY();

    if ((mouseX <= (imgX + (width / 2)) && mouseX >= (imgX - (width / 2)))
                && (mouseY <= (imgY + (height / 2)) && mouseY >= (imgY - (height / 2)))) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    int count = 0;
    for (int i = 0; i < cows.length; i++) {
      if (cows[i] != null && isMouseOver(cows[i])) {
        if (count < 1) {
          cows[i].setDragging(true);
          count++;
        }
      }

    }


  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < cows.length; i++) {
      if (cows[i] != null) {
        cows[i].setDragging(false);
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    char keyPressed = processing.key;
    if (keyPressed == 'c' || keyPressed == 'C') {
      int count = 0;
      for (int i = 0; i < cows.length; i++) {
        if (cows[i] == null && count < 1) {
          int randX = randGen.nextInt(processing.width);
          int randY = randGen.nextInt(processing.height);

          cows[i] = new Cow(processing, randX, randY);
          count++;
        }
      }
    }

    if (keyPressed == 'd' || keyPressed == 'D') {
      int count = 0;
      for (int i = 0; i < cows.length; i++) {
        if (cows[i] != null && isMouseOver(cows[i]) && count < 1) {
          cows[i] = null;
          count++;
        }
      }
    }
  }

}
