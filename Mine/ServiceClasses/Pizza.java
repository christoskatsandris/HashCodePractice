package ServiceClasses;

import java.util.ArrayList;
import java.lang.String;
import MainClasses.App;

public class Pizza {
    int pizzaID = 0;
    int howManyIngredients;
    ArrayList<String> ingredients = new ArrayList<String>();

    public Pizza (int pizzaID, int ingredients) {
        this.pizzaID = pizzaID;
        this.howManyIngredients = ingredients;
    }

    public void addIngredient (String ingredient) throws ArrayIndexOutOfBoundsException, IngredientAlreadyExistsException {
        if (this.ingredients.size() == howManyIngredients) throw new ArrayIndexOutOfBoundsException();
        if (this.ingredients.contains(ingredient)) throw new IngredientAlreadyExistsException();
        this.ingredients.add(ingredient);
    }
    
    public void reportPizza () {
        App.appendToLogNoSeparator("Ingredients (" + howManyIngredients + "): ");
        for (int i=0; i<howManyIngredients; i++) {
            App.appendToLogNoSeparator(ingredients.get(i) + " ");
        }
        App.appendToLogNoSeparator("\n");
    }

    public int getID () {
        return this.pizzaID;
    }
}