package World;

import java.util.Random;

public class PlayerCB extends Player{

  private static final String [] playerTypes = {"Nickel", "Zone", "Man", "Island"};
  private static final int numTypes = playerTypes.length;


  final public double catchingWeight = 0.05;
  final public double manCoverageWeight = 0.20;
  final public double zoneCoverageWeight = 0.20;
  final public double tackleWeight = 0.05;
  final public double speedWeight = 0.25;
  final public double quicknessWeight = 0.25;



  public PlayerCB(Team t){
    whiteWeight = 2;
    blackWeight = 98;

    this.race = findRace();
    getNames();

    this.team = t;
    position = "Cornerback";
    this.playerType = playerTypes[(int) (Math.random() * numTypes)];

    switch (this.playerType){
      case "Nickel" -> getNickelStats();
      case "Zone" -> getZoneStats();
      case "Man" -> getManStats();
      case "Island" -> getIslandStats();

    }
    this.overall = getOverall();

  }

  public PlayerCB(){

    whiteWeight = 2;
    blackWeight = 98;

    race = findRace();
    getNames();

    position = "Cornerback";
    this.playerType = playerTypes[(int) (Math.random() * numTypes)];

    switch (this.playerType){
      case "Nickel" -> getNickelStats();
      case "Zone" -> getZoneStats();
      case "Man" -> getManStats();
      case "Island" -> getIslandStats();

    }
    this.overall = getOverall();
  }

  /**
   * Generates a first and last name based on the race and position of a player
   */




  private void getIslandStats() {

    Random rand = new Random();
    int maxHeight = 74;
    int minHeight = 71;
    this.heightIn = rand.nextInt(minHeight, maxHeight);

    int maxWeight = 210;
    int minWeight = 190;
    this.weight = rand.nextInt(minWeight, maxWeight);

    int maxSpeed = 94;
    int minSpeed = 88;
    this.speed = rand.nextInt(minSpeed, maxSpeed);

    int maxCatching = 65;
    int minCatching = 48;
    this.catching = rand.nextInt(minCatching, maxCatching);

    int maxManCoverage = 88;
    int minManCoverage = 79;
    this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

    int maxZoneCoverage = 83;
    int minZoneCoverage = 72;
    this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

    int maxTackle = 69;
    int minTackle = 48;
    this.tackle = rand.nextInt(minTackle, maxTackle);

    int maxQuickness = 96;
    int minQuickness = 89;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);

  }

  private void getManStats() {

    Random rand = new Random();
    int maxHeight = 75;
    int minHeight = 71;
    this.heightIn = rand.nextInt(minHeight, maxHeight);

    int maxWeight = 210;
    int minWeight = 180;
    this.weight = rand.nextInt(minWeight, maxWeight);

    int maxSpeed = 92;
    int minSpeed = 86;
    this.speed = rand.nextInt(minSpeed, maxSpeed);

    int maxCatching = 80;
    int minCatching = 65;
    this.catching = rand.nextInt(minCatching, maxCatching);

    int maxManCoverage = 80;
    int minManCoverage = 68;
    this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

    int maxZoneCoverage = 88;
    int minZoneCoverage = 72;
    this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

    int maxTackle = 65;
    int minTackle = 47;
    this.tackle = rand.nextInt(minTackle, maxTackle);

    int maxQuickness = 93;
    int minQuickness = 87;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);


  }

  private void getZoneStats() {


    Random rand = new Random();
    int maxHeight = 73;
    int minHeight = 69;
    this.heightIn = rand.nextInt(minHeight, maxHeight);

    int maxWeight = 204;
    int minWeight = 185;
    this.weight = rand.nextInt(minWeight, maxWeight);

    int maxSpeed = 93;
    int minSpeed = 88;
    this.speed = rand.nextInt(minSpeed, maxSpeed);

    int maxCatching = 67;
    int minCatching = 55;
    this.catching = rand.nextInt(minCatching, maxCatching);

    int maxManCoverage = 80;
    int minManCoverage = 70;
    this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

    int maxZoneCoverage = 88;
    int minZoneCoverage = 72;
    this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

    int maxTackle = 72;
    int minTackle = 63;
    this.tackle = rand.nextInt(minTackle, maxTackle);

    int maxQuickness = 93;
    int minQuickness = 87;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);

  }


  private void getNickelStats() {

    Random rand = new Random();
    int maxHeight = 73;
    int minHeight = 68;
    this.heightIn = rand.nextInt(minHeight, maxHeight);

    int maxWeight = 200;
    int minWeight = 178;
    this.weight = rand.nextInt(minWeight, maxWeight);

    int maxSpeed = 93;
    int minSpeed = 87;
    this.speed = rand.nextInt(minSpeed, maxSpeed);

    int maxCatching = 74;
    int minCatching = 53;
    this.catching = rand.nextInt(minCatching, maxCatching);

    int maxManCoverage = 83;
    int minManCoverage = 75;
    this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

    int maxZoneCoverage = 83;
    int minZoneCoverage = 77;
    this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

    int maxTackle = 73;
    int minTackle = 66;
    this.tackle = rand.nextInt(minTackle, maxTackle);

    int maxQuickness = 97;
    int minQuickness = 89;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);
  }

  private int getOverall(){
    int overall =
        (int)(catching *catchingWeight + manCoverage *manCoverageWeight + zoneCoverage *zoneCoverageWeight + speed*speedWeight + quickness*quicknessWeight + tackle*tackleWeight);
    return overall;
  }



  public void printStats(){
    System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nPosition: " + position + "\nPlayer Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nCatching: " + passRush + "\nMan Coverage: " + manCoverage
        + "\nZone Coverage: " + zoneCoverage + "\nTackle: " + tackle);
  }




}



