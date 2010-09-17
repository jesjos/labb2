import java.util.regex.*;

class Fotbollstest {
  
  public static void errorReport (int n) {
    System.err.println("Fel i test " + n + "!");
  }
  
  public static void main(String[] args) {
    String a = "a";
    String b = "b";
    String c = "c";
    
    int goal1 = 1;
    int goal2 = 2;
    
    FotbollsLag t1 = new FotbollsLag(a);
    FotbollsLag t2 = new FotbollsLag(b);
    FotbollsLag t3 = new FotbollsLag(c);
    FotbollsLag t4 = new FotbollsLag("d");
    FotbollsLag t5 = new FotbollsLag(a);
    
    t1.registreraMatch(goal1,0);
    t2.registreraMatch(0,goal2);
    t3.registreraMatch(1,1);
    t4.registreraMatch(1,1);
    
    // test 1 - names match
    if (!t1.getName().equals(a)) {
      errorReport(1);
    }
    
    // test 2 - målskillnad - positive and negative
    if (!(t1.getMålskillnad() == 1 && t2.getMålskillnad() == -2)){
      errorReport(2);
    }
    
    // test 3 - points match
    if (!(t1.getPoints() == 3 && t2.getPoints() == 0 && t3.getPoints() == 1)) {
      errorReport(3);
    }
    
    // test 4 - compareTo works
    if (! (t1.compareTo(t2) == 1 && t2.compareTo(t1) == -1 && t3.compareTo(t4) == 0)) {
      errorReport(4);
    }
    
    // test 5 - equals works
    if (!t1.equals(t5)) {
      errorReport(5);
    }
    
  }
}