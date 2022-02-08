
package Comparator;

import World.Formation;
import java.util.Comparator;

public class CompareGameplanChance implements Comparator<Formation> {
  public CompareGameplanChance() {
  }

  public int compare(Formation a, Formation b) {
    if (a.formationChance > b.formationChance) {
      return 1;
    } else {
      return b.formationChance > a.formationChance ? -1 : 0;
    }
  }
}
