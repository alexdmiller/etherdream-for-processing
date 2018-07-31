package spacefiller.festival;

import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Mode {
  protected Festival festival;

  public Mode(Festival festival) {
    this.festival = festival;

    festival.registerMethod("pre", this);
    festival.registerMethod("mouseEvent", this);
    festival.registerMethod("keyEvent", this);
  }

  public void stop() {
    festival.unregisterMethod("pre", this);
    festival.unregisterMethod("mouseEvent", this);
    festival.unregisterMethod("keyEvent", this);
  }

  public void pre() {

  }

  public void mouseEvent(MouseEvent mouseEvent) {

  }

  public void keyEvent(KeyEvent keyEvent) {

  }
}
