import World.League;
import World.NameFile;
import World.Game;
import World.Team;

import static World.League.TEAM_LIST;

//TODO: put int 03 ints for each division team for easy testing access
public class test {
    public static void main(String args[]) {
        //Testing: Start the league
        NameFile.getFirstNames();
        NameFile.getLastNames();

        League myLeague = new League();

//        Game game = new Game(TEAM_LIST.get(0), TEAM_LIST.get(17), 0);
//        game.playGame();




    }

}
