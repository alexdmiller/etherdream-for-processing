package spacefiller.etherdream;

import processing.core.PApplet;
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
        float lastScreenX = device.points.get(0).x * parent.width + parent.width / 2;
        float lastScreenY = device.points.get(0).y * parent.height + parent.height / 2;

        for (IldaPoint point : device.points) {
          float screenX = point.x * parent.width + parent.width / 2;
          float screenY = point.y * parent.height + parent.height / 2;

          if (screenX != lastScreenX && screenY != lastScreenY) {
            if (point.a > 0) {
              parent.strokeWeight(1);
              parent.stroke(parent.color(point.r, point.g, point.b));
              parent.line(lastScreenX, lastScreenY, screenX, screenY);

              parent.strokeWeight(3);
              parent.stroke(parent.color(point.r, point.g, point.b));
              parent.stroke(255);
              parent.point(screenX, screenY);
            } else {
              parent.strokeWeight(1);
              parent.stroke(100);
              parent.line(lastScreenX, lastScreenY, screenX, screenY);

              parent.noFill();
              parent.stroke(255);
              parent.strokeWeight(1);
              parent.ellipse(screenX, screenY, 10, 10);
            }
          }

          lastScreenX = screenX;
          lastScreenY = screenY;
        }
      }
    }
  }
}
