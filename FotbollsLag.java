class FotbollsLag {
  
  private String name;
  private int playedMatches, wins, losses, draws, goalsFor, goalsAgainst;
  
  public FotbollsLag(String theName) {
    name = theName;
    playedMatches = 0;
    wins = 0;
    losses = 0;
    draws = 0;
    goalsFor = 0;
    goalsAgainst = 0;
  }
  
  public void registreraMatch (int gjordaMål, int insläpptaMål) {
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
  
  public String toString () {
    return  (name + "\t" + playedMatches + "  " + wins + "  " + losses + "  " + 
            draws + "  " + goalsFor + "-" + goalsAgainst + "  " + getPoints() );
  }
  
  public int getPoints() {
    return wins*3 + draws*1;
  }
  
  public int getMålskillnad() {
    return goalsFor - goalsAgainst;
  }
  
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