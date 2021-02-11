package MainClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import ServiceClasses.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.awt.TextArea;

public class App {
    public static int howManyPizzas;
    public static ArrayList<Team> teams;
    public static ArrayList<Pizza> pizzas;
    public static Scanner inputScanner;
    public static TextArea ta;
    public static String log;
    public static AppGUI gui;

    public static void main (String[] args) {
        gui = new AppGUI();
        ta = gui.textArea;
        log = "";
        appendToLog("Welcome to Christos\' Pizzeria!");
    }

    public static boolean importData () {
        try {
            String filename = gui.requestFile();
            if (filename.equals("nullnull")) return true;
            inputScanner = new Scanner (new File(filename));
            String[] currentData = inputScanner.nextLine().split(" ");
            App.howManyPizzas = Integer.parseInt(currentData[0]);

            teams = createTeams(Arrays.copyOfRange(currentData, 1, currentData.length));
            pizzas = createPizzas();
            inputScanner.close();
            appendToLog("Data imported successfully!");
            appendToLog("");
            return false;
        } catch (FileNotFoundException e) {
            appendToLog("File not found. Exiting...");
            System.exit(-1);
            return true;
        }
    }

    public static ArrayList<Team> createTeams (String[] currentData) {
        int teamCounter = 1;
        ArrayList<Team> tempTeams = new ArrayList<Team>();
        for (int i=0; i<3; i++) {                                    // for each of the teamgroups
            for (int j=0; j<Integer.parseInt(currentData[i]); j++) { // create currentData[i] teams with i+2 members
                tempTeams.add(new Team(teamCounter++, i+2));
            }
        }
        return tempTeams;
    }

    public static ArrayList<Pizza> createPizzas () {
        int pizzaCounter = 1;
        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
        while (inputScanner.hasNextLine()) {
            String[] currentData = inputScanner.nextLine().split(" ");
            Pizza currentPizza = new Pizza (pizzaCounter++, Integer.parseInt(currentData[0]));
            pizzas.add(currentPizza);
            try {
                for (int i=1; i<currentData.length; i++) {
                    currentPizza.addIngredient(currentData[i]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                appendToLog("Error in data. Maximum ingredients of current pizza already achieved.");
            } catch (IngredientAlreadyExistsException e) {
                appendToLog("Error in data. Ingredient already exists in pizza.");
            }
        }
        return pizzas;
    }

    public static void reportTeams () {
        appendToLog("There are " + teams.size() + " teams available.");
        for (Team team : teams) {
            appendToLogNoSeparator("Team " + team.getID() + ": ");
            team.reportTeam();
        }
        appendToLog("");
    }

    public static void reportPizzas () {
        appendToLog("There are " + pizzas.size() + " pizzas available.");
        for (Pizza pizza : pizzas) {
            appendToLogNoSeparator("Pizza " + pizza.getID() + ": ");
            pizza.reportPizza();
        }
        appendToLog("");
    }

    public static void distributePizzas () {
        try {
            for (Team team : teams) {
                for (int member=0; member<team.getMembers(); member++) {
                    Pizza aPizza = pizzas.get(0);
                    team.giveAPizza(aPizza);
                    pizzas.remove(0);
                    checkPizzaExistence();
                }
            }
        } catch (NoMorePizzasException e) {
            appendToLog("Pizzas are over!");
            return;
        }
    }

    public static void checkPizzaExistence () throws NoMorePizzasException {
        if (pizzas.size() == 0) throw new NoMorePizzasException();
    }

    public static void appendToLog (String text) {
        log = log + text + System.lineSeparator();
        ta.setText(log);
    }

    public static void appendToLogNoSeparator (String text) {
        log = log + text;
        ta.setText(log);
        ta.setCaretPosition(ta.getText().length());
    }
}
