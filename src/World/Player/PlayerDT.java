
package World.Player;

import World.Team;

import java.util.Random;

public class PlayerDT extends Player {
  private static final String[] playerTypes = new String[]{"Nose Tackle", "Run Stopper", "Pass Rusher"};
  private static final int numTypes;
  public final double passRushWeight = 0.2D;
  public final double runStopWeight = 0.2D;
  public final double strengthWeight = 0.1D;
  public final double tackleWeight = 0.1D;
  public final double pursuitWeight = 0.1D;
  public final double hitPowerWeight = 0.1D;
  public final double speedWeight = 0.1D;
  public final double quicknessWeight = 0.1D;

  public PlayerDT(Team t) {
    this.whiteWeight = 15;
    this.blackWeight = 75;
    this.race = this.findRace();
    this.getNames();
    this.team = t;
    this.position = "Defensive Tackle";
    this.playerType = playerTypes[(int)(Math.random() * (double)numTypes)];
    String var2 = this.playerType;
    byte var3 = -1;
    switch(var2.hashCode()) {
      case -1953750668:
        if (var2.equals("Pass Rusher")) {
          var3 = 2;
        }
        break;
      case -79205114:
        if (var2.equals("Run Stopper")) {
          var3 = 1;
        }
        break;
      case 297736379:
        if (var2.equals("Nose Tackle")) {
          var3 = 0;
        }
    }

    switch(var3) {
      case 0:
        this.getNoseTackleStats();
        break;
      case 1:
        this.getRunStopperStats();
        break;
      case 2:
        this.getPassRusherStats();
    }

    this.overall = this.getOverall();
  }

  public PlayerDT() {
    this.whiteWeight = 15;
    this.blackWeight = 75;
    this.race = this.findRace();
    this.getNames();
    this.position = "Defensive Tackle";
    this.playerType = playerTypes[(int)(Math.random() * (double)numTypes)];
    String var1 = this.playerType;
    byte var2 = -1;
    switch(var1.hashCode()) {
      case -1953750668:
        if (var1.equals("Pass Rusher")) {
          var2 = 2;
        }
        break;
      case -79205114:
        if (var1.equals("Run Stopper")) {
          var2 = 1;
        }
        break;
      case 297736379:
        if (var1.equals("Nose Tackle")) {
          var2 = 0;
        }
    }

    switch(var2) {
      case 0:
        this.getNoseTackleStats();
        break;
      case 1:
        this.getRunStopperStats();
        break;
      case 2:
        this.getPassRusherStats();
    }

    this.overall = this.getOverall();
  }

  private void getPassRusherStats() {
    Random rand = new Random();
    int maxHeight = 78;
    int minHeight = 74;
    this.heightIn = rand.nextInt(minHeight, maxHeight);
    int maxWeight = 270;
    int minWeight = 230;
    this.weight = rand.nextInt(minWeight, maxWeight);
    int maxSpeed = 88;
    int minSpeed = 79;
    this.speed = rand.nextInt(minSpeed, maxSpeed);
    int maxPassRush = 90;
    int minPassRush = 78;
    this.passRush = rand.nextInt(minPassRush, maxPassRush);
    int maxRunStop = 80;
    int minRunStop = 68;
    this.runStop = rand.nextInt(minRunStop, maxRunStop);
    int maxStrength = 85;
    int minStrength = 74;
    this.strength = rand.nextInt(minStrength, maxStrength);
    int maxTackle = 84;
    int minTackle = 75;
    this.tackle = rand.nextInt(minTackle, maxTackle);
    int maxHitPower = 84;
    int minHitPower = 69;
    this.hitPower = rand.nextInt(minHitPower, maxHitPower);
    int maxPursuit = 88;
    int minPursuit = 78;
    this.pursuit = rand.nextInt(minPursuit, maxPursuit);
    int maxQuickness = 86;
    int minQuickness = 75;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);
  }

  private void getRunStopperStats() {
    Random rand = new Random();
    int maxHeight = 78;
    int minHeight = 74;
    this.heightIn = rand.nextInt(minHeight, maxHeight);
    int maxWeight = 320;
    int minWeight = 285;
    this.weight = rand.nextInt(minWeight, maxWeight);
    int maxSpeed = 67;
    int minSpeed = 55;
    this.speed = rand.nextInt(minSpeed, maxSpeed);
    int maxPassRush = 80;
    int minPassRush = 70;
    this.passRush = rand.nextInt(minPassRush, maxPassRush);
    int maxRunStop = 94;
    int minRunStop = 80;
    this.runStop = rand.nextInt(minRunStop, maxRunStop);
    int maxStrength = 94;
    int minStrength = 85;
    this.strength = rand.nextInt(minStrength, maxStrength);
    int maxTackle = 90;
    int minTackle = 80;
    this.tackle = rand.nextInt(minTackle, maxTackle);
    int maxHitPower = 82;
    int minHitPower = 70;
    this.hitPower = rand.nextInt(minHitPower, maxHitPower);
    int maxPursuit = 90;
    int minPursuit = 75;
    this.pursuit = rand.nextInt(minPursuit, maxPursuit);
    int maxQuickness = 77;
    int minQuickness = 65;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);
  }

  private void getNoseTackleStats() {
    Random rand = new Random();
    int maxHeight = 78;
    int minHeight = 73;
    this.heightIn = rand.nextInt(minHeight, maxHeight);
    int maxWeight = 355;
    int minWeight = 315;
    this.weight = rand.nextInt(minWeight, maxWeight);
    int maxSpeed = 64;
    int minSpeed = 54;
    this.speed = rand.nextInt(minSpeed, maxSpeed);
    int maxPassRush = 80;
    int minPassRush = 67;
    this.passRush = rand.nextInt(minPassRush, maxPassRush);
    int maxRunStop = 94;
    int minRunStop = 81;
    this.runStop = rand.nextInt(minRunStop, maxRunStop);
    int maxStrength = 97;
    int minStrength = 88;
    this.strength = rand.nextInt(minStrength, maxStrength);
    int maxTackle = 85;
    int minTackle = 72;
    this.tackle = rand.nextInt(minTackle, maxTackle);
    int maxHitPower = 84;
    int minHitPower = 75;
    this.hitPower = rand.nextInt(minHitPower, maxHitPower);
    int maxPursuit = 86;
    int minPursuit = 73;
    this.pursuit = rand.nextInt(minPursuit, maxPursuit);
    int maxQuickness = 75;
    int minQuickness = 63;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);
  }

  private int getOverall() {
    int overall = (int)((double)this.passRush * 0.2D + (double)this.manCoverage * 0.2D + (double)this.strength * 0.1D + (double)this.speed * 0.1D + (double)this.quickness * 0.1D + (double)this.tackle * 0.1D + 0.1D * (double)this.hitPower + 0.1D * (double)this.pursuit);
    return overall;
  }

  public void printStats() {
    String var10001 = this.firstName;
    System.out.println("Name: " + var10001 + " " + this.lastName + "\nRace: " + this.getRace() + "\nPosition: " + this.position + "\nWorld.Player.Player Archetype: " + this.playerType + "\nOverall: " + this.overall + "\nHeight: " + this.inToFt(this.heightIn) + "\nWeight: " + this.weight + " lbs.\nSpeed: " + this.speed + "\nQuickness: " + this.quickness + "\nCatching: " + this.passRush + "\nMan Coverage: " + this.manCoverage + "\nZone Coverage: " + this.strength + "\nTackle: " + this.tackle);
  }

  static {
    numTypes = playerTypes.length;
  }
}
