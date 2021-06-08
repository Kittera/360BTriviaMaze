package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serial;

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
        //todo load function for serialization
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


