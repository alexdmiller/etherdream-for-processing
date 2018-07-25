package spacefiller.etherdream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtherdreamDeviceTest {
  private EtherdreamDevice device;

  @BeforeEach
  public void setup() throws InterruptedException {
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
}