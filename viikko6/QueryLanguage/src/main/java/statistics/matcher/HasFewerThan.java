package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {
    HasAtLeast hasAtLeast;
    Not not;
    
    public HasFewerThan(int value, String category) {
        hasAtLeast = new HasAtLeast(value, category);
        not = new Not(hasAtLeast);
    }
    
    @Override
    public boolean matches(Player p) {
        return not.matches(p);
    }    
}
