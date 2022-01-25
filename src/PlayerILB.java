import java.util.Random;

public class PlayerILB extends Player{

    private static final String [] playerTypes = {"Thumper", "Coverage", "Hybrid", "Balanced"};
    private static final int numTypes = playerTypes.length;


    final public double catchingWeight = 0.05;
    final public double manCoverageWeight = 0.20;
    final public double zoneCoverageWeight = 0.20;
    final public double tackleWeight = 0.05;
    final public double speedWeight = 0.25;
    final public double quicknessWeight = 0.25;



    public PlayerILB(Team t){
        whiteWeight = 45;
        blackWeight = 55;

        this.race = findRace();
        getNames();

        this.team = t;
        position = "Inside Linebacker";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "Thumper" -> getThumperStats();
            case "Coverage" -> getCoverageStats();
            case "Hybrid" -> getHybridStats();
            case "Balanced" -> getBalancedStats();

        }
        this.overall = getOverall();

    }

    public PlayerILB(){
        whiteWeight = 45;
        blackWeight = 55;

        this.race = findRace();
        getNames();

        position = "Inside Linebacker";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "Thumper" -> getThumperStats();
            case "Coverage" -> getCoverageStats();
            case "Hybrid" -> getHybridStats();
            case "Balanced" -> getBalancedStats();

        }
        this.overall = getOverall();
    }




    private void getBalancedStats() {

        Random rand = new Random();
        int maxHeight = 75;
        int minHeight = 73;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 250;
        int minWeight = 230;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 85;
        int minSpeed = 79;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 65;
        int minCatching = 48;
        this.passRush = rand.nextInt(minCatching, maxCatching);

        int maxManCoverage = 69;
        int minManCoverage = 60;
        this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

        int maxZoneCoverage = 72;
        int minZoneCoverage = 64;
        this.strength = rand.nextInt(minZoneCoverage, maxZoneCoverage);

        int maxTackle = 88;
        int minTackle = 80;
        this.tackle = rand.nextInt(minTackle, maxTackle);

        int maxQuickness = 85;
        int minQuickness = 77;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxPursuit = 85;
        int minPursuit = 77;
        this.pursuit = rand.nextInt(minPursuit, maxPursuit);

        int maxHitPower = 81;
        int minHitPower = 73;
        this.hitPower = rand.nextInt(minHitPower, maxHitPower);


    }

    private void getHybridStats() {

        Random rand = new Random();
        int maxHeight = 75;
        int minHeight = 72;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 240;
        int minWeight = 220;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 90;
        int minSpeed = 85;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 74;
        int minCatching = 66;
        this.passRush = rand.nextInt(minCatching, maxCatching);

        int maxManCoverage = 73;
        int minManCoverage = 63;
        this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

        int maxZoneCoverage = 80;
        int minZoneCoverage = 69;
        this.strength = rand.nextInt(minZoneCoverage, maxZoneCoverage);

        int maxTackle = 84;
        int minTackle = 74;
        this.tackle = rand.nextInt(minTackle, maxTackle);

        int maxQuickness = 90;
        int minQuickness = 83;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxPursuit = 85;
        int minPursuit = 77;
        this.pursuit = rand.nextInt(minPursuit, maxPursuit);

        int maxHitPower = 81;
        int minHitPower = 73;
        this.hitPower = rand.nextInt(minHitPower, maxHitPower);
    }

    private void getCoverageStats() {


        Random rand = new Random();
        int maxHeight = 74;
        int minHeight = 71;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 245;
        int minWeight = 230;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 87;
        int minSpeed = 83;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 67;
        int minCatching = 58;
        this.passRush = rand.nextInt(minCatching, maxCatching);

        int maxManCoverage = 76;
        int minManCoverage = 66;
        this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

        int maxZoneCoverage = 80;
        int minZoneCoverage = 70;
        this.strength = rand.nextInt(minZoneCoverage, maxZoneCoverage);

        int maxTackle = 82;
        int minTackle = 69;
        this.tackle = rand.nextInt(minTackle, maxTackle);

        int maxQuickness = 88;
        int minQuickness = 80;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxPursuit = 85;
        int minPursuit = 75;
        this.pursuit = rand.nextInt(minPursuit, maxPursuit);

        int maxHitPower = 80;
        int minHitPower = 71;
        this.hitPower = rand.nextInt(minHitPower, maxHitPower);

    }


    private void getThumperStats() {

        Random rand = new Random();
        int maxHeight = 76;
        int minHeight = 74;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 260;
        int minWeight = 240;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 83;
        int minSpeed = 74;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 59;
        int minCatching = 44;
        this.passRush = rand.nextInt(minCatching, maxCatching);

        int maxManCoverage = 68;
        int minManCoverage = 45;
        this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

        int maxZoneCoverage = 70;
        int minZoneCoverage = 59;
        this.strength = rand.nextInt(minZoneCoverage, maxZoneCoverage);

        int maxTackle = 89;
        int minTackle = 80;
        this.tackle = rand.nextInt(minTackle, maxTackle);

        int maxQuickness = 81;
        int minQuickness = 72;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxPursuit = 85;
        int minPursuit = 77;
        this.pursuit = rand.nextInt(minPursuit, maxPursuit);

        int maxHitPower = 90;
        int minHitPower = 81;
        this.hitPower = rand.nextInt(minHitPower, maxHitPower);

    }

    private int getOverall(){
        int overall = (int)(passRush *catchingWeight + manCoverage *manCoverageWeight + strength *zoneCoverageWeight + speed*speedWeight + quickness*quicknessWeight + tackle*tackleWeight);
        return overall;
    }



    public void printStats(){
        System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nPosition: " + position + "\nPlayer Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nCatching: " + passRush + "\nMan Coverage: " + manCoverage
            + "\nZone Coverage: " + strength + "\nTackle: " + tackle);
    }


}
