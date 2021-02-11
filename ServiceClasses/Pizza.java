package ServiceClasses;

import java.util.ArrayList;
import java.lang.String;

public class Pizza {
    int howManyIngredients;
    ArrayList<String> ingredients = new ArrayList<String>();

    public Pizza (int ingredients) {
        this.howManyIngredients = ingredients;
    }

    public void addIngredient (String ingredient) throws ArrayIndexOutOfBoundsException, IngredientAlreadyExistsException {
        if (this.ingredients.size() == howManyIngredients) throw new ArrayIndexOutOfBoundsException();
        if (this.ingredients.contains(ingredient)) throw new IngredientAlreadyExistsException();
        this.ingredients.add(ingredient);
    }
    
    public void reportPizza () {
        System.out.print("Ingredients (" + howManyIngredients + "): ");
        for (int i=0; i<howManyIngredients; i++) {
            System.out.print(ingredients.get(i) + " ");
        }
        System.out.print("\n");
    }
}