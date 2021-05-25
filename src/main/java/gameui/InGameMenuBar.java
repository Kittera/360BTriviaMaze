package gameui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

//    static class CustomWindowListener implements WindowListener {
//        public void windowOpened(WindowEvent e) {
//        }
//
//        public void windowClosing(WindowEvent e) {
//
//        }
//
//        public void windowClosed(WindowEvent e) {
//            String closeMessage = "Warning: Any game data not saved will be lost on exit.";
//            JOptionPane warningPane = null;
//            int reply = warningPane.showConfirmDialog(null, closeMessage,
//                    "warning", JOptionPane.YES_NO_OPTION );
//            if(reply == JOptionPane.YES_OPTION) {
//                System.exit(0);
//            } else {
//
//            }
//        }
//
//        public void windowIconified(WindowEvent e) {
//        }
//
//        public void windowDeiconified(WindowEvent e) {
//        }
//
//        public void windowActivated(WindowEvent e) {
//        }
//
//        public void windowDeactivated(WindowEvent e) {
//        }
//    }


}
