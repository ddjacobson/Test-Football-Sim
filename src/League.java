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

    public static int PATRIOTS = 0;

    final int TEST_TEAM = 17;
    /*
       This will be used to determine what divisions play each other during the NFL season.
       We will start with...
       NFC East vs. AFC East
       NFC North vs. AFC North
       NFC South vs. AFC South
       NFC West vs. AFC West
       Each year, the AFC portion will rotate one.
       The first array will be the Conferences
       The second array will be the divisions
     */
    public static Division [] AFCOpponents;
    public static Division [] NFCOpponents;


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

        for (Team t : TEAM_LIST){
            System.out.println("Scheduling for: " + t.name);
            for (Team d : t.teamSchedule){

                System.out.println(d.name + " ");
            }
            System.out.println();
        }

        playGames(TEAM_LIST);
        advanceWeek();
        playGames(TEAM_LIST);

    }




    public void playGames(ArrayList<Team> teamList){
        int numWeeks = NUM_WEEKS;
        int teamsSize = TEAM_LIST.size() - 1;
        int halfSize = TEAM_LIST.size() / 2;
        //preseason won't work
        int week = currWeek - 1;
        ArrayList<Team> teams = new ArrayList<>();
        teams.addAll(teamList);
        teams.remove(0);

        System.out.println("Week " + (week + 1)); // week + 1
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

    private void playGame(Team teamOne, Team teamTwo) {
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

        weekToString();
    }

    private void weekToString(){
        String week = "";
        if (currWeek < 0){
            week += "Preseason Week ";
        }
        else week += "Week ";

        week =  week + Math.abs(currWeek);
        System.out.println(week);
    }





    public void getSchedule(Team t){
        //Schedule Testing
        ArrayList<Team> scheduleTeams;
        getTeamDivisionOpponents(t);
        scheduleTeams = getDiffConferenceTeams(t);
        //Get each conference team, twice

        printDivisionOpponents();
        Division opponent = getSameDivisionOpponents(t, scheduleTeams);
//      System.out.println("Playing the AFC " + opponent.name);




        scheduleTeams.addAll(getSameConferenceTeams(t));

        scheduleTeams.add(createBye());

//      for (Team d : scheduleTeams){
//         System.out.print(d.name + " ");
//      }
//      System.out.println();

        //scheduleTeams(t, scheduleTeams);
    }

    public Team createBye(){
        return new Team("BYE", "BYE");
    }



//    public void scheduleTeams(Team t, ArrayList<Team> schedule){
//        //print out team schedule: debug
//
//        System.out.println("Scheduling for:" + t.name);
//        System.out.println();
//        for (Team a : schedule){
//            System.out.print(a.name + " ");
//        }
//        System.out.println();
//
//        ArrayList<Integer> weekList = new ArrayList<>(17);
//        //fill in week array
//        for (int i = 17; i > 0; i--){
//            weekList.add(i);
//        }
//        boolean weekFilled = false;
//        Random rand = new Random();
//
//        //Team schedule loop, go through all 17 weeks
//        for (int weekNum = 0; weekNum < weekList.size(); ++weekNum){
//            while (!weekFilled){
//                //check if week is filled BRANCH 1
//                if (t.hasGameOnWeek[weekNum]) {
//                    weekFilled = true;
//                    System.out.println("(branch 1) Week " + weekNum + ": " + t.teamSchedule[weekNum].name);
//                    continue;
//                }
//
//
//                //if it is not filled, randomly get a team
//                int opponentIndex = rand.nextInt(schedule.size());
//                if (schedule.get(opponentIndex) == null) continue;
//                Team opponent = schedule.get(opponentIndex);
//                //check if the opponent has a game on that week
//
//                //BRANCH 2
//                if (opponent.hasGameOnWeek[weekNum]){
//                    if (opponent.teamSchedule[weekNum].name.equals(t.name)){
//                        weekFilled = true;
//                        System.out.println("(branch 2) Week " + weekNum + ": " + opponent.name);
//                        continue;
//                    }
//                    //System.out.println("Week " + weekNum + ": " + opponent.name);
//
//                    continue;
//                };
//                //if not, schedule the teams
//
//
//                t.teamSchedule[weekNum] = opponent;
//                t.hasGameOnWeek[weekNum] = true;
//                //branch 3
//                if (opponent.name.equals("BYE")){
//                    schedule.set(opponentIndex, null);
//                    weekFilled = true;
//                    System.out.println("(branch 3) Week " + weekNum + ": " + opponent.name);
//                    continue;
//                }
//
//                opponent.teamSchedule[weekNum] = t;
//                opponent.hasGameOnWeek[weekNum] = true;
//                schedule.set(opponentIndex, null);
//                weekFilled = true;
//                //branch 4
//                System.out.println("(branch 4) Week " + weekNum + ": " + opponent.name);
//            }
//            weekFilled = false;
//
//        }
//
//        }

    public void scheduleTeam(Team t){

    }

    public void scheduleOpponent(Team opponent){

    }


    /**
     * Used to check if the schedule for a team is finished.    public void scheduleTeams(Team t){
        //print out team schedule: debug

        System.out.println("Scheduling for:" + t.name);
        System.out.println();

        ArrayList<Integer> weekList = new ArrayList<>(17);
        //fill in week array
        for (int i = 17; i > 0; i--){
            weekList.add(i);
        }
        boolean weekFilled = false;
        Random rand = new Random();

        //Team schedule loop, go through all 17 weeks
        for (int weekNum = 0; weekNum < weekList.size(); ++weekNum){
            while (!weekFilled){
                //check if week is filled BRANCH 1
                if (t.hasGameOnWeek[weekNum]) {
                    weekFilled = true;
                    System.out.println("(branch 1) Week " + weekNum + ": " + t.teamSchedule[weekNum].name);
                    continue;
                }


                //if it is not filled, randomly get a team
                int opponentIndex = rand.nextInt(TEAM_LIST.size());
                if (TEAM_LIST.get(opponentIndex) == null) continue;
                Team opponent = TEAM_LIST.get(opponentIndex);
                //check if the opponent has a game on that week

                //BRANCH 2
                if (opponent.hasGameOnWeek[weekNum]){
                    if (opponent.teamSchedule[weekNum].name.equals(t.name)){
                        weekFilled = t    public static void listMatches(ArrayList<String> teamList){
        int numTeams = teamList.size();

        if (teamList.size() % 2 != 0){
            teamList.add("BYE");
        }

        int numDays = (numTeams - 1);
        int halfSize = numTeams/2;

        ArrayList<String> teams = new ArrayList<>();

        teams.addAll(teamList);
        teams.remove(0);
        int teamsSize = teams.size();

        for (int day = 0; day  < numDays; day++){
            System.out.println("Day " + (day)); // day + 1
            int teamIdx = day % teamsSize;
            System.out.println("" + teams.get(teamIdx) + " vs " +  teamList.get(0));

            for (int idx = 1; idx< halfSize; idx++){
                int firstTeam = (day+idx) % teamsSize;
                int secondTeam = (day + teamsSize - idx) % teamsSize;
                System.out.println("" + teams.get(firstTeam) + " vs " + teams.get(secondTeam));
            }

        }
    }
rue;
                        System.out.println("(branch 2) Week " + weekNum + ": " + opponent.name);
                        continue;
                    }
                    //System.out.println("Week " + weekNum + ": " + opponent.name);

                    continue;
                };
                //if not, schedule the teams


                t.teamSchedule[weekNum] = opponent;
                t.hasGameOnWeek[weekNum] = true;
                //branch 3
                if (opponent.name.equals("BYE")){
                    TEAM_LIST.set(opponentIndex, null);
                    weekFilled = true;
                    System.out.println("(branch 3) Week " + weekNum + ": " + opponent.name);
                    continue;
                }

                opponent.teamSchedule[weekNum] = t;
                opponent.hasGameOnWeek[weekNum] = true;
                TEAM_LIST.set(opponentIndex, null);
                weekFilled = true;
                //branch 4
                System.out.println("(branch 4) Week " + weekNum + ": " + opponent.name);
            }
            weekFilled = false;

        }

    }

     * @param array
     * @return
     */
    public static boolean areAllTrue(boolean[] array)
    {
        for(boolean b : array) if(!b) return false;
        return true;
    }

    /**
     * This method gets the same division, same conference opponents for Team t.
     * e.g. Team t = Lions : Packers, Bears, Vikings
     * @param t
     * @return
     */
    public Division getSameDivisionOpponents(Team t, ArrayList<Team> teamList){
        Division opponent = null;
        if (t.conference.equals("AFC")){
            opponent = NFCOpponents[t.index];
        }
        else {
            opponent = AFCOpponents[t.index];
        }

        for (Team a : opponent.divisionTeamList){
            if (!t.name.equals(a.name)){
                teamList.add(a);
                teamList.add(a);
            }
        }
        return opponent;
    }

    public void getDivisionOpponents(){
        AFCOpponents = new Division[4];
        NFCOpponents = new Division[4];

        Conference AFC = conferenceList.get(0);
        Conference NFC = conferenceList.get(1);
        AFCOpponents[0] = NFC.divisionList.get(0);
        NFCOpponents[0] = AFC.divisionList.get(0);
        System.out.println(AFCOpponents[0].name + " plays " + NFCOpponents[0].name);

        AFCOpponents[1] = NFC.divisionList.get(1);
        NFCOpponents[1] = AFC.divisionList.get(1);

        AFCOpponents[2] = NFC.divisionList.get(2);
        NFCOpponents[2] = AFC.divisionList.get(2);

        AFCOpponents[3] = NFC.divisionList.get(3);
        NFCOpponents[3] = AFC.divisionList.get(3);
    }

    private void printDivisionOpponents(){
        for (int i = 0; i < 4; ++i){
        }
    }

    private void updateDivisionOpponents(){
        Division [] copy = NFCOpponents.clone();

        Division firstIndex = NFCOpponents[0];
        for (int i = 0; i < NFCOpponents.length; ++i){
            if (i == 3){
                NFCOpponents[i] = firstIndex;
                continue;
            }
            NFCOpponents[i] = copy[i+1];

        }
    }

    private void getTeamDivisionOpponents(Team t){
        int divisionIndex = t.index;
        boolean divisionIsEven = true;
        if (divisionIndex%2==1) divisionIsEven = false;
        int conferenceIndex = 0; //AFC
        if (t.conference.equals("NFC")) conferenceIndex = 1;
        //we want to schedule the divisions based on if the division index is even or odd
        //odd divisions will have one subtracted to know who they play (AFC North = 1 : they play 0)
        //even divisions will have one added to know who they play (AFC East = 0 : they play 1) (AFC South = 2 : they play 3)
        if (divisionIsEven){
            divisionIndex++;
        } else divisionIndex--;
        t.playsDivisionIndex = divisionIndex;
    }

    /**
     * Used to get the teams of the division of the same conference that Team t will be playing
     * e.g. Lions play the NFC West : 49ers, Seahawks, Rams, Cardinals
     * @param t
     * @return
     */
    private ArrayList<Team> getSameConferenceTeams(Team t){
        ArrayList<Team> teamList = new ArrayList<>();
        int divisionNum = currYear%4;
        System.out.println(t.name + " index " + t.index);

        int conference = 0;
        if (t.conference.equals("NFC")){
            conference = 1;
        }

        if (divisionNum == t.index){
            divisionNum++;
        }

        switch (conference){
            case 0 -> {
                for (Team a : NFCOpponents[t.playsDivisionIndex].divisionTeamList){
                    System.out.println(a.name);
                }
                teamList.addAll(NFCOpponents[t.playsDivisionIndex].divisionTeamList);
            }
            case 1 -> {
                teamList.addAll(AFCOpponents[divisionNum].divisionTeamList);
            }

        }
        // Find what teams have the same division finish and are in the same conference, but not playing
        // divisions as Team t.
        for (Division d : League.NFCOpponents){
            if (t.division.equals(d.name)) continue;
            if (d.name.equals(NFCOpponents[t.playsDivisionIndex].name)) continue;

            for (Team team : d.divisionTeamList){
                if (team.lastDivisionFinish == t.lastDivisionFinish){
                    teamList.add(team);
                }
            }
        }


        return teamList;
    }


    private ArrayList<Team> getDiffConferenceTeams(Team t){
        ArrayList<Team> teamList = new ArrayList<>();
        int conference = 0;
        Team teamToAdd;
        int divisionNum = currYear%4;
        if (divisionNum == t.index){
            divisionNum++;
        }
        //If the team's conference is the AFC, find the corresponding NFC division to play
        if (t.conference.equals("AFC")){
            conference = 1;
        }

        switch (conference){
            case 0 -> {
                teamList.addAll(League.NFCOpponents[divisionNum].divisionTeamList);
            }
            case 1 -> {
                teamList.addAll(League.AFCOpponents[divisionNum].divisionTeamList);

            }
        }




        return teamList;
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
        int lastDivisionFinish;
        Scanner input;
        try {
            input = new Scanner(teamFile);
        } catch (FileNotFoundException e) {
            input = new Scanner("basicTeams.txt");
        }
        input = input.useDelimiter(",");
        while (input.hasNextLine()) {
            conference = input.next();
            division = input.next();
            lastDivisionFinish = input.nextInt();
            city = input.next();
            team = input.nextLine();
            team = team.substring(1);

            Team teamToAdd = new Team(city, team, conference, division, lastDivisionFinish);
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
                            leagueList[0][0][nextIndex] = t;//conferenceList.get(0).divisionList.get(0).divisionTeamList.add(t);
                            nextIndex++;
                        }
                        case "North" -> {
                            leagueList[0][1][nextIndex] = t;//conferenceList.get(0).divisionList.get(1).divisionTeamList.add(t);
                            nextIndex++;
                        }
                        case "South" -> {
                            leagueList[0][2][nextIndex] = t;//conferenceList.get(0).divisionList.get(2).divisionTeamList.add(t);
                            nextIndex++;
                        }
                        case "West" -> {
                            leagueList[0][3][nextIndex] = t;//conferenceList.get(0).divisionList.get(3).divisionTeamList.add(t);
                            nextIndex++;
                        }
                    }
                    break;
                case "NFC":
                    switch (t.division) {
                        case "East" -> {
                            leagueList[1][0][nextIndex] = t; //conferenceList.get(1).divisionList.get(0).divisionTeamList.add(t);\
                            nextIndex++;
                        }
                        case "North" -> {
                            leagueList[1][1][nextIndex] = t;//conferenceList.get(1).divisionList.get(1).divisionTeamList.add(t);
                            nextIndex++;
                        }
                        case "South" -> {
                            leagueList[1][2][nextIndex] = t;//conferenceList.get(1).divisionList.get(2).divisionTeamList.add(t);
                            nextIndex++;
                        }
                        case "West" -> {
                            leagueList[1][3][nextIndex] = t;//conferenceList.get(1).divisionList.get(3).divisionTeamList.add(t);
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





