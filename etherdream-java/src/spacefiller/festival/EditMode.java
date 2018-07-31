package spacefiller.festival;

import geomerative.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.List;

public class EditMode extends Mode {
  private float lastX = -1;
  private float lastY = -1;

  public EditMode(Festival festival) {
    super(festival);
    festival.etherdream.debugDraw();
  }

  @Override
  public void stop() {
    festival.etherdream.noDebugDraw();
  }

  @Override
  public void pre() {
    festival.laserCursor.translate(
        festival.mouseX - festival.laserCursor.getX() - festival.laserCursor.getWidth() / 2,
        festival.mouseY - festival.laserCursor.getY() - festival.laserCursor.getWidth() / 2);

    festival.strokeWeight(1);
    festival.stroke(255);
    festival.noFill();

    List<IldaPoint> points = new ArrayList<>();

    festival.strokeWeight(3);
    for (RGeomElem s : festival.group.elements) {
      if (s.getCurveLength() > 0) {
        festival.addPoint(s.getPoint(0), points, Festival.REPEAT_POINTS, 0);

        for (float t = 0; t <= s.getCurveLength(); t += 5) {
          RPoint point = s.getPoint(t / s.getCurveLength());
          festival.addPoint(point, points, 1, 1);
        }

        festival.addPoint(s.getPoints()[s.getPoints().length - 1], points, Festival.REPEAT_POINTS, 0);
      }
    }

    festival.device.setPoints(points);

    super.pre();
  }

  @Override
  public void mouseEvent(MouseEvent mouseEvent) {
    if (mouseEvent.getAction() == MouseEvent.PRESS) {
      if (lastX == -1 && lastY == -1) {
        lastX = festival.mouseX;
        lastY = festival.mouseY;
      } else {
        RShape shape = (RShape) festival.group.elements[festival.group.elements.length - 1];

        RPath path = new RPath();
        RCommand lineCommand = new RCommand(lastX, lastY, festival.mouseX, festival.mouseY);
        path.addCommand(lineCommand);
        shape.addPath(path);

        festival.group.elements[festival.group.elements.length - 1] = new RShape(shape);
        lastX = festival.mouseX;
        lastY = festival.mouseY;
      }
    }
  }

  @Override
  public void keyEvent(KeyEvent keyEvent) {
    if (keyEvent.getAction() == KeyEvent.PRESS) {
      festival.group.addElement(new RShape());
      lastX = -1;
      lastY = -1;
    }
  }
}
