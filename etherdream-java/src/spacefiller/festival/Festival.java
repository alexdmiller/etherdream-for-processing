package spacefiller.festival;

import geomerative.*;
import processing.core.PApplet;
import spacefiller.etherdream.Etherdream;
import spacefiller.etherdream.EtherdreamDevice;
import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.List;

public class Festival extends PApplet {
  private static final int REPEAT_POINTS = 20;
  private float lastX = -1;
  private float lastY = -1;

  public static void main(String[] args) {
    PApplet.main("spacefiller.festival.Festival");
  }

  EtherdreamDevice device;
  Etherdream etherdream;
  RGroup group;
  RShape laserCursor;

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
  }

  @Override
  public void keyPressed() {
    group.addElement(new RShape());
    lastX = -1;
    lastY = -1;
  }

  @Override
  public void mousePressed() {
    if (lastX == -1 && lastY == -1) {
      lastX = mouseX;
      lastY = mouseY;
    } else {
      RShape shape = (RShape) group.elements[group.elements.length - 1];

      RPath path = new RPath();
      RCommand lineCommand = new RCommand(lastX, lastY, mouseX, mouseY);
      path.addCommand(lineCommand);
      shape.addPath(path);

      group.elements[group.elements.length - 1] = new RShape(shape);
      lastX = mouseX;
      lastY = mouseY;
    }
  }

  @Override
  public void draw() {
    background(0);

    laserCursor.translate(mouseX - laserCursor.getX() - laserCursor.getWidth() / 2,
        mouseY - laserCursor.getY() - laserCursor.getWidth() / 2);

    strokeWeight(1);
    stroke(255);
    noFill();
//    group.draw(this);

    List<IldaPoint> points = new ArrayList<>();

    strokeWeight(3);
    for (RGeomElem s : group.elements) {
      if (s.getCurveLength() > 0) {
        addPoint(s.getPoint(0), points, REPEAT_POINTS, 0);

        for (float t = 0; t <= s.getCurveLength(); t += 5) {
          RPoint point = s.getPoint(t / s.getCurveLength());
          addPoint(point, points, 1, 1);
        }

        addPoint(s.getPoints()[s.getPoints().length - 1], points, REPEAT_POINTS, 0);
      }
    }

    device.setPoints(points);
  }

  private void addPoint(RPoint point, List<IldaPoint> ildaPoints, int repeat, float a) {
    for (int i = 0; i < repeat; i++) {
      ildaPoints.add(new IldaPoint((point.x - width / 2) / width, (point.y - height / 2) / height, a, a, a, a));
    }
  }
}
