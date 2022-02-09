package World;

import java.util.Random;

public class PlayerFS extends Player{

  private static final String [] playerTypes = {"Zone Hawk", "Hard Hitter", "Coverage"};
  private static final int numTypes = playerTypes.length;


  final public double catchingWeight = 0.05;
  final public double manCoverageWeight = 0.10;
  final public double zoneCoverageWeight = 0.20;
  final public double tackleWeight = 0.15;
  final public double pursuitWeight = 0.10;
  final public double hitPowerWeight = 0.05;
  final public double speedWeight = 0.20;
  final public double quicknessWeight = 0.15;



  public PlayerFS(Team t){
    whiteWeight = 10;
    blackWeight = 90;

    this.race = findRace();
    getNames();

    this.team = t;
    position = "Free Safety";
    this.playerType = playerTypes[(int) (Math.random() * numTypes)];

    switch (this.playerType){
      case "Zone Hawk" -> getZoneHawkStats();
      case "Hard Hitter" -> getHardHitterStats();
      case "Coverage" -> getCoverageStats();

    }
    this.overall = getOverall();

  }

  public PlayerFS(){

    whiteWeight = 10;
    blackWeight = 90;

    this.race = findRace();
    getNames();

    position = "Free Safety";
    this.playerType = playerTypes[(int) (Math.random() * numTypes)];

    switch (this.playerType){
      case "Zone Hawk" -> getZoneHawkStats();
      case "Hard Hitter" -> getHardHitterStats();
      case "Coverage" -> getCoverageStats();

    }
    this.overall = getOverall();
  }

  private void getCoverageStats() {

    Random rand = new Random();
    int maxHeight = 74;
    int minHeight = 70;
    this.heightIn = rand.nextInt(minHeight, maxHeight);

    int maxWeight = 210;
    int minWeight = 190;
    this.weight = rand.nextInt(minWeight, maxWeight);

    int maxSpeed = 93;
    int minSpeed = 87;
    this.speed = rand.nextInt(minSpeed, maxSpeed);

    int maxCatching = 75;
    int minCatching = 60;
    this.catching = rand.nextInt(minCatching, maxCatching);

    int maxManCoverage = 78;
    int minManCoverage = 70;
    this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

    int maxZoneCoverage = 92;
    int minZoneCoverage = 79;
    this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

    int maxTackle = 83;
    int minTackle = 70;
    this.tackle = rand.nextInt(minTackle, maxTackle);

    int maxHitPower = 80;
    int minHitPower = 64;
    this.hitPower = rand.nextInt(minHitPower, maxHitPower);

    int maxPursuit = 82;
    int minPursuit = 73;
    this.pursuit = rand.nextInt(minPursuit, maxPursuit);

    int maxQuickness = 93;
    int minQuickness = 87;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);


  }

  private void getHardHitterStats() {

    Random rand = new Random();
    int maxHeight = 75;
    int minHeight = 71;
    this.heightIn = rand.nextInt(minHeight, maxHeight);

    int maxWeight = 220;
    int minWeight = 195;
    this.weight = rand.nextInt(minWeight, maxWeight);

    int maxSpeed = 90;
    int minSpeed = 85;
    this.speed = rand.nextInt(minSpeed, maxSpeed);

    int maxCatching = 68;
    int minCatching = 59;
    this.catching = rand.nextInt(minCatching, maxCatching);

    int maxManCoverage = 73;
    int minManCoverage = 67;
    this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

    int maxZoneCoverage = 83;
    int minZoneCoverage = 74;
    this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

    int maxTackle = 85;
    int minTackle = 76;
    this.tackle = rand.nextInt(minTackle, maxTackle);

    int maxHitPower = 86;
    int minHitPower = 79;
    this.hitPower = rand.nextInt(minHitPower, maxHitPower);

    int maxPursuit = 90;
    int minPursuit = 77;
    this.pursuit = rand.nextInt(minPursuit, maxPursuit);

    int maxQuickness = 89;
    int minQuickness = 84;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);

  }


  private void getZoneHawkStats() {
    Random rand = new Random();
    int maxHeight = 74;
    int minHeight = 70;
    this.heightIn = rand.nextInt(minHeight, maxHeight);

    int maxWeight = 214;
    int minWeight = 190;
    this.weight = rand.nextInt(minWeight, maxWeight);

    int maxSpeed = 94;
    int minSpeed = 89;
    this.speed = rand.nextInt(minSpeed, maxSpeed);

    int maxCatching = 83;
    int minCatching = 70;
    this.catching = rand.nextInt(minCatching, maxCatching);

    int maxManCoverage = 80;
    int minManCoverage = 68;
    this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

    int maxZoneCoverage = 94;
    int minZoneCoverage = 72;
    this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

    int maxTackle = 65;
    int minTackle = 57;
    this.tackle = rand.nextInt(minTackle, maxTackle);

    int maxHitPower = 75;
    int minHitPower = 64;
    this.hitPower = rand.nextInt(minHitPower, maxHitPower);

    int maxPursuit = 76;
    int minPursuit = 67;
    this.pursuit = rand.nextInt(minPursuit, maxPursuit);

    int maxQuickness = 93;
    int minQuickness = 87;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);
  }

  private int getOverall(){
    int overall = (int)(passRush *catchingWeight + manCoverage *manCoverageWeight + strength *zoneCoverageWeight + speed*speedWeight + quickness*quicknessWeight + tackle*tackleWeight + hitPowerWeight*hitPower + pursuitWeight*pursuit);
    return overall;
  }



  public void printStats(){
    System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nPosition: " + position + "\nPlayer Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nCatching: " + passRush + "\nMan Coverage: " + manCoverage
        + "\nZone Coverage: " + strength + "\nTackle: " + tackle);
  }


}
