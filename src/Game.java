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
    private int yardLine;
    private int down;
    private int yardsNeeded;
    private boolean hasPossession; //true = home, false = away


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

        qtr = 0;
        homeScore = 0;
        awayScore = 0;


        if (away.name.equals("BYE")){
            isByeWeek = true;
        }

    }

    public void playGame(){
        gameLog += homeTeam.city + homeTeam.name + " vs. " + awayTeam.city + awayTeam.name;
        time = 3600; //60 minute football game in seconds
        Team kickingTeam;
        Team receivingTeam;
        if (coinFlip() == 0){
            hasPossession = true;
            kickingTeam = awayTeam;
            receivingTeam = homeTeam;
        }
        else {
            hasPossession = false;
            kickingTeam = homeTeam;
            receivingTeam = awayTeam;
        }

        kickOff(receivingTeam, kickingTeam);

        while (time>0){

            if (down > 4) { // turnover on downs
                System.out.println("Turnover on downs");
                hasPossession = !hasPossession;
                down = 1;
                yardsNeeded = 10;
                yardLine = 100 - yardLine; //flip the field

            }

                System.out.println(hasPossession);
            if (hasPossession){
                System.out.println("Running play for " + homeTeam.name);
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

        System.out.println("\n" + awayTeam.name);
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
                System.out.println("Catches: " + player.recCatches + "\nRec Yards: " + player.recYards + "\nRec Touchdowns: " + player.recTDs + "\nDrops: " + player.recDrops + "\nRushes: " + player.rushAttempts + "\nRush Yards: " + player.rushYards + "\nRush TDs: " + player.rushTDs + "\nYPC: " + ((float) player.rushYards / player.rushAttempts) );
            }
        }



    }

    private void kickOff(Team receivingTeam, Team kickingTeam) {
        //TODO: implement kickoff
        hasPossession = !hasPossession;
        System.out.println("\n\n" + hasPossession + "\n\n");
        yardLine = 25;
    }

    public void runPlay(Team offense, Team defense){
        totalPlaysHome++;
         //not fourth down, offense will run a play.
            String play = choosePlay(offense, defense);
            System.out.println(play);
            switch (play){
                case "Pass"-> runPassPlay(offense, defense);
                case "Run"-> runRushPlay(offense, defense);
                case "Punt"-> runPuntPlay(offense, defense);
                case "Field Goal"-> runFieldGoalPlay(offense, defense);
//                case "Hail Mary" -> runHailMaryPlay(offense, defense);

            }
            time -= 28; // gets us to 150 plays/game, but we want to have dynamic clock management

        }

    private void runPuntPlay(Team offense, Team defense) {
        hasPossession = !hasPossession;
        down = 1;
        time -= 15;
    }

    private void runFieldGoalPlay(Team offense, Team defense) {
        offense.gameScore += 3;
        kickOff(defense, offense);
        time -= 10;
    }

    /**
     * This method takes care of simulating a run play
     * @param offense
     * @param defense
     */
    private void runRushPlay(Team offense, Team defense) {
        //4-3 defense
        PlayerOLB playOLB = null;
        PlayerOLB playOLB2 = null;
        PlayerILB playILB = null;
        PlayerCB playCB = null;
        PlayerCB playCB2 = null;
        PlayerFS playFS = null;
        PlayerSS playSS = null;
        PlayerRB playRB = null;
        for (PlayerRB rb : offense.rbs){
            if (!rb.isInjured){
                playRB = rb;
            }
        }
        playRB.rushAttempts++;
        Random rand = new Random();
        int yardsGained = 0;

        //get oline rating
        int oLineRating = getOlineRating(offense, "Run");

        System.out.println(oLineRating);
        int rbRating = playRB.overall;
        yardsGained += (rand.nextGaussian()*oLineRating + rbRating) / 18;
        int random = (int) (Math.random() * 100);
        //big gain

        playRB.rushYards += yardsGained;




        //yardsGained = ;

        yardLine += yardsGained;

        if (yardLine >= 100){
            //touchdown

            playRB.rushTDs++;
            offense.gameScore += 6;
            runExtraPoint(offense, defense);



        }

        down++;


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

        // defense, assume 4-3 defense
        ArrayList<PlayerCB> playCBs = new ArrayList<>(0);
        PlayerILB playILB = null;
        ArrayList<Player> playOLBs = new ArrayList<>(0);
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

        /*
         * wr distros:
         *      wr1: 23
         *      wr2: 22
         *      wr3: 20
         *      rb: 15
         *      te: 20
         */
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

        //DEBUGS
//        System.out.println("WR1: " + wr1Weight + " " + wr1.overall);
//        System.out.println("WR2: " + wr2Weight + " " + wr2.overall);
//        System.out.println("WR3: " + wr3Weight + " " + wr3.overall);
//        System.out.println("TE: " + teWeight + " " + playTE.overall);
//        System.out.println("RB: " + rbWeight + " " + playRB.overall);


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

        // determine what happens once the ball is snapped

        //TODO: check for a sack



        //if not sacked, pass is thrown.
        //check for a bad pass

        playQB.passAttempts++;

        //something about pass rush
        if (playQB.throwAccuracy/1.25 < (Math.random()*100)) {
            System.out.println("Pass is incomplete");
            return;
        }
        //if (playQB.throwAccuracy/.8)




        int breakupChance = 20;
        int interceptionChance = (int) (Math.random() * dFocus.passRush);
        int qbIntChance = (int) (Math.random() * playQB.throwAccuracy);
        int randBreakup = (int) (Math.random() * 100);
        //TODO: check if defender can break up pass.
        if (dFocus.runStop > focus.routeRunning){

            breakupChance += dFocus.runStop - focus.routeRunning;

        } else {
            breakupChance -= focus.routeRunning - dFocus.runStop;
        }


        if (breakupChance > 40){

            if (breakupChance < randBreakup){
                //pass is broken up
                dFocus.defensePBUs++;
                //check for int

                if (interceptionChance+5 > qbIntChance){
                    dFocus.defenseInterceptions++;
                    playQB.passInterceptions++;
                }
            }

        } else {
            if (breakupChance < randBreakup) {
                //pass is broken up
                dFocus.defensePBUs++;
                //check for int
            }

        }


        //check for a drop
        int catchPct = (int) (focus.passRush + (focus.passRush *Math.random()));
        int catchChance = (int) (Math.random() * 105);
        if (catchPct < catchChance){ //ball is dropped
            teamDropsHome++;
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
            yardLine += yardsGained;

            if (yardLine >= 100){
                //touchdown!!!
                yardsGained = 100 - lastDownYard;
                playQB.passTDs++;
                focus.recYards += yardsGained;
                focus.recTDs++;
                playQB.passYards += yardsGained;
                playQB.passCompletions++;
                offense.gameScore += 6;
                runExtraPoint(offense, defense);
            } else {
                //not a touchdown, collect stats for the play
                System.out.println("Total yards gained on pass: " + yardsGained);
                focus.recYards += yardsGained;
                playQB.passYards += yardsGained;
                playQB.passCompletions++;
                down++;
            }

            //check for first down
            yardsNeeded -= yardsGained;
            if (yardsNeeded <= 0){
                down = 1;
                yardsNeeded = 10;
            }



        }
        // ------------ END OF PASSING PLAY ------------ //
    }

    private int getAirYards(Player focus, Player dFocus, Player playQB) {
        int airYards = 0;
        boolean isTackled = false;
        //get air yards
        if (focus.position.equals("Running Back")){
            airYards = (int) (( focus.passRush + focus.routeRunning - dFocus.runStop * Math.random())/4);
            System.out.println("-----------------------------------------------------RB air yards: " + airYards);

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

        kickOff(defense, offense);
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
