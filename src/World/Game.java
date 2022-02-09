//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package World;

import Comparator.ComparePlayerOvr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Game {
    public Team homeTeam;
    public Team awayTeam;
    public String gameLog;
    public int qtr;
    public int homeScore;
    public int awayScore;
    public boolean isByeWeek;
    private int time;
    private int qtrTime;
    private int yardLine;
    private int down;
    private int yardsNeeded;
    private String downDist;
    private boolean hasPossession;
    Coach homeCoach;
    Coach awayCoach;
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
        this.homeTeam = home;
        this.awayTeam = away;
        this.qtr = 1;
        this.homeScore = 0;
        this.awayScore = 0;
        if (away.name.equals("BYE")) {
            this.isByeWeek = true;
        }

    }

    public void playGame() {
        boolean gameIsDone = false;
        this.homeCoach = this.homeTeam.hc;
        this.awayCoach = this.awayTeam.hc;
        ++this.homeTeam.gamesPlayed;
        ++this.awayTeam.gamesPlayed;
        this.gameLog = this.gameLog + "\n" + this.homeTeam.wins + "-" + this.homeTeam.losses + " " + this.homeTeam.city + " " + this.homeTeam.name + " vs. " + this.awayTeam.wins + "- " + this.awayTeam.losses + this.awayTeam.city + " " + this.awayTeam.name + "\n";
        this.time = 3600;
        this.qtrTime = 900;
        Team kickingTeam;
        Team receivingTeam;
        if (this.coinFlip() == 0) {
            this.hasPossession = true;
            this.gameLog = this.gameLog + "The " + this.homeTeam.name + " have won the coin toss! They will receive the ball.\n\n";
            kickingTeam = this.awayTeam;
            receivingTeam = this.homeTeam;
        } else {
            this.hasPossession = false;
            this.gameLog = this.gameLog + "The " + this.awayTeam.name + " have won the coin toss! They will receive the ball.\n\n";
            kickingTeam = this.homeTeam;
            receivingTeam = this.awayTeam;
        }

        this.kickOff(receivingTeam, kickingTeam);

        while(this.time > 0) {
            if (this.down > 4) {
                this.gameLog = this.gameLog + "Turnover on downs!\n";
                this.changePossession();
                this.down = 1;
                this.yardsNeeded = 10;
            }

            if (this.hasPossession) {
                this.runPlay(this.homeTeam, this.awayTeam);
            } else {
                this.runPlay(this.awayTeam, this.homeTeam);
            }
        }

        this.printFinalStats();
        this.endGame();
        System.out.println(this.gameLog);
    }

    private void endGame() {
        if (this.homeTeam.gameScore > this.awayTeam.gameScore) {
            this.homeTeam.wins++;
            this.awayTeam.losses++;
        } else if (this.awayTeam.gameScore > this.homeTeam.gameScore) {
            this.homeTeam.losses++;
            this.awayTeam.wins++;
        } else {
            this.homeTeam.ties++;
            this.awayTeam.ties++;
        }

        homeTeam.updateWinPCT();
        awayTeam.updateWinPCT();

        gameLog += "\nFinal Score: \n" + this.homeTeam.name + ": " + this.homeTeam.gameScore + " to " + this.awayTeam.name + ": " + this.awayTeam.gameScore;


        homeTeam.gameScore = 0;
        awayTeam.gameScore = 0;
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

    public void runPlay(Team offense, Team defense) {
        int keptBall = 0;
        String play = choosePlay(offense, defense);
        downDist = getDownDist();

        switch (play){
            case "Pass"-> runPassPlay(offense, defense);
            case "Run"-> runRushPlay(offense, defense);
            case "Punt"-> runPuntPlay(offense, defense);
            case "Field Goal"-> runFieldGoalPlay(offense, defense);
            //                case "Hail Mary" -> runHailMaryPlay(offense, defense);

        }

        if (keptBall == 0 && (play.equals("Pass") || play.equals("Run"))) {
            if (this.gotFirstDown()) {
                down = 1;
                yardsNeeded = 10;
                gameLog += "  First Down!\n";
            } else {
                down++;
            }
        }

        this.time -= 5;
        this.qtrTime -= 5;
        this.checkQtrTime();
    }

    private void endPlay(String outcome, Team offense, Team defense) {
        if (!outcome.equals("Incomplete") && !outcome.equals("Turnover")) {

            int timeBetweenPlay = getTimeBetweenPlay(offense, defense);
            qtrTime -= timeBetweenPlay;
            time -= timeBetweenPlay;
        }
    }

    private int getTimeBetweenPlay(Team offense, Team defense) {
        Random rand = new Random();
        int timeBetweenPlay;
        if (offense.gameScore <= defense.gameScore && this.qtrTime <= 120) {
            timeBetweenPlay = rand.nextInt(10, 25);
        } else {
            timeBetweenPlay = rand.nextInt(15, 35);
        }

        return timeBetweenPlay;
    }

    private boolean gotFirstDown() {
        return this.yardsNeeded <= 0;
    }

    private void spotBall(int yardsGained) {
        if (this.hasPossession) {
            yardLine -= yardsGained;
        } else {
            yardLine += yardsGained;
        }

    }

    private String getYardLine() {
        String yardLineString = "";
        if (this.yardLine > 50) {
            yardLineString = yardLineString + this.homeTeam.name + "' " + (100 - this.yardLine);
        } else {
            yardLineString = yardLineString + this.awayTeam.name + "'s " + this.yardLine;
        }

        return yardLineString;
    }

    private void runPuntPlay(Team offense, Team defense) {
        gameLog +="(" + getQtr() + " " + getQtrTime() + " " + downDist + " ball on the " + getYardLine() + ") The " + offense.name + " punt the ball away to the " + this.awayTeam.name + ". They start their drive at the " + yardLine + "\n\n";
        down = 1;
        yardsNeeded = 10;
        if (hasPossession) {
            yardLine -= 40;
            if (yardLine < 0) {
                yardLine = 20;
            }
        } else {
            this.yardLine += 40;
            if (yardLine > 100) {
                yardLine = 80;
            }
        }

        time -= 20;
        qtrTime -= 20;
        changePossession();
    }

    private void changePossession() {
        hasPossession = !hasPossession;
    }

    private void runFieldGoalPlay(Team offense, Team defense) {
        offense.gameScore += 3;
        this.gameLog =
            this.gameLog + "  The " + offense.name + " kick a " + (yardsToScore()+17) + " yard "
                + "field goal.\n";
        this.time -= 10;
        this.qtrTime -= 10;
        this.kickOff(defense, offense);
    }

    private void runRushPlay(Team offense, Team defense) {
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
        yardsGained += ((rand.nextGaussian()*oLineRating + (rbRating + playRB.speed)/2)/(dlRating/4.5));

        if (playRB.speed > 90 && rand.nextInt(100) > 90){
            gameLog += "     BIG RUN!!!\n";
            yardsGained += rand.nextInt(playRB.speed);
        }

        gameLog += "(" + getQtr() + " " + getQtrTime() + " " + downDist + " ball on the " + getYardLine()  +
            ") ";
        spotBall(yardsGained);

        if (isTouchdown()) {
            //touchdown
            playRB.rushYards += yardLine;

            gameLog += playRB.position + " " + playRB.firstName + " " + playRB.lastName + " runs "
                + "in for a " + yardsGained + " yard touchdown!\n";

            yardLine = 0;
            playRB.rushTDs++;
            offense.gameScore += 6;
            runExtraPoint(offense, defense);

        } else {
            gameLog += playRB.team.name + " " + playRB.position +  " " + playRB.firstName + " " + playRB.lastName + " runs " + "for a gain of " + yardsGained + ".\n";
            playRB.rushYards += yardsGained;
            yardsNeeded -= yardsGained;

            Player dFocus = null;
            int choiceOLB;
            int choiceOLB2;
            int choiceILB;
            int choiceDT;
            int choiceDT2;
            int choiceDE;
            int choiceDE2;
            int choiceCB;
            int choiceCB2;
            int choiceFS;
            int choiceSS;

            if (yardsGained < 2) {
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

            } else if (yardsGained < 12) {
                choiceOLB = (int) (Math.random() * (playOLB.overall + playOLB.tackle));
                choiceOLB2 = (int) (Math.random() * (playOLB2.overall + playOLB2.tackle));
                choiceILB = (int) (Math.random() * (playILB.overall + playILB.tackle));
                choiceSS = (int) (Math.random() * (playSS.overall + playSS.tackle));

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
            } else {
                //one of the CBs/S
            }
        }

        this.endPlay("Tackle", offense, defense);
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

    private int runPassPlay(Team offense, Team defense) {
        new Random();
        Formation set = offense.hc.offensivePlan.getRandomGameplan();

        PlayerQB playQB = null;
        PlayerRB playRB;
        Player focus;

        //get offense
        ArrayList<PlayerWR> playWRs = new ArrayList<>(0);
        ArrayList<PlayerTE> playTEs = new ArrayList<>(0);
        ArrayList<PlayerRB> playRBs = new ArrayList<>(0);

        //get defense
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

        this.totalPassPlaysHome++;
        for (PlayerQB qb : offense.qbs){
            if (!qb.isInjured){
                playQB = qb;
            }
        }

        //get best wrs playing, eventually make an age/potentiall thing so good young players
        // will play more often
        Collections.sort(offense.wrs, new ComparePlayerOvr());

        for (PlayerWR player : offense.wrs){
            if (!player.isInjured && playWRs.size() < set.getNumWRs()){
                playWRs.add(player);
            }
        }

        for (PlayerTE player : offense.tes){
            if (!player.isInjured && playTEs.size() < set.getNumWRs()){
                playTEs.add(player);
            }
        }

        for (PlayerRB player : offense.rbs){
            if (!player.isInjured && playRBs.size() < set.getNumWRs()){
                playRBs.add(player);
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

        Player receiver1 = null;
        Player receiver2 = null;
        Player receiver3 = null;
        Player receiver4 = null;

        playRB = (PlayerRB)playRBs.get(0);
        String formation = set.formationName;
        int setNum;

        switch (formation){
            case "Spread" -> setNum = 4;
            case "Goal Line" -> setNum = 3;
            case "Base" -> setNum = 0;
            case "Twin TE" -> setNum = 2;
            case "I-Formation" -> setNum = 1;
            default -> setNum = 3;
        }

        switch (setNum) {
            case 0 -> {
                receiver1 = playWRs.get(0);
                receiver2 = playWRs.get(1);
                receiver3 = playTEs.get(0);
                receiver4 = playWRs.get(2);
            }
            case 1 -> {
                receiver1 = playWRs.get(0);
                receiver2 = playWRs.get(1);
                receiver3 = playWRs.get(2);
                receiver4 = playTEs.get(0);
            }
            case 2 -> {
                receiver1 = playWRs.get(0);
                receiver2 = playWRs.get(1);
                receiver3 = playTEs.get(0);
                receiver4 = playTEs.get(1);
            }
            case 3 -> {
                receiver1 = playWRs.get(0);
                receiver2 = playTEs.get(0);
                receiver3 = playTEs.get(1);
                receiver4 = playTEs.get(2);
            }
            case 4 -> {
                receiver1 = playWRs.get(0);
                receiver2 = playWRs.get(1);
                receiver3 = playWRs.get(2);
                receiver4 = playWRs.get(3);
            }
        }

        // get
        int wr1Weight = 25;
        int wr2Weight = 25;
        int wr3Weight = 20;
        int wr4Weight = 15;
        int rbWeight = 15;

        // get player weights to use when finding focus player for a down
        wr1Weight += receiver1.overall - receiver2.overall;
        wr2Weight -= receiver1.overall - receiver2.overall;
        wr2Weight += receiver2.overall - receiver3.overall;
        wr3Weight -= receiver2.overall - receiver3.overall;
        wr3Weight += receiver3.overall - receiver4.overall;
        wr4Weight -= receiver3.overall - receiver4.overall;
        wr4Weight -= playRB.catching - receiver4.overall;
        rbWeight += playRB.catching - receiver4.overall;


        if (playQB.throwAccuracy/1.25 < (Math.random()*100)) {
            gameLog += "(" + getQtr() + " " + getQtrTime() + " " + downDist + " ball on the " + getYardLine() + ") " + playQB.team.name + " " + playQB.position + " " + playQB.firstName + " " + playQB.lastName + " throws an incomplete pass.\n";
            endPlay("Incomplete", offense, defense);
            return 0;
        }

        int rand = (int)(Math.random() * 105.0);

        if (rand < wr1Weight) {
            focus = receiver1;
            dFocus =playCBs.get(0);
        } else if (rand > wr1Weight && rand < wr1Weight + wr2Weight) {
            focus = receiver2;
            dFocus = playCBs.get(1);
        } else if (rand > wr1Weight + wr2Weight && rand < wr1Weight + wr2Weight + wr3Weight) {
            dFocus = playCBs.get(2);
            focus = receiver3;
        } else if (rand < wr1Weight + wr2Weight + wr3Weight + wr4Weight) {
            focus = receiver4;
            dFocus = playOLBs.get((int)Math.round(Math.random()));
        } else {
            System.out.println("------------------------------Ball rb");
            focus = playRB;
            int defenseChoice = (int)(Math.random() * 3);
            dFocus = switch (defenseChoice) {
                case 0 -> playOLBs.get(0);
                case 1 -> playOLBs.get(1);
                case 2 -> playILB;
                default -> playOLBs.get(0);
            };
        }

        gameLog += "(" + getQtr() + " " + getQtrTime() + " " + downDist + " ball on the " + getYardLine() + ") " + playQB.team.name + " " + playQB.position +
            " " + playQB.firstName + " " + playQB.lastName + " throws a pass to " + focus.position + " " + focus.firstName + " " + focus.lastName + ".\n";

        playQB.passAttempts++;

        int breakupChance = 39;
        int interceptionChance = (int)(Math.random() * dFocus.manCoverage - (100 - dFocus.overall));

        int qbIntChance = (int)((double)playQB.throwAccuracy - Math.random() * (double)playQB.throwAccuracy);
        int randBreakup = (int)(Math.random() * 150);

        if (!focus.position.equals("Running Back")) {
            if (dFocus.manCoverage > focus.routeRunning) {
                breakupChance += dFocus.manCoverage - focus.routeRunning;
            } else {
                breakupChance -= focus.routeRunning - dFocus.manCoverage;
            }
        }
        System.out.println("Breakup Chance: " + breakupChance + "\nRand Breakup: " + randBreakup);
        if (breakupChance > 40 && breakupChance < randBreakup) {
            System.out.println("BREAKUP");
            dFocus.defensePBUs++;
            if (interceptionChance - 30 > qbIntChance) {
                dFocus.defenseInterceptions++;
                playQB.passInterceptions++;
                gameLog += " The pass is INTERCEPTED by " + dFocus.team.name + " " + dFocus.position + " " + dFocus.firstName + " " + dFocus.lastName + "!! \n";
                down = 1;
                yardsNeeded = 10;
                changePossession();
                endPlay("Incomplete", offense, defense);
                return 1;

            } else {
                //pass breakup, no int
                endPlay("Incomplete", offense, defense);
                return 0;
            }
        }

        //pass is not intercepted

        int catchPct = (int) (focus.catching + (focus.catching * Math.random()));
        int catchChance = (int)(Math.random() * 100);
        System.out.println(catchPct);

        if (catchPct < catchChance) {
            System.out.println("DROP");
            gameLog += " " + focus.position + " " + focus.firstName + " " + focus.lastName + " " + "dropped the pass.\n";
            focus.recDrops++;
            this.endPlay("Incomplete", offense, defense);
            return 0;

        } else {
            focus.recCatches++;
            int airYards = getAirYards(focus, dFocus, playQB);
            int yardsGained = airYards;
            yardsGained += getYAC(offense, defense, airYards, focus, dFocus);
            int lastDownYard = yardLine;
            spotBall(yardsGained);

            if (isTouchdown()) {
                if (hasPossession) {
                    yardsGained = lastDownYard;
                } else {
                    yardsGained = 100 - lastDownYard;
                }

                gameLog += " " + focus.position + " " + focus.firstName + " " + focus.lastName + " caught a " + yardsGained + " yard pass for a TOUCHDOWN!\n";
                playQB.passTDs++;
                focus.recYards += yardsGained;
                focus.recTDs++;
                playQB.passYards += yardsGained;
                playQB.passCompletions++;
                offense.gameScore += 6;
                runExtraPoint(offense, defense);

            } else {
                //not a touchdown, collect stats for the play
                gameLog += " " + focus.firstName + " " + focus.lastName + " catches the pass for a gain of " + yardsGained + " yards.\n";
                focus.recYards += yardsGained;
                playQB.passYards += yardsGained;
                playQB.passCompletions++;
                yardsNeeded -= yardsGained;
                endPlay("Catch", offense, defense);
            }

            return 0;
        }
    }



    private int getAirYards(Player focus, Player dFocus, Player playQB) {
        int airYards;
        if (focus.position.equals("Running Back")) {
            airYards = (int)(((focus.passRush + focus.routeRunning) - dFocus.manCoverage * Math.random()) / 4);
            System.out.println("ForRB");
        } else {
            airYards = (int)((playQB.throwPower + focus.speed - dFocus.speed) * Math.random() * Math.random() / 2.25);
        }

        if (airYards > 40) {
            int x = (int)(Math.random() * 100);
            if (x < 70) {
                airYards -= 40;
            }
        }

        //REMOVE
        System.out.println("Air Yards: " + airYards);
        return airYards;
    }

    private int getYAC(Team offense, Team defense, int airYards, Player focus, Player dFocus) {
        Random rand = new Random();
        int yac = 0;
        boolean isTackled = false;
        if (this.hasPossession) {
            if (airYards >= this.yardLine) {
                return yac;
            }
        } else if (airYards >= 100 - this.yardLine) {
            return yac;
        }

        while(!isTackled) {
            int tackleChance = (int)(Math.random() * (dFocus.strength + dFocus.tackle) / 2);
            if ((double)tackleChance > (double)focus.breakTackle / 2.5) {
                yac += rand.nextInt(1, 4);
                isTackled = true;
                break;
            }

            ++focus.brokenTackles;
            ++dFocus.defenseMissedTackles;
        }

        System.out.println(yac);
        return yac;
    }

    private void runExtraPoint(Team offense, Team defense) {
        offense.gameScore++;
        time -= 3;
        qtrTime -= 3;
        getScore();
        kickOff(defense, offense);
    }

    private void getScore() {
        this.gameLog += homeTeam.name + " - " + homeTeam.gameScore + " " + awayTeam.name + " - " + awayTeam.gameScore + " " + getQtr() + " quarter " + getQtrTime() + "\n\n";
    }

    private String getQtrTime() {
        int time = this.qtrTime;
        String timeString = "";
        int minute = time / 60;
        timeString = timeString + minute;
        int second = time - minute * 60;
        String secString = "" + second;
        if (secString.length() == 1) {
            secString = "0" + second;
        }

        timeString = timeString + ":" + secString;
        return timeString;
    }

    private String getQtr() {
        return switch (qtr) {
            case 1 -> "1st";
            case 2 -> "2nd";
            case 3 -> "3rd";
            case 4 -> "4th";
            default -> "";
        };
    }

    private void checkQtrTime() {
        if (qtrTime <= 0) {
            qtrTime = 900;
            qtr++;
        }
    }



    private String getDownDist() {
        String downDist = "";
        switch(this.down) {
            case 1:
                downDist = downDist + "1st and ";
                break;
            case 2:
                downDist = downDist + "2nd and ";
                break;
            case 3:
                downDist = downDist + "3rd and ";
                break;
            case 4:
                downDist = downDist + "4th and ";
        }

        downDist = downDist + this.yardsNeeded;
        return downDist;
    }

    private String choosePlay(Team offense, Team defense){
        Random rand = new Random();
        int passRtg = offense.passRtg;
        int runRtg = offense.runRtg;
        int passChance =rand.nextInt(85);
        int runChance = rand.nextInt(65);

        if (down > 4){
            //turnover on downs;
            gameLog += " TURNOVER ON DOWNS";
            changePossession();
            down = 1;
            yardsNeeded = 10;
            //flip field
            yardLine = 100 - yardsNeeded;
            return "Hi";

        }  else {
//            double preferPass = (offense.getPassProf() - defense.getPassDef()) / 100 + Math.random() * offense.playbookOff.getPassPref();       //STRATEGIES
//            double preferRush = (offense.getRushProf() - defense.getRushDef()) / 90 + Math.random() * offense.playbookOff.getRunPref();

            if (down == 1 && yardLine >= 91) yardsNeeded = 100 - yardLine;

            //FIXME: Add OT
            if (time <= 20 && ((hasPossession && (awayScore >= homeScore)) || (!hasPossession && (homeScore >= awayScore)))) {
                //Down by 3 or less, or tied, and you have the ball
                if (((hasPossession && (awayScore - homeScore) <= 3) || (!hasPossession && (homeScore - awayScore) <= 3)) && yardsToScore() < 45) {
                    //last second FGA
                    gameLog += "WTF\n";
                    return "Field Goal";
                } else {
                    //hail mary
                   return "Hail Mary";
                }
            } else if (down >= 4) {
                if (((hasPossession && (awayScore - homeScore) > 3) || (!hasPossession && (homeScore - awayScore) > 3)) && time < 300) {
                    //go for it since we need 7 to win -- This also forces going for it if down by a TD in BOT OT
                    if (yardsNeeded < 3 && runChance * 3 > passChance) {
                        return "Run";
                    } else {
                        return "Pass";
                    }
                } else {
                    //4th down
                    if (yardsNeeded < 3) {
                        if (yardsToScore() < 40) {
                            //fga

                            return "Field Goal";
                        } else if (yardsToScore() > 45) {
                            // run play, go for it!
                            return "Run";
                        } else {
                            //punt
                            return "Punt";
                        }
                    } else if (yardsToScore() <= 40 ) {
                        //fga
                        return "Field Goal";
                    } else {
                        //punt
                        return "Punt";
                    }
                }
            } else if ((down == 3 && yardsNeeded > 4) || ((down == 1 || down == 2) && (passChance >= runChance))) {
                // pass play

                return "Pass";
            } else {
                //run play
                return "Run";
            }
        }

    }

//    private String choosePlay(Team offense, Team defense) {
//        int passRtg = offense.passRtg;
//        int runRtg = offense.runRtg;
//        int passChance = 50;
//        int runChance = 500;
//        if (passRtg <= runRtg) {
//             passChance = passChance + (passRtg - runRtg);
//        } else {
//             runChance = runChance + (runRtg - passRtg);
//        }
//
//        Random rand = new Random();
//        int choice = rand.nextInt(40);
//        if (down > 4) {
//            hasPossession = !hasPossession;
//            down = 1;
//            time = 10;
//            time = 100 - time;
//            return "Hi";
//        } else {
//            double preferRush = Math.random() * 50;
//            double preferPass = Math.random() * 70;
//
//            if (down == 1 && yardLine >= 91) {
//                yardsNeeded = 100 - yardLine;
//            }
//
//            if (this.time > 20 || (!this.hasPossession || this.awayScore < this.homeScore) && (this.hasPossession || this.homeScore < this.awayScore)) {
//                if (this.down >= 4) {
//                    if ((this.hasPossession && this.awayScore - this.homeScore > 3 || !this.hasPossession && this.homeScore - this.awayScore > 3) && this.time < 300) {
//                        return this.yardsNeeded < 3 && preferRush * 3.0D > preferPass ? "Run" : "Pass";
//                    } else if (this.yardsNeeded < 3) {
//                        if (this.yardLine > 65) {
//                            return "Field Goal";
//                        } else {
//                            return this.yardLine > 55 ? "Run" : "Punt";
//                        }
//                    } else {
//                        return this.yardLine > 60 ? "Field Goal" : "Punt";
//                    }
//                } else {
//                    return (this.down != 3 || this.yardsNeeded <= 4) && (this.down != 1 && this.down != 2 || !(preferPass >= preferRush)) ? "Run" : "Pass";
//                }
//            } else {
//                return (this.hasPossession && this.awayScore - this.homeScore <= 3 && this.yardLine< 40 || !this.hasPossession && this.homeScore - this.awayScore <= 3) && this.yardLine > 60 ? "Field Goal" : "Pass";
//            }
//        }
//    }

    private int yardsToScore(){
        if (hasPossession){
            return yardLine;
        } else {
            return 100 - yardLine;
        }
    }

    private int coinFlip() {
        Random rand = new Random();
        return rand.nextInt(2);
    }
}
