
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private int goals;
    private int assists;
    private String team;
    private String nationality;

    public String getNationality() {
        return nationality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public int getTotal() {
        return goals + assists;
    }
    
    @Override
    public int compareTo(Player player) {
        return Integer.compare(this.getTotal(), player.getTotal());
    }
    
    @Override
    public String toString() {
        String string = String.format("%-20s%-5s%-1s + %-1s = %s",
                name, team, goals, assists, this.getTotal());
        return string;
    }
      
}
