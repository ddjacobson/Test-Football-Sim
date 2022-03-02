package World;

import Comparator.CompareTeamWinPct;

import java.util.ArrayList;
import java.util.Collections;

public class Division {
    public String conferenceName;
    public int index;
    public String name;
    public ArrayList<Team> divisionTeamList;

    public Division(Conference conference, String name){
        this.name = name;
        this.conferenceName = conference.name;
        this.divisionTeamList = new ArrayList<>();
        int index = 0;


        if (conference.name.equals("NFC")){
            index = 1;
        }

        switch (name){
            case "East" -> {
                conference.teamList.add(League.leagueList[index][0][0]);
                conference.teamList.add(League.leagueList[index][0][1]);
                conference.teamList.add(League.leagueList[index][0][2]);
                conference.teamList.add(League.leagueList[index][0][3]);

                divisionTeamList.add(League.leagueList[index][0][0]);
                divisionTeamList.add(League.leagueList[index][0][1]);
                divisionTeamList.add(League.leagueList[index][0][2]);
                divisionTeamList.add(League.leagueList[index][0][3]);

            }
            case "North" -> {
                conference.teamList.add(League.leagueList[index][1][0]);
                conference.teamList.add(League.leagueList[index][1][1]);
                conference.teamList.add(League.leagueList[index][1][2]);
                conference.teamList.add(League.leagueList[index][1][3]);

                divisionTeamList.add(League.leagueList[index][1][0]);
                divisionTeamList.add(League.leagueList[index][1][1]);
                divisionTeamList.add(League.leagueList[index][1][2]);
                divisionTeamList.add(League.leagueList[index][1][3]);

            }
            case "South" -> {
                conference.teamList.add(League.leagueList[index][2][0]);
                conference.teamList.add(League.leagueList[index][2][1]);
                conference.teamList.add(League.leagueList[index][2][2]);
                conference.teamList.add(League.leagueList[index][2][3]);

                divisionTeamList.add(League.leagueList[index][2][0]);
                divisionTeamList.add(League.leagueList[index][2][1]);
                divisionTeamList.add(League.leagueList[index][2][2]);
                divisionTeamList.add(League.leagueList[index][2][3]);

            }
            case "West" -> {
                conference.teamList.add(League.leagueList[index][3][0]);
                conference.teamList.add(League.leagueList[index][3][1]);
                conference.teamList.add(League.leagueList[index][3][2]);
                conference.teamList.add(League.leagueList[index][3][3]);

                divisionTeamList.add(League.leagueList[index][3][0]);
                divisionTeamList.add(League.leagueList[index][3][1]);
                divisionTeamList.add(League.leagueList[index][3][2]);
                divisionTeamList.add(League.leagueList[index][3][3]);

            }
        }
        }

        public void sortTeams(){
            Collections.sort(divisionTeamList, new CompareTeamWinPct());
        }

        public void printRecords(){
            sortTeams();
            System.out.println(name + "\n");
            for (Team t : divisionTeamList){
                System.out.println(t.name + " " + t.getRecord());
            }
    }

}

