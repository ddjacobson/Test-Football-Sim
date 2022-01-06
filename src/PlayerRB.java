import java.util.Random;

public class PlayerRB extends Player {

    public int overall;
    public String playerType;
    private static final String [] playerTypes = {"Bulldozer", "Elusive ", "Speed", "All-Purpose", "One Cut"};
    private static final int numTypes = playerTypes.length;

    //Ratings
    public int trucking;
    public int elusiveness;
    public int catching;

    //Rating weights
    final public double truckingWeight = 0.15;
    final public double elusivenessWeight = 0.15;
    final public double catchingWeight = 0.10;
    final public double speedWeight = 0.30;
    final public double strengthWeight = 0.10;
    final public double quicknessWeight = 0.20;


    public PlayerRB(Team t){
        team = t;

        position = "Running Back";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];
        
        switch (this.playerType){
            case "Bulldozer" -> getBulldozerStats();
            case "Elusive" -> getElusiveStats();
            case "Speed" -> getSpeedStats();
            case "All-Purpose" -> getAllPurposeStats();
            case "One Cut" -> getOneCutStats();
        }
        this.overall = getOverall();

    }

    private int getOverall() {
        int overall = (int) (trucking*truckingWeight + elusiveness*elusivenessWeight + catching*catchingWeight + speedWeight*speed + strength*strengthWeight + quickness*quicknessWeight);
        return overall;
    }

    private void getOneCutStats() {
        Random rand = new Random();
        int maxHeight = 74;
        int minHeight = 69;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 215;
        int minWeight = 185;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 93;
        int minSpeed = 85;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxTrucking = 86;
        int minTrucking = 68;
        this.trucking = rand.nextInt(minTrucking, maxTrucking);

        int maxElusiveness = 90;
        int minElusiveness = 75;
        this.elusiveness = rand.nextInt(minElusiveness, maxElusiveness);

        int maxQuickness = 92;
        int minQuickness = 84;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 80;
        int minStrength = 64;
        this.strength = rand.nextInt(minStrength, maxStrength);

        int maxCatching = 75;
        int minCatching = 63;
        this.catching = rand.nextInt(minCatching, maxCatching);

    }

    private void getAllPurposeStats() {
        Random rand = new Random();
        int maxHeight = 73;
        int minHeight = 67;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 210;
        int minWeight = 170;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 94;
        int minSpeed = 85;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxTrucking = 75;
        int minTrucking = 62;
        this.trucking = rand.nextInt(minTrucking, maxTrucking);

        int maxElusiveness = 93;
        int minElusiveness = 75;
        this.elusiveness = rand.nextInt(minElusiveness, maxElusiveness);

        int maxQuickness = 92;
        int minQuickness = 84;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 73;
        int minStrength = 55;
        this.strength = rand.nextInt(minStrength, maxStrength);

        int maxCatching = 82;
        int minCatching = 68;
        this.catching = rand.nextInt(minCatching, maxCatching);

    }

    private void getSpeedStats() {
        Random rand = new Random();
        int maxHeight = 73;
        int minHeight = 68;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 213;
        int minWeight = 180;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 97;
        int minSpeed = 89;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxTrucking = 73;
        int minTrucking = 50;
        this.trucking = rand.nextInt(minTrucking, maxTrucking);

        int maxElusiveness = 88;
        int minElusiveness = 69;
        this.elusiveness = rand.nextInt(minElusiveness, maxElusiveness);

        int maxQuickness = 95;
        int minQuickness = 85;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 70;
        int minStrength = 50;
        this.strength = rand.nextInt(minStrength, maxStrength);

        int maxCatching = 71;
        int minCatching = 58;
        this.catching = rand.nextInt(minCatching, maxCatching);

    }

    private void getElusiveStats() {
        Random rand = new Random();
        int maxHeight = 72;
        int minHeight = 67;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 215;
        int minWeight = 175;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 93;
        int minSpeed = 85;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxTrucking = 75;
        int minTrucking = 59;
        this.trucking = rand.nextInt(minTrucking, maxTrucking);

        int maxElusiveness = 95;
        int minElusiveness = 83;
        this.elusiveness = rand.nextInt(minElusiveness, maxElusiveness);

        int maxQuickness = 92;
        int minQuickness = 85;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 75;
        int minStrength = 59;
        this.strength = rand.nextInt(minStrength, maxStrength);

        int maxCatching = 80;
        int minCatching = 66;
        this.catching = rand.nextInt(minCatching, maxCatching);


    }

    private void getBulldozerStats() {
        Random rand = new Random();
        int maxHeight = 76;
        int minHeight = 70;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 240;
        int minWeight = 215;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 90;
        int minSpeed = 81;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxTrucking = 94;
        int minTrucking = 83;
        this.trucking = rand.nextInt(minTrucking, maxTrucking);

        int maxElusiveness = 82;
        int minElusiveness = 65;
        this.elusiveness = rand.nextInt(minElusiveness, maxElusiveness);

        int maxQuickness = 86;
        int minQuickness = 73;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 87;
        int minStrength = 75;
        this.strength = rand.nextInt(minStrength, maxStrength);

        int maxCatching = 69;
        int minCatching = 54;
        this.catching = rand.nextInt(minCatching, maxCatching);

    }



}
