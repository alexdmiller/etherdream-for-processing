package spacefiller.festival;

import geomerative.*;
import processing.core.PApplet;
import spacefiller.etherdream.Etherdream;
import spacefiller.etherdream.EtherdreamDevice;
import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.List;

public class Festival extends PApplet {
  static final int REPEAT_POINTS = 20;

  public static void main(String[] args) {
    PApplet.main("spacefiller.festival.Festival");
  }

  EtherdreamDevice device;
  Etherdream etherdream;
  RGroup group;
  RShape laserCursor;

  Mode currentMode;

  @Override
  public void settings() {
    size(1000,1000);
  }

  @Override
  public void setup() {
    RG.init(this);

    group = new RGroup();

    laserCursor = RShape.createCircle(0, 0, 70);
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

  void addPoint(RPoint point, List<IldaPoint> ildaPoints, int repeat, float a) {
    for (int i = 0; i < repeat; i++) {
      ildaPoints.add(new IldaPoint((point.x - width / 2) / width, (point.y - height / 2) / height, a, a, a, a));
    }
  }
}
