package World;

import java.util.Random;

public class PlayerTE extends Player{

    private static final String [] playerTypes = {"H-Back", "Vertical Threat", "Blocker", "Possession"};
    private static final int numTypes = playerTypes.length;


    //Rating weights
    final public double truckingWeight = 0.05;
    final public double blockingWeight = 0.20;
    final public double catchingWeight = 0.20;
    final public double breakTackleWeight = 0.0;
    final public double speedWeight = 0.20;
    final public double strengthWeight = 0.10;
    final public double quicknessWeight = 0.10;
    final public double routeRunningWeight = 0.15;


    public PlayerTE(Team t){

        whiteWeight = 52;
        blackWeight = 48;

        this.race = findRace();
        getNames();


        team = t;
        position = "Tight End";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "H-Back" -> getHBackStats();
            case "Vertical Threat" -> getVerticalThreatStats();
            case "Blocker" -> getBlockerStats();
            case "Possession" -> getPossessionStats();
        }
        this.overall = getOverall();

    }

    private int getOverall() {
        int overall =
            (int) (routeRunning*routeRunningWeight + breakTackle*breakTackleWeight + blocking*blockingWeight + catching*catchingWeight + speedWeight*speed + strength*strengthWeight + quickness*quicknessWeight);
        return overall;
    }


    private void getPossessionStats() {
        Random rand = new Random();
        int maxHeight = 80;
        int minHeight = 75;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 275;
        int minWeight = 240;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 84;
        int minSpeed = 69;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxTrucking = 84;
        int minTrucking = 64;
        this.breakTackle = rand.nextInt(minTrucking, maxTrucking);

        int maxBlocking = 82;
        int minBlocking = 59;
        this.blocking = rand.nextInt(minBlocking, maxBlocking);

        int maxQuickness = 83;
        int minQuickness = 67;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 83;
        int minStrength = 69;
        this.strength = rand.nextInt(minStrength, maxStrength);

        int maxCatching = 87;
        int minCatching = 73;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxRouteRunning = 78;
        int minRouteRunning = 65;
        this.routeRunning = rand.nextInt(minRouteRunning, maxRouteRunning);

    }

    private void getBlockerStats() {
        Random rand = new Random();
        int maxHeight = 78;
        int minHeight = 74;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 280;
        int minWeight = 245;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 81;
        int minSpeed = 65;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxTrucking = 82;
        int minTrucking = 68;
        this.breakTackle = rand.nextInt(minTrucking, maxTrucking);

        int maxBlocking = 84;
        int minBlocking = 72;
        this.blocking = rand.nextInt(minBlocking, maxBlocking);

        int maxQuickness = 80;
        int minQuickness = 63;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 83;
        int minStrength = 72;
        this.strength = rand.nextInt(minStrength, maxStrength);

        int maxCatching = 77;
        int minCatching = 62;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxRouteRunning = 73;
        int minRouteRunning = 59;
        this.routeRunning = rand.nextInt(minRouteRunning, maxRouteRunning);

    }

    private void getVerticalThreatStats() {
        Random rand = new Random();
        int maxHeight = 79;
        int minHeight = 75;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 265;
        int minWeight = 230;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 90;
        int minSpeed = 82;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxTrucking = 77;
        int minTrucking = 62;
        this.breakTackle = rand.nextInt(minTrucking, maxTrucking);

        int maxBlocking = 73;
        int minBlocking = 57;
        this.blocking = rand.nextInt(minBlocking, maxBlocking);

        int maxQuickness = 87;
        int minQuickness = 75;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 78;
        int minStrength = 64;
        this.strength = rand.nextInt(minStrength, maxStrength);

        int maxCatching = 83;
        int minCatching = 71;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxRouteRunning = 83;
        int minRouteRunning = 67;
        this.routeRunning = rand.nextInt(minRouteRunning, maxRouteRunning);

    }

    private void getHBackStats() {
        Random rand = new Random();
        int maxHeight = 74;
        int minHeight = 72;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 245;
        int minWeight = 215;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 86;
        int minSpeed = 75;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxTrucking = 82;
        int minTrucking = 65;
        this.breakTackle = rand.nextInt(minTrucking, maxTrucking);

        int maxBlocking = 82;
        int minBlocking = 65;
        this.blocking = rand.nextInt(minBlocking, maxBlocking);

        int maxQuickness = 84;
        int minQuickness = 73;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 81;
        int minStrength = 69;
        this.strength = rand.nextInt(minStrength, maxStrength);

        int maxCatching = 75;
        int minCatching = 64;
        this.catching = rand.nextInt(minCatching, maxCatching);

        int maxRouteRunning = 78;
        int minRouteRunning = 68;
        this.routeRunning = rand.nextInt(minRouteRunning, maxRouteRunning);

    }

    public void printStats(){
        System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() +
            "\nPosition: " + position + "\nWorld.Player Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nStrength: " + strength + "\nTrucking: " + breakTackle + "\nBlocking: " + blocking + "\nCatching: " + catching + "\nRoute Running: " + routeRunning);
    }





}
