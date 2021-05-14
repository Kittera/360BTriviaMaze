package gameui;




import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class InGameMenuBar extends JPanel {

    @Serial
    private static final long serialVersionUID = 3217891232321L;

    private JOptionPane warningPane;

    JMenuBar mainMenu;
    JMenu myMenu;
    JMenuItem mySaveGame, myLoadGame, myCloseGame, myAbout, myOptions;

    public InGameMenuBar() {
        mainMenu = new JMenuBar();

        myMenu = new JMenu("Main Menu");
        myOptions = new JMenu("Game Options");
        myAbout = new JMenu("About");

        myAbout.addActionListener(AboutGame);

        mainMenu.add(myMenu);

        myOptions = new JMenuItem("Options");
        myOptions.addActionListener(OptionsMenu);
        myMenu.add(myOptions);

        myAbout = new JMenuItem("About");
        myAbout.addActionListener(AboutGame);
        myMenu.add(myAbout);

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
            String closeMessage = "Warning: Any game data not saved will be lost on exit.";
            int reply = warningPane.showConfirmDialog(null, closeMessage,
                    "warning", JOptionPane.YES_NO_OPTION );
            if(reply == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {

            }
        }
    };

    ActionListener OptionsMenu = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            //todo Options
        }
    };

    ActionListener AboutGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            String aboutMessage = "I will make a file loader to load in the whole message, but I am not sure how big this information pane will be";
            warningPane.showConfirmDialog(null, aboutMessage,
                    "This", JOptionPane.INFORMATION_MESSAGE );
        }


    };


}
