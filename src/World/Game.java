//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package World;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    public Team homeTeam;
    public Team awayTeam;
    public String gameLog;

    public int qtr;
    public int homeScore;
    public int awayScore;
    public boolean isByeWeek;

    //ingame variables

    private int time;
    private int qtrTime;
    private int yardLine;
    private int down;
    private int yardsNeeded;
    private String downDist;
    private boolean hasPossession; //true = home, must get ball to the 0 for TD, false = away


    //initalize home stats
    int totalPassPlaysHome = 0;
    int totalRunPlaysHome = 0;
    int totalPassYardsHome = 0;
    int teamDropsHome = 0;
    int totalPlaysHome = 0;
    int wr1TargetsHome = 0;
    int wr2TargetsHome = 0;
    int wr3Targets = 0;
    int teTargets = 0;
    int rbTargets = 0;




    public Game(Team home, Team away) {
        homeTeam = home;
        awayTeam = away;

        qtr = 1;
        homeScore = 0;
        awayScore = 0;


        if (away.name.equals("BYE")){
            isByeWeek = true;
        }

    }

    public void playGame(){
        gameLog += "\n" +  homeTeam.wins + "-" + homeTeam.losses + " " + homeTeam.city + " " + homeTeam.name + " vs. " + awayTeam.wins + "-" + " " + awayTeam.losses + awayTeam.city + " " +
            awayTeam.name + "\n";

        System.out.println(gameLog);
        time = 3600; //60 minute football game in seconds
        qtrTime = 900;
        Team kickingTeam;
        Team receivingTeam;
        if (coinFlip() == 0){
            hasPossession = true;
            gameLog += "The " + homeTeam.name + " have won the coin toss! They will receive the "
                + "ball.\n\n";
            kickingTeam = awayTeam;
            receivingTeam = homeTeam;
        }
        else {
            hasPossession = false;
            gameLog += "The " + awayTeam.name + " have won the coin toss! They will receive the "
                + "ball.\n\n";
            kickingTeam = homeTeam;
            receivingTeam = awayTeam;
        }

        kickOff(receivingTeam, kickingTeam);

        while (time>0){

            if (down > 4) { // turnover on downs

                gameLog += "Turnover on downs!\n";
                changePossession();
                down = 1;
                yardsNeeded = 10;
            }

            if (hasPossession){
                runPlay(homeTeam, awayTeam); //home team has possession, they are on offense
            }
            else runPlay(awayTeam, homeTeam);
        }

        System.out.println("Game finished\n\n" + homeTeam.name + " - " + homeTeam.gameScore + "\n" + awayTeam.name + " - " + awayTeam.gameScore);
        System.out.println("Yards/pass: " + (double) totalPassYardsHome / totalPassPlaysHome);
        System.out.println("Team drops: " + teamDropsHome);
        System.out.println("Total plays: " + totalPlaysHome);
        System.out.println("Total pass plays" + totalPassPlaysHome);
        printFinalStats();

        endGame();
        System.out.println(gameLog);
    }

    /**
     * Collects stats, updates records, etc.
     */
    private void endGame() {
        //record updating
        if (homeScore > awayScore){
            homeTeam.wins++;
            awayTeam.losses++;
        } else{
            homeTeam.losses++;
            awayTeam.wins++;
        }
        gameLog += "\nFinal Score: \n" + homeTeam.name + ": " + homeTeam.gameScore + " to " + awayTeam.name + ": " + awayTeam.gameScore;


        //stat updating

        //QBs
        for (Player player : homeTeam.qbs){
            if (player.passAttempts > 0){
                //passing
                player.seasonPassAttempts += player.passAttempts;
                player.seasonPassCompletions += player.passCompletions;
                player.seasonPassInterceptions += player.passInterceptions;
                player.seasonPassYards += player.passYards;
                player.seasonPassTDs += player.passTDs;

                //rushing
                player.seasonRushAttempts += player.rushAttempts;
                player.seasonRushYards += player.rushYards;
                player.seasonRushTDs += player.rushTDs;
                player.seasonFumbles += player.fumbles;
            }
        }
        for (Player player : awayTeam.qbs){
            if (player.passAttempts > 0){
                //passing
                player.seasonPassAttempts += player.passAttempts;
                player.seasonPassCompletions += player.passCompletions;
                player.seasonPassInterceptions += player.passInterceptions;
                player.seasonPassYards += player.passYards;
                player.seasonPassTDs += player.passTDs;

                //rushing
                player.seasonRushAttempts += player.rushAttempts;
                player.seasonRushYards += player.rushYards;
                player.seasonRushTDs += player.rushTDs;
                player.seasonFumbles += player.fumbles;
            }
        }


        //RBS

        for (Player player : homeTeam.wrs){
            if (player.recCatches > 0){
                //passing
                player.seasonRecCatches += player.recCatches;
                player.seasonRecYards += player.recYards;
                player.seasonRecTDs += player.recTDs;
                player.seasonRecDrops += player.recDrops;

                //rushing
                player.seasonRushAttempts += player.rushAttempts;
                player.seasonRushYards += player.rushYards;
                player.seasonRushTDs += player.rushTDs;
                player.seasonFumbles += player.fumbles;
            }
        }

        for (Player player : awayTeam.wrs){
            if (player.recCatches > 0){
                //passing
                player.seasonRecCatches += player.recCatches;
                player.seasonRecYards += player.recYards;
                player.seasonRecTDs += player.recTDs;
                player.seasonRecDrops += player.recDrops;

                //rushing
                player.seasonRushAttempts += player.rushAttempts;
                player.seasonRushYards += player.rushYards;
                player.seasonRushTDs += player.rushTDs;
                player.seasonFumbles += player.fumbles;
            }
        }

        //TEs
        for (Player player : homeTeam.tes){
            if (player.recCatches > 0){
                //passing
                player.seasonRecCatches += player.recCatches;
                player.seasonRecYards += player.recYards;
                player.seasonRecTDs += player.recTDs;
                player.seasonRecDrops += player.recDrops;

                //rushing
                player.seasonRushAttempts += player.rushAttempts;
                player.seasonRushYards += player.rushYards;
                player.seasonRushTDs += player.rushTDs;
                player.seasonFumbles += player.fumbles;
            }
        }
        for (Player player : awayTeam.tes){
            if (player.recCatches > 0){
                //passing
                player.seasonRecCatches += player.recCatches;
                player.seasonRecYards += player.recYards;
                player.seasonRecTDs += player.recTDs;
                player.seasonRecDrops += player.recDrops;

                //rushing
                player.seasonRushAttempts += player.rushAttempts;
                player.seasonRushYards += player.rushYards;
                player.seasonRushTDs += player.rushTDs;
                player.seasonFumbles += player.fumbles;
            }
        }


        ///RBs

        for (Player player : homeTeam.rbs){
            if (player.rushAttempts > 0 || player.recCatches > 0){
                //receiving
                player.seasonRecCatches += player.recCatches;
                player.seasonRecYards += player.recYards;
                player.seasonRecTDs += player.recTDs;
                player.seasonRecDrops += player.recDrops;

                //rushing
                player.seasonRushAttempts += player.rushAttempts;
                player.seasonRushYards += player.rushYards;
                player.seasonRushTDs += player.rushTDs;
                player.seasonFumbles += player.fumbles;
            }
        }

        for (Player player : awayTeam.rbs){
            if (player.rushAttempts > 0 || player.recCatches > 0){
                //receiving
                player.seasonRecCatches += player.recCatches;
                player.seasonRecYards += player.recYards;
                player.seasonRecTDs += player.recTDs;
                player.seasonRecDrops += player.recDrops;

                //rushing
                player.seasonRushAttempts += player.rushAttempts;
                player.seasonRushYards += player.rushYards;
                player.seasonRushTDs += player.rushTDs;
                player.seasonFumbles += player.fumbles;
            }
        }


        //defense

        //DTS
        for (Player player : homeTeam.dTackles){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }
        for (Player player : awayTeam.dTackles){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }

        //DEs
        for (Player player : homeTeam.dEnds){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }
        for (Player player : awayTeam.dEnds){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }

        //ILBs
        for (Player player : homeTeam.ilbs){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }
        for (Player player : awayTeam.ilbs){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }
        //OLBs
        for (Player player : homeTeam.olbs){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }
        for (Player player : awayTeam.olbs){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }

        //CBs
        for (Player player : homeTeam.cbs){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }
        for (Player player : awayTeam.cbs){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }

        //FS
        for (Player player : homeTeam.fs){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }
        for (Player player : awayTeam.fs){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }

        //SS
        for (Player player : homeTeam.ss){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }
        for (Player player : awayTeam.ss){
            if (player.gameSnaps > 0){
                //receiving
                player.seasonDefenseTackles += player.defenseTackles;
                player.seasonDefenseInterceptions += player.defenseInterceptions;
                player.seasonDefensePBUs += player.defensePBUs;
                player.seasonDefenseSacks += player.defenseSacks;
            }
        }
    }

    private void printFinalStats(){
        System.out.println("\n-----Final Stats-----");

        System.out.println(homeTeam.name);
        for (Player player : homeTeam.qbs){
            if (player.passAttempts > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Pass Attempts: " + player.passCompletions + "/" + player.passAttempts + "\nPass Yards: " + player.passYards + "\nPass Touchdowns: " + player.passTDs + "\nInterceptions: " + player.passInterceptions + "\n");

            }
        }
        for (Player player : homeTeam.wrs){
            if (player.recCatches > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Catches: " + player.recCatches + "\nRec Yards: " + player.recYards + "\nRec Touchdowns: " + player.recTDs + "\nDrops: " + player.recDrops + "\n");
            }
        }
        for (Player player : homeTeam.tes){
            if (player.recCatches > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Catches: " + player.recCatches + "\nRec Yards: " + player.recYards + "\nRec Touchdowns: " + player.recTDs + "\nDrops: " + player.recDrops + "\n");
            }
        }
        for (Player player : homeTeam.rbs){
            if (player.rushAttempts > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Catches: " + player.recCatches + "\nRec Yards: " + player.recYards + "\nRec Touchdowns: " + player.recTDs + "\nDrops: " + player.recDrops + "\nRushes: " + player.rushAttempts + "\nRush Yards: " + player.rushYards + "\nRush TDs: " + player.rushTDs + "\n" );
            }
        }

        //----Defense----\
        for (Player player : homeTeam.dTackles){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : homeTeam.dEnds){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : homeTeam.ilbs){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : homeTeam.olbs){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : homeTeam.cbs){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }


        for (Player player : homeTeam.fs){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : homeTeam.ss){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }







        System.out.println("\n\n" + awayTeam.name + "---------------");
        for (Player player : awayTeam.qbs){
            if (player.passAttempts > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Pass Attempts: " + player.passCompletions + "/" + player.passAttempts + "\nPass Yards: " + player.passYards + "\nPass Touchdowns: " + player.passTDs + "\nInterceptions: " + player.passInterceptions + "\n");

            }
        }
        for (Player player : awayTeam.wrs){
            if (player.recCatches > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Catches: " + player.recCatches + "\nRec Yards: " + player.recYards + "\nRec Touchdowns: " + player.recTDs + "\nDrops: " + player.recDrops + "\n");
            }
        }
        for (Player player : awayTeam.tes){
            if (player.recCatches > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Catches: " + player.recCatches + "\nRec Yards: " + player.recYards + "\nRec Touchdowns: " + player.recTDs + "\nDrops: " + player.recDrops + "\n");
            }
        }
        for (Player player : awayTeam.rbs){
            if (player.rushAttempts > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("Catches: " + player.recCatches + "\nRec Yards: " + player.recYards + "\nRec Touchdowns: " + player.recTDs + "\nDrops: " + player.recDrops + "\nRushes: " + player.rushAttempts + "\nRush Yards: " + player.rushYards + "\nRush TDs: " + player.rushTDs + "\nYPC: " + ((float) player.rushYards / player.rushAttempts) + "\n");
            }
        }
        //--Defense__\\
        for (Player player : awayTeam.dTackles){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : awayTeam.dEnds){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : awayTeam.ilbs){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : awayTeam.olbs){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : awayTeam.cbs){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }


        for (Player player : awayTeam.fs){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }

        for (Player player : awayTeam.ss){
            if (player.gameSnaps > 0){
                System.out.println( player.position + " " + player.firstName + " " + player.lastName);
                System.out.println("Overall: " + player.overall);
                System.out.println("Speed: " + player.speed);
                System.out.println("\nSnaps: " + player.gameSnaps + "\nTackles: " + player.defenseTackles + "\nInterceptions: " + player.defenseInterceptions + "\nPBUs: " + player.defensePBUs + "\n");

            }
        }


    }

    private void kickOff(Team receivingTeam, Team kickingTeam) {
        //TODO: implement kickoff
        gameLog += "The " + kickingTeam.name + " kick off to the " + receivingTeam.name + "\n";

        if (hasPossession){
            yardLine = 25;
        } else {
            yardLine = 75;
        }
        down = 1;
        yardsNeeded = 10;
        changePossession();
    }

    private boolean isTouchdown(){
        if (hasPossession){
            //ball must be at 0
            return yardLine <= 0;
        } else {
            //ball must be at 100
            return yardLine >= 100;
        }
    }

    public void runPlay(Team offense, Team defense){
        //not fourth down, offense will run a play.
        String play = choosePlay(offense, defense);
        downDist = getDownDist();
        switch (play){
            case "Pass"-> runPassPlay(offense, defense);
            case "Run"-> runRushPlay(offense, defense);
            case "Punt"-> runPuntPlay(offense, defense);
            case "Field Goal"-> runFieldGoalPlay(offense, defense);
            //                case "Hail Mary" -> runHailMaryPlay(offense, defense);

        }
        if (play.equals("Pass")||play.equals("Run")){
            if (gotFirstDown()){
                down = 1;
                yardsNeeded = 10;
                gameLog += "  First Down!\n";
            } else {
                down++;
            }
        }

        qtrTime -= 28;
        time -= 28;// gets us to 150 plays/game, but we want to have dynamic clock
        // management
        checkQtrTime();
        //            checkQtr();
    }

    private boolean gotFirstDown(){
        if (yardsNeeded <= 0){
            return true;
        }
        return false;
    }

    private void spotBall(int yardsGained){
        if (hasPossession){
            yardLine -= yardsGained;

        } else {
            yardLine += yardsGained;
        }
    }


    private void runPuntPlay(Team offense, Team defense) {

        gameLog += "(" + getQtr() + " " + getQtrTime() + " " + downDist + " ball on the " + yardLine  + ") " + "The " + offense.name + " punt the ball away to the " + awayTeam.name + ". "
            + "They start their drive at the " + yardLine + "\n\n";
        down = 1;
        yardsNeeded = 10;
        if (hasPossession){
            yardLine -= 40;
            if (yardLine < 0) yardLine = 20;//touchback
        } else {
            yardLine += 40;
            if (yardLine > 100) yardLine = 80;//touchback
        }
        time -= 15;
        changePossession();
    }

    private void changePossession(){
        hasPossession = !hasPossession;
    }

    private void runFieldGoalPlay(Team offense, Team defense) {
        offense.gameScore += 3;

        gameLog += "The " + offense.name + " kick a field goal.\n";
        time -= 10;
        kickOff(defense, offense);
    }


    /**
     * This method takes care of simulating a run play
     * @param offense
     * @param defense
     */
    private void runRushPlay(Team offense, Team defense) {
        //4-3 defense
        ArrayList<PlayerDE> playDEs = new ArrayList<>(0);
        ArrayList<PlayerDT> playDTs = new ArrayList<>(0);
        ArrayList<PlayerOLB> playOLBs = new ArrayList<>(0);
        ArrayList<PlayerCB> playCBs = new ArrayList<>(0);
        PlayerILB playILB = null;
        PlayerFS playFS = null;
        PlayerSS playSS = null;


        PlayerRB playRB = null;
        for (PlayerRB rb : offense.rbs){
            if (!rb.isInjured){
                playRB = rb;
            }
        }

        for (PlayerDE defender : defense.dEnds){
            if (!defender.isInjured && playDEs.size() < 2){
                defender.gameSnaps++;
                playDEs.add(defender);
            }
        }

        for (PlayerDT defender : defense.dTackles){
            if (!defender.isInjured && playDTs.size() < 2){
                defender.gameSnaps++;
                playDTs.add(defender);
            }
        }

        for (PlayerOLB defender : defense.olbs){
            if (!defender.isInjured && playOLBs.size() < 2){
                defender.gameSnaps++;
                playOLBs.add(defender);
            }
        }

        for (PlayerILB defender : defense.ilbs){
            if (!defender.isInjured){
                defender.gameSnaps++;
                playILB = defender;
                break;
            }
        }

        for (PlayerCB defender : defense.cbs){
            if (!defender.isInjured && playCBs.size() < 2){
                defender.gameSnaps++;
                playCBs.add(defender);
            }
        }

        for (PlayerFS defender : defense.fs){
            if (!defender.isInjured && playCBs.size() < 2){
                defender.gameSnaps++;
                playFS = defender;
                break;
            }
        }

        for (PlayerSS defender : defense.ss){
            if (!defender.isInjured){
                defender.gameSnaps++;
                playSS = defender;
                break;
            }
        }

        //get defensive players
        PlayerDT playDT = playDTs.get(0);
        PlayerDT playDT2 = playDTs.get(1);
        PlayerDE playDE = playDEs.get(0);
        PlayerDE playDE2 = playDEs.get(1);
        PlayerOLB playOLB = playOLBs.get(0);
        PlayerOLB playOLB2 = playOLBs.get(1);
        PlayerCB playCB = playCBs.get(0);
        PlayerCB playCB2 = playCBs.get(1);


        playRB.rushAttempts++;
        playRB.gameSnaps++;
        Random rand = new Random();
        int yardsGained = 0;

        //get oline rating
        int oLineRating = getOlineRating(offense, "Run");
        int dlRating = getDLRating(defense, "Run");
        int rbRating = playRB.overall;
        //lb rating
        yardsGained += ((rand.nextGaussian()*oLineRating + (rbRating + playRB.speed)/2)/(dlRating/2));
        gameLog += "(" + getQtr() + " " + getQtrTime() + " " + downDist + " ball on the " + yardLine  + ") ";
        spotBall(yardsGained);

        if (isTouchdown()){
            //touchdown
            playRB.rushYards += yardLine;

            gameLog += playRB.position + " " + playRB.firstName + " " + playRB.lastName + " runs "
                + "in for a " + yardsGained + " yard touchdown!\n";

            yardLine = 0;
            playRB.rushTDs++;
            offense.gameScore += 6;
            runExtraPoint(offense, defense);

        } else {
            gameLog += playRB.team.name +
                " " + playRB.position +
                " " + playRB.firstName + " " + playRB.lastName + " runs " + "for a gain of " + yardsGained + ".\n";
            playRB.rushYards += yardsGained;
            yardLine += yardsGained;
            yardsNeeded -= yardsGained;


            int choiceDT;
            int choiceDT2;
            int choiceDE;
            int choiceDE2;
            int choiceOLB;
            int choiceOLB2;
            int choiceILB;
            int choiceCB;
            int choiceCB2;
            int choiceFS;
            int choiceSS;
            Player dFocus = null;
            //track stats

            //determine who makes the tackle
            if (yardsGained < 2){
                //one of the DL/OLBs
                choiceDT = (int) (10 + playDT.overall + (Math.random() * playDT.tackle));
                choiceDT2 = (int) (10 + playDT2.overall + (Math.random() * playDT2.tackle));
                choiceDE = (int) (5 + playDE.overall + (Math.random() * playDE.tackle));
                choiceDE2 = (int) (5 + playDE2.overall + (Math.random() * playDE2.tackle));
                choiceOLB = (int) (10 - playOLB.overall + (Math.random() * playOLB.tackle));
                choiceOLB2 = (int) (10 - playOLB2.overall + (Math.random() * playOLB2.tackle));
                choiceILB = (int) (playILB.overall + (Math.random() * playILB.tackle));




                if (choiceDT > choiceDT2 && choiceDT > choiceDE && choiceDT > choiceDE2 && choiceDT > choiceOLB && choiceDT > choiceOLB2 && choiceDT > choiceILB){
                    playDT.defenseTackles++;
                    gameLog += "  The tackle is made by " + playDT.team.name + " " + playDT.position + " " + playDT.firstName + " " + playDT.lastName + "." + "\n";
                } else if (choiceDT2 > choiceDE && choiceDT2 > choiceDE2 && choiceDT2 > choiceOLB && choiceDT > choiceOLB2 && choiceDT2 > choiceILB) {
                    playDT2.defenseTackles++;
                    gameLog += "  The tackle is made by " + playDT2.team.name + " " + playDT2.position + " " + playDT2.firstName + " " + playDT2.lastName + "." + "\n";

                } else if (choiceDE > choiceDE2 && choiceDE > choiceOLB && choiceDE > choiceOLB2 && choiceDE > choiceILB){
                    playDE.defenseTackles++;
                    gameLog += "  The tackle is made by " + playDE.team.name + " " + playDE.position + " " + playDE.firstName + " " + playDE.lastName + "." + "\n";
                } else if (choiceDE2 > choiceOLB && choiceDE2 > choiceOLB2 && choiceDE2 > choiceILB) {
                    playDE2.defenseTackles++;
                    gameLog += "  The tackle is made by " + playDE2.team.name + " " + playDE2.position + " " + playDE2.firstName + " " + playDE2.lastName + "." + "\n";
                } else if (choiceOLB > choiceOLB2 && choiceOLB > choiceILB){
                    playOLB.defenseTackles++;
                    gameLog += "  The tackle is made by " + playOLB.team.name + " " + playOLB.position + " " + playOLB.firstName + " " + playOLB.lastName + "." + "\n";
                } else if (choiceOLB2 > choiceILB){
                    playOLB2.defenseTackles++;
                    gameLog += "  The tackle is made by " + playOLB2.team.name + " " + playOLB2.position + " " + playOLB2.firstName + " " + playOLB2.lastName + "." + "\n";
                } else {
                    playILB.defenseTackles++;
                    gameLog += "  The tackle is made by " + playILB.team.name + " " + playILB.position + " " + playILB.firstName + " " + playILB.lastName + "." + "\n";

                }

            }
            else if (yardsGained < 12){
                //one of the LBS/SS
                choiceOLB = (int) (Math.random() * (playOLB.overall + playOLB.tackle));
                choiceOLB2 = (int) (Math.random() * (playOLB2.overall + playOLB2.tackle));
                choiceILB = (int) (Math.random() * (playILB.overall + playILB.tackle));
                choiceSS = (int) (Math.random() * (playSS.overall + playSS.tackle));
                System.out.println(choiceOLB + " " + choiceOLB2);

                if (choiceOLB > choiceOLB2 && choiceOLB > choiceILB && choiceOLB > choiceSS){
                    playOLB.defenseTackles++;
                    gameLog += "  The tackle is made by " + playOLB.team.name + " " + playOLB.position + " " + playOLB.firstName + " " + playOLB.lastName + "." + "\n";
                } else if (choiceOLB2 > choiceILB && choiceOLB2 > choiceSS) {
                    playOLB2.defenseTackles++;
                    gameLog += "  The tackle is made by " + playOLB2.team.name + " " + playOLB2.position + " " + playOLB2.firstName + " " + playOLB2.lastName + "." + "\n";
                } else if (choiceILB > choiceSS){
                    playILB.defenseTackles++;
                    gameLog += "  The tackle is made by " + playILB.team.name + " " + playILB.position + " " + playILB.firstName + " " + playILB.lastName + "." + "\n";

                } else {
                    playSS.defenseTackles++;
                    gameLog += "  The tackle is made by " + playSS.team.name + " " + playSS.position + " " + playSS.firstName + " " + playSS.lastName + "." + "\n";

                }
            }
            else {
                //one of the CBs/S


                System.out.println("--------------------Greater than 10 yards");
            }



        }
    }



    private int getDLRating(Team defense, String play) {
        int rating = 0;
        int lineNum = 0;

        switch (play){
            case "Run": {
                for  (PlayerDE lineman : defense.dEnds){
                    if (lineNum > 1) break; //want two DEs
                    if (!lineman.isInjured){
                        rating += lineman.runStop;
                        lineNum++;
                    }
                }

                for  (PlayerDT lineman : defense.dTackles){
                    if (lineNum > 3) break; //want two DTs
                    if (!lineman.isInjured){
                        rating += lineman.runStop;
                        lineNum++;
                    }
                }
            }
            case "Pass": {
                for  (PlayerDE lineman : defense.dEnds){
                    if (lineNum > 1) break; //want two DEs
                    if (!lineman.isInjured){
                        rating += lineman.passRush;
                        lineNum++;
                    }
                }

                for  (PlayerDT lineman : defense.dTackles){
                    if (lineNum > 3) break; //want two DTs
                    if (!lineman.isInjured){
                        rating += lineman.passRush;
                        lineNum++;
                    }
                }
            }
        }

        rating = rating/lineNum;

        return rating;

    }

    private int getLBRating(Team defense, String play) {
        int rating = 0;
        int lineNum = 0;

        switch (play){
            case "Run": {
                for  (PlayerOLB linebacker : defense.olbs){
                    if (lineNum > 1) break; //want two DEs
                    if (!linebacker.isInjured){
                        rating += linebacker.runStop;
                        lineNum++;
                    }
                }

                for  (Player lineman : defense.dTackles){
                    if (lineNum > 3) break; //want two DTs
                    if (!lineman.isInjured){
                        rating += lineman.runStop;
                        lineNum++;
                    }
                }
            }
            case "Pass": {
                for  (PlayerDE lineman : defense.dEnds){
                    if (lineNum > 1) break; //want two DEs
                    if (!lineman.isInjured){
                        rating += lineman.passRush;
                        lineNum++;
                    }
                }

                for  (PlayerDT lineman : defense.dTackles){
                    if (lineNum > 3) break; //want two DTs
                    if (!lineman.isInjured){
                        rating += lineman.passRush;
                        lineNum++;
                    }
                }
            }
        }

        rating = rating/lineNum;

        return rating;

    }




    private int getOlineRating(Team offense, String play) {
        int rating = 0;

        //TODO: add in tight end run blocking

        switch (play){
            case "Run": {
                int lineNum = 0;
                for (PlayerOT lineman : offense.tackles){
                    if (lineNum > 1) break;
                    if (!lineman.isInjured){
                        rating += lineman.runBlock;
                        lineNum++;
                    }
                }
                lineNum = 0;
                for (PlayerOG lineman : offense.guards){
                    if (lineNum > 1) break;
                    if (!lineman.isInjured){
                        rating += lineman.runBlock;
                        lineNum++;
                    }
                }
                lineNum = 0;
                for (PlayerOC lineman : offense.centers){
                    if (lineNum > 0) break;
                    if (!lineman.isInjured){
                        rating += lineman.runBlock;
                        lineNum++;
                    }
                }
            }

            case "Pass":

        }


        rating = rating/5;


        return rating;
    }


    private void runPassPlay(Team offense, Team defense) {
        //TODO: implement a playbook system to have different offensive looks
        // offense
        PlayerQB playQB = null;
        ArrayList<PlayerWR> playWRs = new ArrayList<>(0);//3wrs
        PlayerTE playTE = null;
        PlayerRB playRB = null;
        Player focus = null;

        //4-3 defense
        ArrayList<PlayerDE> playDEs = new ArrayList<>(0);
        ArrayList<PlayerDT> playDTs = new ArrayList<>(0);
        ArrayList<PlayerOLB> playOLBs = new ArrayList<>(0);
        ArrayList<PlayerCB> playCBs = new ArrayList<>(0);

        PlayerDT playDT = null;
        PlayerDT platDT2 = null;
        PlayerOLB playOLB = null;
        PlayerOLB playOLB2 = null;
        PlayerILB playILB = null;
        PlayerCB playCB = null;
        PlayerCB playCB2 = null;
        PlayerFS playFS = null;
        PlayerSS playSS = null;


        for (PlayerDE defender : defense.dEnds){
            if (!defender.isInjured && playDEs.size() < 2){
                defender.gameSnaps++;
                playDEs.add(defender);
            }
        }

        for (PlayerDT defender : defense.dTackles){
            if (!defender.isInjured && playDTs.size() < 2){
                defender.gameSnaps++;
                playDTs.add(defender);
            }
        }

        for (PlayerOLB defender : defense.olbs){
            if (!defender.isInjured && playOLBs.size() < 2){
                defender.gameSnaps++;
                playOLBs.add(defender);
            }
        }

        for (PlayerCB defender : defense.cbs){
            if (!defender.isInjured && playCBs.size() < 3){
                defender.gameSnaps++;
                playCBs.add(defender);
            }
        }

        Player dFocus = null;

        //stats
        int yardsGained = 0;


        totalPassPlaysHome++;


        //TODO: implement a collection sort to make sure best qb is at the top of the depth chart
        //TODO: implement a stamina system
        for (PlayerQB qb : offense.qbs){
            if (!qb.isInjured){
                playQB = qb;
            }
        }

        for (PlayerWR wr : offense.wrs){
            if (!wr.isInjured && playWRs.size() < 3){
                playWRs.add(wr);
            }
        }

        for (PlayerTE te : offense.tes){
            if (!te.isInjured){
                playTE = te;
            }
        }

        for (PlayerRB rb : offense.rbs){
            if (!rb.isInjured){
                playRB = rb;
            }
        }

        //get defensive players
        for (PlayerOLB olb : defense.olbs){
            if (!olb.isInjured){
                playOLBs.add(olb);
            }
        }

        for (PlayerILB ilb : defense.ilbs){
            if (!ilb.isInjured){
                playILB = ilb;
            }
        }


        for (PlayerCB cb : defense.cbs){
            if (!cb.isInjured){
                playCBs.add(cb);
            }
        }

        //receiver distros
        int wr1Weight = 25;
        int wr2Weight = 25;
        int teWeight = 20;
        int wr3Weight = 20;
        int rbWeight = 10;

        // get player weights to use when finding focus player for a down
        PlayerWR wr1 = playWRs.get(0);
        PlayerWR wr2 = playWRs.get(1);
        PlayerWR wr3 = playWRs.get(2);

        wr1Weight += wr1.overall - wr2.overall;
        wr2Weight -= wr1.overall - wr2.overall;
        wr2Weight += wr2.overall - wr3.overall;
        wr3Weight -= wr3.overall - wr2.overall;
        wr3Weight -= playTE.overall - wr3.overall;
        teWeight += playTE.overall - wr3.overall;
        wr3Weight -= 3;

        //ball is snapped...

        //TODO: check for a sack


        if (playQB.throwAccuracy/1.25 < (Math.random()*100)) {
            gameLog += "(" + getQtr() + " " + getQtrTime() + " " + downDist + " ball on the " + yardLine + ") " + playQB.team.name + " " + playQB.position +
                " " + playQB.firstName + " " + playQB.lastName + " throws an incomplete pass.\n";
            return;
        }

        //get the target player
        int defenseChoice;
        int rand = (int) (Math.random() * 100);
        if (rand < (wr1Weight)){
            focus = wr1;
            dFocus = playCBs.get(0);
            wr1TargetsHome++;
        } else if (rand > wr1Weight && rand < (wr1Weight+wr2Weight)){
            dFocus = playCBs.get(1);
            focus = wr2;
            wr2TargetsHome++;
        } else if (rand > (wr1Weight+wr2Weight) && rand < (wr1Weight+wr2Weight+wr3Weight)){
            dFocus = playCBs.get(2);
            focus = wr3;

            wr3Targets++;
        } else if (rand < (wr1Weight+wr2Weight+wr3Weight+teWeight)){
            focus = playTE;
            dFocus = playOLBs.get((int) Math.round(Math.random())); //between 0 and 1, either starting olb
            teTargets++;
        } else {
            focus = playRB;
            defenseChoice = (int) (Math.random()*3); //determines who the rb is covered by, make sure to implement for strong safety
            switch (defenseChoice){
                case 0 -> dFocus = playOLBs.get(0);
                case 1 -> dFocus = playOLBs.get(1);
                case 2 -> dFocus = playILB;
                default -> dFocus = playOLBs.get(0); // TODO: fix this
            }

            rbTargets++;
        }

        gameLog += "(" + getQtr() + " " + getQtrTime() + " " + downDist + " ball on the " + yardLine + ") " + playQB.team.name + " " + playQB.position +
            " " + playQB.firstName + " " + playQB.lastName + " throws a pass to " + focus.position + " " + focus.firstName + " " + focus.lastName + ".\n";

        playQB.passAttempts++;

        int breakupChance = 50;
        int interceptionChance = (int) (Math.random() * dFocus.passRush);
        int qbIntChance = (int) (Math.random() * playQB.throwAccuracy);
        int randBreakup = (int) (Math.random() * 100);

        //check if defender can break up pass
        if (dFocus.manCoverage > focus.routeRunning){

            breakupChance += dFocus.manCoverage - focus.routeRunning;

        } else {
            breakupChance -= focus.routeRunning - dFocus.manCoverage;
        }


        if (breakupChance > 40){


            if (breakupChance < randBreakup){
                //pass is broken up
                dFocus.defensePBUs++;

                //check for int
                if (interceptionChance+5 > qbIntChance){
                    dFocus.defenseInterceptions++;
                    playQB.passInterceptions++;
                    gameLog += " The pass is INTERCEPTED by " + dFocus.team.name + " " + dFocus.position + " " + dFocus.firstName + " " + dFocus.lastName + "!! \n";
                    down = 1;
                    yardsNeeded = 10;
                    changePossession();
                }
                return;
            }
        }
        else {
            if (breakupChance < randBreakup) {
                //pass is broken up
                dFocus.defensePBUs++;
                gameLog += " The pass is broken up by " + dFocus.team.name + " " + dFocus.position + " " + dFocus.firstName + " " + dFocus.lastName + "! \n";
                return;
            }

        }


        //check for a drop
        int catchPct = (int) (focus.passRush + (focus.passRush *Math.random()));
        int catchChance = (int) (Math.random() * 105);
        if (catchPct < catchChance){ //ball is dropped
            teamDropsHome++;
            gameLog += " " + focus.position + " " + focus.firstName + " " + focus.lastName + " "
                + "dropped the pass.\n";
            focus.recDrops++;
            time -= 6; //take off 5-8 seconds for drop
            return;
            //end play

        } else { //pass is caught
            focus.recCatches++;
            int airYards = getAirYards(focus, dFocus, playQB);
            //after the play
            yardsGained += airYards;
            int lastDownYard = yardLine;
            spotBall(yardsGained);

            if (isTouchdown()){
                //touchdown!!!
                if (hasPossession){
                    yardsGained = yardLine;
                } else {
                    yardsGained = 100 - lastDownYard;
                }
                gameLog += " " + focus.position + " " + focus.firstName + " " + focus.lastName + " "
                    + "caught a " + yardsGained + " yard pass for a TOUCHDOWN!\n";
                playQB.passTDs++;
                focus.recYards += yardsGained;
                focus.recTDs++;
                playQB.passYards += yardsGained;
                playQB.passCompletions++;
                offense.gameScore += 6;
                runExtraPoint(offense, defense);
            } else {
                //not a touchdown, collect stats for the play
                gameLog += " " + focus.firstName + " " + focus.lastName + " catches the pass for "
                    + "a gain of " + yardsGained + " yards.\n";
                focus.recYards += yardsGained;
                playQB.passYards += yardsGained;
                playQB.passCompletions++;
                yardsNeeded -= yardsGained;
                yardLine += yardsGained;
            }

            //check for first down
        }
        // ------------ END OF PASSING PLAY ------------ //
    }

    private int getAirYards(Player focus, Player dFocus, Player playQB) {
        int airYards = 0;
        boolean isTackled = false;
        //get air yards
        if (focus.position.equals("Running Back")){
            airYards = (int) (( focus.passRush + focus.routeRunning - dFocus.manCoverage * Math.random())/4);

        } else {
            airYards = (int) (( playQB.throwPower + focus.speed - dFocus.speed) * Math.random() * Math.random()/1.5);

        }
        System.out.println("Air yards: " + airYards);
        //what is done after the catch
        int yardsGained = 0;
        while (!isTackled) { // for the ball carrying sequence, use lb and db tackle stats. randomly get defensive players to tackle

            int edge = 0; //0 offense, 1 defense
            int broken = 0;
            if (dFocus.tackle > focus.breakTackle){
                edge = 1;
            }
            switch (edge){
                case 0:
                    broken = (int) (Math.random() * (100 + focus.breakTackle-dFocus.tackle));

                case 1:
                    broken = (int) (Math.random() * (100 + dFocus.tackle-focus.breakTackle));
            }
            if (airYards > 40){
                int x = (int) (Math.random()*100);
                if (x < 85){
                    airYards -= 40;
                }
            }
            if (broken > 50){
                dFocus.defenseTackles++;
                isTackled = true;
            }


        }


        return airYards;
    }

    private void runExtraPoint(Team offense, Team defense) {

        offense.gameScore += 1;
        getScore();
        kickOff(defense, offense);
    }

    private void getScore(){
        gameLog += homeTeam.name + " - " + homeTeam.gameScore + " " + awayTeam.name + " - " + awayTeam.gameScore + " " + getQtr() + " quarter " + getQtrTime() + "\n\n";
    }

    private String getQtrTime(){
        int time = qtrTime;
        String timeString = "";
        //get the minute
        int minute = time/60;
        timeString += "" + minute;

        int second = time - minute*60;
        String secString = "" + second;
        if (secString.length() == 1){
            secString = "0" + second;
        }
        timeString += ":" + secString;
        return timeString;
    }


    private String getQtr(){
        String quarter = "";
        switch (qtr){
            case 1 -> quarter = "1st";
            case 2 -> quarter = "2nd";
            case 3 -> quarter = "3rd";
            case 4 -> quarter = "4th";
        }
        return quarter;
    }

    //    private void checkQtr(){
    //        if (time > 2700){
    //            qtr = 1;
    //        } else if (time > 1800){
    //            qtr = 2;
    //        } else if (time > 900) {
    //            qtr = 3;
    //        } else {
    //            qtr = 4;
    //        }
    //    }

    private void checkQtrTime() {
        if (qtrTime <= 0){
            //reset time
            qtrTime = 900;
            qtr++;
        }
    }

    private String getDownDist(){
        String downDist = "";
        switch (down){
            case 1 -> downDist += "1st and ";
            case 2 -> downDist += "2nd and ";
            case 3 -> downDist += "3rd and ";
            case 4 -> downDist += "4th and ";
        }

        downDist += yardsNeeded;

        return downDist;
    }


    /**
     * Simulates the offense choosing a play given the current game conditions:
     * yardLine, yardsNeeded, time, score
     * TODO: take timeouts into account, change EOG branch
     * @param offense
     * @param defense
     * @return Plays include - Inside run, outside run, QB run, short pass, medium pass, deep pass, hail mary, punt, field goal
     */
    private String choosePlay(Team offense, Team defense) {
        int passRtg = offense.passRtg; //this will be the average of the offense's qb, wrs, and oline
        int runRtg = offense.runRtg; //this will be the average of the offense's oline and rb

        //make these random
        int passChance = 55;
        boolean preferPass;
        int runChance = 45;
        int larger;
        //logic for choosing pass or run on a non-scenario play
        if (passRtg<=runRtg){
            passChance += (passRtg - runRtg);
            larger = passChance;
        } else {
            runChance += (runRtg - passRtg);
            larger = runChance;
        }
        Random rand = new Random();
        int choice = rand.nextInt(100);
        if (choice < larger){
            preferPass = true;
        } else {
            preferPass = false;
        }


        if (time <= 25 && (defense.gameScore>offense.gameScore)){ //trailing under 25 seconds
            if (offense.gameScore-defense.gameScore<=-3){ //down by three or less
                if (yardLine>60){ //inside 40
                    return "Field Goal";
                } else {
                    return "Hail Mary";
                }
            } else { //down by four or more
                return "Hail Mary";
            }
        } else if (down == 4) { //fourth down branch
            if (time <= 120 && (offense.gameScore-defense.gameScore>-3)){ //losing by more than three and two mins or less
                return "Pass";
            } else if (yardsNeeded < 4) { //fourth and less than three
                if (yardLine > 60 ){ //inside 40
                    return "Field Goal";
                } else {
                    if (offense.gameScore-defense.gameScore<0 && yardLine > 50) //losing and at or farther than the 50
                        return "Run";
                }
            } else {
                return "Punt";
            }
            //want to pass
        } else if (yardLine > 90) {
            if (Math.random() * 100 >= 70 ){
                return "Run";
            } else {
                return "Pass";
            }

        } else if (down == 3 && yardsNeeded > 5 || preferPass) { //third and greater than 5
            return "Pass";
        } else {
            return "Run";
        }
        System.out.println(down);
        return "Punt";
    }

    /**
     * simulated coin flip determines who has possession
     * 0 = home team has possession
     * @return
     */
    private int coinFlip(){
        Random rand = new Random();
        return rand.nextInt(2);
    }

}
