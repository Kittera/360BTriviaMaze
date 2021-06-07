package gameui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This class creates a menu bar for the maze panel.
 */

public class InGameMenuBar extends JFrame {


    private JOptionPane warningPane;

    JMenuBar mainMenu;
    JMenu myFile, myHelp;
    JMenuItem mySaveGame, myLoadGame, myCloseGame, myAbout, myOptions;

    public InGameMenuBar() {
        mainMenu = new JMenuBar();

        myFile = new JMenu("File");
        myHelp = new JMenu("Help");

        myOptions = new JMenu("Game Options");
        myAbout = new JMenu("About");
        myAbout.addActionListener(AboutGame);

        mainMenu.add(myFile);
        mainMenu.add(myHelp);

        myOptions = new JMenuItem("Game Instructions");
        myOptions.addActionListener(OptionsMenu);
        myHelp.add(myOptions);

        myAbout = new JMenuItem("About");
        myAbout.addActionListener(AboutGame);
        myHelp.add(myAbout);

        mySaveGame = new JMenuItem("Save Game");
        mySaveGame.addActionListener(SaveGame);
        myFile.add(mySaveGame);


        myLoadGame = new JMenuItem("Load Game");
        myLoadGame.addActionListener(LoadGame);
        myFile.add(myLoadGame);


        myCloseGame = new JMenuItem("Exit Game");
        myCloseGame.addActionListener(ExitGame);
        myFile.add(myCloseGame);
    }

    /**
     * Getter for the menu bar
     * @return JMenuBar item
     */
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

    private final ActionListener OptionsMenu = event -> handleMessage("src/main/resources/GameInstructions");
    private final ActionListener AboutGame = event -> handleMessage("src/main/resources/About");



    private void handleMessage(String thePath) {


        StringBuilder messageString = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(thePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> messageString.append(s).append("\n"));
        } catch (IOException e){
            e.printStackTrace();
        }
        warningPane.showMessageDialog(null, messageString.toString(),
                "This", JOptionPane.INFORMATION_MESSAGE );
    }

}
