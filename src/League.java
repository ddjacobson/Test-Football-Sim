
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class League {
    public static Team[][][] leagueList; //0-1 Conference | 0-3 Division | 0-3 Team
    final public static int START_YEAR = 2022;
    public int currYear;
    final public static int NUM_WEEKS = 17;

    final public int [] preseasonWeeks = {-1, -2, -3, -4};
    final public int [] regularSeasonWeeks = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
    public int currWeek;

    public static ArrayList<Team> TEAM_LIST = new ArrayList<>();      //Add teams a second time - same division

    public ArrayList<Conference> conferenceList;



    /**
     * This constructor will be used to start the league.
     * It will build the conferences, divisions, and teams.
     * While creating teams, it will add them to the according conference and division.
     */
    public League() {
        leagueList = new Team[2][4][4];
        TEAM_LIST = populateLeague("basicTeams.txt");
        currYear = START_YEAR;

        //currWeek = preseasonWeeks[0];, use when preseason is implemented
        currWeek = regularSeasonWeeks[0];
        conferenceList = new ArrayList<>();
        conferenceList.add(new Conference("AFC"));
        conferenceList.add(new Conference("NFC"));

        //NFL-like schedule creation is borderline impossible... I've resorted to round-robin
        //when we advance the season, we want to move our TEAM_LIST, so we get
        //different matchups each schedule generation


        listMatches(TEAM_LIST);

//        for (Team t : TEAM_LIST){
//            System.out.println("Scheduling for: " + t.name);
//            for (Team d : t.teamSchedule){
//
//                System.out.println(d.name + " ");
//            }
//            System.out.println();
//        }

//        for (int week : regularSeasonWeeks){
//            playGames(TEAM_LIST);
//            advanceWeek();
//        }

        Game testGame = new Game(TEAM_LIST.get(0), TEAM_LIST.get(1));
        testGame.playGame();

    }


    /**
     * Creates full 16 game schedule for all 32 teams in league using round-robin scheduling.
     * @param teamList
     */
    public void playGames(ArrayList<Team> teamList){
        int teamsSize = TEAM_LIST.size() - 1;
        int halfSize = TEAM_LIST.size() / 2;
        //preseason won't work
        int week = currWeek - 1;
        ArrayList<Team> teams = new ArrayList<>();
        teams.addAll(teamList);
        teams.remove(0);

        System.out.println("Week " + (week + 1));
        int teamIdx = week % teamsSize;
        System.out.println("" + teams.get(teamIdx).name + " vs " +  teamList.get(0).name);

        for (int idx = 1; idx< halfSize; idx++){
            int firstTeam = (week+idx) % teamsSize;
            int secondTeam = (week + teamsSize - idx) % teamsSize;
            Team teamOne = teams.get(firstTeam);
            Team teamTwo = teams.get(secondTeam);
            teamOne.teamSchedule[week] = teamTwo; //add the teams in each other's schedule list
            teamTwo.teamSchedule[week] = teamOne;
            playGame(teamOne,teamTwo);
            System.out.println("" + teamOne.name + " vs " + teamTwo.name);
        }

    }

    private void playGame(Team homeTeam, Team awayTeam) {
        Game game = new Game(homeTeam, awayTeam);
        game.playGame();
        //System.out.println("Today we have the " + teamOne.name + " vs. the " + teamTwo.name);

    }

    /**
     * Generates the schedule for every team in the league using round-robin scheduling
     * @param teamList
     */

    public static void listMatches(ArrayList<Team> teamList){
        int numTeams = teamList.size();

        if (teamList.size() % 2 != 0){
            teamList.add(new Team("BYE", "BYE"));
        }

        int numDays = 17;
        int halfSize = numTeams/2;

        ArrayList<Team> teams = new ArrayList<>();

        teams.addAll(teamList);
        teams.remove(0);
        int teamsSize = teams.size();

        for (int week = 0; week  < numDays; week++){
            //System.out.println("Week " + (week)); // week + 1
            int teamIdx = week % teamsSize;
            Team t = teams.get(teamIdx);
            Team robin = teamList.get(0);
            robin.teamSchedule[week] = t;
            t.teamSchedule[week] = robin;
            //System.out.println("" + teams.get(teamIdx).name + " vs " +  teamList.get(0).name);

            for (int idx = 1; idx< halfSize; idx++){
                int firstTeam = (week+idx) % teamsSize;
                int secondTeam = (week + teamsSize - idx) % teamsSize;
                Team teamOne = teams.get(firstTeam);
                Team teamTwo = teams.get(secondTeam);
                teamOne.teamSchedule[week] = teamTwo; //add the teams in each other's schedule list
                teamTwo.teamSchedule[week] = teamOne;
                //System.out.println("" + teamOne.name + " vs " + teamTwo.name);
            }

        }
    }



    public void advanceWeek(){
        if (currWeek == -4){
            currWeek = regularSeasonWeeks[0];
            //startRegularSeason();
        }
        else if (currWeek < 0){
            currWeek--;
        }
        else{
            currWeek++;
        }

    }

    private void weekToString(){
        String week = "";
        if (currWeek < 0){
            week += "Preseason Week ";
        }
        else week += "Week ";

        week =  week + Math.abs(currWeek);

    }

    /**
     * @param teamListFileName will be in format "city", "name"
     * @return
     */
    private ArrayList<Team> populateLeague(String teamListFileName) {
        File teamFile = new File(teamListFileName);
        int nextIndex = 0;

        TEAM_LIST = new ArrayList<>();
        String team, city, conference, division;
        int lastDivisionFinish, teamIndex;
        Scanner input;
        try {
            input = new Scanner(teamFile);
        } catch (FileNotFoundException e) {
            input = new Scanner("basicTeams.txt");
        }
        input = input.useDelimiter(",");
        while (input.hasNextLine()) {
            teamIndex = input.nextInt();
            conference = input.next();
            division = input.next();
            lastDivisionFinish = input.nextInt();
            city = input.next();
            team = input.nextLine();
            team = team.substring(1);

            Team teamToAdd = new Team(teamIndex, city, team, conference, division, lastDivisionFinish);
            TEAM_LIST.add(teamToAdd);
        }

        //Sort the teams into their corresponding divisions
      /*
      Reference:
      AFC - 0
      NFC - 1
      East - 0
      North - 1
      South - 2
      West - 3
              */
        for (Team t  : TEAM_LIST){
            //reset the division index when move to next.
            if (nextIndex > 3){
                nextIndex = 0;
            }
            switch (t.conference){
                case "AFC":
                    switch (t.division) {
                        case "East" -> {
                            leagueList[0][0][nextIndex] = t;
                            nextIndex++;
                        }
                        case "North" -> {
                            leagueList[0][1][nextIndex] = t;
                            nextIndex++;
                        }
                        case "South" -> {
                            leagueList[0][2][nextIndex] = t;
                            nextIndex++;
                        }
                        case "West" -> {
                            leagueList[0][3][nextIndex] = t;
                            nextIndex++;
                        }
                    }
                    break;
                case "NFC":
                    switch (t.division) {
                        case "East" -> {
                            leagueList[1][0][nextIndex] = t;
                            nextIndex++;
                        }
                        case "North" -> {
                            leagueList[1][1][nextIndex] = t;
                            nextIndex++;
                        }
                        case "South" -> {
                            leagueList[1][2][nextIndex] = t;
                            nextIndex++;
                        }
                        case "West" -> {
                            leagueList[1][3][nextIndex] = t;
                            nextIndex++;
                        }
                    }
            }
        }
        return TEAM_LIST;
    }


    public void getTeamList() {

        for (Team team : TEAM_LIST) {
            System.out.println(team.city + " " + team.name);
        }
    }


    private void generateSchedule() {


    }


    /**
     * Prints out the teams, along with their record.
     */
    public void printTeams() {
        for (Team[][] teams : leagueList) {
            for (Team[] value : teams) {
                for (Team teamToPrint : value) {
                    System.out.println(teamToPrint.name + " " + teamToPrint.getRecord());
                }
            }

        }


    }}