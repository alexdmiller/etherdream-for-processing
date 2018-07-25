package spacefiller.etherdream;

import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.List;

public class EtherdreamDevice implements Runnable {
  static {
    System.loadLibrary("EtherdreamJNI");
  }

  private static boolean libraryStarted = false;

  private static native void libStart();
  public static native int dacCount();

  private boolean connected;
  private List<IldaPoint> points;
  private Thread thread;
  private int deviceID;

  public EtherdreamDevice() {
    connected = false;
    points = new ArrayList<>();
    thread = new Thread(this);
  }

  private native int deviceConnect(int deviceID);
  private native void deviceDisconnect(int deviceID);
  private native boolean deviceReady(int deviceID);
  private native int deviceWrite(int deviceID, IldaPoint[] points, int length, int pps);

  public void connect(int id) throws InterruptedException {
    if (!libraryStarted) {
      libStart();
      libraryStarted = true;

      // Sleep a little so DAC can be found
      Thread.sleep(1000);
    }

    deviceID = id;
    connected = deviceConnect(deviceID) == 0;
    thread.start();
  }

  public void disconnect() {
    thread.interrupt();
    deviceDisconnect(deviceID);
    connected = false;
  }

  public void addPoints(List<IldaPoint> points) {
    this.points.addAll(points);
  }

  public void setPoints(List<IldaPoint> points) {
    this.points = points;
  }

  public boolean isConnected() {
    return connected;
  }

  @Override
  public void run() {
    while (!thread.isInterrupted()) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        break;
      }
    }
  }
}
