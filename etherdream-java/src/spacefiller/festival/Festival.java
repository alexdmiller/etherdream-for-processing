package spacefiller.festival;

import geomerative.*;
import processing.core.PApplet;
import processing.core.PVector;
import spacefiller.etherdream.Etherdream;
import spacefiller.etherdream.EtherdreamDevice;
import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.List;

public class Festival extends PApplet {
  public static final float INCREMENT_DISTANCE = 0.01f;
  public static final int REPEAT_POINTS = 20;
  private static final float CROSSHAIR_SIZE = 0.1f;

  public static void main(String[] args) {
    PApplet.main("spacefiller.festival.Festival");
  }

  EtherdreamDevice device;
  Etherdream etherdream;
  RGroup group;
  RShape laserCursor;
  Mode currentMode;

  PVector laserMousePosition;

  @Override
  public void settings() {
    // size(1000,1000);
    fullScreen();
  }

  @Override
  public void setup() {
    RG.init(this);

    group = new RGroup();

    laserCursor = new RShape();
    laserCursor.addShape(RShape.createLine(-CROSSHAIR_SIZE, -CROSSHAIR_SIZE, CROSSHAIR_SIZE, CROSSHAIR_SIZE));
    laserCursor.addShape(RShape.createLine(-CROSSHAIR_SIZE, CROSSHAIR_SIZE, CROSSHAIR_SIZE, -CROSSHAIR_SIZE));

    group.addElement(laserCursor);

    group.addElement(new RShape());

    etherdream = new Etherdream(this);
    device = etherdream.connect();

    etherdream.debugDraw();

    currentMode = new EditMode(this);
  }

  @Override
  public void draw() {
    background(0);

//    laserCursor.translate(
//        (mouseX / width) - laserCursor.getX(),
//        (mouseY / height) - lasferCursor.getY());

    laserMousePosition = etherdream.screenToLaser(mouseX, mouseY);
    laserCursor.translate(laserMousePosition.x - laserCursor.getCenter().x, laserMousePosition.y- laserCursor.getCenter().y);
  }

  @Override
  public void keyPressed() {
    if (key == 'a') {
      currentMode.stop();
      currentMode = new AnimateMode(this);
    } else if (key == 'e') {
      currentMode.stop();
      currentMode = new EditMode(this);
    }
  }
}
