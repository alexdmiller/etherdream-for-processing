package spacefiller.festival;

import spacefiller.etherdream.EtherdreamDevice;
import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.List;

public class Festival {
  public static void main(String[] args) throws InterruptedException {
    EtherdreamDevice device = new EtherdreamDevice();
    device.connect(0);

    int t = 0;
    while (true) {
      List<IldaPoint> points = new ArrayList<>();

      for (int i = 0; i < 500; i++) {
        points.add(new IldaPoint(
            (float) (Math.cos(i / 500f * 2 * Math.PI) * (0.8 + Math.sin(t / 100f) * 0.1)),
            (float) (Math.sin(i / 500f * 2 * Math.PI) * (0.8 + Math.sin(t / 100f) * 0.1)),
            1, 1, 1, 1));
      }
      device.setPoints(points);

      t++;
    }
  }
}
