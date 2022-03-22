package World.Player;

import World.Team;

import java.util.Random;

public class PlayerOT extends Player {

    private static final String [] playerTypes = {"Mauler", "Agile", "Blindside"};
    private static final int numTypes = playerTypes.length;


    final public static double passBlockWeight = 0.30;
    final public static double runBlockWeight = 0.30;
    final public static double strengthWeight = 0.20;
    final public static double quicknessWeight = 0.05;



    //Use this constructor for populating teams during league creation
    public PlayerOT(Team t){

        whiteWeight = 47;
        blackWeight = 53;

        this.race = findRace();
        getNames();


        team = t;
        position = "Offensive Tackle";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        switch (this.playerType){
            case "Mauler" -> getMaulerStats();
            case "Agile" -> getAgileStats();
            case "Blindside" -> getBlindsideStats();
        }
        this.overall = getOverall();


    }

    private int getOverall() {
        int overall = (int) ( + passBlockWeight*passBlock + runBlockWeight*runBlock + strength*strengthWeight + quickness*quicknessWeight);
        return overall;
    }

    private void getMaulerStats() {
        Random rand = new Random();
        int maxHeight = 80;
        int minHeight = 76;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 355;
        int minWeight = 320;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 65;
        int minSpeed = 54;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxPassBlock = 80;
        int minPassBlock = 69;
        this.passBlock = rand.nextInt(minPassBlock, maxPassBlock);

        int maxRunBlock = 85;
        int minRunBlock = 72;
        this.runBlock = rand.nextInt(minRunBlock, maxRunBlock);

        int maxQuickness = 69;
        int minQuickness = 50;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 97;
        int minStrength = 88;
        this.strength = rand.nextInt(minStrength, maxStrength);

    }

    private void getBlindsideStats() {
        Random rand = new Random();
        int maxHeight = 78;
        int minHeight = 75;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 325;
        int minWeight = 300;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 69;
        int minSpeed = 58;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxPassBlock = 84;
        int minPassBlock = 74;
        this.passBlock = rand.nextInt(minPassBlock, maxPassBlock);

        int maxRunBlock = 78;
        int minRunBlock = 68;
        this.runBlock = rand.nextInt(minRunBlock, maxRunBlock);

        int maxQuickness = 70;
        int minQuickness = 58;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 92;
        int minStrength = 83;
        this.strength = rand.nextInt(minStrength, maxStrength);

    }

    private void getAgileStats() {
        Random rand = new Random();
        int maxHeight = 77;
        int minHeight = 75;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 315;
        int minWeight = 295;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 73;
        int minSpeed = 67;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxPassBlock = 83;
        int minPassBlock = 74;
        this.passBlock = rand.nextInt(minPassBlock, maxPassBlock);

        int maxRunBlock = 80;
        int minRunBlock = 69;
        this.runBlock = rand.nextInt(minRunBlock, maxRunBlock);

        int maxQuickness = 78;
        int minQuickness = 67;
        this.quickness = rand.nextInt(minQuickness, maxQuickness);

        int maxStrength = 90;
        int minStrength = 80;
        this.strength = rand.nextInt(minStrength, maxStrength);

    }

    public void printStats(){
        System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nPosition: " + position + "\nWorld.Player.Player Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nStrength: " + strength + "\nPass Block: " + passBlock + "\nRun Block: " + runBlock);
    }

}
