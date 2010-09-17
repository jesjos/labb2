/**
 * Class FotbollsLag keeps track of a football team - its losses, wins, goals for, goals against, points.
 *
 * It implements the interface Comparable to enable lists of teams to be automatically sorted
 *
 * @author  Jesper Josefsson & Linus Oleander
 * @version 2010-09-16
 */

class FotbollsLag implements Comparable<FotbollsLag> {
  
  // For the purposes of printing length of name restricted to 15 characters.
  private String name;
  private int playedMatches, wins, losses, draws, goalsFor, goalsAgainst;
  
  public FotbollsLag(String theName) {
    
    // Name length restricted to 15, removes characters over limit
    if (theName.length() > 15) {
      name = theName.substring(0,15);
    }
    else {
      name = theName;
    }
    playedMatches = 0;
    wins = 0;
    losses = 0;
    draws = 0;
    goalsFor = 0;
    goalsAgainst = 0;
  }
  
  /**
   *  Registers a match, adding goals to table and calculating match outcome.
   *  @param gjordaMål      number of goals scored
   *  @param insläpptaMål   number of goals opponent scored
   */
  
  public void registreraMatch (int gjordaMål, int insläpptaMål) {
    if (gjordaMål < 0 || insläpptaMål < 0) {
      System.err.println("Errors: Goals can't be negative!");
    }
    
    playedMatches++;
    goalsFor += gjordaMål;
    goalsAgainst += insläpptaMål;
    
    if (gjordaMål > insläpptaMål) {
      wins++;
    }
    else if (gjordaMål == insläpptaMål) {
      draws++;
    }
    else {
      losses++;
    }
  }
  
  /**
   * @return name of team
   */
  
  // Produces a text representation of the team. Fills with whitespace if team name is shorter than 15 characters.
  public String toString () {
    int length = name.length();
    String nameAndWhiteSpace = name;
    for (int i = 0; i <= 15-length; i++) {
      nameAndWhiteSpace += " ";
    }
    return  (nameAndWhiteSpace + "\t" + playedMatches + "  " + wins + "  " + losses + "  " + 
            draws + "  " + goalsFor + "-" + goalsAgainst + "  " + getPoints() );
  }
  
  /**
   * @return current points
   */
  
  public int getPoints() {
    return wins*3 + draws*1;
  }
  
  /**
   * @return Goals for minus goals agains
   */
  
  public int getMålskillnad() {
    return goalsFor - goalsAgainst;
  }
  
  /**
   * @return Goals for
   */
  
  public int getGoalsFor() {
    return goalsFor;
  }
  
  public String getName() {
    return name;
  }
  
  public int compareTo(FotbollsLag annatLag){
    int a = compHelper(this.getPoints(), annatLag.getPoints());
    if (a != 0) {
      return a;
    }
    else {
      int b = compHelper(this.getMålskillnad(), annatLag.getMålskillnad());
      if (b != 0) {
        return b;
      }
      else {
        return compHelper(this.getGoalsFor(), annatLag.getGoalsFor());
      }
    }
    
  }
  
  private int compHelper (int a, int b){
    if (a == b) {
      return 0;
    }
    else if (a > b) {
      return 1;
    }
    else return -1;
  }
  
}