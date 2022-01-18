import java.util.Random;

public class PlayerOLB extends Player{

    private static final String [] playerTypes = {"Coverage", "Hybrid", "Hard Hitter", "Solid Tackler"}; //add rush types later
    private static final int numTypes = playerTypes.length;



    final public double manCoverageWeight = 0.10;
    final public double zoneCoverageWeight = 0.10;
    final public double tackleWeight = 0.25;
    final public double speedWeight = 0.15;
    final public double hitPowerWeight = 0.10;
    final public double pursuitWeight = 0.15;
    final public double quicknessWeight = 0.15;



    public PlayerOLB(Team t){
        whiteWeight = 30;
        blackWeight = 70;

        this.race = findRace();
        getNames();

        this.team = t;
        position = "Outside Linebacker";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "Coverage" -> getCoverageStats();
            case "Hybrid" -> getHybridStats();
            case "Hard Hitter" -> getHardHitterStats();
            case "Solid Tackler" -> getSolidTacklerStats();

        }
        this.overall = getOverall();

    }

    public PlayerOLB(){

        whiteWeight = 30;
        blackWeight = 70;

        this.race = findRace();
        getNames();

        position = "Outside Linebacker";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "Coverage" -> getCoverageStats();
            case "Hybrid" -> getHybridStats();
            case "Hard Hitter" -> getHardHitterStats();
            case "Solid Tackler" -> getSolidTacklerStats();

        }
        this.overall = getOverall();

    }

    private void getSolidTacklerStats() {
        Random rand = new Random();
        int maxHeight = 76;
        int minHeight = 72;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 245;
        int minWeight = 225;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 84;
        int minSpeed = 78;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 59;
        int minCatching = 49;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxManCoverage = 67;
        int minManCoverage = 55;
        this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

        int maxZoneCoverage = 70;
        int minZoneCoverage = 59;
        this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

        int maxTackle = 94;
        int minTackle = 83;
        this.tackle = rand.nextInt(minTackle, maxTackle);

        int maxHitPower = 86;
        int minHitPower = 75;
        this.hitPower = rand.nextInt(minHitPower, maxHitPower);

        int maxPursuit = 90;
        int minPursuit = 79;
        this.pursuit = rand.nextInt(minPursuit, maxPursuit);

        int maxQuickness = 84;
        int minQuickness = 77;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

    }

    private void getHardHitterStats() {
        Random rand = new Random();
        int maxHeight = 76;
        int minHeight = 73;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 250;
        int minWeight = 230;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 83;
        int minSpeed = 76;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 55;
        int minCatching = 49;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxManCoverage = 69;
        int minManCoverage = 56;
        this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

        int maxZoneCoverage = 71;
        int minZoneCoverage = 63;
        this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

        int maxTackle = 90;
        int minTackle = 78;
        this.tackle = rand.nextInt(minTackle, maxTackle);

        int maxHitPower = 90;
        int minHitPower = 81;
        this.hitPower = rand.nextInt(minHitPower, maxHitPower);

        int maxPursuit = 88;
        int minPursuit = 75;
        this.pursuit = rand.nextInt(minPursuit, maxPursuit);

        int maxQuickness = 82;
        int minQuickness = 76;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);
    }

    private void getHybridStats() {
        Random rand = new Random();
        int maxHeight = 75;
        int minHeight = 73;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 235;
        int minWeight = 210;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 92;
        int minSpeed = 84;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 68;
        int minCatching = 58;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxManCoverage = 75;
        int minManCoverage = 67;
        this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

        int maxZoneCoverage = 80;
        int minZoneCoverage = 68;
        this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

        int maxTackle = 84;
        int minTackle = 68;
        this.tackle = rand.nextInt(minTackle, maxTackle);

        int maxHitPower = 83;
        int minHitPower = 66;
        this.hitPower = rand.nextInt(minHitPower, maxHitPower);

        int maxPursuit = 84;
        int minPursuit = 75;
        this.pursuit = rand.nextInt(minPursuit, maxPursuit);

        int maxQuickness = 90;
        int minQuickness = 83;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);
    }


    private void getCoverageStats() {
        Random rand = new Random();
        int maxHeight = 75;
        int minHeight = 71;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 245;
        int minWeight = 225;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 87;
        int minSpeed = 82;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 75;
        int minCatching = 65;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxManCoverage = 77;
        int minManCoverage = 70;
        this.manCoverage = rand.nextInt(minManCoverage, maxManCoverage);

        int maxZoneCoverage = 83;
        int minZoneCoverage = 77;
        this.zoneCoverage = rand.nextInt(minZoneCoverage, maxZoneCoverage);

        int maxTackle = 83;
        int minTackle = 72;
        this.tackle = rand.nextInt(minTackle, maxTackle);

        int maxHitPower = 82;
        int minHitPower = 70;
        this.hitPower = rand.nextInt(minHitPower, maxHitPower);

        int maxPursuit = 87;
        int minPursuit = 73;
        this.pursuit = rand.nextInt(minPursuit, maxPursuit);

        int maxQuickness = 88;
        int minQuickness = 79;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);    }

    private int getOverall(){
        int overall = (int)(hitPowerWeight*hitPower + pursuitWeight*pursuit + manCoverage*manCoverageWeight + zoneCoverage*zoneCoverageWeight + speed*speedWeight + quickness*quicknessWeight + tackle*tackleWeight);
        return overall;
    }



    public void printStats(){
        System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nPosition: " + position + "\nPlayer Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nPursuit: " + pursuit + "\nHit Power: " + hitPower + "\nMan Coverage: " + manCoverage + "\nZone Coverage: " + zoneCoverage + "\nTackle: " + tackle);
    }


}