
package World;

import Comparator.CompareGameplanChance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Gameplan {
  ArrayList<Formation> sets = new ArrayList();
  String name;

  public Gameplan(int gameplan, boolean isOffense) {
    if (isOffense) {
      switch(gameplan) {
        case 0:
          this.createWestCoastPlan();
          break;
        case 1:
          this.createSpreadPlan();
          break;
        case 2:
          this.createOptionPlan();
          break;
        case 3:
          this.createSmashMouthPlan();
          break;
        case 4:
          this.createZoneRunPlan();
      }

      Collections.sort(this.sets, new CompareGameplanChance());
    }

  }

  private void createWestCoastPlan() {
    this.name = "West Coast";
    this.sets.add(new Formation(1, 0, 3, 1, 50, "Base"));
    this.sets.add(new Formation(1, 0, 3, 1, 10, "I-Formation"));
    this.sets.add(new Formation(1, 0, 2, 2, 20, "Twin TE"));
    this.sets.add(new Formation(1, 0, 4, 0, 20, "Spread"));
    this.sets.add(new Formation(1, 0, 1, 3, 0, "Goal Line"));
  }

  private void createSpreadPlan() {
    this.name = "Spread";
    this.sets.add(new Formation(1, 0, 3, 1, 55, "Base"));
    this.sets.add(new Formation(1, 0, 3, 1, 10, "I-Formation"));
    this.sets.add(new Formation(1, 0, 2, 2, 20, "Twin TE"));
    this.sets.add(new Formation(1, 0, 4, 0, 15, "Spread"));
    this.sets.add(new Formation(1, 0, 1, 3, 0, "Goal Line"));
  }

  private void createOptionPlan() {
    this.name = "Option";

    this.sets.add(new Formation(1, 0, 3, 1, 55, "Base"));
    this.sets.add(new Formation(1, 0, 3, 1, 10, "I-Formation"));
    this.sets.add(new Formation(1, 0, 2, 2, 20, "Twin TE"));
    this.sets.add(new Formation(1, 0, 4, 0, 15, "Spread"));
    this.sets.add(new Formation(1, 0, 1, 3, 0, "Goal Line"));
  }

  private void createSmashMouthPlan() {
    this.name = "Smash Mouth";
    this.sets.add(new Formation(1, 0, 3, 1, 55, "Base"));
    this.sets.add(new Formation(1, 0, 3, 1, 10, "I-Formation"));
    this.sets.add(new Formation(1, 0, 2, 2, 20, "Twin TE"));
    this.sets.add(new Formation(1, 0, 4, 0, 15, "Spread"));
    this.sets.add(new Formation(1, 0, 1, 3, 0, "Goal Line"));
  }

  private void createZoneRunPlan() {
    this.name = "Zone Run";
    this.sets.add(new Formation(1, 0, 3, 1, 55, "Base"));
    this.sets.add(new Formation(1, 0, 3, 1, 10, "I-Formation"));
    this.sets.add(new Formation(1, 0, 2, 2, 20, "Twin TE"));
    this.sets.add(new Formation(1, 0, 4, 0, 15, "Spread"));
    this.sets.add(new Formation(1, 0, 1, 3, 0, "Goal Line"));
  }

  public Formation getRandomGameplan() {
    Random rand = new Random();
    Formation f = (Formation)this.sets.get(0);
    int choice = rand.nextInt(100);
    if (((Formation)this.sets.get(0)).formationChance > choice) {
      return (Formation)this.sets.get(0);
    } else if (((Formation)this.sets.get(1)).formationChance > choice) {
      return (Formation)this.sets.get(1);
    } else if (((Formation)this.sets.get(1)).formationChance + ((Formation)this.sets.get(2)).formationChance > choice) {
      return (Formation)this.sets.get(2);
    } else {
      return ((Formation)this.sets.get(1)).formationChance + ((Formation)this.sets.get(2)).formationChance + ((Formation)this.sets.get(3)).formationChance > choice ? (Formation)this.sets.get(3) : (Formation)this.sets.get(4);
    }
  }
}
