package spacefiller.ilda;

public class IldaPoint {
  public float x, y, r, g, b, a;

  public IldaPoint(float x, float y) {
    this(x, y, 1, 1, 1, 1);
  }

  public IldaPoint(float x, float y, float r, float g, float b, float a) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }
}
