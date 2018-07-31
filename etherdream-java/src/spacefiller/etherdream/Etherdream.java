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
        for (IldaPoint point : device.points) {
          if (point.a > 0) {
            parent.strokeWeight(2);
            parent.stroke(parent.color(point.r, point.g, point.b));
            parent.stroke(255);
            parent.point(point.x * parent.width + parent.width / 2, point.y * parent.height + parent.height / 2);
          } else {
            parent.noFill();
            parent.stroke(255);
            parent.strokeWeight(1);
            parent.ellipse(point.x * parent.width + parent.width / 2, point.y * parent.height + parent.height / 2, 10, 10);
          }
        }
      }
    }
  }
}
