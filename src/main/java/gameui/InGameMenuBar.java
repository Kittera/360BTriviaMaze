package gameui;


import model.Category;
import model.Difficulty;
import model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class InGameMenuBar extends JPanel {


    private JOptionPane warningPane;

    public void setCurrentGame(Game currentGame) {
        if (currentGame == null) return;
        this.currentGame = currentGame;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public Game currentGame;
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

        myOptions = new JMenuItem("Options");
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

    public JMenuBar getBar() {
        return mainMenu;
    }

    ActionListener SaveGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            //todo save game
            //temp save file need to find way to grab current game
            InGamePanel game = new InGamePanel(Category.MYTHOLOGY, Difficulty.EASY);
            Game saveFile = game.InGamePanelSave();
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
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    ActionListener LoadGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
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
