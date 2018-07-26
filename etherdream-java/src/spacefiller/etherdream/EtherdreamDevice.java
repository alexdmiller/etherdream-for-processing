package spacefiller.etherdream;

import spacefiller.ilda.IldaPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EtherdreamDevice implements Runnable {
  static {
    System.loadLibrary("EtherdreamJNI");
  }

  private static boolean libraryStarted = false;

  private static native void libStart();
  public static native int dacCount();

  private boolean connected;
  private final List<IldaPoint> points = Collections.synchronizedList(new ArrayList<>());
  private Thread thread;
  private int deviceID;

  public EtherdreamDevice() {
    connected = false;
    thread = new Thread(this);
  }

  private native int deviceConnect(int deviceID);
  private native void deviceDisconnect(int deviceID);
  private native void deviceWaitUntilReady(int deviceID);
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
    this.points.clear();
    this.points.addAll(points);
  }

  public void clearPoints() {
    this.points.clear();
  }

  public boolean isConnected() {
    return connected;
  }

  @Override
  public void run() {
    while (!thread.isInterrupted()) {
      if (!deviceReady(deviceID)) continue;
      synchronized (points) {
        int success = deviceWrite(deviceID, points.toArray(new IldaPoint[points.size()]), points.size(), 30000);
        if (success != 0) {
          System.out.println("Write failed; error id = " + success);
        }
      }
    }
  }
}
