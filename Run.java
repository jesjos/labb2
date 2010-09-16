import java.util.ArrayList;

class Run {
  public static void main (String[] args) {
    
    
    FotbollsLag team1 = new FotbollsLag("BK Hejsan");
    FotbollsLag team2 = new FotbollsLag("BK Nej");
    FotbollsLag team3 = new FotbollsLag("BKKK");
    
    ArrayList<FotbollsLag> list = new ArrayList<FotbollsLag>();
    
    list.add(team1);
    list.add(team2);
    list.add(team3);
    
        
    FotbollsSerie allsvenskan = new FotbollsSerie("allsvenskan", list);
    if (!allsvenskan.registreraMatch("BK Hejsan","BKKK",1,1)) {
      System.out.println("BAJS!!!");
    }
    allsvenskan.registreraMatch("BK Nej","BKKK", 5,1);
    System.out.println(allsvenskan.toString());
    
  }
}