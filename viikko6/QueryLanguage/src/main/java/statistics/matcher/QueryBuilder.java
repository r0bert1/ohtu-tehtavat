package statistics.matcher;

import java.util.ArrayList;

public class QueryBuilder {
    And and;
    ArrayList<Matcher> matchers;
    
    public QueryBuilder() {
        matchers = new ArrayList<>();
        and = new And(new All());
    }
    
    public And build() {
        return and;
    }
    
    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        this.and = new And(copyToArray(matchers));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        this.and = new And(copyToArray(matchers));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        this.and = new And(copyToArray(matchers));
        return this;
    }
    
    private Matcher[] copyToArray(ArrayList<Matcher> matchers) {
        Matcher[] matcherArray = new Matcher[matchers.size()];
        for (int i = 0; i < matchers.size(); i++) {
            matcherArray[i] = matchers.get(i);
        }
        return matcherArray;
    }
}
