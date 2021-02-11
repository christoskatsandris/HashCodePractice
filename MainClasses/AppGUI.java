package MainClasses;

import java.awt.*;
import java.awt.event.*;
import java.util.InputMismatchException;

public class AppGUI extends Frame {
    private static final long serialVersionUID = 1L;
    Button [] buttons = new Button [5];
    public TextArea textArea;
    Font normalFont, headerFont;
    
    public AppGUI () {
        normalFont = new Font("Segoe UI", Font.PLAIN, 14);
        headerFont = new Font("Segoe UI", Font.BOLD, 20);
        prepareWindow();
        addHeaderAndTextArea();
        addButtons();        
        setVisible(true);
    }

    void prepareWindow () {
        setBounds(100,100,1000,800);
        setFont(normalFont);
        setLayout(null);
        setResizable(false);
        this.addWindowListener(new WindowAdapter () {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        });
    }

    void addHeaderAndTextArea () {
        Label header = new Label ("Christos\' Pizzeria");
        header.setBounds(400, 50, 200, 50);
        header.setAlignment(Label.CENTER);
        header.setFont(headerFont);
        add(header);

        textArea = new TextArea ();
        textArea.setFont(normalFont);
        textArea.setBounds(10, 300, 980, 490);
        add(textArea);
    }

    void addButtons () {
        buttonCreate("Import Data", 20, 100, 130, 30, "importData");
        buttonCreate("Report Teams", 20, 150, 130, 30, "reportTeams");
        buttonCreate("Report Pizzas", 20, 200, 130, 30, "reportPizzas");
        buttonCreate("Distribute Pizzas", 20, 250, 130, 30, "distributePizzas");        
        buttonCreate("Close", 850, 150, 100, 100, "systemExit");        
    }

    void buttonCreate (String label, int x, int y, int width, int height, String labelForActionListener) {
        Button button = new Button (label);
        button.setBounds(x, y, width, height);
        button.addActionListener(new ButtonHandler(button, labelForActionListener));
        add(button);
    }

    public String requestFile () {
        FileDialog dialog = new FileDialog(this, "Choose a file", FileDialog.LOAD);
        dialog.setDirectory(System.getProperty("user.dir"));
        dialog.setVisible(true);
        String filename = dialog.getDirectory() + dialog.getFile();
        return filename;
    }

    public void throwErrorDialog () {
        Dialog dialog = new Dialog (this, "Error");
        dialog.setLayout(new FlowLayout());
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.addWindowListener(new WindowAdapter () {
            public void windowClosing (WindowEvent e) {
                dialog.setVisible(false);
            }
        });

        Label msg = new Label("You should first click all the previous buttons.");
        // msg.setBounds(50, 50, 200, 100);
        // msg.setFont(normalFont);
        dialog.add(msg);

        Button okButton = new Button ("OK");
        // okButton.setBounds(150, 200, 100, 50);
        okButton.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        dialog.add(okButton);

        dialog.setVisible(true);
    }
}

class ButtonHandler implements ActionListener {
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
