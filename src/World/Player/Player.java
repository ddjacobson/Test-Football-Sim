//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package World.Player;

import World.NameFile;
import World.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Player {

    //player bio
    public String firstName;
    public String lastName;
    public String fullName;
    public String position;
    public boolean isInjured;
    public Team team;
    public int weight;
    public int zoneCoverage;
    boolean isDraftProspect;
    public int heightIn;
    public int years;
    public int age;
    public int race;
    public int whiteWeight;
    public int blackWeight;
    public int hispanicWeight;
    public int islanderWeight;
    public String playerType;


    public int overall;
    public int speed;
    public int quickness;
    public int strength;
    public int fatigue;

    // offense attributes
    public int trucking;
    public int elusiveness;
    public int passBlock;
    public int runBlock;
    public int breakTackle;
    public int routeRunning;
    public int blocking;
    public int throwPower;
    public int throwAccuracy;
    public int poise;
    public int potential;
    public int catching;

    // defense attributes
    public int tackle;
    public int manCoverage;
    public int hitPower;
    public int runStop;
    public int passRush;
    public int pursuit;

    public final String[] letterGrades;
    public int[][][] gameStats;
    public int[][] seasonStats;

    //INDICIES
    final public static int PASS = 0;
    final public static int RUSH = 1;
    final public static int REC = 2;
    final public static int DEF = 3;

    //PASS VARS
    final public static int COMP = 0;
    final public static int ATTMP = 1;
    final public static int PYARDS = 2;
    final public static int PTDS = 3;
    final public static int PINTS = 4;
    // RUSH VARS
    final public static int CARRIES = 0;
    final public static int RSHYARDS = 1;
    final public static int RSHTDS = 2;
    final public static int FMBLS = 3;
    // REC VARS
    final public static int CATCHES = 0;
    final public static int RECYARDS = 1;
    final public static int RECTDS = 2;
    final public static int DROPS = 3;
    //DEF VARS
    final public static int TCKLS = 0;
    final public static int SACKS = 1;
    final public static int FFMBLS = 2;
    final public static int INTS = 3;



    public int gameSnaps;

    // offense stats
    public int recDrops;
    public int recCatches;
    public int rushAttempts;
    public int rushYards;
    public int fumbles;
    public int recYards;
    public int recTDs;
    public int rushTDs;
    public int passYards;
    public int passTDs;
    public int passAttempts;
    public int passCompletions;
    public int passInterceptions;
    public int brokenTackles;

    // defense stats
    public int defenseTackles;
    public int defensePBUs;
    public int defenseInterceptions;
    public double defenseSacks;
    public int defenseMissedTackles;
    public int seasonRecCatches;
    public int seasonRecDrops;
    public int seasonRushAttempts;
    public int seasonRushYards;
    public int seasonFumbles;
    public int seasonRecYards;
    public int seasonRecTDs;
    public int seasonRushTDs;
    public int seasonPassYards;
    public int seasonPassTDs;
    public int seasonPassAttempts;
    public int seasonPassCompletions;
    public int seasonPassInterceptions;
    public int seasonBrokenTackles;
    public int seasonDefenseTackles;
    public int seasonDefenseMissedTackles;
    public int seasonDefensePBUs;
    public int seasonDefenseInterceptions;
    public double seasonDefenseSacks;

    public Player() {
        this.fullName = this.firstName + " " + this.lastName;
        gameStats = new int[17][4][6];
        seasonStats = new int[4][6];
        this.letterGrades = new String[]{"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F+", "F", "F-"};
    }

    public int findRace() {
        int larger = this.whiteWeight;
        if (this.whiteWeight < this.blackWeight) {
            larger = this.blackWeight;
        }

        Random rand = new Random();
        int num = rand.nextInt(0, 100);
        return num <= larger ? 1 : 0;
    }

    public void getNames() {
        Random rand = new Random();
        int numNames = 270;
        List nameList;
        switch(this.race) {
            case 0:
                nameList = NameFile.firstLinesWhite;
                break;
            case 1:
                nameList = NameFile.firstLinesBlack;
                break;
            default:
                nameList = NameFile.firstLinesWhite;
        }

        int randInt = rand.nextInt(numNames);
        List firstNames = (List)nameList.get(randInt);
        this.firstName = (String)firstNames.get(0);
        int randIntLast = rand.nextInt(1799);
        List lastNameList = NameFile.lastLines.get(randIntLast);
        this.lastName = ((String)lastNameList.get(0)).toLowerCase();
        String var10001 = this.lastName.substring(0, 1).toUpperCase();
        this.lastName = var10001 + this.lastName.substring(1);
    }

    public String getRace() {
        String var10000;
        switch(this.race) {
            case 0:
                var10000 = "White";
                break;
            case 1:
                var10000 = "Black";
                break;
            default:
                var10000 = "White";
        }

        return var10000;
    }

    String inToFt(int inches) {
        int feet = inches / 12;
        int in = inches % 12;
        return feet + "'" + in + "\"";
    }

    public void printBio() {
        System.out.println("Name: " + firstName + " " + this.lastName + "\nRace: " + this.getRace() +
            "\nWorld.Team: " + this.team + "\nPosition: " + this.position + "\nWorld.Player.Player Archetype: " + this.playerType + "\nOverall: " + this.overall + "\nHeight: " + this.inToFt(this.heightIn) + "\nWeight: " + this.weight + " lbs.\nSpeed: " + this.speed + "\nQuickness: " + this.quickness + "\nStrength: " + this.strength + "\nTrucking: " + this.breakTackle + "\nElusiveness: " + this.elusiveness + "\nCatching: " + this.passRush + "\n");
    }

    public void printStats(){
        System.out.println("Name: " + firstName + " " + lastName + "\nTeam: " + team +
            "\nPosition " + position + "\nPlayer Type: " + playerType + "\nHeight: " + inToFt(heightIn) +
            "\nWeight: " + weight + "\nOverall: " + this.overall);
    }


    public void printGameStats(int week){
        printStats();
    }

    public void printSeasonStats(){
    }

}
