package spacefiller.etherdream;

import processing.core.PApplet;
import processing.core.PVector;
import spacefiller.ilda.IldaPoint;

import java.util.HashMap;
import java.util.Map;

public class Etherdream {
  private PApplet parent;
  private Map<Integer, EtherdreamDevice> devices;
  private boolean debugDraw;

  public Etherdream(PApplet parent) {
    this.parent = parent;
    this.devices = new HashMap<>();

    parent.registerMethod("draw", this);
  }

  public EtherdreamDevice connect() {
    return connect(0);
  }

  public EtherdreamDevice connect(int id) {
    EtherdreamDevice device = new EtherdreamDevice();
    try {
      // TODO: what to do if we've already connected to this device?
      device.connect(id);
      devices.put(id, device);
      return device;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void debugDraw() {
    debugDraw = true;
  }

  public void noDebugDraw() {
    debugDraw = false;
  }

  public void draw() {
    if (debugDraw) {
      parent.strokeWeight(2);
      for (EtherdreamDevice device : devices.values()) {
        PVector lastScreenPosition = laserToScreen(device.points.get(0));

        for (IldaPoint point : device.points) {
          PVector screenPosition = laserToScreen(point);

          if (!lastScreenPosition.equals(screenPosition)) {
            if (point.a > 0) {
              parent.strokeWeight(1);
              parent.stroke(parent.color(point.r * 255, point.g * 255, point.b * 255));
              parent.line(
                  lastScreenPosition.x, lastScreenPosition.y,
                  screenPosition.x, screenPosition.y);

              parent.strokeWeight(3);
              parent.stroke(parent.color(point.r, point.g, point.b));
              parent.stroke(255);
              parent.point(screenPosition.x, screenPosition.y);
            } else {
              parent.strokeWeight(1);
              parent.stroke(100);
              parent.line(
                  lastScreenPosition.x, lastScreenPosition.y,
                  screenPosition.x, screenPosition.y);

              parent.noFill();
              parent.stroke(255);
              parent.strokeWeight(1);
              parent.ellipse(screenPosition.x, screenPosition.y, 10, 10);
            }
          }

          lastScreenPosition = screenPosition;
        }
      }
    }
  }

  public PVector laserToScreen(float x, float y) {
    return new PVector((x + 1) / 2 * parent.width, (y + 1) / 2 * parent.height);
  }

  public PVector screenToLaser(float x, float y) {
    return new PVector(x / parent.width * 2 - 1, y / parent.height * 2 - 1);
  }

  public PVector laserToScreen(IldaPoint p) {
    return new PVector((p.x + 1) / 2 * parent.width, (p.y + 1) / 2 * parent.height);
  }

  public PVector screenToLaser(PVector p) {
    return new PVector(p.x / parent.width * 2 - 1, p.y / parent.height * 2 - 1);
  }
}
