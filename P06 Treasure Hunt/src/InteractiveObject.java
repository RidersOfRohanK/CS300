import processing.core.PApplet;

public class InteractiveObject {
  private final String NAME; // the constant name identifying this interactive object
  private boolean isActive; // active means this interactive object is visible and can be interacted with
  private static PApplet processing = null;

  public static void setProcessing(PApplet processing1) {
    processing = processing1;
  }
  protected static PApplet getProcessing() {// accessor method to retrieve this static field
    return processing;
  }

  public InteractiveObject(String name) {// initializes the name of this object,and sets isActive to true
    NAME = name;
    activate();
  }


  public boolean hasName(String name) {// returns true only when contents of name equal NAME
    if(name.equals(NAME)){
      return true;
    }
    return false;
  }


  public boolean isActive() {// returns true only when isActive is true
    if(isActive){
      return true;
    }
    return false;
  }

  public void activate() {// changes isActive to true
    isActive = true;
  }

  public void deactivate() {// changes isActive to false
    isActive = false;
  }
// this method returns null
// subclass types will override this update() method to do more interesting things
  public Action update() {
    return null;
  }

}
