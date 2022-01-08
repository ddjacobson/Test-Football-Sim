
//TODO: put int 03 ints for each division team for easy testing access
public class test {
    public static void main(String args[]) {
        //Testing: Start the league
        NameFile.getFirstNames();
        NameFile.getLastNames();

        League myLeague = new League();

        int r = League.TEAM_LIST.get(31).passRtg;
        System.out.println(r);

//        NameFile.getFirstNames();
//        NameFile.getLastNames();
//        PlayerQB testQB = new PlayerQB();
//       testQB.printStats();
//       System.out.println();
//       PlayerTE test2 = new PlayerTE();
//       test2.printStats();

    }

}
