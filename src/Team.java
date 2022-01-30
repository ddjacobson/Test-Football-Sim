
import java.util.*;

public class Team {

    //Roster
    public HashMap<String, ArrayList<Player>> roster;
    final public int rosterSize = 53;


    public ArrayList<Team> schedule;
    public String name;
    public String city;
    public String conference;
    public String division;
    public String sameConfDivision;
    public int lastDivisionFinish; //0 = first ... 3 = last
    public int index; //used to access the division of the team
    public int teamIndex;
    public int lastGameOutcome; //-1 = loss, 1 = win, 0 = tie
    public int wins;
    public int losses;
    public int ties;
    public boolean [] hasGameOnWeek;
    public int divWins;
    public int playsDivisionIndex; //index of same conf division that the team plays


    Team [] teamSchedule;

    public int gameScore;


    ArrayList<PlayerQB> qbs;
    ArrayList<PlayerRB> rbs;
    ArrayList<PlayerWR> wrs;
    ArrayList<PlayerTE> tes;
    ArrayList<PlayerOT> tackles;
    ArrayList<PlayerOG> guards;
    ArrayList<PlayerOC> centers;
    ArrayList<PlayerDE> dEnds;
    ArrayList<PlayerDT> dTackles;
    ArrayList<PlayerOLB> olbs;
    ArrayList<PlayerILB> ilbs;
    ArrayList<PlayerCB> cbs;
    ArrayList<PlayerFS> fs;
    ArrayList<PlayerSS> ss;
    ArrayList<Player> k;
    ArrayList<Player> p;


    public int passRtg;
    public int runRtg;

    //bye team
    public Team(String city, String name){
        this.name = name;
        this.city = city;
        hasGameOnWeek = new boolean[17];




    }
    public Team(int tIndex, String city, String name, String conference, String division, int lastDivisionFinish){
        this.name = name;
        this.city = city;
        teamSchedule = new Team[17];
        schedule = new ArrayList<>(17);
        hasGameOnWeek = new boolean[17];
        this.teamIndex = tIndex;
        this.conference = conference;
        this.division = division;
        this.lastDivisionFinish = lastDivisionFinish;
        createRoster();

        getTeamStats();

        switch (division){
            case "East" -> index = 0;
            case "North" -> index = 1;
            case "South" -> index = 2;
            case "West" -> index = 3;
        }
    }

    private void getTeamStats() {
        setPassRtg();
        setRunRtg();
    }

    //Roster
    public void createRoster(){
        this.roster = new HashMap<>();
        qbs = new ArrayList<>();
        rbs = new ArrayList<>();
        wrs = new ArrayList<>();
        tes = new ArrayList<>();
        tackles = new ArrayList<>();
        guards = new ArrayList<>();
        centers = new ArrayList<>();
        dEnds = new ArrayList<>();
        dTackles = new ArrayList<>();
        olbs = new ArrayList<>();
        ilbs = new ArrayList<>();
        cbs = new ArrayList<>();
        fs = new ArrayList<>();
        ss = new ArrayList<>();
        k = new ArrayList<>();
        p = new ArrayList<>();


        //create 3 qbs
        qbs.add(new PlayerQB(this));
        qbs.add(new PlayerQB(this));
        qbs.add(new PlayerQB(this));
        //create 4 rbs
        rbs.add(new PlayerRB(this));
        rbs.add(new PlayerRB(this));
        rbs.add(new PlayerRB(this));
        rbs.add(new PlayerRB(this));
        //create 6 wrs
        wrs.add(new PlayerWR(this));
        wrs.add(new PlayerWR(this));
        wrs.add(new PlayerWR(this));
        wrs.add(new PlayerWR(this));
        wrs.add(new PlayerWR(this));
        wrs.add(new PlayerWR(this));

        //create 4 tes
        tes.add(new PlayerTE(this));
        tes.add(new PlayerTE(this));
        tes.add(new PlayerTE(this));
        tes.add(new PlayerTE(this));

        //create 4 OT
        tackles.add(new PlayerOT(this));
        tackles.add(new PlayerOT(this));
        tackles.add(new PlayerOT(this));
        tackles.add(new PlayerOT(this));

        //create 4 OG
        guards.add(new PlayerOG(this));
        guards.add(new PlayerOG(this));
        guards.add(new PlayerOG(this));
        guards.add(new PlayerOG(this));
//        //create 1 OC
        centers.add(new PlayerOC(this));
//        //create 4 DE
        dEnds.add(new PlayerDE(this));
        dEnds.add(new PlayerDE(this));
        dEnds.add(new PlayerDE(this));
        dEnds.add(new PlayerDE(this));
//        //create 3 DT
        dTackles.add(new PlayerDT(this));
        dTackles.add(new PlayerDT(this));
        dTackles.add(new PlayerDT(this));
//        //create 5 OLB
        olbs.add(new PlayerOLB(this));
        olbs.add(new PlayerOLB(this));
        olbs.add(new PlayerOLB(this));
        olbs.add(new PlayerOLB(this));
        olbs.add(new PlayerOLB(this));
//
//        //create 2 ILB
        ilbs.add(new PlayerILB(this));
        ilbs.add(new PlayerILB(this));
//        //create 6 CB
        cbs.add(new PlayerCB(this));
        cbs.add(new PlayerCB(this));
        cbs.add(new PlayerCB(this));
        cbs.add(new PlayerCB(this));
        cbs.add(new PlayerCB(this));
        cbs.add(new PlayerCB(this));
//
//        //create 2 FS
        fs.add(new PlayerFS(this));
        fs.add(new PlayerFS(this));
//
//        //create 2 SS
        ss.add(new PlayerSS(this));
        ss.add(new PlayerSS(this));
//
//        //create 1 K, P
//        k.add(new PlayerK(this));
//        p.add(new PlayerP(this));

//        roster.put("QB", qbs);
//        roster.put("RB", rbs);
//        roster.put("WR", wrs);
//        roster.put("TE", tes);
//        roster.put("OT", tackles);
//        roster.put("OG", guards);
//        roster.put("OC", centers);
//        roster.put("DE", dEnds);
//        roster.put("DL", dTackles);
//        roster.put("OLB", olbs);
//        roster.put("ILB", ilbs);
//        roster.put("CB", cbs);
//        roster.put("FS", fs);
//        roster.put("SS", ss);
//        roster.put("K", k);
//        roster.put("P", p);

        sortRoster();

       // printRBs();

    }

    private void sortRoster() {

        //Collections.sort(qbs, new Comparator<Player>());


    }

    public void printQBs(){
        for (Player p : roster.get("QB")){
            p.printStats();
            System.out.println();
        }
    }

    public void printRBs(){
        for (Player p : roster.get("RB")){
            p.printStats();
            System.out.println();

        }
    }

    public void printWRs(){
        for (Player p : roster.get("WR")){
            p.printStats();
            System.out.println();

        }
    }

    public void printTEs(){
        for (Player p : roster.get("TE")){
            p.printStats();
            System.out.println();

        }
    }

    public void printOTs(){
        for (Player p : roster.get("OT")){
            p.printStats();
        }
    }

    public void printOGs(){
        for (Player p : roster.get("OG")){
            p.printStats();
        }
    }

    public void printOCs(){
        for (Player p : roster.get("OC")){
            p.printStats();
        }
    }

    public void printDE(){
        for (Player p : roster.get("DE")){
            p.printStats();
        }
    }

    public void printDTs(){
        for (Player p : roster.get("DT")){
            p.printStats();
        }
    }
    public void printOLBs(){
        for (Player p : roster.get("OLB")){
            p.printStats();
        }
    }

    public void printILBs(){
        for (Player p : roster.get("ILB")){
            p.printStats();
        }
    }
    public void printCBs(){
        for (Player p : roster.get("CB")){
            p.printStats();
        }
    }

    public void printFSs(){
        for (Player p : roster.get("FS")){
            p.printStats();
        }
    }

    public void printSSs(){
        for (Player p : roster.get("SS")){
            p.printStats();
        }
    }

    public void setPassRtg(){
        int passRtg = 0;
        int numPlayers = 0;
        //passRtg
        for (Player p : qbs){
            if (numPlayers > 0) break; //only want the first available qb
            if (!p.isInjured){
                passRtg += p.overall;
                numPlayers++;
            }
        }

        for (Player p : wrs){
            if (numPlayers > 3) break; //want one qb plus THREE wrs
            if (!p.isInjured){
                passRtg += p.overall;
                numPlayers++;
            }
        }

        for (Player p : tes){
            if (numPlayers > 4)break; //want one more TE
            if (!p.isInjured){
                passRtg += p.overall;
                numPlayers++;
            }
        }
        //use OL method to get rating
        //passRtg += getOLRtg();
        //numPlayers += 5; //5 more OL

        this.passRtg = passRtg / numPlayers;
        }

    public void setRunRtg(){
        int runRtg = 0;
        int numPlayers = 0;
        //passRtg
        for (Player p : rbs){
            if (numPlayers > 1) break; //only want the top two rbs
            if (!p.isInjured){
                runRtg += p.overall;
                numPlayers++;
            }
        }
        //runRtg += OLRtg();
        //numPlayers += 5 //5 of our OL

        this.runRtg = runRtg / numPlayers;

    }


//    public int getOLRtg(){
//        int passRtg = 0;
//        int numPlayers = 0;
//        for (Player p : tackles{
//            if (numPlayers > 1) {
//                break; //want two Ts
//            }
//            if (!p.isInjured){
//                passRtg += p.overall;
//                numPlayers++;
//            }
//        }
//
//        for (Player p : roster.get("OG")){
//            if (numPlayers > 3) break; //want one qb plus THREE wrs
//            if (!p.isInjured){
//                passRtg += p.overall;
//                numPlayers++;
//            }
//        }
//
//        for (Player p : roster.get("OC")) {
//            if (numPlayers > 5) break; //want one qb plus THREE wrs
//            if (!p.isInjured) {
//                passRtg += p.overall;
//                numPlayers++;
//            }
//        }
//
//        return passRtg;
//
//
//        }







    public boolean checkRoster(){
        if (!hasEnoughPlayers()) return false;
        return false;
    }

    public boolean hasEnoughPlayers(){
        return this.roster.size() == rosterSize;
    }




    /**
     *
     * @param outcome -1 = loss, 1 = win, 0 = tie
     */
    public void updateRecords(int outcome){
        switch (outcome){
            case 1 -> wins++;
            case -1 -> losses++;
            default -> ties++;
        }

    }

    public String getRecord(){
        String s;
        if (ties == 0){
            s = wins + "-"  + losses;
        }
        else {
           s = wins + "-" + losses + "-" + ties;
        }
        return s;
    }


    //Schedule creation

    public void getScheduleTeams(){
        ArrayList<Team> conferenceTeams = getConferenceTeams();
        //Get each conference team, twice
        ArrayList<Team> copy = (ArrayList<Team>) conferenceTeams.clone();
        conferenceTeams.add(copy.get(0));
        conferenceTeams.add(copy.get(1));
        conferenceTeams.add(copy.get(2));


        int finish = this.lastDivisionFinish;


        for (Team t : conferenceTeams){
            System.out.println(t.name);
        }




    }

    private ArrayList<Team> getConferenceTeams(){
        ArrayList<Team> teamList = new ArrayList<>();
        int conference = 0;
        Team teamToAdd;
        if (this.conference.equals("NFC")){
            conference = 1;
        }

            switch (this.division){
                case "East" -> {
                    for (int i = 0; i < 4; ++i){
                        teamToAdd = League.leagueList[conference][0][i];
                        if (this.name.equals(teamToAdd.name)) continue;
                        teamList.add(teamToAdd);
                    }
                }
                case "North" -> {
                    for (int i = 0; i < 4; ++i){
                        teamToAdd = League.leagueList[conference][1][i];
                        if (this.name.equals(teamToAdd.name)) continue;
                        teamList.add(teamToAdd);
                    }

                }
                case "South" -> {
                    for (int i = 0; i < 4; ++i){
                        teamToAdd = League.leagueList[conference][2][i];
                        if (this.name.equals(teamToAdd.name)) continue;
                        teamList.add(teamToAdd);
                    }

                }
                case "West" -> {
                    for (int i = 0; i < 4; ++i){
                        teamToAdd = League.leagueList[conference][3][i];
                        if (this.name.equals(teamToAdd.name)) continue;
                        teamList.add(teamToAdd);
                    }

                }
            }
        return teamList;
}


}

