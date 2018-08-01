package spacefiller.festival;

import geomerative.RGeomElem;
import geomerative.RGroup;
import geomerative.RPoint;
import spacefiller.etherdream.Etherdream;
import spacefiller.etherdream.EtherdreamDevice;
import spacefiller.ilda.IldaPoint;

public class EtherdreamUtils {
  public static void drawRGroupToEtherdream(RGroup group, EtherdreamDevice device) {
    for (RGeomElem s : group.elements) {
      if (s.getCurveLength() > 0) {
        device.addPoint(rpointToIlda(s.getPoint(0), 0), Festival.REPEAT_POINTS);

        for (float t = 0; t <= s.getCurveLength(); t += Festival.INCREMENT_DISTANCE) {
          RPoint point = s.getPoint(t / s.getCurveLength());
          device.addPoint(rpointToIlda(point, 1));
        }

        device.addPoint(rpointToIlda(s.getPoints()[s.getPoints().length - 1], 0), Festival.REPEAT_POINTS);
      }
    }
  }

  public static IldaPoint rpointToIlda(RPoint rpoint, float alpha) {
    return new IldaPoint(rpoint.x, rpoint.y, alpha, alpha, alpha, alpha);
  }
}
