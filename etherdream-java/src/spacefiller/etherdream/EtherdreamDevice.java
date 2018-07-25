package spacefiller.etherdream;

import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.List;

public class EtherdreamDevice implements Runnable {
  static {
    System.loadLibrary("EtherdreamJNI");
  }

  private boolean deviceFound;
  private List<IldaPoint> points;
  private Thread thread;
  private int deviceID;

  public EtherdreamDevice() {
    deviceFound = false;
    points = new ArrayList<>();
    thread = new Thread(this);
  }

  private native void lib_start();
  private native int dac_count();
  private native int device_connect(int deviceID);
  private native boolean device_ready();
  private native int device_write(int deviceID, IldaPoint[] points, int length, int pps);

  public void connect(int id) {
    deviceID = id;
    thread.start();
  }

  public void disconnect() {
    thread.interrupt();
  }

  public void addPoints(List<IldaPoint> points) {
    this.points.addAll(points);
  }

  public void setPoints(List<IldaPoint> points) {
    this.points = points;
  }

  public boolean isDeviceFound() {
    return deviceFound;
  }

  @Override
  public void run() {
    while (!thread.isInterrupted()) {
      System.out.println("yeah");
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        break;
      }
    }
  }
}
