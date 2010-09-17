import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import java.util.Collections;
import javax.swing.JOptionPane;

class FotbollsSerie {
  
  private final String name;
  private ArrayList<FotbollsLag> teams = new ArrayList<FotbollsLag>();
  
  // Maps team names to team references, for fast retrieval
  private HashMap<String, FotbollsLag> nameMap = new HashMap<String,FotbollsLag>();
  
  // Sets up series, updates HashMap to reflect the list of teams.
  public FotbollsSerie (String theName, ArrayList<FotbollsLag> theTeams) {
    name = theName;
    if (theTeams != null) {
      teams.addAll(theTeams);
      updateMap();
    }
  }
  
  /*  Checks whether both teams are in the series, if so - registers the match for both teams
      Returns true for success, false for failure. */
  public boolean registreraMatch (String name1, String name2, int goals1, int goals2) {
    if ((!nameMap.containsKey(name1) || !nameMap.containsKey(name2)) || (name1.equals(name2))) {
      return false;
    }
    else {
      nameMap.get(name1).registreraMatch(goals1, goals2);
      nameMap.get(name2).registreraMatch(goals2, goals1);
      return true;
    }
  }
  
  // Returns reference to team with matching name
  public FotbollsLag getLag (String theName) {
    return nameMap.get(theName);
  }
  
  /*  Reads an input dialog, parses the content and registers the match.
      Displays error messages if input can't be parsed or registration failed. 
      Both space and ; can be used as delimiters. */
      
  public void readResult () {
    
    /*  Compiles regexp used to parse the input - regexp yields 4 groups: the whole string, name team1, name team2,
        goals team1, goals team2 */
        
    Pattern p = Pattern.compile("([^-]+)[-|;](.+)[\\s|;](\\d+)[-|;](\\d+)");
    
    String input = JOptionPane.showInputDialog(null, "Enter match results. \nFormat as follows: FC Z-BK Hoppsan 3-1.");
    
    // Matcher applies the regexp to the input string
    Matcher m = p.matcher(input);
    
    /*  If m.find() fails, no match was found - display error message. 
        Else register match, check for success and inform the user */
        
    if (m.find()) {
      if (this.registreraMatch(m.group(1), m.group(2), Integer.valueOf(m.group(3)), Integer.valueOf(m.group(4)))) {
        JOptionPane.showMessageDialog(null,"Match registered successfully!\n" + 
                                      m.group(1) + "-" + m.group(2) + " " + m.group(3) + "-" + m.group(4));
      }
      else {
        JOptionPane.showMessageDialog(null, "Error: registration failed.");
      }
    }
    else {
      JOptionPane.showMessageDialog(null, "Error: could not interpret input.");
    }
    
  }
  
  // Sorts the table according to team points. Comparator reverseOrder() used to make sure list is in descending order
  public void sortTabell() {
    Collections.sort(teams, Collections.reverseOrder());
  }
  
  // Produces a text representation of the series. Doesn't add linebreak after last team. 
  public String toString() {
    sortTabell();
    int max = teams.size() - 1;
    String output = "";
    for (int i = 0;i < max; i++) {
      output += teams.get(i).toString() + "\n";
    }
    output += teams.get(max).toString();
    return output;
  }
  
  // Updates the HashMap to reflect the current state of the list of teams
  private void updateMap () {
    for (FotbollsLag team : teams) {
      if (!nameMap.containsKey(team.getName()))
        nameMap.put(team.getName(), team);
    }
  }
  
  private static void errorReport (int n) {
    System.err.println("Error in test #" + n + "!");
  }
  
  
  // Tests various methods in the class
  public static void main(String[] args) {
    FotbollsLag t1 = new FotbollsLag("team1");
    FotbollsLag t2 = new FotbollsLag("team2");
    FotbollsLag t3 = new FotbollsLag("team3");
    
    ArrayList<FotbollsLag> list = new ArrayList<FotbollsLag>();
    
    list.add(t1);
    list.add(t2);
    list.add(t3);
    
    FotbollsSerie s = new FotbollsSerie("allsvenskan", list);
    
    // test 1 - getLag returns reference to correct object
    if (! (s.getLag("team1") == t1)) {
      errorReport(1);
    }
    
    // test 2 - match registration works
    s.registreraMatch("team1", "team2", 3, 1);
    if (! (s.getLag("team1").getPoints() == 3 && s.getLag("team2").getPoints() == 0)) {
      errorReport(2);
    } 
    
    // test 3 - check that team order is correct on toString - this tests sorting and compareTo in FotbollsLag
    s.registreraMatch("team2", "team3", 2, 1);
    Pattern p = Pattern.compile("(\\D+\\d).*\\n(\\D+\\d).*\\n(\\D+\\d)");
    Matcher m = p.matcher(s.toString());
    while(m.find()) {
      if (! ( m.group(1).trim().equals("team1") &&
              m.group(2).trim().equals("team2") &&
              m.group(3).trim().equals("team3") )) {
                errorReport(3);
              }
    }
    
    // test 4 - a test of read result - prints the current status of the series so that user can check it,
    // prompts for further tests
    do {
         s.readResult();
         System.out.println(s.toString());
         System.out.println("");
    } while(JOptionPane.showConfirmDialog(null, "Would you like to perform another test?\n") == JOptionPane.YES_OPTION);
  
  }
  
  
}