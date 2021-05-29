package gameui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonPanel extends JPanel {
    private JButton newGame;
    private JButton loadGame;
    private JButton exitGame;

    public MainButtonPanel() {

        buildPanel();
    }
    
    private void buildPanel() {
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
    
    
    ActionListener ExitGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    };
    ActionListener NewGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            getRootPane().setContentPane(new OptionPanel());
        }
    };
    ActionListener LoadGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            //todo load function for serialization
        }
    };
}


