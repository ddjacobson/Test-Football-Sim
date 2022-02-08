
package World;
import java.util.Random;

public class PlayerDE extends Player{

  private static final String [] playerTypes = {"Power Rusher", "Run Stopper", "Speed Rusher"};
  private static final int numTypes = playerTypes.length;


  final public double passRushWeight = 0.20;
  final public double runStopWeight = 0.20;
  final public double strengthWeight = 0.10;
  final public double tackleWeight = 0.10;
  final public double pursuitWeight = 0.10;
  final public double hitPowerWeight = 0.10;
  final public double speedWeight = 0.10;
  final public double quicknessWeight = 0.10;



  public PlayerDE(Team t){
    whiteWeight = 35;
    blackWeight = 65;

    this.race = findRace();
    getNames();

    this.team = t;
    position = "Defensive End";
    this.playerType = playerTypes[(int) (Math.random() * numTypes)];

    switch (this.playerType){
      case "Power Rusher" -> getPowerRusherStats();
      case "Run Stopper" -> getRunStopperStats();
      case "Speed Rusher" -> getSpeedRusherStats();

    }
    this.overall = getOverall();

  }

  public PlayerDE(){
    whiteWeight = 35;
    blackWeight = 65;

    this.race = findRace();
    getNames();


    position = "Defensive End";
    this.playerType = playerTypes[(int) (Math.random() * numTypes)];

    switch (this.playerType){
      case "Power Rusher" -> getPowerRusherStats();
      case "Run Stopper" -> getRunStopperStats();
      case "Speed Rusher" -> getSpeedRusherStats();

    }
    this.overall = getOverall();
  }

  private void getSpeedRusherStats() {

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
    this.manCoverage = rand.nextInt(minRunStop, maxRunStop);

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
    int maxHeight = 79;
    int minHeight = 74;
    this.heightIn = rand.nextInt(minHeight, maxHeight);

    int maxWeight = 305;
    int minWeight = 260;
    this.weight = rand.nextInt(minWeight, maxWeight);

    int maxSpeed = 81;
    int minSpeed = 65;
    this.speed = rand.nextInt(minSpeed, maxSpeed);

    int maxPassRush = 83;
    int minPassRush = 68;
    this.passRush = rand.nextInt(minPassRush, maxPassRush);

    int maxRunStop = 92;
    int minRunStop = 76;
    this.manCoverage = rand.nextInt(minRunStop, maxRunStop);

    int maxStrength = 92;
    int minStrength = 83;
    this.strength = rand.nextInt(minStrength, maxStrength);

    int maxTackle = 90;
    int minTackle = 78;
    this.tackle = rand.nextInt(minTackle, maxTackle);

    int maxHitPower = 86;
    int minHitPower = 69;
    this.hitPower = rand.nextInt(minHitPower, maxHitPower);

    int maxPursuit = 83;
    int minPursuit = 70;
    this.pursuit = rand.nextInt(minPursuit, maxPursuit);

    int maxQuickness = 77;
    int minQuickness = 65;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);

  }


  private void getPowerRusherStats() {
    Random rand = new Random();
    int maxHeight = 79;
    int minHeight = 73;
    this.heightIn = rand.nextInt(minHeight, maxHeight);

    int maxWeight = 280;
    int minWeight = 245;
    this.weight = rand.nextInt(minWeight, maxWeight);

    int maxSpeed = 81;
    int minSpeed = 73;
    this.speed = rand.nextInt(minSpeed, maxSpeed);

    int maxPassRush = 86;
    int minPassRush = 77;
    this.passRush = rand.nextInt(minPassRush, maxPassRush);

    int maxRunStop = 84;
    int minRunStop = 71;
    this.manCoverage = rand.nextInt(minRunStop, maxRunStop);

    int maxStrength = 88;
    int minStrength = 78;
    this.strength = rand.nextInt(minStrength, maxStrength);

    int maxTackle = 85;
    int minTackle = 72;
    this.tackle = rand.nextInt(minTackle, maxTackle);

    int maxHitPower = 84;
    int minHitPower = 73;
    this.hitPower = rand.nextInt(minHitPower, maxHitPower);

    int maxPursuit = 83;
    int minPursuit = 72;
    this.pursuit = rand.nextInt(minPursuit, maxPursuit);

    int maxQuickness = 83;
    int minQuickness = 72;
    this.quickness = rand.nextInt(minQuickness, maxQuickness);

  }

  private int getOverall(){
    int overall = (int)(passRush * passRushWeight + manCoverage * runStopWeight + strength * strengthWeight + speed*speedWeight + quickness*quicknessWeight + tackle*tackleWeight + hitPowerWeight*hitPower + pursuitWeight*pursuit);
    return overall;
  }



  public void printStats(){
    System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nPosition: " + position + "\nPlayer Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nCatching: " + passRush + "\nMan Coverage: " + manCoverage
        + "\nZone Coverage: " + strength + "\nTackle: " + tackle);
  }


}
