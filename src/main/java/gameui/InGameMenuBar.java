package gameui;


import gameui.CloseGamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class InGameMenuBar extends JPanel {

    @Serial
    private static final long serialVersionUID = 3217891232321L;

    private final JPanel closeGamePanel;

    JMenuBar mainMenu;
    JMenu myMenu, myHints, myOptions, myAbout;
    JMenuItem mySaveGame, myLoadGame, myCloseGame;

    public InGameMenuBar() {
        mainMenu = new JMenuBar();
        closeGamePanel = new CloseGamePanel();
        //mainMenu.addMenuListener(new thisMenuListener());

        myMenu = new JMenu("Main Menu");
        myOptions = new JMenu("Game Options");
        myAbout = new JMenu("About");

        mainMenu.add(myMenu);
        mainMenu.add(myOptions);
        mainMenu.add(myAbout);

        mySaveGame = new JMenuItem("Save Game");
        mySaveGame.addActionListener(SaveGame);
        myMenu.add(mySaveGame);


        myLoadGame = new JMenuItem("Load Game");
        myLoadGame.addActionListener(LoadGame);
        myMenu.add(myLoadGame);


        myCloseGame = new JMenuItem("Exit Game");
        myCloseGame.addActionListener(ExitGame);
        myMenu.add(myCloseGame);
    }

    public JMenuBar getBar() {
        return mainMenu;
    }

    ActionListener SaveGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            //todo save game
        }
    };

    ActionListener LoadGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            //todo load game
        }
    };

    ActionListener ExitGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {



            System.exit(0);
        }
    };

    private void closeGame() {

    }

}
