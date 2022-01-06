
import java.util.ArrayList;
import java.util.HashMap;

public class Team {


    //Roster
    public HashMap<String, ArrayList<Player>> roster;
    final public int rosterSize = 53;


    public ArrayList<Team> teamList;
    public String name;
    public String city;
    public String conference;
    public String division;
    public String sameConfDivision;
    public int lastDivisionFinish; //0 = first ... 3 = last
    public int index; //used to access the division of the team
    public int lastGameOutcome; //-1 = loss, 1 = win, 0 = tie
    public int wins;
    public int losses;
    public int ties;
    public boolean [] hasGameOnWeek;
    public int divWins;
    public int playsDivisionIndex; //index of same conf division that the team plays

    Game [] schedule;
    Team [] teamSchedule;


    //bye team
    public Team(String city, String name){
        this.name = name;
        this.city = city;
        hasGameOnWeek = new boolean[17];




    }
    public Team(String city, String name, String conference, String division, int lastDivisionFinish){
        this.name = name;
        this.city = city;
        teamSchedule = new Team[17];
        hasGameOnWeek = new boolean[17];
        this.conference = conference;
        this.division = division;
        this.lastDivisionFinish = lastDivisionFinish;
        createRoster();
        switch (division){
            case "East" -> index = 0;
            case "North" -> index = 1;
            case "South" -> index = 2;
            case "West" -> index = 3;
        }
    }

    //Roster
    public void createRoster(){
        this.roster = new HashMap<>();
        ArrayList<Player> qbs = new ArrayList<>();
        ArrayList<Player> rbs = new ArrayList<>();
        ArrayList<Player> wrs = new ArrayList<>();
        ArrayList<Player> tes = new ArrayList<>();
        ArrayList<Player> tackles = new ArrayList<>();
        ArrayList<Player> guards = new ArrayList<>();
        ArrayList<Player> centers = new ArrayList<>();
        ArrayList<Player> dEnds = new ArrayList<>();
        ArrayList<Player> dTackles = new ArrayList<>();
        ArrayList<Player> olbs = new ArrayList<>();
        ArrayList<Player> ilbs = new ArrayList<>();
        ArrayList<Player> cbs = new ArrayList<>();
        ArrayList<Player> fs = new ArrayList<>();
        ArrayList<Player> ss = new ArrayList<>();
        ArrayList<Player> k = new ArrayList<>();
        ArrayList<Player> p = new ArrayList<>();


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
//        guards.add(new PlayerOG(this));
//        guards.add(new PlayerOG(this));
//        guards.add(new PlayerOG(this));
//        guards.add(new PlayerOG(this));
//        //create 1 OC
//        centers.add(new PlayerOC(this));
//        //create 4 DE
//        dEnds.add(new PlayerDE(this));
//        dEnds.add(new PlayerDE(this));
//        dEnds.add(new PlayerDE(this));
//        dEnds.add(new PlayerDE(this));
//        //create 3 DT
//        dTackles.add(new PlayerDT(this));
//        dTackles.add(new PlayerDT(this));
//        dTackles.add(new PlayerDT(this));
//
//        //create 5 OLB
//        olbs.add(new PlayerOLB(this));
//        olbs.add(new PlayerOLB(this));
//        olbs.add(new PlayerOLB(this));
//        olbs.add(new PlayerOLB(this));
//        olbs.add(new PlayerOLB(this));
//
//        //create 2 ILB
//        ilbs.add(new PlayerILB(this));
//        ilbs.add(new PlayerILB(this));
//        //create 6 CB
//        cbs.add(new PlayerCB(this));
//        cbs.add(new PlayerCB(this));
//        cbs.add(new PlayerCB(this));
//        cbs.add(new PlayerCB(this));
//        cbs.add(new PlayerCB(this));
//        cbs.add(new PlayerCB(this));
//
//        //create 2 FS
//        fs.add(new PlayerFS(this));
//        fs.add(new PlayerFS(this));
//
//        //create 2 SS
//        ss.add(new PlayerSS(this));
//        ss.add(new PlayerSS(this));
//
//        //create 1 K, P
//        k.add(new PlayerK(this));
//        p.add(new PlayerP(this));

        roster.put("Quarterbacks", qbs);
        roster.put("Running Backs", rbs);
        roster.put("Wide Receivers", wrs);
        roster.put("Tight Ends", tes);
        roster.put("Tackles", tackles);
        roster.put("Guards", guards);
        roster.put("Centers", centers);
        roster.put("Defensive Ends", dEnds);
        roster.put("Defensive Linemen", dTackles);
        roster.put("Outside Linebackers", olbs);
        roster.put("Inside Linebackers", ilbs);
        roster.put("Cornerbacks", cbs);
        roster.put("Free Safeties", fs);
        roster.put("Strong Safeties", ss);
        roster.put("Kickers", k);
        roster.put("Punters", p);

        //printTEs();
    }

    public void printQBs(){
        for (Player p : roster.get("Quarterbacks")){
            p.printStats();
            System.out.println();
        }
    }

    public void printRBs(){
        for (Player p : roster.get("Running Backs")){
            p.printStats();
            System.out.println();

        }
    }

    public void printWRs(){
        for (Player p : roster.get("Wide Receivers")){
            p.printStats();
            System.out.println();

        }
    }

    public void printTEs(){
        for (Player p : roster.get("Tight Ends")){
            p.printStats();
            System.out.println();

        }
    }

    public void printOTs(){
        for (Player p : roster.get("Tackles")){
            p.printStats();
        }
    }

    public void printOGs(){
        for (Player p : roster.get("Guards")){
            p.printStats();
        }
    }

    public void printOCs(){
        for (Player p : roster.get("Centers")){
            p.printStats();
        }
    }

    public void printDE(){
        for (Player p : roster.get("Defensive Ends")){
            p.printStats();
        }
    }

    public void printDTs(){
        for (Player p : roster.get("Defensive Tackles")){
            p.printStats();
        }
    }
    public void printOLBs(){
        for (Player p : roster.get("Outside Linebackers")){
            p.printStats();
        }
    }

    public void printILBs(){
        for (Player p : roster.get("Inside Linebackers")){
            p.printStats();
        }
    }
    public void printCBs(){
        for (Player p : roster.get("Cornerbacks")){
            p.printStats();
        }
    }

    public void printFSs(){
        for (Player p : roster.get("Free Safeties")){
            p.printStats();
        }
    }

    public void printSSs(){
        for (Player p : roster.get("Strong Safeties")){
            p.printStats();
        }
    }







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

