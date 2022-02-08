package Comparator;

import World.Player;
import java.util.Comparator;

public class ComparePlayerOvr implements Comparator<Player> {
  public ComparePlayerOvr() {
  }

  public int compare(Player p1, Player p2) {
    if (p1.overall > p2.overall) {
      return 1;
    } else {
      return p2.overall > p1.overall ? -1 : 0;
    }
  }
}
