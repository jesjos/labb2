class Run {
  public static void main (String[] args) {
    
    Fotbollslag team = new Fotbollslag("BK Hejsan");
    team.registreraMatch(3,1);
    System.out.println(team.toString());
    
  }
}