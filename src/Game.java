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

    }

    public void runPlay(Team offense, Team defense){

        //turnover on downs
        if (down > 4){
            hasPosession = !hasPosession;
            down = 1;
            yardsNeeded = 10;
            return;
        } else { //not fourth down, offense will run a play.
            int passRtg; //this will be the average of the offense's qb, wrs, and oline
            int runRtg; //this will be the average of the offense's oline and rb



        }






    }

    private int coinFlip(){
        Random rand = new Random();
        return rand.nextInt(2);
    }

}
