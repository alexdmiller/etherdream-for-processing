package spacefiller.etherdream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EtherdreamDeviceTest {
  private EtherdreamDevice device;

  @BeforeEach
  public void setup() throws InterruptedException {
    Thread.sleep(1000);
    device = new EtherdreamDevice();
    device.connect(0);
  }

  @AfterEach
  public void teardown() {
    if (device.isConnected()) device.disconnect();
  }

  @Test
  public void connect() throws InterruptedException {
    assertEquals(true, device.isConnected());
  }

  @Test
  public void disconnect() throws InterruptedException {
    device.disconnect();

    assertEquals(false, device.isConnected());
  }

  @Test
  public void dacCount() {
    assertEquals(1, EtherdreamDevice.dacCount());
  }

  @Test
  public void sendPoints() throws InterruptedException {
    List<IldaPoint> points = new ArrayList<>();

    for (int i = 0; i < 2000; i++) {
      points.add(new IldaPoint(
          (float) (Math.cos(i / 1000f * 2 * Math.PI) * 0.9),
          (float) (Math.sin(i / 1000f * 2 * Math.PI) * 0.9),
          1, 1, 1, 1));
    }
    device.setPoints(points);

    Thread.sleep(5000);
  }
}