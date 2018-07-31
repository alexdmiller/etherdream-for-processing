package spacefiller.festival;

import spacefiller.ilda.IldaPoint;

public class AnimateMode extends Mode {
  enum Animation {
    SCAN, ROTATE, LINE_FILL
  }

  private Animation currentAnimation = Animation.SCAN;

  public AnimateMode(Festival festival) {
    super(festival);
    festival.etherdream.debugDraw();
  }

  @Override
  public void pre() {
    switch (currentAnimation) {
      case SCAN: {
        float y = 0;
        festival.device.clearPoints();
        for (int i = 0; i < 100; i++) {
          festival.device.addPoint(new IldaPoint(i / 100f * 2 - 1, y, 1, 1, 1, 1));
        }
        break;
      }
      default:
      break;
    }
  }
}
