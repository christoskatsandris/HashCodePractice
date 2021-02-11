package ServiceClasses;

import java.util.ArrayList;

public class Team {
    int members;
    ArrayList<Pizza> pizzasGiven = new ArrayList<Pizza>();

    public Team (int members) {
        this.members = members;
    }
    public int giveAPizza (Pizza pizza) {
        if (this.pizzasGiven.size() == this.members) return -1;
        this.pizzasGiven.add(pizza);
        return 0;
    }

    public void reportTeam () {
        System.out.println("Members: " + members);
    }
}
