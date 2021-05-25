package gameui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonPanel {
    private JPanel myButtonPanel;
    private ButtonGroup theGroup;
    private JButton newGame;
    private JButton loadGame;
    private JButton exitGame;

    public MainButtonPanel() {
        buildPanel();
    }
    
    private void buildPanel() {
        theGroup = new ButtonGroup(); //don't need this really was a test for issue
       
        myButtonPanel = new JPanel(new FlowLayout()); //layout for button panel
        
        myButtonPanel.setBounds(200, 350, 100, 100);
        
        //buttons maybe make a button method to build buttons
        loadGame = new JButton("Load Game");
        newGame = new JButton("New Game");
        exitGame = new JButton("Exit Game");
        
        exitGame.addActionListener(ExitGame);
        loadGame.addActionListener(LoadGame);
        newGame.addActionListener(NewGame);
        
        myButtonPanel.add(loadGame);
        myButtonPanel.add(newGame);
        myButtonPanel.add(exitGame);
        // still part of the test
        theGroup.add(loadGame);
        theGroup.add(newGame);
        theGroup.add(exitGame);
        
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


