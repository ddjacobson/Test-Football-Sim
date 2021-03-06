
import java.util.ArrayList;

public class Conference {

    public String name;
    public ArrayList<Division> divisionList;

    public Conference(String name){
        this.name = name;
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


}
