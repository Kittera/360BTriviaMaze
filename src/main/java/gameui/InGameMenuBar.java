package gameui;


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
            String filename = "file.ser";
            try {
                FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(currentGame);
                out.close();
                file.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ///JFileChooser fs = new JFileChooser((new File("360BTriviaMaze\saveFiles")));
            ///fs.setDialogTitle("Save Game");
            //fs.setFileFilter((new FileFilter(".txt", "Text File")));
            ///int result = fs.showSaveDialog(null);
            ///if(result == JFileChooser.APPROVE_OPTION){
                //String content = textContent.getText

            ///}
        }
    };

    ActionListener LoadGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            InGamePanel loading = new InGamePanel();
            //todo load game
            ///JFileChooser fs = new JFileChooser(new File("\360BTriviaMaze"));
            ///fs.setDialogTitle("Load Game");
            ///int result = fs.showOpenDialog((null));
            ///if (result == JFileChooser.APPROVE_OPTION) {
               /// try{ File file = fs.getSelectedFile();
                  ///  BufferedReader bRead = new BufferedReader(new FileReader(file.getPath()));
                   /// String space = "";
                    ///String space2 = "";
                    ///while ((space2 = bRead.readLine()) != null) {
                      ///  space += space2;
                    ///}
                    ///if (bRead != null) bRead.close();
                ///} catch (Exception e2) {
                   /// JOptionPane.showMessageDialog(null, e2.getMessage());
               /// }
            ///}
            try {
                FileInputStream file = new FileInputStream("file.ser");
                ObjectInputStream in = new ObjectInputStream(file);
                Game state = (Game) in.readObject();
                loading.InGamePanelLoad(state);
                GamePanel frame = new GamePanel();
                frame.load(state);
                in.close();
                file.close();

            }
            catch (IOException ex) {
                System.out.println("IOException is caught");
            }
            catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException" +
                        " is caught");
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
