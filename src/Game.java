import java.util.ArrayList;
import java.util.Random;

public class Game {

    public Team homeTeam;
    public Team awayTeam;

    public int qtr;
    public int homeScore;
    public int awayScore;
    public boolean isByeWeek;

    //ingame variables
    private int time;
    private int yardLine;
    private int down;
    private int yardsNeeded;
    private boolean hasPosession; //true = home, false = away


    //initalize stats
    int totalPassPlays = 0;
    int totalRunPlays = 0;
    int teamDrops = 0;
    int totalPlays = 0;
    int wr1Targets = 0;
    int wr2Targets = 0;
    int wr3Targets = 0;
    int teTargets = 0;
    int rbTargets = 0;




    public Game(Team home, Team away) {
        homeTeam = home;
        awayTeam = away;

        qtr = 0;
        homeScore = 0;
        awayScore = 0;


        if (away.name.equals("BYE")){
            isByeWeek = true;
        }

    }

    public void playGame(){

        time = 3600; //60 minute football game in seconds
        Team kickingTeam;
        Team receivingTeam;
        if (coinFlip() == 0){
            hasPosession = true;
            kickingTeam = awayTeam;
            receivingTeam = homeTeam;
        }
        else {
            hasPosession = false;
            kickingTeam = homeTeam;
            receivingTeam = awayTeam;
        }

//        kickOff(receivingTeam, kickingTeam);

        while (time>0){
            if (hasPosession){
                runPlay(homeTeam, awayTeam); //home team has possession, they are on offense
            }
            else runPlay(awayTeam, homeTeam);
        }

        System.out.println("Game finished");

    }

    public void runPlay(Team offense, Team defense){
        totalPlays++;
        if (down > 4){ // turnover on downs
            hasPosession = !hasPosession;
            down = 1;
            yardsNeeded = 10;
            yardLine = 100 - yardLine; //flip the field
            return;
        } else { //not fourth down, offense will run a play.
            String play = choosePlay(offense, defense);
            System.out.println(play);
            switch (play){
                case "Pass"-> runPassPlay(offense, defense);
//                case "Run"-> runRushPlay(offense, defense);
//                case "Punt"-> runPuntPlay(offense, defense);
//                case "Field Goal"-> runFieldGoalPlay(offense, defense);
//                case "Hail Mary" -> runHailMaryPlay(offense, defense);

            }
            time -= 24; // gets us to 150 plays/game, but we want to have dynamic clock management

        }


            System.out.println("FINAL STATS -------\n");
            System.out.println(wr1Targets);
            System.out.println(wr2Targets);
            System.out.println(wr3Targets);
            System.out.println(teTargets);
            System.out.println(rbTargets);
            System.out.println(wr1Targets+wr2Targets+wr3Targets+teTargets+rbTargets);
            System.out.println(totalPlays);

            System.out.println(teamDrops);


    }


    private void runPassPlay(Team offense, Team defense) {
        //TODO: implement a playbook system to have different offensive looks
        PlayerQB playQB = null;
        ArrayList<PlayerWR> playWRs = new ArrayList<>(0);//3wrs
        PlayerTE playTE = null;
        PlayerRB playRB = null;
        Player focus = null;
        Player dFocus = null;
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

        /*
         * wr distros:
         *      wr1: 23
         *      wr2: 22
         *      wr3: 20
         *      rb: 15
         *      te: 20
         */
        int wr1Weight = 23;
        int wr2Weight = 22;
        int teWeight = 20;
        int wr3Weight = 20;
        int rbWeight = 15;

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
//        System.out.println("WR1: " + wr1Weight + " " + wr1.overall);
//        System.out.println("WR2: " + wr2Weight + " " + wr2.overall);
//        System.out.println("WR3: " + wr3Weight + " " + wr3.overall);
//        System.out.println("TE: " + teWeight + " " + playTE.overall);
//        System.out.println("RB: " + rbWeight + " " + playRB.overall);

        //get the target player

        //stats

        int rand = (int) (Math.random() * 100);
        if (rand < (wr1Weight)){
            System.out.println("WR1");
            focus = wr1;
            wr1Targets++;
        } else if (rand > wr1Weight && rand < (wr1Weight+wr2Weight)){
            System.out.println("WR2");
            focus = wr2;
            wr2Targets++;
        } else if (rand > (wr1Weight+wr2Weight) && rand < (wr1Weight+wr2Weight+wr3Weight)){
            System.out.println("WR3");
            focus = wr3;
            wr3Targets++;
        } else if (rand < (wr1Weight+wr2Weight+wr3Weight+teWeight)){
            System.out.println("TE");
            focus = playTE;
            teTargets++;
        } else {
            System.out.println("RB");
            focus = playRB;
            rbTargets++;
        }
        //TODO: get a defensive focus player

        // determine what happens once the ball is snapped
        boolean isTackled;
        boolean isCaught = true;
        //check for a drop
        int catchPct = (int) (focus.catching + (focus.catching*Math.random()));
        int catchChance = (int) (Math.random() * 105);
        if (catchPct < catchChance){
            teamDrops++;
            isCaught = false;
            time -= 6; //take off 5-8 seconds for drop
            //end play

        } else { //pass is caught

            //get air yards
            int airYards = (int) (( playQB.throwPower + focus.speed - dFocus.speed) * Math.random());
            System.out.println("Air yards: " + airYards);
            //what is done after the catch
            int yardsGained;
            //while (!isTackled) { // for the ball carrying sequence, use lb and db tackle stats. randomly get defensive players to tackle



            //}


        }






        System.out.println("Catch percentage: " + catchPct);


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
        } else if (down == 3 && yardsNeeded > 5 || preferPass) { //third and greater than 5
            return "Pass";
        } else {
            return "Run";
        }
        return "Didn't pick";
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
