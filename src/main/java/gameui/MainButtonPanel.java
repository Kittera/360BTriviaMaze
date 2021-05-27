package gameui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonPanel extends JPanel {
    private JPanel myButtonPanel;
    private ButtonGroup theGroup;
    private JButton newGame;
    private JButton loadGame;
    private JButton exitGame;

    public MainButtonPanel() {

        buildPanel();
    }
    
    private void buildPanel() {


        setBounds(200, 350, 100, 100);
        
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
        
        loadGame.setVisible(true);
        newGame.setVisible(true);
        exitGame.setVisible(true);
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
            getRootPane().setContentPane(new InGamePanel());
        }
    };
    ActionListener LoadGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            //todo load function for serialization
        }
    };

    public Component getButtonGroup() {
        return myButtonPanel;
    }
}


