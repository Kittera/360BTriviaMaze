package gameui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainButtonPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton newGame;
    private JButton loadGame;
    private JButton exitGame;

    private final ActionListener ExitGame =
            event -> System.exit(0);

    private final ActionListener NewGame =
            event -> getRootPane().setContentPane(new OptionPanel());

    private final ActionListener LoadGame = event -> {
        //todo load function for serialization
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

    public MainButtonPanel() {

        buildPanel();
    }
    
    private void buildPanel() {
        setBackground(Color.BLACK);
        setLayout(new GridLayout(3, 5, 0, 10));
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

    }

}


