
package ohtu;

public class Player {
    private String name;
    private int goals;
    private int assists;
    private String team;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    } 

    @Override
    public String toString() {
        return name 
                + " team " + team 
                + " goals " + goals 
                + " assists " + assists;
    }
      
}
