package model;


import javax.swing.*;

public class MainMenu extends JPanel {

    private static final long serialVersionUID = 3217891232321L;

    JMenuBar mainMenu;
    JMenu myMenu;
    JMenuItem startGame, saveGame, loadGame, closeGame;

    public MainMenu() {
       mainMenu = new JMenuBar();
       //mainMenu.addMenuListener(new thisMenuListener());

       myMenu = new JMenu("Main Menu");
       mainMenu.add(myMenu);

       startGame = new JMenuItem("Start Game");
       myMenu.add(startGame);
       saveGame = new JMenuItem("Save Game");
       myMenu.add(saveGame);
       loadGame = new JMenuItem("Load Game");
       myMenu.add(loadGame);
       closeGame = new JMenuItem("Exit Game");
       myMenu.add(closeGame);
    }

    public JMenuBar getBar() {
        return mainMenu;
    }

}
