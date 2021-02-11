package MainClasses;

import java.awt.*;
import java.awt.event.*;

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
        dialog.add(msg);

        Button okButton = new Button ("OK");
        okButton.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        dialog.add(okButton);

        dialog.setVisible(true);
    }
}


