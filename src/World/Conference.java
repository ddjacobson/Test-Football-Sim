package World;

import Comparator.CompareTeamWinPct;

import java.util.ArrayList;
import java.util.Collections;

public class Conference {

    public String name;
    public ArrayList<Division> divisionList;
    public ArrayList<Team> teamList;

    public Conference(String name){
        this.name = name;
        teamList = new ArrayList<>();
        buildDivisions();
    }

    private void buildDivisions() {
        this.divisionList = new ArrayList<>();
        for (int division = 0; division < 4; division++){
            switch (division){
                case 0 -> divisionList.add(new Division(this,"East"));
                case 1 -> divisionList.add(new Division(this,"North"));
                case 2 -> divisionList.add(new Division(this,"South"));
                case 3 -> divisionList.add(new Division(this,"West"));
            }
        }
    }

    public void sortTeams(){
        Collections.sort(teamList, new CompareTeamWinPct());
        for (Team t : teamList){
            System.out.println(t.name);
        }
    }


}
