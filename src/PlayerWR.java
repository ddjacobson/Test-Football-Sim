import java.util.Random;

public class PlayerWR extends Player{

    //Player stats
    private static final String [] playerTypes = {"Possession", "Slot", "Deep Threat", "All Purpose", "Physical"};
    private static final int numTypes = playerTypes.length;


    final public double catchingWeight = 0.20;
    final public double routeRunningWeight = 0.20;
    final public double fieldVisionWeight = 0.10;
    final public double speedWeight = 0.30;
    final public double quicknessWeight = 0.20;



    public PlayerWR(Team t){
        whiteWeight = 10;
        blackWeight = 90;

        this.race = findRace();
        getNames();

        this.team = t;
        position = "Wide Receiver";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "Possession" -> getPossessionStats();  //dual
            case "Slot" -> getSlotStats(); //pocket
            case "Deep Threat" -> getDeepThreatStats(); //strong arm
            case "All Purpose" -> getAllPurposeStats(); //gunslinger
            case "Physical" -> getPhysicalStats(); //game manager
            default -> getPossessionStats();
        }
        this.overall = getOverall();
        //System.out.println(catching);
    }

    public PlayerWR(){

        race = findRace();
        getNames();

        position = "Wide Receiver";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "Possession" -> getPossessionStats();  //dual
            case "Slot" -> getSlotStats(); //pocket
            case "Deep Threat" -> getDeepThreatStats(); //strong arm
            case "All Purpose" -> getAllPurposeStats(); //gunslinger
            case "Physical" -> getPhysicalStats(); //game manager
            default -> getPossessionStats();
        }
        this.overall = getOverall();
    }

    /**
     * Generates a first and last name based on the race and position of a player
     */



    private void getPhysicalStats() {
        Random rand = new Random();

        int maxHeight = 78;
        int minHeight = 73;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 235;
        int minWeight = 195;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 94;
        int minSpeed = 86;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 85;
        int minCatching = 72;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxRouteRunning = 78;
        int minRouteRunning = 63;
        this.routeRunning = rand.nextInt(minRouteRunning, maxRouteRunning);

        int maxFieldVision = 84;
        int minFieldVision = 70;
        this.fieldVision = rand.nextInt(minFieldVision, maxFieldVision);

        int maxQuickness = 85;
        int minQuickness = 75;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);


    }

    private void getAllPurposeStats() {

        Random rand = new Random();
        int maxHeight = 74;
        int minHeight = 66;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 220;
        int minWeight = 175;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 92;
        int minSpeed = 86;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 80;
        int minCatching = 65;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxRouteRunning = 80;
        int minRouteRunning = 68;
        this.routeRunning = rand.nextInt(minRouteRunning, maxRouteRunning);

        int maxFieldVision = 88;
        int minFieldVision = 72;
        this.fieldVision = rand.nextInt(minFieldVision, maxFieldVision);

        int maxQuickness = 93;
        int minQuickness = 87;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

    }

    private void getDeepThreatStats() {

        Random rand = new Random();
        int maxHeight = 77;
        int minHeight = 70;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 225;
        int minWeight = 175;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 98;
        int minSpeed = 89;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 85;
        int minCatching = 69;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxRouteRunning = 84;
        int minRouteRunning = 65;
        this.routeRunning = rand.nextInt(minRouteRunning, maxRouteRunning);

        int maxFieldVision = 86;
        int minFieldVision = 66;
        this.fieldVision = rand.nextInt(minFieldVision, maxFieldVision);

        int maxQuickness = 94;
        int minQuickness = 80;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);


    }

    private void getSlotStats() {


        Random rand = new Random();
        int maxHeight = 74;
        int minHeight = 67;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 220;
        int minWeight = 180;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 92;
        int minSpeed = 83;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 87;
        int minCatching = 75;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxRouteRunning = 90;
        int minRouteRunning = 70;
        this.routeRunning = rand.nextInt(minRouteRunning, maxRouteRunning);

        int maxFieldVision = 90;
        int minFieldVision = 70;
        this.fieldVision = rand.nextInt(minFieldVision, maxFieldVision);

        int maxQuickness = 97;
        int minQuickness = 85;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

    }


    private void getPossessionStats() {

        Random rand = new Random();
        int maxHeight = 76;
        int minHeight = 70;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 225;
        int minWeight = 195;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 90;
        int minSpeed = 82;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxCatching = 95;
        int minCatching = 83;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxRouteRunning = 84;
        int minRouteRunning = 70;
        this.routeRunning = rand.nextInt(minRouteRunning, maxRouteRunning);

        int maxFieldVision = 92;
        int minFieldVision = 75;
        this.fieldVision = rand.nextInt(minFieldVision, maxFieldVision);

        int maxQuickness = 85;
        int minQuickness = 74;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);
    }

    private int getOverall(){
        int overall = (int)(catching*catchingWeight + routeRunning*routeRunningWeight + fieldVision*fieldVisionWeight + speed*speedWeight + quickness*quicknessWeight);
        return overall;
    }



    public void printStats(){
        System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nPosition: " + position + "\nPlayer Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nCatching: " + catching + "\nRoute Running: " + routeRunning + "\nField Vision: " + fieldVision);
    }


}

