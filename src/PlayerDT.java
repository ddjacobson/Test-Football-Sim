import java.util.Random;

public class PlayerDT extends Player{

    private static final String [] playerTypes = {"Nose Tackle", "Run Stopper", "Pass Rusher"};
    private static final int numTypes = playerTypes.length;


    final public double passRushWeight = 0.20;
    final public double runStopWeight = 0.20;
    final public double strengthWeight = 0.10;
    final public double tackleWeight = 0.10;
    final public double pursuitWeight = 0.10;
    final public double hitPowerWeight = 0.10;
    final public double speedWeight = 0.10;
    final public double quicknessWeight = 0.10;



    public PlayerDT(Team t){
        whiteWeight = 15;
        blackWeight = 75;

        this.race = findRace();
        getNames();

        this.team = t;
        position = "Defensive Tackle";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "Nose Tackle" -> getNoseTackleStats();
            case "Run Stopper" -> getRunStopperStats();
            case "Pass Rusher" -> getPassRusherStats();

        }
        this.overall = getOverall();

    }

    public PlayerDT(){
        whiteWeight = 15;
        blackWeight = 75;

        this.race = findRace();
        getNames();


        position = "Defensive Tackle";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "Nose Tackle" -> getNoseTackleStats();
            case "Run Stopper" -> getRunStopperStats();
            case "Pass Rusher" -> getPassRusherStats();

        }
        this.overall = getOverall();
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
        this.manCoverage = rand.nextInt(minRunStop, maxRunStop);

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
        this.manCoverage = rand.nextInt(minRunStop, maxRunStop);

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

    private int getOverall(){
        int overall = (int)(passRush * passRushWeight + manCoverage * runStopWeight + strength * strengthWeight + speed*speedWeight + quickness*quicknessWeight + tackle*tackleWeight + hitPowerWeight*hitPower + pursuitWeight*pursuit);
        return overall;
    }



    public void printStats(){
        System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nPosition: " + position + "\nPlayer Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nCatching: " + passRush + "\nMan Coverage: " + manCoverage
            + "\nZone Coverage: " + strength + "\nTackle: " + tackle);
    }


}

