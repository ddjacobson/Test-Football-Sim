
package World;

public class Formation {
  public String formationName;
  public int formationChance;
  private int numRBs;
  private int numFBs;
  private int numWRs;
  private int numTEs;
  private boolean hasRB;
  private boolean hasFB;
  private boolean hasWR;
  private boolean hasTE;

  public Formation(int rbs, int fbs, int wrs, int tes, int chance, String name) {
    this.formationName = name;
    this.formationChance = chance;
    this.numRBs = rbs;
    this.numFBs = fbs;
    this.numTEs = tes;
    this.numWRs = wrs;
    this.setBools();
  }

  public Formation(int des, int dts, int ngs, int olbs, int mlbs, int cbs, int fs, int ss, String name) {
  }

  private void setBools() {
    if (this.numWRs > 0) {
      this.hasWR = true;
    }

    if (this.numFBs > 0) {
      this.hasFB = true;
    }

    if (this.numTEs > 0) {
      this.hasTE = true;
    }

    if (this.numRBs > 0) {
      this.hasRB = true;
    }

  }

  public int getNumRBs() {
    return this.numRBs;
  }

  public int getNumFBs() {
    return this.numFBs;
  }

  public int getNumWRs() {
    return this.numWRs;
  }

  public int getNumTEs() {
    return this.numTEs;
  }
}
