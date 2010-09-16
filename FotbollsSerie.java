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
  
  // Creates the series in question, copies teams from input to teams list and updates the hash map
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
    if (!nameMap.containsKey(name1) || !nameMap.containsKey(name2)) {
      return false;
    }
    else {
      nameMap.get(name1).registreraMatch(goals1, goals2);
      nameMap.get(name2).registreraMatch(goals2, goals1);
      return true;
    }
  }
  
  public FotbollsLag getLag (String theName) {
    return nameMap.get(theName);
  }
  
  /*  Reads an input dialog, parses the content and registers the match.
      Displays error messages if input can't be parsed or registration failed. 
      Both space and ; can be used as delimiters. */
      
  public void readResult () {
    
    /*  Compiles regexp used to parse the input - regexp yields 4 groups: the whole string, name team1, name team2,
        goals team1, goals team2 */
        
    Pattern p = Pattern.compile("([^-]+)[-|;](.+)\\s(\\d+)[-|;](\\d+)");
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
  
  
}