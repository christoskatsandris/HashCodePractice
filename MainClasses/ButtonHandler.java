package MainClasses;

import java.awt.*;
import java.awt.event.*;
import java.util.InputMismatchException;


public class ButtonHandler implements ActionListener {
    public static Thread mainThread;
    static int order = 0;
    Button buttonPressed;
    String label;
    ButtonHandler (Button buttonPressed, String label) {
        this.buttonPressed = buttonPressed;
        this.label = label;
    }
    public void actionPerformed (ActionEvent e) throws InputMismatchException {
        boolean aborted = false;
        try {
            checkRightOrder(label, order);
        } catch (InputMismatchException f) {
            App.gui.throwErrorDialog();
            return;
        }
        switch (label) {
            case "importData": aborted = App.importData(); break;
            case "reportTeams": App.reportTeams(); break;
            case "reportPizzas": App.reportPizzas(); break;
            case "distributePizzas": App.distributePizzas(); break;
            case "systemExit": System.exit(0); break;
            default: throw new InputMismatchException();
        }
        if (!aborted) {
            order++;
            buttonPressed.setEnabled(false);
        }        
    }

    void checkRightOrder (String buttonPressed, int order) throws InputMismatchException {
        switch (buttonPressed) {
            case "reportTeams": if (order != 1) throw new InputMismatchException(); break;
            case "reportPizzas": if (order != 2) throw new InputMismatchException(); break;
            case "distributePizzas": if (order != 3) throw new InputMismatchException(); break;
            default: break;
        }
    }
}