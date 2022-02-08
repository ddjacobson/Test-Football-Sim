
package Comparator;

import World.Team;
import java.util.Comparator;

public class CompareTeamWinPct implements Comparator<Team> {
  public CompareTeamWinPct() {
  }

  public int compare(Team a, Team b) {
    if (a.winPct - b.winPct < 0.001D) {
      return 1;
    } else {
      return b.winPct - a.winPct < 0.001D ? -1 : 0;
    }
  }
}
