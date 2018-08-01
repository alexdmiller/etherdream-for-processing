package spacefiller.festival;

import geomerative.*;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.List;

public class EditMode extends Mode {
  private PVector lastLaserPoint;

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
    festival.device.clearPoints();

    EtherdreamUtils.drawRGroupToEtherdream(festival.group, festival.device);

    super.pre();
  }

  @Override
  public void mouseEvent(MouseEvent mouseEvent) {
    if (mouseEvent.getAction() == MouseEvent.PRESS) {
      if (lastLaserPoint == null) {
        lastLaserPoint = festival.laserMousePosition;
      } else {
        PVector laserPoint = festival.laserMousePosition;

        RShape shape = (RShape) festival.group.elements[festival.group.elements.length - 1];

        RPath path = new RPath();
        RCommand lineCommand = new RCommand(lastLaserPoint.x, lastLaserPoint.y, laserPoint.x, laserPoint.y);
        path.addCommand(lineCommand);
        shape.addPath(path);

        festival.group.elements[festival.group.elements.length - 1] = new RShape(shape);

        lastLaserPoint = laserPoint;
      }
    }
  }

  @Override
  public void keyEvent(KeyEvent keyEvent) {
    if (keyEvent.getAction() == KeyEvent.PRESS) {
      festival.group.addElement(new RShape());
      lastLaserPoint = null;
    }
  }
}
