package gameui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton newGame;
    private JButton loadGame;
    private JButton exitGame;

    private final ActionListener ExitGame =
            event -> System.exit(0);

    private final ActionListener NewGame =
            event -> getRootPane().setContentPane(new OptionPanel());

    private final ActionListener LoadGame = event -> {
        //todo load function for serialization
    };

    public MainButtonPanel() {

        buildPanel();
    }
    
    private void buildPanel() {
        setBackground(Color.BLACK);
        setLayout(new GridLayout(3, 5, 0, 10));
        //buttons maybe make a button method to build buttons
        loadGame = new JButton("Load Game");
        newGame = new JButton("New Game");
        exitGame = new JButton("Exit Game");
        
        exitGame.addActionListener(ExitGame);
        loadGame.addActionListener(LoadGame);
        newGame.addActionListener(NewGame);


        add(loadGame);
        add(newGame);
        add(exitGame);

    }

}


