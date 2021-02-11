import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import ServiceClasses.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;

public class App {
    public static int howManyPizzas;
    public static ArrayList<Team> teams;
    public static ArrayList<Pizza> pizzas;
    public static Scanner inputScanner;

    public static void main (String[] args) {
        importData();
        reportTeams();
        reportPizzas();
    }

    public static void importData () {
        try {
            inputScanner = new Scanner (new File("InputFiles\\a_example.in"));
            String[] currentData = inputScanner.nextLine().split(" ");
            App.howManyPizzas = Integer.parseInt(currentData[0]);

            teams = createTeams(Arrays.copyOfRange(currentData, 1, currentData.length));
            pizzas = createPizzas();
            inputScanner.close();         
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Team> createTeams (String[] currentData) {
        ArrayList<Team> tempTeams = new ArrayList<Team>();
        for (int i=0; i<3; i++) {                                    // for each of the teamgroups
            for (int j=0; j<Integer.parseInt(currentData[i]); j++) { // create currentData[i] teams with i+2 members
                tempTeams.add(new Team(i+2));
            }
        }
        return tempTeams;
    }

    public static ArrayList<Pizza> createPizzas () {
        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
        while (inputScanner.hasNextLine()) {
            String[] currentData = inputScanner.nextLine().split(" ");
            Pizza currentPizza = new Pizza (Integer.parseInt(currentData[0]));
            pizzas.add(currentPizza);
            try {
                for (int i=1; i<currentData.length; i++) {
                    currentPizza.addIngredient(currentData[i]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error in data. Maximum ingredients of current pizza already achieved.");
            } catch (IngredientAlreadyExistsException e) {
                System.out.println("Error in data. Ingredient already exists in pizza.");
            }
        }
        return pizzas;
    }

    public static void reportTeams () {
        System.out.println("There are " + teams.size() + " teams available.");
        for (int i=0; i<teams.size(); i++) {
            System.out.print("Team " + (i+1) + ": ");
            teams.get(i).reportTeam();
        }
    }

    public static void reportPizzas () {
        System.out.println("There are " + pizzas.size() + " pizzas available.");
        for (int i=0; i<pizzas.size(); i++) {
            System.out.print("Pizza " + (i+1) + ": ");
            pizzas.get(i).reportPizza();
        }
    }
}
