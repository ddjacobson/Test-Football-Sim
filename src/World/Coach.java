//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package World;

import java.util.List;
import java.util.Random;

public class Coach {
  public final int MAX_AGE = 80;
  public final int whiteWeight = 65;
  public final int blackWeight = 35;
  public String firstName;
  public String lastName;
  public int careerYears;
  public int age;
  public int race;
  Gameplan offensivePlan;
  Gameplan defensivePlan;
  public int careerWins = 0;
  public int careerLosses = 0;
  public int careerTies = 0;
  public String careerRecord;

  public Coach() {
    this.getNames();
    this.race = this.findRace();
    this.getRandGameplan();
  }

  private void getRandGameplan() {
    Random rand = new Random();
    int oChoice = rand.nextInt(5);
    this.offensivePlan = new Gameplan(oChoice, true);
  }

  private int findRace() {
    int larger = 65;
    Random rand = new Random();
    int num = rand.nextInt(0, 100);
    return num <= larger ? 1 : 0;
  }

  private void getNames() {
    Random rand = new Random();
    int numNames = 270;
    List nameList;
    switch(this.race) {
      case 0:
        nameList = NameFile.firstLinesWhite;
        break;
      case 1:
        nameList = NameFile.firstLinesBlack;
        break;
      default:
        nameList = NameFile.firstLinesWhite;
    }

    int randInt = rand.nextInt(numNames);
    List firstNames = (List)nameList.get(randInt);
    this.firstName = (String)firstNames.get(0);
    int randIntLast = rand.nextInt(1799);
    List lastNameList = NameFile.lastLines.get(randIntLast);
    this.lastName = ((String)lastNameList.get(0)).toLowerCase();
    String var10001 = this.lastName.substring(0, 1).toUpperCase();
    this.lastName = var10001 + this.lastName.substring(1);
  }
}
