package view;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class MainButtonPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;


    private final ActionListener ExitGame =
            event -> System.exit(0);

    private final ActionListener NewGame =
            event -> {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                getRootPane().setContentPane(new OptionPanel());
                topFrame.pack();
                topFrame.setLocationRelativeTo(null);
            };

    private final ActionListener LoadGame = event -> {
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
        JButton loadGame = new JButton("Load Game");
        JButton newGame = new JButton("New Game");
        JButton exitGame = new JButton("Exit Game");
        
        exitGame.addActionListener(ExitGame);
        loadGame.addActionListener(LoadGame);
        newGame.addActionListener(NewGame);

        add(loadGame);
        add(newGame);
        add(exitGame);
    }
}


