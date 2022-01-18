import java.util.*;

public class Player {
    public String firstName;
    public String lastName;
    public String position;
    public boolean isInjured;
    public Team team;
    public int weight;
    boolean isDraftProspect;
    public int heightIn;
    public int years; // 0 = Rookie, 1 = Second year, 2 = third year
    public int age;

    //Race weights taken from http://www.profootballlogic.com/articles/nfl-census-2016/
    public int race; // 0 - white, 1 - black, 2 - hispanic, 3 - pacific islander
    public int whiteWeight;
    public int blackWeight;
    public int hispanicWeight;
    public int islanderWeight;


    public String playerType;

    //player attributes
    public int overall;
    public int speed;
    public int quickness;
    public int strength;
    public int passBlock;
    public int runBlock;
    public int breakTackle;
    public int elusiveness;
    public int fieldVision;
    public int catching;
    public int routeRunning;
    public int blocking;
    public int throwPower;
    public int throwAccuracy;
    public int poise;
    public int potential;

    public int tackle;
    public int zoneCoverage;
    public int manCoverage;
    public int hitPower;
    public int pursuit;
    final public String [] letterGrades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F+", "F", "F-"};

    //stats
    public int recDrops;
    public int recCatches;
    public int rushAttempts;
    public int rushYards;
    public int recYards;
    public int recTDs;
    public int rushTDs;
    public int passYards;
    public int passTDs;
    public int passAttempts;
    public int passCompletions;
    public int passInterceptions;

    public int defenseTackles;
    public int defensePBUs;
    public int defenseInterceptions;

    public HashMap<String, Integer> gameStats;
    public HashMap<String, Integer> seasonStats;

    /**
     * 0 = White, 1 = Black
     * @return
     */
    public int findRace(){
        int larger = whiteWeight;
        if (whiteWeight < blackWeight) larger = blackWeight;

        Random rand = new Random();
        int num = rand.nextInt(0, 100);
        if (num <= larger) return 1;
        return 0;
    }

    public void getNames(){
        Random rand = new Random();
        int numNames = 270;

        List nameList;
        switch (race){
            case 0 -> nameList = NameFile.firstLinesWhite;
            case 1 -> nameList = NameFile.firstLinesBlack;
            default -> nameList = NameFile.firstLinesWhite;
        }
        //Using the random number, get the name
        int randInt = rand.nextInt(numNames);
        List firstNames = (List) nameList.get(randInt);
        firstName = (String) firstNames.get(0);

        int randIntLast = rand.nextInt(1799);
        List lastNameList = NameFile.lastLines.get(randIntLast);
        lastName = ((String) lastNameList.get(0)).toLowerCase();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
    }


    public String getRace() {

        return switch (race) {
            case 0 -> "White";
            case 1 -> "Black";
            default -> "White";
        };
    }

    String inToFt(int inches){
        int feet = inches / 12;
        int in = inches % 12;
        return feet + "'" + in + "\"";
    }

    public void printStats(){
        System.out.println("Name: " + firstName + " " + lastName + "\nRace: " + getRace() + "\nTeam: " + team + "\nPosition: " + position + "\nPlayer Archetype: " + playerType + "\nOverall: " + overall + "\nHeight: " + inToFt(heightIn) + "\nWeight: " + weight + " lbs.\nSpeed: " + speed + "\nQuickness: " + quickness + "\nStrength: " + strength + "\nTrucking: " + breakTackle + "\nElusiveness: " + elusiveness + "\nCatching: " + catching + "\n");

    }

    private void statSetup() {

        gameStats.put("Rushes", 0);
        gameStats.put("Rush Yards", 0);
        gameStats.put("Rush TDs", 0);
        gameStats.put("Catches", 0);
        gameStats.put("Rec Yards", 0);
        gameStats.put("Rec TDs", 0);
        gameStats.put("Drops", 0);

        //eventually move season stats to new method
        seasonStats.put("Rushes", 0);
        seasonStats.put("Rush Yards", 0);
        seasonStats.put("Rush TDs", 0);
        seasonStats.put("Catches", 0);
        seasonStats.put("Rec Yards", 0);
        seasonStats.put("Rec TDs", 0);
        seasonStats.put("Drops", 0);

    }


}