package ServiceClasses;

import java.lang.String;
import java.util.ArrayList;

import MainClasses.App;

public class Team {
    int teamID;
    int members;
    ArrayList<Pizza> pizzasGiven = new ArrayList<Pizza>();
    ArrayList<String> ingredientsGiven = new ArrayList<String>();
    int howManyIngredientsGiven, howManyUniqueIngredientsGiven;

    public Team (int teamID, int members) {
        this.teamID = teamID;
        this.members = members;
    }
    public void giveAPizza (Pizza pizza) throws ArrayIndexOutOfBoundsException {
        if (this.pizzasGiven.size() == this.members) throw new ArrayIndexOutOfBoundsException();
        this.pizzasGiven.add(pizza);
        for (String ingredient : pizza.ingredients) {
            if (!ingredientsGiven.contains(ingredient)) ingredientsGiven.add(ingredient);
        }
        App.appendToLog("Pizza #" + pizza.getID() + " was given to Team #" + this.getID() + ".");
    }

    public void reportTeam () {
        App.appendToLog("Members: " + members);
    }

    public void calculateHowManyIngredients () {
        howManyIngredientsGiven = 0;
        for (Pizza pizza : pizzasGiven) {
            howManyIngredientsGiven += pizza.howManyIngredients;
        }

        howManyUniqueIngredientsGiven = ingredientsGiven.size();
    }

    public int getMembers () {
        return members;
    }

    public int getID () {
        return this.teamID;
    }
}
