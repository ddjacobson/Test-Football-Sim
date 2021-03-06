import java.util.Random;

public class PlayerQB extends Player{

    //Player stats
    private static final String [] playerTypes = {"Dual Threat", "Pocket Passer", "Strong Arm", "Gunslinger", "Game Manager"};
    private static final int numTypes = playerTypes.length;

    public double hispanicWeight;
    public double islanderWeight;


    final public double throwPwrWeight = 0.30;
    final public double throwAccWeight = 0.30;
    final public double poiseWeight = 0.15;
    final public double speedWeight = 0.15;



    public PlayerQB(Team t){
        whiteWeight = 78;
        blackWeight = 22;


        this.team = t;
        position = "Quarterback";
        this.playerType = playerTypes[(int) (Math.random() * numTypes)];

        this.race = findRace();
        getNames();


        switch (this.playerType){
            case "Dual Threat" -> getDualThreatStats();  //dual
            case "Pocket Passer" -> getPocketPasserStats(); //pocket
            case "Strong Arm" -> getStrongArmStats(); //strong arm
            case "Gunslinger" -> getGunslingerStats(); //gunslinger
            case "Game Manager" -> getGameManagerStats(); //game manager
            default -> getDualThreatStats();
        }
        this.overall = getOverall();

    }

    private void getGameManagerStats() {
        Random rand = new Random();
        int maxHeight = 78;
        int minHeight = 72;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 230;
        int minWeight = 200;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 80;
        int minSpeed = 55;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxThrowPwr = 94;
        int minThrowPwr = 84;
        this.throwPower = rand.nextInt(minThrowPwr, maxThrowPwr);

        int maxThrowAcc = 90;
        int minThrowAcc = 75;
        this.throwAccuracy = rand.nextInt(minThrowAcc, maxThrowAcc);

        int maxPoise = 88;
        int minPoise = 70;
        this.poise = rand.nextInt(minPoise, maxPoise);

    }

    private void getGunslingerStats() {

        Random rand = new Random();
        int maxHeight = 78;
        int minHeight = 73;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 245;
        int minWeight = 205;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 84;
        int minSpeed = 60;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxThrowPwr = 97;
        int minThrowPwr = 88;
        this.throwPower = rand.nextInt(minThrowPwr, maxThrowPwr);

        int maxThrowAcc = 85;
        int minThrowAcc = 70;
        this.throwAccuracy = rand.nextInt(minThrowAcc, maxThrowAcc);

        int maxPoise = 90;
        int minPoise = 75;
        this.poise = rand.nextInt(minPoise, maxPoise);

    }

    private void getStrongArmStats() {
        Random rand = new Random();
        int maxHeight = 79;
        int minHeight = 74;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 245;
        int minWeight = 210;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 77;
        int minSpeed = 63;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxThrowPwr = 97;
        int minThrowPwr = 88;
        this.throwPower = rand.nextInt(minThrowPwr, maxThrowPwr);

        int maxThrowAcc = 85;
        int minThrowAcc = 71;
        this.throwAccuracy = rand.nextInt(minThrowAcc, maxThrowAcc);

        int maxPoise = 83;
        int minPoise = 70;
        this.poise = rand.nextInt(minPoise, maxPoise);


    }

    private void getPocketPasserStats() {

        Random rand = new Random();
        int maxHeight = 78;
        int minHeight = 72;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 245;
        int minWeight = 195;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 75;
        int minSpeed = 50;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxThrowPwr = 94;
        int minThrowPwr = 84;
        this.throwPower = rand.nextInt(minThrowPwr, maxThrowPwr);

        int maxThrowAcc = 88;
        int minThrowAcc = 75;
        this.throwAccuracy = rand.nextInt(minThrowAcc, maxThrowAcc);

        int maxPoise = 80;
        int minPoise = 65;
        this.poise = rand.nextInt(minPoise, maxPoise);

    }


    private void getDualThreatStats() {
        Random rand = new Random();
        int maxHeight = 77;
        int minHeight = 70;
        this.heightIn = rand.nextInt(minHeight, maxHeight);

        int maxWeight = 240;
        int minWeight = 180;
        this.weight = rand.nextInt(minWeight, maxWeight);

        int maxSpeed = 95;
        int minSpeed = 80;
        this.speed = rand.nextInt(minSpeed, maxSpeed);

        int maxThrowPwr = 94;
        int minThrowPwr = 82;
        this.throwPower = rand.nextInt(minThrowPwr, maxThrowPwr);

        int maxThrowAcc = 85;
        int minThrowAcc = 73;
        this.throwAccuracy = rand.nextInt(minThrowAcc, maxThrowAcc);

        int maxPoise = 83;
        int minPoise = 69;
        this.poise = rand.nextInt(minPoise, maxPoise);


    }

    private int getOverall(){
        int overall = (int)(throwPower*throwPwrWeight + throwAccuracy*throwAccWeight + poise*poiseWeight + speed*speedWeight);
        return overall;
    }

    @Override
    public void printStats(){
        System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nPosition: " + position + "\nPlayer Archetype: " + playerType + "\nTeam: " + team.name + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nArm Strength: " + throwPower + "\nThrow Accuracy: " + throwAccuracy + "\nPoise: " + poise + "\n");
    }


}

