import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class FotbollsSerie {
  
  String name;
  ArrayList<FotbollsLag> teams;
  HashMap<String, FotbollsLag> nameMap;
  
  public FotbollsSerie (String theName, ArrayList<FotbollsLag> theTeams) {
    name = theName;
    teams.addAll(theTeams);
    updateMap();
  }
  
  public boolean registreraMatch (String namn1, String namn2, int mål1, int mål2) {
    if (!nameMap.containsKey(namn1) || !nameMap.containsKey(namn2)) {
      return false;
    }
    else {
      nameMap.get(namn1).registreraMatch(mål1, mål2);
      nameMap.get(namn1).registreraMatch(mål2, mål1);
      updateMap();
      return true;
    }
  }
  
  public FotbollsLag getTeam (String theName) {
    return nameMap.get(theName);
  }
  
  public void sortTabell() {

    Collections.sort(teams, teams.compare())

  }
  
  /*
  public void sortTabell() {
    FotbollsLag temp;
    boolean swap = true;
    int size = teams.size();
    int i;
    
   do {
      for (i = 0; i < size; i++) {
        if (teams.get(i).compare(teams.get(i + 1)) == 1) {
          
        }
      }
    } while (swap);
    
  }
  */
  
  private void updateMap () {
    for (FotbollsLag team : teams) {
      nameMap.put(team.getName(), team);
    }
  }
  
  
}