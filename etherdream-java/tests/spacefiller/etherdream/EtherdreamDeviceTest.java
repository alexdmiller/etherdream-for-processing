package spacefiller.etherdream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtherdreamDeviceTest {
  private EtherdreamDevice device;

  @BeforeEach
  public void setup() {
    device = new EtherdreamDevice();
    device.connect(0);
  }

  @AfterEach
  public void teardown() {
    device.disconnect();
  }

  @Test
  public void connect() throws InterruptedException {
    Thread.sleep(1000);
  }
}