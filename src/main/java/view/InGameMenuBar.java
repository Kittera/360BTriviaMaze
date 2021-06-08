package view;

import model.Category;
import model.Difficulty;
import model.Game;

import java.io.*;
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

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    private Game currentGame;

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

    ActionListener SaveGame = event -> {
        //todo save game

        Game saveFile = currentGame;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Game");
        chooser.setCurrentDirectory(new File("/Desktop"));
        int user = chooser.showSaveDialog(null);
        if(user == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream file = new FileOutputStream(chooser.getSelectedFile() + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(saveFile);
                out.close();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    ActionListener LoadGame = event -> {
        //todo load game
        InGamePanel loading = new InGamePanel();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/Desktop"));
        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File saveFile = chooser.getSelectedFile();
                FileInputStream file = new FileInputStream(saveFile);
                ObjectInputStream in = new ObjectInputStream(file);
                Game state = (Game) in.readObject();
                loading.InGamePanelLoad(state);
                GamePanel frame = new GamePanel();
                frame.load(state);
                in.close();
                file.close();

            } catch (IOException ex) {
                System.out.println("IOException is caught");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException" +
                        " is caught");
            }
        }
    };


    ActionListener ExitGame = event -> {
        String closeMessage = "Warning: Any game data not saved will be lost on exit.";
        int reply = JOptionPane.showConfirmDialog(null, closeMessage,
                "warning", JOptionPane.YES_NO_OPTION );
        if(reply == JOptionPane.YES_OPTION) {
            System.exit(0);
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
        JOptionPane.showMessageDialog(null, messageString.toString(),
                "This", JOptionPane.INFORMATION_MESSAGE );
    }

}
